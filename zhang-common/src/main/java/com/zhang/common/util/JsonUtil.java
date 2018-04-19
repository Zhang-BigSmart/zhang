package com.zhang.common.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/13
 * @history
 */
public class JsonUtil {

    /**
     * bean转json
     * @param obj
     * @return
     */
    public static String Object2Json(Object obj){
        if (obj == null)
            return null;
        return JSONObject.toJSONString(obj);
    }

    /**
     * JSON转bean
     * @param json
     * @param valueType
     * @param <T>
     */
    public static <T> T Json2Object(String json, Class<T> valueType){
        if (StringUtils.isBlank(json)) {
            return null;
        }
        if (valueType == null) {
            return null;
        }
        return JSONObject.parseObject(json, valueType);
    }

    /**
     * JSON 转 MAP
     * @param json
     * @return
     */
    public static Map<String, String> Json2Map(String json){
        if (StringUtils.isBlank(json))
            return null;
        return (Map<String, String>) JSONObject.parse(json);
    }

}
