package com.build4d.platform.export.excel.constants;


import com.build4d.base.service.exception.Build4DGenerallyException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public enum CellBorderEnum {
    NONE("None"),
    THIN("Thin"),
    MEDIUM("Medium"),
    DASHED("Dashed"),
    DOTTED("Dotted"),
    THICK("Thick");

    private String _nText;
    CellBorderEnum(String _nText) {
        this._nText = _nText;
    }

    public String getText(){
        return this._nText;
    }

    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"CellBorderEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }

    public static CellBorderEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(CellBorderEnum.NONE._nText)){
            return CellBorderEnum.NONE;
        }
        else if(text.equals(CellBorderEnum.THIN._nText)){
            return CellBorderEnum.THIN;
        }
        else if(text.equals(CellBorderEnum.MEDIUM._nText)){
            return CellBorderEnum.MEDIUM;
        }
        else if(text.equals(CellBorderEnum.DASHED._nText)){
            return CellBorderEnum.DASHED;
        }
        else if(text.equals(CellBorderEnum.DOTTED._nText)){
            return CellBorderEnum.DOTTED;
        }
        else if(text.equals(CellBorderEnum.THICK._nText)){
            return CellBorderEnum.THICK;
        }
        throw NotSupportException();
    }
}
