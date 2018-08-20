package com.build4d.project.constructionproject.service.impl;

import com.build4d.base.dbaccess.dao.GeneralMapper;
import com.build4d.base.service.IAddBefore;
import com.build4d.base.service.impl.BaseService;
import com.build4d.base.tools.common.MD5Utility;
import com.build4d.project.constructionproject.service.IProOrganService;
import com.build4d.project.constructionproject.service.IProUserService;
import com.build4d.project.dbaccess.dao.ProUserMapper;
import com.build4d.project.dbaccess.dbentities.ProOrganEntity;
import com.build4d.project.dbaccess.dbentities.ProUserEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ProUserService extends BaseService<ProUserEntity> implements IProUserService {
    ProUserMapper proUserMapper;

    @Autowired
    IProOrganService organService;

    public ProUserService(ProUserMapper _defaultBaseMapper, SqlSessionTemplate _sqlSessionTemplate, GeneralMapper _generalMapper) {
        super(_defaultBaseMapper, _sqlSessionTemplate, _generalMapper);
        proUserMapper=_defaultBaseMapper;
    }


    public String getInitialPassword(){
        return "1";
    }

    @Override
    public void resetPassword(int userSid)
    {
        ProUserEntity proUser=proUserMapper.selectByPrimaryKey(userSid);
        proUser.setUserPsw(MD5Utility.GetMD5Code(getInitialPassword(),true));
        proUserMapper.updatePwd(proUser);
    }

    @Override
    public boolean existAccount(Integer userSid, String userAccount)
    {
       return proUserMapper.existAccount(userSid,userAccount);
    }

    @Override
    public int saveBySelective(int id, ProUserEntity record) {
        return super.saveBySelective(id, record, new IAddBefore<ProUserEntity>() {
            @Override
            public ProUserEntity run(ProUserEntity item) {
                if(item.getUserPsw()==null || item.getUserPsw().equals("")) {
                    item.setUserPsw(MD5Utility.GetMD5Code(getInitialPassword(), true));
                }
                item.setUserOrder(proUserMapper.nextOrderNum());
                return item;
            }
        });
    }

    @Override
    public ProUserEntity getUserByAccountAndPwd(String userAccount,String userPsw){
        ProUserEntity userEntity=proUserMapper.selectUserByAccountAndPwd(userAccount,userPsw);
        if(userEntity==null)
        {
            return null;
        }
        ProOrganEntity organEntity=organService.getByPrimaryKey(Integer.parseInt(userEntity.getUserOrgId()));
        userEntity.setProOrgan(organEntity);
        return userEntity;
    }

    @Override
    public ProUserEntity getUserByWXNo(String openid) {
        return proUserMapper.selectUserByWxNo(openid);
    }
}
