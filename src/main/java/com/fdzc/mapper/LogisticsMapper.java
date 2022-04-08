package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.Logistics;
import com.fdzc.vo.LogisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LogisticsMapper extends BaseMapper<Logistics> {

    @Select("SELECT position as name,count(*) as `value` FROM `tb_logistics`\n" +
            "group by position")
    @Results({
            @Result(property = "name",  column = "name"),
            @Result(property = "value", column = "value"),
    })
    List<LogisticsVo> getCensus();


    @Select("SELECT owner as name,count(*) as `value` FROM `tb_logistics`\n" +
            "group by owner\n" +
            "order by `value` desc\n" +
            "limit 5")
    @Results({
            @Result(property = "name",  column = "name"),
            @Result(property = "value", column = "value"),
    })
    List<LogisticsVo> getTop();
}
