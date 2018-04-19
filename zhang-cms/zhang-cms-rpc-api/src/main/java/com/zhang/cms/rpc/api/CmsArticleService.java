package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseService;
import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.cms.dao.model.CmsArticleExample;

import java.util.List;

/**
* CmsArticleService接口
* Created by zihao on 2017/8/7.
*/
public interface CmsArticleService extends BaseService<CmsArticle, CmsArticleExample> {

    public List<CmsArticle> selectCmsAticlesByUserId(Integer id);

}