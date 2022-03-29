package com.fdzc.shiro.realm;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.mapper.RoleMapper;
import com.fdzc.mapper.UserMapper;
import com.fdzc.pojo.Permission;
import com.fdzc.pojo.Role;
import com.fdzc.pojo.User;
import com.fdzc.service.PermissionService;
import com.fdzc.service.RoleService;
import com.fdzc.service.UserService;
import com.fdzc.shiro.jwt.JWTToken;
import com.fdzc.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

//    @Autowired
//    private void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userService.selectUserByName(token.getUsername());
        String password = user.getPassword();
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());*/
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        User user = userService.selectUserByName(username);
        String password = user.getPassword();
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        int status = user.getStatus();
        if (status == 0) {
            throw new AuthenticationException("该用户暂未激活！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");

    }

    /**
     * 获取授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userService.selectUserByName(username);
        //获得该用户角色
        List<Role> roles = roleService.getUserRolesByUserId(user.getId());
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        for (Role role : roles) {
            set.add(role.getRoleName());
        }
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;*/
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userService.selectUserByName(username);
        List<Role> roles = roleService.getUserRolesByUserId(user.getId());
        //以下就是将用户对应的角色更改了就可以更改对应的权限
        //所以只要知道每个角色有哪些权限，就知道每个用户有什么权限了。
//        Set<String> permissionSet = new HashSet<>();
        //获得该用户角色
//        String role = userMapper.getRole(username);

        //每个角色拥有默认的权限
//        String rolePermission = userMapper.getRolePermission(username);
        //每个用户可以设置新的权限
//        String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        for (Role role : roles) {
        roleSet.add(role.getRoleName());
        }

        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        for (Role role : roles) {
            List<Permission> permissions = permissionService.selectPermissionByRoleId(role.getId());
            for (Permission permission : permissions) {
                permissionSet.add(permission.getPermissionName());
            }
        }
//        roleSet.add(role);
//        permissionSet.add(rolePermission);
//        permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}