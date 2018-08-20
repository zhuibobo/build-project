package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.service.impl.BaseService;
import com.build4d.base.tools.common.MD5Utility;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IArolProjBaseInfoService;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.constructionproject.service.IProUserService;
import com.build4d.project.constructionproject.vo.RegisterVo;
import com.build4d.project.dbaccess.dao.ProOrganMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ArolProjBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public class ProOrganService extends BaseService<ProOrganEntity> implements IProOrganService {

    ProOrganMapper proOrganMapper;

    @Autowired
    IArolProjBaseInfoService arolProjBaseInfoService;

    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @Autowired
    IProUserService proUserService;

    public ProOrganService(ProOrganMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proOrganMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProOrganEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProOrganEntity>() {
            @Override
            public ProOrganEntity run(ProOrganEntity item) {
                item.setOrgOrder(proOrganMapper.nextOrderNum());
                return item;
            }
        });
    }

    @Override
    public List<ProOrganEntity> selectEnabledOrgs(String orgType) {
         return proOrganMapper.selectEnabledOrgs(orgType);
    }

    @Override
    public PageInfo<ProOrganEntity> getPageFoOrganMange(int pageNum, int pageSize, Map<String,Object> searchItemMap){
        PageHelper.startPage(pageNum, pageSize);
        //PageHelper.
        //Map<String,Object> parasm=new HashMap<>();
        //parasm.put("orgName","1");
        List<ProOrganEntity> lsit=proOrganMapper.selectOrganManage(searchItemMap);
        PageInfo<ProOrganEntity> pageInfo = new PageInfo<>(lsit);
        return pageInfo;
    }


    @Override
    @Transactional(rollbackFor =Exception.class)
    public int RegisterOrgAndProject(RegisterVo rVo) throws Exception {
       ProOrganEntity organEntity= rVo.getProOrganEntity();
       Integer orgSid=getNextId();
       if(orgSid==null){
            throw new Exception("单位ID为空！");
       }
       else {
           organEntity.setOrgSid(orgSid);
           organEntity.setOrgStatus(0);
           organEntity.setOrgType("建设单位");
           Integer r=  saveBySelective(orgSid, organEntity);

           ProUserEntity userEntity=rVo.getProUserEntity();
           int userId=proUserService.getNextId();
           userEntity.setUserSid(userId);
           userEntity.setUserType("业务用户");
           userEntity.setUserStatus(0);
           userEntity.setUserOrgId(String.valueOf(orgSid));
           userEntity.setUserPsw(MD5Utility.GetMD5Code(userEntity.getUserPsw(),true));
           proUserService.saveBySelective(userId,userEntity);

           int proSid=arolProjBaseInfoService.getNextId();
           ArolProjBaseInfoEntity projBaseInfoEntity=rVo.getProjBaseInfoEntity();
           projBaseInfoEntity.setSid(proSid);
           projBaseInfoEntity.setProjLocation(rVo.getEngBaseInfoEntity().getEngZone());
           projBaseInfoEntity.setOrgNoCode(organEntity.getOrgCode());
           projBaseInfoEntity.setConOrgSid(orgSid);
           arolProjBaseInfoService.saveBySelective(proSid,projBaseInfoEntity);

           ArolEngBaseInfoEntity arolEngBaseInfoEntity=rVo.getEngBaseInfoEntity();
           int engSid=arolEngBaseInfoService.getNextId();
            arolEngBaseInfoEntity.setSid(engSid);
           arolEngBaseInfoEntity.setProjSid(proSid);
           arolEngBaseInfoEntity.getEngOrgAndCode().setLandUseOrgSid(orgSid);

           rVo.getEngBaseInfoEntity().getEngOrgAndCode().setLandUseOrgSid(orgSid);
           rVo.getEngBaseInfoEntity().getEngOrgAndCode().setLandUseOrgName(organEntity.getOrgName());

           //创建Session信息
           B4DSession b4DSession = new B4DSession();
           b4DSession.setUserId(userEntity.getUserSid().toString());
           b4DSession.setUserName(userEntity.getUserUsername());
           b4DSession.setOrganId(organEntity.getOrgSid().toString());
           b4DSession.setOrganName(organEntity.getOrgName());
           b4DSession.setOrganCode(organEntity.getOrgCode());
           B4DSessionUtility.addSessionAttr(B4DSessionUtility.UserLoginSessionKey, b4DSession);

           arolEngBaseInfoService.saveBySelective(engSid,arolEngBaseInfoEntity);
         return engSid;
       }
     }

    @Override
    public ProOrganEntity getByOrganCodeKey(String orgNoCode) {
        return proOrganMapper.selectByOrgCode(orgNoCode);
    }

    @Override
    public List<ProOrganEntity> selectEnabledOrgsWithCreateByBuildOrgan(String organId) {
        return proOrganMapper.selectEnabledOrgsWithCreateByBuildOrgan(organId);
    }

    @Override
    public  boolean existOrgan(Integer orgSid,String orgName,String orgCode)
    {
        return proOrganMapper.existOrgan(orgSid,orgName,orgCode);
    }
}

