package com.selfcreate.qingxie.service.activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.selfcreate.qingxie.bean.activity.HomePageActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.activity.ActivityExample;
import com.selfcreate.qingxie.bean.activity.ActivityExample.Criteria;
import com.selfcreate.qingxie.bean.user.Favourite;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.bean.user.UserActivityExample;
import com.selfcreate.qingxie.dao.activity.ActivityMapper;
import com.selfcreate.qingxie.dao.user.UserActivityMapper;

public interface ActivityService {

    Activity getById(Integer id);

    List getAll();

    List getAllByUserId(Integer userId);

    List getWorksByUserId(Integer userId);

    List getForks(Integer userId);

    void releaseActivity(Activity activity);

    void addFork(Favourite favourite);
    
    void updateActivity(Activity activity);
    
    boolean isForkAgain(Integer userId, Integer activityId);

    void signUp(Integer userId,Integer activityId);
}
