package com.build4d.platform.export.general.vo;

import org.w3c.dom.Node;

import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportVo {
    protected String id="";
    protected String name="";
    protected String exportFileName="";
    private MainDataSourceVo mainDataSourceVo;
    private Map<String,Map<String,String>> vtConvertDataSource =null;
    private Node exportNode=null;
    private Map<String,SubDataSourceVo> subDataSourceVoMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public MainDataSourceVo getMainDataSourceVo() {
        return mainDataSourceVo;
    }

    public void setMainDataSourceVo(MainDataSourceVo mainDataSourceVo) {
        this.mainDataSourceVo = mainDataSourceVo;
    }

    public Map<String,Map<String,String>> getVtConvertDataSource() {
        return vtConvertDataSource;
    }

    public void setVtConvertDataSource(Map<String,Map<String,String>> vtConvertDataSource) {
        this.vtConvertDataSource = vtConvertDataSource;
    }

    public Node getExportNode() {
        return exportNode;
    }

    public void setExportNode(Node exportNode) {
        this.exportNode = exportNode;
    }

    public Map<String, SubDataSourceVo> getSubDataSourceVoMap() {
        return subDataSourceVoMap;
    }

    public void setSubDataSourceVoMap(Map<String, SubDataSourceVo> subDataSourceVoMap) {
        this.subDataSourceVoMap = subDataSourceVoMap;
    }
}
