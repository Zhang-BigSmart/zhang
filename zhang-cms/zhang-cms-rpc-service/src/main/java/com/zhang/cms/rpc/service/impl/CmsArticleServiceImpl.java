package com.zhang.cms.rpc.service.impl;

import com.zhang.cms.rpc.mapper.CmsArticleExtMapper;
import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsArticleMapper;
import com.zhang.cms.dao.model.CmsArticle;
import com.zhang.cms.dao.model.CmsArticleExample;
import com.zhang.cms.rpc.api.CmsArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* CmsArticleService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

    @Autowired
    CmsArticleMapper cmsArticleMapper;

    @Autowired
    CmsArticleExtMapper cmsArticleExtMapper;

    @Override
    public List<CmsArticle> selectCmsAticlesByUserId(Integer id){
        return cmsArticleExtMapper.selectCmsArticlesByUserId(id);
    }



}