package com.build4d.project.dbaccess.dao;

import com.build4d.base.dbaccess.dao.BaseMapper;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import org.apache.ibatis.annotations.Param;

public interface ProUserMapper  extends BaseMapper<ProUserEntity> {
    void updatePwd(ProUserEntity proUser);
    boolean existAccount(@Param("userSid") Integer userSid, @Param("userAccount") String userAccount );

    ProUserEntity selectUserByAccountAndPwd(@Param("userAccount") String userAccount,@Param("userPsw") String userPsw);

    ProUserEntity selectUserByWxNo(@Param("openid") String openid);
}