package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdzc.pojo.Notice;
import com.fdzc.pojo.Person;
import com.fdzc.pojo.Trip;
import com.fdzc.service.PersonService;
import com.fdzc.service.TripService;
import com.fdzc.utils.Result;
import com.fdzc.utils.SystemContent;
import com.fdzc.vo.PersonVo;
import com.fdzc.vo.TripVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "人员表")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @ApiOperation("查询所有人员")
    @RequestMapping("/select")
    @RequiresRoles(logical = Logical.OR, value = {"admin","user"})
    Result<List<TripVo>> selectAll(){

        List<TripVo> tripVoList = personService.selectAll();

        return Result.ok(tripVoList);
    }

    @ApiOperation("人员添加接口")
    @PostMapping("/addPerson")
    public Result<Notice> addArticle(@RequestBody String personStr){
        PersonVo personVo= JSON.parseObject(personStr, PersonVo.class);
        int count = personService.addPerson(personVo);
        if ( count > 0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("审核通过接口")
    @PostMapping("/active")
    @RequiresRoles(logical = Logical.OR, value = {"admin","user"})
    public Result deleteArticle(@RequestBody String idstr){
        JSONObject jsonObject = JSONObject.parseObject(idstr);
        String string = jsonObject.getString("id");
        Integer id = Integer.parseInt(string);
        if (personService.activeChecked(id)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }

}
