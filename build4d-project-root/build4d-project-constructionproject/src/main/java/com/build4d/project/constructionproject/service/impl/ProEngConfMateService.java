package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProEngConfMateService;
import com.build4d.project.dbaccess.dao.ProEngConfMateMapper;
import com.build4d.project.dbaccess.dao.ProOpLogMapper;
import com.build4d.project.dbaccess.dbentities.ProEngConfMateEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProEngConfMateService extends BaseService<ProEngConfMateEntity> implements IProEngConfMateService {

    ProEngConfMateMapper proEngConfMateMapper;

    public ProEngConfMateService(ProEngConfMateMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proEngConfMateMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProEngConfMateEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProEngConfMateEntity>() {
            @Override
            public ProEngConfMateEntity run(ProEngConfMateEntity item) {
                item.setMateOrder(proEngConfMateMapper.nextOrderNum());
                return item;
            }
        });
    }

    @Override
    public List<ProEngConfMateEntity> getListByEngSid(Integer engSid) {
        return proEngConfMateMapper.selectByEngSid(engSid);
    }
}