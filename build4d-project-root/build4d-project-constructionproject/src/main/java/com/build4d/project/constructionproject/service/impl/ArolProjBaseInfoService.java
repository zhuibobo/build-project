package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolProjBaseInfoService;
import com.build4d.project.dbaccess.dao.ArolProjBaseInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolProjBaseInfoEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolProjBaseInfoService   extends BaseService<ArolProjBaseInfoEntity> implements IArolProjBaseInfoService {
    ArolProjBaseInfoMapper arolProjBaseInfoMapper;

    public ArolProjBaseInfoService(ArolProjBaseInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolProjBaseInfoMapper=_defaultBaseMapper;
    }
}
