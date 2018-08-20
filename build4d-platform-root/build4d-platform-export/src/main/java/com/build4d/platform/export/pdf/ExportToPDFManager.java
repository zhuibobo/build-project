package com.build4d.platform.export.pdf;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.platform.export.general.config.ConfigManager;
import com.build4d.platform.export.general.vo.ExportResult;
import com.build4d.platform.export.pdf.builder.ExportPDFVoBuilder;
import com.build4d.platform.export.pdf.to.ExportToPDF;
import com.build4d.platform.export.pdf.vo.ExportPDFVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportToPDFManager {
    public static boolean IsDebuger=true;
    public static ExportResult exportToPDFWithForm(String configFilePath, String exportId, String sourceCategoryId, String currentCategoryId, String buttonId,
                                                   String recordIds, String folderId) throws Build4DGenerallyException, SAXException, ParserConfigurationException, XPathExpressionException, IOException, IllegalAccessException, InstantiationException, ParserConfigurationException, SAXException, XPathExpressionException {
        ConfigManager cm=new ConfigManager();
        Document document = cm.LoadDocument(configFilePath);
        Node exportNode=cm.getExportNode(document,exportId);

        long wStartTime=0l;
        if(IsDebuger){
            System.out.println("Begin从数据库中获取数据并转换实体--------------------------------------------------------------------------------------------------------------------");
            wStartTime=System.currentTimeMillis();
        }

        ExportPDFVo exportPDFVo=convertExportNodeToVo(document,exportNode,sourceCategoryId,  currentCategoryId,  buttonId, recordIds,  folderId);
        //loadDataSourceToExportVo(document,exportNode,exportPDFVo,sourceCategoryId,  currentCategoryId,  buttonId, recordIds,  folderId);
        if(IsDebuger){
            long wEndTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("获取数据并转换为ExportPDFVo实体耗时： "+(wEndTime-wStartTime)+"ms");
            System.out.println("End从数据库中获取数据并转换实体--------------------------------------------------------------------------------------------------------------------");
        }

        if(IsDebuger){
            System.out.println("Begin生成PDF全程--------------------------------------------------------------------------------------------------------------------");
            wStartTime=System.currentTimeMillis();
        }

        byte[] pdfData=exportToPDF(exportPDFVo);

        if(IsDebuger){
            long wEndTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("将exportPDFVo实体转换为PDF耗时： "+(wEndTime-wStartTime)+"ms");
            System.out.println("End生成PDF全程--------------------------------------------------------------------------------------------------------------------");
        }

        ExportResult pdfExportResult=new ExportResult();
        pdfExportResult.setByteData(pdfData);
        pdfExportResult.setExportFileName(exportPDFVo.getExportFileName());
        return pdfExportResult;
    }
    public static byte[] exportToPDF(ExportPDFVo vo) throws IOException, Build4DGenerallyException, XPathExpressionException {
        ExportToPDF exportToPDF=new ExportToPDF();
        return exportToPDF.ToPDF(vo);
    }
    /*public static void loadDataSourceToExportVo(Document document, Node exportNode, ExportPDFVo exportPDFVo,String sourceCategoryId, String currentCategoryId, String buttonId,
                                                String recordIds, String folderId) throws SSSBaseException, XPathExpressionException, IllegalAccessException, InstantiationException {
        ExportPDFVoBuilder.BuilderMainDataSource(exportPDFVo, document, exportNode,sourceCategoryId,currentCategoryId,buttonId,recordIds,folderId);
        ExportPDFVoBuilder.BuilderSubDataSource(exportPDFVo, document, exportNode);
    }*/
    public static ExportPDFVo convertExportNodeToVo(Document document,Node exportNode,String sourceCategoryId, String currentCategoryId, String buttonId,
                                                    String recordIds, String folderId) throws XPathExpressionException, Build4DGenerallyException, InstantiationException, IllegalAccessException {
        ExportPDFVo exportPDFVo=new ExportPDFVo();
        ExportPDFVoBuilder.BuilderBaseProp(exportPDFVo,exportNode);

        ExportPDFVoBuilder.BuilderMainDataSource(exportPDFVo, document, exportNode,sourceCategoryId,currentCategoryId,buttonId,recordIds,folderId);
        ExportPDFVoBuilder.BuilderSubDataSource(exportPDFVo, document, exportNode);
        ExportPDFVoBuilder.BuilderVTConvertDataSource(exportPDFVo, document, exportNode);
        ExportPDFVoBuilder.BuilderFormater(exportPDFVo, document, exportNode);
        ExportPDFVoBuilder.BuilderStyles(exportPDFVo, document, exportNode);
        exportPDFVo.setExportNode(exportNode);
        /*ExportExcelVoBuilder.BuilderTemplate(exportPDFVo,document,exportNode);*/
        return exportPDFVo;
    }
}
