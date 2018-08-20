package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;
import java.util.Date;

public class ArolEngSpecInfoEntity {
    private String engSid;

    private BigDecimal landUseArea;

    private BigDecimal constructionArea;

    private BigDecimal engBudget;

    private BigDecimal engCost;

    private Date startDate;

    private Date endDate;

    private Date requestDate;

    private Date approvalDate;

    private String landUseTypeCode;

    private String landSupplyTypeCode;

    private String landPlanningTypeCode;

    private Integer buildingNums;

    private BigDecimal totalLength;

    private String terrainNo;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    private String seismicGrade;

    private String defenseArea;

    private String fireGrade;

    private String engQuality;

    private String awardsInfo;

    private Integer parkingNum;

    public ArolEngSpecInfoEntity(String engSid, BigDecimal landUseArea, BigDecimal constructionArea, BigDecimal engBudget, BigDecimal engCost, Date startDate, Date endDate, Date requestDate, Date approvalDate, String landUseTypeCode, String landSupplyTypeCode, String landPlanningTypeCode, Integer buildingNums, BigDecimal totalLength, String terrainNo, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt, String seismicGrade, String defenseArea, String fireGrade, String engQuality, String awardsInfo, Integer parkingNum) {
        this.engSid = engSid;
        this.landUseArea = landUseArea;
        this.constructionArea = constructionArea;
        this.engBudget = engBudget;
        this.engCost = engCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.landUseTypeCode = landUseTypeCode;
        this.landSupplyTypeCode = landSupplyTypeCode;
        this.landPlanningTypeCode = landPlanningTypeCode;
        this.buildingNums = buildingNums;
        this.totalLength = totalLength;
        this.terrainNo = terrainNo;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.seismicGrade = seismicGrade;
        this.defenseArea = defenseArea;
        this.fireGrade = fireGrade;
        this.engQuality = engQuality;
        this.awardsInfo = awardsInfo;
        this.parkingNum = parkingNum;
    }

    public ArolEngSpecInfoEntity() {
        super();
    }

    public String getEngSid() {
        return engSid;
    }

    public void setEngSid(String engSid) {
        this.engSid = engSid == null ? null : engSid.trim();
    }

    public BigDecimal getLandUseArea() {
        return landUseArea;
    }

    public void setLandUseArea(BigDecimal landUseArea) {
        this.landUseArea = landUseArea;
    }

    public BigDecimal getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(BigDecimal constructionArea) {
        this.constructionArea = constructionArea;
    }

    public BigDecimal getEngBudget() {
        return engBudget;
    }

    public void setEngBudget(BigDecimal engBudget) {
        this.engBudget = engBudget;
    }

    public BigDecimal getEngCost() {
        return engCost;
    }

    public void setEngCost(BigDecimal engCost) {
        this.engCost = engCost;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getLandUseTypeCode() {
        return landUseTypeCode;
    }

    public void setLandUseTypeCode(String landUseTypeCode) {
        this.landUseTypeCode = landUseTypeCode == null ? null : landUseTypeCode.trim();
    }

    public String getLandSupplyTypeCode() {
        return landSupplyTypeCode;
    }

    public void setLandSupplyTypeCode(String landSupplyTypeCode) {
        this.landSupplyTypeCode = landSupplyTypeCode == null ? null : landSupplyTypeCode.trim();
    }

    public String getLandPlanningTypeCode() {
        return landPlanningTypeCode;
    }

    public void setLandPlanningTypeCode(String landPlanningTypeCode) {
        this.landPlanningTypeCode = landPlanningTypeCode == null ? null : landPlanningTypeCode.trim();
    }

    public Integer getBuildingNums() {
        return buildingNums;
    }

    public void setBuildingNums(Integer buildingNums) {
        this.buildingNums = buildingNums;
    }

    public BigDecimal getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(BigDecimal totalLength) {
        this.totalLength = totalLength;
    }

    public String getTerrainNo() {
        return terrainNo;
    }

    public void setTerrainNo(String terrainNo) {
        this.terrainNo = terrainNo == null ? null : terrainNo.trim();
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

    public String getSeismicGrade() {
        return seismicGrade;
    }

    public void setSeismicGrade(String seismicGrade) {
        this.seismicGrade = seismicGrade == null ? null : seismicGrade.trim();
    }

    public String getDefenseArea() {
        return defenseArea;
    }

    public void setDefenseArea(String defenseArea) {
        this.defenseArea = defenseArea == null ? null : defenseArea.trim();
    }

    public String getFireGrade() {
        return fireGrade;
    }

    public void setFireGrade(String fireGrade) {
        this.fireGrade = fireGrade == null ? null : fireGrade.trim();
    }

    public String getEngQuality() {
        return engQuality;
    }

    public void setEngQuality(String engQuality) {
        this.engQuality = engQuality == null ? null : engQuality.trim();
    }

    public String getAwardsInfo() {
        return awardsInfo;
    }

    public void setAwardsInfo(String awardsInfo) {
        this.awardsInfo = awardsInfo == null ? null : awardsInfo.trim();
    }

    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }
}