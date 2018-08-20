package com.build4d.platform.export.excel.constants;

import com.build4d.base.service.exception.Build4DGenerallyException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public enum DataSourceTypeEnum {
    MAIN("Main"),
    SUB("Sub");

    private String _nText;
    DataSourceTypeEnum(String _nText) {
        this._nText = _nText;
    }

    public String getText(){
        return this._nText;
    }

    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"DataSourceTypeEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }

    public static DataSourceTypeEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(DataSourceTypeEnum.MAIN._nText)){
            return DataSourceTypeEnum.MAIN;
        }
        else if(text.equals(DataSourceTypeEnum.SUB._nText)){
            return DataSourceTypeEnum.SUB;
        }
        throw NotSupportException();
    }
}
