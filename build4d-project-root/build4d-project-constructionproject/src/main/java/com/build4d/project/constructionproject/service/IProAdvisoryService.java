package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryEntity;

import java.util.List;
import java.util.Map;

public interface IProAdvisoryService extends IBaseService<ProAdvisoryEntity> {
    List<Map> getAboutEng(String organId);
}
