package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;

public class ArolProjBaseInfoEntity {
    private Integer sid;

    private String projId;

    private String orgNoCode;

    private Integer conOrgSid;

    private String projName;

    private String projLocation;

    private BigDecimal totalLandArea;

    private BigDecimal totalBuildingArea;

    private Integer buildingNums;

    private BigDecimal plotRatio;

    private BigDecimal greenCoverage;

    private BigDecimal buildingDensity;

    private Integer upParkingNums;

    private Integer downParkingNums;

    private BigDecimal totalConsBudget;

    private BigDecimal totalConsSettlement;

    private BigDecimal totalLandPrice;

    private Integer version;

    private ProOrganEntity proOrgan;

    public ProOrganEntity getProOrgan() {
        return proOrgan;
    }

    public void setProOrgan(ProOrganEntity proOrgan) {
        this.proOrgan = proOrgan;
    }

    public ArolProjBaseInfoEntity(String projId, String projName, String orgNoCode,Integer conOrgSid) {
        this.projId = projId;
        this.projName = projName;
        this.orgNoCode=orgNoCode;
        this.conOrgSid=conOrgSid;
    }

    public ArolProjBaseInfoEntity(Integer sid, String projId, String orgNoCode,Integer conOrgSid, String projName, String projLocation, BigDecimal totalLandArea, BigDecimal totalBuildingArea, Integer buildingNums, BigDecimal plotRatio, BigDecimal greenCoverage, BigDecimal buildingDensity, Integer upParkingNums, Integer downParkingNums, BigDecimal totalConsBudget, BigDecimal totalConsSettlement, BigDecimal totalLandPrice, Integer version) {
        this.sid = sid;
        this.projId = projId;
        this.orgNoCode = orgNoCode;
        this.projName = projName;
        this.projLocation = projLocation;
        this.totalLandArea = totalLandArea;
        this.totalBuildingArea = totalBuildingArea;
        this.buildingNums = buildingNums;
        this.plotRatio = plotRatio;
        this.greenCoverage = greenCoverage;
        this.buildingDensity = buildingDensity;
        this.upParkingNums = upParkingNums;
        this.downParkingNums = downParkingNums;
        this.totalConsBudget = totalConsBudget;
        this.totalConsSettlement = totalConsSettlement;
        this.totalLandPrice = totalLandPrice;
        this.version = version;
        this.conOrgSid=conOrgSid;
    }

    public ArolProjBaseInfoEntity() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId == null ? null : projId.trim();
    }

    public String getOrgNoCode() {
        return orgNoCode;
    }

    public void setOrgNoCode(String orgNoCode) {
        this.orgNoCode = orgNoCode == null ? null : orgNoCode.trim();
    }

    public Integer getConOrgSid() {
        return conOrgSid;
    }

    public void setConOrgSid(Integer conOrgSid) {
        this.conOrgSid = conOrgSid;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName == null ? null : projName.trim();
    }

    public String getProjLocation() {
        return projLocation;
    }

    public void setProjLocation(String projLocation) {
        this.projLocation = projLocation == null ? null : projLocation.trim();
    }

    public BigDecimal getTotalLandArea() {
        return totalLandArea;
    }

    public void setTotalLandArea(BigDecimal totalLandArea) {
        this.totalLandArea = totalLandArea;
    }

    public BigDecimal getTotalBuildingArea() {
        return totalBuildingArea;
    }

    public void setTotalBuildingArea(BigDecimal totalBuildingArea) {
        this.totalBuildingArea = totalBuildingArea;
    }

    public Integer getBuildingNums() {
        return buildingNums;
    }

    public void setBuildingNums(Integer buildingNums) {
        this.buildingNums = buildingNums;
    }

    public BigDecimal getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(BigDecimal plotRatio) {
        this.plotRatio = plotRatio;
    }

    public BigDecimal getGreenCoverage() {
        return greenCoverage;
    }

    public void setGreenCoverage(BigDecimal greenCoverage) {
        this.greenCoverage = greenCoverage;
    }

    public BigDecimal getBuildingDensity() {
        return buildingDensity;
    }

    public void setBuildingDensity(BigDecimal buildingDensity) {
        this.buildingDensity = buildingDensity;
    }

    public Integer getUpParkingNums() {
        return upParkingNums;
    }

    public void setUpParkingNums(Integer upParkingNums) {
        this.upParkingNums = upParkingNums;
    }

    public Integer getDownParkingNums() {
        return downParkingNums;
    }

    public void setDownParkingNums(Integer downParkingNums) {
        this.downParkingNums = downParkingNums;
    }

    public BigDecimal getTotalConsBudget() {
        return totalConsBudget;
    }

    public void setTotalConsBudget(BigDecimal totalConsBudget) {
        this.totalConsBudget = totalConsBudget;
    }

    public BigDecimal getTotalConsSettlement() {
        return totalConsSettlement;
    }

    public void setTotalConsSettlement(BigDecimal totalConsSettlement) {
        this.totalConsSettlement = totalConsSettlement;
    }

    public BigDecimal getTotalLandPrice() {
        return totalLandPrice;
    }

    public void setTotalLandPrice(BigDecimal totalLandPrice) {
        this.totalLandPrice = totalLandPrice;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}