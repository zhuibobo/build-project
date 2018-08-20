package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ArolFileInfoEntity {
    private Integer sid;

    private Integer engSid;

    private Integer archSid;

    private String fileId;

    private String oldFileId;

    private String fileNo;

    private String archFileNo;

    private String regNo;

    private String microNo;

    private String fileTitle;

    private String responsibility;

    private String fileImageNo;

    private String manuscriptCode;

    private String storageTypeCode;

    private String securityLevelCode;

    private Date createdStart;

    private Date createdEnd;

    private String mediaTypeCode;

    private String pageNo;

    private String specCode;

    private Integer textNums;

    private Integer drawingNums;

    private Integer phoneNums;

    private Integer baseMapNums;

    private Integer negativeNums;

    private String consCode;

    private String segmentCode;

    private String fileSourceCode;

    private Integer scanFlag;

    private Integer lockStatus;

    private String unitsCode;

    private Integer pdfCreateFlag;

    private String pdfPath;

    private String pdfFilename;

    private Date pdfCreateDt;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    private Integer catalogid;

    private Integer realPageNums;

    private Integer uploadPageNums;

    private String checkOpinion;

    private String instructorRemark;

    private Integer status;

    private String fileTypeCode;

    private String fileType;

    private Integer fileSize;

    private String remark;

    private String checkPerson;

    private Date checkDate;

    private ProEngConfMateEntity proEngConfMateEntity;

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ProEngConfMateEntity getProEngConfMateEntity() {
        return proEngConfMateEntity;
    }

    public void setProEngConfMateEntity(ProEngConfMateEntity proEngConfMateEntity) {
        this.proEngConfMateEntity = proEngConfMateEntity;
    }

    public Integer getRealPageNums() {
        return realPageNums;
    }

    public void setRealPageNums(Integer realPageNums) {
        this.realPageNums = realPageNums;
    }

    public Integer getUploadPageNums() {
        return uploadPageNums;
    }

    public void setUploadPageNums(Integer uploadPageNums) {
        this.uploadPageNums = uploadPageNums;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getInstructorRemark() {
        return instructorRemark;
    }

    public void setInstructorRemark(String instructorRemark) {
        this.instructorRemark = instructorRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Integer catalogid) {
        this.catalogid = catalogid;
    }


    public ArolFileInfoEntity(Integer sid, Integer engSid, Integer archSid, String fileId, String oldFileId, String fileNo, String archFileNo, String regNo, String microNo, String fileTitle, String responsibility, String fileImageNo, String manuscriptCode, String storageTypeCode, String securityLevelCode, Date createdStart, Date createdEnd, String mediaTypeCode, String pageNo, String specCode, Integer textNums, Integer drawingNums, Integer phoneNums, Integer baseMapNums, Integer negativeNums, String consCode, String segmentCode, String fileSourceCode, Integer scanFlag, Integer lockStatus, String unitsCode, Integer pdfCreateFlag, String pdfPath, String pdfFilename, Date pdfCreateDt, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt,Integer catalogid,Integer realPageNums,Integer uploadPageNums,String checkOpinion,String instructorRemark,Integer status,String fileTypeCode,String fileType,Integer fileSize,String remark,String checkPerson,Date checkDate) {
        this.sid = sid;
        this.engSid = engSid;
        this.archSid = archSid;
        this.fileId = fileId;
        this.oldFileId = oldFileId;
        this.fileNo = fileNo;
        this.archFileNo = archFileNo;
        this.regNo = regNo;
        this.microNo = microNo;
        this.fileTitle = fileTitle;
        this.responsibility = responsibility;
        this.fileImageNo = fileImageNo;
        this.manuscriptCode = manuscriptCode;
        this.storageTypeCode = storageTypeCode;
        this.securityLevelCode = securityLevelCode;
        this.createdStart = createdStart;
        this.createdEnd = createdEnd;
        this.mediaTypeCode = mediaTypeCode;
        this.pageNo = pageNo;
        this.specCode = specCode;
        this.textNums = textNums;
        this.drawingNums = drawingNums;
        this.phoneNums = phoneNums;
        this.baseMapNums = baseMapNums;
        this.negativeNums = negativeNums;
        this.consCode = consCode;
        this.segmentCode = segmentCode;
        this.fileSourceCode = fileSourceCode;
        this.scanFlag = scanFlag;
        this.lockStatus = lockStatus;
        this.unitsCode = unitsCode;
        this.pdfCreateFlag = pdfCreateFlag;
        this.pdfPath = pdfPath;
        this.pdfFilename = pdfFilename;
        this.pdfCreateDt = pdfCreateDt;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.catalogid=catalogid;
        this.realPageNums=realPageNums;
        this.uploadPageNums=uploadPageNums;
        this.checkOpinion=checkOpinion;
        this.instructorRemark=instructorRemark;
        this.status=status;
        this.fileTypeCode=fileTypeCode;
        this.fileType=fileType;
        this.fileSize=fileSize;
        this.remark=remark;
        this.checkPerson=checkPerson;
        this.checkDate=checkDate;
    }

    public ArolFileInfoEntity() {
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

    public Integer getArchSid() {
        return archSid;
    }

    public void setArchSid(Integer archSid) {
        this.archSid = archSid;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getOldFileId() {
        return oldFileId;
    }

    public void setOldFileId(String oldFileId) {
        this.oldFileId = oldFileId == null ? null : oldFileId.trim();
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }

    public String getArchFileNo() {
        return archFileNo;
    }

    public void setArchFileNo(String archFileNo) {
        this.archFileNo = archFileNo == null ? null : archFileNo.trim();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo == null ? null : regNo.trim();
    }

    public String getMicroNo() {
        return microNo;
    }

    public void setMicroNo(String microNo) {
        this.microNo = microNo == null ? null : microNo.trim();
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility == null ? null : responsibility.trim();
    }

    public String getFileImageNo() {
        return fileImageNo;
    }

    public void setFileImageNo(String fileImageNo) {
        this.fileImageNo = fileImageNo == null ? null : fileImageNo.trim();
    }

    public String getManuscriptCode() {
        return manuscriptCode;
    }

    public void setManuscriptCode(String manuscriptCode) {
        this.manuscriptCode = manuscriptCode == null ? null : manuscriptCode.trim();
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

    public Date getCreatedStart() {
        return createdStart;
    }

    public void setCreatedStart(Date createdStart) {
        this.createdStart = createdStart;
    }

    public Date getCreatedEnd() {
        return createdEnd;
    }

    public void setCreatedEnd(Date createdEnd) {
        this.createdEnd = createdEnd;
    }

    public String getMediaTypeCode() {
        return mediaTypeCode;
    }

    public void setMediaTypeCode(String mediaTypeCode) {
        this.mediaTypeCode = mediaTypeCode == null ? null : mediaTypeCode.trim();
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo == null ? null : pageNo.trim();
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    public Integer getTextNums() {
        return textNums;
    }

    public void setTextNums(Integer textNums) {
        this.textNums = textNums;
    }

    public Integer getDrawingNums() {
        return drawingNums;
    }

    public void setDrawingNums(Integer drawingNums) {
        this.drawingNums = drawingNums;
    }

    public Integer getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(Integer phoneNums) {
        this.phoneNums = phoneNums;
    }

    public Integer getBaseMapNums() {
        return baseMapNums;
    }

    public void setBaseMapNums(Integer baseMapNums) {
        this.baseMapNums = baseMapNums;
    }

    public Integer getNegativeNums() {
        return negativeNums;
    }

    public void setNegativeNums(Integer negativeNums) {
        this.negativeNums = negativeNums;
    }

    public String getConsCode() {
        return consCode;
    }

    public void setConsCode(String consCode) {
        this.consCode = consCode == null ? null : consCode.trim();
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode == null ? null : segmentCode.trim();
    }

    public String getFileSourceCode() {
        return fileSourceCode;
    }

    public void setFileSourceCode(String fileSourceCode) {
        this.fileSourceCode = fileSourceCode == null ? null : fileSourceCode.trim();
    }

    public Integer getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(Integer scanFlag) {
        this.scanFlag = scanFlag;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getUnitsCode() {
        return unitsCode;
    }

    public void setUnitsCode(String unitsCode) {
        this.unitsCode = unitsCode == null ? null : unitsCode.trim();
    }

    public Integer getPdfCreateFlag() {
        return pdfCreateFlag;
    }

    public void setPdfCreateFlag(Integer pdfCreateFlag) {
        this.pdfCreateFlag = pdfCreateFlag;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath == null ? null : pdfPath.trim();
    }

    public String getPdfFilename() {
        return pdfFilename;
    }

    public void setPdfFilename(String pdfFilename) {
        this.pdfFilename = pdfFilename == null ? null : pdfFilename.trim();
    }

    public Date getPdfCreateDt() {
        return pdfCreateDt;
    }

    public void setPdfCreateDt(Date pdfCreateDt) {
        this.pdfCreateDt = pdfCreateDt;
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
}