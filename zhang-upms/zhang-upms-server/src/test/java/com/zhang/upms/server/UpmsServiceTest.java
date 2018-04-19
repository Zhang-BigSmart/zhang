package com.zhang.upms.server;

import com.zhang.common.util.PropertiesFileUtil;
import com.zhang.common.util.RedisUtil;
import com.zhang.upms.dao.model.UpmsRole;
import com.zhang.upms.dao.model.UpmsUser;
import com.zhang.upms.rpc.api.UpmsApiService;
import com.zhang.upms.rpc.api.UpmsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * upmsservice单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dubbo-consumer.xml"
        //"classpath:../../META-INF.spring/applicationContext-jdbc.xml"
})
//@Rollback(value = true)
//@Transactional(transactionManager = "transactionManager")
public class UpmsServiceTest {

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Test
    public void apiTest(){
        List<UpmsRole> list = upmsApiService.selectUpmsRoleByUpmsUserId(1);
        System.out.println(list.toString());
    }

    @Test
    public void userTest(){
        UpmsUser upmsUser = upmsUserService.selectByPrimaryKey(1);
        System.out.println(upmsUser.toString());
    }

    @Test
    public void test(){
        System.out.println(PropertiesFileUtil.getInstance().get("zhang-admin.version"));

    }

    @Test
    public void redisTest() {
        String ZHANG_UPMS_SERVER_SESSION_ID = "zhang-upms-server-session-id";
        String sessionId = "cef33b92-2490-4d86-ada3-51463e94d90e";
        String code = RedisUtil.get("zhang-upms-session-id_cef33b92-2490-4d86-ada3-51463e94d90e");
        System.out.println(code);
    }

}
