package com.build4d.platform.export.excel.vo;

import com.build4d.platform.export.excel.constants.DataSourceTypeEnum;

import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class CellVo {

    //private String type;
    //用于程序计算使用，作为占位符
    private boolean forMerged=false;

    private int mergerRow=1;
    private int mergerCell=1;
    private int autoMergerCell=1;

    private boolean userCustomize=false;
    private String userCustomizeName="";
    private String userCustomizeCaption="";
    private List<String> userCustomincludeCellName=null;
    private String userCustomGroup="";
    private String userCustomGroupKey="";

    private DataSourceTypeEnum dataSourceType;
    private String bindDataSource="";
    private String bindField="";
    private String defaultValue="";
    private String formaterRef="";
    private String vtConverter="";
    private String defaultConverter="";

    private CellStyleVo title_StyleVo;
    private CellStyleVo data_StyleVo;

    private String caption="";

    public boolean isForMerged() {
        return forMerged;
    }

    public void setForMerged(boolean forMerged) {
        this.forMerged = forMerged;
    }

    public int getMergerRow() {
        return mergerRow;
    }

    public void setMergerRow(int mergerRow) {
        this.mergerRow = mergerRow;
    }

    public int getMergerCell() {
        return mergerCell;
    }

    public void setMergerCell(int mergerCell) {
        this.mergerCell = mergerCell;
    }

    public int getAutoMergerCell() {
        return autoMergerCell;
    }

    public void setAutoMergerCell(int autoMergerCell) {
        this.autoMergerCell = autoMergerCell;
    }

    public boolean isUserCustomize() {
        return userCustomize;
    }

    public void setUserCustomize(boolean userCustomize) {
        this.userCustomize = userCustomize;
    }

    public String getUserCustomizeName() {
        return userCustomizeName;
    }

    public void setUserCustomizeName(String userCustomizeName) {
        this.userCustomizeName = userCustomizeName;
    }

    public List<String> getUserCustomincludeCellName() {
        return userCustomincludeCellName;
    }

    public void setUserCustomincludeCellName(List<String> userCustomincludeCellName) {
        this.userCustomincludeCellName = userCustomincludeCellName;
    }

    public String getUserCustomGroup() {
        return userCustomGroup;
    }

    public void setUserCustomGroup(String userCustomGroup) {
        this.userCustomGroup = userCustomGroup;
    }

    public String getUserCustomGroupKey() {
        return userCustomGroupKey;
    }

    public void setUserCustomGroupKey(String userCustomGroupKey) {
        this.userCustomGroupKey = userCustomGroupKey;
    }

    public String getUserCustomizeCaption() {
        return userCustomizeCaption;
    }

    public void setUserCustomizeCaption(String userCustomizeCaption) {
        this.userCustomizeCaption = userCustomizeCaption;
    }

    public DataSourceTypeEnum getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceTypeEnum dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getBindDataSource() {
        return bindDataSource;
    }

    public void setBindDataSource(String bindDataSource) {
        this.bindDataSource = bindDataSource;
    }

    public String getBindField() {
        return bindField;
    }

    public void setBindField(String bindField) {
        this.bindField = bindField;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFormaterRef() {
        return formaterRef;
    }

    public void setFormaterRef(String formaterRef) {
        this.formaterRef = formaterRef;
    }

    public String getVtConverter() {
        return vtConverter;
    }

    public void setVtConverter(String vtConverter) {
        this.vtConverter = vtConverter;
    }

    public String getDefaultConverter() {
        return defaultConverter;
    }

    public void setDefaultConverter(String defaultConverter) {
        this.defaultConverter = defaultConverter;
    }

    public CellStyleVo getTitle_StyleVo() {
        return title_StyleVo;
    }

    public void setTitle_StyleVo(CellStyleVo title_StyleVo) {
        this.title_StyleVo = title_StyleVo;
    }

    public CellStyleVo getData_StyleVo() {
        return data_StyleVo;
    }

    public void setData_StyleVo(CellStyleVo data_StyleVo) {
        this.data_StyleVo = data_StyleVo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
