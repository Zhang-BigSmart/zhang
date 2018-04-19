package com.zhang.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zhang.cms.common.constant.CmsResult;
import com.zhang.cms.common.constant.CmsResultConstant;
import com.zhang.cms.dao.model.CmsSetting;
import com.zhang.cms.dao.model.CmsSettingExample;
import com.zhang.cms.rpc.api.CmsSettingService;
import com.zhang.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 设置controller
 * @date 2017/8/13
 * @history
 */
@Controller
@Api(value = "设置管理", description = "设置管理")
@RequestMapping("/manage/setting")
public class CmsSettingController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(CmsSettingController.class);

    @Autowired
    private CmsSettingService cmsSettingService;

    @ApiOperation(value = "评论首页")
    @RequiresPermissions("cms:setting:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/setting/index.jsp";
    }

    @ApiOperation(value = "评论列表")
    @RequiresPermissions("cms:setting:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        CmsSettingExample cmsSettingExample = new CmsSettingExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            cmsSettingExample.setOrderByClause(sort + " " + order);
        }
        List<CmsSetting> rows = cmsSettingService.selectByExampleForOffsetPage(cmsSettingExample, offset, limit);
        long total = cmsSettingService.countByExample(cmsSettingExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增设置")
    @RequiresPermissions("cms:setting:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/setting/create.jsp";
    }

    @ApiOperation(value = "新增设置")
    @RequiresPermissions("cms:setting:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(CmsSetting cmsSetting) {

        int count = cmsSettingService.insertSelective(cmsSetting);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除设置")
    @RequiresPermissions("cms:setting:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = cmsSettingService.deleteByPrimaryKeys(ids);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改设置")
    @RequiresPermissions("cms:setting:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsSetting setting = cmsSettingService.selectByPrimaryKey(id);
        modelMap.put("setting", setting);
        return "/manage/setting/update.jsp";
    }

    @ApiOperation(value = "修改设置")
    @RequiresPermissions("cms:setting:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, CmsSetting cmsSetting) {

        cmsSetting.setSettingId(id);
        int count = cmsSettingService.updateByPrimaryKeySelective(cmsSetting);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

}
