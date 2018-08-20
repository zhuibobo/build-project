package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProRoleService;
import com.build4d.project.dbaccess.dao.ProRoleMapper;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProRoleService extends BaseService<ProRoleEntity> implements IProRoleService {
    ProRoleMapper proRoleMapper;

    public ProRoleService(ProRoleMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proRoleMapper=_defaultBaseMapper;
    }

    @Override
    public List<ProRoleEntity> getRolesByUserId(Integer userSid) {
        return proRoleMapper.selectRolesByUserId(userSid);
    }
}
