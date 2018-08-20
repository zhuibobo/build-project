package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;

import java.util.List;

public interface ProRoleMapper extends BaseMapper<ProRoleEntity> {

    List<ProRoleEntity> selectRolesByUserId(Integer userSid);
}