package com.fdzc.service;

import com.fdzc.pojo.Role;
import com.fdzc.vo.RoleVo;

import java.util.List;

public interface RoleService {

    //根据用户id获取用户角色
    List<Role> getUserRolesByUserId(Integer id);

    //获取所有用户人数
    List<RoleVo> getHomeUserType();
}
