package com.build4d.web.project.controller.system;

import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.JsonUtility;
import com.build4d.base.tools.common.MD5Utility;
import com.build4d.base.tools.common.search.GeneralSearchUtility;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.dbaccess.dao.ProUserMapper;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import com.build4d.project.dbaccess.dbentities.ProUserRoleEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import com.build4d.web.model.IViewTransferItemModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb, liwl
 * @Date: 2018/4/27
 * @Description:
 * @Version 1.0.0
 */
@Controller
@RequestMapping("project/system")
public class UserController {

    @Autowired
    IProUserService proUserService;

    @Autowired
    IProOrganService proOrganService;

    @Autowired
    IProUserRoleService proUserRoleService;

    @Autowired
    IProRoleService proRoleService;

    @RequestMapping(value = "user/userlist", method = RequestMethod.GET)
    public ModelAndView UserList() {
        //ModelAndView modelAndView=new ModelAndView("Project/System/User/BusinessUserList");
        ModelAndView modelAndView=new ModelAndView("Project/System/User/UserList");
        return modelAndView;
    }

    @RequestMapping(value = "user/signaturuserlist", method = RequestMethod.GET)
    public ModelAndView signaturUserList() {
        ModelAndView modelAndView=new ModelAndView("Project/System/User/SignatureUserList");
        return modelAndView;
    }

    @RequestMapping(value = "user/outsourcinguserlist", method = RequestMethod.GET)
    public ModelAndView outsourcingUserList() {
        ModelAndView modelAndView=new ModelAndView("Project/System/User/OutsourcingUserList");
        return modelAndView;
    }

    @RequestMapping(value = "user/documentuserlist", method = RequestMethod.GET)
    public ModelAndView documentUserList() {
        ModelAndView modelAndView=new ModelAndView("Project/System/User/DocumentUserList");
        return modelAndView;
    }

    @RequestMapping(value = "user/getuserlistdata", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo getUserListData(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object>  searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        PageInfo<ProUserEntity> proUserList=proUserService.getPage(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proUserList);
    }

    @RequestMapping(value = "user/usereidt", method = RequestMethod.GET)
    public ModelAndView userEdit(Integer userSid, HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView;
        if (B4DSessionUtility.getSession().isFullPriv()) {
            modelAndView = new ModelAndView("Project/System/User/UserEdit");
        } else {
            modelAndView = new ModelAndView("Project/System/User/UserEdit4Build");
        }
        ProUserEntity proUser = null;
        if (userSid == null || userSid == 0) {
            userSid = proUserService.getNextId();
            proUser = new ProUserEntity();
            proUser.setUserStatus(1);
            proUser.setUserOrgId("''");
            proUser.setUserWxbinding("未绑定");

            String userType = request.getParameter("userType");
            proUser.setUserType(userType);
        } else {
            proUser = proUserService.getByPrimaryKey(userSid);
        }
        List<ProOrganEntity> orgData;
        if (B4DSessionUtility.getSession().isFullPriv()) {
            orgData = proOrganService.selectEnabledOrgs(null);
        }
        else
        {
            orgData = proOrganService.selectEnabledOrgsWithCreateByBuildOrgan(B4DSessionUtility.getSession().getOrganId());
        }
        modelAndView.addObject("orgData", JsonUtility.toObjectString(orgData));
        modelAndView.addObject("proUser", proUser);
        modelAndView.addObject("userSid", userSid);
        return modelAndView;
    }

    @RequestMapping(value = "user/myuserinfo", method = RequestMethod.GET)
    public ModelAndView myUserInfo(HttpServletRequest request) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("Project/System/User/MyUserInfo");
        B4DSession session=B4DSessionUtility.getSession();
        ProUserEntity proUser =proUser = proUserService.getByPrimaryKey(Integer.valueOf(session.getUserId()));
        ProOrganEntity orgData=proOrganService.getByPrimaryKey(Integer.valueOf(session.getOrganId()));
        modelAndView.addObject("orgData", orgData);
        modelAndView.addObject("proUser", proUser);
        return modelAndView;
    }

    @RequestMapping(value = "user/checkaccountpwd")
    @ResponseBody
    public  Build4DResponseVo checkAccountPwd(String account,String pwd)
    {
        Boolean r=false;
        pwd = MD5Utility.GetMD5Code(pwd, true);
        ProUserEntity proUserEntity = proUserService.getUserByAccountAndPwd(account, pwd);
        if(proUserEntity!=null)
        {
            r=true;
        }
        return Build4DResponseVo.saveSuccess(r);
    }

    @RequestMapping(value = "user/encryptpwd")
    @ResponseBody
    public  Build4DResponseVo encryptPwd(String pwd)
    {
        if(pwd.equals(""))
           return Build4DResponseVo.saveSuccess(null);
        else
            return Build4DResponseVo.saveSuccess(MD5Utility.GetMD5Code(pwd, true));
    }

