package com.zhang.cms.web.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
public class CmsSessionFactory implements SessionFactory{

    public Session createSession(SessionContext sessionContext) {
        CmsSession session = new CmsSession();
        if (null != sessionContext && sessionContext instanceof WebSessionContext){
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request) {
                session.setHost(request.getRemoteAddr());
                session.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return session;
    }
}
