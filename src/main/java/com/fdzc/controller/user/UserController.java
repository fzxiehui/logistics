package com.fdzc.controller.user;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fdzc.pojo.User;
import com.fdzc.service.UserService;
import com.fdzc.utils.Result;
import com.fdzc.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户权限接口")
@RestController
@RequestMapping("/user")
@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
public class UserController{

    @Autowired
    UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Result addUser(User user){
        int count = userService.addUser(user);
        if (count > 0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public Result deleteUser(Integer id){
        int count = userService.deleteUserById(id);
        if (count > 0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("更新用户和分配角色")
    @PostMapping("/updateUser")
    public Result updateUser(){
        return Result.ok();
    }

    @ApiOperation("添加用户角色")
    @PostMapping("/addRole")
    public Result addRole(Integer userId, Integer roleId){
        int count = userService.addRole(userId,roleId);
        if (count > 0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/selectUser")
    public Result<List<User>> selectUser(){
        List<User> userList= userService.selectAll();
        return Result.ok(userList);
    }

    @ApiOperation("根据id激活用户")
    @GetMapping("/activeUser")
    public Result activeUser(Integer id){
        userService.activeUser(id);
        return Result.ok();
    }
}
