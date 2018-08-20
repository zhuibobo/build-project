package com.build4d.project.constructionproject.service;

import com.build4d.project.dbaccess.dbentities.ProRolePrivEntity;

import java.util.List;

public interface IProRolePrivService  {
    void overSaveMenuRolePriv(Integer roleId, String[] menuIdArray);

    List<ProRolePrivEntity> getRoleMenuPriv(int roleId);

    List<ProRolePrivEntity> getRolesMenuPriv(List<Integer> roleIdList);
}
