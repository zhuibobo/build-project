package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ArolEFileInfoEntity;
import org.apache.ibatis.annotations.Param;

public interface ArolEFileInfoMapper extends BaseMapper<ArolEFileInfoEntity> {
    void deleteByEngSid(@Param("engSid") Integer engSid);
}