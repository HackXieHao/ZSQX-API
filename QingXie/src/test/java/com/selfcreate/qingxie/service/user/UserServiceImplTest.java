package com.selfcreate.qingxie.service.user;

import com.selfcreate.qingxie.bean.user.UserExperience;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author evans 2018/3/10 18:15
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {
    final Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    UserService userService;
    @Test
    public void getIconUrl() {
        try {
            System.out.println(userService.getIconUrl(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserExperience(){
        try {
            UserExperience experience=new UserExperience();
            experience.setUserId(3);
            experience.setActivityName("南湖大草原");
            experience.setBegin(new Date());
            experience.setEnd(new Date());
            userService.updateUserExperience(experience, RequestMethod.POST);
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}