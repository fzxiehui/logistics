package com.fdzc;

import com.alibaba.fastjson.JSONObject;
import com.fdzc.mapper.PermissionMapper;
import com.fdzc.mapper.RoleMapper;
import com.fdzc.mapper.UserMapper;
import com.fdzc.pojo.*;
import com.fdzc.service.*;
import com.fdzc.utils.*;
import com.fdzc.vo.RoleVo;
import com.fdzc.vo.UserVo;
import com.github.pagehelper.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class SpringbootMSystemApplicationTests {


    @Autowired
    UserMapper userMapper;

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

    @Autowired
    NoticeService noticeService;

    @Autowired
    LogisticsService logisticsService;


    @Test
    void contextLoads() {
        List<RoleVo> type = roleService.getHomeUserType();
        System.out.println(type);
        System.out.println("=========================");
    }

    @Test
    void contextLoads2() {
        int i = roleMapper.getRoleByUserPhone("111");
        System.out.println(i);
        System.out.println("=========================");
    }

    @Test
    void test(){
        List<User> userList = userService.selectAll();
        System.out.println(userList);
        int i = userService.checkUserRoles(1);
        System.out.println(i);
    }

    @Test
    void test2(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDkyNDgyMDksInVzZXJuYW1lIjoiMTExIn0.uWI9SoHDTxF4xguGTFzBdYBRuGC3f5RaWvTeXKFK38M";
        String username = JWTUtil.getUsername(token);
        System.out.println(username);

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

    @Test
    void test4(){
        List<Notice> noticeList = noticeService.selectAllPage();
        System.out.println(noticeList);
        System.out.println("======================");
        Result<List<Notice>> ok = Result.ok(noticeList);
        System.out.println(ok);
    }

    @Test
    void test5(){
        Notice notice = new Notice();
        notice.setId(4);
        notice.setContent("s十多个撒大都市asdgasdgsdgsagsda");
        notice.setTitle("修改");
        int i = noticeService.updateNotice(notice);
        System.out.println("=========="+i+"============");
    }

    @Test
    void test6(){
        Notice notice = new Notice();
        notice.setContent("大厦是公dfshdfhdshdfhdh司的撒大dfhsddfgfdgdh噶的s十多sdhfdhdf个撒大都市asdgasdgsdgsagsda");
        notice.setTitle("修山豆根时地 告士刁改");
        int i = noticeService.addNotice(notice);
        System.out.println("=========="+i+"============");
    }


    @Test
    void test7(){
        List<Logistics> logisticsList = logisticsService.selectLogistics("18930300365");
        System.out.println(logisticsList);
    }

}
