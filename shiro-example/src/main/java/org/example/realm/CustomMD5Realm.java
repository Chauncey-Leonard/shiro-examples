package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义MD5 realm
 */
public class CustomMD5Realm extends AuthorizingRealm {
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();

        // 根据当前用户信息，获取当前用户的角色信息以及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 将从数据库中查询到的角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("user");
        simpleAuthorizationInfo.addRole("admin");

        // 将数据库中查询到的权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");
        simpleAuthorizationInfo.addStringPermission("product:create");
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 从token中获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        if ("Chauncey".equals(principal)) {
            // salt
            String salt = "x0*7ps";

            // MD5
            // String password = "e10adc3949ba59abbe56e057f20f883e";

            // MD5 + Salt
            // String password = "5f9dcb70720b6f58a2b219015fad5c30";

            // MD5 + Salt + Hash
            String password = "14b9f6ecccb925f15cf92c44ff9326ce";

            /*
             * 参数一: 数据库用户名
             * 参数二: 数据库密码(md5/md5 + salt/md5 + salt + hash)
             * 参数三: 注册时的随机盐
             * 参数四: 自定义realm名字
             * */
            return new SimpleAuthenticationInfo(
                    principal,
                    password,
                    ByteSource.Util.bytes(salt),
                    this.getName()
            );
        }
        return null;
    }
}
