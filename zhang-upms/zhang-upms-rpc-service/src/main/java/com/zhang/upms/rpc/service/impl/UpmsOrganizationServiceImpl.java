package com.zhang.upms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.upms.dao.mapper.UpmsOrganizationMapper;
import com.zhang.upms.dao.model.UpmsOrganization;
import com.zhang.upms.dao.model.UpmsOrganizationExample;
import com.zhang.upms.rpc.api.UpmsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsOrganizationService实现
* Created by zihao on 2017/7/25.
*/
@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}