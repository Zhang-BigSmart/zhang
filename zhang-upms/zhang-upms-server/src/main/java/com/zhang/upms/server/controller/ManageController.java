package com.zhang.upms.server.controller;

import com.zhang.common.base.BaseController;
import com.zhang.upms.dao.model.UpmsPermission;
import com.zhang.upms.dao.model.UpmsSystem;
import com.zhang.upms.dao.model.UpmsSystemExample;
import com.zhang.upms.dao.model.UpmsUser;
import com.zhang.upms.rpc.api.UpmsApiService;
import com.zhang.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 后台controller
 * @date 2017/8/3
 * @history
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        modelMap.put("upmsSystems", upmsSystems);
        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        modelMap.put("upmsPermissions", upmsPermissions);
        return "/manage/index.jsp";
    }


}
