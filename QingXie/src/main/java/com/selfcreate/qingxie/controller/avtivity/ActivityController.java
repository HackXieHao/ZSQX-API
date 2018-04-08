package com.selfcreate.qingxie.controller.avtivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.selfcreate.qingxie.bean.activity.*;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.service.file.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.service.activity.ActivityService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/activity")
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    @Qualifier("picServiceImpl")
    private FileService picService;
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 发布活动
     *
     * @param activity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/releaseActivity", method = RequestMethod.PUT)
    public Msg releaseActivity(@RequestBody Activity activity) {
        if (activity != null) {
            activityService.releaseActivity(activity);
            return Msg.success().add("activity", activity);
        } else {
            return Msg.error("活动信息不能为空");
        }
    }

    /**
     * 返回首页数据
     * Activity:活动id，活动名称，首页图片路径，活动简介
     * 用于首页轮询图片和活动摘要
     * 分页返回，根据create_time排序
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public Msg getHome(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "size", defaultValue = "8") int size) {
        try {
            PageHelper.startPage(page, size);
            List<Activity> acs = activityService.getAll();
            PageInfo pageInfo = new PageInfo<>(acs);
            List<HomePageActivity> result = new ArrayList<>();
            for (Activity ac : acs) {
                result.add(new HomePageActivity(ac.getId(), ac.getName(), ac.getHomepagePic(), ac.getGeneral(), ac.getCreateTime()));
            }
            pageInfo.setList(result);
            return Msg.success("获取成功").add("PageInfo", pageInfo);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return Msg.error("系统异常");
        }
    }

    /**
     * 获取我的志愿服务的接口
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/activities", method = RequestMethod.GET)
    public Msg getUserActivities(@PathVariable("userId") int userId) {
        try {
            logger.info("》》》用户id为" + userId + "请求获取我的志愿服务");
            List<Activity4User> acs = activityService.getAllByUserId(userId);
            if (acs == null) {
                return Msg.error("无志愿服务数据");
            }
            return Msg.success("获取成功").add("UserActivityList", acs);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Msg.error("系统异常");
        }
    }

    /**
     * 获取我的志愿工作接口
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/works", method = RequestMethod.GET)
    public Msg getUserWorks(@PathVariable("userId") int userId) {
        try {
            logger.info("》》》用户id为" + userId + "请求获取我的志愿工作");
            List<Activity4User> acs = activityService.getWorksByUserId(userId);
            if (acs == null) {
                return Msg.error("无志愿工作数据");
            }
            return Msg.success("获取成功").add("UserActivityList", acs);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Msg.error("系统异常");
        }
    }

    /**
     * 获取我的活动收藏接口
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/forks", method = RequestMethod.GET)
    public Msg getForks(@PathVariable("userId") int userId) {
        try {
            logger.info("》》》用户id为" + userId + "请求获取我的收藏");
            List<Activity4User> acs = activityService.getForks(userId);
            if (acs == null) {
                return Msg.error("无收藏活动数据");
            }
            return Msg.success("获取成功").add("UserActivityList", acs);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Msg.error("系统异常");
        }
    }

    /**
     * 获取活动详情接口
     *
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/details", method = RequestMethod.GET)
    public Msg getDetails(@PathVariable("activityId") int activityId) {
        try {
            Activity ac = activityService.getById(activityId);
            return Msg.success("获取成功").add("Activity", ac);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        }
    }

    /**
     * 发布推文接口
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/add", method = RequestMethod.POST)
    public Msg pushActivity(@PathVariable("userId") int userId) {
        //TODO
        return null;
    }

    /**
     * 活动报名接口
     *
     * @param activityId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/{userId}/join", method = RequestMethod.POST)
    public Msg joinActivity(@PathVariable("activityId") int activityId, @PathVariable("userId") int userId) {
        //TODO
        return null;
    }

    /**
     * 活动照片上传接口
     * FIXME:权限验证
     * @param request
     * @param userId 上传用户的id
     * @param pic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/pic/add", method = RequestMethod.POST)
    public Msg pushActivity(HttpServletRequest request, @PathVariable("userId") int userId, @RequestParam(value="pic",required=false)MultipartFile pic) {
        if(pic==null||pic.getSize()==0){
            return Msg.error("上传图片为空");
        }
        try {
            String accessPath=picService.save2Local(pic, pic.getOriginalFilename());
            return Msg.success("图片上传成功").add("picAccessPath", accessPath);
        }catch (QingxieInnerException e){
            return Msg.error(e.getMessage());
        }
    }
}
