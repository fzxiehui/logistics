package com.fdzc;

import com.fdzc.mapper.PermissionMapper;
import com.fdzc.mapper.RoleMapper;
import com.fdzc.pojo.Permission;
import com.fdzc.pojo.Role;
import com.fdzc.pojo.User;
import com.fdzc.service.PermissionService;
import com.fdzc.service.RoleService;
import com.fdzc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class SpringbootMSystemApplicationTests {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
        List<Role> roles = roleMapper.getUserRolesByUserId(1);
        System.out.println(roles);
    }

    @Test
    void test(){
        System.out.println("============================");
        List<Role> roles = roleMapper.getUserRolesByUserId(1);
        System.out.println("=======================");
        System.out.println(roles);
    }

    @Test
    void test2(){
        System.out.println("============================");
        List<Permission> permissionList = permissionMapper.selectPermissionByRoleId(1);
        System.out.println("=======================");
        System.out.println(1);
    }


    @Test
    void test3(){
        String username = "ysq";
        User user = userService.selectUserByName(username);
        List<Role> roles = roleService.getUserRolesByUserId(user.getId());
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

        System.out.println("===============================");

        System.out.println(roleSet);
        System.out.println(permissionSet);

        System.out.println("===============================");

    }




}
