package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngSpecInfoService;
import com.build4d.project.dbaccess.dao.ArolEngSpecInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngSpecInfoEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngSpecInfoService  extends BaseService<ArolEngSpecInfoEntity> implements IArolEngSpecInfoService {
    ArolEngSpecInfoMapper arolEngSpecInfoMapper;

    public ArolEngSpecInfoService(ArolEngSpecInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngSpecInfoMapper=_defaultBaseMapper;
    }
}
