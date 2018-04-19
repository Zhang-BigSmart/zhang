package com.zhang.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 服务启动类
 * @date 2017/7/27
 * @history
 */
public class ZhangUpmsRpcServiceApplication {

    private static Logger _log = LoggerFactory.getLogger(ZhangUpmsRpcServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> zheng-upms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        _log.info(">>>>> zheng-upms-rpc-service 启动完成 <<<<<");
    }

}
