package com.build4d.web.project.controller.project;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.constructionproject.vo.ArchiveVo;
import com.build4d.project.dbaccess.dbentities.*;
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
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liwl
 * Date: 2018-06-10
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("project/engineering/archive")
public class EngineeringArchiveController {
    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @Autowired
    IProOrganService proOrganService;

    @Autowired
    IProEngConfMateService proEngConfMateService;

    @Autowired
    IArolArchInfoService arolArchInfoService;

    @Autowired
    IArolFileInfoService arolFileInfoService;

    //案卷的详细页面
    @RequestMapping(value = "archdetail", method = RequestMethod.GET)
    public ModelAndView archDetail(int sid) throws IOException, ParseException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/Archive/ArchDetail");
        ArolArchInfoEntity arolArchInfoEntity=arolArchInfoService.getByPrimaryKey(sid);
        modelAndView.addObject("arolArchInfoEntity", JsonUtility.toObjectString(arolArchInfoEntity));
        return modelAndView;
    }

    //某个工程案卷的列表页面
    @RequestMapping(value = "archlistbyeng", method = RequestMethod.GET)
    public ModelAndView archListByEng(int sid) throws IOException, ParseException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/Archive/ArchListByEng");
        modelAndView.addObject("sid",sid);
        B4DSession session= B4DSessionUtility.getSession();
        ProOrganEntity organEntity=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        ArolEngBaseInfoEntity engEntity=arolEngBaseInfoService.selectEngOrgAndCodeByPrimaryKey(sid);
        modelAndView.addObject("engBaseEntity", JsonUtility.toObjectString(engEntity));
        modelAndView.addObject("organEntity", JsonUtility.toObjectString(organEntity));
        modelAndView.addObject("currUser",JsonUtility.toObjectString(session));
        return modelAndView;
    }

    //案卷管理列表页面
    @RequestMapping(value = "archlist", method = RequestMethod.GET)
    public ModelAndView archList(HttpServletRequest request) throws IOException, ParseException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/Archive/ArchList");
        B4DSession session= B4DSessionUtility.getSession();
        ProOrganEntity organEntity=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("organEntity", JsonUtility.toObjectString(organEntity));
        return modelAndView;
    }

    //获取关联工程的案卷列表数据
    @RequestMapping(value = "getarchivelistjoinengdata")
    @ResponseBody
    public Build4DResponseVo getArchiveListJoinEngData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolArchInfoEntity> archEntities=arolArchInfoService.searchArchJoinEngByMap(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",archEntities);
    }

    //整理组卷页面
    @RequestMapping(value = "buildarchinfo", method = RequestMethod.GET)
    public ModelAndView buildArchInfo(int sid) throws IOException, ParseException {
        ModelAndView modelAndView=new ModelAndView("Project/Project/Engineering/Archive/BuildArchInfo");
        modelAndView.addObject("sid",sid);
        B4DSession session= B4DSessionUtility.getSession();
        ProOrganEntity organEntity=proOrganService.getByPrimaryKey(Integer.parseInt(session.getOrganId()));
        ArolEngBaseInfoEntity engEntity=arolEngBaseInfoService.selectEngOrgAndCodeByPrimaryKey(sid);
        modelAndView.addObject("engBaseEntity", JsonUtility.toObjectString(engEntity));
        modelAndView.addObject("organEntity", JsonUtility.toObjectString(organEntity));
        modelAndView.addObject("currUser",JsonUtility.toObjectString(session));
        return modelAndView;
    }

    //获取案卷列表数据
    @RequestMapping(value = "getarchivelistdata")
    @ResponseBody
    public Build4DResponseVo getArchiveListData(String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        List<ArolArchInfoEntity> archEntities=arolArchInfoService.searchByMap(searchMap);
        return Build4DResponseVo.success("获取成功",archEntities);
    }

    //按分页获取案卷列表
    @RequestMapping(value = "getarchivelistdatabypage")
    @ResponseBody
    public Build4DResponseVo getArchiveListDataByPage(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ArolArchInfoEntity> archEntities=arolArchInfoService.getPage(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",archEntities);
    }

    //保存单个案卷信息
    @RequestMapping(value = "savesinglearchivedata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveSingleArchiveData(@RequestBody ArolArchInfoEntity archEntity) {
        B4DSession session= B4DSessionUtility.getSession();
        Integer archSid=archEntity.getSid();
        if(archSid==null)
        {
            archSid=arolArchInfoService.getNextId();
            archEntity.setSid(archSid);
            archEntity.setStatus(0);
            archEntity.setCreatedBy(session.getUserName());
            archEntity.setCreatedDt(new Date());
            archEntity.setOrganSid(Integer.parseInt(session.getOrganId()));
        }
        archEntity.setUpdatedDt(new Date());
        archEntity.setUpdatedBy(session.getUserName());
        arolArchInfoService.saveBySelective(archEntity.getSid(),archEntity);
        return Build4DResponseVo.success("保存成功");
    }

    //单个案卷信息审核
    @RequestMapping(value = "singleArchCheck", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo singleArchCheck(@RequestBody ArolArchInfoEntity archEntity) {
        B4DSession session= B4DSessionUtility.getSession();
        archEntity.setCheckDate(new Date());
        archEntity.setCheckPerson(session.getUserName());
        if(archEntity.getStatus().equals(1))
            archEntity.setLockStatus("1");
        arolArchInfoService.saveBySelective(archEntity.getSid(),archEntity);
        return Build4DResponseVo.success("保存成功");
    }

    //组卷并保存案卷信息
    @RequestMapping(value = "savearchivedata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveArchiveData(@RequestBody ArchiveVo archiveVo) {
        String ids="";
        B4DSession session= B4DSessionUtility.getSession();
        List<ArolFileInfoEntity> selNodes=archiveVo.getFileInfoEntityList();
        ArolArchInfoEntity archEntity=archiveVo.getArchInfoEntity();
        for (ArolFileInfoEntity selNode : selNodes) {
            ids+=selNode.getProEngConfMateEntity().getMateSid()+",";
        }
        ids=ids.substring(0, ids.length()-1);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("engSid",archiveVo.getArchInfoEntity().getEngSid());
        map.put("matePSidList",ids);
        //map.put("isdirectory",0);
        List<ArolFileInfoEntity> fileInfoEntities= arolFileInfoService.searchByMap(map);

        Integer archSid=archEntity.getSid();
        if(archSid==null)
        {
            archSid=arolArchInfoService.getNextId();
            archEntity.setSid(archSid);
            archEntity.setStatus(0);
            archEntity.setOrganSid(Integer.parseInt(session.getOrganId()));
            arolArchInfoService.addSelective(archEntity);
        }
        archiveVo.setArchInfoEntity(archEntity);

        for (ArolFileInfoEntity fileInfoEntity : fileInfoEntities) {
            fileInfoEntity.setArchSid(archSid);
            arolFileInfoService.updateByKeySelective(fileInfoEntity);
        }
        archiveVo.setFileInfoEntityList(fileInfoEntities);
        return Build4DResponseVo.success("保存成功",archiveVo);
    }
}
