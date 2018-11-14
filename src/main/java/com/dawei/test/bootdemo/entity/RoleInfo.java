package com.dawei.test.bootdemo.entity;

import java.util.Date;
import java.util.Set;

/**
 * @author Dawei 2018/11/14
 */
public class RoleInfo {

    private static final long serialVersionUID = 1L;

    private Long id;
    /* 角色名称   */
    private String roleName;
    /* 创建时间*/
    private Date createTime;

    private Long createBy;

    private String remarks;

    private Integer delFlag;

    private Set<MenuInfo> menuInfoSet;

    private Set<UserInfo> userInfoSet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Set<MenuInfo> getMenuInfoSet() {
        return menuInfoSet;
    }

    public void setMenuInfoSet(Set<MenuInfo> menuInfoSet) {
        this.menuInfoSet = menuInfoSet;
    }

    public Set<UserInfo> getUserInfoSet() {
        return userInfoSet;
    }

    public void setUserInfoSet(Set<UserInfo> userInfoSet) {
        this.userInfoSet = userInfoSet;
    }
}
