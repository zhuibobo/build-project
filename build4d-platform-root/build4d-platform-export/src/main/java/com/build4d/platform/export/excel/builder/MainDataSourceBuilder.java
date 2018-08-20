package com.build4d.platform.export.excel.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IExportDataSource;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public class MainDataSourceBuilder {
    private static String ErrorBuilderDataSource="找不到配置未启用的数据源，请配置数据源并将【Enable】设置为【True】";
    public MainDataSourceVo getMainDataSource(Document document, Node exportNode, ExportSetTypeEnum exportSetType, String[] recordIdArray) throws XPathExpressionException, Build4DGenerallyException {
        Node dataSourceNode= XMLUtility.parseForNode(exportNode,"MainDataSource");
        Node sqlDataSourceNode=XMLUtility.parseForNode(dataSourceNode,"SQLDataSource[@Enable='True']");
        IExportDataSource dataSource=null;
        //todo
        /*if(sqlDataSourceNode!=null){
            dataSource=new CategoryViewDataSourceBuilder();
            return dataSource.getDataSource(document,exportNode,sqlDataSourceNode,exportSetType,recordIdArray,null);
        }
        Node apiDataSourceNode=XMLUtil.parseForNode(dataSourceNode,"APIDataSource[@Enable='True']");
        if(apiDataSourceNode!=null){
            dataSource=new APIDataSourceBuilder();
            return dataSource.getDataSource(document,exportNode,apiDataSourceNode,exportSetType,recordIdArray,null);
        }*/
        throw new Build4DGenerallyException(0,ErrorBuilderDataSource);
    }
}
