package com.build4d.web.platform.controller.builder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("builder/managements/storage/tables")
public class StorageTablesController {

    @RequestMapping(value = "management", method = RequestMethod.GET)
    public ModelAndView management() {
        ModelAndView modelAndView=new ModelAndView("Builder/Managements/Storage/Tables/TableManagement");
        return modelAndView;
    }
}
