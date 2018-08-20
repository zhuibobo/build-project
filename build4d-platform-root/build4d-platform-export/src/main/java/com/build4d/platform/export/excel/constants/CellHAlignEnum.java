package com.build4d.platform.export.excel.constants;


import com.build4d.base.service.exception.Build4DGenerallyException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public enum CellHAlignEnum {
    CENTER("Center"),
    LEFT("Left"),
    RIGHT("Right");

    private String _nText;
    CellHAlignEnum(String _nText) {
        this._nText = _nText;
    }

    public String getText(){
        return this._nText;
    }

    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"CellHAlignEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }

    public static CellHAlignEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(CellHAlignEnum.CENTER._nText)){
            return CellHAlignEnum.CENTER;
        }
        else if(text.equals(CellHAlignEnum.LEFT._nText)){
            return CellHAlignEnum.LEFT;
        }
        else if(text.equals(CellHAlignEnum.LEFT._nText)){
            return CellHAlignEnum.LEFT;
        }
        throw NotSupportException();
    }
}
