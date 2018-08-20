package com.build4d.platform.export.general;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.vo.CategoryParaVo;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public interface IExportDataSource {
    MainDataSourceVo getDataSource(Document document, Node exportNode, Node dataSourceNode, ExportSetTypeEnum exportSetType, String[] recordIdArray, CategoryParaVo categoryParaVo) throws Build4DGenerallyException;
}
