package com.build4d.web.platform.service;

import com.build4d.base.dbaccess.dbentities.DemoEntity2;
import com.build4d.platform.organ.service.IDemo2Service;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.platform.manage.organ.OrganBeansConfig;
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
@ContextConfiguration(classes= {MybatisBeansConfig.class,OrganBeansConfig.class})
public class Demo2ServiceTest {
    @Autowired
    private IDemo2Service demo2Service;

    @Before
    public void before() {
        System.out.println("@Before");
    }

    @Test
    public void crudSimple(){
        DemoEntity2 demoEntity=new DemoEntity2();
        String key= UUID.randomUUID().toString();
        addDemo(key);
        DemoEntity2 dbDemoEntity=demo2Service.getByPrimaryKey(key);
        Assert.assertEquals(key,dbDemoEntity.getDemoId());
        //update
        dbDemoEntity.setNameText("update_demo1");
        demo2Service.updateByKey(dbDemoEntity);
        dbDemoEntity=demo2Service.getByPrimaryKey(key);
        Assert.assertEquals("update_demo1",dbDemoEntity.getNameText());
        //demo2Service.deleteAll();
    }

    private String addDemo(String key) {
        DemoEntity2 demoEntity=new DemoEntity2();
        demoEntity.setDemoId(key);
        demoEntity.setfDate(new Date());
        demoEntity.setIsVirtual(1);
        demoEntity.setNameText("demo1"+key);
        demoEntity.setOrderNum(1);
        demoEntity.setStatus(1);
        demo2Service.add(demoEntity);
        return key;
    }
}
