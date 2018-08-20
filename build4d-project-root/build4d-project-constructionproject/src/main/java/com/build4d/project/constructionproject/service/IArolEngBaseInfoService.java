package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;

import java.util.Map;
import java.util.List;

public interface IArolEngBaseInfoService extends IBaseService<ArolEngBaseInfoEntity> {
    ArolEngBaseInfoEntity selectEngHouseSpecByPrimaryKey(int id);

    ArolEngBaseInfoEntity selectEngFacilitySpecByPrimaryKey(int id);

    ArolEngBaseInfoEntity selectEngOrgAndCodeByPrimaryKey(int id);

    List<Map<String,Object>> countEngByOrgan(Integer currOrgId);
}
