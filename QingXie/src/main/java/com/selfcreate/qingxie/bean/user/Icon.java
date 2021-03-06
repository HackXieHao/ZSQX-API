package com.selfcreate.qingxie.bean.user;

import java.util.Date;

public class Icon {
    private Integer iconId;

    private String iconPath;

    private Date createTime;

    public Icon() {
    }

    public Icon(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath == null ? null : iconPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}