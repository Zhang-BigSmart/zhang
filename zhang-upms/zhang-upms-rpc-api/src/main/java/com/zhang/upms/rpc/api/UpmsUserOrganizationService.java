package com.zhang.upms.rpc.api;

import com.zhang.common.base.BaseService;
import com.zhang.upms.dao.model.UpmsUserOrganization;
import com.zhang.upms.dao.model.UpmsUserOrganizationExample;

/**
* UpmsUserOrganizationService接口
* Created by zihao on 2017/7/25.
*/
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);
}