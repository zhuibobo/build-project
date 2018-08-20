package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ArolEngOrgAndCodeEntity {
    private Integer engSid;

    private Integer landUseOrgSid;

    private String landUseOrgName;

    private Integer initiationApprovalOrgSid;

    private String initiationApprovalOrgName;

    private Integer designOrgSid;

    private String designOrgName;

    private Integer reconnaissanceOrgSid;

    private String reconnaissanceOrgName;

    private Integer supervisionOrgSid;

    private String supervisionOrgName;

    private Integer constructOrgSid;

    private String constructOrgName;

    private Integer planningApprovalOrgSid;

    private String planningApprovalOrgName;

    private Integer securityOrgSid;

    private String securityOrgName;

    private Integer initiationApprovalNo;

    private String landUsePlanningNo;

    private String landUseNo;

    private String constructNo;

    private String designNo;

    private String landNo;

    private String terrainNo;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    private String instructor;

    private String pmDirector;

    private String fieldManager;

    private String subUnit;

    private String projectManager;

    private String supervionChecker;

    private String consUnitChecker;

    private String finishBah;

    private String otherCert;

    private Integer instructorSid;

    public ArolEngOrgAndCodeEntity(Integer engSid, Integer landUseOrgSid, String landUseOrgName, Integer initiationApprovalOrgSid, String initiationApprovalOrgName, Integer designOrgSid, String designOrgName, Integer reconnaissanceOrgSid, String reconnaissanceOrgName, Integer supervisionOrgSid, String supervisionOrgName, Integer constructOrgSid, String constructOrgName, Integer planningApprovalOrgSid, String planningApprovalOrgName, Integer securityOrgSid, String securityOrgName, Integer initiationApprovalNo, String landUsePlanningNo, String landUseNo, String constructNo, String designNo, String landNo, String terrainNo, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt, String instructor, String pmDirector, String fieldManager, String subUnit, String projectManager, String supervionChecker, String consUnitChecker, String finishBah, String otherCert, Integer instructorSid) {
        this.engSid = engSid;
        this.landUseOrgSid = landUseOrgSid;
        this.landUseOrgName = landUseOrgName;
        this.initiationApprovalOrgSid = initiationApprovalOrgSid;
        this.initiationApprovalOrgName = initiationApprovalOrgName;
        this.designOrgSid = designOrgSid;
        this.designOrgName = designOrgName;
        this.reconnaissanceOrgSid = reconnaissanceOrgSid;
        this.reconnaissanceOrgName = reconnaissanceOrgName;
        this.supervisionOrgSid = supervisionOrgSid;
        this.supervisionOrgName = supervisionOrgName;
        this.constructOrgSid = constructOrgSid;
        this.constructOrgName = constructOrgName;
        this.planningApprovalOrgSid = planningApprovalOrgSid;
        this.planningApprovalOrgName = planningApprovalOrgName;
        this.securityOrgSid = securityOrgSid;
        this.securityOrgName = securityOrgName;
        this.initiationApprovalNo = initiationApprovalNo;
        this.landUsePlanningNo = landUsePlanningNo;
        this.landUseNo = landUseNo;
        this.constructNo = constructNo;
        this.designNo = designNo;
        this.landNo = landNo;
        this.terrainNo = terrainNo;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
        this.instructor = instructor;
        this.pmDirector = pmDirector;
        this.fieldManager = fieldManager;
        this.subUnit = subUnit;
        this.projectManager = projectManager;
        this.supervionChecker = supervionChecker;
        this.consUnitChecker = consUnitChecker;
        this.finishBah = finishBah;
        this.otherCert = otherCert;
        this.instructorSid=instructorSid;
    }

    public ArolEngOrgAndCodeEntity() {
        super();
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public Integer getLandUseOrgSid() {
        return landUseOrgSid;
    }

    public void setLandUseOrgSid(Integer landUseOrgSid) {
        this.landUseOrgSid = landUseOrgSid;
    }

    public String getLandUseOrgName() {
        return landUseOrgName;
    }

    public void setLandUseOrgName(String landUseOrgName) {
        this.landUseOrgName = landUseOrgName == null ? null : landUseOrgName.trim();
    }

    public Integer getInitiationApprovalOrgSid() {
        return initiationApprovalOrgSid;
    }

    public void setInitiationApprovalOrgSid(Integer initiationApprovalOrgSid) {
        this.initiationApprovalOrgSid = initiationApprovalOrgSid;
    }

    public String getInitiationApprovalOrgName() {
        return initiationApprovalOrgName;
    }

    public void setInitiationApprovalOrgName(String initiationApprovalOrgName) {
        this.initiationApprovalOrgName = initiationApprovalOrgName == null ? null : initiationApprovalOrgName.trim();
    }

    public Integer getDesignOrgSid() {
        return designOrgSid;
    }

    public void setDesignOrgSid(Integer designOrgSid) {
        this.designOrgSid = designOrgSid;
    }

    public String getDesignOrgName() {
        return designOrgName;
    }

    public void setDesignOrgName(String designOrgName) {
        this.designOrgName = designOrgName == null ? null : designOrgName.trim();
    }

    public Integer getReconnaissanceOrgSid() {
        return reconnaissanceOrgSid;
    }

    public void setReconnaissanceOrgSid(Integer reconnaissanceOrgSid) {
        this.reconnaissanceOrgSid = reconnaissanceOrgSid;
    }

    public String getReconnaissanceOrgName() {
        return reconnaissanceOrgName;
    }

    public void setReconnaissanceOrgName(String reconnaissanceOrgName) {
        this.reconnaissanceOrgName = reconnaissanceOrgName == null ? null : reconnaissanceOrgName.trim();
    }

    public Integer getSupervisionOrgSid() {
        return supervisionOrgSid;
    }

    public void setSupervisionOrgSid(Integer supervisionOrgSid) {
        this.supervisionOrgSid = supervisionOrgSid;
    }

    public String getSupervisionOrgName() {
        return supervisionOrgName;
    }

    public void setSupervisionOrgName(String supervisionOrgName) {
        this.supervisionOrgName = supervisionOrgName == null ? null : supervisionOrgName.trim();
    }

    public Integer getConstructOrgSid() {
        return constructOrgSid;
    }

    public void setConstructOrgSid(Integer constructOrgSid) {
        this.constructOrgSid = constructOrgSid;
    }

    public String getConstructOrgName() {
        return constructOrgName;
    }

    public void setConstructOrgName(String constructOrgName) {
        this.constructOrgName = constructOrgName == null ? null : constructOrgName.trim();
    }

    public Integer getPlanningApprovalOrgSid() {
        return planningApprovalOrgSid;
    }

    public void setPlanningApprovalOrgSid(Integer planningApprovalOrgSid) {
        this.planningApprovalOrgSid = planningApprovalOrgSid;
    }

    public String getPlanningApprovalOrgName() {
        return planningApprovalOrgName;
    }

    public void setPlanningApprovalOrgName(String planningApprovalOrgName) {
        this.planningApprovalOrgName = planningApprovalOrgName == null ? null : planningApprovalOrgName.trim();
    }

    public Integer getSecurityOrgSid() {
        return securityOrgSid;
    }

    public void setSecurityOrgSid(Integer securityOrgSid) {
        this.securityOrgSid = securityOrgSid;
    }

    public String getSecurityOrgName() {
        return securityOrgName;
    }

    public void setSecurityOrgName(String securityOrgName) {
        this.securityOrgName = securityOrgName == null ? null : securityOrgName.trim();
    }

    public Integer getInitiationApprovalNo() {
        return initiationApprovalNo;
    }

    public void setInitiationApprovalNo(Integer initiationApprovalNo) {
        this.initiationApprovalNo = initiationApprovalNo;
    }

    public String getLandUsePlanningNo() {
        return landUsePlanningNo;
    }

    public void setLandUsePlanningNo(String landUsePlanningNo) {
        this.landUsePlanningNo = landUsePlanningNo == null ? null : landUsePlanningNo.trim();
    }

    public String getLandUseNo() {
        return landUseNo;
    }

    public void setLandUseNo(String landUseNo) {
        this.landUseNo = landUseNo == null ? null : landUseNo.trim();
    }

    public String getConstructNo() {
        return constructNo;
    }

    public void setConstructNo(String constructNo) {
        this.constructNo = constructNo == null ? null : constructNo.trim();
    }

    public String getDesignNo() {
        return designNo;
    }

    public void setDesignNo(String designNo) {
        this.designNo = designNo == null ? null : designNo.trim();
    }

    public String getLandNo() {
        return landNo;
    }

    public void setLandNo(String landNo) {
        this.landNo = landNo == null ? null : landNo.trim();
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor == null ? null : instructor.trim();
    }

    public String getPmDirector() {
        return pmDirector;
    }

    public void setPmDirector(String pmDirector) {
        this.pmDirector = pmDirector == null ? null : pmDirector.trim();
    }

    public String getFieldManager() {
        return fieldManager;
    }

    public void setFieldManager(String fieldManager) {
        this.fieldManager = fieldManager == null ? null : fieldManager.trim();
    }

    public String getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit == null ? null : subUnit.trim();
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager == null ? null : projectManager.trim();
    }

    public String getSupervionChecker() {
        return supervionChecker;
    }

    public void setSupervionChecker(String supervionChecker) {
        this.supervionChecker = supervionChecker == null ? null : supervionChecker.trim();
    }

    public String getConsUnitChecker() {
        return consUnitChecker;
    }

    public void setConsUnitChecker(String consUnitChecker) {
        this.consUnitChecker = consUnitChecker == null ? null : consUnitChecker.trim();
    }

    public String getFinishBah() {
        return finishBah;
    }

    public void setFinishBah(String finishBah) {
        this.finishBah = finishBah == null ? null : finishBah.trim();
    }

    public String getOtherCert() {
        return otherCert;
    }

    public void setOtherCert(String otherCert) {
        this.otherCert = otherCert == null ? null : otherCert.trim();
    }

    public Integer getInstructorSid() {
        return instructorSid;
    }

    public void setInstructorSid(Integer instructorSid) {
        this.instructorSid = instructorSid;
    }
}