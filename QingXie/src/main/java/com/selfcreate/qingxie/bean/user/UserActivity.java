package com.selfcreate.qingxie.bean.user;

import java.util.Date;

public class UserActivity {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private Integer count;

    /**
     * 进行状态，1：报名，-1:报名失败，2:面试，-2:面试失败，3:参与志愿服务当中,0:默认值，无意义
     */
    private Integer status;

    private Integer stuff;

    private Integer condition;

    private Date regTime;

    private Date interviewTime;

    private Date createTime;

    public UserActivity(Integer userId, Integer activityId, Integer count, Integer status, Integer stuff) {
        this.userId = userId;
        this.activityId = activityId;
        this.count = count;
        this.status = status;
        this.stuff = stuff;
    }

    public UserActivity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStuff() {
        return stuff;
    }

    public void setStuff(Integer stuff) {
        this.stuff = stuff;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}