package com.fdzc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.mapper.PersonMapper;
import com.fdzc.mapper.TripMapper;
import com.fdzc.pojo.Person;
import com.fdzc.pojo.Trip;
import com.fdzc.service.PersonService;
import com.fdzc.vo.PersonVo;
import com.fdzc.vo.TripVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    TripMapper tripMapper;

    @Override
    public int activeChecked(Integer id) {
        Person person = new Person();
        person.setId(id);
        person.setCheck(1);
        return personMapper.updateById(person);
    }

    @Override
    public int addPerson(PersonVo personVo) {
        int danger = personVo.getDanger();
        int count = 0;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone",personVo.getPhone());
        Person person = personMapper.selectOne(wrapper);
        if (person==null){
            count = personMapper.insert((Person)personVo);
        }else{
            personVo.setId(person.getId());
        }
        if (danger == 1){
            Trip trip = new Trip();
            trip.setPersonId(personVo.getId());
            trip.setPlace(personVo.getPlace());
            trip.setPlate(personVo.getPlate());
            trip.setTraffic(personVo.getTraffic());
            count = tripMapper.insert(trip);
        }
        return count;
    }

    @Override
    public List<TripVo> selectAll() {

        List<Person> personList = personMapper.selectList(null);
        List<TripVo> tripVoList = new ArrayList<>();
        for (Person person : personList) {
            QueryWrapper wrapper = new QueryWrapper();
            TripVo tripVo = new TripVo();
            wrapper.eq("person_id",person.getId());
            List<Trip> selectList = tripMapper.selectList(wrapper);
            tripVo.setId(person.getId());
            tripVo.setName(person.getName());
            tripVo.setPhone(person.getPhone());
            tripVo.setType(person.getType());
            tripVo.setNucleic(person.getNucleic());
            tripVo.setVaccine(person.getVaccine());
            tripVo.setDanger(person.getDanger());
            tripVo.setCheck(person.getCheck());
            tripVo.setCreateTime(person.getCreateTime());
            tripVo.setInfo(selectList);
            tripVoList.add(tripVo);
        }
//        List<PersonVo> personVoList = personMapper.selectList(null);
        return tripVoList;
    }
}
