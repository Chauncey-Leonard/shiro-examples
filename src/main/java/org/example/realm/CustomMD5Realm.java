package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义MD5 realm
 */
public class CustomMD5Realm extends AuthorizingRealm {
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从token中获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        if ("Chauncey".equals(principal)) {
            // MD5
            String password = "e10adc3949ba59abbe56e057f20f883e";
            // MD5 + Salt
            // String passwordWithSalt = "5f9dcb70720b6f58a2b219015fad5c30";
            // MD5 + Salt + Hash
            // String passwordWithSaltAndHash = "14b9f6ecccb925f15cf92c44ff9326ce";
            return new SimpleAuthenticationInfo(principal, password, this.getName());
        }
        return null;
    }
}
