package com.zhang.upms.client.util;

import com.alibaba.dubbo.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc: request参数工具类
 * @date 2017/7/30
 * @history
 */
public class RequestParameterUtil {

    /**
     * 移除url中的code、username参数
     * @param request
     * @return
     */
    public static String getParameterWithOutCode(HttpServletRequest request) {
        StringBuffer backUrl = request.getRequestURL();
        String params = "";
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            if (!entry.getKey().equals("upms_code") && !entry.getKey().equals("upms_username")){
                if (params.equals("")){
                    params = entry.getKey() + "=" + entry.getValue()[0];
                }else{
                    params += "&" + entry.getKey() + "=" + entry.getValue()[0];
                }
            }
        }
        if (!StringUtils.isBlank(params)){
            backUrl = backUrl.append("?").append(params);
        }
        return backUrl.toString();
    }

}
