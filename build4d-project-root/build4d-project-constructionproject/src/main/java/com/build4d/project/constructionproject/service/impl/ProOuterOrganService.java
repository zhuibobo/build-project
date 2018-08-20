package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProOuterOrganService;
import com.build4d.project.dbaccess.dao.ProOuterOrganMapper;
import com.build4d.project.dbaccess.dbentities.ProOuterOrganEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public class ProOuterOrganService extends BaseService<ProOuterOrganEntity> implements IProOuterOrganService {
    ProOuterOrganMapper proOuterOrganMapper;

    public ProOuterOrganService(ProOuterOrganMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proOuterOrganMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProOuterOrganEntity record) {
        if(id==0&&record.getOorgSid()==0){
            record.setOorgSid(proOuterOrganMapper.nextId());
        }
        return super.saveBySelective(id, record, new IAddBefore<ProOuterOrganEntity>() {
            @Override
            public ProOuterOrganEntity run(ProOuterOrganEntity item) {
                item.setOorgOrder(proOuterOrganMapper.nextOrderNum());
                return item;
            }
        });
    }

    @Override
    public PageInfo<ProOuterOrganEntity> getPage(int pageNum, int pageSize, Map<String, Object> searchItemMap, String oorg_type){
        PageHelper.startPage(pageNum, pageSize);
        List<ProOuterOrganEntity> lsit=proOuterOrganMapper.searchByOrganType(searchItemMap,oorg_type);
        PageInfo<ProOuterOrganEntity> pageInfo = new PageInfo<>(lsit);
        return pageInfo;
    }
}
