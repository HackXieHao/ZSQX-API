package com.selfcreate.qingxie.bean.user;

public class UserActivityHoursVw {
    private Integer id;

    private Integer userId;

    private String userName;

    private Integer classId;

    private String className;

    private Integer activityId;

    private String activityName;

    private Integer count;

    private Integer voluntaryHours;

    private Integer isConfirm;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
}