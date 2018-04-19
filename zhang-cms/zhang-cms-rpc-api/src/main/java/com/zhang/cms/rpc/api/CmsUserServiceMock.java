package com.zhang.cms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.cms.dao.mapper.CmsUserMapper;
import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.dao.model.CmsUserExample;

/**
* 降级实现CmsUserService接口
* Created by zihao on 2017/8/19.
*/
public class CmsUserServiceMock extends BaseServiceMock<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

}
