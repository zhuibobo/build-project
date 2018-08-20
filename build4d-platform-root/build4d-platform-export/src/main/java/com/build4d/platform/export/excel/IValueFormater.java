package com.build4d.platform.export.excel;

import com.build4d.platform.export.excel.vo.BindDataCellVo;
import com.build4d.platform.export.excel.vo.ExportExcelVo;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public interface IValueFormater {
    String Formater(ExportExcelVo exportExcelVo, List<BindDataCellVo> bindDataCellVoList, BindDataCellVo bindDataCellVo, Map<String, Object> singleRecord, Map<String, Object> complexSingleRecord, XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow row, XSSFCell cell);
}
