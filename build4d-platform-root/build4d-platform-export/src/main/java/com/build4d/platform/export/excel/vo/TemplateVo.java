package com.build4d.platform.export.excel.vo;

import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class TemplateVo {
    private String exportCaption;
    private List<RowVo> rowVoList=null;
    private List<BindDataCellVo> bindDataCellVoList=null;
    private CellStyleVo captionStyle;
    private boolean autoSizeColumn=false;

    private List<String> userCustomizeGroupKeyOrderList;

    public boolean isAutoSizeColumn() {
        return autoSizeColumn;
    }

    public void setAutoSizeColumn(boolean autoSizeColumn) {
        this.autoSizeColumn = autoSizeColumn;
    }

    public String getExportCaption() {
        return exportCaption;
    }

    public void setExportCaption(String exportCaption) {
        this.exportCaption = exportCaption;
    }

    public List<RowVo> getRowVoList() {
        return rowVoList;
    }

    public void setRowVoList(List<RowVo> rowVoList) {
        this.rowVoList = rowVoList;
    }

    public List<BindDataCellVo> getBindDataCellVoList() {
        return bindDataCellVoList;
    }

    public void setBindDataCellVoList(List<BindDataCellVo> bindDataCellVoList) {
        this.bindDataCellVoList = bindDataCellVoList;
    }

    public CellStyleVo getCaptionStyle() {
        return captionStyle;
    }

    public void setCaptionStyle(CellStyleVo captionStyle) {
        this.captionStyle = captionStyle;
    }

    public List<String> getUserCustomizeGroupKeyOrderList() {
        return userCustomizeGroupKeyOrderList;
    }

    public void setUserCustomizeGroupKeyOrderList(List<String> userCustomizeGroupKeyOrderList) {
        this.userCustomizeGroupKeyOrderList = userCustomizeGroupKeyOrderList;
    }
}
