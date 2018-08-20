package com.build4d.platform.export.excel.formater;

import com.build4d.platform.export.excel.IValueFormater;
import com.build4d.platform.export.excel.constants.ColorsEnum;
import com.build4d.platform.export.excel.vo.BindDataCellVo;
import com.build4d.platform.export.excel.vo.ExportExcelVo;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/10
 * @Description:
 * @Version 1.0.0
 */
public class ConsolidationDateDemo implements IValueFormater {

    public String Formater(ExportExcelVo exportExcelVo, List<BindDataCellVo> bindDataCellVoList, BindDataCellVo bindDataCellVo,
                           Map<String, Object> singleRecord, Map<String, Object> complexSingleRecord,
                           XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow row, XSSFCell cell)
    {
        if(singleRecord.get("F_SELF_NAME").toString().equals("成龙99")||singleRecord.get("F_SELF_NAME").toString().equals("成龙90")){
            XSSFCellStyle cellStyle=workbook.createCellStyle();
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(ColorsEnum.BLUE.getIndex());
            //todo
            //cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cell.setCellStyle(cellStyle);
        }
        if(singleRecord.get("F_SELF_NAME").toString().equals("成龙80")){
            XSSFCellStyle cellStyle=workbook.createCellStyle();
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(ColorsEnum.CORAL.getIndex());
            for(int i=0;i<20;i++){
                XSSFCell cell1=row.getCell(i);
                if(cell1!=null) {
                    cell1.setCellStyle(cellStyle);
                }
            }
        }
        return singleRecord.get("F_SELF_IDCARD_SD").toString().split(" ")[0]+"到"+singleRecord.get("F_SELF_IDCARD_ED").toString().split(" ")[0];
    }
}
