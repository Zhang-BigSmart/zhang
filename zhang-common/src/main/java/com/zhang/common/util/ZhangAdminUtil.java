package com.zhang.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 启动解压zhangAdmin-x.x.x.jar到resources目录
 * @date 2017/7/31
 * @history
 */
public class ZhangAdminUtil implements InitializingBean, ServletContextAware{

    private static Logger _log = LoggerFactory.getLogger(ZhangAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压zhang-admin =====");
        String version = PropertiesFileUtil.getInstance().get("zhang-admin.version");
        _log.info("zhang-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/zhang-admin-" + version + ".jar");
        _log.info("zhang-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/zhang-admin";
        _log.info("zhang-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压zhang-admin完成 =====");
    }
}
