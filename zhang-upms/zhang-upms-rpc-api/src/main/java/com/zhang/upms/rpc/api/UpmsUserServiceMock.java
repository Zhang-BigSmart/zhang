package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.upms.dao.mapper.UpmsUserMapper;
import com.zhang.upms.dao.model.UpmsUser;
import com.zhang.upms.dao.model.UpmsUserExample;

/**
* 降级实现UpmsUserService接口
* Created by zihao on 2017/7/25.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }
}
