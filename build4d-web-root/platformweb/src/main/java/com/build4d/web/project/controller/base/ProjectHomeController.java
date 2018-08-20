package com.build4d.web.project.controller.base;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.list.IListWhereCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.project.constructionproject.service.IProRolePrivService;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;
import com.build4d.web.general.NormalMenuUtility;
import com.build4d.web.general.model.NormalMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2018/4/26
 * @Description:
 * @Version 1.0.0
 */
@Controller
@RequestMapping("project")
public class ProjectHomeController {

    @Autowired
    private NormalMenuUtility normalMenuUtility;

    @Autowired
    private IProRolePrivService proRolePrivService;

    @RequestMapping(value = "frame", method = RequestMethod.GET)
    public ModelAndView projectFrame() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, Build4DGenerallyException {
        ModelAndView modelAndView=new ModelAndView("Project/Frame");
        List<NormalMenuVo> builderMenuVos=normalMenuUtility.getMenus(NormalMenuUtility.ProjectMenuPath);
        List<NormalMenuVo> privMenuVos=new ArrayList<>();
        if(!B4DSessionUtility.getSession().isFullPriv()){
            if(B4DSessionUtility.getSession().getRoleKeys()==null||B4DSessionUtility.getSession().getRoleKeys().size()==0){
                modelAndView=new ModelAndView("Project/FrameNotPriv");
                return modelAndView;
            }
            List<Integer> roleKey= ListUtility.ListStringToInt(B4DSessionUtility.getSession().getRoleKeys());
            List<ProRolePrivEntity> proRolePrivEntities=proRolePrivService.getRolesMenuPriv(roleKey);
            for (final NormalMenuVo normalMenuVo : builderMenuVos) {
                boolean hasPriv=ListUtility.Exist(proRolePrivEntities, new IListWhereCondition<ProRolePrivEntity>() {
                    @Override
                    public boolean Condition(ProRolePrivEntity item) {
                        return item.getRoprObjId().equals(normalMenuVo.getName());
                    }
                });
                if(hasPriv){
                    privMenuVos.add(normalMenuVo);
                }
            }
        }
        else {
            privMenuVos=builderMenuVos;
        }
        B4DSession session=B4DSessionUtility.getSession();
        String menuJson= JsonUtility.toObjectString(privMenuVos);
        modelAndView.addObject("currUserEntity",JsonUtility.toObjectString(session));
        modelAndView.addObject("menuJson",menuJson);
        return modelAndView;
    }

}
