package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsCategoryTagMapper;
import com.zhang.cms.dao.model.CmsCategoryTag;
import com.zhang.cms.dao.model.CmsCategoryTagExample;
import com.zhang.cms.rpc.api.CmsCategoryTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCategoryTagService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsCategoryTagServiceImpl extends BaseServiceImpl<CmsCategoryTagMapper, CmsCategoryTag, CmsCategoryTagExample> implements CmsCategoryTagService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryTagServiceImpl.class);

    @Autowired
    CmsCategoryTagMapper cmsCategoryTagMapper;

}