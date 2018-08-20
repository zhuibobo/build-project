package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.base.tools.common.DateUtility;
import com.build4d.project.constructionproject.service.IProUserRoleService;
import com.build4d.project.dbaccess.dao.ProUserRoleMapper;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;
import com.build4d.project.dbaccess.dbentities.ProUserRoleEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProUserRoleService extends BaseService<ProUserRoleEntity> implements IProUserRoleService {
    ProUserRoleMapper proUserRoleMapper;

    public ProUserRoleService(ProUserRoleMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proUserRoleMapper=_defaultBaseMapper;
    }

    @Override
    public void bindUserToRole(int[] userIds, int roleId) {
        if(userIds!=null){
            for (int userId : userIds) {
                Map<String,Integer> paras=new HashMap<>();
                paras.put("userSid",userId);
                paras.put("roleSid",roleId);
                ProUserRoleEntity entity=proUserRoleMapper.selectByUserIdAndRoleId(paras);
                if(entity==null){
                    entity=new ProUserRoleEntity();
                    entity.setUsroSid(proUserRoleMapper.nextId());
                    entity.setRoleSid(roleId);
                    entity.setUserSid(userId);
                    entity.setUsroOrder(proUserRoleMapper.nextOrderNum());
                    entity.setUsroCreateTime(new Date());
                    proUserRoleMapper.insertSelective(entity);
                }
            }
        }
    }

    @Override
    public List<ProUserRoleEntity> getByRoleId(int roleId){
        return proUserRoleMapper.selectByRoleId(roleId);
    }

    @Override
    public List<ProUserRoleEntity> getUsersByRoleId(int roleId){
        return proUserRoleMapper.selectUsersByRoleId(roleId);
    }

}
