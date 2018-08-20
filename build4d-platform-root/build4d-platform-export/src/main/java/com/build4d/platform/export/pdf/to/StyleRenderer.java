package com.build4d.platform.export.pdf.to;

import com.build4d.platform.export.pdf.vo.ExportPDFVo;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AbstractElement;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.w3c.dom.Node;

import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/7.
 */
class StyleRenderer {
    public void SetBlockElementStyle(ExportPDFVo vo, Document document, Node node, BlockElement blockElement, String styleRef){
        //document.
        if(vo.getStyles()==null)return;
        Map<String,String> styleMap=vo.getStyles().get(styleRef);
        if(styleMap==null)return;

        SetAbstractElementStyle(vo,document,node,blockElement,styleRef);
        for (Map.Entry<String, String> propKV : styleMap.entrySet()) {
            String value=propKV.getValue();
            String key=propKV.getKey();
            if (key.equals("MarginTop")) {
                float marginValue = Float.parseFloat(value);
                blockElement.setMarginTop(marginValue);
            } else if (key.equals("MarginBottom")) {
                float marginValue = Float.parseFloat(value);
                blockElement.setMarginBottom(marginValue);
            } else if (key.equals("MarginLeft")) {
                float marginValue = Float.parseFloat(value);
                blockElement.setMarginLeft(marginValue);
            } else if (key.equals("MarginRight")) {
                float marginValue = Float.parseFloat(value);
                blockElement.setMarginRight(marginValue);
            }else if (key.equals("VerticalAlignment")) {
                if (value.equals("BOTTOM")) {
                    blockElement.setVerticalAlignment(VerticalAlignment.BOTTOM);
                } else if (value.equals("MIDDLE")) {
                    blockElement.setVerticalAlignment(VerticalAlignment.MIDDLE);
                } else if (value.equals("TOP")) {
                    blockElement.setVerticalAlignment(VerticalAlignment.TOP);
                }
            }
        }
    }

    public void SetAbstractElementStyle(ExportPDFVo vo, Document document, Node node, AbstractElement abstractElement, String styleRef){
        if(vo.getStyles()==null)return;
        Map<String,String> styleMap=vo.getStyles().get(styleRef);
        if(styleMap==null)return;
        for (Map.Entry<String, String> propKV : styleMap.entrySet()) {
            String value=propKV.getValue();
            String key=propKV.getKey();
            if (key.equals("FontSize")) {
                float fontsize = Float.parseFloat(value);
                abstractElement.setFontSize(fontsize);
            } else if (key.equals("HorizontalAlignment")) {
                if (value.equals("LEFT")) {
                    abstractElement.setHorizontalAlignment(HorizontalAlignment.LEFT);
                } else if (value.equals("CENTER")) {
                    abstractElement.setHorizontalAlignment(HorizontalAlignment.CENTER);
                } else if (value.equals("RIGHT")) {
                    abstractElement.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                }
            } else if (key.equals("TextAlignment")) {
                if (value.equals("LEFT")) {
                    abstractElement.setTextAlignment(TextAlignment.CENTER);
                } else if (value.equals("CENTER")) {
                    abstractElement.setTextAlignment(TextAlignment.CENTER);
                } else if (value.equals("RIGHT")) {
                    abstractElement.setTextAlignment(TextAlignment.RIGHT);
                }else if (value.equals("JUSTIFIED")) {
                    abstractElement.setTextAlignment(TextAlignment.JUSTIFIED);
                } else if (value.equals("JUSTIFIED_ALL")) {
                    abstractElement.setTextAlignment(TextAlignment.JUSTIFIED_ALL);
                }
            } else if(key.equals("FontColor")){
                Color color=GetColor(propKV);
                abstractElement.setFontColor(color);
            } else if (key.equals("WidthPercent")) {
                float fValue = Float.parseFloat(value);
                //abstractElement.setWidthPercent(fValue);
            } else if (key.equals("BackgroundColor")) {
                Color color=GetColor(propKV);
                //abstractElement.setBackgroundColor(color);
            } else if(key.equals("Height")){
                float fValue = Float.parseFloat(value);
                //abstractElement.setHeight(fValue);
            }
        }
    }

    public Color GetColor(Map.Entry<String, String> propKV){
        String value=propKV.getValue();
        /*if (value.equals("BLACK")) {
            return Color.BLACK;
        }else if (value.equals("BLUE")) {
            return Color.BLUE;
        }else if (value.equals("CYAN")) {
            return Color.CYAN;
        }else if (value.equals("DARK_GRAY")) {
            return Color.DARK_GRAY;
        }else if (value.equals("GRAY")) {
            return Color.GRAY;
        }else if (value.equals("GREEN")) {
            return Color.GREEN;
        }else if (value.equals("LIGHT_GRAY")) {
            return Color.LIGHT_GRAY;
        }else if (value.equals("MAGENTA")) {
            return Color.MAGENTA;
        }else if (value.equals("ORANGE")) {
            return Color.ORANGE;
        }else if (value.equals("PINK")) {
            return Color.PINK;
        }else if (value.equals("RED")) {
            return Color.RED;
        }else if (value.equals("WHITE")) {
            return Color.WHITE;
        }else if (value.equals("YELLOW")) {
            return Color.YELLOW;
        }*/
        return null;
    }
}
