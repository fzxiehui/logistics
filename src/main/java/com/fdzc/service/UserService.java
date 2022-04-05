package com.fdzc.service;


import com.fdzc.pojo.User;
import com.fdzc.vo.UserVo;

import java.util.List;

public interface UserService {
    //通过登录名查询用户
    User selectUserByName(String name);

    //用户注册的方法
    int userRegister(UserVo userVo);

    //激活角色
    int activeUser(Integer id);

    //查询用户列表的方法
    List<User> selectAll();

    //删除用户的方法
    int deleteUserById(Integer id);

    //查询对应用户是否有关联角色
    int checkUserRoles(Integer id);

    //添加用户
    int addUser(User user);

    //给用户添加角色
    int addRole(Integer userId, Integer roleId);

    User selectUserByPhone(String phone);

    List<UserVo> selectUser();

    int userUpdate(UserVo userVo);
}
