package com.build4d.platform.export.excel.formater;

import com.build4d.platform.export.general.ISubDataSource;
import com.build4d.platform.export.general.vo.ExportVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/12
 * @Description:
 * @Version 1.0.0
 */
public class GetHouseDataDemo implements ISubDataSource {
    public List<Map> getDataSource(ExportVo exportVo, Document document, Node exportNode, Node dataSourceNode) {
        return null;
    }
}
