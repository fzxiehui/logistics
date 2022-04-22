package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.Person;
import com.fdzc.vo.TripVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PersonMapper extends BaseMapper<Person> {


    @Select("SELECT position as name,count(*) as `value` FROM `tb_logistics`\n" +
            "group by position")
    @Results({
            @Result(property = "name",  column = "name"),
            @Result(property = "value", column = "value"),
    })
    List<TripVo> selectAllList();

}
