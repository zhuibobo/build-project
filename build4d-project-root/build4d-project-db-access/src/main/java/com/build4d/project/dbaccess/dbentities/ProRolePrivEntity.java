package com.build4d.project.dbaccess.dbentities;

public class ProRolePrivEntity {
    private Integer roprSid;

    private Integer roleSid;

    private String roprObjType;

    private String roprObjId;

    private String roprOperationId;

    public ProRolePrivEntity(Integer roprSid, Integer roleSid, String roprObjType, String roprObjId, String roprOperationId) {
        this.roprSid = roprSid;
        this.roleSid = roleSid;
        this.roprObjType = roprObjType;
        this.roprObjId = roprObjId;
        this.roprOperationId = roprOperationId;
    }

    public ProRolePrivEntity() {
        super();
    }

    public Integer getRoprSid() {
        return roprSid;
    }

    public void setRoprSid(Integer roprSid) {
        this.roprSid = roprSid;
    }

    public Integer getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(Integer roleSid) {
        this.roleSid = roleSid;
    }

    public String getRoprObjType() {
        return roprObjType;
    }

    public void setRoprObjType(String roprObjType) {
        this.roprObjType = roprObjType == null ? null : roprObjType.trim();
    }

    public String getRoprObjId() {
        return roprObjId;
    }

    public void setRoprObjId(String roprObjId) {
        this.roprObjId = roprObjId == null ? null : roprObjId.trim();
    }

    public String getRoprOperationId() {
        return roprOperationId;
    }

    public void setRoprOperationId(String roprOperationId) {
        this.roprOperationId = roprOperationId == null ? null : roprOperationId.trim();
    }
}