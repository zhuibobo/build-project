package com.build4d.platform.export.pdf.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.ClassUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IExportDataSource;
import com.build4d.platform.export.general.builder.ExportVoBuilder;
import com.build4d.platform.export.general.builder.SubDataSourceBuilder;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.datasource.APIDataSourceBuilder;
import com.build4d.platform.export.general.vo.CategoryParaVo;
import com.build4d.platform.export.general.vo.ExportVo;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import com.build4d.platform.export.pdf.IValueFormater;
import com.build4d.platform.export.pdf.datasource.CategoryTableDataSourceBuilder;
import com.build4d.platform.export.pdf.vo.ExportPDFVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportPDFVoBuilder extends ExportVoBuilder {
    private static String ErrorBuilderDataSource="找不到配置未启用的数据源，请配置数据源并将【Enable】设置为【True】";
    public static void BuilderMainDataSource(ExportPDFVo exportPDFVo, Document document, Node exportNode, String sourceCategoryId, String currentCategoryId, String buttonId,
                                             String recordIds, String folderId) throws XPathExpressionException, Build4DGenerallyException {
        Node dataSourceNode= XMLUtility.parseForNode(exportNode,"MainDataSource");
        Node sqlDataSourceNode=XMLUtility.parseForNode(dataSourceNode,"SQLDataSource[@Enable='True']");
        IExportDataSource dataSource=null;
        MainDataSourceVo mainDataSourceVo=null;
        CategoryParaVo categoryParaVo=new CategoryParaVo(sourceCategoryId,currentCategoryId,buttonId,folderId);
        if(sqlDataSourceNode!=null){
            dataSource=new CategoryTableDataSourceBuilder();
             mainDataSourceVo=dataSource.getDataSource(document,exportNode,sqlDataSourceNode, ExportSetTypeEnum.LISTSETWITHSELECTED,recordIds.split(","),categoryParaVo);
        }
        Node apiDataSourceNode=XMLUtility.parseForNode(dataSourceNode,"APIDataSource[@Enable='True']");
        if(apiDataSourceNode!=null){
            dataSource=new APIDataSourceBuilder();
             mainDataSourceVo= dataSource.getDataSource(document,exportNode,sqlDataSourceNode,ExportSetTypeEnum.LISTSETWITHSELECTED,recordIds.split(","),categoryParaVo);
        }
        exportPDFVo.setMainDataSourceVo(mainDataSourceVo);
        //throw new SSSGenerallyException(0,ErrorBuilderDataSource);
    }

    public static void BuilderSubDataSource(ExportVo exportVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException, InstantiationException, IllegalAccessException {
        SubDataSourceBuilder subDataSourceBuilder=new SubDataSourceBuilder();
        exportVo.setSubDataSourceVoMap(subDataSourceBuilder.getSubDataSource(exportVo,document,exportNode));
    }

    public static void BuilderStyles(ExportPDFVo exportPDFVo, Document document, Node exportNode) throws XPathExpressionException {
        if(XMLUtility.parseForNodeList(exportNode,"Styles/Style").isEmpty()){
            exportPDFVo.setStyles(null);
        }
        else
        {
            Map<String,Map<String,String>> styles=new HashMap<String, Map<String,String>>();
            List<Node> styleNodeList=XMLUtility.parseForNodeList(exportNode,"Styles/Style");
            for (Node styleNode : styleNodeList) {
                Map<String,String> style=new HashMap();
                List<Node> propNodeList=XMLUtility.parseForNodeList(styleNode,"Prop");
                for (Node propNode : propNodeList) {
                    String name=XMLUtility.getAttribute(propNode,"Name");
                    String value=XMLUtility.getAttribute(propNode,"Value");
                    if(!style.containsKey(name)) {
                        style.put(name, value);
                    }
                }
                String Id=XMLUtility.getAttribute(styleNode,"Id");
                if(!styles.containsKey(Id)) {
                    styles.put(Id, style);
                }
            }
            exportPDFVo.setStyles(styles);
        }
    }

    public static void BuilderFormater(ExportPDFVo exportPDFVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
        Node formatersNode=XMLUtility.parseForNode(exportNode,"Formaters");
        if(formatersNode!=null) {
            //FormaterBuilder fm = new FormaterBuilder();
            Map<String, IValueFormater> formaterMap = getFormaterInstances(document, formatersNode);
            exportPDFVo.setValueFormater(formaterMap);
        }
    }

    protected static Map<String,IValueFormater> getFormaterInstances(Document document, Node formatersNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
        Map<String,IValueFormater> result=new HashMap<String, IValueFormater>();
        List<Node> formaterNodeList= XMLUtility.parseForNodeList(formatersNode,"Formater");
        if(formaterNodeList!=null&&formaterNodeList.size()>0){
            for (Node node : formaterNodeList) {
                String name=XMLUtility.getAttribute(node,"Name");
                if(!result.containsKey(name)){
                    String fullClassName=XMLUtility.parseForString(node,"FullClassName").trim();
                    if(!fullClassName.equals("")) {
                        IValueFormater instance = (IValueFormater) ClassUtility.loadClass(fullClassName).newInstance();
                        result.put(name, instance);
                    }
                }
                else
                {
                    throw new Build4DGenerallyException(0,"Formaters>Formater[@Name='"+name+"'],存在相同Name的节点");
                }
            }
        }
        return result;
    }
}
