package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsUser;
import com.zhang.upms.dao.model.UpmsUserExample;

/**
* UpmsUserService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);
}