package com.build4d.project.constructionproject.vo;

import com.build4d.project.dbaccess.dbentities.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 注册报建
 * User: liwl
 * Date: 2018-05-15
 * To change this template use File | Settings | File Templates.
 */
public class RegisterVo {
    ProOrganEntity proOrganEntity;
    ProUserEntity proUserEntity;
    ArolProjBaseInfoEntity projBaseInfoEntity;
    ArolEngBaseInfoEntity engBaseInfoEntity;

    public ProOrganEntity getProOrganEntity() {
         if(proOrganEntity==null){
            proOrganEntity=new ProOrganEntity();
        }
        return proOrganEntity;
    }

    public void setProOrganEntity(ProOrganEntity proOrganEntity) {
        this.proOrganEntity = proOrganEntity;
    }

    public ProUserEntity getProUserEntity() {
        if(proUserEntity==null){
            proUserEntity=new ProUserEntity();
        }
        return proUserEntity;
    }

    public void setProUserEntity(ProUserEntity proUserEntity) {
        this.proUserEntity = proUserEntity;
    }

    public ArolProjBaseInfoEntity getProjBaseInfoEntity() {
        if(projBaseInfoEntity==null)
        {
            projBaseInfoEntity=new ArolProjBaseInfoEntity();
        }
        return projBaseInfoEntity;
    }

    public void setProjBaseInfoEntity(ArolProjBaseInfoEntity projBaseInfoEntity) {
        this.projBaseInfoEntity = projBaseInfoEntity;
    }
    public ArolEngBaseInfoEntity getEngBaseInfoEntity() {
        if(engBaseInfoEntity==null)
        {
            engBaseInfoEntity=new ArolEngBaseInfoEntity();
            engBaseInfoEntity.setEngOrgAndCode(new ArolEngOrgAndCodeEntity());
            engBaseInfoEntity.setEngSpecInfo(new ArolEngSpecInfoEntity());
        }
        return engBaseInfoEntity;
    }

    public void setEngBaseInfoEntity(ArolEngBaseInfoEntity engBaseInfoEntity) {
        this.engBaseInfoEntity = engBaseInfoEntity;
    }
}
