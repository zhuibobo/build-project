package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProEngFlowLogEntity {
    private Integer sid;

    private Integer engSid;

    private Integer organSid;

    private String organName;

    private String organType;

    private Integer userSid;

    private String userName;

    private Date opDate;

    private Integer opStatus;

    private String opOpinion;

    public ProEngFlowLogEntity(Integer sid, Integer engSid, Integer organSid, String organName, String organType, Integer userSid, String userName, Date opDate, Integer opStatus, String opOpinion) {
        this.sid = sid;
        this.engSid = engSid;
        this.organSid = organSid;
        this.organName = organName;
        this.organType = organType;
        this.userSid = userSid;
        this.userName = userName;
        this.opDate = opDate;
        this.opStatus = opStatus;
        this.opOpinion = opOpinion;
    }

    public ProEngFlowLogEntity() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public Integer getOrganSid() {
        return organSid;
    }

    public void setOrganSid(Integer organSid) {
        this.organSid = organSid;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType == null ? null : organType.trim();
    }

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public Integer getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(Integer opStatus) {
        this.opStatus = opStatus;
    }

    public String getOpOpinion() {
        return opOpinion;
    }

    public void setOpOpinion(String opOpinion) {
        this.opOpinion = opOpinion == null ? null : opOpinion.trim();
    }
}