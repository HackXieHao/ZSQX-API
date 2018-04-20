package com.selfcreate.qingxie.enums;

/**
 * 志愿者映射枚举类
 * @author evans 2018/4/3 20:10
 */

public enum  RolesEnum {
    /**
     * 0表示志愿者
     * 1表示负责人
     * 2表示青协工作者
     */
    VOLUNTEER("志愿者",0),
    CHARGER("负责人",1),
    WORKER("青协工作者",2);
    private int code;

    private String role;

    private RolesEnum(String role,int code){
        this.code=code;
        this.role=role;
    }

    // 普通方法
    public static String getName(int index) {
        for (RolesEnum c : values()) {
            if (c.getCode() == index) {
                return c.role;
            }
        }
        return null;
    }
    // get set 方法
    public String getStatus() {
        return role;
    }
    public void setStatus(String role) {
        this.role = role;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
