package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArolEngBaseInfoMapper extends BaseMapper<ArolEngBaseInfoEntity> {
    ArolEngBaseInfoEntity selectEngHouseSpecByPrimaryKey(int id);
    ArolEngBaseInfoEntity selectEngFacilitySpecByPrimaryKey(int id);
    ArolEngBaseInfoEntity selectEngOrgAndCodeByPrimaryKey(int id);
    List<Map<String,Object>> countEngByOrgan(@Param("currOrgId") Integer currOrgId);
    String getEngNo();
}