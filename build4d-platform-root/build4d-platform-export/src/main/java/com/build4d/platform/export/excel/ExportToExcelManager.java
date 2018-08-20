package com.build4d.platform.export.excel;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.base.tools.common.list.IListLoopupCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.platform.export.excel.builder.ExportExcelVoBuilder;
import com.build4d.platform.export.excel.to.ExportToExcel;
import com.build4d.platform.export.excel.vo.CellVo;
import com.build4d.platform.export.excel.vo.ExportExcelVo;
import com.build4d.platform.export.excel.vo.RowVo;
import com.build4d.platform.export.excel.vo.UserCustomizeVo;
import com.build4d.platform.export.general.config.ConfigManager;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.vo.ExportResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class ExportToExcelManager {
    public static boolean IsDebuger=true;

    public static ExportResult exportToExcel(String configFilePath, String exportId, String userCustomize, ExportSetTypeEnum exportSetType, String[] recordIdArray) throws Build4DGenerallyException, SAXException, ParserConfigurationException, XPathExpressionException, IOException, IllegalAccessException, InstantiationException {
        ConfigManager cm=new ConfigManager();
        Document document = cm.LoadDocument(configFilePath);
        Node exportNode=cm.getExportNode(document,exportId);

        long wStartTime=0l;
        if(IsDebuger){
            System.out.println("Begin从数据库中获取数据并转换实体--------------------------------------------------------------------------------------------------------------------");
            wStartTime=System.currentTimeMillis();
        }

        ExportExcelVo exportExcelVo=convertExportNodeToVo(document,exportNode,false,true,true,exportSetType,recordIdArray);
        if(userCustomize!=null&&!userCustomize.equals("")&&!userCustomize.equals("ExportAllCell")){
            String[] userCustomizeArray=userCustomize.split(";");
            ExportExcelVoBuilder.ReBuilderTemplateByUserCustomize(exportExcelVo,document,exportNode,userCustomizeArray);
        }
        loadDataSourceToExportVo(document,exportNode,exportExcelVo,exportSetType,recordIdArray);
        if(IsDebuger){
            long wEndTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("获取数据并转换为ExportExcelVo实体耗时： "+(wEndTime-wStartTime)+"ms");
            System.out.println("End从数据库中获取数据并转换实体--------------------------------------------------------------------------------------------------------------------");
        }

        if(IsDebuger){
            System.out.println("Begin生成EXCEL全程--------------------------------------------------------------------------------------------------------------------");
            wStartTime=System.currentTimeMillis();
        }

        byte[] excelData=exportToExcel(exportExcelVo);

        if(IsDebuger){
            long wEndTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("将exportExcelVo实体转换为EXCEL耗时： "+(wEndTime-wStartTime)+"ms");
            System.out.println("End生成EXCEL全程--------------------------------------------------------------------------------------------------------------------");
        }

        ExportResult exportResult =new ExportResult();
        exportResult.setByteData(excelData);
        exportResult.setExportFileName(exportExcelVo.getExportFileName());
        return exportResult;
    }

    public static byte[] exportToExcel(ExportExcelVo vo) throws IOException, Build4DGenerallyException {
        ExportToExcel exportToExcel=new ExportToExcel();
        return exportToExcel.ToExcel(vo);
    }

    public static boolean isUserCustomize(String configFilePath,String exportId) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, Build4DGenerallyException {
        ConfigManager cm=new ConfigManager();
        Document document = cm.LoadDocument(configFilePath);
        Node exportNode=cm.getExportNode(document,exportId);
        if(XMLUtility.getAttribute(exportNode,"UserCustomize").equals("True")){
            return true;
        }
        return false;
    }

    public static UserCustomizeVo getUserCustomizeVo(String configFilePath, String exportId) throws Exception {
        try {
            ExportExcelVo exportExcelVo = getExportExcelVo(configFilePath, exportId, false, false, false,ExportSetTypeEnum.LISTSET,null);
            UserCustomizeVo userCustomizeVo = new UserCustomizeVo();
            userCustomizeVo.setEnable(exportExcelVo.isUserCustomize());
            List<CellVo> userCustCellVoList = new ArrayList<CellVo>();
            userCustomizeVo.setCellVoList(userCustCellVoList);
            List<RowVo> rowVoList = exportExcelVo.getTemplateVo().getRowVoList();
            for (RowVo rowVo : rowVoList) {
                List<CellVo> cellVoList = rowVo.getCellVoList();
                for (CellVo cellVo : cellVoList) {
                    if (cellVo.isUserCustomize() && !cellVo.getUserCustomizeName().equals("")) {
                        userCustCellVoList.add(cellVo);
                    }
                }
            }
            Map<String, List<CellVo>> groupCells = ListUtility.Loopup(userCustCellVoList, new IListLoopupCondition<String, CellVo>() {
                public String Condition(CellVo Item) {
                    return Item.getUserCustomGroupKey();
                }
            });

            List<List<CellVo>> tempCellVoList=new ArrayList<List<CellVo>>();
            List<String> groupKeyOrder=exportExcelVo.getTemplateVo().getUserCustomizeGroupKeyOrderList();
            for (String orderKey : groupKeyOrder) {
                if(groupCells.containsKey(orderKey)){
                    tempCellVoList.add(groupCells.get(orderKey));
                    groupCells.remove(orderKey);
                }
            }
            for (Map.Entry<String, List<CellVo>> stringListEntry : groupCells.entrySet()) {
                tempCellVoList.add(stringListEntry.getValue());
            }

            userCustomizeVo.setGroupCellList(tempCellVoList);
            return userCustomizeVo;
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    public static ExportExcelVo getExportExcelVoTempladteOnly(String configFilePath,String exportId) throws IllegalAccessException, Build4DGenerallyException, ParserConfigurationException, IOException, XPathExpressionException, InstantiationException, SAXException {
        return getExportExcelVo(configFilePath,exportId,false,false,false,ExportSetTypeEnum.LISTSET,null);
    }

    /*public static ExportExcelVo getExportExcelVo(String configFilePath,String exportId) throws IllegalAccessException, SSSBaseException, ParserConfigurationException, IOException, XPathExpressionException, InstantiationException, SAXException {
        return getExportExcelVo(configFilePath,exportId,true,true,true);
    }*/

    public static ExportExcelVo getExportExcelVo(String configFilePath,String exportId,boolean loadDataSource,boolean loadTVDataSource,boolean loadFormater,ExportSetTypeEnum exportSetType,String[] recordIdArray) throws Build4DGenerallyException, SAXException, ParserConfigurationException, XPathExpressionException, IOException, IllegalAccessException, InstantiationException {
        ConfigManager cm = new ConfigManager();
        Document document = cm.LoadDocument(configFilePath);
        Node exportNode = cm.getExportNode(document, exportId);
        ExportExcelVo exportExcelVo = convertExportNodeToVo(document, exportNode,loadDataSource,loadTVDataSource,loadFormater,exportSetType,recordIdArray);
        return exportExcelVo;
    }

    public static void loadDataSourceToExportVo(Document document,Node exportNode,ExportExcelVo exportExcelVo,ExportSetTypeEnum exportSetType,String[] recordIdArray) throws Build4DGenerallyException, XPathExpressionException, IllegalAccessException, InstantiationException {
        ExportExcelVoBuilder.BuilderMainDataSource(exportExcelVo, document, exportNode,exportSetType,recordIdArray);
        ExportExcelVoBuilder.BuilderSubDataSource(exportExcelVo, document, exportNode);
    }

    public static ExportExcelVo convertExportNodeToVo(Document document,Node exportNode,boolean loadDataSource,boolean loadTVDataSource,boolean loadFormater,ExportSetTypeEnum exportSetType,String[] recordIdArray) throws XPathExpressionException, Build4DGenerallyException, InstantiationException, IllegalAccessException {
        ExportExcelVo exportExcelVo=new ExportExcelVo();
        ExportExcelVoBuilder.BuilderBaseProp(exportExcelVo,exportNode);
        if(loadDataSource) {
            ExportExcelVoBuilder.BuilderMainDataSource(exportExcelVo, document, exportNode,exportSetType,recordIdArray);
            ExportExcelVoBuilder.BuilderSubDataSource(exportExcelVo, document, exportNode);
        }
        if(loadTVDataSource) {
            ExportExcelVoBuilder.BuilderVTConvertDataSource(exportExcelVo, document, exportNode);
        }
        if(loadFormater) {
            ExportExcelVoBuilder.BuilderFormater(exportExcelVo, document, exportNode);
        }
        ExportExcelVoBuilder.BuilderTemplate(exportExcelVo,document,exportNode);
        return exportExcelVo;
    }
}
