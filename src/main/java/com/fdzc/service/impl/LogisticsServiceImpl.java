package com.fdzc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.mapper.LogisticsMapper;
import com.fdzc.pojo.Logistics;
import com.fdzc.service.LogisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    LogisticsMapper logisticsMapper;

    @Override
    public List<Logistics> selectLogistics(String owner) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like(StringUtils.isNotBlank(owner),"owner",owner);
        List list = logisticsMapper.selectList(wrapper);
        return list;
    }

    @Override
    public int addLogistics(Logistics logistics) {
        return logisticsMapper.insert(logistics);
    }

    @Override
    public int updateLogistics(Logistics logistics) {
        return logisticsMapper.updateById(logistics);
    }

    @Override
    public int deleteLogisticsById(Integer id) {
        return logisticsMapper.deleteById(id);
    }

}
