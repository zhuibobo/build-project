package com.build4d.web.general.model;

import java.util.List;

public class NormalMenuVo {
    private String name;
    private String parentName;
    private String text;
    private String iconType;
    private String url;
    private String openType;
    private String path;

    public NormalMenuVo(String name, String parentName, String text, String iconType, String url, String openType, String path) {
        this.name = name;
        this.text = text;
        this.iconType = iconType;
        this.url = url;
        this.openType = openType;
        this.path = path;
        this.parentName=parentName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }
}
