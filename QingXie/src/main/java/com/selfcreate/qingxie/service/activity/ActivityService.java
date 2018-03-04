package com.selfcreate.qingxie.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.activity.ActivityExample;
import com.selfcreate.qingxie.bean.activity.ActivityExample.Criteria;
import com.selfcreate.qingxie.dao.activity.ActivityMapper;

@Service
public class ActivityService {

	@Autowired
	private ActivityMapper activityMapper;
	
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
