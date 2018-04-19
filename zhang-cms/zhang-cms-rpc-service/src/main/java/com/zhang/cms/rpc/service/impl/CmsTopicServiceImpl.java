package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsTopicMapper;
import com.zhang.cms.dao.model.CmsTopic;
import com.zhang.cms.dao.model.CmsTopicExample;
import com.zhang.cms.rpc.api.CmsTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsTopicService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsTopicServiceImpl extends BaseServiceImpl<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

    private static Logger _log = LoggerFactory.getLogger(CmsTopicServiceImpl.class);

    @Autowired
    CmsTopicMapper cmsTopicMapper;

}