package com.build4d.web.beanconfig.project.constructionproject;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.tools.common.PathUtility;
import com.build4d.project.constructionproject.service.*;
import com.build4d.project.constructionproject.service.impl.*;
import com.build4d.project.dbaccess.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("ProjectBeansConfig")
@EnableTransactionManagement
public class ProjectBeansConfig {
    @Bean
    public IArolArchInfoService arolArchInfoService(GeneralMapper generalMapper, ArolArchInfoMapper arolArchInfoMapper, SqlSessionTemplate sqlSessionTemplate) {
        IArolArchInfoService arolArchInfoService=new ArolArchInfoService(arolArchInfoMapper,sqlSessionTemplate,generalMapper);
        return arolArchInfoService;
    }

    @Bean
    public IArolEFileInfoService arolEFileInfoService(GeneralMapper generalMapper, ArolEFileInfoMapper arolEFileInfoMapper , SqlSessionTemplate sqlSessionTemplate) {
        IArolEFileInfoService arolEFileInfoService =new ArolEFileInfoService(arolEFileInfoMapper,sqlSessionTemplate,generalMapper);
        return arolEFileInfoService;
    }

    @Bean
    public IArolEngBaseInfoService arolEngBaseInfoService(GeneralMapper generalMapper, ArolEngBaseInfoMapper arolEngBaseInfoMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngBaseInfoService arolEngBaseInfoService =new ArolEngBaseInfoService(arolEngBaseInfoMapper,sqlSessionTemplate,generalMapper);
        return arolEngBaseInfoService;
    }

    @Bean
    public IArolEngFacilitySpecService arolEngFacilitySpecService(GeneralMapper generalMapper, ArolEngFacilitySpecMapper arolEngFacilitySpecMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngFacilitySpecService arolEngFacilitySpecService =new ArolEngFacilitySpecService(arolEngFacilitySpecMapper,sqlSessionTemplate,generalMapper);
        return arolEngFacilitySpecService;
    }

    @Bean
    public IArolEngHouseSpecService arolEngHouseSpecService(GeneralMapper generalMapper, ArolEngHouseSpecMapper arolEngHouseSpecMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngHouseSpecService arolEngHouseSpecService =new ArolEngHouseSpecService(arolEngHouseSpecMapper,sqlSessionTemplate,generalMapper);
        return arolEngHouseSpecService;
    }

    @Bean
    public IArolEngOrgAndCodeService arolEngOrgAndCodeService(GeneralMapper generalMapper, ArolEngOrgAndCodeMapper arolEngOrgAndCodeMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngOrgAndCodeService arolEngOrgAndCodeService =new ArolEngOrgAndCodeService(arolEngOrgAndCodeMapper,sqlSessionTemplate,generalMapper);
        return arolEngOrgAndCodeService;
    }

    @Bean
    public IArolEngOtherSpecService arolEngOtherSpecService(GeneralMapper generalMapper, ArolEngOtherSpecMapper arolEngOtherSpecMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngOtherSpecService arolEngOtherSpecService =new ArolEngOtherSpecService(arolEngOtherSpecMapper,sqlSessionTemplate,generalMapper);
        return arolEngOtherSpecService;
    }

    @Bean
    public IArolEngPBSpecService arolEngPBSpecService(GeneralMapper generalMapper, ArolEngPBSpecMapper arolEngPBSpecMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngPBSpecService arolEngPBSpecService =new ArolEngPBSpecService(arolEngPBSpecMapper,sqlSessionTemplate,generalMapper);
        return arolEngPBSpecService;
    }

    @Bean
    public IArolEngSpecInfoService arolEngSpecInfoService(GeneralMapper generalMapper, ArolEngSpecInfoMapper arolEngSpecInfoMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolEngSpecInfoService arolEngSpecInfoService =new ArolEngSpecInfoService(arolEngSpecInfoMapper,sqlSessionTemplate,generalMapper);
        return arolEngSpecInfoService;
    }

    @Bean
    public IArolFileInfoService arolFileInfoService(GeneralMapper generalMapper, ArolFileInfoMapper arolFileInfoMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolFileInfoService arolFileInfoService =new ArolFileInfoService(arolFileInfoMapper,sqlSessionTemplate,generalMapper);
        return arolFileInfoService;
    }

    @Bean
    public IArolProjBaseInfoService arolProjBaseInfoService(GeneralMapper generalMapper, ArolProjBaseInfoMapper arolProjBaseInfoMapper  , SqlSessionTemplate sqlSessionTemplate) {
        IArolProjBaseInfoService arolProjBaseInfoService =new ArolProjBaseInfoService(arolProjBaseInfoMapper,sqlSessionTemplate,generalMapper);
        return arolProjBaseInfoService;
    }

    @Bean
    public IProOrganService proOrganService(GeneralMapper generalMapper, ProOrganMapper proOrganMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProOrganService proOrganService =new ProOrganService(proOrganMapper,sqlSessionTemplate,generalMapper);
        return proOrganService;
    }

