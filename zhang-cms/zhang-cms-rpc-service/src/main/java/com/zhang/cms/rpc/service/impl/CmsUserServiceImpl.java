package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsUserMapper;
import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.dao.model.CmsUserExample;
import com.zhang.cms.rpc.api.CmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsUserService实现
* Created by zihao on 2017/8/19.
*/
@Service
@Transactional
@BaseService
public class CmsUserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

    private static Logger _log = LoggerFactory.getLogger(CmsUserServiceImpl.class);

    @Autowired
    CmsUserMapper cmsUserMapper;

}