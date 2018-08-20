package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngOtherSpecService;
import com.build4d.project.dbaccess.dao.ArolEngOtherSpecMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngOtherSpecEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngOtherSpecService  extends BaseService<ArolEngOtherSpecEntity> implements IArolEngOtherSpecService {
    ArolEngOtherSpecMapper arolEngOtherSpecMapper;

    public ArolEngOtherSpecService(ArolEngOtherSpecMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngOtherSpecMapper=_defaultBaseMapper;
    }
}
