package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngHouseSpecService;
import com.build4d.project.dbaccess.dao.ArolEngHouseSpecMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngHouseSpecEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngHouseSpecService  extends BaseService<ArolEngHouseSpecEntity> implements IArolEngHouseSpecService {
    ArolEngHouseSpecMapper arolEngHouseSpecMapper;

    public ArolEngHouseSpecService(ArolEngHouseSpecMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngHouseSpecMapper=_defaultBaseMapper;
    }
}
