package com.build4d.web.project.controller.system;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.list.IListWhereCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.project.constructionproject.service.IProRolePrivService;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;
import com.build4d.web.general.NormalMenuUtility;
import com.build4d.web.general.model.Build4DResponseVo;
import com.build4d.web.general.model.NormalMenuVo;
import com.build4d.web.model.IViewTreeNodeModel;
import com.build4d.web.model.ZTreeNodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("project/system")
public class MenuController {

    @Autowired
    private NormalMenuUtility builderMenuUtility;

    @Autowired
    private IProRolePrivService proRolePrivService;

    /*@RequestMapping(value = "menu/allmenu")
    public Build4DResponseVo allMenu() throws ParserConfigurationException, Build4DGenerallyException, SAXException, XPathExpressionException, IOException {
        List<NormalMenuVo> builderMenuVos=builderMenuUtility.getMenus(NormalMenuUtility.ProjectMenuPath);
        return Build4DResponseVo.success("",builderMenuVos);
    }

    @RequestMapping(value = "menu/getmenubypriv")
    public Build4DResponseVo getMenuByPriv() throws ParserConfigurationException, Build4DGenerallyException, SAXException, XPathExpressionException, IOException {
        List<NormalMenuVo> builderMenuVos=builderMenuUtility.getMenus(NormalMenuUtility.ProjectMenuPath);
        if(!B4DSessionUtility.getSSSSession().isFullPriv()){
            List<Integer> roleKey= ListUtility.ListStringToInt(B4DSessionUtility.getSSSSession().getRoleKeys());
            List<ProRolePrivEntity> proRolePrivEntities=proRolePrivService.getRolesMenuPriv(roleKey);
            for (NormalMenuVo normalMenuVo : builderMenuVos) {

            }
        }
        return Build4DResponseVo.success("",builderMenuVos);
    }*/

    @RequestMapping(value = "menu/allmenutoztreetitleincludeurl")
    @ResponseBody
    public Build4DResponseVo allMenuToIViewTreeTitleIncludeUrl(int roleId) throws ParserConfigurationException, Build4DGenerallyException, SAXException, XPathExpressionException, IOException {
        List<NormalMenuVo> builderMenuVos=builderMenuUtility.getMenus(NormalMenuUtility.ProjectMenuPath);
        List<ZTreeNodeModel> zTreeNodeModelList=new ArrayList<>();
        List<ProRolePrivEntity> proRolePrivEntities=proRolePrivService.getRoleMenuPriv(roleId);
        for (NormalMenuVo builderMenuVo : builderMenuVos) {
            final ZTreeNodeModel node=new ZTreeNodeModel();
            node.setId(builderMenuVo.getName());
            node.setTitle(builderMenuVo.getText()+"["+builderMenuVo.getUrl()+"]");
            node.setChildren(null);
            node.setDesc(builderMenuVo.getUrl());
            node.setParentId(builderMenuVo.getParentName());
            node.setOpen(true);
            boolean isChecked= ListUtility.Exist(proRolePrivEntities, new IListWhereCondition<ProRolePrivEntity>() {
                @Override
                public boolean Condition(ProRolePrivEntity item) {
                    return item.getRoprObjId().equals(node.getId());
                }
            });
            node.setChecked(isChecked);
            zTreeNodeModelList.add(node);
        }
        return Build4DResponseVo.success("",zTreeNodeModelList);
    }
}
