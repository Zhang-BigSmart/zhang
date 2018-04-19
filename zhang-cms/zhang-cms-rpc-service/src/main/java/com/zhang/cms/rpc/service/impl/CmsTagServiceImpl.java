package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsTagMapper;
import com.zhang.cms.dao.model.CmsTag;
import com.zhang.cms.dao.model.CmsTagExample;
import com.zhang.cms.rpc.api.CmsTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsTagService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

    @Autowired
    CmsTagMapper cmsTagMapper;

}