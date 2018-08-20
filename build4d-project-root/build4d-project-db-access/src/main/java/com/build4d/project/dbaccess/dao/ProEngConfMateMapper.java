package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProEngConfMateEntity;

import java.util.List;

public interface ProEngConfMateMapper extends BaseMapper<ProEngConfMateEntity> {

    List<ProEngConfMateEntity> selectByEngSid(Integer engSid);
}