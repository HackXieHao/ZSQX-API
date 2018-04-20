package com.selfcreate.qingxie.bean.user;

public class ClassInfo {
    private Integer id;

    private String name;

    private String manager;

    public ClassInfo() {
    }

    public ClassInfo(String name, String manager) {
        this.name = name;
        this.manager = manager;
    }

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + name.hashCode() + name.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        ClassInfo info = (ClassInfo) obj;
        return name.equals(info.getName()) && manager.equals(info.getManager());
    }
}