package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.service.impl.BaseService;
import com.build4d.project.constructionproject.service.IProAdvisoryService;
import com.build4d.project.constructionproject.service.IProDictionaryService;
import com.build4d.project.dbaccess.dao.ProAdvisoryMapper;
import com.build4d.project.dbaccess.dbentities.ProAdvisoryEntity;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProAdvisoryService  extends BaseService<ProAdvisoryEntity> implements IProAdvisoryService
{
    ProAdvisoryMapper proAdvisoryMapper;

    public ProAdvisoryService(ProAdvisoryMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);

        proAdvisoryMapper=_defaultBaseMapper;
    }

    @Override
    public int saveBySelective(String id, ProAdvisoryEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProAdvisoryEntity>() {
            @Override
            public ProAdvisoryEntity run(ProAdvisoryEntity item) {
                B4DSession session= B4DSessionUtility.getSession();
                item.setCreatetime(new Date());
                item.setOrganId(session.getOrganId());
                item.setOrganName(session.getOrganName());
                item.setUserId(session.getUserId());
                item.setUserName(session.getUserName());
                item.setStatus("咨询");
                return item;
            }
        });
    }

    @Override
    public List<Map> getAboutEng(String organId) {
        String sql="select IAMS_AROL_ENG_BASE_INFO.SID, ENG_NAME  " +
                "from iams_arol_eng_base_info " +
                "INNER JOIN iams_arol_proj_base_info on PROJ_SID=iams_arol_proj_base_info.SID " +
                "LEFT JOIN iams_arol_eng_org_and_code on iams_arol_eng_base_info.SID=iams_arol_eng_org_and_code.ENG_SID  " +
                "WHERE (IAMS_AROL_PROJ_BASE_INFO.CON_ORG_SID= "+organId+" or " +
                "IAMS_AROL_ENG_ORG_AND_CODE.DESIGN_ORG_SID= "+organId+" or " +
                "IAMS_AROL_ENG_ORG_AND_CODE.RECONNAISSANCE_ORG_SID= "+organId+" or " +
                "IAMS_AROL_ENG_ORG_AND_CODE.SUPERVISION_ORG_SID= "+organId+" or " +
                "IAMS_AROL_ENG_ORG_AND_CODE.CONSTRUCT_ORG_SID= "+organId+" )" +
                " ORDER by iams_arol_eng_base_info.SID ASC";
        return generalMapper.executeSql(sql);
    }
}
