package com.zhang.common.listener;

import com.zhang.common.annotation.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc: spring容器初始化完成事件
 * @date 2017/7/18
 * @history
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent>{

    private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(null == contextRefreshedEvent.getApplicationContext().getParent()){
            _log.debug(">>>>> spring初始化完毕 <<<<<");
            Map<String, Object> services = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for(Object service : services.values()){
                _log.debug(">>>>> {}.initMapper()", service.getClass().getSimpleName());
                try {
                    Method initMapper = service.getClass().getMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    _log.error("初始化service的initMapper方法异常", e);
                    e.printStackTrace();
                }
            }

        }
    }
}
