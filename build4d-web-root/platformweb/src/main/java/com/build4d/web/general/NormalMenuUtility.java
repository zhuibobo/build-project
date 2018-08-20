package com.build4d.web.general;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.PathUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.base.tools.common.list.IListWhereCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.web.general.model.NormalMenuVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NormalMenuUtility {

    public static String ProjectMenuPath= File.separator+"Views"+File.separator+"Project"+File.separator+"Config"+File.separator+"MenusConfig.xml";

    PathUtility pathUtility;

    public void setPathUtility(PathUtility _pathUtility) {
        pathUtility = _pathUtility;
    }

    public List<NormalMenuVo> getMenus(String _xmlPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, Build4DGenerallyException {
        String xmlPath= pathUtility.getWebInfPath()+ _xmlPath;
        List<NormalMenuVo> builderManageMenuVoList=new ArrayList<>();
        Document document=XMLUtility.parseForDoc(xmlPath);
        List<Node> rootNodes=XMLUtility.parseForNodeList(document,"/BuilderManageMenuRoot/Menu");

        NormalMenuVo rootVo=new NormalMenuVo("0","","Root","","","","Root/");
        addtoList(builderManageMenuVoList,rootVo);
        //builderManageMenuVoList.add(rootVo);
        for (Node rootNode : rootNodes) {
            NormalMenuVo topVo=nodeToVo(rootNode,rootVo);
            List<Node> childItems=XMLUtility.parseForNodeList(rootNode,"Items/Menu");
            if(childItems!=null&&childItems.size()>0){
                //List<BuilderMenuVo> l2list=new ArrayList<>();
                for (Node childItem : childItems) {
                    NormalMenuVo childVo=nodeToVo(childItem,topVo);
                    addtoList(builderManageMenuVoList,childVo);
                    List<Node> childItems_l2=XMLUtility.parseForNodeList(childItem,"Items/Menu");
                    if(childItems_l2!=null&&childItems_l2.size()>0){
                        for (Node childItem_l2 : childItems_l2) {
                            NormalMenuVo childVo_l2=nodeToVo(childItem_l2,childVo);
                            addtoList(builderManageMenuVoList,childVo_l2);
                        }
                    }
                    //builderManageMenuVoList.add(childVo);
                    //l2list.add(childVo);
                }
                //rootVo.setItems(l2list);
            }
            addtoList(builderManageMenuVoList,topVo);
            //builderManageMenuVoList.add(topVo);
        }
        return builderManageMenuVoList;
    }

    private void addtoList(List<NormalMenuVo> builderManageMenuVoList, final NormalMenuVo vo) throws Build4DGenerallyException {
        if(ListUtility.Exist(builderManageMenuVoList, new IListWhereCondition<NormalMenuVo>() {
            @Override
            public boolean Condition(NormalMenuVo item) {
                return item.getName().equals(vo.getName());
            }
        })){
           throw new Build4DGenerallyException("菜单的Name必须唯一["+vo.getName()+"]");
        }
        else
        {
            builderManageMenuVoList.add(vo);
        }
    }

    private NormalMenuVo nodeToVo(Node node, NormalMenuVo parentMenuVo) {
        String name = XMLUtility.getAttribute(node, "Name");
        String parentName = "";
        String parentText = "";

        parentName = parentMenuVo.getName();
        parentText = parentMenuVo.getText();

        String text = XMLUtility.getAttribute(node, "Text");
        String iconType = XMLUtility.getAttribute(node, "IconType");
        String url = XMLUtility.getAttribute(node, "Url");
        String openType = XMLUtility.getAttribute(node, "OpenType");
        return new NormalMenuVo(name, parentName, text, iconType, url, openType, parentText + "/" + text);
    }
}
