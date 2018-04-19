package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsUserRole;
import com.zhang.upms.dao.model.UpmsUserRoleExample;

/**
* UpmsUserRoleService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);
}