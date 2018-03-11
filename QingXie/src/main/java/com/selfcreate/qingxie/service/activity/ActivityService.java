package com.selfcreate.qingxie.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.activity.ActivityExample;
import com.selfcreate.qingxie.bean.activity.ActivityExample.Criteria;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.bean.user.UserActivityExample;
import com.selfcreate.qingxie.dao.activity.ActivityMapper;
import com.selfcreate.qingxie.dao.user.UserActivityMapper;

@Service
public class ActivityService {

	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private UserActivityMapper userActivityMapper;
	
	/**
	 * 根据活动id获取活动信息
	 * @param id
	 * @return
	 */
	public Activity getById(Integer id){
		return activityMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据userId获取参加的activity信息，用于我的志愿服务页面
	 * @param userId
	 * @return
	 */
	public List<UserActivity> getAllByUserId(Integer userId){
		UserActivityExample example = new UserActivityExample();
		com.selfcreate.qingxie.bean.user.UserActivityExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userActivityMapper.selectByExample(example);
	}
	
	/**
	 * 获取所有活动信息
	 * @return
	 */
	public List<Activity> getAll(){
		ActivityExample example = new ActivityExample();
		Criteria criteria = example.createCriteria();
		return activityMapper.selectByExample(example);
	}
	
	/**
	 * 发布活动
	 * @param activity
	 */
	public void releaseActivity(Activity activity){
		activityMapper.insertSelective(activity);
	}
	
}
