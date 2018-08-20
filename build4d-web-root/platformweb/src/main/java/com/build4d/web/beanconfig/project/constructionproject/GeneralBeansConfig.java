package com.build4d.web.beanconfig.project.constructionproject;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.project.constructionproject.service.IProjectDemo1Service;
import com.build4d.project.constructionproject.service.impl.ProjectDemo1Service;
import com.build4d.project.dbaccess.dao.Demo1Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("ProjectGeneralBeansConfig")
@EnableTransactionManagement
public class GeneralBeansConfig {
    @Bean
    public IProjectDemo1Service demo1Service(GeneralMapper generalMapper, Demo1Mapper demoMapper, SqlSessionTemplate sqlSessionTemplate) {
        IProjectDemo1Service demo1Service=new ProjectDemo1Service(demoMapper,sqlSessionTemplate,generalMapper);
        return demo1Service;
    }
}
