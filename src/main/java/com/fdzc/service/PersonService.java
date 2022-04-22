package com.fdzc.service;

import com.fdzc.vo.PersonVo;
import com.fdzc.vo.TripVo;

import java.util.List;

public interface PersonService {
    int activeChecked(Integer id);

    int addPerson(PersonVo personVo);

    List<TripVo> selectAll();
}
