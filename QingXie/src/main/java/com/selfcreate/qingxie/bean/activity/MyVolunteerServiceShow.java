package com.selfcreate.qingxie.bean.activity;

import java.util.Date;

/**
 * 用于我的志愿服务页面显示
 * @author Administrator
 *
 */
public class MyVolunteerServiceShow {

	//活动ID
	private Integer id;

	//活动名称
    private String name;
    
    //主办方
    private String sponser;
    
    //活动简介
    private String general;
    
    //活动状态
    private Integer status;
	
    //活动开始时间
    private Date startTime;

    //活动结束时间
    private Date endTime;

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
		this.name = name;
	}

	public String getSponser() {
		return sponser;
	}

	public void setSponser(String sponser) {
		this.sponser = sponser;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
    
    
}
