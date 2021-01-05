package org.example.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 自定义realm测试
 */
public class CustomRealmTest {

    @Test
    public void test() {
        // 创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 设置realm
        defaultSecurityManager.setRealm(new CustomRealm());
        // 将安全管理器注入安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("Chauncey", "123456");
        // 用户认证
        try {
            subject.login(token);
            System.out.println("认证成功");
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！");
        }
    }
}
