package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngPBSpecService;
import com.build4d.project.dbaccess.dao.ArolEngPBSpecMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngPBSpecEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngPBSpecService  extends BaseService<ArolEngPBSpecEntity> implements IArolEngPBSpecService {
    ArolEngPBSpecMapper arolEngPBSpecMapper;

    public ArolEngPBSpecService(ArolEngPBSpecMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngPBSpecMapper=_defaultBaseMapper;
    }
}
