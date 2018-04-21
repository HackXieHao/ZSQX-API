package com.selfcreate.qingxie.bean.activity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Activity {
    private Integer id;

    private String name;

    private Integer managerId;

    private String type;

    private Integer status;

    private Double hours;

    private Double hourPerTime;

    private Integer needVolunteers;

    private String place;

    private String general;

    private String descriptions;

    private Date regTime;

    private Date regEndTime;

    private Date interviewTime;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String sponsor;

    private String homepagePic;

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

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getHourPerTime() {
        return hourPerTime;
    }

    public void setHourPerTime(Double hourPerTime) {
        this.hourPerTime = hourPerTime;
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

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general == null ? null : general.trim();
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions == null ? null : descriptions.trim();
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public String getHomepagePic() {
        return homepagePic;
    }

    public void setHomepagePic(String homepagePic) {
        this.homepagePic = homepagePic == null ? null : homepagePic.trim();
    }

	@Override
	public String toString() {
		return "Activity [id=" + id + "]";
	}

	
    
    
}