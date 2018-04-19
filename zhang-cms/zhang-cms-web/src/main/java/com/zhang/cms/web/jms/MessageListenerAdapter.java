package com.zhang.cms.web.jms;

import com.zhang.common.util.JsonUtil;

import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.HashMap;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/10/23
 * @history
 */
public class MessageListenerAdapter {

    public void receiveMessage(HashMap message){
        System.out.println("接收到的map对象消息");
        System.out.println(JsonUtil.Object2Json(message));
    }

    /*public void receiveMessage(Object obj){
        System.out.println("接收到的obj对象消息");
        System.out.println(JsonUtil.Object2Json(obj));
    }*/

}
