package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ProOrganMapper  extends BaseMapper<ProOrganEntity> {
    List<ProOrganEntity> selectEnabledOrgs(@Param("orgType") String orgType);
    ProOrganEntity selectByOrgCode(@Param("orgCode") String orgCode);
    List<ProOrganEntity> selectOrganManage(Map<String, Object> searchItemMap);
    boolean existOrgan(@Param("orgSid") Integer orgSid,@Param("orgName") String orgName,@Param("orgCode") String orgCode);

    List<ProOrganEntity> selectEnabledOrgsWithCreateByBuildOrgan(@Param("organId") String organId);
}