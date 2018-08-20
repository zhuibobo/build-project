package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngOrgAndCodeService;
import com.build4d.project.dbaccess.dao.ArolEngOrgAndCodeMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngOrgAndCodeEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEngOrgAndCodeService  extends BaseService<ArolEngOrgAndCodeEntity> implements IArolEngOrgAndCodeService {
    ArolEngOrgAndCodeMapper arolEngOrgAndCodeMapper;

    public ArolEngOrgAndCodeService(ArolEngOrgAndCodeMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEngOrgAndCodeMapper=_defaultBaseMapper;
    }
}
