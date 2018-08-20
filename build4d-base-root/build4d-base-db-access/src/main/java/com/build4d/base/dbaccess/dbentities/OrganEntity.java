package com.build4d.base.dbaccess.dbentities;

public class OrganEntity {
    private String organId;

    private Integer childCount;

    private Integer isVirtual;

    private Integer orderNum;

    private String organAddress;

    private String organContactor;

    private String organDomain;

    private String organFax;

    private String organName;

    private String organNum;

    private String organPhone;

    private String organPost;

    private String organType;

    private String parentId;

    private String parentIdList;

    private String shortName;

    private Integer status;

    public OrganEntity(String organId, Integer childCount, Integer isVirtual, Integer orderNum, String organAddress, String organContactor, String organDomain, String organFax, String organName, String organNum, String organPhone, String organPost, String organType, String parentId, String parentIdList, String shortName, Integer status) {
        this.organId = organId;
        this.childCount = childCount;
        this.isVirtual = isVirtual;
        this.orderNum = orderNum;
        this.organAddress = organAddress;
        this.organContactor = organContactor;
        this.organDomain = organDomain;
        this.organFax = organFax;
        this.organName = organName;
        this.organNum = organNum;
        this.organPhone = organPhone;
        this.organPost = organPost;
        this.organType = organType;
        this.parentId = parentId;
        this.parentIdList = parentIdList;
        this.shortName = shortName;
        this.status = status;
    }

    public OrganEntity() {
        super();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrganAddress() {
        return organAddress;
    }

    public void setOrganAddress(String organAddress) {
        this.organAddress = organAddress == null ? null : organAddress.trim();
    }

    public String getOrganContactor() {
        return organContactor;
    }

    public void setOrganContactor(String organContactor) {
        this.organContactor = organContactor == null ? null : organContactor.trim();
    }

    public String getOrganDomain() {
        return organDomain;
    }

    public void setOrganDomain(String organDomain) {
        this.organDomain = organDomain == null ? null : organDomain.trim();
    }

    public String getOrganFax() {
        return organFax;
    }

    public void setOrganFax(String organFax) {
        this.organFax = organFax == null ? null : organFax.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getOrganNum() {
        return organNum;
    }

    public void setOrganNum(String organNum) {
        this.organNum = organNum == null ? null : organNum.trim();
    }

    public String getOrganPhone() {
        return organPhone;
    }

    public void setOrganPhone(String organPhone) {
        this.organPhone = organPhone == null ? null : organPhone.trim();
    }

    public String getOrganPost() {
        return organPost;
    }

    public void setOrganPost(String organPost) {
        this.organPost = organPost == null ? null : organPost.trim();
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType == null ? null : organType.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIdList() {
        return parentIdList;
    }

    public void setParentIdList(String parentIdList) {
        this.parentIdList = parentIdList == null ? null : parentIdList.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}