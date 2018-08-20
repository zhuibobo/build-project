package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProOpLogEntity {
    private Integer logSid;

    private String logType;

    private String logName;

    private Integer userSid;

    private Date logCreateTime;

    private String logDesc;

    private String logText;

    public ProOpLogEntity(Integer logSid, String logType, String logName, Integer userSid, Date logCreateTime, String logDesc) {
        this.logSid = logSid;
        this.logType = logType;
        this.logName = logName;
        this.userSid = userSid;
        this.logCreateTime = logCreateTime;
        this.logDesc = logDesc;
    }

    public ProOpLogEntity(Integer logSid, String logType, String logName, Integer userSid, Date logCreateTime, String logDesc, String logText) {
        this.logSid = logSid;
        this.logType = logType;
        this.logName = logName;
        this.userSid = userSid;
        this.logCreateTime = logCreateTime;
        this.logDesc = logDesc;
        this.logText = logText;
    }

    public ProOpLogEntity() {
        super();
    }

    public Integer getLogSid() {
        return logSid;
    }

    public void setLogSid(Integer logSid) {
        this.logSid = logSid;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName == null ? null : logName.trim();
    }

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public Date getLogCreateTime() {
        return logCreateTime;
    }

    public void setLogCreateTime(Date logCreateTime) {
        this.logCreateTime = logCreateTime;
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc == null ? null : logDesc.trim();
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText == null ? null : logText.trim();
    }
}