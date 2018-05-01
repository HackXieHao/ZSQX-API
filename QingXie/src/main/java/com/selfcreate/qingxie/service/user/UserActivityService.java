package com.selfcreate.qingxie.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.bean.user.UserActivityExample;
import com.selfcreate.qingxie.bean.user.UserActivityExample.Criteria;
import com.selfcreate.qingxie.dao.user.UserActivityMapper;

@Service
public class UserActivityService {
	
	@Autowired
	private UserActivityMapper userActivityMapper;
	
	/**
	 * 更新一条数据
	 * @param userActivity
	 */
	public void update(UserActivity userActivity){
		userActivityMapper.updateByPrimaryKey(userActivity);
	}
	
	/**
	 * 根据活动ID和用户ID获取用户活动数据
	 * @param activityId
	 * @param userId
	 * @return
	 */
	public UserActivity getByActivityIdAndUserId(Integer activityId, Integer userId){
		UserActivityExample example = new UserActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		criteria.andUserIdEqualTo(userId);
		if(userActivityMapper.selectByExample(example).size() > 0){
			return userActivityMapper.selectByExample(example).get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取活动对应的所有志愿者
	 * @param activityId
	 * @return
	 */
	public List<UserActivity> getAllVolunteersByActivityId(Integer activityId){
		UserActivityExample example = new UserActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		//状态为进行中或已结束的
		List<Integer> status = new ArrayList<Integer>();
		status.add(3);
		status.add(4);
		criteria.andStatusIn(status);
		return userActivityMapper.selectByExample(example);
	}
	
	/**
	 * 获取某个活动已报名人数
	 * @param activityId
	 * @return
	 */
	public long getApplyNumber(Integer activityId){
		UserActivityExample example = new UserActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		//状态为报名的
//		criteria.andStatusEqualTo(1);
		List<Integer> status = new ArrayList<Integer>();
		status.add(1);
		status.add(2);
		criteria.andStatusIn(status);
		return userActivityMapper.countByExample(example);
	}
	
	/**
	 * 根据activityId获取所有UserActivity信息
	 * @param activityId
	 * @return
	 */
	public List<UserActivity> getByActivityId(Integer activityId){
		UserActivityExample example = new UserActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andActivityIdEqualTo(activityId);
		return userActivityMapper.selectByExample(example);
	}
	
}
