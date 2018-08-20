package com.build4d.platform.export.excel.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/7
 * @Description:
 * @Version 1.0.0
 */
public class XSSFExcelUtility {
    private XSSFWorkbook workbook=null;

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    private XSSFCellStyle fullBorderStyle=null;
    public XSSFCellStyle getFullBorderStyle(){
        if(fullBorderStyle==null){
            fullBorderStyle=workbook.createCellStyle();
            fullBorderStyle.setBorderTop(BorderStyle.THIN);
            fullBorderStyle.setBorderBottom(BorderStyle.THIN);
            fullBorderStyle.setBorderLeft(BorderStyle.THIN);
            fullBorderStyle.setBorderRight(BorderStyle.THIN);
            fullBorderStyle.setAlignment(HorizontalAlignment.CENTER);
            fullBorderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        return  fullBorderStyle;
    }

    public boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    public Cell getMergedRegionFirstCell(Sheet sheet, int row, int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return sheet.getRow(firstRow).getCell(firstColumn);
                }
            }
        }
        return null;
    }

    public void setAllRegionSetFullBorderStyle(Sheet sheet) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            for (int j = range.getFirstRow(); j <= range.getLastRow(); j++) {
                Row row = sheet.getRow(j);
                for (int k = range.getFirstColumn(); k <= range.getLastColumn(); k++) {
                    Cell cell = row.getCell(k);
                    cell.setCellStyle(getFullBorderStyle());
                }
            }
        }
    }

    public void setCellFullBorder(int start, int end, XSSFRow row) {
        for(int i=start;i<=end;i++){
            Cell cell=row.getCell(i);
            if(cell==null) {
                cell = row.createCell(i);
            }
            cell.setCellStyle(getFullBorderStyle());
        }
    }

    public void setCellStyle(int start, int end, XSSFRow row,XSSFCellStyle cellStyle) {
        for(int i=start;i<=end;i++){
            Cell cell=row.getCell(i);
            if(cell==null) {
                cell = row.createCell(i);
            }
            cell.setCellStyle(cellStyle);
        }
    }
}
