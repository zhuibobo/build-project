package com.build4d.platform.convert.pdf.Impl;

import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.platform.convert.pdf.IWordConvertToPDFComponent;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

public class MSOfficeConvertWordToPDF implements IWordConvertToPDFComponent {
    static final int wdFormatPDF = 17;// PDF 格式
    public String convertToPDF(String sourceFilePath){
        System.out.println("启动Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        String toFileName = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();

            //String path =  this.getSession().getServletContext().getRealPath("/")+"attachment/";
            String sfileName = sourceFilePath;
            toFileName = sourceFilePath.substring(0,sourceFilePath.lastIndexOf("\\")+1)+ UUIDUtility.getFullTimeStr()+".pdf";

            doc = Dispatch.call(docs,  "Open" , sfileName).toDispatch();
            System.out.println("打开文档..." + sfileName);
            System.out.println("转换文档到PDF..." + toFileName);
            File tofile = new File(toFileName);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc,
                    "SaveAs",
                    toFileName, // FileName
                    wdFormatPDF);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            Dispatch.call(doc,"Close",false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        //如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();
        return toFileName;
    }
}
