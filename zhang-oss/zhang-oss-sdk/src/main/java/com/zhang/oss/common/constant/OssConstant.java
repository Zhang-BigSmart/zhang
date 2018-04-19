package com.zhang.oss.common.constant;

import com.zhang.common.base.BaseConstants;
import com.zhang.common.util.PropertiesFileUtil;

/**
 * @author Edison
 * @ClassName:
 * @Desc:oss系统常量类
 * @date 2017/10/15
 * @history
 */
public class OssConstant extends BaseConstants {

    // endpoint
    public static final String ALIYUN_OSS_ENDPOINT = PropertiesFileUtil.getInstance("config").get("aliyun.oss.endpoint");

    // bucketName
    public static final String ALIYUN_OSS_BUCKET_NAME = PropertiesFileUtil.getInstance("config").get("aliyun.oss.bucketName");

    // 文件大小
    public static final int ALIYUN_OSS_MAX_SIZE = PropertiesFileUtil.getInstance("config").getInt("aliyun.oss.maxSize");

    // 签名有效期(单位:分钟)
    public static final int ALIYUN_OSS_EXPIRE = PropertiesFileUtil.getInstance("config").getInt("aliyun.oss.policy.expire");
}
