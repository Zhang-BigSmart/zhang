package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseServiceMock;
import com.zhang.upms.dao.mapper.UpmsUserRoleMapper;
import com.zhang.upms.dao.model.UpmsUserRole;
import com.zhang.upms.dao.model.UpmsUserRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserRoleService接口
* Created by zihao on 2017/7/25.
*/
public class UpmsUserRoleServiceMock extends BaseServiceMock<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceMock.class);

    @Override
    public int role(String[] roleIds, int id) {
        _log.info("UpmsUserRoleServiceMock => role");
        return 0;
    }
}
