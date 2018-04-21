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
import com.selfcreate.qingxie.bean.user.UserActivityHoursVw;
import com.selfcreate.qingxie.service.user.UserActivityHourService;
import com.selfcreate.qingxie.util.ResponseUtil;

@RequestMapping("/vhours")
@Controller
public class UserActivityHourController {
	
	@Autowired
	private UserActivityHourService userActivityHourService;
	
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
		String[] fileds = { "id", "userId", "activityName", "count", "voluntaryHours"};
		List<Map<String, Object>> response = ResponseUtil.getResultMap(userActivityHoursVws, fileds);
		return Msg.success().add("userActivityHours", response);
	}
	
}
