package com.fdzc.controller.user;

import com.fdzc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController{

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public Result login() {
        return Result.ok("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public Result submitLogin() {
        return Result.ok("您拥有获得该接口的信息的权限！");
    }
}