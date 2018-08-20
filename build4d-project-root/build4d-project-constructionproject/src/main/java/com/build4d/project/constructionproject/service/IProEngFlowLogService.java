package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProEngFlowLogEntity;
import com.github.pagehelper.PageInfo;

import java.util.Map;
import java.util.List;

public interface IProEngFlowLogService extends IBaseService<ProEngFlowLogEntity> {

    void deleteByEngSid(Integer engSid);

    PageInfo<Map> searchLog(Integer page_num, Integer page_size, Map<String, Object> searchMap);
}
