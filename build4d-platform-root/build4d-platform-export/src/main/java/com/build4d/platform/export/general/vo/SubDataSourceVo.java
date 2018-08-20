package com.build4d.platform.export.general.vo;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public class SubDataSourceVo {
    private List<Map> dataSource = null;
    private boolean enable=false;
    private String key;
    private String name;
    private String fkKey;

    public List<Map> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Map> dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFkKey() {
        return fkKey;
    }

    public void setFkKey(String fkKey) {
        this.fkKey = fkKey;
    }
}
