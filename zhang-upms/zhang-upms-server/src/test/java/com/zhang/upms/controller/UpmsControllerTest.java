package com.zhang.upms.controller;

import com.zhang.common.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/13
 * @history
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/resource")
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dubbo-consumer.xml"
})
public class UpmsControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void mvcTest() throws Exception {

        Map<String,String> map = new HashMap<String,String>();
        map.put("offset","0");
        map.put("limit","3");
        map.put("search",null);
        map.put("sort",null);
        map.put("order",null);

        MvcResult result =
                        mockMvc.perform(get("/test/SessionTest"))
                                //.andExpect(status().isOk())

                                .andReturn();
        //result.getResponse()
        System.out.println(result);
    }


}
