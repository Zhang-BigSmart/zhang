package com.zhang.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsUserPermission;
import com.zhang.upms.dao.model.UpmsUserPermissionExample;

/**
* UpmsUserPermissionService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermission, UpmsUserPermissionExample> {

    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}