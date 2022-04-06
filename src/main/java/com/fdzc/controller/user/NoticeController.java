package com.fdzc.controller.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.pojo.Logistics;
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
import org.apache.shiro.authz.annotation.Logical;
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
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation("查询所有公告")
    @RequestMapping("/select")
    Result<List<Notice>> selectAll(){
        List<Notice> noticeList = noticeService.selectAllPage();
        return Result.ok(noticeList);
    }

    @ApiOperation("公告发布处理接口")
    @PostMapping("/addNotice")
    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin","user"})
    public Result<Notice> addArticle(@RequestBody String noticeStr){
        Notice notice = JSON.parseObject(noticeStr,Notice.class);
        notice = noticeService.addNotice(notice);
        if ( notice != null){
            return Result.ok(notice);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }

    @ApiOperation("公告删除处理接口")
    @PostMapping("/deleteNotice")
    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin","user"})
    public Result deleteArticle(@RequestBody String idstr){
        JSONObject jsonObject = JSONObject.parseObject(idstr);
        String string = jsonObject.getString("id");
        Integer id = Integer.parseInt(string);
        if (noticeService.deleteNoticeById(id)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }

    @ApiOperation("公告修改处理接口")
    @PostMapping("/updateNotice")
    @RequiresRoles(logical = Logical.OR, value = {"logistics", "admin","user"})
    public Result updateArticle(@RequestBody String noticeStr){
        Notice notice = JSON.parseObject(noticeStr,Notice.class);
        if (noticeService.updateNotice(notice)>0){
            return Result.ok(SystemContent.SUCCESS);
        }else {
            return Result.fail(SystemContent.FAIL);
        }
    }
}
