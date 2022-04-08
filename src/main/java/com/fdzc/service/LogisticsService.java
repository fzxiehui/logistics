package com.fdzc.service;

import com.fdzc.pojo.Logistics;
import com.fdzc.vo.LogisticsVo;

import java.util.List;

public interface LogisticsService {

    //通过查询用户
    List<Logistics> selectLogistics(String owner);

    //添加物流信息
    int addLogistics(Logistics logistics);

    //修改物流信息
    int updateLogistics(Logistics logistics);

    //删除物流信息
    int deleteLogisticsById(Integer id);

    List<LogisticsVo> getCensus();

    List<LogisticsVo> getTop();
}
