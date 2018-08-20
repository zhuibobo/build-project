package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ArolArchInfoEntity;
import com.build4d.project.dbaccess.dbentities.Demo1Entity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArolArchInfoMapper extends BaseMapper<ArolArchInfoEntity> {
    List<ArolArchInfoEntity> searchArchJoinEngByMap(Map<String,Object> searchItemMap);
    List<Map<String,Object>>  countArchByMap(Map<String,Object> searchItemMap);
    void deleteByEngSid(@Param("engSid") Integer engSid);
}