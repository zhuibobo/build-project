package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProFileService;
import com.build4d.project.dbaccess.dao.ProFileMapper;
import com.build4d.project.dbaccess.dbentities.ProFileEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProFileService extends BaseService<ProFileEntity> implements IProFileService {

    ProFileMapper proFileMapper;

    public ProFileService(ProFileMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proFileMapper=_defaultBaseMapper;
    }

    @Override
    public List<ProFileEntity> getByOuter(String outer_table_name, String outer_sid) {
        return proFileMapper.getByOuter(outer_table_name,outer_sid);
    }
}
