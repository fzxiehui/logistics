package com.fdzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.pojo.Trip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TripMapper extends BaseMapper<Trip> {
}
