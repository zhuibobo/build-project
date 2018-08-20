package com.build4d.platform.export.general.vo;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/11
 * @Description:
 * @Version 1.0.0
 */
public class ExportResult {
    private byte[] byteData;
    private String exportFileName;

    public byte[] getByteData() {
        return byteData;
    }

    public void setByteData(byte[] byteData) {
        this.byteData = byteData;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }
}
