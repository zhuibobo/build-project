package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProEngConfTypeEntity {
    private Integer engSid;

    private String engValue;

    private String engName;

    private String engDesc;

    private Date engCreateTime;

    private Integer engOrder;

    private Integer engStatus;

    public ProEngConfTypeEntity(Integer engSid, String engValue, String engName, String engDesc, Date engCreateTime, Integer engOrder, Integer engStatus) {
        this.engSid = engSid;
        this.engValue = engValue;
        this.engName = engName;
        this.engDesc = engDesc;
        this.engCreateTime = engCreateTime;
        this.engOrder = engOrder;
        this.engStatus = engStatus;
    }

    public ProEngConfTypeEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public String getEngValue() {
        return engValue;
    }

    public void setEngValue(String engValue) {
        this.engValue = engValue == null ? null : engValue.trim();
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName == null ? null : engName.trim();
    }

    public String getEngDesc() {
        return engDesc;
    }

    public void setEngDesc(String engDesc) {
        this.engDesc = engDesc == null ? null : engDesc.trim();
    }

    public Date getEngCreateTime() {
        return engCreateTime;
    }

    public void setEngCreateTime(Date engCreateTime) {
        this.engCreateTime = engCreateTime;
    }

    public Integer getEngOrder() {
        return engOrder;
    }

    public void setEngOrder(Integer engOrder) {
        this.engOrder = engOrder;
    }

    public Integer getEngStatus() {
        return engStatus;
    }

    public void setEngStatus(Integer engStatus) {
        this.engStatus = engStatus;
    }
}