package com.zhang.common.plugin;

import com.zhang.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 支持加密配置文件插件
 * @date 2017/7/23
 * @history
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

    private String[] propertyNames = {
            "master.jdbc.password", "slave.jdbc.password", "generator.jdbc.password", "master.redis.password"
    };

    /**
     * 解密指定propertyName的加密属性值
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue){
        for(String p : propertyNames) {
            if(p.equalsIgnoreCase(propertyName)){
                return AESUtil.AESDecode(propertyValue);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}

