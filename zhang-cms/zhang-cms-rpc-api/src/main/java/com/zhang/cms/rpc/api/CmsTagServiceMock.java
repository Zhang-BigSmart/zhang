package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsTagMapper;
import com.zhang.cms.dao.model.CmsTag;
import com.zhang.cms.dao.model.CmsTagExample;

/**
* 降级实现CmsTagService接口
* Created by zihao on 2017/8/7.
*/
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
