package com.build4d.web.project.controller.system;

import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.IProRolePrivService;
import com.build4d.project.constructionproject.service.IProRoleService;
import com.build4d.project.constructionproject.service.impl.ProDictionaryService;
import com.build4d.project.constructionproject.service.impl.ProRoleService;
import com.build4d.project.dbaccess.dbentities.ProDictionaryEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2018/4/27
 * @Description:
 * @Version 1.0.0
 */
@Controller
@RequestMapping("project/system")
public class RoleController {

    @Autowired
    IProRoleService proRoleService;

    @Autowired
    IProRolePrivService proRolePrivService;

    @RequestMapping(value = "role/list", method = RequestMethod.GET)
    public ModelAndView List() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Role/RoleList");
        return modelAndView;
    }


    @RequestMapping(value = "role/detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer sId,String op) {
        ModelAndView modelAndView=new ModelAndView("Project/System/Role/RoleEdit");
        ProRoleEntity entity=null;
        if(sId==null||sId==0){
            sId=proRoleService.getNextId();
            entity=new ProRoleEntity();
            entity.setRoleOrder(1);
        }
        else
        {
            entity=proRoleService.getByPrimaryKey(sId);
        }
        modelAndView.addObject("entity",entity);
        modelAndView.addObject("sId",sId);
        modelAndView.addObject("op",op);
        return modelAndView;
    }

    @RequestMapping(value = "role/listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListData(Integer page_size,Integer page_num,String search_condition) throws IOException, ParseException {
        //Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ProRoleEntity> proOrganPageInfo=proRoleService.getPage(page_num,page_size);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }

    @RequestMapping(value = "role/saveedit", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEdit(@RequestBody ProRoleEntity proRoleEntity) {
        proRoleService.saveBySelective(proRoleEntity.getRoleSid(), proRoleEntity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "role/rolemember", method = RequestMethod.GET)
    public ModelAndView roleMember(Integer roleId,String op) {
        ModelAndView modelAndView=new ModelAndView("Project/System/Role/RoleMemberEdit");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }

    @RequestMapping(value = "role/rolepriv", method = RequestMethod.GET)
    public ModelAndView rolePriv(Integer roleId,String op) {
        ModelAndView modelAndView=new ModelAndView("Project/System/Role/RolePrivEdit");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }

    @RequestMapping(value = "role/saverolemenupriv", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveRoleMenuPriv(Integer roleId,String menuIds) {
        String[] menuIdArray=menuIds.split(";");
        proRolePrivService.overSaveMenuRolePriv(roleId,menuIdArray);
        return Build4DResponseVo.opSuccess();
        /*ModelAndView modelAndView=new ModelAndView("Project/System/Role/RolePrivEdit");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;*/
    }
}
