package com.zhang.upms.client.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 强制退出会话过滤器
 * @date 2017/7/30
 * @history
 */
public class UpmsSessionForceLogoutFilter extends AccessControlFilter {


    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception {
        Session session = getSubject(request,response).getSession(false);
        if (session == null){
            return true;
        }
        boolean forceout = session.getAttribute("FORCE_LOGOUT") == null;
        return forceout;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        getSubject(request, response).logout();
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
