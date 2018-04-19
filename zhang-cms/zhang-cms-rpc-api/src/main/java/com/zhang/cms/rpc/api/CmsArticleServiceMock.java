package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsArticleMapper;
import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.cms.dao.model.CmsArticleExample;

import java.util.List;

/**
* 降级实现CmsArticleService接口
* Created by zihao on 2017/8/7.
*/
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

    public List<CmsArticle> selectCmsAticlesByUserId(Integer id) {
        return null;
    }
}
