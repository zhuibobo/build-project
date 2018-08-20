package com.build4d.platform.export.excel.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.ClassUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.excel.IValueFormater;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public class FormaterBuilder {
    public Map<String,IValueFormater> getFormaterInstances(Document document, Node formatersNode) throws XPathExpressionException, Build4DGenerallyException, IllegalAccessException, InstantiationException {
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
