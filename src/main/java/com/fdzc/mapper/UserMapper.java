package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.User;
import com.fdzc.vo.UserVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

    @Select("select tu.id,username,status,realname,role_id as type from tb_user tu\n" +
            "left join tb_user_role tur\n" +
            "on tu.id = tur.user_id")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "realname", column = "realname"),
            @Result(property = "status", column = "status"),
            @Result(property = "type", column = "type"),
    })
    List<UserVo> selectAllUser();


}
