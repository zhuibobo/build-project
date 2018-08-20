package com.build4d.web.model;

import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2018/5/11
 * @Description:
 * @Version 1.0.0
 */
public class IViewTreeNodeModel {
    public String id;
    public String title;
    public boolean expand;
    public String desc;
    public String innerRender;
    public String parentId;
    public boolean selected;

    List<IViewTreeNodeModel> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<IViewTreeNodeModel> getChildren() {
        return children;
    }

    public void setChildren(List<IViewTreeNodeModel> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getInnerRender() {
        return innerRender;
    }

    public void setInnerRender(String innerRender) {
        this.innerRender = innerRender;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
