package com.build4d.platform.organ.service.impl;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.base.dbaccess.dao.DemoMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.dbaccess.dbentities.DemoEntity;
import com.build4d.base.dbaccess.exenum.EnableTypeEnum;
import com.build4d.base.service.impl.BaseService;
import com.build4d.platform.organ.service.IDemoService;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class DemoService extends BaseService<DemoEntity> implements IDemoService {

    DemoMapper demoMapper;

    public DemoService(DemoMapper _demoMapper, SqlSessionTemplate sqlSessionTemplate, GeneralMapper generalMapper){
        super(_demoMapper,sqlSessionTemplate,generalMapper);
        demoMapper= _demoMapper;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveErrorByTransactional(String key) throws Exception {
        DemoEntity demoEntity=new DemoEntity();
        demoEntity.setDemoId(key);
        demoEntity.setfDate(new Date());
        demoEntity.setIsVirtual(1);
        demoEntity.setNameText("demo1");
        demoEntity.setOrderNum(1);
        demoEntity.setStatus(EnableTypeEnum.enable);
        demoMapper.insert(demoEntity);
        throw new Exception("异常回滚");
    }

    @Override
    public void addBatch(List<DemoEntity> demoEntities) {
        demoMapper.insertBatch(demoEntities);
    }

    /*@Override
    public List<DemoEntity> getPage(int pageNum, int pageSize){
        //PageHelper.
        PageHelper.startPage(pageNum, pageSize);
        return demoMapper.selectAll();
    }*/

    /*@Override
    public void addBatch(List<DemoEntity> demoEntities) {
        defaultBaseMapper.
    }*/

}
