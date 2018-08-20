package com.build4d.project.dbaccess.dbentities;

import java.util.Date;

public class ProDictionaryEntity {
    private Integer dictSid;

    private String dictValue;

    private String dictText;

    private Integer dictOrder;

    private Date dictCreateTime;

    private Integer dictParentId;

    private String dictParentIdlist;

    private Integer dictStatus;

    private Integer dictIsSelected;

    private String dictDesc;

    private Integer dictChildCount;

    private String dictExAttr1;

    private String dictExAttr2;

    private String dictExAttr3;

    private String dictExAttr4;

    public ProDictionaryEntity(Integer dictSid, String dictValue, String dictText, Integer dictOrder, Date dictCreateTime, Integer dictParentId, String dictParentIdlist, Integer dictStatus, Integer dictIsSelected, String dictDesc, Integer dictChildCount, String dictExAttr1, String dictExAttr2, String dictExAttr3, String dictExAttr4) {
        this.dictSid = dictSid;
        this.dictValue = dictValue;
        this.dictText = dictText;
        this.dictOrder = dictOrder;
        this.dictCreateTime = dictCreateTime;
        this.dictParentId = dictParentId;
        this.dictParentIdlist = dictParentIdlist;
        this.dictStatus = dictStatus;
        this.dictIsSelected = dictIsSelected;
        this.dictDesc = dictDesc;
        this.dictChildCount = dictChildCount;
        this.dictExAttr1 = dictExAttr1;
        this.dictExAttr2 = dictExAttr2;
        this.dictExAttr3 = dictExAttr3;
        this.dictExAttr4 = dictExAttr4;
    }

    public ProDictionaryEntity() {
        super();
    }

    public Integer getDictSid() {
        return dictSid;
    }

    public void setDictSid(Integer dictSid) {
        this.dictSid = dictSid;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictText() {
        return dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText == null ? null : dictText.trim();
    }

    public Integer getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(Integer dictOrder) {
        this.dictOrder = dictOrder;
    }

    public Date getDictCreateTime() {
        return dictCreateTime;
    }

    public void setDictCreateTime(Date dictCreateTime) {
        this.dictCreateTime = dictCreateTime;
    }

    public Integer getDictParentId() {
        return dictParentId;
    }

    public void setDictParentId(Integer dictParentId) {
        this.dictParentId = dictParentId;
    }

    public String getDictParentIdlist() {
        return dictParentIdlist;
    }

    public void setDictParentIdlist(String dictParentIdlist) {
        this.dictParentIdlist = dictParentIdlist == null ? null : dictParentIdlist.trim();
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
    }

    public Integer getDictIsSelected() {
        return dictIsSelected;
    }

    public void setDictIsSelected(Integer dictIsSelected) {
        this.dictIsSelected = dictIsSelected;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    public Integer getDictChildCount() {
        return dictChildCount;
    }

    public void setDictChildCount(Integer dictChildCount) {
        this.dictChildCount = dictChildCount;
    }

    public String getDictExAttr1() {
        return dictExAttr1;
    }

    public void setDictExAttr1(String dictExAttr1) {
        this.dictExAttr1 = dictExAttr1 == null ? null : dictExAttr1.trim();
    }

    public String getDictExAttr2() {
        return dictExAttr2;
    }

    public void setDictExAttr2(String dictExAttr2) {
        this.dictExAttr2 = dictExAttr2 == null ? null : dictExAttr2.trim();
    }

    public String getDictExAttr3() {
        return dictExAttr3;
    }

    public void setDictExAttr3(String dictExAttr3) {
        this.dictExAttr3 = dictExAttr3 == null ? null : dictExAttr3.trim();
    }

    public String getDictExAttr4() {
        return dictExAttr4;
    }

    public void setDictExAttr4(String dictExAttr4) {
        this.dictExAttr4 = dictExAttr4 == null ? null : dictExAttr4.trim();
    }
}