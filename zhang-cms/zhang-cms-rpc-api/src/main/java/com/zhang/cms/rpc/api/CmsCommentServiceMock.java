package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsCommentMapper;
import com.zhang.cms.dao.model.CmsComment;
import com.zhang.cms.dao.model.CmsCommentExample;

/**
* 降级实现CmsCommentService接口
* Created by zihao on 2017/8/7.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
