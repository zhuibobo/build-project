package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProAdvisoryEntity {
    private String advSid;

    private String advContent;

    private String userId;

    private String userName;

    private String organId;

    private String organName;

    private Date createtime;

    private String status;

    private String outerId;

    public ProAdvisoryEntity(String advSid, String advContent, String userId, String userName, String organId, String organName, Date createtime, String status, String outerId) {
        this.advSid = advSid;
        this.advContent = advContent;
        this.userId = userId;
        this.userName = userName;
        this.organId = organId;
        this.organName = organName;
        this.createtime = createtime;
        this.status = status;
        this.outerId = outerId;
    }

    public ProAdvisoryEntity() {
        super();
    }

    public String getAdvSid() {
        return advSid;
    }

    public void setAdvSid(String advSid) {
        this.advSid = advSid == null ? null : advSid.trim();
    }

    public String getAdvContent() {
        return advContent;
    }

    public void setAdvContent(String advContent) {
        this.advContent = advContent == null ? null : advContent.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }
}