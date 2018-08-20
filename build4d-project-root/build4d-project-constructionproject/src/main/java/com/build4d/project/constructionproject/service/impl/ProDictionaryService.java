package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProDictionaryService;
import com.build4d.project.dbaccess.dao.ProDictionaryMapper;
import com.build4d.project.dbaccess.dbentities.ProDictionaryEntity;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProDictionaryService extends BaseService<ProDictionaryEntity> implements IProDictionaryService {

    ProDictionaryMapper proDictionaryMapper;

    public ProDictionaryService(ProDictionaryMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proDictionaryMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProDictionaryEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProDictionaryEntity>() {
            @Override
            public ProDictionaryEntity run(ProDictionaryEntity item) {
                //获取父亲节点,并更新节点内容
                ProDictionaryEntity parentEnt=proDictionaryMapper.selectByPrimaryKey(item.getDictParentId());
                parentEnt.setDictChildCount(parentEnt.getDictChildCount()+1);
                proDictionaryMapper.updateByPrimaryKey(parentEnt);

                item.setDictOrder(proDictionaryMapper.nextOrderNum());
                item.setDictParentIdlist(parentEnt.getDictParentIdlist()+"※"+item.getDictSid());
                return item;
            }
        });
    }


}
