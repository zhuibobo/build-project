package com.build4d.web.platform.controller.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.web.general.NormalMenuUtility;
import com.build4d.web.general.model.NormalMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("builder/frame")
public class FrameController {

    @Autowired
    private NormalMenuUtility builderMenuUtility;

    @RequestMapping(value = "get_menu_json", method = RequestMethod.GET)
    @ResponseBody
    public List<NormalMenuVo> getMenuJson() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, Build4DGenerallyException {
        return builderMenuUtility.getMenus("/Views/Builder/Config/MenusConfig.xml");
    }
}
