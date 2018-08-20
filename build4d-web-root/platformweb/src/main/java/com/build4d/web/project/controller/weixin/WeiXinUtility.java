package com.build4d.web.project.controller.weixin;

import com.build4d.base.tools.common.HttpClientUtility;
import com.build4d.base.tools.common.JsonUtility;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeiXinUtility {

    String WeiXinBaseAddress="https://api.weixin.qq.com/cgi-bin/";
    String appID="wx366a82f398b2c8b9";
    String appsecret="c2d08c6ea1c2077114f61f21bc97f031";
    String Token="Alex4dWinXin";

    public static String accesstoken="";
    public static Map<String,String> tempCode=new HashMap<>();

    public String getAccessToken() throws IOException {
        if(accesstoken.equals("")){
            String fullAddress=WeiXinBaseAddress+"token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
            HttpClientUtility httpClientUtility=new HttpClientUtility();
            String result=httpClientUtility.getHttpGetResult(fullAddress);
            Map<String,String> mapResult=JsonUtility.toObject(result, Map.class);
            accesstoken=mapResult.get("access_token");
        }
        return accesstoken;
    }

    public String buildAttentionUrl() throws IOException {
        //https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
        String fullAddress=WeiXinBaseAddress+"qrcode/create?access_token="+this.getAccessToken();

        HttpClientUtility httpClientUtility=new HttpClientUtility();
        String result=httpClientUtility.getHttpPostJsonResult(fullAddress,"{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}");
        if(result.indexOf("errmsg")>0){
            accesstoken="";
            fullAddress=WeiXinBaseAddress+"qrcode/create?access_token="+this.getAccessToken();

            httpClientUtility=new HttpClientUtility();
            result=httpClientUtility.getHttpPostJsonResult(fullAddress,"{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}");
        }

        return result;
    }

    public String buildGetBindAccountUrl(String redirect_uri) throws UnsupportedEncodingException {
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
        String fullAddress="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appID+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        return fullAddress;
    }

    public Map<String,String> getLoginToken(String code) throws IOException {
        //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String fullAddress="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
        HttpClientUtility httpClientUtility=new HttpClientUtility();
        String result=httpClientUtility.getHttpGetResult(fullAddress);
        Map<String,String> mapResult=JsonUtility.toObject(result, Map.class);
        return mapResult;
    }
}
