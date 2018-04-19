package com.zhang.cms.web.shiro.filter;

import com.zhang.cms.web.shiro.session.CmsSessionDao;
import com.zhang.common.util.PropertiesFileUtil;
import com.zhang.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
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
import java.net.URLEncoder;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 重写authc过滤器
 * @date 2017/8/20
 * @history
 */
public class CmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger _log = LoggerFactory.getLogger(CmsAuthenticationFilter.class);

    //局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_ID = "zhang_cms_web_session_id";
    // 单点同一个code所有局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_IDS = "zhang_cms_web_session_ids";

    @Autowired
    private CmsSessionDao cmsSessionDao;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request,response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) (session.getTimeout() / 1000);
        // 判断据不回话是否登录
        String cacheClientSession = RedisUtil.get(ZHANG_CMS_WEB_SESSION_ID + "_" + sessionId);
        if (StringUtils.isNotBlank(cacheClientSession)) {
            RedisUtil.set(ZHANG_CMS_WEB_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(ZHANG_CMS_WEB_SESSION_IDS + "_" + cacheClientSession, timeOut);
            jedis.close();

            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer login_url = new StringBuffer(PropertiesFileUtil.getInstance("zhang-cms-web").get("cms.loginUrl"));

        //回调地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)){
            backurl.append("?").append(queryString);
        }
        login_url.append("?").append("backurl=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        WebUtils.toHttp(response).sendRedirect(login_url.toString());
        System.out.println(login_url.toString());
        return false;
    }
}
