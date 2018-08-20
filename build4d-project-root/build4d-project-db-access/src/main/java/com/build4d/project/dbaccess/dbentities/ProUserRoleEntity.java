package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProUserRoleEntity {
    private Integer usroSid;

    private Integer userSid;

    private Integer roleSid;

    private Integer usroOrder;

    private Date usroCreateTime;

    private ProUserEntity proUser;

    public ProUserEntity getProUser() {
        return proUser;
    }

    public void setProUser(ProUserEntity proUser) {
        this.proUser = proUser;
    }

    public ProUserRoleEntity(Integer usroSid, Integer userSid, Integer roleSid, Integer usroOrder, Date usroCreateTime) {
        this.usroSid = usroSid;
        this.userSid = userSid;
        this.roleSid = roleSid;
        this.usroOrder = usroOrder;
        this.usroCreateTime = usroCreateTime;
    }

    public ProUserRoleEntity() {
        super();
    }

    public Integer getUsroSid() {
        return usroSid;
    }

    public void setUsroSid(Integer usroSid) {
        this.usroSid = usroSid;
    }

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public Integer getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(Integer roleSid) {
        this.roleSid = roleSid;
    }

    public Integer getUsroOrder() {
        return usroOrder;
    }

    public void setUsroOrder(Integer usroOrder) {
        this.usroOrder = usroOrder;
    }

    public Date getUsroCreateTime() {
        return usroCreateTime;
    }

    public void setUsroCreateTime(Date usroCreateTime) {
        this.usroCreateTime = usroCreateTime;
    }
}