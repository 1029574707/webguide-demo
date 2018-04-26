package com.ceiec.webguide.formal.entity;

import java.io.Serializable;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: 系统日志实体类
 **/
public class SysLogEntity implements Serializable{

    private static final long serialVersionUID = 8669887157527054170L;

    private Integer logId;

    private String taskId;

    private String logContent;

    private String operator;

    private String operationTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "SysLogVO{" +
                "logId=" + logId +
                ", taskId='" + taskId + '\'' +
                ", logContent='" + logContent + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                '}';
    }
}
