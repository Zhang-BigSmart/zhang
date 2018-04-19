package com.zhang.cms.web.shiro.session;

import com.zhang.common.util.RedisUtil;
import com.zhang.common.util.SerializableUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 基于redis的sessionDao，缓存共享session
 * @date 2017/8/21
 * @history
 */
public class CmsSessionDao extends CachingSessionDAO{

    private static Logger _log = LoggerFactory.getLogger(CmsSessionDao.class);

    // 会话key
    private final static String ZHANG_CMS_SHIRO_SESSION_ID = "zhang_cms_shiro_session_id";
    // 全局会话key
    private final static String ZHANG_CMS_SERVER_SESSION_ID = "";
    // 全局会话列表key
    private final static String ZHANG_CMS_SERVER_SESSION_IDS = "";
    //局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_ID = "zhang_cms_web_session_id";
    // 单点同一个code所有局部会话key
    private final static String ZHANG_CMS_WEB_SESSION_IDS = "zhang_cms_web_session_ids";


    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(ZHANG_CMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        _log.debug("doCreate >>>>> sessionId={}", sessionId);
        System.out.println("doCreate >>>>> sessionId={}"+ sessionId);
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(ZHANG_CMS_SHIRO_SESSION_ID + "_" + sessionId);
        _log.debug("doReadSession >>>>> sessionId={}", sessionId);
        System.out.println("doReadSession >>>>> sessionId={}"+ sessionId);
        return SerializableUtil.deserialize(session);
    }

    protected void doUpdate(Session session) {
        //如果会话过期/停止 不用更新
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return ;
        }
        // 更新session的最后一次访问时间
        CmsSession cmsSession = (CmsSession) session;
        CmsSession cacheCmsSession = (CmsSession) doReadSession(session.getId());
        if (null != cacheCmsSession) {
            cmsSession.setStatus(cacheCmsSession.getStatus());
            cmsSession.setAttribute("FORCE_LOGOUT", cacheCmsSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(ZHANG_CMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新ZHENG_UPMS_SERVER_SESSION_ID、ZHENG_UPMS_SERVER_CODE过期时间 TODO
        _log.debug("doUpdate >>>>> sessionId={}", session.getId());
        System.out.println("doUpdate >>>>> sessionId={}"+ session.getId());
    }

    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();

        String code = RedisUtil.get(ZHANG_CMS_WEB_SESSION_ID + "_" + sessionId);
        Jedis jedis = RedisUtil.getJedis();
        jedis.del(ZHANG_CMS_WEB_SESSION_ID + "_" + sessionId);
        jedis.srem(ZHANG_CMS_WEB_SESSION_IDS + "_" + sessionId);
        jedis.close();

        // 删除session
        RedisUtil.remove(ZHANG_CMS_SHIRO_SESSION_ID + "_" + sessionId);
        _log.debug("doDelete >>>>> sessionId={}", sessionId);
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标志，当会话访问到系统时，判断有该标志，则退出登录
            String session = RedisUtil.get(ZHANG_CMS_SHIRO_SESSION_ID + "_" + sessionId);
            CmsSession cmsSession = (CmsSession) SerializableUtil.deserialize(session);
            cmsSession.setStatus(CmsSession.OnlineStatus.force_logout);
            cmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(ZHANG_CMS_SHIRO_SESSION_ID+ "_" + sessionId, SerializableUtil.serialize(cmsSession), (int) cmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, CmsSession.OnlineStatus onlineStatus){
        CmsSession session = (CmsSession) doReadSession(sessionId);
        if (null == session)
            return;
        session.setStatus(onlineStatus);
        RedisUtil.set(ZHANG_CMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
