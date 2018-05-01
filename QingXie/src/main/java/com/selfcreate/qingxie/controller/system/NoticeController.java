package com.selfcreate.qingxie.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.system.Notice;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.service.system.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author evans 2018/4/11 9:30
 */

@RequestMapping("/notice")
@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    /**
     * 发布公告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Msg releaseNotice(@RequestBody Notice record){
        try {
            noticeService.pushNotice(record);
            return Msg.success("发布成功");
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        }
    }

    /**
     * 获取公告列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Msg getNotices(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "8") int size){
        try{
            PageHelper.startPage(page, size);
            List<Notice> notices=noticeService.getNotices();
            PageInfo pageInfo=new PageInfo(notices);
            return Msg.success("获取成功").add("PageInfo", pageInfo);
        }catch (QingxieInnerException e){
            return Msg.error(e.getMessage());
        }
    }

    /**
     * 获取公告详情
     * @param noticeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{noticeId}/details",method = RequestMethod.GET)
    public Msg getDetailsById(@PathVariable("noticeId") int noticeId){
        try {
            Notice result=noticeService.getDetails(noticeId);
            return Msg.success("获取成功").add("Notice", result);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        }
    }
}
