package com.build4d.platform.export.general.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.vo.ExportVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportVoBuilder {
    public static void BuilderBaseProp(ExportVo exportVo, Node exportNode) {
        String id = XMLUtility.getAttribute(exportNode, "Id");
        String name = XMLUtility.getAttribute(exportNode, "Name");
        String exportFileName = XMLUtility.getAttribute(exportNode, "ExportFileName");
        String userCustomize = XMLUtility.getAttribute(exportNode, "UserCustomize");
        String run = XMLUtility.getAttribute(exportNode, "Run");
        exportVo.setId(id);
        exportVo.setName(name);
        exportVo.setExportFileName(exportFileName);
    }

    public static void BuilderVTConvertDataSource(ExportVo exportVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
        Node vtConvertDataSourceNode=XMLUtility.parseForNode(exportNode,"VTConvertDataSource");
        if(vtConvertDataSourceNode!=null) {
            VTConvertDataSourceBuilder vtConvertDataSource = new VTConvertDataSourceBuilder();
            vtConvertDataSource.ValidateKeyIsRepeat(vtConvertDataSourceNode);

            Map<String, Map<String, String>> vtDataSource = vtConvertDataSource.getVTDataSource(document, exportNode, vtConvertDataSourceNode);
            exportVo.setVtConvertDataSource(vtDataSource);
        }
    }
}
