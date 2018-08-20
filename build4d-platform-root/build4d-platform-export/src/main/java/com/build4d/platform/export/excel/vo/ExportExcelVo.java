package com.build4d.platform.export.excel.vo;


import com.build4d.platform.export.excel.IValueFormater;
import com.build4d.platform.export.general.vo.ExportVo;

import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class ExportExcelVo extends ExportVo {
    public static final String Run_OnlyTitle= "OnlyTitle";
    public static final String Run_InCludeData= "InCludeData";

    private boolean userCustomize=false;

    private TemplateVo templateVo;

    private String run="";
    private Map<String,IValueFormater> valueFormater=null;
    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public boolean isUserCustomize() {
        return userCustomize;
    }

    public void setUserCustomize(boolean userCustomize) {
        this.userCustomize = userCustomize;
    }

    public TemplateVo getTemplateVo() {
        return templateVo;
    }

    public void setTemplateVo(TemplateVo templateVo) {
        this.templateVo = templateVo;
    }

    public Map<String, IValueFormater> getValueFormater() {
        return valueFormater;
    }

    public void setValueFormater(Map<String, IValueFormater> valueFormater) {
        this.valueFormater = valueFormater;
    }
}
