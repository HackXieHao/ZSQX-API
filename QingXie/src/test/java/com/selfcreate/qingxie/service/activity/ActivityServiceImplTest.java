package com.selfcreate.qingxie.service.activity;

import com.selfcreate.qingxie.bean.activity.Activity4User;
import com.selfcreate.qingxie.controller.avtivity.BaseControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author evans 2018/4/2 0:32
 */

public class ActivityServiceImplTest extends BaseControllerTest {

    @Autowired
    ActivityService activityService;
    @Test
    public void getById() {
    }

    @Test
    public void getAllByUserId() {
        List<Activity4User> acs=activityService.getAllByUserId(2);
        for(Activity4User ac:acs){
            System.out.println(ac.getName());
        }
    }

    @Test
    public void getAll() {
        List acs=activityService.getAll();
        for(Object obj:acs){
            System.out.println(obj);
        }
    }


    @Test
    public void releaseActivity() {
    }
}