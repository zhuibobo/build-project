package com.build4d.web.project.controller.weixin;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.project.constructionproject.service.IProUserService;
import com.build4d.project.constructionproject.service.impl.ProUserService;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("project/weixinbindaccount")
public class WeiXinBindAccount {

    public static Map<String,TempTokenInfo> tempTokenInfoMap=new HashMap<>();

    @Autowired
    IProUserService proUserService;

    @RequestMapping(value = "beginbindaccounttowx", method = RequestMethod.GET)
    public ModelAndView BeginBindAccountToWx() {
        ModelAndView modelAndView=new ModelAndView("Project/WeiXin/BindAccountToWx");
        return modelAndView;
    }

    WeiXinUtility weiXinUtility=new WeiXinUtility();

    @RequestMapping(value = "createAttentionRQUrl")
    @ResponseBody
    public Build4DResponseVo createAttentionRQUrl() throws IOException {
        return Build4DResponseVo.success(weiXinUtility.buildAttentionUrl());
    }

    @RequestMapping(value = "createBindAccountRQUrl")
    @ResponseBody
    public Build4DResponseVo createBindAccountRQUrl(HttpServletRequest request) throws IOException {
        B4DSession session= B4DSessionUtility.getSession();
        String redirect_uri = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort()+request.getContextPath();
        redirect_uri=redirect_uri+"/project/weixinbindaccount/scAndBindToAccount/"+session.getUserId()+".do";
        System.out.println("绑定URL："+redirect_uri);
        redirect_uri= URLEncoder.encode(redirect_uri,"utf-8");
        return Build4DResponseVo.success(weiXinUtility.buildGetBindAccountUrl(redirect_uri));
    }

    @RequestMapping(value = "scAndBindToAccount/{User_Id}", method = RequestMethod.GET)
    public ModelAndView BeginBindAccountToWx(@PathVariable("User_Id") Integer userId,String code,String state) throws IOException {
        ModelAndView modelAndView = new ModelAndView("Project/WeiXin/BindAccountToWxResult");
        try {
            TempTokenInfo tempTokenInfo = new TempTokenInfo();
            if (!tempTokenInfoMap.containsKey(code)) {
                Map<String, String> data = weiXinUtility.getLoginToken(code);
                if(data.size()==2){
                    modelAndView.addObject("Message", "获取微信信息失败！");
                    return modelAndView;
                }
                tempTokenInfo.setAccess_token(String.valueOf(data.get("access_token")));
                tempTokenInfo.setExpires_in(String.valueOf(data.get("expires_in")));
                tempTokenInfo.setOpenid(String.valueOf(data.get("openid")));
                tempTokenInfo.setRefresh_token(String.valueOf(data.get("refresh_token")));
                tempTokenInfo.setScope(String.valueOf(data.get("scope")));
                tempTokenInfoMap.put(code, tempTokenInfo);
            } else {
                tempTokenInfo = tempTokenInfoMap.get(code);
            }

            if(tempTokenInfo.getOpenid()==null||tempTokenInfo.getOpenid().equals("")||tempTokenInfo.getOpenid().toLowerCase().equals("null")){
                modelAndView.addObject("Message", "获取微信信息失败！");
                return modelAndView;
            }
            //判断账号是否已经绑定了其他账号
            ProUserEntity otherproUserEntity = proUserService.getUserByWXNo(tempTokenInfo.getOpenid());
            if(otherproUserEntity!=null){
                /*B4DSession session=B4DSessionUtility.getSession();
                if(!otherproUserEntity.getUserSid().toString().equals(session.getUserId())){*/
                    modelAndView.addObject("Message","当前微信号已经绑定了用户："+otherproUserEntity.getUserSid()+"["+otherproUserEntity.getUserUsername()+"]");
                /*}
                else {
                    modelAndView.addObject("Message", "绑定账号成功！");
                }*/
            }
            else {
                //进行账号绑定
                ProUserEntity proUserEntity = proUserService.getByPrimaryKey(userId);
                proUserEntity.setUserWxbinding("已绑定");
                proUserEntity.setUserWxno(tempTokenInfo.getOpenid());
                proUserService.saveBySelective(proUserEntity.getUserSid(), proUserEntity);

                modelAndView.addObject("Message", "绑定账号成功！");
            }
        }
        catch (Exception ex) {
            modelAndView.addObject("Message","绑定账号失败！"+ex.getMessage());
        }
        return modelAndView;
    }
}
