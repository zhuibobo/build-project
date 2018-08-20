package com.build4d.platform.export.general.config;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class ConfigManager {
    //private Document document;
    public String ValidateSuccessMsg="Sucess";
    public String ValidateErrorRepeatId="文档中的Export节点出现重复的ID";
    public String ValidateErrorNotId="文档中的Export节点中不存在该ID";

    public Document LoadDocument(String filePath) throws ParserConfigurationException, SAXException, IOException {
        Document document = XMLUtility.parseForDoc(filePath);
        return document;
    }

    private String validateDocuemnt(Document document,String exportId) throws XPathExpressionException, Build4DGenerallyException {
        List<Node> exportNodes=XMLUtility.parseForNodeList(document,"/Config/Export[@Id='"+exportId+"']");
        String msg=ValidateSuccessMsg;
        if(exportNodes!=null){
            if(exportNodes.size()==0){
                msg= ValidateErrorNotId+":"+exportId;
            }
            else if(exportNodes.size()>1)
            {
                msg= ValidateErrorRepeatId+":"+exportId;
            }
        }
        else
        {
            msg= ValidateErrorNotId+":"+exportId;
        }
        if(msg.equals(ValidateSuccessMsg)) {
            return ValidateSuccessMsg;
        }
        else
        {
            throw new Build4DGenerallyException(0,msg);
        }
    }

    public Node getExportNode(Document document,String exportId) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, Build4DGenerallyException {
        if(validateDocuemnt(document,exportId).equals(ValidateSuccessMsg)){
            List<Node> exportNodes=XMLUtility.parseForNodeList(document,"/Config/Export[@Id='"+exportId+"']");
            return exportNodes.get(0);
        }
        return null;
    }

    public Node getExportNode(String filePath,String exportId) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, Build4DGenerallyException {
        Document doc=LoadDocument(filePath);
        if(validateDocuemnt(doc,exportId).equals(ValidateSuccessMsg)){
            List<Node> exportNodes=XMLUtility.parseForNodeList(doc,"/Config/Export[@Id='"+exportId+"']");
            return exportNodes.get(0);
        }
        return null;
    }
}
