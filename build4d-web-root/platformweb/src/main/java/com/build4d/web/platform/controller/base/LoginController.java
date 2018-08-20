package com.build4d.web.platform.controller.base;

import com.aliyuncs.exceptions.ClientException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.DateUtility;
import com.build4d.base.tools.common.MD5Utility;
import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.project.constructionproject.general.ProjectProp;
import com.build4d.project.constructionproject.general.SMSUtility;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.constructionproject.service.IProRoleService;
import com.build4d.project.constructionproject.service.IProUserService;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import com.build4d.web.project.controller.base.ImageGeneralController;
import com.build4d.web.project.controller.weixin.LoginTempMappingInfo;
import com.build4d.web.project.controller.weixin.TempTokenInfo;
import com.build4d.web.project.controller.weixin.WeiXinUtility;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: zhuangrb
 * @Date: 2018/4/26
 * @Description:
 * @Version 1.0.0
 */

@Controller
public class LoginController {

    @Autowired
    IProUserService proUserService;

    @Autowired
    IProRoleService proRoleService;

    @Autowired
    ImageGeneralController imageGeneralController;

    @Autowired
    IProOrganService proOrganService;

    WeiXinUtility weiXinUtility=new WeiXinUtility();

    public static Map<String,LoginTempMappingInfo> tempLoginMappingMap=new HashMap<>();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        /*try {
            SMSUtility.Send("SMS_138076152", "13723728693");
        } catch (ClientException e) {
            e.printStackTrace();
        }*/
        //System.out.println("Home Controller Call"+ ProjectProp.getValue("DefaultPassword"));

        //String time=DateUtility.getDate_yyMdHmsSSS();
        long time=System.currentTimeMillis();
        System.out.println(time);

        //微信登陆
        //1:生成一个唯一id,作为客户端唯一的标志
        //2:客户端使用该标志进行轮询
        //3:基于该标志生成一个提供客户扫描的二维码,二维码生成连接带上参数,避免缓存
        //4:客户扫描该二维码.
        //5:微信打开连接,并使用标志设置标志与用户关联,存入缓存中
        //6:轮询程序检测改标志是否可以登陆.
        //7:如果可以登陆,则使用标志进行登陆.
        //8:登陆后删除改标志.
        String loginKey= UUIDUtility.getUUIDNotSplit();
        request.getSession().setAttribute(B4DSessionUtility.EXSessionKey1,loginKey);

