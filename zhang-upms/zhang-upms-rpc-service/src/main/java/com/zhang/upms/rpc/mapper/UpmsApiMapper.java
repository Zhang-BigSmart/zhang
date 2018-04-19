package com.zhang.upms.rpc.mapper;

import com.zhang.upms.dao.model.UpmsPermission;
import com.zhang.upms.dao.model.UpmsRole;

import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/7/25
 * @history
 */
public interface UpmsApiMapper {

    // 根据用户id获取所拥有的权限
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    // 根据用户id获取所属的角色
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

}
