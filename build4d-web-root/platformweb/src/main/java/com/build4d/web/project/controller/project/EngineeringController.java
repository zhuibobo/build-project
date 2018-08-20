package com.build4d.web.project.controller.project;

import com.aliyuncs.exceptions.ClientException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.general.QRCodeUtility;
import com.build4d.project.constructionproject.general.SMSUtility;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.dbaccess.dbentities.*;
import com.build4d.web.general.model.Build4DResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.map.HashedMap;
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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liwl
 * Date: 2018-05-10
 */
@Controller
@RequestMapping("project")
public class EngineeringController {
    //项目信息
    @Autowired
    IArolProjBaseInfoService arolProjBaseInfoService;

    //工程信息
    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    //组织
    @Autowired
    IProOrganService proOrganService;

    @Autowired
    IProUserService proUserService;

    @Autowired
    IProUserRoleService proUserRoleService;

    @Autowired
    IProEngFlowLogService proEngFlowLogService;

    @Autowired
    IProEngConfMateService proEngConfMateService;

    @Autowired
    IArolEngOrgAndCodeService arolEngOrgAndCodeService;

    @RequestMapping(value = "engineering/engineeringlist", method = RequestMethod.GET)
    public ModelAndView engineeringList() {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/EngineeringList");
        B4DSession session= B4DSessionUtility.getSession();
        modelAndView.addObject("currOrgCode",session.getOrganCode());
        modelAndView.addObject("currOrgId",session.getOrganId());
        return modelAndView;
    }

    @RequestMapping(value = "engineering/engineeringlistfortrace", method = RequestMethod.GET)
    public ModelAndView EngineeringListForTrace() {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/EngineeringListForTrace");
        B4DSession session= B4DSessionUtility.getSession();
        modelAndView.addObject("currOrgCode",session.getOrganCode());
        modelAndView.addObject("currOrgId",session.getOrganId());
        return modelAndView;
    }
    @RequestMapping(value = "engineering/getengineeringlistdata", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo getEngineeringListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolEngBaseInfoEntity> proEngList=arolEngBaseInfoService.getPage(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proEngList);
    }

    @RequestMapping(value = "engineering/getengineeringbasedata")
    @ResponseBody
    public Build4DResponseVo getEngineeringBaseData(Integer sid) throws IOException, ParseException {
        ArolEngBaseInfoEntity engEntity = arolEngBaseInfoService.selectEngOrgAndCodeByPrimaryKey(sid);
        return Build4DResponseVo.success("获取成功",engEntity);
    }

    @RequestMapping(value = "engineering/engineeringdetail", method = RequestMethod.GET)
    public ModelAndView engineeringDetail(Integer sid, HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/EngineeringDetail");
        ArolEngBaseInfoEntity entity=null;
        String engType=request.getParameter("engType");
        B4DSession session=B4DSessionUtility.getSession();
        if(sid==null||sid==0){
            entity=new ArolEngBaseInfoEntity();
            entity.setStatus(0);
            entity.setEngZone("");
        }
        else
        {
            if(engType.equals("1"))
                entity=arolEngBaseInfoService.selectEngHouseSpecByPrimaryKey(sid);
            else
                entity=arolEngBaseInfoService.selectEngFacilitySpecByPrimaryKey(sid);
        }

        if(entity.getEngOrgAndCode()==null) {
            entity.setEngOrgAndCode(new ArolEngOrgAndCodeEntity());
            entity.getEngOrgAndCode().setLandUseOrgName(session.getOrganName());
            entity.getEngOrgAndCode().setLandUseOrgSid(Integer.parseInt(session.getOrganId()));
        }
        if(entity.getEngSpecInfo()==null) {
            entity.setEngSpecInfo(new ArolEngSpecInfoEntity());
        }
        if(engType.equals("1")  && entity.getEngHouseSpec()==null)
        {
            entity.setEngHouseSpec(new ArolEngHouseSpecEntity());
        }
        else if((engType.equals("2") || engType.equals("3")) && entity.getEngFacilitySpec()==null){
            entity.setEngFacilitySpec(new ArolEngFacilitySpecEntity());
        }

        Map<String,Object> map=new HashedMap<String,Object>();
        map.put("conOrgSid",session.getOrganId());
        List<ArolProjBaseInfoEntity> proBaseInfo=arolProjBaseInfoService.searchByMap(map);
        modelAndView.addObject("proBaseInfo", JsonUtility.toObjectString(proBaseInfo));
        List<ProOrganEntity> orgData=proOrganService.selectEnabledOrgs(null);
        modelAndView.addObject("orgData", JsonUtility.toObjectString(orgData));
        modelAndView.addObject("dataEnties",JsonUtility.toObjectString(entity));
        modelAndView.addObject("status",entity.getStatus());

        ProOrganEntity currOrgEnt=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("orgType",currOrgEnt.getOrgType());

        /*List<ProEngConfMateEntity> proEngConfMateEntities=proEngConfMateService.getListByEngSid(1);
        modelAndView.addObject("engConfMateJson", JsonUtility.toObjectString(proEngConfMateEntities));*/

        return modelAndView;
    }

