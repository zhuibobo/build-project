package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngFacilitySpecService;
import com.build4d.project.dbaccess.dao.ArolEngFacilitySpecMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngFacilitySpecEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngFacilitySpecService  extends BaseService<ArolEngFacilitySpecEntity> implements IArolEngFacilitySpecService {
    ArolEngFacilitySpecMapper arolEngFacilitySpecMapper;

    public ArolEngFacilitySpecService(ArolEngFacilitySpecMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngFacilitySpecMapper=_defaultBaseMapper;
    }
}
