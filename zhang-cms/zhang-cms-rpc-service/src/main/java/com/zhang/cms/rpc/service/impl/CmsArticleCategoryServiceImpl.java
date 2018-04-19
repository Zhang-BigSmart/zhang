package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsArticleCategoryMapper;
import com.zhang.cms.dao.model.CmsArticleCategory;
import com.zhang.cms.dao.model.CmsArticleCategoryExample;
import com.zhang.cms.rpc.api.CmsArticleCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsArticleCategoryService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsArticleCategoryServiceImpl extends BaseServiceImpl<CmsArticleCategoryMapper, CmsArticleCategory, CmsArticleCategoryExample> implements CmsArticleCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleCategoryServiceImpl.class);

    @Autowired
    CmsArticleCategoryMapper cmsArticleCategoryMapper;

}