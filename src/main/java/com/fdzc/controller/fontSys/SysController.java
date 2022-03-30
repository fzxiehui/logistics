package com.fdzc.controller.fontSys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value="页面跳转controller",tags={"页面跳转控制类"})
@Controller
public class SysController {

    @ApiOperation("去前台首页面")
    @RequestMapping({"/","/index","/index.html"})
    public String index(){
        return "font/index/user";
    }

}
