package org.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * shiro的配置文件主要是用来学习shiro时书写我们系统中相关的权限数据
 * 配置文件以.ini结尾
 *
 * 步骤:
 *   1.创建默认安全管理器
 *   2。给安全管理器设置realm
 *   3.将默认安全管理器注入安全管理工具类中
 *   4.获取主体对象
 *   5.创建令牌
 *   6.用户认证
 */
public class App {
    public static void main(String[] args) {
        // 1.创建安全管理器对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 2.给安全管理器设置realm
        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        // 3.为全局安全工具类设置安全管理器对象
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 4.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("Chauncey", "123456");

        // 6.用户认证
        try {
            System.out.println("认证状态: " + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态: " + subject.isAuthenticated());
            System.out.println("认证成功");
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }
    }
}
