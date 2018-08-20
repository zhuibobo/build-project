package com.build4d.web.beanconfig.mybatis;

import com.build4d.base.dbaccess.dao.Demo2Mapper;
import com.build4d.base.dbaccess.dao.DemoMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.dbaccess.dao.OrganMapper;
import com.build4d.base.dbaccess.exenum.EnableTypeEnum;
import com.build4d.base.dbaccess.exenum.UniversalIntEnumHandler;
import com.build4d.base.dbaccess.general.DBProp;
import com.build4d.project.dbaccess.dao.*;
import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

@Configuration
/*@MapperScan(basePackages = "com.build4d.base.dao")*/
public class MybatisBeansConfig {

    /*@Bean(destroyMethod="close")*/
    @Bean
    public ComboPooledDataSource dataSourceBean() throws PropertyVetoException {
        /*String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverName);
        comboPooledDataSource.setJdbcUrl("jdbc:sqlserver://120.24.254.101:1433; DatabaseName=Build4D");
        comboPooledDataSource.setUser("sa");
        comboPooledDataSource.setPassword("zhenx#88DATA");*/
        //String driverName="com.mysql.cj.jdbc.Driver";
        String driverName="com.mysql.jdbc.Driver";
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverName);
        //comboPooledDataSource.setJdbcUrl("");
        comboPooledDataSource.setJdbcUrl(DBProp.getValue("Url"));
        comboPooledDataSource.setUser(DBProp.getValue("User"));
        comboPooledDataSource.setPassword(DBProp.getValue("Password"));
       /* ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverName);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/build4d?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");*/
        return comboPooledDataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSourceBean());
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor=new PageInterceptor();
        Properties properties=new Properties();
        properties.getProperty("params","value1");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(PageInterceptor pageInterceptor) throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceBean());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatismappers/*.xml"));
        TypeHandler[] typeHandlers = {new UniversalIntEnumHandler(EnableTypeEnum.class)};
        sessionFactory.setTypeHandlers(typeHandlers);
        Interceptor[] interceptors={pageInterceptor};
        sessionFactory.setPlugins(interceptors);
        //sessionFactory.setTypeAliasesPackage("com.build4d");
        return sessionFactory;
    }

    @Bean
    public OrganMapper userMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(OrganMapper.class);
    }

    @Bean
    public DemoMapper demoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(DemoMapper.class);
    }

    @Bean
    public Demo2Mapper demo2Mapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(Demo2Mapper.class);
    }

    @Bean
    public Demo1Mapper demo1Mapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(Demo1Mapper.class);
    }

    @Bean
    public ArolArchInfoMapper arolArchInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolArchInfoMapper.class);
    }

    @Bean
    public ArolEFileInfoMapper arolEFileInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEFileInfoMapper.class);
    }

    @Bean
    public ArolEngBaseInfoMapper arolEngBaseInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngBaseInfoMapper.class);
    }

    @Bean
    public ArolEngFacilitySpecMapper arolEngFacilitySpecMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngFacilitySpecMapper.class);
    }

    @Bean
    public ArolEngHouseSpecMapper arolEngHouseSpecMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngHouseSpecMapper.class);
    }

    @Bean
    public ArolEngOrgAndCodeMapper arolEngOrgAndCodeMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngOrgAndCodeMapper.class);
    }

    @Bean
    public ArolEngOtherSpecMapper arolEngOtherSpecMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngOtherSpecMapper.class);
    }

    @Bean
    public ArolEngPBSpecMapper arolEngPBSpecMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngPBSpecMapper.class);
    }

    @Bean
    public ArolEngSpecInfoMapper arolEngSpecInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolEngSpecInfoMapper.class);
    }

    @Bean
    public ArolFileInfoMapper arolFileInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolFileInfoMapper.class);
    }

    @Bean
    public ArolProjBaseInfoMapper arolProjBaseInfoMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ArolProjBaseInfoMapper.class);
    }

    @Bean
    public ProOrganMapper proOrganMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProOrganMapper.class);
    }

    @Bean
    public ProUserMapper proUserMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProUserMapper.class);
    }

    @Bean
    public ProDictionaryMapper proDictionaryMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProDictionaryMapper.class);
    }

    @Bean
    public ProOpLogMapper proOpLogMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProOpLogMapper.class);
    }

    @Bean
    public ProRoleMapper proRoleMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProRoleMapper.class);
    }

    @Bean
    public ProRolePrivMapper proRolePrivMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProRolePrivMapper.class);
    }

    @Bean
    public ProUserRoleMapper proUserRoleMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProUserRoleMapper.class);
    }

    @Bean
    public ProEngConfMateMapper proEngConfMateMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProEngConfMateMapper.class);
    }

    @Bean
    public ProEngConfTypeMapper proEngConfTypeMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProEngConfTypeMapper.class);
    }

    @Bean
    public ProFileMapper proFileMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProFileMapper.class);
    }

    @Bean
    public ProOuterOrganMapper proOuterOrganMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProOuterOrganMapper.class);
    }

    @Bean
    public GeneralMapper generalMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(GeneralMapper.class);
    }

    @Bean
    public ProEngFlowLogMapper proEngFlowLogMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProEngFlowLogMapper.class);
    }

    @Bean
    public ProAdvisoryMapper proAdvisoryMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProAdvisoryMapper.class);
    }

    @Bean
    public ProAdvisoryReplyMapper proAdvisoryReplyMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProAdvisoryReplyMapper.class);
    }

    @Bean
    public ProMapObjMapper proMapObjMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(ProMapObjMapper.class);
    }

    /*@Bean
    public SpringManagedTransactionFactory getSpringManagedTransactionFactory() throws PropertyVetoException {
        return new SpringManagedTransactionFactory(getDataSourceBean(), TransactionIsolationLevel.SERIALIZABLE,true);
    }*/

    /*@Override
    @Bean(name="annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        try {
            transactionManager.setDataSource(getDataSourceBean());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return transactionManager;
    }*/

    /*@Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws PropertyVetoException {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSourceBean());
        return  sqlSessionFactoryBean;
    }*/

    /*@Bean
    public MapperFactoryBean getOrganMapperBean() throws PropertyVetoException {
        MapperFactoryBean mapperFactoryBean=new MapperFactoryBean();
        mapperFactoryBean.setMapperInterface(OrganMapper.class);
        mapperFactoryBean.setSqlSessionFactory((SqlSessionFactory) getSqlSessionFactoryBean());
        return mapperFactoryBean;
    }*/
}
