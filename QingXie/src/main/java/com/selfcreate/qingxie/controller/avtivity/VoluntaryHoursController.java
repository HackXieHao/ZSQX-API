package com.selfcreate.qingxie.controller.avtivity;

import com.selfcreate.qingxie.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author evans 2018/4/11 9:22
 */
@Controller
@RequestMapping("/vhours")
public class VoluntaryHoursController {
    @ResponseBody
    @RequestMapping(value = "/{userId}/list",method = RequestMethod.GET)
    public Msg getHoursDetails(@PathVariable("userId") int userId){
        throw new RuntimeException();
    }

    @ResponseBody
    @RequestMapping(value = "/{activityId}/list",method = RequestMethod.GET)
    public Msg getHoursDetailsOfActivity(@PathVariable("activityId") int userId){
        throw new RuntimeException();
    }

    @ResponseBody
    @RequestMapping(value = "/{classId}/list",method = RequestMethod.GET)
    public Msg getHoursGeneralOfClass(@PathVariable("classId") int userId){
        throw new RuntimeException();
    }
}
