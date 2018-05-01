package com.selfcreate.qingxie.controller.avtivity;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.UserActivity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author evans 2018/4/11 9:02
 */
@Controller
@RequestMapping("/manage")
public class ActivityManageController {
    @ResponseBody
    @RequestMapping(value = "/{userId}/list",method = RequestMethod.GET)
    public Msg getManagedActivities(@PathVariable("userId") int userId){
        throw new RuntimeException();
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/{activityId}/move",method = RequestMethod.PUT)
    public Msg updateActivityStatus(@PathVariable("userId") int userId, @PathVariable("activityId") int activityId){
        throw new RuntimeException();
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/{activityId}/volunteers",method = RequestMethod.GET)
    public Msg getVolunteersOfActivity(@PathVariable("userId") int userId, @PathVariable("activityId") int activityId){
        throw new RuntimeException();
    }

    /**
     * 确认某名志愿者是否参与了某次志愿服务
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/confirm",method = RequestMethod.PUT)
    public Msg confirm(@PathVariable("userId") int userId, UserActivity record){
        throw new RuntimeException();
    }
}
