package com.ceiec.webguide.formal.entity;

import java.io.Serializable;

/**
 * CreateDate: 2018/4/9 <br/>
 * Author: WangHao <br/>
 * Description:
 **/

public class UserEntity implements Serializable{

    private static final long serialVersionUID = -2260195792728818456L;

    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
