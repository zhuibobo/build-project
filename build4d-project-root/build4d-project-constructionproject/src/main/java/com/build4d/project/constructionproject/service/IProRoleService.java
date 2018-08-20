package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProRoleEntity;

import java.util.List;

public interface IProRoleService extends IBaseService<ProRoleEntity> {
    List<ProRoleEntity> getRolesByUserId(Integer userSid);
}
