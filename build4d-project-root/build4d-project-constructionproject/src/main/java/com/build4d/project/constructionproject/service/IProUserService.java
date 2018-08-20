package com.build4d.project.constructionproject.service;

import com.build4d.base.service.IBaseService;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;

public interface IProUserService extends IBaseService<ProUserEntity> {
    void resetPassword(int userSid);

    boolean existAccount(Integer userSid, String userAccount);

    ProUserEntity getUserByAccountAndPwd(String userAccount, String userPsw);

    ProUserEntity getUserByWXNo(String openid);
}
