package com.example.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置类
 * <p>
 * Created by Chauncey on 2021/2/8 11:59
 */
@Configuration
public class ShiroConfig {

    /**
     * 3.创建shiroFilter主要用于拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        /*
         * 添加 shiro 的内置过滤器
         * anon：无需认证
         * authc：必须认证
         * user：必须拥有记住我功能才可以使用
         * perms：拥有对某个资源的权限
         * role：拥有某个角色才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联自定义realm
        defaultWebSecurityManager.setRealm(customRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 1.创建自定义realm
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

}
