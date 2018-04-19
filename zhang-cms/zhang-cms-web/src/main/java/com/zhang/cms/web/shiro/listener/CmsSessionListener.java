package com.zhang.cms.web.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/21
 * @history
 */
public class CmsSessionListener implements SessionListener {

    private static Logger _log = LoggerFactory.getLogger(CmsSessionListener.class);

    public void onStart(Session session) {
        _log.debug("会话创建：" + session.getId());
    }

    public void onStop(Session session) {
        _log.debug("会话停止：" + session.getId());
    }

    public void onExpiration(Session session) {
        _log.debug("会话过期：" + session.getId());
    }
}
