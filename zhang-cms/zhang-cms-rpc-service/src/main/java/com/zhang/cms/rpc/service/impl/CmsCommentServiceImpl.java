package com.zhang.cms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.cms.dao.mapper.CmsCommentMapper;
import com.zhang.cms.dao.model.CmsComment;
import com.zhang.cms.dao.model.CmsCommentExample;
import com.zhang.cms.rpc.api.CmsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCommentService实现
* Created by zihao on 2017/8/7.
*/
@Service
@Transactional
@BaseService
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    CmsCommentMapper cmsCommentMapper;

}