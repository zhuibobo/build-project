package com.build4d.base.dbaccess.dbentities;

import com.build4d.base.dbaccess.exenum.EnableTypeEnum;

import java.util.Date;

public class DemoEntity {
    private String demoId;

    private Integer isVirtual;

    private Integer orderNum;

    private EnableTypeEnum status;

    private String nameText;

    private Date fDate;

    public DemoEntity(String demoId, Integer isVirtual, Integer orderNum, EnableTypeEnum status, String nameText, Date fDate) {
        this.demoId = demoId;
        this.isVirtual = isVirtual;
        this.orderNum = orderNum;
        this.status = status;
        this.nameText = nameText;
        this.fDate = fDate;
    }

    public DemoEntity() {
        super();
    }

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId == null ? null : demoId.trim();
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

    public EnableTypeEnum getStatus() {
        return status;
    }

    public void setStatus(EnableTypeEnum status) {
        this.status = status;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText == null ? null : nameText.trim();
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }
}