package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select tbp.id,tbp.permission_name,tbp.resource_url from tb_role tbr\n" +
            "left join tb_role_perm tbrp on tbr.id = tbrp.role_id\n" +
            "left join tb_permission tbp on tbrp.perm_id = tbp.id\n" +
            "where tbr.id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "permissionName", column = "permission_name"),
            @Result(property = "resourceUrl", column = "resource_url")
    })
    List<Permission> selectPermissionByRoleId(Integer id);
}
