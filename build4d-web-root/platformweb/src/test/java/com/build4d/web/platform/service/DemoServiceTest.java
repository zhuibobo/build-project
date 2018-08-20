package com.build4d.web.platform.service;

import com.build4d.base.dbaccess.dbentities.DemoEntity;
import com.build4d.base.dbaccess.exenum.EnableTypeEnum;
import com.build4d.base.tools.general.impl.CalculateRunTimeDefault;
import com.build4d.platform.organ.service.IDemoService;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.platform.manage.organ.OrganBeansConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {MybatisBeansConfig.class,OrganBeansConfig.class})
public class DemoServiceTest {
    @Autowired
    private IDemoService demoService;

    @Before
    public void before() {
        System.out.println("@Before");
    }

    @Test
    public void crudSimple(){
        DemoEntity demoEntity=new DemoEntity();
        String key= UUID.randomUUID().toString();
        addDemo(key);
        DemoEntity dbDemoEntity=demoService.getByPrimaryKey(key);
        Assert.assertEquals(key,dbDemoEntity.getDemoId());
        //update
        dbDemoEntity.setNameText("update_demo1");
        demoService.updateByKey(dbDemoEntity);
        dbDemoEntity=demoService.getByPrimaryKey(key);
        Assert.assertEquals("update_demo1",dbDemoEntity.getNameText());
        demoService.deleteAll();
    }

    private String addDemo(String key) {
        DemoEntity demoEntity=new DemoEntity();
        demoEntity.setDemoId(key);
        demoEntity.setfDate(new Date());
        demoEntity.setIsVirtual(1);
        demoEntity.setNameText("demo1"+key);
        demoEntity.setOrderNum(1);
        demoEntity.setStatus(EnableTypeEnum.enable);
        demoService.add(demoEntity);
        return key;
    }

    private void addBatch(int num){
        List<DemoEntity> demoEntities=new ArrayList<>();
        for (int i=0;i<1000;i++){
            DemoEntity demoEntity=new DemoEntity();
            demoEntity.setDemoId(String.valueOf(i));
            demoEntity.setfDate(new Date());
            demoEntity.setIsVirtual(1);
            demoEntity.setNameText("demo1"+i);
            demoEntity.setOrderNum(i);
            demoEntity.setStatus(EnableTypeEnum.enable);
            demoEntities.add(demoEntity);
        }
        demoService.addBatch(demoEntities);
        //demoService.add
    }
    /*@Rule
    public ExpectedException thrown= ExpectedException.none();*/
    @Test
    public void saveErrorByTransactional(){
        String key=UUID.randomUUID().toString();
        //thrown.expect(IllegalArgumentException.class);
        //thrown.expectMessage("age should be +ve");
        try {
            demoService.saveErrorByTransactional(key);
        } catch (Exception e) {
            Assert.assertEquals("异常回滚",e.getMessage());
            DemoEntity dbDemoEntity = demoService.getByPrimaryKey(key);
            Assert.assertNull(dbDemoEntity);
        }
    }

    /*@Test
    public void addSingle(){
        CalculateRunTimeDefault.CalculateRunTimeDefault(() -> {
            demoService.deleteAll();
            for (int i=0;i<1000;i++){
                addDemo(String.valueOf(i));
            }

        },"循环插入");
    }

    @Test
    public void addBatch(){
        CalculateRunTimeDefault.CalculateRunTimeDefault(() -> {
            demoService.deleteAll();
            addBatch(1000);
        },"批量插入");
    }*/

    /*@Test
    public void getPage(){
        demoService.deleteAll();
        addBatch(1000);
        List<DemoEntity> pageEnt=demoService.getPage(1,10);
        Assert.assertEquals(10,pageEnt.size());
        for (int i=0;i<10;i++){
            Assert.assertEquals(i,(int)pageEnt.get(i).getOrderNum());
        }
    }*/
}
