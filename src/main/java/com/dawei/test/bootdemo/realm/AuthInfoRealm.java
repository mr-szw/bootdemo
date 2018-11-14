package com.dawei.test.bootdemo.realm;

import com.dawei.test.bootdemo.constants.ConstantsConfig;
import com.dawei.test.bootdemo.entity.MenuInfo;
import com.dawei.test.bootdemo.entity.RoleInfo;
import com.dawei.test.bootdemo.entity.UserInfo;
import com.dawei.test.bootdemo.service.UserInfoService;
import com.dawei.test.bootdemo.utils.EncodesUtil;
import com.google.common.base.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dawei 2018/11/14
 */
@Component(value = "authInfoRealm")
public class AuthInfoRealm extends AuthorizingRealm {

    @Lazy
    @Resource
    private UserInfoService userInfoService;

    /**
     * 权限
     *
     * @param principalCollection
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        //获取用户信息 包含：[角色列表]、[菜单目录标识]
        UserInfo userInfo = userInfoService.findUserByLoginName(shiroUser.getloginName());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<RoleInfo> roleSet = userInfo.getRoleSet();
        Set<String> roleNames = roleSet.parallelStream().filter(role -> StringUtils.isNotBlank(role.getRoleName())).map(RoleInfo::getRoleName).collect(Collectors.toSet());

        Set<MenuInfo> menuInfoSet = userInfo.getMenuInfoSet();
        Set<String> permissions = menuInfoSet.parallelStream().filter(menuInfo -> StringUtils.isNotBlank(menuInfo.getPermission())).map(MenuInfo::getPermission).collect(Collectors.toSet());

        authorizationInfo.setRoles(roleNames);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 权限 获取
     *
     * @param authenticationToken 权限Token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = (String) token.getPrincipal();
        UserInfo userInfo = userInfoService.findUserByLoginName(username);
        if (userInfo == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (userInfo.getLocked() == 1) {
            throw new LockedAccountException(); //帐号锁定
        }


        String saltStr = userInfo.getSalt();
        byte[] salt = EncodesUtil.decodeHex(userInfo.getSalt());
        ShiroUser shiroUser = new ShiroUser(userInfo.getId(), userInfo.getLoginName(), userInfo.getNickName(), userInfo.getIcon());

        return new SimpleAuthenticationInfo(
                shiroUser,
                userInfo.getPassword(),
                ByteSource.Util.bytes(salt),
                getName()
        );
    }


    /**
     * 移除用户认证信息
     *
     * @param username 登陆名
     */
    public void removeUserAuthorizationInfoCache(String username) {
        SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
        principalCollection.add(username, super.getName());
        super.clearCachedAuthorizationInfo(principalCollection);
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ConstantsConfig.HASH_ALGORITHM);
        matcher.setHashIterations(ConstantsConfig.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;

        public Long id;
        public String loginName;
        public String nickName;
        public String icon;

        public ShiroUser(Long id, String loginName, String nickName, String icon) {
            this.id = id;
            this.loginName = loginName;
            this.nickName = nickName;
            this.icon = icon;
        }

        public String getloginName() {
            return loginName;
        }

        public String getNickName() {
            return nickName;
        }

        public String getIcon() {
            return icon;
        }

        public Long getId() {
            return id;
        }


        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return nickName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(loginName);
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (loginName == null) {
                return other.loginName == null;
            } else return loginName.equals(other.loginName);
        }
    }


}
