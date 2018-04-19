package com.zhang.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 资源文件读取工具
 * @date 2017/7/19
 * @history
 */
public class PropertiesFileUtil {

    private static HashMap<String, PropertiesFileUtil> configMap = new HashMap<String, PropertiesFileUtil>();

    private Date loadTime = null;

    private ResourceBundle resourceBundle = null;

    private static final String NAME = "config";

    private static final Integer TIME_OUT = 60 * 1000;

    //私有构造方法，创建单例
    private PropertiesFileUtil(String name){
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesFileUtil getInstance(){
        return getInstance(NAME);
    }

    public static synchronized  PropertiesFileUtil getInstance(String name){
        PropertiesFileUtil conf = configMap.get(name);
        if (null == conf){
            conf = new PropertiesFileUtil(name);
            configMap.put(name,conf);
        }
        //判断打开的资源文件是否超时1分钟
        if ((new Date().getTime() - conf.getLoadTime().getTime() > TIME_OUT)){
            conf = new PropertiesFileUtil(name);
            configMap.put(name,conf);
        }
        return conf;
    }

    //根据key读取value
    public String get(String key){
        try {
            String value = resourceBundle.getString(key);
            return value;
        } catch (MissingResourceException e) {
            return "";
        }
    }

    // 根据key读取value(整形)
    public Integer getInt(String key){
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        }catch (MissingResourceException e) {
            return null;
        }
    }

    // 根据key读取value(布尔)
    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        }catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }

}
