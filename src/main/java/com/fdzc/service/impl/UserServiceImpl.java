package com.fdzc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.mapper.UserMapper;
import com.fdzc.pojo.User;
import com.fdzc.service.UserService;
import com.fdzc.utils.PasswordUtil;
import com.fdzc.utils.SystemContent;
import com.fdzc.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //通过登录名查询用户
    @Override
    public User selectUserByName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
    }

    //用户注册的方法
    @Override
    public int userRegister(User user) {
        int count = 0;
        //设置用户的权限
        user.setPhone(SystemContent.PHONE);
        //给用户的账号进行加密
        //设置用户加密的颜盐值
        user.setSalt(UUIDUtils.randomUUID());
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(),SystemContent.COUNT_TIMES));
        user.setJwtSecret("123@qwe");
        if(userMapper.insert(user)>0){
            User userR = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
            count = 1;
        }
        return count;
    }

}
