package com.selfcreate.qingxie.bean.user;

import java.util.Date;

public class UserActivityHours {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private Integer voluntaryHours;

    private Integer isConfirm;

    private Date createTime;

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

    public Integer getVoluntaryHours() {
        return voluntaryHours;
    }

    public void setVoluntaryHours(Integer voluntaryHours) {
        this.voluntaryHours = voluntaryHours;
    }

    public Integer getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}