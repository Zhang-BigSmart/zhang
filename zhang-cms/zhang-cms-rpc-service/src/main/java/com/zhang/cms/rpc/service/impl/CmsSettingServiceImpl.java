package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsSettingMapper;
import com.zhang.cms.dao.model.CmsSetting;
import com.zhang.cms.dao.model.CmsSettingExample;
import com.zhang.cms.rpc.api.CmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSettingService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

    private static Logger _log = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

    @Autowired
    CmsSettingMapper cmsSettingMapper;

}