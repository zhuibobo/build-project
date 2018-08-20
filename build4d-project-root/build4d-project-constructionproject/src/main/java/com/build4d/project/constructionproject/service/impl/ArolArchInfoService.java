package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolArchInfoService;
import com.build4d.project.dbaccess.dao.ArolArchInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolArchInfoEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public class ArolArchInfoService extends BaseService<ArolArchInfoEntity> implements IArolArchInfoService {

    ArolArchInfoMapper arolArchInfoMapper;

    public ArolArchInfoService(ArolArchInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolArchInfoMapper= _defaultBaseMapper;
    }

    @Override
    public PageInfo<ArolArchInfoEntity> searchArchJoinEngByMap(int pageNum, int pageSize, Map<String, Object> searchItemMap)
    {
        PageHelper.startPage(pageNum, pageSize);
        //PageHelper.
        List<ArolArchInfoEntity> lsit=arolArchInfoMapper.searchArchJoinEngByMap(searchItemMap);
        PageInfo<ArolArchInfoEntity> pageInfo = new PageInfo<ArolArchInfoEntity>(lsit);
        return pageInfo;
    }

    @Override
    public List<Map<String,Object>> countArchByMap(Map<String, Object> searchItemMap)
    {
        return arolArchInfoMapper.countArchByMap(searchItemMap);
    }


    @Override
    public void deleteByEngSid(Integer engSid)
    {
        arolArchInfoMapper.deleteByEngSid(engSid);
    }
}
