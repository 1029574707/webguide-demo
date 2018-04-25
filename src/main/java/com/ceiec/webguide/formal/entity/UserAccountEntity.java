package com.ceiec.webguide.formal.entity;

import java.io.Serializable;

/**
 * CreateDate: 2018/4/11 <br/>
 * Author: WangHao <br/>
 * Description: 系统用户账号信息
 **/


public class UserAccountEntity implements Serializable {

    private static final long serialVersionUID = -3436383939709331131L;

    //用户Id，32位uuid
    private String userId;

    //用户真实姓名
    private String userName;

    //用户登录名
    private String loginName;

    //登录密码
    private String password;

    //用户角色
    private Integer role;

    //工号
    private String jobId;

    //部门
    private Integer department;

    //联系地址
    private String address;

    //联系方式
    private String contact;

    //个性签名
    private String signature;

    //头像URL
    private String avatar;

    //是否已经删除
    private int deleted;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public UserAccountEntity(String userId, String userName, String loginName, String password, Integer role, String jobId, Integer department, String address, String contact, String signature, String avatar, int deleted) {
        this.userId = userId;
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.role = role;
        this.jobId = jobId;
        this.department = department;
        this.address = address;
        this.contact = contact;
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
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", jobId='" + jobId + '\'' +
                ", department=" + department +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
