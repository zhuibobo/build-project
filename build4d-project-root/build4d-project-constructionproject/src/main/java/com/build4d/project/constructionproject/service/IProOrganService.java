package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.constructionproject.vo.RegisterVo;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
public interface IProOrganService extends IBaseService<ProOrganEntity> {
    List<ProOrganEntity> selectEnabledOrgs(String orgType);

    PageInfo<ProOrganEntity> getPageFoOrganMange(int pageNum, int pageSize, Map<String,Object> searchItemMap);


    @Transactional(rollbackFor =Exception.class)
    int RegisterOrgAndProject(RegisterVo rVo) throws Exception;

    boolean existOrgan(Integer orgSid,String orgName,String orgCode);

    ProOrganEntity getByOrganCodeKey(String orgNoCode);

    List<ProOrganEntity> selectEnabledOrgsWithCreateByBuildOrgan(String organId);

}
