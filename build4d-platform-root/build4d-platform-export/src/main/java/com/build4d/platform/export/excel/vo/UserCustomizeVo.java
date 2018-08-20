package com.build4d.platform.export.excel.vo;

import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/5
 * @Description:
 * @Version 1.0.0
 */
public class UserCustomizeVo {
    private boolean enable=false;
    List<CellVo> cellVoList;
    //Map<String,List<CellVo>> groupCellListMap;
    List<List<CellVo>> groupCellList;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<CellVo> getCellVoList() {
        return cellVoList;
    }

    public void setCellVoList(List<CellVo> cellVoList) {
        this.cellVoList = cellVoList;
    }

    /*public Map<String, List<CellVo>> getGroupCellListMap() {
        return groupCellListMap;
    }

    public void setGroupCellListMap(Map<String, List<CellVo>> groupCellListMap) {
        this.groupCellListMap = groupCellListMap;
    }*/

    public List<List<CellVo>> getGroupCellList() {
        return groupCellList;
    }

    public void setGroupCellList(List<List<CellVo>> groupCellList) {
        this.groupCellList = groupCellList;
    }
}
