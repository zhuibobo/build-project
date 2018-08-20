package com.build4d.platform.export.excel.to;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.platform.export.excel.ExportToExcelManager;
import com.build4d.platform.export.excel.IValueFormater;
import com.build4d.platform.export.excel.constants.CellBorderEnum;
import com.build4d.platform.export.excel.constants.CellHAlignEnum;
import com.build4d.platform.export.excel.constants.CellVAlignEnum;
import com.build4d.platform.export.excel.constants.DataSourceTypeEnum;
import com.build4d.platform.export.excel.utility.XSSFExcelUtility;
import com.build4d.platform.export.excel.vo.*;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import com.build4d.platform.export.general.vo.SubDataSourceVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/6
 * @Description:
 * @Version 1.0.0
 */
public class ExportToExcel {
    private Map<Integer, XSSFCellStyle> _dataCellStyle = null;

    public XSSFCellStyle getCellStyle(CellStyleVo cellStyleVo, XSSFExcelUtility xssfExcelUtil, XSSFWorkbook workbook) {
        if (cellStyleVo == null) {
            return xssfExcelUtil.getFullBorderStyle();
        } else {
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            if (cellStyleVo.getFontSize() > 0) {
                font.setFontHeightInPoints((short) cellStyleVo.getFontSize());
            }
            if (cellStyleVo.getFontColor() != null) {
                font.setColor(cellStyleVo.getFontColor().getIndex());
            }
            if (cellStyleVo.getBorder() != null) {
                if (cellStyleVo.getBorder() == CellBorderEnum.THIN) {
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                } else if (cellStyleVo.getBorder() == CellBorderEnum.NONE) {
                    cellStyle.setBorderTop(BorderStyle.NONE);
                    cellStyle.setBorderBottom(BorderStyle.NONE);
                    cellStyle.setBorderLeft(BorderStyle.NONE);
                    cellStyle.setBorderRight(BorderStyle.NONE);
                } else if (cellStyleVo.getBorder() == CellBorderEnum.DOTTED) {
                    cellStyle.setBorderTop(BorderStyle.DOTTED);
                    cellStyle.setBorderBottom(BorderStyle.DOTTED);
                    cellStyle.setBorderLeft(BorderStyle.DOTTED);
                    cellStyle.setBorderRight(BorderStyle.DOTTED);
                } else if (cellStyleVo.getBorder() == CellBorderEnum.MEDIUM) {
                    cellStyle.setBorderTop(BorderStyle.MEDIUM);
                    cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                    cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyle.setBorderRight(BorderStyle.MEDIUM);
                }
            }

            if (cellStyleVo.getvAlign() != null) {
                if (cellStyleVo.getvAlign() == CellVAlignEnum.CENTER) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                } else if (cellStyleVo.getvAlign() == CellVAlignEnum.TOP) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                } else if (cellStyleVo.getvAlign() == CellVAlignEnum.BOTTOM) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
                }
            }

            if (cellStyleVo.gethAlign() != null) {
                if (cellStyleVo.gethAlign() == CellHAlignEnum.CENTER) {
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                } else if (cellStyleVo.gethAlign() == CellHAlignEnum.LEFT) {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                } else if (cellStyleVo.gethAlign() == CellHAlignEnum.RIGHT) {
                    cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                }
            }

            if (cellStyleVo.getForegroundColor() != null) {
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setFillForegroundColor(cellStyleVo.getForegroundColor().getIndex());
            }

            cellStyle.setFont(font);
            return cellStyle;
        }
    }

    public void BuildCaption(ExportExcelVo exportExcelVo, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, int maxcolumn) {
        XSSFRow captionRow = sheet.createRow(0);

        XSSFCell captionCell = captionRow.createCell(0);
        captionCell.setCellValue(exportExcelVo.getTemplateVo().getExportCaption());
        XSSFCellStyle xssfCellStyle = getCellStyle(exportExcelVo.getTemplateVo().getCaptionStyle(), xssfExcelUtil, workbook);

        captionCell.setCellStyle(xssfCellStyle);
        if (exportExcelVo.getTemplateVo().getCaptionStyle().getHeight() > 0) {
            captionRow.setHeightInPoints(exportExcelVo.getTemplateVo().getCaptionStyle().getHeight());
        }

        xssfExcelUtil.setCellStyle(1, maxcolumn, captionRow, xssfCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, maxcolumn));
    }

    public int BuildHeader(ExportExcelVo exportExcelVo, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, int maxcolumn) {
        List<RowVo> rowVoList = exportExcelVo.getTemplateVo().getRowVoList();

        int titleStartRow = 1;
        int titleEndRow = rowVoList.size() + titleStartRow;
        int maxCloumnNum = exportExcelVo.getTemplateVo().getBindDataCellVoList().size();
        int dataStartRow = 0;

        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastcolumn = 0;


        for (int i = 0; i < rowVoList.size(); i++) {
            RowVo rowVo = rowVoList.get(i);
            int currentRowNum = titleStartRow + i;
            XSSFRow titleRow = sheet.createRow(currentRowNum);
            if (rowVo.getRowHeigth() > 0) {
                titleRow.setHeightInPoints(rowVo.getRowHeigth());
            }
            List<CellVo> cellVoList = rowVo.getCellVoList();
            int cellNum = 0;
            int temp = 1;
            for (CellVo cellVo : cellVoList) {
                boolean cellIsMerged = xssfExcelUtil.isMergedRegion(sheet, currentRowNum, cellNum);
                while (cellIsMerged && temp < 40) {
                    temp++;
                    cellNum++;
                    cellIsMerged = xssfExcelUtil.isMergedRegion(sheet, currentRowNum, cellNum);

                    /*if(titleRow.getCell(cellNum)==null){
                        titleRow.createCell(cellNum);
                    }*/
                }
                XSSFCell tempCell = titleRow.createCell(cellNum);
                tempCell.setCellValue(cellVo.getCaption());

                CellStyleVo cellStyleVo = cellVo.getTitle_StyleVo();
                XSSFCellStyle cellStyle = getCellStyle(cellStyleVo, xssfExcelUtil, workbook);
                tempCell.setCellStyle(cellStyle);
                if (cellStyleVo.getWidth() > 0) {
                    sheet.setColumnWidth(tempCell.getColumnIndex(), cellStyleVo.getWidth());
                }

                firstRow = titleStartRow + i;
                lastRow = titleStartRow + i + cellVo.getMergerRow() - 1;
                firstCol = cellNum;
                lastcolumn = cellNum + cellVo.getAutoMergerCell() - 1;
                //System.out.println("合并位置1： " + "fr:" + firstRow + ";lr:" + lastRow + ";fc:" + firstCol + ";lc:" + lastcolumn);
                if (lastRow > firstRow || lastcolumn > firstCol) {
                    sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastcolumn));
                    //将合并格创建为单元格，用于生成边框

                }
                cellNum++;
            }
            dataStartRow = currentRowNum + 1;
        }

        for (int i = titleStartRow; i < titleEndRow; i++) {
            XSSFRow titleRow = sheet.getRow(i);
            if (titleRow != null) {
                for (int j = 0; j < maxCloumnNum; j++) {
                    XSSFCell titleCell = titleRow.getCell(j);
                    if (titleCell != null) {
                        //titleCell.setCellStyle(xssfExcelUtil.getFullBorderStyle());
                        //CellVo cellVo=exportExcelVo.getTemplateVo().getRowVoList().get(i).getCellVoList().get(j);
                        //XSSFCellStyle cellStyle=getCellStyle(cellVo.getTitle_StyleVo(),xssfExcelUtil,workbook);
                        //titleCell.setCellStyle(cellStyle);
                        //titleCell.setCellStyle(xssfExcelUtil.getFullBorderStyle());
                    } else {
                        titleCell = titleRow.createCell(j);
                        XSSFCell mergedFirstCell = (XSSFCell) xssfExcelUtil.getMergedRegionFirstCell(sheet, i, j);
                        XSSFCellStyle cellStyle = mergedFirstCell.getCellStyle();
                        titleCell.setCellStyle(cellStyle);
                        //boolean cellIsMerged= xssfExcelUtil.isMergedRegion(sheet,currentRowNum,cellNum);
                        //titleCell.setCellStyle(xssfExcelUtil.getFullBorderStyle());
                    }
                }
            }
        }
        return dataStartRow;
    }

    public void BuildData(ExportExcelVo exportExcelVo, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, int maxcolumn, int dataStartRow) throws Build4DGenerallyException {
        boolean isExportMainDataSoueceOnly = IsExportMainDataSoueceOnly(exportExcelVo);
        Map<Integer, XSSFCellStyle> xssfCellStyleMap = BuildDataCellStyle(exportExcelVo, xssfExcelUtil, workbook);
        if (isExportMainDataSoueceOnly) {
            //如果绑定的是单一来源数据
            BuildOnlyMainData(exportExcelVo, workbook, sheet, xssfExcelUtil, maxcolumn, dataStartRow, xssfCellStyleMap);
        } else {
            //如果绑定的是复合数据
            BuildComplexData(exportExcelVo, workbook, sheet, xssfExcelUtil, maxcolumn, dataStartRow, xssfCellStyleMap);
        }

    }

    private void BuildOnlyMainData(ExportExcelVo exportExcelVo, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, int maxcolumn, int dataStartRow, Map<Integer, XSSFCellStyle> xssfCellStyleMap) {
        MainDataSourceVo mainDataSourceVo = exportExcelVo.getMainDataSourceVo();
        int eachdataStartRow = dataStartRow;
        List<BindDataCellVo> bindDataCellVoList = null;
        for (int i = 0; i < mainDataSourceVo.getDataSource().size(); i++) {
            Map<String, Object> rowRecord = mainDataSourceVo.getDataSource().get(i);
            XSSFRow excelRow = sheet.createRow(eachdataStartRow);
            eachdataStartRow++;
            bindDataCellVoList = exportExcelVo.getTemplateVo().getBindDataCellVoList();
            for (int j = 0; j < bindDataCellVoList.size(); j++) {
                BindDataCellVo bindDataCellVo = bindDataCellVoList.get(j);
                XSSFCell excelCell = excelRow.createCell(j);
                excelCell.setCellStyle(xssfCellStyleMap.get(j));
                String value = ResolveBindValue(exportExcelVo, bindDataCellVoList, bindDataCellVo, rowRecord, null, workbook, sheet, xssfExcelUtil, excelRow, excelCell, i);
                excelCell.setCellValue(value);

                /*String sourceValue="";
                if (rowRecord.containsKey(bindDataCellVo.getBindField())) {
                    //数据优先级API>默认值>转换值>原始值
                    sourceValue = rowRecord.get(bindDataCellVo.getBindField()).toString();
                }
                String value = "";
                if (!bindDataCellVo.getFormaterRef().equals("")) {
                    value = this.GetFormaterValue(exportExcelVo, bindDataCellVoList, bindDataCellVo, rowRecord, null, workbook, sheet, dataRow, tempCell);
                } else if (!bindDataCellVo.getVtConverter().equals("")) {
                    value = this.GetVTConvertValue(exportExcelVo, bindDataCellVo, sourceValue);
                } else if (!bindDataCellVo.getDefaultConverter().equals("")) {
                    value = this.GetDefaultConverterValue(bindDataCellVo, sourceValue);
                } else {
                    value = sourceValue;
                    if (value.equals("")) {
                        if (!bindDataCellVo.getDefaultValue().equals("")) {
                            value = this.GetDefaultValue(bindDataCellVo);
                        }
                    }
                }
                tempCell.setCellValue(value);*/
            }
        }
    }

    private void BuildComplexData(ExportExcelVo exportExcelVo, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, int maxcolumn, int dataStartRow, Map<Integer, XSSFCellStyle> xssfCellStyleMap) throws Build4DGenerallyException {
        long wStartTime = 0l;
        if (ExportToExcelManager.IsDebuger) {
            System.out.println("Begin-》BuildComplexData》重新计算数据间关系--------------------------------------------------------------------------------------------------------------------");
            wStartTime = System.currentTimeMillis();
        }

        //region 重新计算数据集合
        List<BindDataCellVo> bindDataCellVoList = exportExcelVo.getTemplateVo().getBindDataCellVoList();
        Map<String, String> usedDataSetMap = new HashMap<String, String>();
        for (BindDataCellVo bindDataCellVo : bindDataCellVoList) {
            if (!usedDataSetMap.containsKey(bindDataCellVo.getBindDataSource())) {
                usedDataSetMap.put(bindDataCellVo.getBindDataSource(), "used");
            }
        }

        //判断当前数据集中是否存在指定名称的数据集合
        for (Map.Entry<String, String> stringStringEntry : usedDataSetMap.entrySet()) {
            String key = stringStringEntry.getKey();
            if (!key.equals(DataSourceTypeEnum.MAIN.getText())) {
                if (!exportExcelVo.getSubDataSourceVoMap().containsKey(key)) {
                    String errorMsg = "请检查在SubDataSource节点中是否存在Name为" + key + "的数据源！";
                    throw new Build4DGenerallyException(0, errorMsg);
                }
            }
        }

        //-------------将主记录和从记录进行关联并分组。
        //--出从属记录根据FKKey进行分组
        Map<String, List<Map>> rebuildSubRecords = new HashMap<String, List<Map>>();
        //Map<String,Integer> mainMaxSubMap=new HashMap<String, Integer>();
        Map<String, SubDataSourceVo> tempSubDataSourceVoMap = exportExcelVo.getSubDataSourceVoMap();
        for (Map.Entry<String, SubDataSourceVo> sourceVoEntry : tempSubDataSourceVoMap.entrySet()) {
            if (usedDataSetMap.containsKey(sourceVoEntry.getKey())) {
                List<Map> subDataSource = sourceVoEntry.getValue().getDataSource();
                if (subDataSource != null && subDataSource.size() > 0) {
                    for (int i = 0; i < subDataSource.size(); i++) {
                        Map<String, String> subRecord = subDataSource.get(i);
                        if (i == 0) {
                            if (!subRecord.containsKey(sourceVoEntry.getValue().getFkKey())) {
                                String errorMsg = "请检查在SubDataSource节点中是否存在Name为" + sourceVoEntry.getKey() + "的数据源,其中的数据源是否包含了列" + sourceVoEntry.getValue().getFkKey() + "！";
                                throw new Build4DGenerallyException(0, errorMsg);
                            }
                        }

                        String fkRecordId = subRecord.get(sourceVoEntry.getValue().getFkKey());
                        String fullKey = sourceVoEntry.getKey() + "_" + fkRecordId;
                        if (!rebuildSubRecords.containsKey(fullKey)) {
                            List<Map> tempList = new ArrayList<Map>();
                            tempList.add(subRecord);
                            rebuildSubRecords.put(fullKey, tempList);
                        } else {
                            List<Map> tempList = rebuildSubRecords.get(fullKey);
                            tempList.add(subRecord);
                        }
                    }
                }
            }
        }


        List<Map<String, Object>> rebuildRecords = new ArrayList<Map<String, Object>>();
        List<Map> mainRecords = exportExcelVo.getMainDataSourceVo().getDataSource();

        int _maxSubRecordLength = 0;
        if (mainRecords != null && mainRecords.size() > 0) {
            for (Map mainRecord : mainRecords) {
                Map<String, Object> complexSingleRecord = new HashMap<String, Object>();
                _maxSubRecordLength = 1;
                //存入主记录
                complexSingleRecord.put("Main", mainRecord);
                //根据Name存入从属记录
                String RecordId = mainRecord.get(exportExcelVo.getMainDataSourceVo().getKey()).toString();
                for (Map.Entry<String, SubDataSourceVo> sourceVoEntry : tempSubDataSourceVoMap.entrySet()) {
                    String name = sourceVoEntry.getKey();
                    String fullKey = name + "_" + RecordId;
                    List<Map> tempSubRecords = rebuildSubRecords.get(fullKey);
                    complexSingleRecord.put(name, tempSubRecords);
                    if (tempSubRecords != null) {
                        if (tempSubRecords.size() > _maxSubRecordLength) {
                            _maxSubRecordLength = tempSubRecords.size();
                        }
                    }
                }
                mainRecord.put("_MaxSubRecordLength", _maxSubRecordLength);
                rebuildRecords.add(complexSingleRecord);
            }
        }

        //endregion
        if (ExportToExcelManager.IsDebuger) {
            long wEndTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("重新计算数据间关系耗时： " + (wEndTime - wStartTime) + "ms");
            System.out.println("End-》BuildComplexData》重新计算数据间关系--------------------------------------------------------------------------------------------------------------------");
        }

        if (ExportToExcelManager.IsDebuger) {
            System.out.println("Begin-》BuildComplexData》生成EXCEL--------------------------------------------------------------------------------------------------------------------");
            wStartTime = System.currentTimeMillis();
        }
        //region 将数据写入EXCEL
        int _MaxSubRecordLength = 0;
        String value = "";
        XSSFRow excelRow = null;
        XSSFCell excelCell = null;
        XSSFRow styleRow = null;
        XSSFCell styleCell = null;
        int firstRow;
        int lastRow;
        int firstCol;
        int lastcolumn;
        XSSFRow subRow = null;
        XSSFCell subCell = null;
        String subValue = "";
        if (rebuildRecords != null && rebuildRecords.size() > 0) {
            int eachdataStartRow = dataStartRow;

            for (int ix = 0; ix < rebuildRecords.size(); ix++) {
                Map<String, Object> fullRecord = rebuildRecords.get(ix);
                Map<String, Object> mainRecord = (Map<String, Object>) fullRecord.get("Main");
                excelRow = sheet.createRow(eachdataStartRow);

                bindDataCellVoList = exportExcelVo.getTemplateVo().getBindDataCellVoList();

                _MaxSubRecordLength = Integer.parseInt(mainRecord.get("_MaxSubRecordLength").toString());
                for (int j = 0; j < bindDataCellVoList.size(); j++) {
                    BindDataCellVo bindDataCellVo = bindDataCellVoList.get(j);
                    excelCell = excelRow.createCell(j);
                    excelCell.setCellStyle(xssfCellStyleMap.get(j));

                    value = ResolveBindValue(exportExcelVo, bindDataCellVoList, bindDataCellVo, mainRecord, fullRecord, workbook, sheet, xssfExcelUtil, excelRow, excelCell, ix);

                    excelCell.setCellValue(value);
                    if (bindDataCellVo.getBindDataSource().equals(DataSourceTypeEnum.MAIN.getText())) {
                        if (_MaxSubRecordLength > 1) {
                            firstRow = eachdataStartRow;
                            lastRow = eachdataStartRow + _MaxSubRecordLength - 1;
                            firstCol = j;
                            lastcolumn = j;
                            //System.out.println("合并位置BuildComplexData222222： " + "fr:" + firstRow + ";lr:" + lastRow + ";fc:" + firstCol + ";lc:" + lastcolumn);
                            if (lastRow > firstRow) {
                                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastcolumn));
                                //创建合并空Cell，并设置样式
                                for (int x = firstRow + 1; x <= lastRow; x++) {
                                    styleRow = sheet.getRow(x);
                                    if (styleRow == null) {
                                        styleRow = sheet.createRow(x);
                                    }
                                    styleCell = styleRow.createCell(firstCol);
                                    styleCell.setCellStyle(xssfCellStyleMap.get(j));
                                }
                            }
                        }
                    } else {

                        String dataSourceName = bindDataCellVo.getBindDataSource();
                        List<Map> dataSource = (List<Map>) fullRecord.get(dataSourceName);
                        //补充空格并设置默认记录
                        firstRow = eachdataStartRow;
                        lastRow = eachdataStartRow + _MaxSubRecordLength - 1;
                        firstCol = j;
                        for (int x = firstRow + 1; x <= lastRow; x++) {
                            styleRow = sheet.getRow(x);
                            styleCell = styleRow.createCell(firstCol);
                            styleCell.setCellStyle(xssfCellStyleMap.get(j));
                        }
                        if (dataSource != null) {
                            for (int i = 0; i < dataSource.size(); i++) {
                                Map map = dataSource.get(i);
                                subRow = sheet.getRow(eachdataStartRow + i);
                                subCell = subRow.getCell(j);
                                subCell.setCellStyle(xssfCellStyleMap.get(j));
                                subValue = ResolveBindValue(exportExcelVo, bindDataCellVoList, bindDataCellVo, map, fullRecord, workbook, sheet, xssfExcelUtil, excelRow, excelCell, i);
                                subCell.setCellValue(subValue);
                            }
                        }
                    }
                }

                eachdataStartRow += _MaxSubRecordLength;
            }
        }
        //endregion
        if (ExportToExcelManager.IsDebuger) {
            long wEndTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("生成EXCEL耗时： " + (wEndTime - wStartTime) + "ms");
            System.out.println("End-》BuildComplexData》生成EXCEL--------------------------------------------------------------------------------------------------------------------");
        }
    }

    public String ResolveBindValue(ExportExcelVo exportExcelVo, List<BindDataCellVo> bindDataCellVoList, BindDataCellVo bindDataCellVo, Map record, Map<String, Object> complexRecord, XSSFWorkbook workbook, XSSFSheet sheet, XSSFExcelUtility xssfExcelUtil, XSSFRow excelRow, XSSFCell excelCell, int rowIndex) {
        String value = "";
        String sourceValue = "";
        if (record.containsKey(bindDataCellVo.getBindField())) {
            //数据优先级API>默认值>转换值>原始值
            Object obj = record.get(bindDataCellVo.getBindField());
            sourceValue = obj == null ? "" : obj.toString();
        }
        if (!bindDataCellVo.getFormaterRef().equals("")) {
            value = this.GetFormaterValue(exportExcelVo, bindDataCellVoList, bindDataCellVo, record, complexRecord, workbook, sheet, excelRow, excelCell);
        } else if (!bindDataCellVo.getVtConverter().equals("")) {
            value = this.GetVTConvertValue(exportExcelVo, bindDataCellVo, sourceValue);
        } else if (!bindDataCellVo.getDefaultConverter().equals("")) {
            value = this.GetDefaultConverterValue(bindDataCellVo, sourceValue, rowIndex);
        } else {
            value = sourceValue;
            if (value.equals("")) {
                if (!bindDataCellVo.getDefaultValue().equals("")) {
                    value = this.GetDefaultValue(bindDataCellVo);
                }
            }
        }
        return value;
    }

    private Map<Integer, XSSFCellStyle> BuildDataCellStyle(ExportExcelVo exportExcelVo, XSSFExcelUtility xssfExcelUtil, XSSFWorkbook workbook) {
        if (_dataCellStyle == null) {
            _dataCellStyle = new HashMap<Integer, XSSFCellStyle>();
            List<BindDataCellVo> bindDataCellVoList = exportExcelVo.getTemplateVo().getBindDataCellVoList();
            for (int i = 0; i < bindDataCellVoList.size(); i++) {
                XSSFCellStyle xssfCellStyle = getCellStyle(bindDataCellVoList.get(i).getData_StyleVo(), xssfExcelUtil, workbook);
                _dataCellStyle.put(i, xssfCellStyle);
            }
        }
        return _dataCellStyle;
    }

    private boolean IsExportMainDataSoueceOnly(ExportExcelVo exportExcelVo) {
        List<BindDataCellVo> bindDataCellVoList = exportExcelVo.getTemplateVo().getBindDataCellVoList();
        for (BindDataCellVo bindDataCellVo : bindDataCellVoList) {
            if (bindDataCellVo.getDataSourceType().equals(DataSourceTypeEnum.SUB)) {
                return false;
            }
        }
        return true;
    }

    private String GetDefaultConverterValue(BindDataCellVo bindDataCellVo, String sourceValue, int RowIndex) {
        if (bindDataCellVo.getDefaultConverter().equals("ToShortDateTime")) {
            if (sourceValue != null && !sourceValue.equals("") && sourceValue.indexOf(" ") > 0) {
                return sourceValue.split(" ")[0];
            }
        }
        if (bindDataCellVo.getDefaultConverter().equals("ToRowNumber")) {
            return String.valueOf(RowIndex + 1);
        }
        return sourceValue;
    }

    private String GetVTConvertValue(ExportExcelVo exportExcelVo, BindDataCellVo bindDataCellVo, String sourceValue) {
        String VTKey = bindDataCellVo.getVtConverter().trim();
        if (exportExcelVo.getVtConvertDataSource().containsKey(VTKey)) {
            return exportExcelVo.getVtConvertDataSource().get(VTKey).get(sourceValue);
        }
        return "找不到数据源";
    }

    private String GetFormaterValue(ExportExcelVo exportExcelVo, List<BindDataCellVo> bindDataCellVoList, BindDataCellVo bindDataCellVo,
                                    Map<String, Object> singleRecord, Map<String, Object> complexSingleRecord,
                                    XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow row, XSSFCell cell) {
        IValueFormater valueFormater = exportExcelVo.getValueFormater().get(bindDataCellVo.getFormaterRef());
        if (valueFormater != null) {
            return valueFormater.Formater(exportExcelVo, bindDataCellVoList, bindDataCellVo,
                    singleRecord, complexSingleRecord,
                    workbook, sheet, row, cell);
        }
        return "未找到格式化对象！";
    }

    private String GetDefaultValue(BindDataCellVo bindDataCellVo) {
        return bindDataCellVo.getDefaultValue();
    }

    public byte[] ToExcel(ExportExcelVo exportExcelVo) throws IOException, Build4DGenerallyException {


        XSSFExcelUtility xssfExcelUtil = new XSSFExcelUtility();
        XSSFWorkbook workbook = new XSSFWorkbook();
        xssfExcelUtil.setWorkbook(workbook);
        XSSFSheet sheet = workbook.createSheet();

        int maxcolumn = exportExcelVo.getTemplateVo().getBindDataCellVoList().size() - 1;

        long wStartTime = 0l;
        if (ExportToExcelManager.IsDebuger) {
            System.out.println("Begin构建EXCEL表头--------------------------------------------------------------------------------------------------------------------");
            wStartTime = System.currentTimeMillis();
        }
        //写入标题
        BuildCaption(exportExcelVo, workbook, sheet, xssfExcelUtil, maxcolumn);

        //构建头部
        int dataStartRow = BuildHeader(exportExcelVo, workbook, sheet, xssfExcelUtil, maxcolumn);

        if (ExportToExcelManager.IsDebuger) {
            long wEndTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("构建EXCEL表头耗时： " + (wEndTime - wStartTime) + "ms");
            System.out.println("end构建EXCEL表头--------------------------------------------------------------------------------------------------------------------");
        }

        BuildData(exportExcelVo, workbook, sheet, xssfExcelUtil, maxcolumn, dataStartRow);

        if (exportExcelVo.getTemplateVo().isAutoSizeColumn()) {

            if (ExportToExcelManager.IsDebuger) {
                System.out.println("Begin生成EXCEL后自动设置列宽--------------------------------------------------------------------------------------------------------------------");
                wStartTime = System.currentTimeMillis();
            }
            for (int i = 0; i < maxcolumn; i++) {
                if (sheet.getColumnWidth(i) <= 2148) {
                    sheet.autoSizeColumn((short) i);
                }
            }
            if (ExportToExcelManager.IsDebuger) {
                long wEndTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("生成EXCEL后自动设置列宽耗时： " + (wEndTime - wStartTime) + "ms");
                System.out.println("End生成EXCEL后自动设置列宽--------------------------------------------------------------------------------------------------------------------");
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bos.close();
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}