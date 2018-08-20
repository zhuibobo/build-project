package com.build4d.web.platform.service;

import com.build4d.base.dbaccess.dbentities.OrganEntity;
import com.build4d.platform.organ.service.IOrganService;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.platform.manage.organ.OrganBeansConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @Author: zhuangrb
 * @Date: 2018/4/5
 * @Description:
 * @Version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {MybatisBeansConfig.class,OrganBeansConfig.class})
public class OrganServiceTest {
    @Autowired
    private IOrganService organService;

    @Before
    public void before() {
        System.out.println("@Before");
    }

    @Test
    public void insert() throws Exception {
        //org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        OrganEntity organEntity=new OrganEntity();
        String organId= UUID.randomUUID().toString();
        organEntity.setOrganId(organId);
        organEntity.setIsVirtual(0);
        organEntity.setOrderNum(0);
        organEntity.setOrganDomain("");
        organEntity.setOrganName("zx");
        organEntity.setStatus(0);
        organService.addSelective(organEntity);
        OrganEntity neworganEntity=organService.getByPrimaryKey(organId);
        Assert.assertEquals(organId,neworganEntity.getOrganId());
        /*List<Map> organs=organService.executeSql("select * from TB4D_ORGAN");
        Assert.assertEquals("1","1");
        Map sqlMap=new HashMap();
        sqlMap.put("sql","select * from TB4D_ORGAN where ORGAN_ID=#{ORGAN_ID}");
        sqlMap.put("ORGAN_ID",organId);
        List<Map> organsMap=organService.executeSqlMap(sqlMap);
        Assert.assertEquals(1,organsMap.size());
        Assert.assertEquals("zx",organsMap.get(0).get("ORGAN_NAME"));*/
    }
}
