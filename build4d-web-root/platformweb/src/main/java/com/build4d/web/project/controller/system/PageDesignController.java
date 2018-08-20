package com.build4d.web.project.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("project")
public class PageDesignController {

    @RequestMapping(value = "pagedesign/p1", method = RequestMethod.GET)
    public ModelAndView p1() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/PJ");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p2", method = RequestMethod.GET)
    public ModelAndView p2() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/DYJSCZT");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p3", method = RequestMethod.GET)
    public ModelAndView p3() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/JSJSKZT");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p4", method = RequestMethod.GET)
    public ModelAndView p4() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/XMJDYS");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p5", method = RequestMethod.GET)
    public ModelAndView p5() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/ZXSJZ");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p6", method = RequestMethod.GET)
    public ModelAndView p6() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/YSSQS");
        return modelAndView;
    }

    @RequestMapping(value = "pagedesign/p7", method = RequestMethod.GET)
    public ModelAndView p7() {
        ModelAndView modelAndView=new ModelAndView("Project/PageDesign/YYSYJHS");
        return modelAndView;
    }
}
