package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.fdzc.pojo.Logistics;
import com.fdzc.service.LogisticsService;
import com.fdzc.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "物流权限接口")
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    @Autowired
    LogisticsService logisticsService;

    //手机号的模糊查询
    @PostMapping("/queryLogistics")
    @RequiresRoles("logistics")
    public Result<List<Logistics>> selectLogistics(@PathVariable("owner") String owner){
        List<Logistics> logisticsList = logisticsService.selectLogistics(owner);
        if (logisticsList != null){
            return Result.ok(logisticsList);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/logisticList")
    public Result<List<Logistics>> logisticList(){
        List<Logistics> logisticsList = logisticsService.selectLogistics(null);

        if (logisticsList != null){
            return Result.ok(logisticsList);
        }else {
            return Result.fail();
        }
    }

    @PostMapping("/increaseLogistic")
    public Result increaseLogistic(@RequestBody String logisticsString){
        Logistics logistics = JSON.parseObject(logisticsString,Logistics.class);
        int count = logisticsService.addLogistics(logistics);
        if (count > 0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("/updateLogistics")
    public Result updateLogistics(Logistics logistics){
        int count = logisticsService.updateLogistics(logistics);
        if (count > 0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("/deleteLogistics")
    public Result deleteLogistic(Integer id){
        int count = logisticsService.deleteLogisticsById(id);
        if (count > 0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


}
