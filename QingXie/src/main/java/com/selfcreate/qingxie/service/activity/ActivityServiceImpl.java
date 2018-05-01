package com.selfcreate.qingxie.service.activity;

import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.activity.Activity4User;
import com.selfcreate.qingxie.bean.activity.ActivityExample;
import com.selfcreate.qingxie.bean.activity.HomePageActivity;
import com.selfcreate.qingxie.bean.user.Favourite;
import com.selfcreate.qingxie.bean.user.FavouriteExample;
import com.selfcreate.qingxie.bean.user.FavouriteExample.Criteria;
import com.selfcreate.qingxie.bean.user.UserActivity;
import com.selfcreate.qingxie.bean.user.UserActivityExample;
import com.selfcreate.qingxie.dao.activity.ActivityMapper;
import com.selfcreate.qingxie.dao.user.FavouriteMapper;
import com.selfcreate.qingxie.dao.user.UserActivityMapper;
import com.selfcreate.qingxie.enums.ActivityStatusEnum;
import com.selfcreate.qingxie.enums.RolesEnum;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.exception.RepeatOperateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author evans 2018/4/2 0:17
 */

@Service
public class ActivityServiceImpl implements ActivityService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private FavouriteMapper favouriteMapper;

    /**
     * 判断是否重复收藏
     * @param userId
     * @param activityId
     * @return
     */
    public boolean isForkAgain(Integer userId, Integer activityId){
    	FavouriteExample example = new FavouriteExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andUserIdEqualTo(userId);
    	criteria.andActivityIdEqualTo(activityId);
    	if(favouriteMapper.selectByExample(example).size() > 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * 根据活动id获取活动信息
     *
     * @param id
     * @return
     */
    @Override
    public Activity getById(Integer id) {
        try {
            return activityMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("数据库异常");
        }
    }

    /**
     * 根据userId获取参加的activity信息，用于我的志愿服务页面
     * 活动id，活动名称，主办方，活动简介，活动状态，活动开始时间，活动结束时间。
     *
     * @param userId
     * @return
     */
    @Override
    public List<Activity4User> getAllByUserId(Integer userId) {
        try {
            return queryByUserAndStuff(userId, 0);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("系统异常");
        }
    }

    /**
     * FIXME:需要验证用户是否为青协工作者，根据roleId或者flag字段
     *
     * @param userId
     * @return
     */
    @Override
    public List<Activity4User> getWorksByUserId(Integer userId) {
        try {
            return queryByUserAndStuff(userId, 2, 1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("系统异常");
        }
    }

    @Override
    public List<Activity4User> getForks(Integer userId) {
        try {
            //根据userId查询favouriteList
            FavouriteExample example = new FavouriteExample();
            example.createCriteria().andUserIdEqualTo(userId);
            example.setOrderByClause("create_time desc");
            List<Favourite> favouriteList = favouriteMapper.selectByExample(example);
            //遍历favouriteList，根据activityId查询对应活动
            if (!favouriteList.isEmpty()) {
                List<Activity4User> result = new ArrayList<>();
                for (Favourite fa : favouriteList) {
                    Activity ac = activityMapper.selectByPrimaryKey(fa.getActivityId());
                    result.add(new Activity4User(fa.getId(), ac.getId(),
                            ac.getName(), ac.getSponsor(), ac.getGeneral(),
                            //防止出现status为空报错
                            ActivityStatusEnum.getName(ac.getStatus() == null ? 0 : ac.getStatus()),
                            ac.getCreateTime(), null));
                }
                return result;
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("系统异常");
        }
    }

    private List<Activity4User> queryByUserAndStuff(Integer userId, Integer... stuffCode) {
        //查询userActivity
        UserActivityExample example = new UserActivityExample();
        for (int i = 0; i < stuffCode.length; i++) {
            example.or().andUserIdEqualTo(userId).andStuffEqualTo(stuffCode[i]);
        }
        //根据时间降序排序
        example.setOrderByClause("create_time desc");
        List<UserActivity> uacs = userActivityMapper.selectByExample(example);
        //查询对应的Activity
        if (!uacs.isEmpty()) {
            List<Activity4User> result = new ArrayList<>();
            for (UserActivity uac : uacs) {
                Activity ac = activityMapper.selectByPrimaryKey(uac.getActivityId());
                result.add(new Activity4User(uac.getId(), ac.getId(), ac.getName(), RolesEnum.getName(uac.getStuff()),
                        ac.getSponsor(), ac.getGeneral(), ActivityStatusEnum.getName(uac.getStatus() == null ? 0 : uac.getStatus()),
                        uac.getCreateTime(), ac.getEndTime()));
            }
            return result;
        }
        return null;
    }

    /**
     * 获取所有活动信息
     *
     * @return
     */
    @Override
    public List<Activity> getAll() {
        try {
            ActivityExample example = new ActivityExample();
            example.setOrderByClause("create_time desc");
            return activityMapper.selectByExample(example);
            // activity根据创建时间排序
//        activities.sort((o1, o2) -> Long.compare(o2.getCreateTime().getTime(), o1.getCreateTime().getTime()));
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new QingxieInnerException("数据库异常");
        }
    }

    /**
     * 发布活动
     *
     * @param activity
     */
    @Override
    public void releaseActivity(Activity activity) {
        activityMapper.insertSelective(activity);
    }

    /**
     * 1.报名人数限制? 无
     * 2.报名条件限制？无
     * 3.重复报名
     * 4.权限验证
     * todo:验证是否在报名中
     * @param userId
     * @param activityId
     */
    @Override
    public void signUp(Integer userId, Integer activityId) {
        //查询是否已存在报名信息
        UserActivityExample example=new UserActivityExample();
        example.createCriteria().andUserIdEqualTo(userId).andActivityIdEqualTo(activityId);
        boolean hasSignedUp=userActivityMapper.selectByExample(example).size()>0;
        if(hasSignedUp){
            throw new RepeatOperateException("重复报名");
        }
        UserActivity userActivity=new UserActivity(userId,activityId,0,1,0);
        try{
            userActivityMapper.insert(userActivity);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new QingxieInnerException("数据库异常");
        }
    }
    /**
     * 添加至我的收藏
     * @param favourite
     */
    @Override
    public void addFork(Favourite favourite){
        favouriteMapper.insertSelective(favourite);
    }

    /**
     * 更新活动记录
     * @param activity
     */
    @Override
    public void updateActivity(Activity activity){
        activityMapper.updateByPrimaryKey(activity);
    }

}
