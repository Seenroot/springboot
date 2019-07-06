package com.seenroot.springboot.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
// 配置类的注解，声明为一个配置类
// @Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截
         * 常用的过滤器：
         *      anon: 无需认证(登录)可以访问
         *      authc: 必须认证(登录)才可以访问
         *      user: 如果使用rememberMe的功能可以直接访问
         *      perms: 该资源必须授予资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // filterChainDefinitionMap.put("/add", "authc");
        // filterChainDefinitionMap.put("/update", "authc");

        filterChainDefinitionMap.put("/testThymeleaf", "anon");

        // 放行 login
        filterChainDefinitionMap.put("/login", "anon");

        // 资源授权 要在下面的拦截所有的 前面
        // 注意： 当前授权拦截后，shiro会自动跳转到未授权的页面
        filterChainDefinitionMap.put("/add", "perms[user:add]");
        filterChainDefinitionMap.put("/update", "perms[user:update]");

        filterChainDefinitionMap.put("/*", "authc");

        // 修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        // 设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     *
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
