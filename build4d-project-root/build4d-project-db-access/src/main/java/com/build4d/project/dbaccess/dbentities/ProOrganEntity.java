package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProOrganEntity {
    private Integer orgSid;

    private String orgNo;

    private String orgCode;

    private String orgName;

    private String orgDesc;

    private Date orgCreateTime;

    private String orgPhone;

    private String orgContacts;

    private String orgContMobile;

    private Integer orgOrder;

    private Integer orgStatus;

    private String orgType;

    private String orgEmail;

    private String orgAddr;

    private String creatreOrgId;

    public ProOrganEntity(Integer orgSid, String orgNo, String orgCode, String orgName, String orgDesc, Date orgCreateTime, String orgPhone, String orgContacts, String orgContMobile, Integer orgOrder, Integer orgStatus, String orgType, String orgEmail, String orgAddr) {
        this.orgSid = orgSid;
        this.orgNo = orgNo;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgDesc = orgDesc;
        this.orgCreateTime = orgCreateTime;
        this.orgPhone = orgPhone;
        this.orgContacts = orgContacts;
        this.orgContMobile = orgContMobile;
        this.orgOrder = orgOrder;
        this.orgStatus = orgStatus;
        this.orgType = orgType;
        this.orgEmail = orgEmail;
        this.orgAddr = orgAddr;
    }

    public ProOrganEntity(Integer orgSid, String orgNo, String orgCode, String orgName, String orgDesc, Date orgCreateTime, String orgPhone, String orgContacts, String orgContMobile, Integer orgOrder, Integer orgStatus, String orgType, String orgEmail, String orgAddr, String creatreOrgId) {
        this.orgSid = orgSid;
        this.orgNo = orgNo;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgDesc = orgDesc;
        this.orgCreateTime = orgCreateTime;
        this.orgPhone = orgPhone;
        this.orgContacts = orgContacts;
        this.orgContMobile = orgContMobile;
        this.orgOrder = orgOrder;
        this.orgStatus = orgStatus;
        this.orgType = orgType;
        this.orgEmail = orgEmail;
        this.orgAddr = orgAddr;
        this.creatreOrgId = creatreOrgId;
    }

    public ProOrganEntity(Integer orgSid, String orgCode, String orgName, String orgType) {
        this.orgSid = orgSid;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgType = orgType;
    }


    public ProOrganEntity() {
        super();
    }

    public Integer getOrgSid() {
        return orgSid;
    }

    public void setOrgSid(Integer orgSid) {
        this.orgSid = orgSid;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    public Date getOrgCreateTime() {
        return orgCreateTime;
    }

    public void setOrgCreateTime(Date orgCreateTime) {
        this.orgCreateTime = orgCreateTime;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    public String getOrgContacts() {
        return orgContacts;
    }

    public void setOrgContacts(String orgContacts) {
        this.orgContacts = orgContacts == null ? null : orgContacts.trim();
    }

    public String getOrgContMobile() {
        return orgContMobile;
    }

    public void setOrgContMobile(String orgContMobile) {
        this.orgContMobile = orgContMobile == null ? null : orgContMobile.trim();
    }

    public Integer getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(Integer orgOrder) {
        this.orgOrder = orgOrder;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgEmail() {
        return orgEmail;
    }
    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getCreatreOrgId() {
        return creatreOrgId;
    }

    public void setCreatreOrgId(String creatreOrgId) {
        this.creatreOrgId = creatreOrgId == null ? null : creatreOrgId.trim();
    }
}