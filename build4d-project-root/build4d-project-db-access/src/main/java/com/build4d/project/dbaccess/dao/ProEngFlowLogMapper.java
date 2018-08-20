package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProEngFlowLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProEngFlowLogMapper extends BaseMapper<ProEngFlowLogEntity> {
    void deleteByEngSid(@Param("engSid") Integer engSid);

    List<Map> searchLog(Map<String, Object> searchMap);
}