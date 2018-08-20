package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ArolFileInfoEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IArolFileInfoService  extends IBaseService<ArolFileInfoEntity> {
    //依据工程ID、工程类型及单位类型,合并目录及已经上传文件
    PageInfo<ArolFileInfoEntity> getFileListByEng(Integer engSid,Integer engType,Map<String,Object>  searchMap,int pageNum, int pageSize);

    @Transactional(rollbackFor =Exception.class)
    int saveBatchSelective(List<ArolFileInfoEntity> entities) throws Exception;

    List<Map<String,Object>> countUploadByMap(Map<String, Object> map);

    String cloneNextId(Integer engSid, String fileNo);

    void deleteByEngSid(Integer engSid);
}
