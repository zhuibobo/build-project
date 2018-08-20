package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProMapObjEntity;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/17
 * To change this template use File | Settings | File Templates.
 */
public interface IProMapObjService  extends IBaseService<ProMapObjEntity> {
    void coverObjMark(String bindto, String bindtoid, String lat, String lng, String distext);

    ProMapObjEntity getByBindTo(String bindto, String bindtoid);
}
