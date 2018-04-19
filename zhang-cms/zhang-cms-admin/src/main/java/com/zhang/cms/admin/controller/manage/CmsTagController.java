package com.zhang.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zhang.cms.common.constant.CmsResult;
import com.zhang.cms.common.constant.CmsResultConstant;
import com.zhang.cms.dao.model.CmsTag;
import com.zhang.cms.dao.model.CmsTagExample;
import com.zhang.cms.rpc.api.CmsTagService;
import com.zhang.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 标签controller
 * @date 2017/8/13
 * @history
 */
@Controller
@Api(value = "标签管理", description = "标签管理")
@RequestMapping("/manage/tag")
public class CmsTagController extends BaseController{

    private static Logger _log = LoggerFactory.getLogger(CmsTagController.class);

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueDestination;

    @ApiOperation(value = "标签首页")
    @RequiresPermissions("cms:tag:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        sendMessage(queueDestination, "hello");
        return "/manage/tag/index.jsp";
    }

    @ApiOperation(value = "标签列表")
    @RequiresPermissions("cms:tag:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        CmsTagExample cmsTagExample = new CmsTagExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isNotBlank(order)){
            cmsTagExample.setOrderByClause(sort + " " + order);
        }
        List<CmsTag> rows = cmsTagService.selectByExampleForOffsetPage(cmsTagExample, offset, limit);
        long total = cmsTagService.countByExample(cmsTagExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增标签")
    @RequiresPermissions("cms:tag:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/tag/create.jsp";
    }

    @ApiOperation(value = "新增标签")
    @RequiresPermissions("cms:tag:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(CmsTag cmsTag) {

        long time = System.currentTimeMillis();
        cmsTag.setCtime(time);
        cmsTag.setOrders(time);
        int count = cmsTagService.insertSelective(cmsTag);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除标签")
    @RequiresPermissions("cms:tag:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = cmsTagService.deleteByPrimaryKeys(ids);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改标签")
    @RequiresPermissions("cms:tag:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsTag tag = cmsTagService.selectByPrimaryKey(id);
        modelMap.put("tag", tag);
        return "/manage/tag/update.jsp";
    }

    @ApiOperation(value = "修改标签")
    @RequiresPermissions("cms:tag:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, CmsTag cmsTag) {

        cmsTag.setTagId(id);
        int count = cmsTagService.updateByPrimaryKeySelective(cmsTag);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
