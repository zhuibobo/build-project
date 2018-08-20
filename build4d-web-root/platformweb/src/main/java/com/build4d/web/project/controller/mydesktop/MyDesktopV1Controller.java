package com.build4d.web.project.controller.mydesktop;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.project.constructionproject.service.IArolArchInfoService;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/project/mydesktop")
public class MyDesktopV1Controller {

    @Autowired
    IProOrganService organService;

    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @Autowired
    IArolArchInfoService arolArchInfoService;

    @RequestMapping(value = "gotomydesktopv1", method = RequestMethod.GET)
    public ModelAndView gotoMyDesktopV1() {
        ModelAndView modelAndView=new ModelAndView("Project/MyDesktop/MyDesktopV1");
        return modelAndView;
    }


    @RequestMapping(value = "gotomydesktopconstructionunit", method = RequestMethod.GET)
    public ModelAndView gotoMyDesktopConstructionUnit() throws JsonProcessingException{
        ModelAndView modelAndView=new ModelAndView("Project/MyDesktop/MyDesktopConstructionUnit");
        B4DSession session= B4DSessionUtility.getSession();
        List<Map<String,Object>> m= arolEngBaseInfoService.countEngByOrgan(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("statusData", JsonUtility.toObjectString(m));
        return modelAndView;
    }

    @RequestMapping(value = "gotomydesktopconstructionunitv2", method = RequestMethod.GET)
    public ModelAndView gotoMyDesktopConstructionUnitV2() throws JsonProcessingException{
        ModelAndView modelAndView=new ModelAndView("Project/MyDesktop/MyDesktopConstructionUnitV2");
        B4DSession session= B4DSessionUtility.getSession();
        List<Map<String,Object>> m= arolEngBaseInfoService.countEngByOrgan(Integer.parseInt(session.getOrganId()));

        Map<String,Object> map=new HashedMap<String,Object>();
        map.put("organSid",session.getOrganId());
        List<Map<String,Object>> s=arolArchInfoService.countArchByMap(map);
        modelAndView.addObject("statusData", JsonUtility.toObjectString(m));
        modelAndView.addObject("archStatusData", JsonUtility.toObjectString(s));
        return modelAndView;
    }

    @RequestMapping(value = "gotomydesktoparchives", method = RequestMethod.GET)
    public ModelAndView gotoMyDesktopArchives() throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/MyDesktop/MyDesktopArchives");
        B4DSession session= B4DSessionUtility.getSession();
        List<Map<String,Object>> m= arolEngBaseInfoService.countEngByOrgan(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("statusData", JsonUtility.toObjectString(m));
        return modelAndView;
    }

    @RequestMapping(value = "gotomydesktoparchivesv2", method = RequestMethod.GET)
    public ModelAndView gotoMyDesktopArchivesV2() throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("Project/MyDesktop/MyDesktopArchivesV2");
        B4DSession session= B4DSessionUtility.getSession();
        List<Map<String,Object>> m= arolEngBaseInfoService.countEngByOrgan(Integer.parseInt(session.getOrganId()));
        modelAndView.addObject("statusData", JsonUtility.toObjectString(m));
        return modelAndView;
    }
}
