package com.fdzc.service.impl;

import com.fdzc.mapper.RoleMapper;
import com.fdzc.mapper.UserMapper;
import com.fdzc.pojo.Role;
import com.fdzc.service.RoleService;
import com.fdzc.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getUserRolesByUserId(Integer id) {
        return roleMapper.getUserRolesByUserId(id);
    }

    @Override
    public List<RoleVo> getHomeUserType() {
        return roleMapper.getHomeUserType();
    }
}
