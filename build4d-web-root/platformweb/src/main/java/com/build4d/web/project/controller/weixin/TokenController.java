package com.build4d.web.project.controller.weixin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("project/weixin")
public class TokenController {

    @RequestMapping(value = "accepttoken")
    public void accepttoken(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+",echostr:"+echostr);
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            response.getOutputStream().println(echostr);
        }
    }
}
