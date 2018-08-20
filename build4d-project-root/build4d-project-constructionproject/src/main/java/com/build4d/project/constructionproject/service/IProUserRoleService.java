package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProUserRoleEntity;

import java.util.List;

public interface IProUserRoleService extends IBaseService<ProUserRoleEntity> {

    void bindUserToRole(int[] userIds,int roleId);

    List<ProUserRoleEntity> getByRoleId(int roleId);

    List<ProUserRoleEntity> getUsersByRoleId(int roleId);
}
