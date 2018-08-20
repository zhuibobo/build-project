package com.build4d.web.project.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhuangrb
 * @Date: 2018/4/27
 * @Description:
 * @Version 1.0.0
 */

@Controller
@RequestMapping("project/system")
public class PersonController {
    @RequestMapping(value = "person/personedit", method = RequestMethod.GET)
    public ModelAndView personEdit() {
        ModelAndView modelAndView=new ModelAndView("Project/System/Person/PersonEdit");
        return modelAndView;
    }
}
