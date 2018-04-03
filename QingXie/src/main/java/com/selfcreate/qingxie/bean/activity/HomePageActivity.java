package com.selfcreate.qingxie.bean.activity;

import java.util.Date;

/**
 * 首页卡片显示
 * @author Administrator
 *
 */
public class HomePageActivity {

	private int id;

	//活动名称
	private String name;

	private String homePagePath;

	//活动简介
	private String general;

	private Date createTime;

	public HomePageActivity(int id, String name, String homePagePath, String general, Date createTime) {
		this.id = id;
		this.name = name;
		this.homePagePath = homePagePath;
		this.general = general;
		this.createTime = createTime;
	}

	public HomePageActivity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHomePagePath() {
		return homePagePath;
	}

	public void setHomePagePath(String homePagePath) {
		this.homePagePath = homePagePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}
}
