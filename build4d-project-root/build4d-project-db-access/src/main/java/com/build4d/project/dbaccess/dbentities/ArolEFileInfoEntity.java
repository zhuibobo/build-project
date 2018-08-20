package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ArolEFileInfoEntity {
    private Integer sid;

    private Integer engSid;

    private Integer archSid;

    private Integer fileSid;

    private String filename;

    private String filepath;

    private String orderRank;

    private String filetype;

    private String filelen;

    private String filedescription;

    private String createdBy;

    private Date createdDt;

    private Integer version;

    private String updatedBy;

    private Date updatedDt;

    public ArolEFileInfoEntity(Integer sid, Integer engSid, Integer archSid, Integer fileSid, String filename, String filepath, String orderRank, String filetype, String filelen, String filedescription, String createdBy, Date createdDt, Integer version, String updatedBy, Date updatedDt) {
        this.sid = sid;
        this.engSid = engSid;
        this.archSid = archSid;
        this.fileSid = fileSid;
        this.filename = filename;
        this.filepath = filepath;
        this.orderRank = orderRank;
        this.filetype = filetype;
        this.filelen = filelen;
        this.filedescription = filedescription;
        this.createdBy = createdBy;
        this.createdDt = createdDt;
        this.version = version;
        this.updatedBy = updatedBy;
        this.updatedDt = updatedDt;
    }

    public ArolEFileInfoEntity() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getEngSid() {
        return engSid;
    }

    public void setEngSid(Integer engSid) {
        this.engSid = engSid;
    }

    public Integer getArchSid() {
        return archSid;
    }

    public void setArchSid(Integer archSid) {
        this.archSid = archSid;
    }

    public Integer getFileSid() {
        return fileSid;
    }

    public void setFileSid(Integer fileSid) {
        this.fileSid = fileSid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getOrderRank() {
        return orderRank;
    }

    public void setOrderRank(String orderRank) {
        this.orderRank = orderRank == null ? null : orderRank.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getFilelen() {
        return filelen;
    }

    public void setFilelen(String filelen) {
        this.filelen = filelen == null ? null : filelen.trim();
    }

    public String getFiledescription() {
        return filedescription;
    }

    public void setFiledescription(String filedescription) {
        this.filedescription = filedescription == null ? null : filedescription.trim();
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