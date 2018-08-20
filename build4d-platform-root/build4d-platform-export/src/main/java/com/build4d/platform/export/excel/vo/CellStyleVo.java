package com.build4d.platform.export.excel.vo;

import com.build4d.platform.export.excel.constants.CellBorderEnum;
import com.build4d.platform.export.excel.constants.CellHAlignEnum;
import com.build4d.platform.export.excel.constants.CellVAlignEnum;
import com.build4d.platform.export.excel.constants.ColorsEnum;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public class CellStyleVo {
    private int width=0;
    private int height=0;

    private CellBorderEnum border;

    private CellHAlignEnum hAlign;
    private CellVAlignEnum vAlign;

    private int fontSize=0;

    private ColorsEnum fontColor;

    private ColorsEnum foregroundColor;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CellBorderEnum getBorder() {
        return border;
    }

    public void setBorder(CellBorderEnum border) {
        this.border = border;
    }

    public CellHAlignEnum gethAlign() {
        return hAlign;
    }

    public void sethAlign(CellHAlignEnum hAlign) {
        this.hAlign = hAlign;
    }

    public CellVAlignEnum getvAlign() {
        return vAlign;
    }

    public void setvAlign(CellVAlignEnum vAlign) {
        this.vAlign = vAlign;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public ColorsEnum getFontColor() {
        return fontColor;
    }

    public void setFontColor(ColorsEnum fontColor) {
        this.fontColor = fontColor;
    }

    public ColorsEnum getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(ColorsEnum foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
}
