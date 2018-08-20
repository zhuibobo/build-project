package com.build4d.platform.organ.service.impl;

import com.build4d.base.dbaccess.dao.Demo2Mapper;
import com.build4d.base.dbaccess.dao.DemoMapper;
import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.dbaccess.dbentities.DemoEntity;
import com.build4d.base.dbaccess.dbentities.DemoEntity2;
import com.build4d.base.dbaccess.exenum.EnableTypeEnum;
import com.build4d.base.service.impl.BaseService;
import com.build4d.platform.organ.service.IDemo2Service;
import com.build4d.platform.organ.service.IDemoService;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class Demo2Service extends BaseService<DemoEntity2> implements IDemo2Service {

    Demo2Mapper demoMapper;

    public Demo2Service(Demo2Mapper _demoMapper, SqlSessionTemplate sqlSessionTemplate, GeneralMapper generalMapper){
        super(_demoMapper,sqlSessionTemplate,generalMapper);
        demoMapper= _demoMapper;
    }
}
