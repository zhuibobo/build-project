package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProEngConfTypeService;
import com.build4d.project.dbaccess.dao.ProEngConfTypeMapper;
import com.build4d.project.dbaccess.dbentities.ProEngConfTypeEntity;
import org.mybatis.spring.SqlSessionTemplate;

public class ProEngConfTypeService extends BaseService<ProEngConfTypeEntity> implements IProEngConfTypeService {

    ProEngConfTypeMapper proEngConfTypeMapper;

    public ProEngConfTypeService(ProEngConfTypeMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proEngConfTypeMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProEngConfTypeEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProEngConfTypeEntity>() {
            @Override
            public ProEngConfTypeEntity run(ProEngConfTypeEntity item) {
                item.setEngOrder(proEngConfTypeMapper.nextOrderNum());
                return item;
            }
        });
    }
}