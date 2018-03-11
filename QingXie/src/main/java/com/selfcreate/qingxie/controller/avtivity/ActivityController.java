package com.selfcreate.qingxie.controller.avtivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.activity.HomePageActivity;
import com.selfcreate.qingxie.bean.activity.HomePagePic;
import com.selfcreate.qingxie.bean.activity.MyVolunteerServiceShow;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.service.activity.ActivityService;

@RequestMapping("/activity/")
@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	// private static Logger log = Logger.getLogger(ActivityController.class);

	/**
	 * 我的志愿服务显示
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMyVolunteerService/{userId}", method = RequestMethod.GET)
	public Msg getMyVolunteerService(@PathVariable("userId") Integer userId,@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		//分页尚存BUG
		PageHelper.startPage(page, size);
//		List<Activity> activities = activityService.getAll();
		List<UserActivity> userActivities = activityService.getAllByUserId(userId);
		List<MyVolunteerServiceShow> myVolunteerServiceShows = new ArrayList<MyVolunteerServiceShow>();
		for (UserActivity userActivity : userActivities) {
			MyVolunteerServiceShow myVolunteerServiceShow = new MyVolunteerServiceShow();
			Activity activity = activityService.getById(userActivity.getActivityId());
			myVolunteerServiceShow.setId(activity.getId());
			myVolunteerServiceShow.setName(activity.getName());
			myVolunteerServiceShow.setSponser(activity.getSponser());
			myVolunteerServiceShow.setGeneral(activity.getGeneral());
			myVolunteerServiceShow.setStatus(activity.getStatus());
			myVolunteerServiceShow.setStartTime(activity.getStartTime());
			myVolunteerServiceShow.setEndTime(activity.getEndTime());
			myVolunteerServiceShows.add(myVolunteerServiceShow);
		}

		// 活动信息
		PageInfo pageInfo = new PageInfo(myVolunteerServiceShows);
		return Msg.success().add("activities", pageInfo);
	}

	/**
	 * 获取所有活动信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAllActivities", method = RequestMethod.GET)
	public Msg getAllActivities(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		//page和Size有BUG
		PageHelper.startPage(page, size);
		List<Activity> activities = activityService.getAll();
		// activity根据创建时间排序
		Collections.sort(activities, new Comparator<Activity>() {
			@Override
			public int compare(Activity o1, Activity o2) {
				if (o1.getCreateTime().getTime() > o2.getCreateTime().getTime()) {
					return -1;
				} else if (o1.getCreateTime().getTime() < o2.getCreateTime().getTime()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		for (Activity activity : activities) {
			System.out.println(activity.getId());
		}

		List<HomePageActivity> homePageActivities = new ArrayList<HomePageActivity>();
		for (Activity activity : activities) {
			HomePageActivity homePageActivity = new HomePageActivity();
			homePageActivity.setName(activity.getName());
			homePageActivity.setGeneral(activity.getGeneral());
			homePageActivities.add(homePageActivity);
		}

		// 活动信息
		PageInfo pageInfo = new PageInfo(activities);
		pageInfo.setList(homePageActivities);

		List<HomePagePic> homePagePics = new ArrayList<HomePagePic>();
		int flag = 0;
		for (Activity activity : activities) {
			if (flag < 4) {
				HomePagePic homePagePic = new HomePagePic();
				homePagePic.setId(activity.getId());
				homePagePic.setGeneral(activity.getGeneral());
				homePagePic.setHomepagePic(activity.getHomepagePic());
				homePagePics.add(homePagePic);
				flag ++ ;
			}

		}

		return Msg.success().add("activities", pageInfo).add("homePagePics", homePagePics);
	}

	/**
	 * 发布活动
	 * 
	 * @param activity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "releaseActivity", method = RequestMethod.PUT)
	public Msg releaseActivity(@RequestBody Activity activity) {
		if (activity != null) {
			activityService.releaseActivity(activity);
			return Msg.success().add("activity", activity);
		} else {
			return Msg.error("活动信息不能为空");
		}
	}
}
