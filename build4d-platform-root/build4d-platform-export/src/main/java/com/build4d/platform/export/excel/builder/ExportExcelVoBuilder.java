package com.build4d.platform.export.excel.builder;


import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.excel.IValueFormater;
import com.build4d.platform.export.excel.vo.ExportExcelVo;
import com.build4d.platform.export.excel.vo.TemplateVo;
import com.build4d.platform.export.general.builder.ExportVoBuilder;
import com.build4d.platform.export.general.builder.SubDataSourceBuilder;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class ExportExcelVoBuilder extends ExportVoBuilder {

    public static void BuilderBaseProp(ExportExcelVo exportVo, Node exportNode) {
        /*String id = XMLUtil.getAttribute(exportNode, "Id");
        String name = XMLUtil.getAttribute(exportNode, "Name");
        String exportFileName = XMLUtil.getAttribute(exportNode, "ExportFileName");
        exportVo.setId(id);
        exportVo.setName(name);
        exportVo.setExportFileName(exportFileName);*/
        ExportVoBuilder.BuilderBaseProp(exportVo,exportNode);
        String userCustomize = XMLUtility.getAttribute(exportNode, "UserCustomize");
        String run = XMLUtility.getAttribute(exportNode, "Run");
        exportVo.setUserCustomize(userCustomize.toLowerCase().equals("true") ? true : false);
        exportVo.setRun(run.toLowerCase().equals(ExportExcelVo.Run_OnlyTitle.toLowerCase()) ? ExportExcelVo.Run_OnlyTitle : ExportExcelVo.Run_InCludeData);
    }

    public static void BuilderMainDataSource(ExportExcelVo exportExcelVo, Document document, Node exportNode, ExportSetTypeEnum exportSetType, String[] recordIdArray) throws XPathExpressionException, Build4DGenerallyException {
        MainDataSourceBuilder mainDataSourceBuilder=new MainDataSourceBuilder();
        exportExcelVo.setMainDataSourceVo(mainDataSourceBuilder.getMainDataSource(document,exportNode,exportSetType,recordIdArray));
    }

    public static void BuilderSubDataSource(ExportExcelVo exportExcelVo, Document document, Node exportNode) throws XPathExpressionException,Build4DGenerallyException, InstantiationException, IllegalAccessException {
        SubDataSourceBuilder subDataSourceBuilder=new SubDataSourceBuilder();
        exportExcelVo.setSubDataSourceVoMap(subDataSourceBuilder.getSubDataSource(exportExcelVo,document,exportNode));
    }

    public static void BuilderFormater(ExportExcelVo exportExcelVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
        Node formatersNode=XMLUtility.parseForNode(exportNode,"Formaters");
        if(formatersNode!=null) {
            FormaterBuilder fm = new FormaterBuilder();
            Map<String, IValueFormater> formaterMap = fm.getFormaterInstances(document, formatersNode);
            exportExcelVo.setValueFormater(formaterMap);
        }
    }

    public static void BuilderTemplate(ExportExcelVo exportExcelVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException {
        Node templateNode=XMLUtility.parseForNode(exportNode,"Template");
        TemplateBuilder templateBuilder=new TemplateBuilder();
        TemplateVo templateVo=templateBuilder.getTemplateVo(exportExcelVo,document,exportNode,templateNode);
        exportExcelVo.setTemplateVo(templateVo);
        templateVo.setBindDataCellVoList(templateBuilder.getBindDataCellBuilder(exportExcelVo));
        templateBuilder.validateVo(exportExcelVo,templateVo);
    }

    public static void ReBuilderTemplateByUserCustomize(ExportExcelVo exportExcelVo, Document document, Node exportNode,String[] userCustomize) throws Build4DGenerallyException {
        TemplateBuilder templateBuilder=new TemplateBuilder();
        templateBuilder.rebuildWithUserCustomize(exportExcelVo,userCustomize);
    }
}
