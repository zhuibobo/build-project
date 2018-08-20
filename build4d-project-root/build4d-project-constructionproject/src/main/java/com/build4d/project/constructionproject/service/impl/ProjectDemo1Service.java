package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProjectDemo1Service;
import com.build4d.project.dbaccess.dao.Demo1Mapper;
import com.build4d.project.dbaccess.dbentities.Demo1Entity;
import org.mybatis.spring.SqlSessionTemplate;

public class ProjectDemo1Service extends BaseService<Demo1Entity> implements IProjectDemo1Service {

    Demo1Mapper demoMapper;

    public ProjectDemo1Service(BaseMapper<Demo1Entity> _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
    }
}
