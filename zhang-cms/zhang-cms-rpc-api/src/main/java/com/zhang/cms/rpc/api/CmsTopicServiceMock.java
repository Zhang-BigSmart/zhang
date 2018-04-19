package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsTopicMapper;
import com.zhang.cms.dao.model.CmsTopic;
import com.zhang.cms.dao.model.CmsTopicExample;

/**
* 降级实现CmsTopicService接口
* Created by zihao on 2017/8/7.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
