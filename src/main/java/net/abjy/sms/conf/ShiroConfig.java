package net.abjy.sms.conf;


import net.abjy.sms.redis.RedisServiceImpl;
import net.abjy.sms.redis.RedisSessionDAO;
import net.abjy.sms.service.impl.RolesPermissionsServiceImpl;
import net.abjy.sms.shiro.CustomRolesAuthorizationFilter;
import net.abjy.sms.shiro.ShiroPermissionFactory;
import net.abjy.sms.shiro.UserShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置类
 * Created by Administrator on 2018/9/5.
 */
@Configuration
public class ShiroConfig {

    //将自己的验证方式加入容器
    @Bean
    public UserShiroRealm userShiroRealm() {
        return new UserShiroRealm();
    }

    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("SHRIOSESSIONID");
        return simpleCookie;
    }

    //保证实现了Shiro内部lifecycle函数的bean执行
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //配置shiro session 的一个管理器
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setGlobalSessionTimeout(-1000);  //session有效期 默认值1800000 30分钟 1800000毫秒  -1000表示永久
        SimpleCookie simpleCookie = getSimpleCookie();
        simpleCookie.setHttpOnly(true);                 //设置js不可读取此Cookie
        simpleCookie.setMaxAge(3 * 365 * 24 * 60 * 60); //3年 cookie有前期
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("userShiroRealm") UserShiroRealm userShiroRealm,
                                           @Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userShiroRealm);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroPermissionFactory shiroPermissionFactory(RedisServiceImpl redisCache) {
        ShiroPermissionFactory shiroFilter = new ShiroPermissionFactory(new RolesPermissionsServiceImpl());
        shiroFilter.setSecurityManager(securityManager());

        //登录认证不通过跳转
        shiroFilter.setLoginUrl("/loginUnAuth");
        //权限认证不通过跳转
        shiroFilter.setUnauthorizedUrl("/authorUnAuth");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("roleOrFilter", new CustomRolesAuthorizationFilter(new RolesPermissionsServiceImpl(), redisCache));
        shiroFilter.setFilters(filters);

        shiroFilter.setFilterChainDefinitions("/###/@@@/** = authc");
        return shiroFilter;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}