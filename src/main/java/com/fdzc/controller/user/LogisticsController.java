package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdzc.pojo.Logistics;
import com.fdzc.service.LogisticsService;
import com.fdzc.utils.JWTUtil;
import com.fdzc.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "物流权限接口")
@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    @Autowired
    LogisticsService logisticsService;

    @PostMapping("/logisticsList")
    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin","guest"})
    public Result<List<Logistics>> logisticList(HttpServletRequest request){
        String token = request.getHeader("token");
        String username = JWTUtil.getUsername(token);
        List<Logistics> logisticsList = logisticsService.selectLogistics(username);
        if (logisticsList.size() >0){
            return Result.ok(logisticsList);
        }else {
            return Result.fail("没有数据");
        }
    }

    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin"})
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

    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin"})
    @PostMapping("/updateLogistics")
    public Result updateLogistics(@RequestBody String logisticsString){
        Logistics logistics = JSON.parseObject(logisticsString,Logistics.class);
        int count = logisticsService.updateLogistics(logistics);
        if (count > 0){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("/deleteLogistics")
    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin"})
    public Result deleteLogistic(@RequestBody String idstr){
        JSONObject jsonObject = JSONObject.parseObject(idstr);
        String string = jsonObject.getString("id");
        Integer id = Integer.parseInt(string);
        int count = logisticsService.deleteLogisticsById(id);
        if (count > 0){
            return Result.ok("ok");
        }else{
            return Result.fail();
        }
    }

}
