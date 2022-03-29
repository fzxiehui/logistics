package com.fdzc.mapper;

import com.fdzc.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {
    @Select("select tr.id,tr.role_name from tb_user tu\n" +
            "left join tb_user_role tur on tu.id = tur.user_id\n" +
            "left join tb_role tr on  tur.role_id = tr.id\n" +
            "where tu.id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "roleName", column = "role_name")})
    public List<Role> getUserRolesByUserId(Integer id);
    }
