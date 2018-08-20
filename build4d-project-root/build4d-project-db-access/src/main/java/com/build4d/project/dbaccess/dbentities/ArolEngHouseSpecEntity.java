package com.build4d.project.dbaccess.dbentities;

import java.math.BigDecimal;
import java.util.Date;

public class ArolEngHouseSpecEntity {
    private Integer engSid;

    private BigDecimal height;

    private Integer underNums;

    private Integer overNums;

    private BigDecimal floorArea;

    private BigDecimal underArea;

    private BigDecimal finishArea;

    private String consTypeCode;

    private Integer buidlingNums;

    private String basisTypeCode;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    public ArolEngHouseSpecEntity(Integer engSid, BigDecimal height, Integer underNums, Integer overNums, BigDecimal floorArea, BigDecimal underArea, BigDecimal finishArea, String consTypeCode, Integer buidlingNums, String basisTypeCode, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt) {
        this.engSid = engSid;
        this.height = height;
        this.underNums = underNums;
        this.overNums = overNums;
        this.floorArea = floorArea;
        this.underArea = underArea;
        this.finishArea = finishArea;
        this.consTypeCode = consTypeCode;
        this.buidlingNums = buidlingNums;
        this.basisTypeCode = basisTypeCode;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
    }

    public ArolEngHouseSpecEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getUnderNums() {
        return underNums;
    }

    public void setUnderNums(Integer underNums) {
        this.underNums = underNums;
    }

    public Integer getOverNums() {
        return overNums;
    }

    public void setOverNums(Integer overNums) {
        this.overNums = overNums;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getUnderArea() {
        return underArea;
    }

    public void setUnderArea(BigDecimal underArea) {
        this.underArea = underArea;
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

    public Integer getBuidlingNums() {
        return buidlingNums;
    }

    public void setBuidlingNums(Integer buidlingNums) {
        this.buidlingNums = buidlingNums;
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
}