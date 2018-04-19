package com.zhang.cms.web.jms;

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
 * @Desc:
 * @date 2017/8/16
 * @history
 */
public class DefaultMessageQueueListener implements MessageListener {

    private static Logger _log = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            public void run() {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    _log.info("消费：{}", text);
                    System.out.println("消费：" + text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
