package com.build4d.web.beanconfig.platform.manage.organ;

import com.build4d.base.dbaccess.dao.Demo2Mapper;
import com.build4d.base.dbaccess.dao.DemoMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.dbaccess.dao.OrganMapper;
import com.build4d.platform.organ.service.IDemo2Service;
import com.build4d.platform.organ.service.IDemoService;
import com.build4d.platform.organ.service.IOrganService;
import com.build4d.platform.organ.service.impl.Demo2Service;
import com.build4d.platform.organ.service.impl.DemoService;
import com.build4d.platform.organ.service.impl.OrganService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class OrganBeansConfig {

    @Bean
    public IDemoService demoService(GeneralMapper generalMapper, DemoMapper demoMapper, SqlSessionTemplate sqlSessionTemplate) {
        IDemoService demoService=new DemoService(demoMapper,sqlSessionTemplate,generalMapper);
        //demoService.setDefaultBaseMapper(demoMapper);
        //demoService.setGeneralMapper(generalMapper);
        //demoService.setSqlSessionTemplate(sqlSessionTemplate);
        return demoService;
    }

    @Bean
    public IOrganService organService(GeneralMapper generalMapper, OrganMapper organMapper, SqlSessionTemplate sqlSessionTemplate) {
        IOrganService organService=new OrganService(organMapper,sqlSessionTemplate,generalMapper);
        //organService.setDefaultBaseMapper(organMapper);
        //organService.setGeneralMapper(generalMapper);
        //organService.setSqlSessionTemplate(sqlSessionTemplate);
        return organService;
    }

    @Bean
    public IDemo2Service demo2Service(GeneralMapper generalMapper, Demo2Mapper demo2Mapper, SqlSessionTemplate sqlSessionTemplate) {
        IDemo2Service demoService=new Demo2Service(demo2Mapper,sqlSessionTemplate,generalMapper);
        return demoService;
    }
}
