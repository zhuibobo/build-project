package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ArolArchInfoEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface IArolArchInfoService extends IBaseService<ArolArchInfoEntity> {
    PageInfo<ArolArchInfoEntity> searchArchJoinEngByMap(int pageNum, int pageSize, Map<String, Object> searchItemMap);

    List<Map<String,Object>> countArchByMap(Map<String, Object> searchItemMap);

    abstract void deleteByEngSid(Integer engSid);
}
