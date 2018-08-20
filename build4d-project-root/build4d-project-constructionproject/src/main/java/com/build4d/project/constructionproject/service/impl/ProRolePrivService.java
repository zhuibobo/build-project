package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProRolePrivService;
import com.build4d.project.dbaccess.dao.ProRolePrivMapper;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProRolePrivService extends BaseService<ProRolePrivEntity> implements IProRolePrivService {
    ProRolePrivMapper proRolePrivMapper;

    public ProRolePrivService(ProRolePrivMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proRolePrivMapper=_defaultBaseMapper;
    }


    @Override
    public void overSaveMenuRolePriv(Integer roleId, String[] menuIdArray) {
        proRolePrivMapper.deleteRoleMenuPriv(roleId);
        for (String s : menuIdArray) {
            ProRolePrivEntity proRolePrivEntity=new ProRolePrivEntity();
            proRolePrivEntity.setRoleSid(roleId);
            proRolePrivEntity.setRoprObjId(s);
            proRolePrivEntity.setRoprObjType("Menu");
            proRolePrivEntity.setRoprSid(proRolePrivMapper.nextId());
            proRolePrivEntity.setRoprOperationId("1");
            proRolePrivMapper.insert(proRolePrivEntity);
        }
    }

    @Override
    public List<ProRolePrivEntity> getRoleMenuPriv(int roleId) {
        return proRolePrivMapper.selectRoleMenuPriv(roleId);
    }

    @Override
    public List<ProRolePrivEntity> getRolesMenuPriv(List<Integer> roleIdList) {
        return proRolePrivMapper.selectRoleIdsMenuPrivs(roleIdList);
    }
}
