package com.zhang.upms.dao;

import com.zhang.common.util.AESUtil;
import com.zhang.common.util.MybatisGeneratorUtil;
import com.zhang.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 代码生成类
 * @date 2017/7/23
 * @history
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "zhang-upms";
    private static String DATABASE = "zhang";
    private static String TABLE_PREFIX = "upms_";
    private static String PACKAGE_NAME = "com.zhang.upms";
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    // 需要insert后返回主键的表配置，key:表名,value:主键名
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();
    static {
        LAST_INSERT_ID_TABLES.put("upms_user", "user_id");
    }

    /**
     * 自动代码生成
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_PREFIX, PACKAGE_NAME, LAST_INSERT_ID_TABLES);
    }

}
