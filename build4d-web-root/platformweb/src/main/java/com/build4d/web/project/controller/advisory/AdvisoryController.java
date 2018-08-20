package com.build4d.web.project.controller.advisory;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IProAdvisoryReplyService;
import com.build4d.project.constructionproject.service.IProAdvisoryService;
import com.build4d.project.constructionproject.service.impl.ProAdvisoryService;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryEntity;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryReplyEntity;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("project/advisory")
public class AdvisoryController {

    @Autowired
    IProAdvisoryService proAdvisoryService;

    @Autowired
    IProAdvisoryReplyService proAdvisoryReplyService;

    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView advisoryList() {
        ModelAndView modelAndView=new ModelAndView("Project/Advisory/AdvisoryList");
        return modelAndView;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail() throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/Advisory/AdvisoryEdit");
        B4DSession session= B4DSessionUtility.getSession();
        String organId=session.getOrganId();
        //organId="5";
        List<Map> aboutEntList=proAdvisoryService.getAboutEng(organId);
        modelAndView.addObject("aboutEnts", JsonUtility.toObjectString(aboutEntList));
        modelAndView.addObject("userName", session.getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListData(Integer page_size, Integer page_num, String search_condition) {
        //Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ProAdvisoryEntity> proOrganPageInfo=proAdvisoryService.getPage(page_num,page_size);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }

    @RequestMapping(value = "saveadvisory", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveAdvisory(@RequestBody ProAdvisoryEntity proAdvisoryEntity) {
        proAdvisoryEntity.setAdvSid(UUIDUtility.getUUID());
        proAdvisoryService.saveBySelective(proAdvisoryEntity.getAdvSid(), proAdvisoryEntity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "replylist", method = RequestMethod.GET)
    public ModelAndView advisoryReplyList() {
        ModelAndView modelAndView=new ModelAndView("Project/Advisory/AdvisoryReplyList");
        return modelAndView;
    }

    @RequestMapping(value = "replydetail", method = RequestMethod.GET)
    public ModelAndView replyDetail(String AdvisoryId) throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/Advisory/AdvisoryReplyEdit");
        B4DSession session= B4DSessionUtility.getSession();
        modelAndView.addObject("AdvisoryId",AdvisoryId);

        ProAdvisoryEntity proAdvisoryEntity=proAdvisoryService.getByPrimaryKey(AdvisoryId);

        List<ProAdvisoryReplyEntity> proAdvisoryReplyEntities=proAdvisoryReplyService.getByAdvisoryId(AdvisoryId);

        ArolEngBaseInfoEntity arolEngBaseInfoEntity=arolEngBaseInfoService.getByPrimaryKey(Integer.parseInt(proAdvisoryEntity.getOuterId()));

        modelAndView.addObject("proAdvisoryEntity",JsonUtility.toObjectString(proAdvisoryEntity));
        modelAndView.addObject("proAdvisoryReplyEntities",JsonUtility.toObjectString(proAdvisoryReplyEntities));
        modelAndView.addObject("arolEngBaseInfoEntity",JsonUtility.toObjectString(arolEngBaseInfoEntity));
        return modelAndView;
    }

    @RequestMapping(value = "saveadvisoryreply", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveAdvisoryReply(String AdvisoryId,String NewReply) {
        B4DSession session=B4DSessionUtility.getSession();
        ProAdvisoryReplyEntity proAdvisoryReplyEntity=new ProAdvisoryReplyEntity();
        proAdvisoryReplyEntity.setReplySid(UUIDUtility.getUUID());
        proAdvisoryReplyEntity.setAdvSid(AdvisoryId);
        proAdvisoryReplyEntity.setCreatetime(new Date());
        proAdvisoryReplyEntity.setOrganId(session.getOrganId());
        proAdvisoryReplyEntity.setOrganName(session.getOrganName());
        proAdvisoryReplyEntity.setUserId(session.getUserId());
        proAdvisoryReplyEntity.setUserName(session.getUserName());
        proAdvisoryReplyEntity.setReplyContent(NewReply);
        proAdvisoryReplyEntity.setStatus("回复");
        proAdvisoryReplyService.save(proAdvisoryReplyEntity.getReplySid(),proAdvisoryReplyEntity);
        return Build4DResponseVo.saveSuccess(proAdvisoryReplyEntity);
    }
}
