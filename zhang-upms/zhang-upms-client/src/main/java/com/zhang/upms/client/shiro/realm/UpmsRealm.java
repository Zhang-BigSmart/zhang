package com.zhang.upms.client.shiro.realm;


import com.zhang.common.util.MD5Util;
import com.zhang.common.util.PropertiesFileUtil;
import com.zhang.upms.dao.model.UpmsPermission;
import com.zhang.upms.dao.model.UpmsRole;
import com.zhang.upms.dao.model.UpmsUser;
import com.zhang.upms.rpc.api.UpmsApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 用户认证和授权
 * @date 2017/7/29
 * @history
 */
public class UpmsRealm extends AuthorizingRealm {

    private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    @Autowired
    private UpmsApiService upmsApiService;

    /**
     *  授权： 验证权限时调用
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<String>();
        for (UpmsRole upmsRole : upmsRoles){
            if(StringUtils.isNotBlank(upmsRole.getName())){
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<String>();
        for (UpmsPermission upmsPermission : upmsPermissions){
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String upmsType = PropertiesFileUtil.getInstance("zhang-upms-client").get("upms.type");
        if ("client".equals(upmsType)){
            return new SimpleAuthenticationInfo(username, password, getName());
        }

        //查询用户信息
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        if (null == upmsUser){
            throw new UnknownAccountException();
        }
        if (!upmsUser.getPassword().equals(MD5Util.MD5(password + upmsUser.getSalt()))){
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getLocked() == 1){
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
