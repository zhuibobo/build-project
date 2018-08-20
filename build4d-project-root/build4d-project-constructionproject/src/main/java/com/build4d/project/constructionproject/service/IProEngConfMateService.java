package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProEngConfMateEntity;

import java.util.List;

public interface IProEngConfMateService extends IBaseService<ProEngConfMateEntity> {
    List<ProEngConfMateEntity> getListByEngSid(Integer engSid);
}
