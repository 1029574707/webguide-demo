package com.ceiec.webguide.formal.entity;

import java.io.Serializable;

/**
 * CreateDate: 2018/4/11 <br/>
 * Author: WangHao <br/>
 * Description: 系统用户账号信息
 **/


public class UserAccountEntity implements Serializable {

    private static final long serialVersionUID = -3436383939709331131L;

    //user id, use UUID
    private String userId;

    //the username for login
    private String username;

    //the user's real name
    private String realName;

    //password
    private String password;

    //the role for this user
    private Integer role;

    //job number for this user
    private String jobId;

    //department for this user
    private Integer department;

    //address for this user
    private String address;

    //the contact phone number for this user
    private String mobile;

    //personal signature
    private String signature;

    //the URL for this user's avatar
    private String avatar;

    //if this user is deleted, 0 for not deleted, 1 for already deleted
    private int deleted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public UserAccountEntity(String userId, String username, String realName, String password, Integer role, String jobId, Integer department, String address, String mobile, String signature, String avatar, int deleted) {
        this.userId = userId;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.role = role;
        this.jobId = jobId;
        this.department = department;
        this.address = address;
        this.mobile = mobile;
        this.signature = signature;
        this.avatar = avatar;
        this.deleted = deleted;
    }

    public UserAccountEntity() {
    }

    @Override
    public String toString() {
        return "UserAccountEntity{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", jobId='" + jobId + '\'' +
                ", department=" + department +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
