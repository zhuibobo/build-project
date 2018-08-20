package com.build4d.web.model;

/**
 * @Author: zhuangrb
 * @Date: 2018/5/11
 * @Description:
 * @Version 1.0.0
 */
public class IViewTransferItemModel {
    String key;
    String label;
    String description;
    boolean disabled;

    public IViewTransferItemModel(String key, String label, String description, boolean disabled) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.disabled = disabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
