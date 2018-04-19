package com.zhang.upms.rpc.service.impl;

import com.zhang.common.annotation.BaseService;
import com.zhang.common.base.BaseServiceImpl;
import com.zhang.upms.dao.mapper.UpmsUserRoleMapper;
import com.zhang.upms.dao.model.UpmsUserRole;
import com.zhang.upms.dao.model.UpmsUserRoleExample;
import com.zhang.upms.rpc.api.UpmsUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserRoleService实现
* Created by zihao on 2017/7/25.
*/
@Service
@Transactional
@BaseService
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceImpl.class);

    @Autowired
    UpmsUserRoleMapper upmsUserRoleMapper;

    @Override
    public int role(String[] roleIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        upmsUserRoleMapper.deleteByExample(upmsUserRoleExample);
        // 增加新纪录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(id);
                upmsUserRole.setRoleId((NumberUtils.toInt(roleId)));
                result = upmsUserRoleMapper.insertSelective(upmsUserRole);
            }
        }
        return result;
    }
}