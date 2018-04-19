package com.zhang.cms.admin.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 消费者
 * @date 2017/8/16
 * @history
 */
public class DefaultMessageQueueListener implements MessageListener{

    private static Logger _log = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void onMessage(final Message message) {
        //使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        _log.info("消费消息：{}", textMessage.getText());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
