package com.build4d.web.platform.controller;

import com.build4d.platform.organ.service.IOrganService;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.platform.manage.organ.OrganBeansConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {MybatisBeansConfig.class,OrganBeansConfig.class})
public class demo1Test {

    @Autowired
    private IOrganService organService;

    /*@Test
    public void conndb() throws Exception {
        OrganEntity organEntity=new OrganEntity();
        String organId1=UUID.randomUUID().toString();
        organEntity.setOrganId(organId1);
        organEntity.setIsVirtual(0);
        organEntity.setOrderNum(0);
        organEntity.setOrganDomain("");
        organEntity.setOrganName("zx");
        organEntity.setStatus(0);
        organService.insertNew(organEntity);
    }*/

    /*@Test
    public void conndb() {
        SqlSessionFactory sqlSessionFactory=getSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        OrganMapper organMapper=session.getMapper(OrganMapper.class);
        OrganEntity organEntity=new OrganEntity();
        organEntity.setOrganId(UUID.randomUUID().toString());
        organEntity.setIsVirtual(0);
        organEntity.setOrderNum(0);
        organEntity.setOrganDomain("");
        organEntity.setOrganName("zx");
        organEntity.setStatus(0);
        organMapper.insert(organEntity);
        session.commit();
        session.close();
    }

    public SqlSessionFactory getSessionFactory(){
        //mybatis的配置文件
        String resource = "MyBatisConf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        //SqlSession session = sessionFactory.openSession();
        *//**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         *//*
        return sessionFactory;
        *//*String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        person user = session.selectOne(statement, 1);
        System.out.println(user);*//*
    }*/
}