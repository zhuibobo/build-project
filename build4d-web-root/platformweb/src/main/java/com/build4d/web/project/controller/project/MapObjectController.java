package com.build4d.web.project.controller.project;

import com.build4d.project.constructionproject.service.IProMapObjService;
import com.build4d.project.dbaccess.dbentities.ArolEngBaseInfoEntity;
import com.build4d.project.dbaccess.dbentities.ProMapObjEntity;
import com.build4d.web.general.model.Build4DResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: zhuangrb
 * Date: 2018/7/17
 * To change this template use File | Settings | File Templates.
 */


@Controller
@RequestMapping("project/mapobject")
public class MapObjectController {
    @Autowired
    IProMapObjService proMapObjService;

    @RequestMapping(value = "newobjmark", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo newObjMark(String bindto,String bindtoid,String LAT,String LNG,String DISTEXT) {

        proMapObjService.coverObjMark(bindto,bindtoid,LAT,LNG,DISTEXT);
        //Integer id=arolEngBaseInfoService.saveBySelective(entity.getSid(), entity);
        return Build4DResponseVo.saveSuccess();
    }

    @RequestMapping(value = "getobjmark", method = RequestMethod.POST)
    @ResponseBody
    public Build4DResponseVo getObjMark(String bindto,String bindtoid) {

        ProMapObjEntity proMapObjEntity=proMapObjService.getByBindTo(bindto,bindtoid);
        if(proMapObjEntity!=null) {
            return Build4DResponseVo.success("suc", proMapObjEntity);
        }
        else {
            return Build4DResponseVo.success("未绑定标识");
        }
    }
}
