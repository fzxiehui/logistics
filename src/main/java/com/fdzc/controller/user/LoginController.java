package com.fdzc.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdzc.pojo.Role;
import com.fdzc.pojo.User;
import com.fdzc.service.RoleService;
import com.fdzc.service.UserService;
import com.fdzc.utils.JWTUtil;
import com.fdzc.utils.PasswordUtil;
import com.fdzc.utils.Result;
import com.fdzc.utils.SystemContent;
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

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "登录控制")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "/unauthorized/{message}")
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
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        //从SecurityUtils创建一个subject
        Subject subject = SecurityUtils.getSubject();
        User userlogin = userService.selectUserByName(username);
        password= PasswordUtil.md5(password, userlogin.getSalt(), SystemContent.COUNT_TIMES);
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
        return Result.ok(JWTUtil.createToken(username));
//        List<Role> roles = roleService.getUserRolesByUserId(userlogin.getId());
//        for (Role role : roles) {
//            if ("user".equals(role.getRoleName())) {
//                return Result.ok("欢迎登陆");
//            }
//            if ("admin".equals(role.getRoleName())) {
//                return Result.ok("欢迎来到管理员页面");
//            }
//        }

//        return Result.ok("登录成功");

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
    public JSONObject register(@RequestParam("loginName") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Map<String,Object> map = new HashMap<String,Object>();
        if(userService.userRegister(user)>0){
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
    public String logout(){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //退出登录
        subject.logout();
        //获取当前用户
        //获取session
        Session session = subject.getSession();
        //销毁session
        session.removeAttribute(SystemContent.USERLOGIN);
        return "redirect:/";
    }


}
