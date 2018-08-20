package com.build4d.platform.export.pdf.vo;

import com.build4d.platform.export.general.vo.ExportVo;
import com.build4d.platform.export.pdf.IValueFormater;

import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportPDFVo extends ExportVo {

    private Map<String,Map<String,String>> styles =null;

    private Map<String,IValueFormater> valueFormater=null;

    public Map<String, Map<String, String>> getStyles() {
        return styles;
    }

    public void setStyles(Map<String, Map<String, String>> styles) {
        this.styles = styles;
    }

    public Map<String, IValueFormater> getValueFormater() {
        return valueFormater;
    }

    public void setValueFormater(Map<String, IValueFormater> valueFormater) {
        this.valueFormater = valueFormater;
    }
}
