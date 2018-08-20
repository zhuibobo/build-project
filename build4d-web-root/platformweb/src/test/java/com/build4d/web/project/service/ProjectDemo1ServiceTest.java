package com.build4d.web.project.service;

import com.build4d.platform.organ.service.IDemo2Service;
import com.build4d.project.constructionproject.service.IProjectDemo1Service;
import com.build4d.project.dbaccess.dbentities.Demo1Entity;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.platform.manage.organ.OrganBeansConfig;
import com.build4d.web.beanconfig.project.constructionproject.GeneralBeansConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {MybatisBeansConfig.class,GeneralBeansConfig.class})
public class ProjectDemo1ServiceTest {

    @Autowired
    private IProjectDemo1Service projectDemo1Service;

    @Before
    public void before() {
        System.out.println("@Before");
    }

    @Test
    public void crudSimple(){
        Demo1Entity demoEntity=new Demo1Entity();
        String key= UUID.randomUUID().toString();
        addDemo(key);
        Demo1Entity dbDemoEntity=projectDemo1Service.getByPrimaryKey(key);
        Assert.assertEquals(key,dbDemoEntity.getDemoId());
        //update
        dbDemoEntity.setNameText("update_demo1");
        projectDemo1Service.updateByKey(dbDemoEntity);
        dbDemoEntity=projectDemo1Service.getByPrimaryKey(key);
        Assert.assertEquals("update_demo1",dbDemoEntity.getNameText());
        //demo2Service.deleteAll();
    }

    private String addDemo(String key) {
        Demo1Entity demoEntity=new Demo1Entity();
        demoEntity.setDemoId(key);
        demoEntity.setfDate(new Date());
        demoEntity.setIsVirtual(1);
        demoEntity.setNameText("demo1"+key);
        demoEntity.setOrderNum(1);
        demoEntity.setStatus(1);
        projectDemo1Service.add(demoEntity);
        return key;
    }
}
