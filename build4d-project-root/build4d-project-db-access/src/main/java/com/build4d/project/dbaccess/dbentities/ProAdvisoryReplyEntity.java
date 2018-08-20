package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProAdvisoryReplyEntity {
    private String replySid;

    private String replyContent;

    private String advSid;

    private String userId;

    private String userName;

    private String organId;

    private String organName;

    private Date createtime;

    private String status;

    public ProAdvisoryReplyEntity(String replySid, String replyContent, String advSid, String userId, String userName, String organId, String organName, Date createtime, String status) {
        this.replySid = replySid;
        this.replyContent = replyContent;
        this.advSid = advSid;
        this.userId = userId;
        this.userName = userName;
        this.organId = organId;
        this.organName = organName;
        this.createtime = createtime;
        this.status = status;
    }

    public ProAdvisoryReplyEntity() {
        super();
    }

    public String getReplySid() {
        return replySid;
    }

    public void setReplySid(String replySid) {
        this.replySid = replySid == null ? null : replySid.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public String getAdvSid() {
        return advSid;
    }

    public void setAdvSid(String advSid) {
        this.advSid = advSid == null ? null : advSid.trim();
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
}