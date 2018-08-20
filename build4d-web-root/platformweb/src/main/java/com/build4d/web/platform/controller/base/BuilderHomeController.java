package com.build4d.web.platform.controller.base;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.web.general.NormalMenuUtility;
import com.build4d.web.general.model.NormalMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("builder")
public class BuilderHomeController {

    @Autowired
    private NormalMenuUtility builderMenuUtility;

    @RequestMapping(value = "frame", method = RequestMethod.GET)
    public ModelAndView frame() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, Build4DGenerallyException {
        ModelAndView modelAndView=new ModelAndView("Builder/Frame");
        List<NormalMenuVo> builderMenuVos=builderMenuUtility.getMenus(NormalMenuUtility.ProjectMenuPath);
        String menuJson= JsonUtility.toObjectString(builderMenuVos);
        modelAndView.addObject("menuJson",menuJson);
        return modelAndView;
    }
}
