package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProFileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProFileMapper extends BaseMapper<ProFileEntity> {
    List<ProFileEntity> getByOuter(@Param("outerTableName") String outer_table_name,@Param("outerSid") String outer_sid);
}