package com.fdzc.controller.user;

import com.fdzc.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员权限接口")
@RestController
@RequestMapping("/admin")
@RequiresRoles("admin")
public class AdminController {

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    @RequiresPermissions("user:*")
    public Result login() {
        return Result.ok("欢迎进入，您的身份是管理员");
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @RequiresPermissions("user:add")
    public Result login2() {
        return Result.ok("欢迎进入，您的身份是管理员权限是user");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @RequiresPermissions("per:add")
    public Result login3() {
        return Result.ok("这个进不来了");
    }

}
