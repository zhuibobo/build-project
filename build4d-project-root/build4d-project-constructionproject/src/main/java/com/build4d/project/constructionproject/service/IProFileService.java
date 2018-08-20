package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProFileEntity;

import java.util.List;

public interface IProFileService extends IBaseService<ProFileEntity> {
    List<ProFileEntity> getByOuter(String outer_table_name, String outer_sid);
}
