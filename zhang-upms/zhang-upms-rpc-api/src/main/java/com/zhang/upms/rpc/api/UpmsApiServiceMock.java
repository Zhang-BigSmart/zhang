package com.zhang.upms.rpc.api;

import com.zhang.upms.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/7/25
 * @history
 */
public class UpmsApiServiceMock implements UpmsApiService{

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceMock.class);

    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserId");
        return null;
    }

    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserIdByCache");
        return null;
    }

    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserId");
        return null;
    }

    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserIdByCache");
        return null;
    }

    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        _log.info("UpmsApiServiceMock => selectUpmsRolePermisstionByUpmsRoleId");
        return null;
    }

    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsUserPermissionByUpmsUserId");
        return null;
    }

    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        _log.info("UpmsApiServiceMock => selectUpmsSystemByExample");
        return null;
    }

    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        _log.info("UpmsApiServiceMock => selectUpmsOrganizationByExample");
        return null;
    }

    public UpmsUser selectUpmsUserByUsername(String username) {
        _log.info("UpmsApiServiceMock => selectUpmsUserByUsername");
        return null;
    }

    public int insertUpmsLogSelective(UpmsLog record) {
        _log.info("UpmsApiServiceMock => insertSelective");
        return 0;
    }
}
