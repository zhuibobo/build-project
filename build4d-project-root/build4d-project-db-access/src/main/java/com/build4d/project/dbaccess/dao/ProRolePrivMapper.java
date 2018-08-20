package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProOpLogEntity;
import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;

import java.util.List;

public interface ProRolePrivMapper extends BaseMapper<ProRolePrivEntity> {

    void deleteRoleMenuPriv(Integer roleSid);

    List<ProRolePrivEntity> selectRoleMenuPriv(Integer roleSid);

    List<ProRolePrivEntity> selectRoleIdsMenuPrivs(List<Integer> roleIdList);
}