package com.build4d.project.dbaccess.dbentities;

public class ProUserEntity {
    private Integer userSid;

    private String userOrgId;

    private String userType;

    private String userAccount;

    private String userPsw;

    private String userUsername;

    private String userPhone;

    private String userMobile;

    private String userFax;

    private String userWxno;

    private String userWxbinding;

    private Integer userOrder;

    private Integer userStatus;

    private String userDesc;

    private ProOrganEntity proOrgan;

    private String creatreOrgId;

    public ProUserEntity(Integer userSid, String userOrgId, String userType, String userAccount, String userPsw, String userUsername, String userPhone, String userMobile, String userFax, String userWxno, String userWxbinding, Integer userOrder, Integer userStatus, String userDesc, String creatreOrgId) {
        this.userSid = userSid;
        this.userOrgId = userOrgId;
        this.userType = userType;
        this.userAccount = userAccount;
        this.userPsw = userPsw;
        this.userUsername = userUsername;
        this.userPhone = userPhone;
        this.userMobile = userMobile;
        this.userFax = userFax;
        this.userWxno = userWxno;
        this.userWxbinding = userWxbinding;
        this.userOrder = userOrder;
        this.userStatus = userStatus;
        this.userDesc = userDesc;
        this.creatreOrgId = creatreOrgId;
    }

    public ProUserEntity(Integer userSid, String userOrgId, String userType,String userUsername, String userWxno) {
        this.userSid = userSid;
        this.userOrgId = userOrgId;
        this.userType = userType;
        this.userUsername = userUsername;
        this.userWxno = userWxno;
    }
    public ProUserEntity() {
        super();
    }

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public String getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(String userOrgId) {
        this.userOrgId = userOrgId == null ? null : userOrgId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw == null ? null : userPsw.trim();
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserFax() {
        return userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax == null ? null : userFax.trim();
    }

    public String getUserWxno() {
        return userWxno;
    }

    public void setUserWxno(String userWxno) {
        this.userWxno = userWxno == null ? null : userWxno.trim();
    }

    public String getUserWxbinding() {
        return userWxbinding;
    }

    public void setUserWxbinding(String userWxbinding) {
        this.userWxbinding = userWxbinding == null ? null : userWxbinding.trim();
    }

    public Integer getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Integer userOrder) {
        this.userOrder = userOrder;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    public ProOrganEntity getProOrgan() {
        return proOrgan;
    }

    public void setProOrgan(ProOrganEntity proOrgan) {
        this.proOrgan = proOrgan;
    }

    public String getCreatreOrgId() {
        return creatreOrgId;
    }

    public void setCreatreOrgId(String creatreOrgId) {
        this.creatreOrgId = creatreOrgId == null ? null : creatreOrgId.trim();
    }
}