package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
