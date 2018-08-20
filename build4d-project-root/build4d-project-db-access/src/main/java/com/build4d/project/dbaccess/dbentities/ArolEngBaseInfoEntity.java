package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ArolEngBaseInfoEntity {
    private Integer sid;

    private String orgNoCode;

    private Integer recOrgSid;

    private Integer projSid;

    private String engId;

    private String engNo;

    private String engName;

    private String engZone;

    private String engLocation;

    private String archCategoryCode;

    private String engCategoryCode;

    private String oldEngId;

    private Date archivingDate;

    private String handoverOrgName;

    //具体见：\src\main\webapp\Js\ProProcessLib.js
    private Integer status;

    private Integer sourceCode;

    private String microNo;

    private String indexedBy;

    private Date indexedDt;

    private String arrangedBy;

    private Date arrangedDt;

    private String checkedBy;

    private Date checkedDt;

    private Integer engType;

    private Integer engType2;

    private Integer specType;

    private String engFormerName;

    private String archiveYear;

    private Date transferDate;

    private String receiveBy;

    private Date receiveDt;

    private String deposit;

    private String securityLevelCode;

    private String storageTypeCode;

    private Integer engGuideSid;

    private String roadName;

    private String note;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    //建设单位信息
    private ProOrganEntity constructionOrgEntity;
    //項目基本信息
    private ArolProjBaseInfoEntity proBaseInfo;
    //通用专业记载信息
    private ArolEngSpecInfoEntity engSpecInfo;
    //房屋建筑专业记载信息
    private ArolEngHouseSpecEntity engHouseSpec;
    //市政建筑专业记载信息
    private ArolEngFacilitySpecEntity engFacilitySpec;
    //规划建筑专业记载信息
    private ArolEngPBSpecEntity engPbSpec;
    //其他专业记载信息
    private ArolEngOtherSpecEntity engOtherSpec;
    //工程责任者信息关联
    private ArolEngOrgAndCodeEntity engOrgAndCode;

    public ProOrganEntity getConstructionOrgEntity() {
        if(constructionOrgEntity==null)
            constructionOrgEntity=new ProOrganEntity();
        return constructionOrgEntity;
    }

    public void setConstructionOrgEntity(ProOrganEntity constructionOrgEntity) {
        this.constructionOrgEntity = constructionOrgEntity;
    }

    public ArolEngOrgAndCodeEntity getEngOrgAndCode() {
        return engOrgAndCode;
    }

    public void setEngOrgAndCode(ArolEngOrgAndCodeEntity engOrgAndCode) {
        this.engOrgAndCode = engOrgAndCode;
    }

    public ArolEngSpecInfoEntity getEngSpecInfo() {
        return engSpecInfo;
    }

    public void setEngSpecInfo(ArolEngSpecInfoEntity engSpecInfo) {
        this.engSpecInfo = engSpecInfo;
    }

    public ArolEngHouseSpecEntity getEngHouseSpec() {
        return engHouseSpec;
    }

    public void setEngHouseSpec(ArolEngHouseSpecEntity engHouseSpec) {
        this.engHouseSpec = engHouseSpec;
    }

    public ArolEngFacilitySpecEntity getEngFacilitySpec() {
        return engFacilitySpec;
    }

    public void setEngFacilitySpec(ArolEngFacilitySpecEntity engFacilitySpec) {
        this.engFacilitySpec = engFacilitySpec;
    }

    public ArolEngPBSpecEntity getEngPbSpec() {
        return engPbSpec;
    }

    public void setEngPbSpec(ArolEngPBSpecEntity engPbSpec) {
        this.engPbSpec = engPbSpec;
    }

    public ArolEngOtherSpecEntity getEngOtherSpec() {
        return engOtherSpec;
    }

    public void setEngOtherSpec(ArolEngOtherSpecEntity engOtherSpec) {
        this.engOtherSpec = engOtherSpec;
    }

    public ArolProjBaseInfoEntity getProBaseInfo() {
        return proBaseInfo;
    }

    public void setProBaseInfo(ArolProjBaseInfoEntity proBaseInfo) {
        this.proBaseInfo = proBaseInfo;
    }

    public ArolEngBaseInfoEntity(Integer sid, String orgNoCode,Integer recOrgSid, Integer projSid, String engId, String engNo, String engName, String engZone, String engLocation, String archCategoryCode, String engCategoryCode, String oldEngId, Date archivingDate, String handoverOrgName, Integer status, Integer sourceCode, String microNo, String indexedBy, Date indexedDt, String arrangedBy, Date arrangedDt, String checkedBy, Date checkedDt, Integer engType, Integer engType2,Integer specType, String engFormerName, String archiveYear, Date transferDate, String receiveBy, Date receiveDt, String deposit, String securityLevelCode, String storageTypeCode, Integer engGuideSid, String roadName, String note, Integer version, String updatedBy, Date updatedDt) {
        this.sid = sid;
        this.orgNoCode = orgNoCode;
        this.projSid = projSid;
        this.engId = engId;
        this.engNo = engNo;
        this.engName = engName;
        this.engZone = engZone;
        this.engLocation = engLocation;
        this.archCategoryCode = archCategoryCode;
        this.engCategoryCode = engCategoryCode;
        this.oldEngId = oldEngId;
        this.archivingDate = archivingDate;
        this.handoverOrgName = handoverOrgName;
        this.status = status;
        this.sourceCode = sourceCode;
        this.microNo = microNo;
        this.indexedBy = indexedBy;
        this.indexedDt = indexedDt;
        this.arrangedBy = arrangedBy;
        this.arrangedDt = arrangedDt;
        this.checkedBy = checkedBy;
        this.checkedDt = checkedDt;
        this.engType = engType;
        this.engType2 = engType2;
        this.specType = specType;
        this.engFormerName = engFormerName;
        this.archiveYear = archiveYear;
        this.transferDate = transferDate;
        this.receiveBy = receiveBy;
        this.receiveDt = receiveDt;
        this.deposit = deposit;
        this.securityLevelCode = securityLevelCode;
        this.storageTypeCode = storageTypeCode;
        this.engGuideSid = engGuideSid;
        this.roadName = roadName;
        this.note = note;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.recOrgSid=recOrgSid;
    }

    public ArolEngBaseInfoEntity(String engNo, String engName,Integer engType) {
        this.engNo = engNo;
        this.engName = engName;
        this.engType = engType;
    }
    public ArolEngBaseInfoEntity() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getOrgNoCode() {
        return orgNoCode;
    }

    public void setOrgNoCode(String orgNoCode) {
        this.orgNoCode = orgNoCode == null ? null : orgNoCode.trim();
    }


    public Integer getRecOrgSid() {
        return recOrgSid;
    }

    public void setRecOrgSid(Integer recOrgSid) {
        this.recOrgSid = recOrgSid;
    }

    public Integer getProjSid() {
        return projSid;
    }

    public void setProjSid(Integer projSid) {
        this.projSid = projSid;
    }

    public String getEngId() {
        return engId;
    }

    public void setEngId(String engId) {
        this.engId = engId == null ? null : engId.trim();
    }

    public String getEngNo() {
        return engNo;
    }

    public void setEngNo(String engNo) {
        this.engNo = engNo == null ? null : engNo.trim();
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName == null ? null : engName.trim();
    }

    public String getEngZone() {
        return engZone;
    }

    public void setEngZone(String engZone) {
        this.engZone = engZone == null ? null : engZone.trim();
    }

    public String getEngLocation() {
        return engLocation;
    }

    public void setEngLocation(String engLocation) {
        this.engLocation = engLocation == null ? null : engLocation.trim();
    }

    public String getArchCategoryCode() {
        return archCategoryCode;
    }

    public void setArchCategoryCode(String archCategoryCode) {
        this.archCategoryCode = archCategoryCode == null ? null : archCategoryCode.trim();
    }

    public String getEngCategoryCode() {
        return engCategoryCode;
    }

    public void setEngCategoryCode(String engCategoryCode) {
        this.engCategoryCode = engCategoryCode == null ? null : engCategoryCode.trim();
    }

    public String getOldEngId() {
        return oldEngId;
    }

    public void setOldEngId(String oldEngId) {
        this.oldEngId = oldEngId == null ? null : oldEngId.trim();
    }

    public Date getArchivingDate() {
        return archivingDate;
    }

    public void setArchivingDate(Date archivingDate) {
        this.archivingDate = archivingDate;
    }

    public String getHandoverOrgName() {
        return handoverOrgName;
    }

    public void setHandoverOrgName(String handoverOrgName) {
        this.handoverOrgName = handoverOrgName == null ? null : handoverOrgName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(Integer sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getMicroNo() {
        return microNo;
    }

    public void setMicroNo(String microNo) {
        this.microNo = microNo == null ? null : microNo.trim();
    }

    public String getIndexedBy() {
        return indexedBy;
    }

    public void setIndexedBy(String indexedBy) {
        this.indexedBy = indexedBy == null ? null : indexedBy.trim();
    }

    public Date getIndexedDt() {
        return indexedDt;
    }

    public void setIndexedDt(Date indexedDt) {
        this.indexedDt = indexedDt;
    }

    public String getArrangedBy() {
        return arrangedBy;
    }

    public void setArrangedBy(String arrangedBy) {
        this.arrangedBy = arrangedBy == null ? null : arrangedBy.trim();
    }

    public Date getArrangedDt() {
        return arrangedDt;
    }

    public void setArrangedDt(Date arrangedDt) {
        this.arrangedDt = arrangedDt;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy == null ? null : checkedBy.trim();
    }

    public Date getCheckedDt() {
        return checkedDt;
    }

    public void setCheckedDt(Date checkedDt) {
        this.checkedDt = checkedDt;
    }

    public Integer getEngType() {
        return engType;
    }

    public void setEngType(Integer engType) {
        this.engType = engType;
    }

    public Integer getEngType2() {
        return engType2;
    }

    public void setEngType2(Integer engType2) {
        this.engType2 = engType2;
    }

    public Integer getSpecType() {
        return specType;
    }

    public void setSpecType(Integer specType) {
        this.specType = specType;
    }

    public String getEngFormerName() {
        return engFormerName;
    }

    public void setEngFormerName(String engFormerName) {
        this.engFormerName = engFormerName == null ? null : engFormerName.trim();
    }

    public String getArchiveYear() {
        return archiveYear;
    }

    public void setArchiveYear(String archiveYear) {
        this.archiveYear = archiveYear == null ? null : archiveYear.trim();
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy == null ? null : receiveBy.trim();
    }

    public Date getReceiveDt() {
        return receiveDt;
    }

    public void setReceiveDt(Date receiveDt) {
        this.receiveDt = receiveDt;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public String getSecurityLevelCode() {
        return securityLevelCode;
    }

    public void setSecurityLevelCode(String securityLevelCode) {
        this.securityLevelCode = securityLevelCode == null ? null : securityLevelCode.trim();
    }

    public String getStorageTypeCode() {
        return storageTypeCode;
    }

    public void setStorageTypeCode(String storageTypeCode) {
        this.storageTypeCode = storageTypeCode == null ? null : storageTypeCode.trim();
    }

    public Integer getEngGuideSid() {
        return engGuideSid;
    }

    public void setEngGuideSid(Integer engGuideSid) {
        this.engGuideSid = engGuideSid;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName == null ? null : roadName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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

}