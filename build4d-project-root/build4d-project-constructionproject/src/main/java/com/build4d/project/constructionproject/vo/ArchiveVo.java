package com.build4d.project.constructionproject.vo;

import com.build4d.project.dbaccess.dbentities.ArolArchInfoEntity;
import com.build4d.project.dbaccess.dbentities.ArolFileInfoEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liwl
 * Date: 2018-06-11
 * To change this template use File | Settings | File Templates.
 */
public class ArchiveVo {
    List<ArolFileInfoEntity> fileInfoEntityList;
    ArolArchInfoEntity archInfoEntity;

    public List<ArolFileInfoEntity> getFileInfoEntityList() {
        return fileInfoEntityList;
    }

    public void setFileInfoEntityList(List<ArolFileInfoEntity> fileInfoEntityList) {
        this.fileInfoEntityList = fileInfoEntityList;
    }

    public ArolArchInfoEntity getArchInfoEntity() {
        return archInfoEntity;
    }

    public void setArchInfoEntity(ArolArchInfoEntity archInfoEntity) {
        this.archInfoEntity = archInfoEntity;
    }
}
