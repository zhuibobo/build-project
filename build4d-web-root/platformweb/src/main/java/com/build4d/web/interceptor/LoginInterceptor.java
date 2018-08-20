package com.build4d.web.interceptor;

import com.build4d.base.service.exception.SessionTimeoutException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*httpServletResponse.setContentType("text/html;charset=UTF-8");
        Map<String,String> igUrl=new HashMap<String, String>();
        igUrl.put("/login.do","");
        igUrl.put("/redirectlogin.do","");
        igUrl.put("/validateUser.do","");
        igUrl.put("/validateWxLoginKeyLoginEnable.do","");
        igUrl.put("/buildMyWXLoginCode.do","");
        igUrl.put("/project/system/organ/register.do","");
        igUrl.put("/project/system/organ/existOrgan.do","");
        String absPath=httpServletRequest.getRequestURI();
        String appName=httpServletRequest.getContextPath();
        String url=absPath.replaceAll(appName,"");
        if(igUrl.containsKey(url)){
            return true;
        }

        if(url.indexOf("appapi")>0){
            return true;
        }
        if(url.indexOf(".do")>=0){
            try {
                B4DSession session = B4DSessionUtility.getSession();
                if (session == null) {
                    *//*PrintWriter out = httpServletResponse.getWriter();
                    out.flush();
                    out.println("<script>BaseUtility.RedirectToLogin()</script>");*//*
                    httpServletResponse.sendRedirect(appName+"/redirectlogin.do");
                    return false;
                }
            }
            catch (SessionTimeoutException ex){
                httpServletResponse.sendRedirect(appName+"/redirectlogin.do");
                return false;
            }
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
