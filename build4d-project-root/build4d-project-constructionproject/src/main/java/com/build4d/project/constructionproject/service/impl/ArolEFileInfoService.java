package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEFileInfoService;
import com.build4d.project.dbaccess.dao.ArolEFileInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolEFileInfoEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ArolEFileInfoService extends BaseService<ArolEFileInfoEntity> implements IArolEFileInfoService {
    ArolEFileInfoMapper arolEFileInfoMapper;

    public ArolEFileInfoService(ArolEFileInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolEFileInfoMapper=_defaultBaseMapper;
    }


    @Override
    public void deleteByEngSid(Integer engSid)
    {
        arolEFileInfoMapper.deleteByEngSid(engSid);
    }
}
