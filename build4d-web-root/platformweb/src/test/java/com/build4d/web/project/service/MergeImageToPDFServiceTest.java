package com.build4d.web.project.service;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.PathUtility;
import com.build4d.project.constructionproject.service.impl.MergeImageToPDFService;
import com.build4d.project.dbaccess.dbentities.Demo1Entity;
import com.build4d.web.beanconfig.mybatis.MybatisBeansConfig;
import com.build4d.web.beanconfig.project.constructionproject.GeneralBeansConfig;
import com.build4d.web.beanconfig.sys.RootConfig;
import com.build4d.web.beanconfig.sys.WebConfig;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class MergeImageToPDFServiceTest {
    @Autowired
    PathUtility pathUtility;

    @Test
    public void MergeImageToPDFService_ToPDF() throws IOException, Build4DGenerallyException {
        MergeImageToPDFService mergeImageToPDFService=new MergeImageToPDFService();
        mergeImageToPDFService.setPathUtility(pathUtility);
        byte[] data=mergeImageToPDFService.ToPDF(null);
        FileUtils.writeByteArrayToFile(new File("D:\\test1.pdf"), data);
    }
}
