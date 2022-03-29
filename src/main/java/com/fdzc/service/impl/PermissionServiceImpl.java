package com.fdzc.service.impl;

import com.fdzc.mapper.PermissionMapper;
import com.fdzc.mapper.RoleMapper;
import com.fdzc.pojo.Permission;
import com.fdzc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> selectPermissionByRoleId(Integer id) {
        return permissionMapper.selectPermissionByRoleId(id);
    }
}
