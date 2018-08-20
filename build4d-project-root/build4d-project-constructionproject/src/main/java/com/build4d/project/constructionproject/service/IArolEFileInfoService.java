package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ArolEFileInfoEntity;

public interface IArolEFileInfoService extends IBaseService<ArolEFileInfoEntity> {
    void deleteByEngSid(Integer engSid);
}
