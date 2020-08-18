package com.demo.cs.template.shiro.redis;

import com.demo.cs.template.bean.TempUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 */
public class CsDemoRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /// ==========获取授权信息==========
        String userName=principals.getPrimaryPrincipal().toString();
        
        /// ==========设置session==========
        //数据库中查询对应用户信息------start
        TempUser user=new TempUser();
        user.setUserName(userName);
        //数据库中查询对应用户信息------end
        Subject subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        session.setAttribute("user_info", user);
        
        /// ==========设置权限集合==========
        Set<String> roleSet=new HashSet<>();
        Set<String> permissionSet=new HashSet<>();
        //数据库中获取角色、权限------start
        roleSet.add("user");
        permissionSet.add("user:ADD");
        permissionSet.add("user:DEL");
        //数据库中获取角色、权限------end
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /// ==========获取身份认证信息==========
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        String userName=usernamePasswordToken.getUsername();
        ///数据库中用户名查询用户信息，传入以下方法作为参数校验
        return new SimpleAuthenticationInfo("zhangsan", "123456", getName());
        //可手动抛出异常信息
        //throw new AuthenticationException();
    }
}
