package com.build4d.web.project.controller.project;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.InetAddressUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.general.QRCodeUtility;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IArolProjBaseInfoService;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ArolProjBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liwl
 * Date: 2018-05-10
 */
@Controller
@RequestMapping("project")
public class ProjectController {
    @Autowired
    IArolProjBaseInfoService arolProjBaseInfoService;

    @Autowired
    IProOrganService proOrganService;

    //工程信息
    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @RequestMapping(value = "project/projectlist", method = RequestMethod.GET)
    public ModelAndView ProjectList() { 
        ModelAndView modelAndView=new ModelAndView("Project/Project/Project/ProjectList");
        B4DSession session= B4DSessionUtility.getSession();
        modelAndView.addObject("currOrgCode",session.getOrganCode());
        return modelAndView;
    }


    @RequestMapping(value = "project/getprojectlistdata", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo getProjectListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolProjBaseInfoEntity> proUserList=arolProjBaseInfoService.getPage(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proUserList);
    }

    @RequestMapping(value = "project/projectdetail", method = RequestMethod.GET)
    public ModelAndView projectDetail(Integer projectSid, HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Project/ProjectDetail");
        ArolProjBaseInfoEntity proBase=null;
        if(projectSid==null||projectSid==0){
            proBase=new ArolProjBaseInfoEntity();
            B4DSession session=B4DSessionUtility.getSession();
            proBase.setConOrgSid(Integer.valueOf(session.getOrganId()));
            proBase.setOrgNoCode(session.getOrganCode());
        }
        else
        {
            proBase=arolProjBaseInfoService.getByPrimaryKey(projectSid);
        }
        List<ProOrganEntity> orgData=proOrganService.selectEnabledOrgs(null);
        modelAndView.addObject("orgData", JsonUtility.toObjectString(orgData));
        modelAndView.addObject("dataEnties",JsonUtility.toObjectString(proBase));
        return modelAndView;
    }

    @RequestMapping(value = "project/saveprojectdetail", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveProjectDetail(@RequestBody ArolProjBaseInfoEntity proBase) {
        if(proBase.getSid()==null)
            proBase.setSid(arolProjBaseInfoService.getNextId());
        arolProjBaseInfoService.saveBySelective(proBase.getSid(), proBase);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "project/delproject")
    @ResponseBody
    public Build4DResponseVo delProject(int projectSid) {
        arolProjBaseInfoService.deleteByKey(projectSid);
        return Build4DResponseVo.deleteSuccess();
    }

    @RequestMapping(value = "project/viewprojectqrcode", method = RequestMethod.GET)
    public ModelAndView viewProjectQRCode(String projectId,HttpServletRequest request) throws UnknownHostException {
        String fullUrl= QRCodeUtility.buildQRCodeUrl(request,"/project/project/viewprojectforappv1.do?projectId="+projectId);
        ModelAndView modelAndView=new ModelAndView("Project/Project/Project/ViewProjectQRCode");
        modelAndView.addObject("url",fullUrl);
        return modelAndView;
    }


    @RequestMapping(value = "project/viewprojectforappv1", method = RequestMethod.GET)
    public ModelAndView viewProjectForAppV1(int projectId,HttpServletRequest request) throws UnknownHostException {

        ModelAndView modelAndView=new ModelAndView("Project/Project/Project/ViewProjectForAppV1");
        ArolProjBaseInfoEntity arolProjBaseInfoEntity=arolProjBaseInfoService.getByPrimaryKey(projectId);
        ArolEngBaseInfoEntity engBaseInfoEntity=arolEngBaseInfoService.getByPrimaryKey(projectId);
       /* modelAndView.addObject("ProEnt",arolProjBaseInfoEntity);*/
        modelAndView.addObject("EngEnt",engBaseInfoEntity);
        return modelAndView;
    }
    /*@RequestMapping(value = "project/exists")
    @ResponseBody
    public Build4DResponseVo exists(Integer projectSid,String name) {
        boolean r= arolProjBaseInfoService.existProject(projectSid,name);
        return Build4DResponseVo.success("",r);
    }*/
}
