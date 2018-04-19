package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsUserArticleMapper;
import com.zhang.cms.dao.model.CmsUserArticle;
import com.zhang.cms.dao.model.CmsUserArticleExample;
import com.zhang.cms.rpc.api.CmsUserArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsUserArticleService实现
* Created by zihao on 2017/8/19.
*/
@Service
@Transactional
@BaseService
public class CmsUserArticleServiceImpl extends BaseServiceImpl<CmsUserArticleMapper, CmsUserArticle, CmsUserArticleExample> implements CmsUserArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsUserArticleServiceImpl.class);

    @Autowired
    CmsUserArticleMapper cmsUserArticleMapper;

}