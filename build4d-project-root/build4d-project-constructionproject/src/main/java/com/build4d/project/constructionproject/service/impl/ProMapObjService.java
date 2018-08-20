package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.impl.BaseService;
import com.build4d.base.tools.common.UUIDUtility;
import com.build4d.project.constructionproject.service.IProAdvisoryReplyService;
import com.build4d.project.constructionproject.service.IProMapObjService;
import com.build4d.project.dbaccess.dao.ProMapObjMapper;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryReplyEntity;
import com.build4d.project.dbaccess.dbentities.ProMapObjEntity;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/17
 * To change this template use File | Settings | File Templates.
 */
public class ProMapObjService  extends BaseService<ProMapObjEntity> implements IProMapObjService {
    ProMapObjMapper proMapObjMapper;

    public ProMapObjService(ProMapObjMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proMapObjMapper=_defaultBaseMapper;
    }

    @Override
    public void coverObjMark(String bindto, String bindtoid, String lat, String lng, String distext) {
        ProMapObjEntity mapObjEntity=proMapObjMapper.selectByBindToMark(bindto,bindtoid);
        if(mapObjEntity==null){
            mapObjEntity=new ProMapObjEntity();
            mapObjEntity.setBindto(bindto);
            mapObjEntity.setBindtoid(bindtoid);
            mapObjEntity.setDistext(distext);
            mapObjEntity.setId(UUIDUtility.getUUID());
            mapObjEntity.setType("Mark");
            mapObjEntity.setLat(lat);
            mapObjEntity.setLng(lng);
            proMapObjMapper.insertSelective(mapObjEntity);
        }
        else
        {
            mapObjEntity.setLat(lat);
            mapObjEntity.setLng(lng);
            mapObjEntity.setDistext(distext);
            proMapObjMapper.updateByPrimaryKeySelective(mapObjEntity);
        }
    }

    @Override
    public ProMapObjEntity getByBindTo(String bindto, String bindtoid) {
        return proMapObjMapper.selectByBindToMark(bindto,bindtoid);
    }
}
