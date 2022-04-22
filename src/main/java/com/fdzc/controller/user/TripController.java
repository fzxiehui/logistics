package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdzc.pojo.Notice;
import com.fdzc.pojo.Person;
import com.fdzc.service.NoticeService;
import com.fdzc.service.TripService;
import com.fdzc.utils.Result;
import com.fdzc.utils.SystemContent;
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

@Api(tags = "行程")
@RestController
@RequestMapping("/api/trip")
public class TripController {




}
