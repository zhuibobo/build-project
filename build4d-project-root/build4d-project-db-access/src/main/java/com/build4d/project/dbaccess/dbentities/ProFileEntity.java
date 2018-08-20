package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProFileEntity {
    private Integer fileSid;

    private String fileName;

    private String fileStoreName;

    private String fileStorePath;

    private Integer fileOrder;

    private String fileType;

    private String fileSize;

    private String fileCreater;

    private Date fileCreateTime;

    private String fileExtension;

    private String fileUnitId;

    private String fileUnitName;

    private String fileDesc;

    private String fileGroup1;

    private String fileGroup2;

    private String outerSid;

    private String outerTableName;

    public ProFileEntity(Integer fileSid, String fileName, String fileStoreName, String fileStorePath, Integer fileOrder, String fileType, String fileSize, String fileCreater, Date fileCreateTime, String fileExtension, String fileUnitId, String fileUnitName, String fileDesc, String fileGroup1, String fileGroup2, String outerSid, String outerTableName) {
        this.fileSid = fileSid;
        this.fileName = fileName;
        this.fileStoreName = fileStoreName;
        this.fileStorePath = fileStorePath;
        this.fileOrder = fileOrder;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileCreater = fileCreater;
        this.fileCreateTime = fileCreateTime;
        this.fileExtension = fileExtension;
        this.fileUnitId = fileUnitId;
        this.fileUnitName = fileUnitName;
        this.fileDesc = fileDesc;
        this.fileGroup1 = fileGroup1;
        this.fileGroup2 = fileGroup2;
        this.outerSid = outerSid;
        this.outerTableName = outerTableName;
    }

    public ProFileEntity() {
        super();
    }

    public Integer getFileSid() {
        return fileSid;
    }

    public void setFileSid(Integer fileSid) {
        this.fileSid = fileSid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileStoreName() {
        return fileStoreName;
    }

    public void setFileStoreName(String fileStoreName) {
        this.fileStoreName = fileStoreName == null ? null : fileStoreName.trim();
    }

    public String getFileStorePath() {
        return fileStorePath;
    }

    public void setFileStorePath(String fileStorePath) {
        this.fileStorePath = fileStorePath == null ? null : fileStorePath.trim();
    }

    public Integer getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(Integer fileOrder) {
        this.fileOrder = fileOrder;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileCreater() {
        return fileCreater;
    }

    public void setFileCreater(String fileCreater) {
        this.fileCreater = fileCreater == null ? null : fileCreater.trim();
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension == null ? null : fileExtension.trim();
    }

    public String getFileUnitId() {
        return fileUnitId;
    }

    public void setFileUnitId(String fileUnitId) {
        this.fileUnitId = fileUnitId == null ? null : fileUnitId.trim();
    }

    public String getFileUnitName() {
        return fileUnitName;
    }

    public void setFileUnitName(String fileUnitName) {
        this.fileUnitName = fileUnitName == null ? null : fileUnitName.trim();
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc == null ? null : fileDesc.trim();
    }

    public String getFileGroup1() {
        return fileGroup1;
    }

    public void setFileGroup1(String fileGroup1) {
        this.fileGroup1 = fileGroup1 == null ? null : fileGroup1.trim();
    }

    public String getFileGroup2() {
        return fileGroup2;
    }

    public void setFileGroup2(String fileGroup2) {
        this.fileGroup2 = fileGroup2 == null ? null : fileGroup2.trim();
    }

    public String getOuterSid() {
        return outerSid;
    }

    public void setOuterSid(String outerSid) {
        this.outerSid = outerSid == null ? null : outerSid.trim();
    }

    public String getOuterTableName() {
        return outerTableName;
    }

    public void setOuterTableName(String outerTableName) {
        this.outerTableName = outerTableName == null ? null : outerTableName.trim();
    }
}