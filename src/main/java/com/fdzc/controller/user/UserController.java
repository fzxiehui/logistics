package com.fdzc.controller.user;


import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户权限接口")
@RestController
@RequestMapping("/user")
@RequiresRoles("user")
public class UserController {
}
