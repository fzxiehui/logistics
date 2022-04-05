package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select tr.id,tr.role_name from tb_user tu\n" +
            "left join tb_user_role tur on tu.id = tur.user_id\n" +
            "left join tb_role tr on  tur.role_id = tr.id\n" +
            "where tu.id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "roleName", column = "role_name")})
    public List<Role> getUserRolesByUserId(Integer id);

    @Delete("delete from tb_user_role\n" +
            "where id in (\n" +
            "select id from (SELECT tur.id FROM `tb_user_role` tur\n" +
            "left join tb_user tu\n" +
            "on tur.user_id = tu.id\n" +
            "where tu.id = #{id}) as te\n" +
            ")\n")
    public int deleteUserAndRole(Integer id);

    @Insert("insert into `tb_user_role`(user_id,role_id) values(#{userId},#{roleId})")
    public int inserUserAndRole(Integer userId,Integer roleId);

    }
