package com.ceiec.webguide.formal.vo;

import com.ceiec.webguide.formal.entity.UserAccountEntity;

/**
 * CreateDate: 2018/4/23 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

public class UserAccountVo extends UserAccountEntity{

    private int systemMode;

    public int getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(int systemMode) {
        this.systemMode = systemMode;
    }

    public UserAccountVo(String userId, String username, String realName, String password, int role, String jobId, int department, String address, String contact, String signature, String avatar, int deleted, int systemMode) {
        super(userId, username, realName, password, role, jobId, department, address, contact, signature, avatar, deleted);
        this.systemMode = systemMode;
    }

    public UserAccountVo() {
    }

    @Override
    public String toString() {
        return "UserAccountVo{" +
                "systemMode=" + systemMode +
                '}';
    }
}
