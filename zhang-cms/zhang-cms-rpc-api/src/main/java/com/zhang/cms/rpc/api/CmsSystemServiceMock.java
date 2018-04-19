package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsSystemMapper;
import com.zhang.cms.dao.model.CmsSystem;
import com.zhang.cms.dao.model.CmsSystemExample;

/**
* 降级实现CmsSystemService接口
* Created by zihao on 2017/8/7.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
