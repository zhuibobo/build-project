package com.build4d.project.constructionproject.service.impl;


import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.dbaccess.dao.ArolEngBaseInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ArolEngHouseSpecEntity;
import com.build4d.project.dbaccess.dbentities.ProEngFlowLogEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.List;

import javax.xml.ws.soap.Addressing;

public class ArolEngBaseInfoService extends BaseService<ArolEngBaseInfoEntity> implements IArolEngBaseInfoService {

    ArolEngBaseInfoMapper arolEngBaseInfoMapper;

    //工程责任者信息关联
    @Autowired
    IArolEngOrgAndCodeService arolEngOrgAndCodeService;

    //通用专业记载信息
    @Autowired
    IArolEngSpecInfoService arolEngSpecInfoService;

    //市政建筑专业记载信息
    @Autowired
    IArolEngFacilitySpecService arolEngFacilitySpecService;

    //房屋建筑专业记载信息
    @Autowired
    IArolEngHouseSpecService arolEngHouseSpecService;

    @Autowired
    IProEngFlowLogService proEngFlowLogService;

    @Autowired
    IArolFileInfoService arolFileInfoService;

    @Autowired
    IArolEFileInfoService arolEFileInfoService;

    @Autowired
    IArolArchInfoService arolArchInfoService;


    public ArolEngBaseInfoService(ArolEngBaseInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngBaseInfoMapper=_defaultBaseMapper;
    }

    @Override
    public ArolEngBaseInfoEntity selectEngHouseSpecByPrimaryKey(int id)
    {
        return arolEngBaseInfoMapper.selectEngHouseSpecByPrimaryKey(id);
    }

    @Override
    public ArolEngBaseInfoEntity selectEngFacilitySpecByPrimaryKey(int id) {
        return arolEngBaseInfoMapper.selectEngFacilitySpecByPrimaryKey(id);
    }

    @Override
    public ArolEngBaseInfoEntity selectEngOrgAndCodeByPrimaryKey(int id)
    {
        return arolEngBaseInfoMapper.selectEngOrgAndCodeByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int saveBySelective(int id, ArolEngBaseInfoEntity entity) {
        boolean isAdd=false;
        B4DSession session= B4DSessionUtility.getSession();
        if(arolEngBaseInfoMapper.selectByPrimaryKey(id)==null)
        {
            entity.setEngNo(arolEngBaseInfoMapper.getEngNo());
            isAdd=true;
        }
        if(entity.getEngOrgAndCode()!=null) {
            entity.getEngOrgAndCode().setEngSid(id);
            if(isAdd)
            {
                entity.getEngOrgAndCode().setCreatedBy(session.getUserName());
                entity.getEngOrgAndCode().setCreatedDt(new Date());
            }
            else
            {
                entity.getEngOrgAndCode().setUpdatedBy(session.getUserName());
                entity.getEngOrgAndCode().setUpdatedDt(new Date());
            }
        }
        if(entity.getEngSpecInfo()!=null) {
            entity.getEngSpecInfo().setEngSid(String.valueOf(id));
            if(isAdd)
            {
                entity.getEngSpecInfo().setCreatedBy(session.getUserName());
                entity.getEngSpecInfo().setCreatedDt(new Date());
            }
            else
            {
                entity.getEngSpecInfo().setUpdatedBy(session.getUserName());
                entity.getEngSpecInfo().setUpdatedDt(new Date());
            }
        }
        if(entity.getEngHouseSpec()!=null){
            entity.getEngHouseSpec().setEngSid(id);
            if(isAdd)
            {
                entity.getEngHouseSpec().setCreatedBy(session.getUserName());
                entity.getEngHouseSpec().setCreatedDt(new Date());
            }
            else
            {
                entity.getEngHouseSpec().setUpdatedBy(session.getUserName());
                entity.getEngHouseSpec().setUpdatedDt(new Date());
            }
        }
        if(entity.getEngFacilitySpec()!=null){
            entity.getEngFacilitySpec().setEngSid(id);
            if(isAdd)
            {
                entity.getEngFacilitySpec().setCreatedBy(session.getUserName());
                entity.getEngFacilitySpec().setCreatedDt(new Date());
            }
            else
            {
                entity.getEngFacilitySpec().setUpdatedBy(session.getUserName());
                entity.getEngFacilitySpec().setUpdatedDt(new Date());
            }
        }

        int r=super.saveBySelective(id, entity);
        if(entity.getEngOrgAndCode()!=null)
            arolEngOrgAndCodeService.saveBySelective(entity.getSid(),entity.getEngOrgAndCode());
        if(entity.getEngSpecInfo()!=null)
            arolEngSpecInfoService.saveBySelective(entity.getEngSpecInfo().getEngSid(),entity.getEngSpecInfo());
        if(entity.getEngFacilitySpec()!=null)
            arolEngFacilitySpecService.saveBySelective(entity.getSid(),entity.getEngFacilitySpec());
        if(entity.getEngHouseSpec()!=null) {
            ArolEngHouseSpecEntity houseSpecEntity = entity.getEngHouseSpec();
            if (houseSpecEntity.getCreatedBy()==null){
                houseSpecEntity.setCreatedBy("");
            }
            arolEngHouseSpecService.saveBySelective(entity.getSid(), entity.getEngHouseSpec());
        }
        if(isAdd)
        {
            //流转日志
            Integer flowId=proEngFlowLogService.getNextId();
            ProEngFlowLogEntity flowLogEntity=new ProEngFlowLogEntity();
            flowLogEntity.setSid(flowId);
            flowLogEntity.setEngSid(entity.getSid());
            flowLogEntity.setOpStatus(entity.getStatus());
            flowLogEntity.setOpOpinion(null);
            flowLogEntity.setOrganType(null);
            proEngFlowLogService.saveBySelective(flowId,flowLogEntity);
        }
        return id;
    }

    @Override
    public  List<Map<String,Object>> countEngByOrgan(Integer currOrgId)
    {
        return arolEngBaseInfoMapper.countEngByOrgan(currOrgId);
    }

    @Override
    public int deleteByKey(int id) {
        int r=arolEngBaseInfoMapper.deleteByPrimaryKey(id);
        arolEngFacilitySpecService.deleteByKey(id);
        arolEngHouseSpecService.deleteByKey(id);
        arolEngOrgAndCodeService.deleteByKey(id);
        arolEngSpecInfoService.deleteByKey(id);
        proEngFlowLogService.deleteByEngSid(id);
        arolFileInfoService.deleteByEngSid(id);
        arolEFileInfoService.deleteByEngSid(id);
        arolArchInfoService.deleteByEngSid(id);
        return r;
    }
}