    @Bean
    public IProUserService proUserService(GeneralMapper generalMapper, ProUserMapper proUserMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProUserService proUserService =new ProUserService(proUserMapper,sqlSessionTemplate,generalMapper);
        return proUserService;
    }

    @Bean
    public IProDictionaryService proDictionaryService(GeneralMapper generalMapper, ProDictionaryMapper proDictionaryMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProDictionaryService proDictionaryService =new ProDictionaryService(proDictionaryMapper,sqlSessionTemplate,generalMapper);
        return proDictionaryService;
    }

    @Bean
    public IProOpLogService proOpLogService(GeneralMapper generalMapper, ProOpLogMapper proOpLogMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProOpLogService proOpLogService =new ProOpLogService(proOpLogMapper,sqlSessionTemplate,generalMapper);
        return proOpLogService;
    }

    @Bean
    public IProRolePrivService proRolePrivService(GeneralMapper generalMapper, ProRolePrivMapper proRolePrivMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProRolePrivService proRolePrivService =new ProRolePrivService(proRolePrivMapper,sqlSessionTemplate,generalMapper);
        return proRolePrivService;
    }

    @Bean
    public IProRoleService proRoleService(GeneralMapper generalMapper, ProRoleMapper proRoleMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProRoleService proRoleService =new ProRoleService(proRoleMapper,sqlSessionTemplate,generalMapper);
        return proRoleService;
    }

    @Bean
    public IProUserRoleService proUserRoleService(GeneralMapper generalMapper, ProUserRoleMapper proUserRoleMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProUserRoleService proUserRoleService =new ProUserRoleService(proUserRoleMapper,sqlSessionTemplate,generalMapper);
        return proUserRoleService;
    }

    @Bean
    public IProEngConfMateService proEngConfMateService(GeneralMapper generalMapper, ProEngConfMateMapper proEngConfMateMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProEngConfMateService proEngConfMateService =new ProEngConfMateService(proEngConfMateMapper,sqlSessionTemplate,generalMapper);
        return proEngConfMateService;
    }

    @Bean
    public IProEngConfTypeService proEngConfTypeService(GeneralMapper generalMapper, ProEngConfTypeMapper proEngConfTypeMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProEngConfTypeService proEngConfTypeService =new ProEngConfTypeService(proEngConfTypeMapper,sqlSessionTemplate,generalMapper);
        return proEngConfTypeService;
    }

    @Bean
    public IProEngFlowLogService proEngFlowLogService(GeneralMapper generalMapper, ProEngFlowLogMapper proEngFlowLogMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProEngFlowLogService proEngFlowLogService =new ProEngFlowLogService(proEngFlowLogMapper,sqlSessionTemplate,generalMapper);
        return proEngFlowLogService;
    }

    @Bean
    public IProFileService proFileService(GeneralMapper generalMapper, ProFileMapper proFileMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProFileService proFileService =new ProFileService(proFileMapper,sqlSessionTemplate,generalMapper);
        return proFileService;
    }

    @Bean
    public IProOuterOrganService proOuterOrganService(GeneralMapper generalMapper, ProOuterOrganMapper proOuterOrganMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProOuterOrganService proOuterOrganService =new ProOuterOrganService(proOuterOrganMapper,sqlSessionTemplate,generalMapper);
        return proOuterOrganService;
    }

    @Bean
    public IProAdvisoryService proAdvisoryService(GeneralMapper generalMapper, ProAdvisoryMapper proAdvisoryMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProAdvisoryService proAdvisoryService =new ProAdvisoryService(proAdvisoryMapper,sqlSessionTemplate,generalMapper);
        return proAdvisoryService;
    }

    @Bean
    public IProAdvisoryReplyService proAdvisoryReplyService(GeneralMapper generalMapper, ProAdvisoryReplyMapper proAdvisoryReplyMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProAdvisoryReplyService proAdvisoryReplyService =new ProAdvisoryReplyService(proAdvisoryReplyMapper,sqlSessionTemplate,generalMapper);
        return proAdvisoryReplyService;
    }

    @Bean
    public IProMapObjService proMapObjService(GeneralMapper generalMapper, ProMapObjMapper proMapObjMapper, SqlSessionTemplate sqlSessionTemplate) {
        ProMapObjService proMapObjService =new ProMapObjService(proMapObjMapper,sqlSessionTemplate,generalMapper);
        return proMapObjService;
    }

    @Bean
    public MergeImageToPDFService mergeImageToPDFService(PathUtility pathUtility,IProFileService proFileService) {
        MergeImageToPDFService mergeImageToPDFService =new MergeImageToPDFService();
        mergeImageToPDFService.setPathUtility(pathUtility);
        mergeImageToPDFService.setProFileService(proFileService);
        return mergeImageToPDFService;
    }
}
