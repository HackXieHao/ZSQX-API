package com.selfcreate.qingxie.bean.activity;

import java.util.Date;

public class Activity {
    private Integer id;

    private String name;

    private Integer managerId;

    private Integer hours;

    private Integer hourPerTime;

    private Date regTime;

    private Date regEndTime;

    private Date interviewTime;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String general;

    private Integer needVolunteers;

    private String place;

    private String descriptions;

    private Integer activityDetailPictureId;

    private Integer homepagePictureId;

    private String type;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getHourPerTime() {
        return hourPerTime;
    }

    public void setHourPerTime(Integer hourPerTime) {
        this.hourPerTime = hourPerTime;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general == null ? null : general.trim();
    }

    public Integer getNeedVolunteers() {
        return needVolunteers;
    }

    public void setNeedVolunteers(Integer needVolunteers) {
        this.needVolunteers = needVolunteers;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions == null ? null : descriptions.trim();
    }

    public Integer getActivityDetailPictureId() {
        return activityDetailPictureId;
    }

    public void setActivityDetailPictureId(Integer activityDetailPictureId) {
        this.activityDetailPictureId = activityDetailPictureId;
    }

    public Integer getHomepagePictureId() {
        return homepagePictureId;
    }

    public void setHomepagePictureId(Integer homepagePictureId) {
        this.homepagePictureId = homepagePictureId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}