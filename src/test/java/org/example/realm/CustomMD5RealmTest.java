package org.example.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class CustomMD5RealmTest {
    @Test
    public void test() {
        // 创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 创建自定义MD5 realm对象
        CustomMD5Realm realm = new CustomMD5Realm();

        // 创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        // 设置算法名称
        credentialsMatcher.setHashAlgorithmName("md5");

        // 设置hash散列
        credentialsMatcher.setHashIterations(1024);

        // 设置realm使用hash凭证匹配器
        realm.setCredentialsMatcher(credentialsMatcher);

        // 注入自定义MD5 realm
        defaultSecurityManager.setRealm(realm);

        // 将安全管理器注入安全管理工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 通过安全管理工具类获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("Chauncey", "123456");

        // 用户认证
        try {
            subject.login(token);
            System.out.println("认证成功！");
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！");
        }

        // 认证用户进行授权
        if (subject.isAuthenticated()) {
            // 基于角色权限控制
            System.out.println(subject.hasRole("admin"));

            // 基于多角色权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));

            // 是否具有其中的一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user", "super"));
            for (boolean aBoolean : booleans) {
                System.out.println("aBoolean = " + aBoolean);
            }

            System.out.println("================== 权限 ======================");

            // 基于权限字符串的访问控制 资源标识符:操作:资源类型
            System.out.println(subject.isPermitted("user:*:01"));
            System.out.println(subject.isPermitted("product:create"));

            // 分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:10");
            for (boolean b : permitted) {
                System.out.println("b = " + b);
            }
        }
    }
}
