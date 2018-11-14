package com.dawei.test.bootdemo.entity;

import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Dawei 2018/11/14
 */
public class UserInfo implements Serializable {

    /* ID */
    private Long id;
    /* 用户名 */
    private String userName;
    /* 登陆名 */
    private String loginName;
    /* 昵称 */
    private String nickName;
    /* ICON */
    private String icon;
    /* photo */
    private String photoUrl;
    /*  密码 */
    private String password;
    /* salt */
    private String salt;
    /* phoneNum  */
    private String phoneNum;
    /* email */
    private String email;
    /* locked */
    private Integer locked;
    /* createTime */
    private Date createTime;
    private Date updateTime;
    private Long createBy;
    private Long updateBy;
    /* remarks */
    private String remarks;
    /* 删除标识 1 : 删除 */
    private Integer delFlag;



    private Set<RoleInfo> roleSet = Sets.newHashSet();

    private Set<MenuInfo> menuInfoSet= Sets.newHashSet();


    public UserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
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

    public Set<RoleInfo> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<RoleInfo> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<MenuInfo> getMenuInfoSet() {
        return menuInfoSet;
    }

    public void setMenuInfoSet(Set<MenuInfo> menuInfoSet) {
        this.menuInfoSet = menuInfoSet;
    }
}
