package com.build4d.platform.export.excel.constants;

import com.build4d.base.service.exception.Build4DGenerallyException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public enum CellVAlignEnum {
    CENTER("Center"),
    TOP("Top"),
    BOTTOM("Bottom");

    private String _nText;
    CellVAlignEnum(String _nText) {
        this._nText = _nText;
    }

    public String getText(){
        return this._nText;
    }

    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"CellVAlignEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }

    public static CellVAlignEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(CellVAlignEnum.CENTER._nText)){
            return CellVAlignEnum.CENTER;
        }
        else if(text.equals(CellVAlignEnum.TOP._nText)){
            return CellVAlignEnum.TOP;
        }
        else if(text.equals(CellVAlignEnum.BOTTOM._nText)){
            return CellVAlignEnum.BOTTOM;
        }
        throw NotSupportException();
    }
}
