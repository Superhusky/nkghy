package com.njkgkj.nkg_hy.user.entity;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
public class UserEntity {
    private Integer id;
    private String guid;
    private String username;
    private String realname;
    private String password;
    private String department;
    private Integer isdelete;
    private String rmark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getRmark() {
        return rmark;
    }

    public void setRmark(String rmark) {
        this.rmark = rmark;
    }
}
