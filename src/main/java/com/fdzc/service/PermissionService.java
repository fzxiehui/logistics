package com.fdzc.service;

import com.fdzc.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> selectPermissionByRoleId(Integer id);


}
