package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProOpLogService;
import com.build4d.project.dbaccess.dao.ProOpLogMapper;
import com.build4d.project.dbaccess.dbentities.ProOpLogEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ProOpLogService extends BaseService<ProOpLogEntity> implements IProOpLogService {

    ProOpLogMapper proOpLogMapper;

    public ProOpLogService(ProOpLogMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proOpLogMapper=_defaultBaseMapper;
    }
}