    @RequestMapping(value = "engineering/saveengineeringdetail", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveProjectDetail(@RequestBody ArolEngBaseInfoEntity entity) {
        if(entity.getSid()==null) {
            int sid=arolEngBaseInfoService.getNextId();
            entity.setSid(sid);
        }
        Integer id=arolEngBaseInfoService.saveBySelective(entity.getSid(), entity);
        return Build4DResponseVo.saveSuccess(id);
    }

    @RequestMapping(value = "engineering/delengineering")
    @ResponseBody
    public Build4DResponseVo delProject(int sid) {
        arolEngBaseInfoService.deleteByKey(sid);
        return Build4DResponseVo.deleteSuccess();
    }


    //首页弹出列表页
    @RequestMapping(value = "engineering/listfordesktop", method = RequestMethod.GET)
    public ModelAndView engineeringListForDesktop() {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/EngineeringListForDesktop");
        B4DSession session= B4DSessionUtility.getSession();
        modelAndView.addObject("currOrgId",session.getOrganId());
        ProOrganEntity currOrgEnt=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("orgType",currOrgEnt.getOrgType());
        return modelAndView;
    }

    //注册登记后第一次审核时详细页
    @RequestMapping(value = "engineering/detailfor1st", method = RequestMethod.GET)
    public ModelAndView engineeringDetailFor1st(Integer sid, HttpServletRequest request) throws JsonProcessingException {
        ArolEngBaseInfoEntity entity=null;
        String engType=request.getParameter("engType");
        if(engType.equals("1"))
            entity=arolEngBaseInfoService.selectEngHouseSpecByPrimaryKey(sid);
        else
            entity=arolEngBaseInfoService.selectEngFacilitySpecByPrimaryKey(sid);

        if(entity.getEngOrgAndCode()==null) {
            entity.setEngOrgAndCode(new ArolEngOrgAndCodeEntity());
        }
        if(entity.getEngSpecInfo()==null) {
            entity.setEngSpecInfo(new ArolEngSpecInfoEntity());
        }
        if(engType.equals("1")  && entity.getEngHouseSpec()==null)
        {
            entity.setEngHouseSpec(new ArolEngHouseSpecEntity());
        }
        else if((engType.equals("2") || engType.equals("3")) && entity.getEngFacilitySpec()==null){
            entity.setEngFacilitySpec(new ArolEngFacilitySpecEntity());
        }

        //建设单位
        ProOrganEntity orgEntity=null;
        Integer orgId=entity.getProBaseInfo().getConOrgSid();
        if(orgId!=null && !orgId.equals(""))
            orgEntity=proOrganService.getByPrimaryKey(orgId);
        if(orgEntity==null)
            orgEntity=new ProOrganEntity();

        //接收单位
        String receiveOrgName="";
        ProOrganEntity receiveOrgEntity=proOrganService.getByPrimaryKey(entity.getRecOrgSid());
        if(receiveOrgEntity!=null && receiveOrgEntity.getOrgName()!=null)
            receiveOrgName=receiveOrgEntity.getOrgName();


        String url="Project/Project/Engineering/EngineeringDetailFor1st";
        ModelAndView modelAndView=new ModelAndView(url);
        modelAndView.addObject("orgEntity", JsonUtility.toObjectString(orgEntity));
        modelAndView.addObject("dataEnties",JsonUtility.toObjectString(entity));
        modelAndView.addObject("receiveOrgName",receiveOrgName);
        modelAndView.addObject("status",entity.getStatus());
        B4DSession session=B4DSessionUtility.getSession();
        ProOrganEntity currOrgEnt=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("orgType",currOrgEnt.getOrgType());
        return modelAndView;
    }


    ///描述：更新状态
    ///参数：isCheck 是否是审核操作
    ///参数：isPass 如果是审核操作，是否审核通过
    @RequestMapping(value = "engineering/updatestatus")
    @ResponseBody
    public Build4DResponseVo updateCheckStatus(Integer sid,Integer currStatus,Integer nextStatus,String opinion, HttpServletRequest request)  throws ClientException {
        ArolEngBaseInfoEntity engBaseInfoEntity=arolEngBaseInfoService.getByPrimaryKey(sid);
        Integer status=engBaseInfoEntity.getStatus();
        if(currStatus.equals(status))
        {
            status=nextStatus;
            Integer proSid= engBaseInfoEntity.getProjSid();
            ArolProjBaseInfoEntity projBaseInfoEntity=arolProjBaseInfoService.getByPrimaryKey(proSid);
            Integer orgSid=projBaseInfoEntity.getConOrgSid();
            ProOrganEntity organEntity= proOrganService.getByPrimaryKey(orgSid);
            String tel= organEntity.getOrgContMobile();
            if(status==200) //报建审核通过
            {
                organEntity.setOrgStatus(1);
                proOrganService.saveBySelective(organEntity.getOrgSid(),organEntity);

                Map<String,Object> m=new HashMap<String,Object>();
                m.put("userOrgId",organEntity.getOrgSid());
                List<ProUserEntity> userEntities=proUserService.searchByMap(m);
                for (ProUserEntity ent : userEntities) {
                    ent.setUserStatus(1);
                    proUserService.updateByKeySelective(ent);

                    //为用户设置相关的角色
                    int[] userIds={ent.getUserSid()};
                    proUserRoleService.bindUserToRole(userIds,1);
                }
            }

            //消息通知
            if(tel!=null && !tel.equals("")){
                if(status==200) {
                    //报建审核通过
                    SMSUtility.Send("SMS_138076152", tel);
                }else if(status==1700){
                    //档案已移交
                    SMSUtility.Send("SMS_138076151", tel);
                }
                else if(status==2300){
                    //审定发证
                    SMSUtility.Send("SMS_138076152", tel);
                }
            }

            //更新工程状态
            engBaseInfoEntity.setStatus(status);
            arolEngBaseInfoService.updateByKeySelective(engBaseInfoEntity);

            //流转日志
            Integer flowId=proEngFlowLogService.getNextId();
            ProEngFlowLogEntity flowLogEntity=new ProEngFlowLogEntity();
            flowLogEntity.setSid(flowId);
            flowLogEntity.setEngSid(sid);
            flowLogEntity.setOpStatus(status);
            flowLogEntity.setOpOpinion(opinion);
            flowLogEntity.setOrganType(organEntity.getOrgType());
            proEngFlowLogService.saveBySelective(flowId,flowLogEntity);

            return Build4DResponseVo.saveSuccess(status);
        }
        else
        {
            return Build4DResponseVo.error("传入的当前状态与实际状态不一致, 保存失败!");
        }
    }


    @RequestMapping(value = "engineering/viewengqrcode", method = RequestMethod.GET)
    public ModelAndView viewProjectQRCode(Integer id,HttpServletRequest request) throws UnknownHostException {
        ArolEngBaseInfoEntity entity=arolEngBaseInfoService.getByPrimaryKey(id);
        String fullUrl="";
        Boolean isBuild=false;
        if(entity.getStatus()>=800 && entity.getStatus()!=900 ) {
            fullUrl = QRCodeUtility.buildQRCodeUrl(request, "/project/engineering/engineeringdetail.do?op=view&sid=" + id + "&engType=" + entity.getEngType());
            isBuild=true;
        }
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/ViewEngQRCode");
        modelAndView.addObject("url",fullUrl);
        modelAndView.addObject("isBuild",isBuild);
        return modelAndView;
    }

    @RequestMapping(value = "engineering/baidumap", method = RequestMethod.GET)
    public ModelAndView BaiduMap() {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/BaiduMap");
        return modelAndView;
    }

    @RequestMapping(value = "engineering/printtemp", method = RequestMethod.GET)
    public ModelAndView printTemp(int sid,HttpServletRequest request) throws UnknownHostException {
        ArolEngBaseInfoEntity entity=arolEngBaseInfoService.selectEngOrgAndCodeByPrimaryKey(sid);

        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/PrintTemp");
        modelAndView.addObject("entity",entity);
        String fullUrl="";
        fullUrl= QRCodeUtility.buildQRCodeUrl(request,"/project/project/viewprojectforappv1.do?projectId="+sid);
        modelAndView.addObject("url",fullUrl);
        return modelAndView;
    }
 
    @RequestMapping(value = "engineering/engprogress", method = RequestMethod.GET)
    public ModelAndView engProgress(int sid,HttpServletRequest request) throws UnknownHostException, JsonProcessingException {
        ArolEngBaseInfoEntity entity=arolEngBaseInfoService.getByPrimaryKey(sid);
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/EngineeringProgress");

        Map<String,Object> m=new HashMap<String,Object>();
        m.put("engSid",sid);
        List<ProEngFlowLogEntity> engFlowLogEntities=proEngFlowLogService.searchByMap(m);
        modelAndView.addObject("LogEntities",JsonUtility.toObjectString(engFlowLogEntities));
        return modelAndView;
    }

    @RequestMapping(value = "engineering/setinstructors")
    @ResponseBody
    public Build4DResponseVo setInstructors(Integer engSid,Integer userId,String userName) {
        ArolEngOrgAndCodeEntity orgAndCodeEntity=new ArolEngOrgAndCodeEntity();
        orgAndCodeEntity.setEngSid(engSid);
        orgAndCodeEntity.setInstructor(userName);
        orgAndCodeEntity.setInstructorSid(userId);
        arolEngOrgAndCodeService.updateByKeySelective(orgAndCodeEntity);
        return Build4DResponseVo.saveSuccess();
    }
}