package com.build4d.base.dbaccess.dao;

import com.build4d.base.dbaccess.dbentities.DemoEntity;

import java.util.List;

public interface DemoMapper extends BaseMapper<DemoEntity> {
    /*int deleteByPrimaryKey(String demoId);

    int insert(DemoEntity record);

    int insertSelective(DemoEntity record);*/

    /*List<DemoEntity> selectByExample(DemoEntityExample example);*/

    /*DemoEntity selectByPrimaryKey(String demoId);

    int updateByPrimaryKeySelective(DemoEntity record);

    int updateByPrimaryKey(DemoEntity record);*/

    int insertBatch(List<DemoEntity> demoEntities);
}