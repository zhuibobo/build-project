package com.build4d.web.project.controller.system;

import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.constructionproject.vo.RegisterVo;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb, liwl
 * @Date: 2018/4/27
 * @Description:
 * @Version 1.0.0
 */
@Controller
@RequestMapping("project/system")
public class OrganController {

    @Autowired
    IProOrganService proOrganService;

    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @RequestMapping(value = "organ/organlist", method = RequestMethod.GET)
    public ModelAndView organList() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Organ/OrganList");
        return modelAndView;
    }

    @RequestMapping(value = "organ/getorganlistdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getOrganListData(Integer page_size,Integer page_num,String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ProOrganEntity> proOrganPageInfo=proOrganService.getPageFoOrganMange(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }

    @RequestMapping(value = "organ/organeidt", method = RequestMethod.GET)
    public ModelAndView organEdit(Integer orgSid) {
        ModelAndView modelAndView;
        if(B4DSessionUtility.getSession().isFullPriv()){
            modelAndView=new ModelAndView("Project/System/Organ/OrganEdit");
        }
        else
        {
            modelAndView=new ModelAndView("Project/System/Organ/OrganEdit4Build");
        }

        ProOrganEntity proOrgan=null;
        if(orgSid==null||orgSid==0){
            orgSid=proOrganService.getNextId();
            proOrgan=new ProOrganEntity();
            proOrgan.setOrgStatus(1);
        }
        else
        {
            proOrgan=proOrganService.getByPrimaryKey(orgSid);
        }
        modelAndView.addObject("proOrgan",proOrgan);
        modelAndView.addObject("orgSid",orgSid);
        return modelAndView;
    }

    @RequestMapping(value = "organ/saveorganeidt", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveOrgan(@RequestBody ProOrganEntity proOrgan) {
        if(proOrganService.getByPrimaryKey(proOrgan.getOrgSid())==null) {
            proOrgan.setCreatreOrgId(B4DSessionUtility.getSession().getOrganId());
        }
        proOrganService.saveBySelective(proOrgan.getOrgSid(), proOrgan);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "organ/delorgan")
    @ResponseBody
    public Build4DResponseVo delOrgan(int orgSid) {
        proOrganService.deleteByKey(orgSid);
        return Build4DResponseVo.deleteSuccess();
    }

    @RequestMapping(value = "organ/register", method = RequestMethod.GET)
    public ModelAndView Register(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/System/Organ/Register");
        RegisterVo vo=new RegisterVo();

        List<ProOrganEntity> orgData=proOrganService.selectEnabledOrgs("档案管理单位");
        modelAndView.addObject("orgData", JsonUtility.toObjectString(orgData));
        modelAndView.addObject("dataEnties",JsonUtility.toObjectString(vo));
        return modelAndView;
    }

    @RequestMapping(value = "organ/registerstatement", method = RequestMethod.GET)
    public ModelAndView RegisterStatement(Integer sid) throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/System/Organ/RegisterStatement");
        ArolEngBaseInfoEntity engBaseInfoEntity=arolEngBaseInfoService.selectEngOrgAndCodeByPrimaryKey(sid);
        ProOrganEntity organEntity=proOrganService.getByPrimaryKey(engBaseInfoEntity.getRecOrgSid());
        modelAndView.addObject("archOrgEntity",JsonUtility.toObjectString(organEntity));
        modelAndView.addObject("engBaseInfoEntity",JsonUtility.toObjectString(engBaseInfoEntity));
        return modelAndView;
    }

    @RequestMapping(value = "organ/saveregister", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveRegister(@RequestBody RegisterVo entity) throws Exception {
        Integer engSid=proOrganService.RegisterOrgAndProject(entity);
        return Build4DResponseVo.saveSuccess(engSid);
    }

    @RequestMapping(value = "organ/existOrgan")
    @ResponseBody
    public Build4DResponseVo existOrgan(Integer orgSid,String orgName,String orgCode) throws Exception {
        boolean r=proOrganService.existOrgan(orgSid,orgName,orgCode);
        return Build4DResponseVo.success("",r);
    }

    @RequestMapping(value = "organ/organlist4build", method = RequestMethod.GET)
    public ModelAndView organList4Build() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Organ/OrganList4Build");
        return modelAndView;
    }

    @RequestMapping(value = "organ/getorganlistdata4build", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getOrganListData4Build(Integer page_size,Integer page_num,String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        searchMap.put("creatreOrgId",B4DSessionUtility.getSession().getOrganId());
        PageInfo<ProOrganEntity> proOrganPageInfo=proOrganService.getPageFoOrganMange(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }
}
