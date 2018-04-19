package com.zhang.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsRolePermission;
import com.zhang.upms.dao.model.UpmsRolePermissionExample;

/**
* UpmsRolePermissionService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample> {

    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}