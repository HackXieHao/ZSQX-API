package com.selfcreate.qingxie.enums;

/**
 * @author evans 2018/4/2 22:23
 */

public enum  ActivityStatusEnum {
    /**
     * 1表示报名中
     */
    REGISTERING("报名中",1),
    REG_REFUSED("报名失败",-1),
    INTERVIEWING("面试中",2),
    INT_REFUSED("面试失败",-2),
    INVOKING("进行中",3),
    FINISHED("已完成",4),
    DEFAULT("无记录",0);
    /**
     * 状态描述
     */
    private String status;
    /**
     * 状态代码
     */
    private int code;

    /**
     * 构造函数
     * @param status
     * @param code
     */
    private ActivityStatusEnum(String status, int code) {
        this.status = status;
        this.code = code;
    }
    // 普通方法
    public static String getName(int index) {
        for (ActivityStatusEnum c : values()) {
            if (c.getCode() == index) {
                return c.status;
            }
        }
        return null;
    }
    // get set 方法
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
