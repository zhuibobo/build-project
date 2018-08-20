package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryReplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProAdvisoryReplyMapper extends BaseMapper<ProAdvisoryReplyEntity> {
    List<ProAdvisoryReplyEntity> selectByAdvisoryId(@Param("advisoryId") String advisoryId);
}