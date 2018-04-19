package com.zhang.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsPermission;
import com.zhang.upms.dao.model.UpmsPermissionExample;

/**
* UpmsPermissionService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);
}