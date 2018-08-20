package com.build4d.platform.export.general.constants;

import com.build4d.base.service.exception.Build4DGenerallyException;

public enum ExportSetTypeEnum {

    LISTSET("ListSet"),
    LISTSETWITHSELECTED("ListSetWithSelected");

    private String _nText;
    ExportSetTypeEnum(String _nText) {
        this._nText = _nText;
    }

    public String getText(){
        return this._nText;
    }

    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"CellVAlignEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }

    public static ExportSetTypeEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(ExportSetTypeEnum.LISTSET._nText)){
            return ExportSetTypeEnum.LISTSET;
        }
        else if(text.equals(ExportSetTypeEnum.LISTSETWITHSELECTED._nText)){
            return ExportSetTypeEnum.LISTSETWITHSELECTED;
        }
        throw NotSupportException();
    }

}
