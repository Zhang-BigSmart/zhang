package com.zhang.upms.client.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhang.common.util.PropertiesFileUtil;
import com.zhang.common.util.RedisUtil;
import com.zhang.upms.client.shiro.session.UpmsSessionDao;
import com.zhang.upms.client.util.RequestParameterUtil;
import com.zhang.upms.common.constant.UpmsConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 重写authc过滤器
 * @date 2017/7/30
 * @history
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter{

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    // 局部会话key
    private final static String ZHANG_UPMS_CLIENT_SESSION_ID = "zhang-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String ZHANG_UPMS_CLIENT_SESSION_IDS = "zhang-upms-client-session-ids";

    @Autowired
    UpmsSessionDao upmsSessionDao;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 判断请求类型
        String upmsType = PropertiesFileUtil.getInstance("zhang-upms-client").get("upms.type");
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        if ("client".equals(upmsType)) {
                return validateClient(request, response);
        }
        if ("server".equals(upmsType)) {
            return subject.isAuthenticated();
        }
        return false;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("zhang-upms-client").get("sso.server.url"));
        // server需要登录
        String upmsType = PropertiesFileUtil.getInstance("zhang-upms-client").get("upms.type");
        if ("server".equals(upmsType)) {
            WebUtils.toHttp(servletResponse).sendRedirect(sso_server_url.append("/sso/login").toString());
            return false;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(PropertiesFileUtil.getInstance("zhang-upms-client").get("appID"));
        // 回跳地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        WebUtils.toHttp(servletResponse).sendRedirect(sso_server_url.toString());
        return false;
    }

    /**
     * 认证中心登陆成功带回code
     * @param request
     * @param response
     * @return
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) (session.getTimeout() / 1000);
        // 判断局部会话是否登录
        String cacheClientSession = RedisUtil.get(ZHANG_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
        if (StringUtils.isNotBlank(cacheClientSession)){
            // 更新code有效期
            RedisUtil.set(ZHANG_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(ZHANG_UPMS_CLIENT_SESSION_ID + "_" + sessionId, timeOut);
            jedis.close();
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                    httpServletResponse.sendRedirect(backUrl.toString());
                } catch (IOException e) {
                    _log.error("局部会话已登录，移除code参数跳转出错：", e);
                }
            }else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");
        // 已拿到code
        if (StringUtils.isNotBlank(code)){
            // HttpPost去校验code
            StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("zhang-upms-client").get("sso.server.url"));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/code");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    System.out.println(result);
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
                        // code校验正确，创建局部会话
                        RedisUtil.set(ZHANG_UPMS_CLIENT_SESSION_ID + "_" + sessionId, code, timeOut);
                        // 保存code对应的局部会话sessionId，方便退出操作
                        RedisUtil.sadd(ZHANG_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId, timeOut);
                        _log.debug("当前code={}，对应的注册系统个数：{}个", code, RedisUtil.getJedis().scard(ZHANG_UPMS_CLIENT_SESSION_IDS + "_" + code));
                        // 移除url中的token参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        try {
                            String username = request.getParameter("upms_username");
                            subject.login(new UsernamePasswordToken(username, ""));
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl.toString());
                            return true;
                        }catch (IOException e) {
                            _log.error("已拿到code，移除code参数跳转出错：", e);
                        }
                    }else {
                        _log.warn(result.getString("data"));
                    }
                }
            } catch (IOException e) {
                _log.error("验证token失败：", e);
            }
        }
        return false;
    }
}
