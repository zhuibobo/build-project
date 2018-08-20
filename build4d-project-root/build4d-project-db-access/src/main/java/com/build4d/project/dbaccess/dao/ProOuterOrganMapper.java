package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProOuterOrganEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProOuterOrganMapper extends BaseMapper<ProOuterOrganEntity> {
    List<ProOuterOrganEntity> searchByOrganType(Map<String, Object> searchItemMap,@Param("oorg_type") String oorg_type);
}