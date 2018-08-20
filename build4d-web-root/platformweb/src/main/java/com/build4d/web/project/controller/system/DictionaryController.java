package com.build4d.web.project.controller.system;


import com.build4d.project.constructionproject.service.IProDictionaryService;
import com.build4d.project.dbaccess.dbentities.ProDictionaryEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("project/system")
public class DictionaryController {

    @Autowired
    IProDictionaryService proDictionaryService;

    @RequestMapping(value = "dictionary/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Dictionary/DictionaryList");
        return modelAndView;
    }

    @RequestMapping(value = "dictionary/detail", method = RequestMethod.GET)
    public ModelAndView detail(String dictParentId,Integer sId,String op) {
        ModelAndView modelAndView=new ModelAndView("Project/System/Dictionary/DictionaryEdit");
        modelAndView.addObject("dictParentId",dictParentId);

        ProDictionaryEntity entity=null;
        if(sId==null||sId==0){
            sId=proDictionaryService.getNextId();
            entity=new ProDictionaryEntity();
            entity.setDictOrder(1);
        }
        else
        {
            entity=proDictionaryService.getByPrimaryKey(sId);
        }
        modelAndView.addObject("entity",entity);
        modelAndView.addObject("sId",sId);
        modelAndView.addObject("op",op);
        return modelAndView;
    }

    @RequestMapping(value = "dictionary/listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListData(){
        List<ProDictionaryEntity> proOrganPageInfo=proDictionaryService.getALL();
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }

    @RequestMapping(value = "dictionary/saveedit", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveEdit(@RequestBody ProDictionaryEntity dictionaryEntity) {
        proDictionaryService.saveBySelective(dictionaryEntity.getDictSid(), dictionaryEntity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "dictionary/getlistdatabypid", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getListDataByPid(String pid,int haveParent) {
        Map<String,Object> m=new HashMap<String, Object>();
        m.put("dictParentIdlist",pid);
        if(haveParent==0)
         m.put("dictParentId",pid);
        List<ProDictionaryEntity> dic=proDictionaryService.searchByMap(m);
        return Build4DResponseVo.success("",dic);
    }
}
