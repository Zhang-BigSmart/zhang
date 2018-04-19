package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsSystemMapper;
import com.zhang.cms.dao.model.CmsSystem;
import com.zhang.cms.dao.model.CmsSystemExample;
import com.zhang.cms.rpc.api.CmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSystemService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsSystemServiceImpl extends BaseServiceImpl<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(CmsSystemServiceImpl.class);

    @Autowired
    CmsSystemMapper cmsSystemMapper;

}