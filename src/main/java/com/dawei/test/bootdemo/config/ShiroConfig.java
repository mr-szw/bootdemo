package com.dawei.test.bootdemo.config;

import com.dawei.test.bootdemo.constants.ConstantsConfig;
import com.dawei.test.bootdemo.filter.CaptchaFormAuthenticationFilter;
import com.dawei.test.bootdemo.realm.AuthInfoRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dawei 2018/11/14
 */
@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${spring.redis.host}")
    private String jedisHost;

    @Value("${spring.redis.port}")
    private Integer jedisPort;

    @Value("${spring.redis.password}")
    private String jedisPassword;




    /* SpringBoot 使用Filter */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        filterProxy.setTargetFilterLifecycle(true);
        /* 配置指定的 Filter */
        filterProxy.setTargetBeanName("shiroFilterFactoryBean");
        filterRegistrationBean.setFilter(filterProxy);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.ERROR, DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
        return filterRegistrationBean;
    }


    /**
     * 权限管理器  重要
     *
     * @param authInfoRealm 权限池
     */
    @Bean
    public SecurityManager securityManager(@Qualifier("authInfoRealm") AuthInfoRealm authInfoRealm) {
        logger.info("- - - - - - -shiro开始加载- - - - - - ");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authInfoRealm);
        //defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        defaultWebSecurityManager.setSessionManager(webSessionManager());
        //defaultWebSecurityManager.setCacheManager(cacheManager());
        return defaultWebSecurityManager;
    }


    /**
     * 請求过滤器
     * 对請求做拦截分类处理
     *
     * @param authInfoRealm 权限Realm
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("authInfoRealm") AuthInfoRealm authInfoRealm) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager(authInfoRealm));

        //登录成功默认跳转页面，不配置则跳转至”/”。如果登陆前点击的一个需要登录的页面，则在登录自动跳转到那个需要登录的页面。不跳转到此。
        bean.setSuccessUrl("/index");
        //没有登录的用户请求需要登录的页面时自动跳转到登录页面，不是必须的属性，不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面。
        bean.setLoginUrl("/login");
        //没有权限默认跳转的页面
        bean.setUnauthorizedUrl("/login");

        Map<String, Filter> map = Maps.newHashMap();
        map.put("authc", new CaptchaFormAuthenticationFilter());
        bean.setFilters(map);
        /*
         * 配置访问权限
         * [authc:表示需要认证(登录)才能使用，没有参数]
         * [anon:没有参数，表示可以匿名使用。]
         * [roles:参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法]
         * [perms:参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。]
         * [rest:根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等]
         * [port:当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。]
         * [authcBasic:例如/admins/user/**=authcBasic没有参数表示httpBasic认证]
         * [ssl:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https]
         * [user:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查]
         * */
        LinkedHashMap<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/showBlog/**", "anon");
        filterChainDefinitionMap.put("/blog/**", "anon");
        filterChainDefinitionMap.put("/login/main", "anon");
        filterChainDefinitionMap.put("/genCaptcha", "anon");
        filterChainDefinitionMap.put("/systemLogout", "authc");
        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }


    /**
     * 设置权限相关的Session配置管理
     * @see #redisManager()  超时失效时间需要与Session失效统一
     */
    @Bean
    public SessionManager webSessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        manager.setGlobalSessionTimeout(60 * 60 * 1000);
        manager.setSessionValidationSchedulerEnabled(true);
        /* Session 管理方式默认管理方式为shiro本身的管理， 若使用Redis缓存则使用shiro-redis管理*/
        manager.setSessionDAO(redisSessionDAO());
        return manager;
    }

    /**
     * RedisSessionDao 用来作为Shiro的Session管理容器
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        /* 管理前缀  */
        sessionDAO.setKeyPrefix(ConstantsConfig.REDIS_SESSION_PRE);
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }


    /**
     * Shiro 的Redis 管理器
     * @see #webSessionManager()  超时失效时间需要与Session失效统一
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager manager = new RedisManager();
        manager.setHost(jedisHost);
        manager.setPort(jedisPort);
        //这里是用户session的时长 跟上面的setGlobalSessionTimeout 应该保持一直（上面是1个小时 下面是秒做单位的 我们设置成3600）
        manager.setExpire(60 * 60);
        manager.setPassword(jedisPassword);
        return manager;
    }

}
