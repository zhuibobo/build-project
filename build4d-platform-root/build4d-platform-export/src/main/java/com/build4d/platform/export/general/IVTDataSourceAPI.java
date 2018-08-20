package com.build4d.platform.export.general;

import org.w3c.dom.Node;

import java.util.Map;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public interface IVTDataSourceAPI {
    Map<String,String> getDataSource(Node apiDataSourceNode);
}
