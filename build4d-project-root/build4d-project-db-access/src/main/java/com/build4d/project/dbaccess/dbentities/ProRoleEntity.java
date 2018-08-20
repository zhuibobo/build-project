package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProRoleEntity {
    private Integer roleSid;

    private String roleName;

    private String roleUseOrgId;

    private Date roleCreateTime;

    private Integer roleOrder;

    private Integer roleStatus;

    private String roleType;

    private String roleDesc;

    public ProRoleEntity(Integer roleSid, String roleName, String roleUseOrgId, Date roleCreateTime, Integer roleOrder, Integer roleStatus, String roleType, String roleDesc) {
        this.roleSid = roleSid;
        this.roleName = roleName;
        this.roleUseOrgId = roleUseOrgId;
        this.roleCreateTime = roleCreateTime;
        this.roleOrder = roleOrder;
        this.roleStatus = roleStatus;
        this.roleType = roleType;
        this.roleDesc = roleDesc;
    }

    public ProRoleEntity() {
        super();
    }

    public Integer getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(Integer roleSid) {
        this.roleSid = roleSid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleUseOrgId() {
        return roleUseOrgId;
    }

    public void setRoleUseOrgId(String roleUseOrgId) {
        this.roleUseOrgId = roleUseOrgId == null ? null : roleUseOrgId.trim();
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Integer getRoleOrder() {
        return roleOrder;
    }

    public void setRoleOrder(Integer roleOrder) {
        this.roleOrder = roleOrder;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }
}