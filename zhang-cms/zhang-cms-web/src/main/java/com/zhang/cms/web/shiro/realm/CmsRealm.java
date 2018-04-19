package com.zhang.cms.web.shiro.realm;

import com.zhang.cms.dao.model.CmsUser;
import com.zhang.cms.rpc.api.CmsApiService;
import com.zhang.common.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 用户认证和授权
 * @date 2017/8/21
 * @history
 */
public class CmsRealm extends AuthorizingRealm {

    private static Logger _log = LoggerFactory.getLogger(CmsRealm.class);

    @Autowired
    private CmsApiService cmsApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
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
        String password = new String((byte[]) authenticationToken.getCredentials());
        // 查询用户信息

        CmsUser cmsUser = cmsApiService.selectCmsUserByUsername(username);

        if (null == cmsUser){
            throw new UnknownAccountException();
        }
        if (!cmsUser.getPassword().equals(MD5Util.MD5(password + cmsUser.getSalt()))){
            throw new IncorrectCredentialsException();
        }
        if ("1".equals(cmsUser.getLocked())){
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("123" + "123"));
    }
}
