package com.ceiec.webguide.formal.entity;

import java.io.Serializable;
import java.util.Date;

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

    private String operatorId;

    private Date operateTime;

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

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return "SysLogEntity{" +
                "logId=" + logId +
                ", taskId='" + taskId + '\'' +
                ", logContent='" + logContent + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operateTime=" + operateTime +
                '}';
    }
}
