package com.ceiec.webguide.formal.vo;

import com.ceiec.webguide.formal.entity.SysLogEntity;

/**
 * CreateDate: 2018/4/26 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class SysLogVO extends SysLogEntity {

    private Integer operatorRole;

    public Integer getOperatorRole() {
        return operatorRole;
    }

    public void setOperatorRole(Integer operatorRole) {
        this.operatorRole = operatorRole;
    }

    @Override
    public String toString() {
        return "SysLogVO{" +
                "operatorRole=" + operatorRole +
                '}';
    }
}