    @RequestMapping(value = "user/saveusereidt", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo saveUser(@RequestBody ProUserEntity proUser) {
        if(proUserService.getByPrimaryKey(proUser.getUserSid())==null) {
            proUser.setCreatreOrgId(B4DSessionUtility.getSession().getOrganId());
            //如果是建筑单位新加的用户，则根据单位的类型自己设置人员的角色
            if(!B4DSessionUtility.getSession().isFullPriv()){
                ProOrganEntity proOrganEntity=proOrganService.getByPrimaryKey(Integer.parseInt(proUser.getUserOrgId()));
                int[] temp={proUser.getUserSid()};
                if(proOrganEntity.getOrgType().equals("施工单位")){
                    int roleId=4;
                    proUserRoleService.bindUserToRole(temp,roleId);
                }
                else if(proOrganEntity.getOrgType().equals("监理单位")){
                    int roleId=3;
                    proUserRoleService.bindUserToRole(temp,roleId);
                }
                else if(proOrganEntity.getOrgType().equals("签章单位")){
                    //int roleId=1;
                    //proUserRoleService.bindUserToRole(temp,roleId);
                }
                else if(proOrganEntity.getOrgType().equals("外协单位")){
                    //int roleId=1;
                    //proUserRoleService.bindUserToRole(temp,roleId);
                }
            }
        }
        proUserService.saveBySelective(proUser.getUserSid(), proUser);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "user/deluser")
    @ResponseBody
    public Build4DResponseVo delUser(int userSid) {
        proUserService.deleteByKey(userSid);
        return Build4DResponseVo.deleteSuccess();
    }

    @RequestMapping(value = "user/resetpwd")
    @ResponseBody
    public Build4DResponseVo resetPwd(int userSid) {
        proUserService.resetPassword(userSid);
        return Build4DResponseVo.opSuccess();
    }

    @RequestMapping(value = "user/exists")
    @ResponseBody
    public Build4DResponseVo exists(Integer userSid,String account) {
       boolean r= proUserService.existAccount(userSid,account);
        return Build4DResponseVo.success("",r);
    }

    @RequestMapping(value = "user/getalluserforiviewtransfer")
    @ResponseBody
    public Build4DResponseVo getAllUserForIViewTransfer() {
        List<ProUserEntity> allUser=proUserService.getALL();
        List<IViewTransferItemModel> iViewTransferItemModels=new ArrayList<>();
        for (ProUserEntity proUserEntity : allUser) {
            iViewTransferItemModels.add(new IViewTransferItemModel(proUserEntity.getUserSid().toString(), proUserEntity.getUserType()+" "+proUserEntity.getUserUsername(),"",false));
        }
        return Build4DResponseVo.success("",iViewTransferItemModels);
    }

    @RequestMapping(value = "user/bindusertorole")
    @ResponseBody
    public Build4DResponseVo bindUserToRole(int roleId,String userIds) {
        if(userIds!=null&&!userIds.equals("")){
            String[] userIdArrayStr=userIds.split(";");
            int[] userIdArray=new int[userIdArrayStr.length];
            for (int i = 0; i < userIdArrayStr.length; i++) {
                String s = userIdArrayStr[i];
                userIdArray[i]= Integer.parseInt(s);
            }
            proUserRoleService.bindUserToRole(userIdArray,roleId);
        }
        return Build4DResponseVo.opSuccess();
    }

    @RequestMapping(value = "user/getrolemember")
    @ResponseBody
    public Build4DResponseVo getRoleMember(int roleId) {
        List<ProUserRoleEntity> proUserRoleEntityList=proUserRoleService.getByRoleId(roleId);
        List<String> ids=new ArrayList<>();
        for (ProUserRoleEntity entity : proUserRoleEntityList) {
            ids.add(entity.getUserSid().toString());
        }
        return Build4DResponseVo.success("",ids);
    }

    @RequestMapping(value = "user/getmemberbyroleid")
    @ResponseBody
    public Build4DResponseVo getMemberByRoleId(int roleId) {
        List<ProUserRoleEntity> proUserRoleEntities=proUserRoleService.getUsersByRoleId(roleId);
        return Build4DResponseVo.success("",proUserRoleEntities);
    }

    @RequestMapping(value = "user/userlist4build", method = RequestMethod.GET)
    public ModelAndView UserList4Build() {
        //ModelAndView modelAndView=new ModelAndView("Project/System/User/BusinessUserList");
        ModelAndView modelAndView=new ModelAndView("Project/System/User/UserList4Build");
        return modelAndView;
    }

    @RequestMapping(value = "user/getuserlistdata4build", method = RequestMethod.GET)
    @ResponseBody
    public Build4DResponseVo getUserListData4Build(Integer page_size, Integer page_num, String search_condition) throws IOException, ParseException {
        Map<String,Object>  searchMap= GeneralSearchUtility.deserializationToMap(search_condition);
        searchMap.put("creatreOrgId",B4DSessionUtility.getSession().getOrganId());
        PageInfo<ProUserEntity> proUserList=proUserService.getPage(page_num,page_size,searchMap);
        return Build4DResponseVo.success("获取成功",proUserList);
    }
}
