package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ArolFileInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArolFileInfoMapper extends BaseMapper<ArolFileInfoEntity> {
    void buildCatalogByEng(@Param("engSid") Integer engSid,@Param("engType") Integer engType);
    int countByEng(Map<String,Object> map);
    List<Map<String,Object>> countUploadByMap(Map<String,Object> map);
    String cloneNextId(@Param("engSid") Integer engSid, @Param("fileNo") String fileNo);
    void deleteByEngSid(@Param("engSid") Integer engSid);
}