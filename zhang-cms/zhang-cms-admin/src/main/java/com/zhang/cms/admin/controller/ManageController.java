package com.zhang.cms.admin.controller;

import com.zhang.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 后台控制器
 * @date 2017/8/9
 * @history
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台控制器", description = "后台管理")
public class ManageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    /**
     * 后台首页
     * @return
     */
    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/index.jsp";
    }



    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        Counter counter = new Counter();
        while(true){
            counter.add(random.nextInt(10));
            Thread.sleep(1000);
        }

    }

}
class Counter{

    private static int totalCount = 0;

    public int add(int num) throws InterruptedException {
        totalCount += num;
        System.out.println("====== ");
        System.out.println("parameter num:" + num);
        System.out.println("return value:" + totalCount);
        Thread.sleep(1000);
        return totalCount;
    }
}