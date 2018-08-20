package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProOuterOrganEntity;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface IProOuterOrganService extends IBaseService<ProOuterOrganEntity> {
    PageInfo<ProOuterOrganEntity> getPage(int pageNum, int pageSize, Map<String, Object> searchItemMap, String oorg_type);
}
