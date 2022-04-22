package com.fdzc.service;

import com.fdzc.pojo.Notice;

import java.util.List;

public interface NoticeService {
    //查询所有公告
    List<Notice> selectAllPage();

    //添加公告
    Notice addNotice(Notice notice);

    //删除公告
    int deleteNoticeById(Integer id);

    //修改公告
    int updateNotice(Notice notice);

}
