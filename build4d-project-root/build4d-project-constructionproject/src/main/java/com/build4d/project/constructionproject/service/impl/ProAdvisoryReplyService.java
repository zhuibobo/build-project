package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProAdvisoryReplyService;
import com.build4d.project.dbaccess.dao.ProAdvisoryReplyMapper;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryReplyEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProAdvisoryReplyService  extends BaseService<ProAdvisoryReplyEntity> implements IProAdvisoryReplyService {

    ProAdvisoryReplyMapper proAdvisoryReplyMapper;

    public ProAdvisoryReplyService(ProAdvisoryReplyMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proAdvisoryReplyMapper=_defaultBaseMapper;
    }

    @Override
    public List<ProAdvisoryReplyEntity> getByAdvisoryId(String advisoryId) {
        return proAdvisoryReplyMapper.selectByAdvisoryId(advisoryId);
    }
}
