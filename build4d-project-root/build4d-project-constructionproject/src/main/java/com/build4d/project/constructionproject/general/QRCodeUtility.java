package com.build4d.project.constructionproject.general;

import com.build4d.base.tools.common.InetAddressUtility;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public class QRCodeUtility {
    public static String buildQRCodeUrl(HttpServletRequest request,String appendUrl) throws UnknownHostException {
        String fullUrl="";
        if(InetAddressUtility.getThisPCHostName().equals("DESKTOP-KUAEU1Q")){
            String ip="http://192.168.3.2:8080";
            fullUrl=ip+request.getContextPath()+appendUrl;
        }
        else {
            String ip="http://"+request.getServerName()+":"+request.getServerPort();
            fullUrl=ip+request.getContextPath()+appendUrl;
        }
        return fullUrl;
    }
}
