package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IArolEngBaseInfoService;
import com.build4d.project.constructionproject.service.IArolFileInfoService;
import com.build4d.project.constructionproject.service.IProEngConfMateService;
import com.build4d.project.dbaccess.dao.ArolFileInfoMapper;
import com.build4d.project.dbaccess.dbentities.ArolFileInfoEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.map.LinkedMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArolFileInfoService   extends BaseService<ArolFileInfoEntity> implements IArolFileInfoService {
    ArolFileInfoMapper arolFileInfoMapper;

    //工程信息
    @Autowired
    IArolEngBaseInfoService arolEngBaseInfoService;

    @Autowired
    IProEngConfMateService proEngConfMateService;

    public ArolFileInfoService(ArolFileInfoMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        arolFileInfoMapper=_defaultBaseMapper;
    }

    //依据工程ID、工程类型及单位类型,合并目录及已经上传文件
    @Override
    public PageInfo<ArolFileInfoEntity> getFileListByEng(Integer engSid,Integer engType,Map<String,Object>  searchMap,int pageNum, int pageSize){
        Map<String,Object> wm=new HashMap<String,Object>();
        wm.put("engSid",engSid);
        wm.put("engType",engType);
        int count=arolFileInfoMapper.countByEng(wm);
        if(count==0){
            arolFileInfoMapper.buildCatalogByEng(engSid,engType);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ArolFileInfoEntity> arolFileInfoEntities= arolFileInfoMapper.searchByMap(searchMap);
        PageInfo<ArolFileInfoEntity>  pageInfo = new PageInfo<ArolFileInfoEntity>(arolFileInfoEntities);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public int saveBatchSelective(List<ArolFileInfoEntity> entities) throws Exception {
        try {
            for (ArolFileInfoEntity entity : entities) {
                if (entity.getSid() == null) {
                    entity.setSid(super.getNextId());
                }
                super.saveBySelective(entity.getSid(), entity);
            }
            return 1;
        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public  List<Map<String,Object>> countUploadByMap(Map<String, Object> map)
    {
        return arolFileInfoMapper.countUploadByMap(map);
    }

    @Override
    public String cloneNextId(Integer engSid, String fileNo)
    {
        return arolFileInfoMapper.cloneNextId(engSid,fileNo);
    }

    @Override
    public void deleteByEngSid(Integer engSid)
    {
        arolFileInfoMapper.deleteByEngSid(engSid);
    }
}
