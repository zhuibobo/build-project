package com.build4d.platform.organ.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.dbaccess.dbentities.OrganEntity;
import com.build4d.base.service.impl.BaseService;
import com.build4d.platform.organ.service.IOrganService;
import org.mybatis.spring.SqlSessionTemplate;

public class OrganService extends BaseService<OrganEntity> implements IOrganService{

    public OrganService(BaseMapper<OrganEntity> _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
    }

    /*
    @Override
    public OrganMapper getOrganMapper() {
        return organMapper;
    }

    @Override
    public void setOrganMapper(OrganMapper organMapper) {
        this.organMapper = organMapper;
    }

    private OrganMapper organMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void insertNew(OrganEntity organEntity) throws Exception {
        organMapper.insert(organEntity);
        //throw new Exception("insertNew error");
    }

    @Override
    public void delete(String id) {
        organMapper.deleteByPrimaryKey(id);
    }*/
}
