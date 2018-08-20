package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;
import java.util.Date;

public class ArolEngFacilitySpecEntity {
    private Integer engSid;

    private BigDecimal width;

    private BigDecimal length;

    private Double height;

    private BigDecimal span;

    private Integer holenums;

    private String levelCode;

    private String loadCode;

    private BigDecimal headroom;

    private String startX;

    private String endX;

    private String pipeDiameter;

    private String pipeMaterial;

    private String aperture;

    private BigDecimal finishArea;

    private String consTypeCode;

    private String basisTypeCode;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    private String loadStandard;

    private String bridgeLoad;

    private String category;

    private String other;

    public ArolEngFacilitySpecEntity(Integer engSid, BigDecimal width, BigDecimal length, Double height, BigDecimal span, Integer holenums, String levelCode, String loadCode, BigDecimal headroom, String startX, String endX, String pipeDiameter, String pipeMaterial, String aperture, BigDecimal finishArea, String consTypeCode, String basisTypeCode, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt, String loadStandard, String bridgeLoad, String category, String other) {
        this.engSid = engSid;
        this.width = width;
        this.length = length;
        this.height = height;
        this.span = span;
        this.holenums = holenums;
        this.levelCode = levelCode;
        this.loadCode = loadCode;
        this.headroom = headroom;
        this.startX = startX;
        this.endX = endX;
        this.pipeDiameter = pipeDiameter;
        this.pipeMaterial = pipeMaterial;
        this.aperture = aperture;
        this.finishArea = finishArea;
        this.consTypeCode = consTypeCode;
        this.basisTypeCode = basisTypeCode;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.loadStandard = loadStandard;
        this.bridgeLoad = bridgeLoad;
        this.category = category;
        this.other = other;
    }

    public ArolEngFacilitySpecEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public BigDecimal getSpan() {
        return span;
    }

    public void setSpan(BigDecimal span) {
        this.span = span;
    }

    public Integer getHolenums() {
        return holenums;
    }

    public void setHolenums(Integer holenums) {
        this.holenums = holenums;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public String getLoadCode() {
        return loadCode;
    }

    public void setLoadCode(String loadCode) {
        this.loadCode = loadCode == null ? null : loadCode.trim();
    }

    public BigDecimal getHeadroom() {
        return headroom;
    }

    public void setHeadroom(BigDecimal headroom) {
        this.headroom = headroom;
    }

    public String getStartX() {
        return startX;
    }

    public void setStartX(String startX) {
        this.startX = startX == null ? null : startX.trim();
    }

    public String getEndX() {
        return endX;
    }

    public void setEndX(String endX) {
        this.endX = endX == null ? null : endX.trim();
    }

    public String getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(String pipeDiameter) {
        this.pipeDiameter = pipeDiameter == null ? null : pipeDiameter.trim();
    }

    public String getPipeMaterial() {
        return pipeMaterial;
    }

    public void setPipeMaterial(String pipeMaterial) {
        this.pipeMaterial = pipeMaterial == null ? null : pipeMaterial.trim();
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture == null ? null : aperture.trim();
    }

    public BigDecimal getFinishArea() {
        return finishArea;
    }

    public void setFinishArea(BigDecimal finishArea) {
        this.finishArea = finishArea;
    }

    public String getConsTypeCode() {
        return consTypeCode;
    }

    public void setConsTypeCode(String consTypeCode) {
        this.consTypeCode = consTypeCode == null ? null : consTypeCode.trim();
    }

    public String getBasisTypeCode() {
        return basisTypeCode;
    }

    public void setBasisTypeCode(String basisTypeCode) {
        this.basisTypeCode = basisTypeCode == null ? null : basisTypeCode.trim();
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

    public String getLoadStandard() {
        return loadStandard;
    }

    public void setLoadStandard(String loadStandard) {
        this.loadStandard = loadStandard == null ? null : loadStandard.trim();
    }

    public String getBridgeLoad() {
        return bridgeLoad;
    }

    public void setBridgeLoad(String bridgeLoad) {
        this.bridgeLoad = bridgeLoad == null ? null : bridgeLoad.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}