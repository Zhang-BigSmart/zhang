package com.zhang.upms.server.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/3
 * @history
 */
@Controller
@RequestMapping("/test")
@Api(value = "测试", description = "测试")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/SessionTest")
    public String SessionTest(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(session.getAttribute("count"));
        String count = ""+session.getAttribute("count");
        System.out.println("---"+count);
        if (StringUtils.isBlank(count)){
            session.setAttribute("count",1);
        }else{
            session.setAttribute("count",Integer.parseInt(count)+1);
        }

        return "SessionTest";
    }

}
