package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;
import com.build4d.project.dbaccess.dbentities.ProUserRoleEntity;

import java.util.List;
import java.util.Map;

public interface ProUserRoleMapper extends BaseMapper<ProUserRoleEntity> {

    ProUserRoleEntity selectByUserIdAndRoleId(Map map);

    List<ProUserRoleEntity> selectByRoleId(Integer integer);

    List<ProUserRoleEntity> selectUsersByRoleId(Integer integer);
}