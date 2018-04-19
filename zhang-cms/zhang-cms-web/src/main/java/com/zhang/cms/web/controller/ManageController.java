package com.zhang.cms.web.controller;

import com.zhang.cms.common.constant.CmsResult;
import com.zhang.cms.common.constant.CmsResultConstant;
import com.zhang.cms.web.shiro.session.CmsSession;
import com.zhang.cms.web.shiro.session.CmsSessionDao;
import com.zhang.common.base.BaseController;
import com.zhang.common.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
@Controller
@Api(value = "登录管理", description = "登录管理")
/*@RequestMapping("/cms")*/
public class ManageController extends BaseController{

    //局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_ID = "zhang_cms_web_session_id";
    // 单点同一个code所有局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_IDS = "zhang_cms_web_session_ids";

    @Autowired
    CmsSessionDao cmsSessionDao;


    @ApiOperation(value = "首页")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        return "redirect:cms/personal/bloglist";

    }


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //Session session = subject.getSession();
        //String serverSessionId = session.getId().toString();

        // 判断是否已登录
        //System.out.println("===="+RequestUtil.getBasePath(request));
        return "login";
    }


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            return new CmsResult(CmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new CmsResult(CmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisUtil.get(ZHANG_CMS_WEB_SESSION_ID + "_" + sessionId);
        // 登录
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try{
                if (BooleanUtils.toBoolean(rememberMe))
                    usernamePasswordToken.setRememberMe(true);
                else
                    usernamePasswordToken.setRememberMe(false);
            }catch(UnknownAccountException e){
                return new CmsResult(CmsResultConstant.INVALID_USERNAME, "帐号不存在！");
            }catch (IncorrectCredentialsException e) {
                return new CmsResult(CmsResultConstant.INVALID_PASSWORD, "密码错误！");
            } catch (LockedAccountException e) {
                return new CmsResult(CmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
            }
            // 更新session状态
            cmsSessionDao.updateStatus(sessionId, CmsSession.OnlineStatus.on_line);
            // 全局会话sessioId列表，供会话管理
            RedisUtil.lpush(ZHANG_CMS_WEB_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            //　全局会话 code
            RedisUtil.set(ZHANG_CMS_WEB_SESSION_ID+ "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {
            return new CmsResult(CmsResultConstant.SUCCESS, "/");
        } else {
            return new CmsResult(CmsResultConstant.SUCCESS, backurl);
        }
    }

    public String logout(HttpServletRequest request){
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }


}
