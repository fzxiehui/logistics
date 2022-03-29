package com.fdzc.controller.user;

import com.fdzc.utils.Result;
import io.swagger.annotations.Api;
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
    public Result login() {
        return Result.ok("欢迎进入，您的身份是管理员");
    }

}
