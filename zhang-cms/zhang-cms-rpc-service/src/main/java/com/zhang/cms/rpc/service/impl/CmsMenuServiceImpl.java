package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsMenuMapper;
import com.zhang.cms.dao.model.CmsMenu;
import com.zhang.cms.dao.model.CmsMenuExample;
import com.zhang.cms.rpc.api.CmsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsMenuService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsMenuServiceImpl extends BaseServiceImpl<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

    private static Logger _log = LoggerFactory.getLogger(CmsMenuServiceImpl.class);

    @Autowired
    CmsMenuMapper cmsMenuMapper;

}