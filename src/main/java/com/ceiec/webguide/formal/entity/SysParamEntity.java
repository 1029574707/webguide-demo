package com.ceiec.webguide.formal.entity;

import java.io.Serializable;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: 系统参数实体类
 **/
public class SysParamEntity implements Serializable{

    private static final long serialVersionUID = 1201597743912813226L;

    private Integer parameterId;

    private String parameterName;

    private double value;

    private String operateRoles;

    private Integer systemMode;

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getOperateRoles() {
        return operateRoles;
    }

    public void setOperateRoles(String operateRoles) {
        this.operateRoles = operateRoles;
    }

    public Integer getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(Integer systemMode) {
        this.systemMode = systemMode;
    }

    @Override
    public String toString() {
        return "SysParamEntity{" +
                "parameterId=" + parameterId +
                ", parameterName='" + parameterName + '\'' +
                ", value=" + value +
                ", operateRoles=" + operateRoles +
                ", systemMode=" + systemMode +
                '}';
    }
}
