package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ArolArchInfoEntity {
    private Integer sid;

    private Integer engSid;

    private String archNo;

    private String archId;

    private Integer boxSid;

    private String engArchNo;

    private String regNo;

    private String archTitle;

    private String makeOrgName;

    private String storageTypeCode;

    private String securityLevelCode;

    private String mediaTypeCode;

    private String archTypeCode;

    private String unitsCode;

    private String specCode;

    private Date startDate;

    private Date endDate;

    private String checkTypeCode;

    private String oldArchId;

    private String archWidth;

    private String shelvingTypeCode;

    private String compactDiskNums;

    private String lockStatus;

    private String microNo;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    private String remark;

    private Integer organSid;

    private Integer status;

    private String checkOpinion;

    private String checkPerson;

    private Date checkDate;

    private ArolEngBaseInfoEntity engBaseInfoEntity;


    public ArolArchInfoEntity(Integer sid, Integer engSid, String archNo, String archId, Integer boxSid, String engArchNo, String regNo, String archTitle, String makeOrgName, String storageTypeCode, String securityLevelCode, String mediaTypeCode, String archTypeCode, String unitsCode, String specCode, Date startDate, Date endDate, String checkTypeCode, String oldArchId, String archWidth, String shelvingTypeCode, String compactDiskNums, String lockStatus, String microNo, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt, String remark, Integer organSid,Integer status,String checkOpinion,String checkPerson,Date checkDate) {
        this.sid = sid;
        this.engSid = engSid;
        this.archNo = archNo;
        this.archId = archId;
        this.boxSid = boxSid;
        this.engArchNo = engArchNo;
        this.regNo = regNo;
        this.archTitle = archTitle;
        this.makeOrgName = makeOrgName;
        this.storageTypeCode = storageTypeCode;
        this.securityLevelCode = securityLevelCode;
        this.mediaTypeCode = mediaTypeCode;
        this.archTypeCode = archTypeCode;
        this.unitsCode = unitsCode;
        this.specCode = specCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.checkTypeCode = checkTypeCode;
        this.oldArchId = oldArchId;
        this.archWidth = archWidth;
        this.shelvingTypeCode = shelvingTypeCode;
        this.compactDiskNums = compactDiskNums;
        this.lockStatus = lockStatus;
        this.microNo = microNo;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.remark=remark;
        this.organSid=organSid;

        this.status=status;
        this.checkOpinion=checkOpinion;
        this.checkPerson=checkPerson;
        this.checkDate=checkDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public ArolArchInfoEntity() {
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

    public String getArchNo() {
        return archNo;
    }

    public void setArchNo(String archNo) {
        this.archNo = archNo == null ? null : archNo.trim();
    }

    public String getArchId() {
        return archId;
    }

    public void setArchId(String archId) {
        this.archId = archId == null ? null : archId.trim();
    }

    public Integer getBoxSid() {
        return boxSid;
    }

    public void setBoxSid(Integer boxSid) {
        this.boxSid = boxSid;
    }

    public String getEngArchNo() {
        return engArchNo;
    }

    public void setEngArchNo(String engArchNo) {
        this.engArchNo = engArchNo == null ? null : engArchNo.trim();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo == null ? null : regNo.trim();
    }

    public String getArchTitle() {
        return archTitle;
    }

    public void setArchTitle(String archTitle) {
        this.archTitle = archTitle == null ? null : archTitle.trim();
    }

    public String getMakeOrgName() {
        return makeOrgName;
    }

    public void setMakeOrgName(String makeOrgName) {
        this.makeOrgName = makeOrgName == null ? null : makeOrgName.trim();
    }

    public String getStorageTypeCode() {
        return storageTypeCode;
    }

    public void setStorageTypeCode(String storageTypeCode) {
        this.storageTypeCode = storageTypeCode == null ? null : storageTypeCode.trim();
    }

    public String getSecurityLevelCode() {
        return securityLevelCode;
    }

    public void setSecurityLevelCode(String securityLevelCode) {
        this.securityLevelCode = securityLevelCode == null ? null : securityLevelCode.trim();
    }

    public String getMediaTypeCode() {
        return mediaTypeCode;
    }

    public void setMediaTypeCode(String mediaTypeCode) {
        this.mediaTypeCode = mediaTypeCode == null ? null : mediaTypeCode.trim();
    }

    public String getArchTypeCode() {
        return archTypeCode;
    }

    public void setArchTypeCode(String archTypeCode) {
        this.archTypeCode = archTypeCode == null ? null : archTypeCode.trim();
    }

    public String getUnitsCode() {
        return unitsCode;
    }

    public void setUnitsCode(String unitsCode) {
        this.unitsCode = unitsCode == null ? null : unitsCode.trim();
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode == null ? null : checkTypeCode.trim();
    }

    public String getOldArchId() {
        return oldArchId;
    }

    public void setOldArchId(String oldArchId) {
        this.oldArchId = oldArchId == null ? null : oldArchId.trim();
    }

    public String getArchWidth() {
        return archWidth;
    }

    public void setArchWidth(String archWidth) {
        this.archWidth = archWidth == null ? null : archWidth.trim();
    }

    public String getShelvingTypeCode() {
        return shelvingTypeCode;
    }

    public void setShelvingTypeCode(String shelvingTypeCode) {
        this.shelvingTypeCode = shelvingTypeCode == null ? null : shelvingTypeCode.trim();
    }

    public String getCompactDiskNums() {
        return compactDiskNums;
    }

    public void setCompactDiskNums(String compactDiskNums) {
        this.compactDiskNums = compactDiskNums == null ? null : compactDiskNums.trim();
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus == null ? null : lockStatus.trim();
    }

    public String getMicroNo() {
        return microNo;
    }

    public void setMicroNo(String microNo) {
        this.microNo = microNo == null ? null : microNo.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrganSid() {
        return organSid;
    }

    public void setOrganSid(Integer organSid) {
        this.organSid = organSid;
    }


    public ArolEngBaseInfoEntity getEngBaseInfoEntity() {
        return engBaseInfoEntity;
    }

    public void setEngBaseInfoEntity(ArolEngBaseInfoEntity engBaseInfoEntity) {
        this.engBaseInfoEntity = engBaseInfoEntity;
    }
}