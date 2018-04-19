package com.zhang.cms.web.jms;

import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.common.util.JsonUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/10/23
 * @history
 */
public class ObjectListener implements MessageListener {

    public void onMessage(Message message) {
        if (message instanceof ObjectMessage){
            ObjectMessage objMessage = (ObjectMessage) message;
            try {
                Object obj = objMessage.getObject();
                CmsArticle cmsArticle = (CmsArticle) obj;
                System.out.println("接收到的对象："+JsonUtil.Object2Json(cmsArticle));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
