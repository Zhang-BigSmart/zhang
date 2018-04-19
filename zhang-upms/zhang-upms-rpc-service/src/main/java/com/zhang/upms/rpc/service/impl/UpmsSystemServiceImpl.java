package com.zhang.upms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.upms.dao.mapper.UpmsSystemMapper;
import com.zhang.upms.dao.model.UpmsSystem;
import com.zhang.upms.dao.model.UpmsSystemExample;
import com.zhang.upms.rpc.api.UpmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsSystemService实现
* Created by zihao on 2017/7/25.
*/
@Service
@Transactional
@BaseService
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

}