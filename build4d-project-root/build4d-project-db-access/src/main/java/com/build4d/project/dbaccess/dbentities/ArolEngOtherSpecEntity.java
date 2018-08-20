package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;
import java.util.Date;

public class ArolEngOtherSpecEntity {
    private Integer engSid;

    private String useTypeCode;

    private String requisitionTypeCode;

    private String oldUseType;

    private Date approvalDate;

    private BigDecimal useArea;

    private String scale;

    private String loadLevel;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    public ArolEngOtherSpecEntity(Integer engSid, String useTypeCode, String requisitionTypeCode, String oldUseType, Date approvalDate, BigDecimal useArea, String scale, String loadLevel, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt) {
        this.engSid = engSid;
        this.useTypeCode = useTypeCode;
        this.requisitionTypeCode = requisitionTypeCode;
        this.oldUseType = oldUseType;
        this.approvalDate = approvalDate;
        this.useArea = useArea;
        this.scale = scale;
        this.loadLevel = loadLevel;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
    }

    public ArolEngOtherSpecEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public String getUseTypeCode() {
        return useTypeCode;
    }

    public void setUseTypeCode(String useTypeCode) {
        this.useTypeCode = useTypeCode == null ? null : useTypeCode.trim();
    }

    public String getRequisitionTypeCode() {
        return requisitionTypeCode;
    }

    public void setRequisitionTypeCode(String requisitionTypeCode) {
        this.requisitionTypeCode = requisitionTypeCode == null ? null : requisitionTypeCode.trim();
    }

    public String getOldUseType() {
        return oldUseType;
    }

    public void setOldUseType(String oldUseType) {
        this.oldUseType = oldUseType == null ? null : oldUseType.trim();
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getLoadLevel() {
        return loadLevel;
    }

    public void setLoadLevel(String loadLevel) {
        this.loadLevel = loadLevel == null ? null : loadLevel.trim();
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
}