package com.build4d.platform.export.general.datasource;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.ClassUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IExportDataSource;

import com.build4d.platform.export.general.vo.CategoryParaVo;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class APIDataSourceBuilder implements IExportDataSource {

    public MainDataSourceVo getDataSource(Document document, Node exportNode, Node dataSourceNode, ExportSetTypeEnum exportSetType, String[] recordIdArray, CategoryParaVo categoryParaVo) throws Build4DGenerallyException {
        try {
            MainDataSourceVo mainDataSourceVo = new MainDataSourceVo();
            boolean enable = XMLUtility.getAttribute(dataSourceNode, "Enable").equals("True") ? true : false;
            String key = XMLUtility.getAttribute(dataSourceNode, "Key");
            mainDataSourceVo.setEnable(enable);
            mainDataSourceVo.setKey(key);
            mainDataSourceVo.setName("Main");
            if (!enable) {
                return mainDataSourceVo;
            }
            String fullClassName = XMLUtility.parseForString(dataSourceNode, "FullClassName");
            IExportDataSource api= (IExportDataSource) ClassUtility.loadClass(fullClassName).newInstance();
            return api.getDataSource(document,exportNode,dataSourceNode,exportSetType,recordIdArray,categoryParaVo);
        }
        catch (Exception ex){
            throw new Build4DGenerallyException(0,ex.getMessage());
        }
    }
}
