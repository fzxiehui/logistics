package com.fdzc.service;


import com.fdzc.pojo.User;

import java.util.List;

public interface UserService {
    //通过登录名查询用户
    User selectUserByName(String name);

    //用户注册的方法
    int userRegister(User user);


}
