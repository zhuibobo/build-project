package com.build4d.platform.export.pdf.formater;



import com.build4d.platform.export.pdf.IValueFormater;
import com.build4d.platform.export.pdf.vo.ExportPDFVo;

/**
 * Created by bobo-sss on 2017/9/7.
 */
public class DemoFormater implements IValueFormater {
    public String Formater(ExportPDFVo exportPDFVo) {
        return "我叫API...";
    }
}
