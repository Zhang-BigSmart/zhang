package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsPageMapper;
import com.zhang.cms.dao.model.CmsPage;
import com.zhang.cms.dao.model.CmsPageExample;
import com.zhang.cms.rpc.api.CmsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsPageService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsPageServiceImpl extends BaseServiceImpl<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

    private static Logger _log = LoggerFactory.getLogger(CmsPageServiceImpl.class);

    @Autowired
    CmsPageMapper cmsPageMapper;

}