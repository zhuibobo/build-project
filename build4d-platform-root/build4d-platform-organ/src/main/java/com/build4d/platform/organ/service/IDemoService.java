package com.build4d.platform.organ.service;

import com.build4d.base.dbaccess.dbentities.DemoEntity;
import com.build4d.base.service.IBaseService;

import java.util.List;

public interface IDemoService extends IBaseService<DemoEntity> {
    void saveErrorByTransactional(String key) throws Exception;

    void addBatch(List<DemoEntity> demoEntities);

    /*List<DemoEntity> getPage(int pageNum, int pageSize);*/
}
