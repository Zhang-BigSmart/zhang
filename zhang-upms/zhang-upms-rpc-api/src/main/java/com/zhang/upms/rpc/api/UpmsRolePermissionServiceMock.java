package com.zhang.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.zhang.common.base.BaseServiceMock;
import com.zhang.upms.dao.mapper.UpmsRolePermissionMapper;
import com.zhang.upms.dao.model.UpmsRolePermission;
import com.zhang.upms.dao.model.UpmsRolePermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsRolePermissionService接口
* Created by zihao on 2017/7/25.
*/
public class UpmsRolePermissionServiceMock extends BaseServiceMock<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRolePermissionServiceMock.class);

    @Override
    public int rolePermission(JSONArray datas, int id) {
        _log.info("UpmsRolePermissionServiceMock => rolePermission");
        return 0;
    }
}
