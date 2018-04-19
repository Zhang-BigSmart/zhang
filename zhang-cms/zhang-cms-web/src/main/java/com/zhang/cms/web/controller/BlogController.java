package com.zhang.cms.web.controller;

import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.cms.dao.model.CmsArticleExample;
import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.rpc.api.CmsApiService;
import com.zhang.cms.rpc.api.CmsArticleService;
import com.zhang.cms.rpc.api.CmsUserService;
import com.zhang.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 个人主页controller
 * @date 2017/8/17
 * @history
 */
@Controller
@Api(value = "个人主页", description = "个人主页")
@RequestMapping("/cms/personal")
public class BlogController extends BaseController{

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsApiService cmsApiService;

    @ApiOperation(value = "个人博客列表")
    @RequestMapping(value = "/bloglist", method = RequestMethod.GET)
    public String bloglist(HttpServletRequest request, ModelMap map,
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order){

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        CmsUser cmsUser = cmsApiService.selectCmsUserByUsername(username);

        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.setOrderByClause("ctime desc");
        cmsArticleExample.createCriteria().andUserIdEqualTo(cmsUser.getUserId());

        List<CmsArticle> rows = cmsArticleService.selectByExampleWithBLOBsForOffsetPage(cmsArticleExample,offset,limit);
        List<CmsArticle> articleList = new ArrayList<CmsArticle>();
        for (CmsArticle article : rows){
            if (StringUtils.isNotBlank(article.getContent()) && article.getContent().length()>100){
                String s = article.getContent();
                article.setContent(s.substring(0,100));
            }
            articleList.add(article);
        }
        map.put("articleList",articleList);
        return "personal/bloglist";
    }

    @ApiOperation(value = "个人博客")
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blog(HttpServletRequest request){
        System.out.println("test==-blog");
        return "personal/blog";
    }

}
