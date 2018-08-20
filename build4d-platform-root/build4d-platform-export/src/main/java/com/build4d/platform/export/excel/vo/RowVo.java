package com.build4d.platform.export.excel.vo;

import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class RowVo {
    private int rowHeigth =0;
    private List<CellVo> cellVoList=null;

    public int getRowHeigth() {
        return rowHeigth;
    }

    public void setRowHeigth(int rowHeigth) {
        this.rowHeigth = rowHeigth;
    }

    public List<CellVo> getCellVoList() {
        return cellVoList;
    }

    public void setCellVoList(List<CellVo> cellVoList) {
        this.cellVoList = cellVoList;
    }
}
