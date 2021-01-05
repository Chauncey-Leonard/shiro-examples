package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm, 将认证、授权数据来源转换为数据库
 */
public class CustomRealm extends AuthorizingRealm {
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
        // 根据用户名查询数据库
        if ("Chauncey".equals(principal)) {
            /*
             * 参数一: 返回数据库中的用户名
             * 参数二: 数据库中的密码
             * 参数三: 当前realm的名称
             */
            return new SimpleAuthenticationInfo(principal, "123456", this.getName());
        }
        return null;
    }
}
