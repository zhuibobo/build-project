package com.build4d.web.project.controller.project;

import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.IProEngFlowLogService;
import com.build4d.project.constructionproject.service.impl.ProEngFlowLogService;
import com.build4d.web.general.model.Build4DResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/21
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("project")
public class EngineeringLogController {

    @Autowired
    IProEngFlowLogService proEngFlowLogService;

    @RequestMapping(value = "log/englog", method = RequestMethod.GET)
    public ModelAndView englog() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Log/ENGLog");
        return modelAndView;
    }

    @RequestMapping(value = "log/listdata", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getOrganListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object> searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<Map> proOrganPageInfo=proEngFlowLogService.searchLog(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proOrganPageInfo);
    }
}
