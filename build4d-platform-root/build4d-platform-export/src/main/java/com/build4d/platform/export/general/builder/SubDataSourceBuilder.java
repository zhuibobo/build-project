package com.build4d.platform.export.general.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.ClassUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.ISubDataSource;
import com.build4d.platform.export.general.vo.ExportVo;
import com.build4d.platform.export.general.vo.SubDataSourceVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public class SubDataSourceBuilder {

    public Map<String, SubDataSourceVo> getSubDataSource(ExportVo exportVo, Document document, Node exportNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
        Node subDataSourceNode = XMLUtility.parseForNode(exportNode, "SubDataSource");
        if(subDataSourceNode==null){
            return null;
        }
        Map<String, SubDataSourceVo> result = new HashMap<String, SubDataSourceVo>();
        if (subDataSourceNode.getChildNodes().getLength() > 0) {
            for (int i = 0; i < subDataSourceNode.getChildNodes().getLength(); i++) {
                Node datasourceNode = subDataSourceNode.getChildNodes().item(i);
                String name = "";
                SubDataSourceVo subDataSourceVo = null;
                if (datasourceNode.getNodeName().equals("SQLDataSource")) {
                    name = XMLUtility.getAttribute(datasourceNode, "Name");
                    boolean enable = XMLUtility.getAttribute(datasourceNode, "Enable").equals("True") ? true : false;
                    if (!enable)
                        continue;
                    String fkKey = XMLUtility.getAttribute(datasourceNode, "FKKey");
                    String key = XMLUtility.getAttribute(datasourceNode, "Key");
                    String sql = XMLUtility.parseForNode(datasourceNode, "SQL").getTextContent().trim();
                    subDataSourceVo = new SubDataSourceVo();
                    subDataSourceVo.setName(name);
                    subDataSourceVo.setKey(key);
                    subDataSourceVo.setEnable(enable);
                    subDataSourceVo.setFkKey(fkKey);
                    List<String> sqlParas=new ArrayList<String>();
                    if (!sql.equals("")) {
                        if(sql.indexOf("{")>0) {
                            List<Map> mainDataSource=exportVo.getMainDataSourceVo().getDataSource();
                            if (mainDataSource!= null && mainDataSource.size() > 0) {
                                String pattern = "\\{\\S*}";
                                Pattern r = Pattern.compile(pattern);
                                Matcher m = r.matcher(sql);
                                while (m.find()) {
                                    String placeholder = m.group();
                                    String[] conditionPara = placeholder.replace("{", "").replace("}", "").split("\\.");

                                    if (conditionPara[0].toLowerCase().equals("main")) {
                                        String mainFieldName = conditionPara[1];
                                        String opera=conditionPara[2];
                                        if (!mainDataSource.get(0).containsKey(mainFieldName)) {
                                            String errorMsg="在主数据源中找不到字段"+mainFieldName;
                                            throw new Build4DGenerallyException(0,errorMsg);
                                        }
                                        if(opera.toLowerCase().equals("toinlist")) {
                                            String tempStr = "";
                                            for (Map map : mainDataSource) {
                                                tempStr += "?,";
                                                sqlParas.add(map.get(mainFieldName).toString());
                                            }
                                            tempStr=tempStr.substring(0,tempStr.length()-1);
                                            sql=sql.replace(placeholder,tempStr);
                                        }
                                    }
                                }
                                //todo
                                //subDataSourceVo.setDataSource(HibernateStaticUtil.findByMultiRecordMapSQLString(sql,sqlParas.toArray()));
                            }
                        }
                        else
                        {
                            //todo
                            //subDataSourceVo.setDataSource(HibernateStaticUtil.findByMultiRecordMapSQLString(sql));
                        }
                    }
                } else if (datasourceNode.getNodeName().equals("APIDataSource")) {
                    name = XMLUtility.getAttribute(datasourceNode, "Name");
                    boolean enable = XMLUtility.getAttribute(datasourceNode, "Enable").equals("True") ? true : false;
                    if (!enable)
                        continue;
                    String fkKey = XMLUtility.getAttribute(datasourceNode, "FKKey");
                    String key = XMLUtility.getAttribute(datasourceNode, "Key");
                    String fullClassName = XMLUtility.parseForNode(datasourceNode, "FullClassName").getTextContent().trim();
                    subDataSourceVo = new SubDataSourceVo();
                    subDataSourceVo.setName(name);
                    subDataSourceVo.setKey(key);
                    subDataSourceVo.setEnable(enable);
                    subDataSourceVo.setFkKey(fkKey);
                    if (!fullClassName.equals("")) {
                        ISubDataSource subDataSourceApi = (ISubDataSource) ClassUtility.loadClass(fullClassName).newInstance();
                        subDataSourceVo.setDataSource(subDataSourceApi.getDataSource(exportVo, document, exportNode, datasourceNode));
                    }
                }
                if (!name.equals("")) {
                    if (!result.containsKey(name)) {
                        result.put(name, subDataSourceVo);
                    } else {
                        throw new Build4DGenerallyException(0, "SubDataSource中存在name相同的数据源节点");
                    }
                }
            }
        }
        return result;
    }
}
