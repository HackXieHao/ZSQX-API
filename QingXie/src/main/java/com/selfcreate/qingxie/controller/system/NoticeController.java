package com.selfcreate.qingxie.controller.system;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.system.Notice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author evans 2018/4/11 9:30
 */

@RequestMapping("/notice")
@Controller
public class NoticeController {
    /**
     * 发布公告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Msg releaseNotice(@RequestBody Notice record){
        throw new RuntimeException();
    }

    /**
     * 获取公告列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Msg getNotices(){
        throw new RuntimeException();
    }

    /**
     * 获取公告详情
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{noticeId}/list",method = RequestMethod.GET)
    public Msg getHoursDetailsOfActivity(@PathVariable("noticeId") int userId){
        throw new RuntimeException();
    }
}
