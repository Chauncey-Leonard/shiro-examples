package com.example.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置类
 * <p>
 * Created by Chauncey on 2021/2/8 11:59
 */
@Configuration
public class ShiroConfig {

    /**
     * 1.创建shiroFilter主要用于拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        return new ShiroFilterFactoryBean();
    }

    /**
     * 2.创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager() {
        return new DefaultWebSecurityManager();
    }

    /**
     * 3.创建自定义realm
     */

}
