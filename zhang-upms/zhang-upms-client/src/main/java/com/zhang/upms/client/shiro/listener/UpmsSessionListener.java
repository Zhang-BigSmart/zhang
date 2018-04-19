package com.zhang.upms.client.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/7/30
 * @history
 */
public class UpmsSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(UpmsSessionListener.class);

    public void onStart(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    public void onStop(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }
}
