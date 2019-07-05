package com.seenroot.springboot.shiro;

import com.seenroot.springboot.domain.User;
import com.seenroot.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    // 注入业务
    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        List<User> dbUserList = userService.findByName(token.getUsername());

        for (User dbUser: dbUserList) {
            // 编写Shiro判断逻辑，判断用户名和密码
            // 1. 判断用户名
            if (dbUser == null) {
                // 用户名不存在
                return null; // Shiro底层会抛出UnknownAccountException
            }

            // 2. 判断密码
            return new SimpleAuthenticationInfo("", dbUser.getPassword(), "");
        }
        return null;

        // 假设数据库的用户名和密码
        // User dbUser = new User();
        // dbUser.setName("bob");
        // dbUser.setPassword("123456");

    }
}
