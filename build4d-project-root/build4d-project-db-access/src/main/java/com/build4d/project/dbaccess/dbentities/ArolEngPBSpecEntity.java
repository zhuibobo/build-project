package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;
import java.util.Date;

public class ArolEngPBSpecEntity {
    private Integer engSid;

    private BigDecimal consArea;

    private Integer buidlingNums;

    private BigDecimal lengthValue;

    private BigDecimal heightValue;

    private Integer floors;

    private BigDecimal widthValue;

    private String specCode;

    private String loadCode;

    private String headroom;

    private String holenums;

    private String materialCode;

    private String subEngName;

    private String overNums;

    private String underNums;

    private BigDecimal useAmount;

    private String resourceNums;

    private String parcelNo;

    private String basisTypeCode;

    private String startX;

    private String endX;

    private Date approvalDate;

    private Date applayDate;

    private BigDecimal useArea;

    private String useTypeCode;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    public ArolEngPBSpecEntity(Integer engSid, BigDecimal consArea, Integer buidlingNums, BigDecimal lengthValue, BigDecimal heightValue, Integer floors, BigDecimal widthValue, String specCode, String loadCode, String headroom, String holenums, String materialCode, String subEngName, String overNums, String underNums, BigDecimal useAmount, String resourceNums, String parcelNo, String basisTypeCode, String startX, String endX, Date approvalDate, Date applayDate, BigDecimal useArea, String useTypeCode, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt) {
        this.engSid = engSid;
        this.consArea = consArea;
        this.buidlingNums = buidlingNums;
        this.lengthValue = lengthValue;
        this.heightValue = heightValue;
        this.floors = floors;
        this.widthValue = widthValue;
        this.specCode = specCode;
        this.loadCode = loadCode;
        this.headroom = headroom;
        this.holenums = holenums;
        this.materialCode = materialCode;
        this.subEngName = subEngName;
        this.overNums = overNums;
        this.underNums = underNums;
        this.useAmount = useAmount;
        this.resourceNums = resourceNums;
        this.parcelNo = parcelNo;
        this.basisTypeCode = basisTypeCode;
        this.startX = startX;
        this.endX = endX;
        this.approvalDate = approvalDate;
        this.applayDate = applayDate;
        this.useArea = useArea;
        this.useTypeCode = useTypeCode;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
    }

    public ArolEngPBSpecEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public BigDecimal getConsArea() {
        return consArea;
    }

    public void setConsArea(BigDecimal consArea) {
        this.consArea = consArea;
    }

    public Integer getBuidlingNums() {
        return buidlingNums;
    }

    public void setBuidlingNums(Integer buidlingNums) {
        this.buidlingNums = buidlingNums;
    }

    public BigDecimal getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(BigDecimal lengthValue) {
        this.lengthValue = lengthValue;
    }

    public BigDecimal getHeightValue() {
        return heightValue;
    }

    public void setHeightValue(BigDecimal heightValue) {
        this.heightValue = heightValue;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public BigDecimal getWidthValue() {
        return widthValue;
    }

    public void setWidthValue(BigDecimal widthValue) {
        this.widthValue = widthValue;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    public String getLoadCode() {
        return loadCode;
    }

    public void setLoadCode(String loadCode) {
        this.loadCode = loadCode == null ? null : loadCode.trim();
    }

    public String getHeadroom() {
        return headroom;
    }

    public void setHeadroom(String headroom) {
        this.headroom = headroom == null ? null : headroom.trim();
    }

    public String getHolenums() {
        return holenums;
    }

    public void setHolenums(String holenums) {
        this.holenums = holenums == null ? null : holenums.trim();
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getSubEngName() {
        return subEngName;
    }

    public void setSubEngName(String subEngName) {
        this.subEngName = subEngName == null ? null : subEngName.trim();
    }

    public String getOverNums() {
        return overNums;
    }

    public void setOverNums(String overNums) {
        this.overNums = overNums == null ? null : overNums.trim();
    }

    public String getUnderNums() {
        return underNums;
    }

    public void setUnderNums(String underNums) {
        this.underNums = underNums == null ? null : underNums.trim();
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public String getResourceNums() {
        return resourceNums;
    }

    public void setResourceNums(String resourceNums) {
        this.resourceNums = resourceNums == null ? null : resourceNums.trim();
    }

    public String getParcelNo() {
        return parcelNo;
    }

    public void setParcelNo(String parcelNo) {
        this.parcelNo = parcelNo == null ? null : parcelNo.trim();
    }

    public String getBasisTypeCode() {
        return basisTypeCode;
    }

    public void setBasisTypeCode(String basisTypeCode) {
        this.basisTypeCode = basisTypeCode == null ? null : basisTypeCode.trim();
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

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getApplayDate() {
        return applayDate;
    }

    public void setApplayDate(Date applayDate) {
        this.applayDate = applayDate;
    }

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public String getUseTypeCode() {
        return useTypeCode;
    }

    public void setUseTypeCode(String useTypeCode) {
        this.useTypeCode = useTypeCode == null ? null : useTypeCode.trim();
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