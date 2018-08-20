package com.build4d.platform.export.general.vo;

/**
 * Created by bobo-sss on 2017/9/6.
 */
public class CategoryParaVo {
    public String sourceCategoryId;
    public String currentCategoryId;
    public String buttonId;
    public String folderId;

    public CategoryParaVo(String sourceCategoryId, String currentCategoryId, String buttonId, String folderId) {
        this.sourceCategoryId = sourceCategoryId;
        this.currentCategoryId = currentCategoryId;
        this.buttonId = buttonId;
        this.folderId = folderId;
    }

    public String getSourceCategoryId() {
        return sourceCategoryId;
    }

    public void setSourceCategoryId(String sourceCategoryId) {
        this.sourceCategoryId = sourceCategoryId;
    }

    public String getCurrentCategoryId() {
        return currentCategoryId;
    }

    public void setCurrentCategoryId(String currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
}