        ModelAndView modelAndView=new ModelAndView("Login");
        modelAndView.addObject("WXLoginKey",loginKey);
        return modelAndView;
    }

    @RequestMapping(value = "/redirectlogin", method = RequestMethod.GET)
    public ModelAndView redirectlogin(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView("RedirectLogin");
        return modelAndView;
    }

    @RequestMapping(value = "buildMyWXLoginCode")
    public void buildMyWXLoginCode(HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        String redirect_uri = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort()+request.getContextPath();
        redirect_uri=redirect_uri+"/enableUserLogin/"+request.getSession().getAttribute(B4DSessionUtility.EXSessionKey1)+".do";
        System.out.println("绑定URL："+redirect_uri);
        redirect_uri= URLEncoder.encode(redirect_uri,"utf-8");
        String full_Url=weiXinUtility.buildGetBindAccountUrl(redirect_uri);
        System.out.println("微信FULL_URL："+full_Url);
        imageGeneralController.buildUrlQRCode(request,response,full_Url);
    }

    @RequestMapping(value = "enableUserLogin/{LoginKey}", method = RequestMethod.GET)
    public ModelAndView enableUserLogin(@PathVariable("LoginKey") String LoginKey, String code, String state) throws IOException {
        ModelAndView modelAndView = new ModelAndView("Project/WeiXin/BindAccountToWxResult");
        if(code==null||code.equals("")){
            modelAndView.addObject("Message","code不能为空!");
            return modelAndView;
        }
        try {
            String key = LoginKey + "$" + code;
            if (!tempLoginMappingMap.containsKey(key)) {
                LoginTempMappingInfo loginTempMappingInfo = new LoginTempMappingInfo();
                Map<String, String> data = weiXinUtility.getLoginToken(code);
                loginTempMappingInfo.setAccess_token(String.valueOf(data.get("access_token")));
                loginTempMappingInfo.setExpires_in(String.valueOf(data.get("expires_in")));
                loginTempMappingInfo.setOpenid(String.valueOf(data.get("openid")));
                loginTempMappingInfo.setRefresh_token(String.valueOf(data.get("refresh_token")));
                loginTempMappingInfo.setScope(String.valueOf(data.get("scope")));
                loginTempMappingInfo.setLoginKey(LoginKey);
                tempLoginMappingMap.put(key, loginTempMappingInfo);
                System.out.println("SaveKey"+key);
            }
            //进行账号绑定
            modelAndView.addObject("Message", "PC端进行登陆中！");
        }
        catch (Exception ex) {
            modelAndView.addObject("Message","PC端登陆失败！"+ex.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/validateWxLoginKeyLoginEnable", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo validateWxLoginKeyLoginEnable(HttpServletRequest request) {
        Object obj=request.getSession().getAttribute(B4DSessionUtility.EXSessionKey1);
        if(obj!=null) {
            String loginKey = obj.toString();
            for (Map.Entry<String, LoginTempMappingInfo> stringLoginTempMappingInfoEntry : tempLoginMappingMap.entrySet()) {
                if (stringLoginTempMappingInfoEntry.getKey().indexOf(loginKey) == 0) {
                    return Build4DResponseVo.success();
                }
            }
        }
        return Build4DResponseVo.error("无法登陆，未找到Key");
    }

    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo validateUser(String account, String pwd,String loginKey, HttpServletRequest request) {

        ProUserEntity proUserEntity=null;

        if(loginKey!=null&&!loginKey.equals("")){
            //获取登陆信息
            LoginTempMappingInfo loginTempMappingInfo=null;
            String key="";
            for (Map.Entry<String, LoginTempMappingInfo> stringLoginTempMappingInfoEntry : tempLoginMappingMap.entrySet()) {
                if(stringLoginTempMappingInfoEntry.getKey().indexOf(loginKey)==0){
                    loginTempMappingInfo=stringLoginTempMappingInfoEntry.getValue();
                    key=stringLoginTempMappingInfoEntry.getKey();
                    break;
                }
            }
            if(loginTempMappingInfo!=null){
                //使用微信号进行登陆
                proUserEntity=proUserService.getUserByWXNo(loginTempMappingInfo.getOpenid());
                //删除登陆code
                tempLoginMappingMap.remove(key);
                if(proUserEntity==null){
                    return Build4DResponseVo.error("未找到绑定的账号，请登陆后绑定账号！");
                }
            }
        }
        /*try {
            SMSUtility.Send("SMS_135040877","13927425407");
        } catch (ClientException e) {
            e.printStackTrace();
        }*/
        else {
            //适应账号登陆
            pwd = MD5Utility.GetMD5Code(pwd, true);
            proUserEntity = proUserService.getUserByAccountAndPwd(account, pwd);
        }

        if (proUserEntity != null) {
            ProOrganEntity organEntity = proUserEntity.getProOrgan();
            if(organEntity==null)
            {
                organEntity=proOrganService.getByPrimaryKey(Integer.parseInt(proUserEntity.getUserOrgId()));
            }
            B4DSession b4DSession = new B4DSession();
            b4DSession.setUserId(proUserEntity.getUserSid().toString());
            b4DSession.setUserName(proUserEntity.getUserUsername());
            b4DSession.setOrganId(organEntity.getOrgSid().toString());
            b4DSession.setOrganName(organEntity.getOrgName());
            b4DSession.setOrganCode(organEntity.getOrgCode());
            List<ProRoleEntity> proRoleEntityList = proRoleService.getRolesByUserId(proUserEntity.getUserSid());
            if (proUserEntity.getUserType().equals("超级管理员")) {
                b4DSession.setFullPriv(true);
            }
            if (proRoleEntityList != null && proRoleEntityList.size() > 0) {
                List<String> roleKeys = new ArrayList<>();
                for (ProRoleEntity proRoleEntity : proRoleEntityList) {
                    roleKeys.add(proRoleEntity.getRoleSid().toString());
                }
                b4DSession.setRoleKeys(roleKeys);
            }
            B4DSessionUtility.addSessionAttr(B4DSessionUtility.UserLoginSessionKey, b4DSession);
            return Build4DResponseVo.opSuccess();
        } else {
            return Build4DResponseVo.error("您的用户名或密码错误！");
        }
    }
}
