package com.build4d.web.project.controller.weixin;

import com.build4d.base.tools.common.HttpClientUtility;
import com.build4d.web.general.model.Build4DResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("project/weixin")
public class WeiXinController {
    WeiXinUtility weiXinUtility=new WeiXinUtility();

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView=new ModelAndView("Project/WeiXin/Test");
        return modelAndView;
    }

    @RequestMapping(value = "access_token")
    @ResponseBody
    public Build4DResponseVo access_token() throws IOException {
        //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        return Build4DResponseVo.success(weiXinUtility.getAccessToken());
    }

    @RequestMapping(value = "createAttentionRQUrl")
    @ResponseBody
    public Build4DResponseVo createAttentionRQUrl() throws IOException {
        //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        return Build4DResponseVo.success(weiXinUtility.buildAttentionUrl());
    }

    @RequestMapping(value = "createRQUrl")
    @ResponseBody
    public Build4DResponseVo createRQUrl() throws IOException {
        //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String redirect_uri= URLEncoder.encode("http://www.alex4d.top:8080/b4d/project/weixinbindaccount/validateCodeLogin.do","utf-8");
        return Build4DResponseVo.success(weiXinUtility.buildGetBindAccountUrl(redirect_uri));
    }

    @RequestMapping(value = "validateCodeLogin", method = RequestMethod.GET)
    public ModelAndView validateCodeLogin(String code,String state) throws IOException {
        Map<String,String> data=weiXinUtility.getLoginToken(code);
        System.out.printf("length......"+String.valueOf(data.size()));
        ModelAndView modelAndView=new ModelAndView("Project/WeiXin/validateCodeLogin");
        modelAndView.addObject("access_token",data.get("access_token").toString());
        modelAndView.addObject("openid",data.get("openid").toString());
        return modelAndView;
    }
}
