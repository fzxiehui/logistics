package com.fdzc.controller.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.pojo.Notice;
import com.fdzc.pojo.User;
import com.fdzc.service.NoticeService;
import com.fdzc.utils.DataGridViewResult;
import com.fdzc.utils.Result;
import com.fdzc.utils.SystemContent;
import com.fdzc.vo.NoticeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "公告表")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation("查询所有公告")
    @RequestMapping("/select")
    Result<List<Notice>> selectAll(){
        List<Notice> noticeList = noticeService.selectAllPage();
        return Result.ok(noticeList);
    }

    @RequiresRoles("admin")
    @ApiOperation("公告添加处理接口")
    @PostMapping("/addNotice")
    public Result addArticle(@ApiParam("前台传入新增article文章参数") Notice notice){
        if (noticeService.addNotice(notice)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }

    @RequiresRoles("admin")
    @ApiOperation("公告删除处理接口")
    @PostMapping("/deleteNotice")
    public Result deleteArticle(@ApiParam("前台传入新增article文章参数id") Integer id){

        if (noticeService.deleteNoticeById(id)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }

    @RequiresRoles("admin")
    @ApiOperation("公告修改处理接口")
    @PostMapping("/updateNotice")
    public Result updateArticle(@ApiParam("前台传入新增article文章参数") Notice notice){
        if (noticeService.updateNotice(notice)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }
}
