package com.zhang.upms.server.controller.manage;

import com.zhang.common.base.BaseController;
import com.zhang.upms.common.constant.UpmsResult;
import com.zhang.upms.common.constant.UpmsResultConstant;
import com.zhang.upms.dao.model.UpmsSystem;
import com.zhang.upms.dao.model.UpmsSystemExample;
import com.zhang.upms.rpc.api.UpmsSystemService;
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
 * @Desc: 系统控制器
 * @date 2017/8/3
 * @history
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/system")
public class UpmsSystemController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsSystemController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @ApiOperation(value = "系统首页")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/system/index.jsp";
    }

    @ApiOperation(value = "系统列表")
    @RequiresPermissions("upms:system:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        System.out.println("in");
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)){
            upmsSystemExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)){
            upmsSystemExample.or().andTitleLike("%" + search + "%");
        }
        List<UpmsSystem> rows = upmsSystemService.selectByExampleForOffsetPage(upmsSystemExample, offset, limit);
        long total = upmsSystemService.countByExample(upmsSystemExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/system/create.jsp";
    }

    @ApiOperation(value = "新增系统")
    @RequiresPermissions("upms:system:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(UpmsSystem upmsSystem) {

        long time = System.currentTimeMillis();
        upmsSystem.setCtime(time);
        upmsSystem.setOrders(time);
        int count = upmsSystemService.insertSelective(upmsSystem);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除系统")
    @RequiresPermissions("upms:system:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids){
        int count = upmsSystemService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS,count);
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsSystem system = upmsSystemService.selectByPrimaryKey(id);
        modelMap.put("system", system);
        return "/manage/system/update.jsp";
    }

    @ApiOperation(value = "修改系统")
    @RequiresPermissions("upms:system:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsSystem upmsSystem) {

        upmsSystem.setSystemId(id);
        int count = upmsSystemService.updateByPrimaryKeySelective(upmsSystem);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
