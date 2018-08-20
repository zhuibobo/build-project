package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryReplyEntity;

import java.util.List;

public interface IProAdvisoryReplyService extends IBaseService<ProAdvisoryReplyEntity> {
    List<ProAdvisoryReplyEntity> getByAdvisoryId(String advisoryId);
}
