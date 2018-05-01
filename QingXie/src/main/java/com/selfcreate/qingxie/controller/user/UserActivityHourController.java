package com.selfcreate.qingxie.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.bean.user.UserActivityHours;
import com.selfcreate.qingxie.bean.user.UserActivityHoursVw;
import com.selfcreate.qingxie.service.user.UserActivityHourService;
import com.selfcreate.qingxie.service.user.UserService;
import com.selfcreate.qingxie.util.ResponseUtil;

@RequestMapping("/vhours")
@Controller
public class UserActivityHourController {
	
	@Autowired
	private UserActivityHourService userActivityHourService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取班级所有学生的工时
	 * @param classId
	 * @param page
	 * @param size
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{classId}/general", method = RequestMethod.GET)
	public Msg getUserHoursByClassId(@PathVariable("classId") Integer classId, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size){
		PageHelper.startPage(page, size);
		List<User> users = userActivityHourService.getUserByClassId(classId);
		//计算总工时
		for (User user : users){
			List<UserActivityHoursVw> userActivityHourVws = userActivityHourService.getByUserId(user.getId());
			double hoursSum = 0;
			for (UserActivityHoursVw userActivityHoursVw : userActivityHourVws){
				hoursSum += userActivityHoursVw.getVoluntaryHours();
			}
			user.setHours(hoursSum);
			userService.update(user);
		}
		String[] fileds = { "id", "studentId", "name", "hours"};
		List<Map<String, Object>> response = ResponseUtil.getResultMap(users, fileds);
		PageInfo pageInfo = new PageInfo(response);
		return Msg.success().add("classHours", pageInfo);
	}
	
	/**
	 * 获取某活动所有人工时详情
	 * @param activityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{activityId}/detailsByActivityId", method = RequestMethod.GET)
	public Msg getUserActivityHoursByActivityId(@PathVariable("activityId") Integer activityId, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size){
		System.out.println(page);
		PageHelper.startPage(page,size);
		List<UserActivityHoursVw> userActivityHoursVws = userActivityHourService.getByActivityId(activityId);
		String[] fileds = { "id", "userName", "activityName", "className", "voluntaryHours"};
		List<Map<String, Object>> response = ResponseUtil.getResultMap(userActivityHoursVws, fileds);
		PageInfo pageInfo = new PageInfo(response);
		return Msg.success().add("userActivityHours", pageInfo);
	}
	
	/**
	 * 我的志愿工时查询
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{userId}/detailsByUserId", method = RequestMethod.GET)
	public Msg getUserActivityHoursByUserId(@PathVariable("userId") Integer userId){
		List<UserActivityHoursVw> userActivityHoursVws = userActivityHourService.getByUserId(userId);
		String[] fileds = { "id", "userId", "activityName", "count", "voluntaryHours","activityStarTime"};
		List<Map<String, Object>> response = ResponseUtil.getResultMap(userActivityHoursVws, fileds);
		return Msg.success().add("userActivityHours", response);
	}
	
}
