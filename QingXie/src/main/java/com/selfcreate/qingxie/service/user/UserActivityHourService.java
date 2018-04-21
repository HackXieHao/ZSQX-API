package com.selfcreate.qingxie.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.bean.user.UserActivityHours;
import com.selfcreate.qingxie.bean.user.UserActivityHoursVw;
import com.selfcreate.qingxie.bean.user.UserActivityHoursVwExample;
import com.selfcreate.qingxie.bean.user.UserActivityHoursVwExample.Criteria;
import com.selfcreate.qingxie.bean.user.UserExample;
import com.selfcreate.qingxie.dao.user.UserActivityHoursMapper;
import com.selfcreate.qingxie.dao.user.UserActivityHoursVwMapper;
import com.selfcreate.qingxie.dao.user.UserMapper;

@Service
public class UserActivityHourService {
	
	@Autowired
	private UserActivityHoursMapper userActivityHoursMapper;
	
	@Autowired
	private UserActivityHoursVwMapper userActivityHoursVwMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 获取某个班级所有人
	 * @param classId
	 * @return
	 */
	public List<User> getUserByClassId(Integer classId){
		UserExample example = new UserExample();
		com.selfcreate.qingxie.bean.user.UserExample.Criteria criteria = example.createCriteria();
		criteria.andClassIdEqualTo(classId);
		return userMapper.selectByExample(example);
	}
	
	/**
	 * 获取某活动所有人工时详情
	 * @param activityId
	 * @return
	 */
	public List<UserActivityHoursVw> getByActivityId(Integer activityId){
		UserActivityHoursVwExample example = new UserActivityHoursVwExample();
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		return userActivityHoursVwMapper.selectByExample(example);
	}
	
	/**
	 * 我的志愿工时查询
	 * @param userId
	 * @return
	 */
	public List<UserActivityHoursVw> getByUserId(Integer userId){
		UserActivityHoursVwExample example = new UserActivityHoursVwExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userActivityHoursVwMapper.selectByExample(example);
	}
	
	/**
	 * 添加一条userActivityHours记录
	 * @param userActivityHours
	 */
	public void add(UserActivityHours userActivityHours){
		userActivityHoursMapper.insertSelective(userActivityHours);
	}
	
}
