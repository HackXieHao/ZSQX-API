package com.selfcreate.qingxie.controller.avtivity;

import java.util.*;

import com.selfcreate.qingxie.bean.activity.*;
import com.selfcreate.qingxie.bean.user.Favourite;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.bean.user.UserActivityHours;
import com.selfcreate.qingxie.bean.user.ArriveConfirm;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.service.file.FileService;
import com.selfcreate.qingxie.service.user.UserActivityHourService;
import com.selfcreate.qingxie.service.user.UserActivityService;
import com.selfcreate.qingxie.service.user.UserService;
import com.selfcreate.qingxie.util.ResponseUtil;
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
import com.selfcreate.qingxie.bean.user.ArriveConfirm;
import com.selfcreate.qingxie.bean.user.Favourite;
import com.selfcreate.qingxie.bean.user.User;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private UserActivityHourService userActivityHourService;

    /**
     * 获取某个活动报名人数
     *
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/getApplyNumber", method = RequestMethod.GET)
    public Msg getApplyNumber(@PathVariable("activityId") Integer activityId) {
        if (activityId != null) {
            return Msg.success().add("applyNumber", userActivityService.getApplyNumber(activityId));
        } else {
            return Msg.error();
        }
    }

    /**
     * 活动签到修改
     *
     * @param activityId
     * @param arriveConfirms
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/modifyConfirm", method = RequestMethod.POST)
    public Msg modifyConfirm(@PathVariable("activityId") Integer activityId, @RequestBody ArriveConfirm[] arriveConfirms) {
        if (arriveConfirms != null) {
            for (ArriveConfirm arriveConfirm : arriveConfirms) {
                UserActivity userActivity = userActivityService.getByActivityIdAndUserId(activityId, arriveConfirm.getUserId());
                if (userActivity != null) {
                    int count = userActivity.getCount();
                    System.out.println(count);
                    if (arriveConfirm.getIsArrived()) {
                        count++;
                        System.out.println(count);
                    } else {
                        count--;
                        System.out.println(count);
                    }
                    userActivity.setCount(count);
                    userActivityService.update(userActivity);
                } else {
                    return Msg.error("该活动无此志愿者");
                }

            }
            return Msg.success("签到修改成功");
        }
        return Msg.error("传入数据有误");
    }

    /**
     * 活动签到确认
     *
     * @param activityId
     * @param arriveConfirms
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/arriveConfirm", method = RequestMethod.POST)
    public Msg arriveConfirm(@PathVariable("activityId") Integer activityId, @RequestBody ArriveConfirm[] arriveConfirms) {
        if (arriveConfirms != null) {
            for (ArriveConfirm arriveConfirm : arriveConfirms) {
                UserActivity userActivity = userActivityService.getByActivityIdAndUserId(activityId, arriveConfirm.getUserId());
                if (userActivity != null) {
                    int count = userActivity.getCount();
                    System.out.println(count);
                    if (arriveConfirm.getIsArrived()) {
                        count++;
                        System.out.println(count);
                        userActivity.setCount(count);
                        userActivityService.update(userActivity);
                    }
                } else {
                    return Msg.error("该活动无此志愿者");
                }

            }
            return Msg.success("签到成功");
        }
        return Msg.error("传入数据有误");
    }

    /**
     * 活动推进，1表示未开始，2表示进行中，3表示已结束
     *
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{activityId}/boostActivity", method = RequestMethod.POST)
    public Msg boostActivity(@PathVariable("activityId") Integer activityId) {
        if (activityId != null) {
            Activity activity = activityService.getById(activityId);
            int status = activity.getStatus();
            if (status == 3) {
                return Msg.error("活动已结束，无法推进");
            } else if (status == 2) {
                status++;
                activity.setStatus(status);
                activityService.updateActivity(activity);
                //此时推进则表示活动即将结束，需要给志愿者们保存工时
                //1.获取该活动的所有志愿者
                List<UserActivity> userActivities = userActivityService.getByActivityId(activityId);
                for (UserActivity userActivity : userActivities) {
                    UserActivityHours userActivityHours = new UserActivityHours();
                    userActivityHours.setUserId(userActivity.getUserId());
                    userActivityHours.setActivityId(activityId);
                    //青协工作人员和活动负责人每次工时上浮2个
                    if (userActivity.getStuff() == 1 || userActivity.getStuff() == 2) {
                        userActivityHours.setVoluntaryHours((int) (userActivity.getCount() * (activity.getHourPerTime() + 2)));
                    } else {
                        userActivityHours.setVoluntaryHours((int) (userActivity.getCount() * activity.getHourPerTime()));
                    }
                    userActivityHourService.add(userActivityHours);
                }
                return Msg.success("测试");
            } else {
                status++;
                activity.setStatus(status);
                activityService.updateActivity(activity);
                return Msg.success("推进成功");
            }
        } else {
            return Msg.error("请选择活动");
        }
    }

    /**
     * 获取首页轮播图
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHomePagePic", method = RequestMethod.GET)
    public Msg getHomePagePic() {
        logger.info("》》》请求获取首页轮播图");
        List<Activity> activities = activityService.getAll();
        List<HomePagePicInfo> homePagePicInfos = new ArrayList<HomePagePicInfo>();
        Collections.sort(activities, new Comparator<Activity>() {
            // 降序排列
            @Override
            public int compare(Activity o1, Activity o2) {
                long i = o1.getCreateTime().getTime() - o2.getCreateTime().getTime();
                if (i > 0) {
                    return -1;
                } else if (i == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
        System.out.println(activities);
        int i = 0;
        for (Activity activity : activities) {
            if (activity.getHomepagePic() != null) {
                HomePagePicInfo homePagePicInfo = new HomePagePicInfo();
                homePagePicInfo.setActivityId(activity.getId());
                homePagePicInfo.setGeneral(activity.getGeneral());
                homePagePicInfo.setHomePagePic(activity.getHomepagePic());
//				System.out.println(homePagePic);
                homePagePicInfos.add(homePagePicInfo);
                i++;
            }
            if (i == 4) {
                break;
            }
        }
//		System.out.println(homePagePics);
        return Msg.success("处理成功").add("homePagePics", homePagePicInfos);
    }

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
        	activity.setCreateTime(new Date());
            activityService.releaseActivity(activity);
            return Msg.success().add("activity", activity);
        } else {
            return Msg.error("活动信息不能为空");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{activityId}/volunteers", method = RequestMethod.GET)
    public Msg getVolunteerNumber(@PathVariable("activityId") Integer activityId) {
        List<UserActivity> userActivities = userActivityService.getAllVolunteersByActivityId(activityId);
        List<Integer> ids = new ArrayList<Integer>();
        for (UserActivity userActivity : userActivities) {
            ids.add(userActivity.getUserId());
        }
        List<User> users = userService.getUsersByIds(ids);
        String[] fileds = {"id", "studentId", "name", "telephone", "qq"};
        List<Map<String, Object>> response = ResponseUtil.getResultMap(users, fileds);
        return Msg.success().add("volunteers", response);
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
     * 添加活动至我的收藏
     *
     * @param favourite
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addFork", method = RequestMethod.POST)
    public Msg addFork(@RequestBody Favourite favourite) {
        try {
            if (favourite != null) {
                logger.info("》》》用户id为" + favourite.getUserId() + "请求添加" + favourite.getActivityId() + "到我的收藏");
                if (activityService.isForkAgain(favourite.getUserId(), favourite.getActivityId())) {
                    return Msg.error("该活动已收藏");
                }
                activityService.addFork(favourite);
                return Msg.success("添加成功");
            } else {
                return Msg.error("传入信息有误！");
            }

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
        try {
            activityService.signUp(userId, activityId);
            return Msg.success("报名成功");
        } catch (Exception e) {
            return Msg.error(e.getMessage());
        }
    }

    /**
     * 活动照片上传接口
     * FIXME:权限验证
     *
     * @param request
     * @param userId  上传用户的id
     * @param pic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/pic/add", method = RequestMethod.POST)
    public Msg pushActivity(HttpServletRequest request, @PathVariable("userId") int userId, @RequestParam(value = "pic", required = false) MultipartFile pic) {
        if (pic == null || pic.getSize() == 0) {
            return Msg.error("上传图片为空");
        }
        try {
            String accessPath = picService.save2Local(pic, pic.getOriginalFilename());
            return Msg.success("图片上传成功").add("picAccessPath", accessPath);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        }
    }
}