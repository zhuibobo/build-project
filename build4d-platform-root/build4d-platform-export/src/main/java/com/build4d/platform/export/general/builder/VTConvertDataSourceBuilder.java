package com.build4d.platform.export.general.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.ClassUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IVTDataSourceAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class VTConvertDataSourceBuilder {
    public boolean ValidateKeyIsRepeat(Node vtDataSourceNode) throws Build4DGenerallyException {
        if(vtDataSourceNode==null){
            return true;
        }
        NodeList dataSources=vtDataSourceNode.getChildNodes();

        //校验是否存在相同的key的节点
        List<String> keys=new ArrayList<String>();
        for(int i=0;i<dataSources.getLength();i++){
            Node dataSource=dataSources.item(i);
            if(dataSource.getNodeName().equals("SQLDataSource")||dataSource.getNodeName().equals("DictionaryDataSource")||dataSource.getNodeName().equals("ApiDataSource")) {
                String key= XMLUtility.getAttribute(dataSource,"Key");
                if(keys.contains(key)){
                    throw new Build4DGenerallyException(0,"VTConvertDataSource中存在相同Key的节点");
                }
                keys.add(key);
            }
        }
        return true;
    }
    private Map<String,String> getSQLDataSource(Node sqlDataSourceNode) throws Build4DGenerallyException {
        String key=XMLUtility.getAttribute(sqlDataSourceNode,"Key");
        String sql=sqlDataSourceNode.getTextContent().trim();
        if(!sql.equals("")) {
            //List<Map> dbResult = HibernateStaticUtil.findByMultiRecordMapSQLString(sql);
            List<Map> dbResult = new ArrayList<>();
            //todo
            if (dbResult != null && dbResult.size() > 0) {
                Map<String, Object> singleRecord = dbResult.get(0);
                if (!singleRecord.containsKey("Value")) {
                    throw new Build4DGenerallyException(0, "VTConvertDataSource》SQLDataSource[@Key='" + key + "']节点解析的数据源不包含Value字段");
                }
                if (!singleRecord.containsKey("Text")) {
                    throw new Build4DGenerallyException(0, "VTConvertDataSource》SQLDataSource[@Key='" + key + "']节点解析的数据源不包含Text字段");
                }
            }
            Map<String,String> result=new HashMap<String, String>();
            for (Map map : dbResult) {
                result.put(map.get("Value").toString(),map.get("Text").toString());
            }

            return result;
        }
        return null;
    }
    private Map<String,String> getDictionaryDataSource(Node dictionaryDataSourceNode) throws XPathExpressionException {
        List<Node> itemNodes=XMLUtility.parseForNodeList(dictionaryDataSourceNode,"Item");
        if(itemNodes!=null&&itemNodes.size()>0) {
            Map<String,String> dictResult = new HashMap<String, String>();
            for (Node itemNode : itemNodes) {
                String value = XMLUtility.parseForString(itemNode, "Value").trim();
                String text = XMLUtility.parseForString(itemNode, "Text").trim();
                dictResult.put(value,text);
            }
            return dictResult;
        }
        else {
            return null;
        }
    }
    private Map<String,String> getApiDataSource(Node apiDataSourceNode) throws XPathExpressionException, IllegalAccessException, InstantiationException, Build4DGenerallyException {
        Map<String,String> apiResult=new HashMap<String, String>();
        //String key=XMLUtil.getAttribute(apiDataSourceNode,"Key");
        Node fullCalssNode=XMLUtility.parseForNode(apiDataSourceNode,"FullClassName");
        String FullClassName=fullCalssNode.getTextContent().trim();
        if(!FullClassName.equals("")) {
            IVTDataSourceAPI ivtDataSourceAPI = (IVTDataSourceAPI) ClassUtility.loadClass(FullClassName).newInstance();
            apiResult = ivtDataSourceAPI.getDataSource(apiDataSourceNode);
            /*if (apiResult != null && apiResult.size() > 0) {
                Map<String, Object> singleRecord = apiResult.get(0);
                if (!singleRecord.containsKey("Value")) {
                    throw new SSSGenerallyException(0, "VTConvertDataSource》ApiDataSource[@Key='" + key + "']节点解析的数据源不包含Value字段");
                }
                if (!singleRecord.containsKey("Text")) {
                    throw new SSSGenerallyException(0, "VTConvertDataSource》ApiDataSource[@Key='" + key + "']节点解析的数据源不包含Text字段");
                }
            }*/
            return apiResult;
        }
        return null;
    }
    public Map<String,Map<String,String>> getVTDataSource(Document document, Node exportNode, Node vtDataSourceNode) throws Build4DGenerallyException, XPathExpressionException, InstantiationException, IllegalAccessException {
        Map<String,Map<String,String>> result=new HashMap<String, Map<String, String>>();
        NodeList dataSources=vtDataSourceNode.getChildNodes();
        for(int i=0;i<dataSources.getLength();i++){
            Node vtDataSource=dataSources.item(i);
            if(vtDataSource.getNodeName().equals("SQLDataSource")){
                String key=XMLUtility.getAttribute(vtDataSource,"Key");
                Map<String,String> sqlDataSource=this.getSQLDataSource(vtDataSource);
                if(sqlDataSource!=null) {
                    result.put(key, sqlDataSource);
                }
            }
            else if(vtDataSource.getNodeName().equals("DictionaryDataSource")){
                String key=XMLUtility.getAttribute(vtDataSource,"Key");
                Map<String,String> dictDataSource=this.getDictionaryDataSource(vtDataSource);
                if(dictDataSource!=null) {
                    result.put(key, dictDataSource);
                }
            }
            else if(vtDataSource.getNodeName().equals("ApiDataSource")){
                String key=XMLUtility.getAttribute(vtDataSource,"Key");
                Map<String,String> apiDataSource=this.getApiDataSource(vtDataSource);
                if(apiDataSource!=null) {
                    result.put(key, apiDataSource);
                }
            }
        }
        return result;
    }
}
