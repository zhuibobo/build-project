package com.build4d.project.constructionproject.service.impl;


import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.dbaccess.dao.ProEngFlowLogMapper;
import com.build4d.project.dbaccess.dbentities.ProEngFlowLogEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class ProEngFlowLogService extends BaseService<ProEngFlowLogEntity> implements IProEngFlowLogService {

    ProEngFlowLogMapper proEngFlowLogMapper;

    public ProEngFlowLogService(ProEngFlowLogMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proEngFlowLogMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(int id, ProEngFlowLogEntity entity) {
        try {
            B4DSession session= B4DSessionUtility.getSession();
            entity.setUserSid(Integer.parseInt(session.getUserId()));
            entity.setUserName(session.getUserName());
            entity.setOrganSid(Integer.parseInt(session.getOrganId()));
            entity.setOrganName(session.getOrganName());
        }catch (Exception ex)
        { }
        entity.setOpDate(new Date());
        return super.saveBySelective(id,entity);
    }

    @Override
    public void deleteByEngSid(Integer engSid)
    {
        proEngFlowLogMapper.deleteByEngSid(engSid);
    }

    @Override
    public PageInfo<Map> searchLog(Integer page_num, Integer page_size, Map<String, Object> searchMap) {
        PageHelper.startPage(page_num, page_size);
        //PageHelper.
        List<Map> lsit=proEngFlowLogMapper.searchLog(searchMap);
        PageInfo<Map> pageInfo = new PageInfo<Map>(lsit);
        return pageInfo;
    }
}
