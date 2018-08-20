package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProEngConfMateEntity {
    private Integer mateSid;

    private Integer engSid;

    private String engValue;

    private String mateValue;

    private String mateName;

    private String unitType;

    private Integer mateNeed;

    private Date mateCreateTime;

    private Integer mateOrder;

    private Integer mateStatus;

    private String mateDesc;

    private String mateType;

    private Integer matePSid;

    private String materialNo;

    private Integer isdirectory;

    public ProEngConfMateEntity(Integer mateSid, Integer engSid, String engValue, String mateValue, String mateName, String unitType, Integer mateNeed, Date mateCreateTime, Integer mateOrder, Integer mateStatus, String mateDesc, String mateType,Integer matePSid,String materialNo,Integer isdirectory) {
        this.mateSid = mateSid;
        this.engSid = engSid;
        this.engValue = engValue;
        this.mateValue = mateValue;
        this.mateName = mateName;
        this.unitType = unitType;
        this.mateNeed = mateNeed;
        this.mateCreateTime = mateCreateTime;
        this.mateOrder = mateOrder;
        this.mateStatus = mateStatus;
        this.mateDesc = mateDesc;
        this.mateType = mateType;
        this.matePSid=matePSid;
        this.materialNo=materialNo;
        this.isdirectory=isdirectory;
    }

    public ProEngConfMateEntity(Integer mateSid, String mateValue, String mateName, String unitType, Integer matePSid,Integer isdirectory) {
        this.mateSid = mateSid;
        this.mateValue = mateValue;
        this.mateName = mateName;
        this.unitType = unitType;
        this.matePSid=matePSid;
        this.isdirectory=isdirectory;
    }

    public ProEngConfMateEntity() {
        super();
    }

    public Integer getMateSid() {
        return mateSid;
    }

    public void setMateSid(Integer mateSid) {
        this.mateSid = mateSid;
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

    public String getMateValue() {
        return mateValue;
    }

    public void setMateValue(String mateValue) {
        this.mateValue = mateValue == null ? null : mateValue.trim();
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName == null ? null : mateName.trim();
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType == null ? null : unitType.trim();
    }

    public Integer getMateNeed() {
        return mateNeed;
    }

    public void setMateNeed(Integer mateNeed) {
        this.mateNeed = mateNeed;
    }

    public Date getMateCreateTime() {
        return mateCreateTime;
    }

    public void setMateCreateTime(Date mateCreateTime) {
        this.mateCreateTime = mateCreateTime;
    }

    public Integer getMateOrder() {
        return mateOrder;
    }

    public void setMateOrder(Integer mateOrder) {
        this.mateOrder = mateOrder;
    }

    public Integer getMateStatus() {
        return mateStatus;
    }

    public void setMateStatus(Integer mateStatus) {
        this.mateStatus = mateStatus;
    }

    public String getMateDesc() {
        return mateDesc;
    }

    public void setMateDesc(String mateDesc) {
        this.mateDesc = mateDesc == null ? null : mateDesc.trim();
    }

    public String getMateType() {
        return mateType;
    }

    public void setMateType(String mateType) {
        this.mateType = mateType == null ? null : mateType.trim();
    }

    public Integer getMatePSid() {
        return matePSid;
    }

    public void setMatePSid(Integer matePSid) {
        this.matePSid = matePSid;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public Integer getIsdirectory() {
        return isdirectory;
    }

    public void setIsdirectory(Integer isdirectory) {
        this.isdirectory = isdirectory;
    }

}