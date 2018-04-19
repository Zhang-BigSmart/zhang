package com.zhang.upms.client.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edison
 * @ClassName:
 * @Desc: session工厂
 * @date 2017/7/30
 * @history
 */
public class UpmsSessionFactory implements SessionFactory {

    public Session createSession(SessionContext sessionContext) {
        UpmsSession session = new UpmsSession();
        if (null != sessionContext && sessionContext instanceof WebSessionContext){
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request){
                session.setHost(request.getRemoteHost());
                session.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return session;
    }
}
