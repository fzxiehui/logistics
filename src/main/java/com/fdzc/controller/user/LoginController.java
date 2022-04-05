package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdzc.pojo.Logistics;
import com.fdzc.pojo.Role;
import com.fdzc.pojo.User;
import com.fdzc.service.RoleService;
import com.fdzc.service.UserService;
import com.fdzc.utils.JWTUtil;
import com.fdzc.utils.PasswordUtil;
import com.fdzc.utils.Result;
import com.fdzc.utils.SystemContent;
import com.fdzc.vo.RoleVo;
import com.fdzc.vo.UserVo;
import com.fdzc.vo.tokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Api(tags = "登录控制")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "/unauthorized/{message}")
    @ResponseBody
    public Result unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Result.fail(message);
    }

    @RequestMapping(value = "/notLogin")
    @ResponseBody
    public Result notLogin() {
        return Result.ok("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public Result notRole() {
        return Result.ok("您没有权限！");
    }

    @ApiOperation("验证用户登录")
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody String userparms){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = JSON.parseObject(userparms,User.class);
//        String username = user.getUsername();
        String password = user.getPassword();
        String username = user.getUsername();
        //从SecurityUtils创建一个subject
        Subject subject = SecurityUtils.getSubject();
//        User userlogin = userService.selectUserByPhone(phone);
        User userlogin = userService.selectUserByName(username);
        password= PasswordUtil.md5(password, userlogin.getSalt(), SystemContent.COUNT_TIMES);
        List<Role> roles = roleService.getUserRolesByUserId(userlogin.getId());
        Role role = roles.get(0);
        Integer type = role.getId();
        map.put("type",type);
        map.put("realname",userlogin.getRealname());
        map.put("token",JWTUtil.createToken(username));
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try{
//            subject.login(token);
//        }catch (UnknownAccountException e){
//            return Result.fail("账号错误");
//        }catch (IncorrectCredentialsException e){
//            return Result.fail("密码错误");
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.fail("其他错误");
//        }
        return Result.ok(map);
    }

    @ApiOperation("检测用户名是存在")
    @ResponseBody
    @PostMapping("/checkRegisterName")
    public JSONObject checkLoginName(String username){
        Map<String,Object> map = new HashMap<String,Object>();
        if(userService.selectUserByName(username)==null){
            map.put(SystemContent.SUCCESS,true);
        }else{
            map.put(SystemContent.SUCCESS,false);
            map.put(SystemContent.MESSAGE,"登录账号已被注册!");
        }
        return JSON.parseObject(JSON.toJSONString(map));
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public JSONObject register(@RequestBody String jsonBody){
        UserVo userVo = JSONObject.parseObject(jsonBody,UserVo.class);
        Map<String,Object> map = new HashMap<String,Object>();
        if(userService.userRegister(userVo)>0){
            map.put(SystemContent.SUCCESS,true);
            map.put(SystemContent.MESSAGE,"注册成功");
        }else{
            map.put(SystemContent.SUCCESS,true);
            map.put(SystemContent.MESSAGE,"注册失败");
        }
        return JSON.parseObject(JSON.toJSONString(map));
    }

    @ApiOperation("跳转登录页面")
    @GetMapping("/toLogin")
    public String login(){
        return "font/login/login";
    }

    @ApiOperation("访问权限不足的页面提醒")
    @RequestMapping("/noauth")
    public String unauthorized(){
        return "error/404";
    }

    @ApiOperation("退出登录的请求")
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        //获取当前用户
        String token = request.getHeader("token");
        String username = JWTUtil.getUsername(token);
        JWTUtil.createEXToken(username);
        //退出登录
//        //获取当前用户
//        //获取session
//        Session session = subject.getSession();
//        //销毁session
//        session.removeAttribute(SystemContent.USERLOGIN);
        //生成的将生成的token的签名更改
        return Result.fail("退出成功");
    }

    @ApiOperation("获取所有类型的请求")
    @GetMapping("/getHomeUserType")
    @ResponseBody
    public Result<List<RoleVo>> getHomeUserType(){
        List<RoleVo> roleVoList = roleService.getHomeUserType();
        return Result.ok(roleVoList);
    }

}
