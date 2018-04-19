package com.zhang.upms.common.constant;

import com.zhang.common.base.BaseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edison
 * @ClassName:
 * @Desc: upms系统常量类
 * @date 2017/7/23
 * @history
 */
public class UpmsConstant extends BaseConstants {

    public static final String UPMS_TYPE = "upms.type";

    // 全局会话key
    public final static String ZHANG_UPMS_SERVER_SESSION_ID = "zhang-upms-server-session-id";
    // 全局会话key列表
    public final static String ZHANG_UPMS_SERVER_SESSION_IDS = "zhang-upms-server-session-ids";
    // code key
    public final static String ZHANG_UPMS_SERVER_CODE = "zhang-upms-server-code";

    // 会话key
    public final static String ZHANG_UPMS_SHIRO_SESSION_ID = "zhang-upms-shiro-session-id";
    // 局部会话key
    public final static String ZHANG_UPMS_CLIENT_SESSION_ID = "zhang-upms-client-session-id";
    // 单点同一个code所有局部会话key
    public final static String ZHANG_UPMS_CLIENT_SESSION_IDS = "zhang-upms-client-session-ids";

}
