package com.build4d.web.project.controller.system;

import com.build4d.project.constructionproject.service.IProOuterOrganService;
import com.build4d.project.dbaccess.dbentities.ProOuterOrganEntity;
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

@Controller
@RequestMapping("project/system")
public class OuterOrganController {
    @Autowired
    IProOuterOrganService proOuterOrganService;

    @RequestMapping(value = "outerorgan/list", method = RequestMethod.GET)
    public ModelAndView organList(String oorg_type) {
        ModelAndView modelAndView=new ModelAndView("Project/System/OuterOrgan/OuterOrganList");
        modelAndView.addObject("oorg_type",oorg_type);
        return modelAndView;
    }

    @RequestMapping(value = "outerorgan/listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListData(Integer page_size, Integer page_num, String search_condition,String oorg_type) throws IOException, ParseException {
        //Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ProOuterOrganEntity> proOuterOrganEntityPageInfo=proOuterOrganService.getPage(page_num,page_size,null,oorg_type);
        return Build4DResponseVo.success("获取成功",proOuterOrganEntityPageInfo);
    }

    @RequestMapping(value = "outerorgan/detail", method = RequestMethod.GET)
    public ModelAndView detail(Integer sId,String op,String oorg_type) {
        ModelAndView modelAndView=new ModelAndView("Project/System/OuterOrgan/OuterOrganEdit");
        ProOuterOrganEntity entity=null;
        if(sId==null||sId==0){

        }
        else
        {
            entity=proOuterOrganService.getByPrimaryKey(sId);
        }
        modelAndView.addObject("entity",entity);
        modelAndView.addObject("op",op);
        modelAndView.addObject("sId",sId);
        modelAndView.addObject("oorg_type",oorg_type);
        return modelAndView;
    }

    @RequestMapping(value = "outerorgan/saveedit", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEdit(@RequestBody ProOuterOrganEntity proOuterOrganEntity) {
        if(proOuterOrganEntity.getOorgSid()==null){
            proOuterOrganEntity.setOorgSid(0);
        }
        proOuterOrganService.saveBySelective(proOuterOrganEntity.getOorgSid(), proOuterOrganEntity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "outerorgan/del")
    @ResponseBody
    public Build4DResponseVo del(int sId) {
        proOuterOrganService.deleteByKey(sId);
        return Build4DResponseVo.deleteSuccess();
    }
}
