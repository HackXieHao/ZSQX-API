package com.selfcreate.qingxie.controller.avtivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.selfcreate.qingxie.bean.activity.*;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.selfcreate.qingxie.bean.user.Favourite;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.bean.user.UserActivityHours;
import com.selfcreate.qingxie.service.activity.ActivityService;
import com.selfcreate.qingxie.service.user.UserActivityHourService;
import com.selfcreate.qingxie.service.user.UserActivityService;

@RequestMapping("/activity")
@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserActivityService userActivityService;
	
	@Autowired
	private UserActivityHourService userActivityHourService;
	
	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 获取某个活动报名人数
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{activityId}/getApplyNumber", method = RequestMethod.GET)
	public Msg getApplyNumber(@PathVariable("activityId") Integer activityId){
		if(activityId != null){
			return Msg.success().add("applyNumber", userActivityService.getApplyNumber(activityId));
		}else{
			return Msg.error();
		}
	}
	
	/**
	 * 活动推进，1表示未开始，2表示进行中，3表示已结束
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{activityId}/boostActivity", method = RequestMethod.POST)
	public Msg boostActivity(@PathVariable("activityId") Integer activityId){
		if(activityId != null){
			Activity activity = activityService.getById(activityId);
			int status = activity.getStatus();
			if(status == 3){
				return Msg.error("活动已结束，无法推进");
			}else if(status ==2){
				status ++;
				activity.setStatus(status);
				activityService.updateActivity(activity);
				//此时推进则表示活动即将结束，需要给志愿者们保存工时
					//1.获取该活动的所有志愿者
				List<UserActivity> userActivities = userActivityService.getByActivityId(activityId);
				for (UserActivity userActivity : userActivities){
					UserActivityHours userActivityHours = new UserActivityHours();
					userActivityHours.setUserId(userActivity.getUserId());
					userActivityHours.setActivityId(activityId);
					//青协工作人员和活动负责人每次工时上浮2个
					if(userActivity.getStuff() == 1 || userActivity.getStuff() == 2){
						userActivityHours.setVoluntaryHours((int) (userActivity.getCount() * (activity.getHourPerTime() + 2)));
					}else{
						userActivityHours.setVoluntaryHours((int) (userActivity.getCount() * activity.getHourPerTime()));
					}
					userActivityHourService.add(userActivityHours);
				}
				return Msg.success("测试");
			}else{
				status ++;
				activity.setStatus(status);
				activityService.updateActivity(activity);
				return Msg.success("推进成功");
			}
		}else{
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
		List<String> homePagePics = new ArrayList<String>();
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
			String homePagePic = activity.getHomepagePic();
			if (homePagePic != null) {
//				System.out.println(homePagePic);
				homePagePics.add(homePagePic);
				i ++;
			}
			if (i == 4) {
				break;
			}
		}
//		System.out.println(homePagePics);
		return Msg.success("处理成功").add("homePagePics", homePagePics);
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
			activityService.releaseActivity(activity);
			return Msg.success().add("activity", activity);
		} else {
			return Msg.error("活动信息不能为空");
		}
	}

	/**
	 * 返回首页数据 Activity:活动id，活动名称，首页图片路径，活动简介 用于首页轮询图片和活动摘要 分页返回，根据create_time排序
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
				result.add(new HomePageActivity(ac.getId(), ac.getName(), ac.getHomepagePic(), ac.getGeneral(),
						ac.getCreateTime()));
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
		// TODO
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
		// TODO
		return null;
	}
}
