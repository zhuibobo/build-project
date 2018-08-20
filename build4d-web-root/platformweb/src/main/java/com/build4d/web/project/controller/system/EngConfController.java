package com.build4d.web.project.controller.system;

import com.build4d.project.constructionproject.service.IProEngConfMateService;
import com.build4d.project.constructionproject.service.IProEngConfTypeService;
import com.build4d.project.dbaccess.dbentities.ProEngConfMateEntity;
import com.build4d.project.dbaccess.dbentities.ProEngConfTypeEntity;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("project/system")
public class EngConfController {

    @Autowired
    IProEngConfMateService proEngConfMateService;

    @Autowired
    IProEngConfTypeService proEngConfTypeService;


    @RequestMapping(value = "engconf/list", method = RequestMethod.GET)
    public ModelAndView List() {
        ModelAndView modelAndView=new ModelAndView("Project/System/EngConf/EngConfTypeList");
        return modelAndView;
    }

    @RequestMapping(value = "engconf/detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer sId,String op) {
        ModelAndView modelAndView=new ModelAndView("Project/System/EngConf/EngConfTypeEdit");
        ProEngConfTypeEntity entity=null;
        if(sId==null||sId==0){
            sId=proEngConfTypeService.getNextId();
            entity=new ProEngConfTypeEntity();
            entity.setEngOrder(1);
        }
        else
        {
            entity=proEngConfTypeService.getByPrimaryKey(sId);
        }
        modelAndView.addObject("entity",entity);
        modelAndView.addObject("sId",sId);
        modelAndView.addObject("op",op);
        return modelAndView;
    }

    @RequestMapping(value = "engconf/listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        PageInfo<ProEngConfTypeEntity> proOrganPageInfo=proEngConfTypeService.getPage(page_num,page_size);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }

    @RequestMapping(value = "engconf/saveedit", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEdit(@RequestBody ProEngConfTypeEntity proRoleEntity) {
        proEngConfTypeService.saveBySelective(proRoleEntity.getEngSid(), proRoleEntity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "engconf/matedetail", method = RequestMethod.GET)
    public ModelAndView detail(Integer sId) {
        ModelAndView modelAndView=new ModelAndView("Project/System/EngConf/EngConfMateEdit");
        ProEngConfTypeEntity entity=proEngConfTypeService.getByPrimaryKey(sId);
        modelAndView.addObject("eng_entity",entity);
        modelAndView.addObject("sId",sId);
        return modelAndView;
    }

    @RequestMapping(value = "engconf/savemateedit", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEdit(@RequestBody ProEngConfMateEntity proEngConfMateEntity) {
        if(proEngConfMateEntity.getMateSid()==null){
            proEngConfMateEntity.setMateSid(proEngConfMateService.getNextId());
            proEngConfMateEntity.setMateCreateTime(new Date());
            proEngConfMateEntity.setMateType("电子材料");
        }
        proEngConfMateService.saveBySelective(proEngConfMateEntity.getMateSid(), proEngConfMateEntity);
        return Build4DResponseVo.saveSuccess(proEngConfMateEntity);
    }

    @RequestMapping(value = "engconf/listmatedata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListMateData(Integer engSid) throws IOException, ParseException {
        List<ProEngConfMateEntity> proEngConfMateEntities=proEngConfMateService.getListByEngSid(engSid);
        return Build4DResponseVo.success("获取成功",proEngConfMateEntities);
    }

    @RequestMapping(value = "engconf/delmatedata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo delMateData(Integer mateSid) throws IOException, ParseException {
        proEngConfMateService.deleteByKey(mateSid);
        return Build4DResponseVo.opSuccess();
    }
/*
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
        *//*ModelAndView modelAndView=new ModelAndView("Project/System/Role/RolePrivEdit");
        modelAndView.addObject("roleId",roleId);
        return modelAndView;*//*
    }*/
}
