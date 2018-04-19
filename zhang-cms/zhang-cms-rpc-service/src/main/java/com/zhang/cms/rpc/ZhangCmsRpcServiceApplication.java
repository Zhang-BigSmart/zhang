package com.zhang.cms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/7
 * @history
 */
public class ZhangCmsRpcServiceApplication {

    private static Logger _log = LoggerFactory.getLogger(ZhangCmsRpcServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> zhang-cms-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        _log.info(">>>>> zhang-cms-rpc-service 启动完成 <<<<<");
    }

}
