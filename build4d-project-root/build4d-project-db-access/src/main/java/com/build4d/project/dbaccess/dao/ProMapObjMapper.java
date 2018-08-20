package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProMapObjEntity;
import org.apache.ibatis.annotations.Param;

public interface ProMapObjMapper extends BaseMapper<ProMapObjEntity> {
    ProMapObjEntity selectByBindToMark(@Param("bindto") String bindto,@Param("bindtoid") String bindtoid);
}