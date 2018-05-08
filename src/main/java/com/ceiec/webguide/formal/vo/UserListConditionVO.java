package com.ceiec.webguide.formal.vo;

/**
 * CreateDate: 2018/5/8 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class UserListConditionVO extends ParentVO{

    private String userId;

    private int role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserListConditionVO{" +
                "userId='" + userId + '\'' +
                ", role=" + role +
                '}';
    }
}
