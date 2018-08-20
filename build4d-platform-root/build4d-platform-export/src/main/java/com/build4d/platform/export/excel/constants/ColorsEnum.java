package com.build4d.platform.export.excel.constants;

import com.build4d.base.service.exception.Build4DGenerallyException;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/9
 * @Description:
 * @Version 1.0.0
 */
public enum ColorsEnum {
    BLACK(8,"黑色"),
    WHITE(9,"白色"),
    RED(10,"红色"),
    BRIGHT_GREEN(11,"亮绿色"),
    BLUE(12,"蓝色"),
    YELLOW(13,"黄色"),
    PINK(14,"粉色"),
    TURQUOISE(15,"绿松石"),
    DARK_RED(16,"深红"),
    GREEN(17,"绿色"),
    DARK_BLUE(18,"深蓝"),
    DARK_YELLOW(19,"深黄色"),
    VIOLET(20,"紫色"),
    TEAL(21,"水鸭蓝"),
    GREY_25_PERCENT(22,"灰色20P"),
    GREY_50_PERCENT(23,"灰色50P"),
    CORNFLOWER_BLUE(24,"羽毛蓝色"),
    MAROON(25,"栗色"),
    LEMON_CHIFFON(26,"柠檬色"),
    ORCHID(28,"兰花色"),
    CORAL(29,"珊瑚色"),
    ROYAL_BLUE(30,"宝蓝色"),
    LIGHT_CORNFLOWER_BLUE(31,"灯管蓝色"),
    SKY_BLUE(40,"天蓝色"),
    LIGHT_TURQUOISE(41,"绿松石"),
    LIGHT_GREEN(42,"品绿色"),
    LIGHT_YELLOW(43,"葱黄色"),
    PALE_BLUE(44,"淡蓝色"),
    ROSE(45,"玫瑰色"),
    LAVENDER(46,"薰衣草色"),
    TAN(47,"黄褐色"),
    LIGHT_BLUE(48,"浅蓝色"),
    AQUA(49,"水绿色"),
    LIME(50,"青柠色"),
    GOLD(51,"金色"),
    LIGHT_ORANGE(52,"浅橙色"),
    ORANGE(53,"橙色"),
    BLUE_GREY(54,"蓝灰色"),
    GREY_40_PERCENT(55,"灰色40P"),
    SEA_GREEN(57,"海绿色"),
    DARK_GREEN(58,"深绿色"),
    OLIVE_GREEN(59,"橄榄绿色"),
    BROWN(60,"棕色"),
    PLUM(61,"紫红色"),
    INDIGO(62,"靛色"),
    GREY_80_PERCENT(63,"灰色80P");

    public final short index;
    private String _nText;

    private ColorsEnum(int idx,String text) {
        this.index = (short)idx;
        this._nText=text;
    }
    public static Build4DGenerallyException NotSupportException() throws Build4DGenerallyException {
        return new Build4DGenerallyException(0,"CellHAlignEnum.ThrowNotSupportException 不支持当前操作符！"){};
    }
    public static ColorsEnum parseText(String text) throws Build4DGenerallyException {
        if(text.equals(ColorsEnum.BLACK._nText)){
            return ColorsEnum.BLACK;
        }
        else if(text.equals(ColorsEnum.WHITE._nText)){
            return ColorsEnum.WHITE;
        }
        else if(text.equals(ColorsEnum.RED._nText)){
            return ColorsEnum.RED;
        }
        else if(text.equals(ColorsEnum.BRIGHT_GREEN._nText)){
            return ColorsEnum.BRIGHT_GREEN;
        }
        else if(text.equals(ColorsEnum.BLUE._nText)){
            return ColorsEnum.BLUE;
        }
        else if(text.equals(ColorsEnum.YELLOW._nText)){
            return ColorsEnum.YELLOW;
        }
        else if(text.equals(ColorsEnum.PINK._nText)){
            return ColorsEnum.PINK;
        }
        else if(text.equals(ColorsEnum.TURQUOISE._nText)){
            return ColorsEnum.TURQUOISE;
        }
        else if(text.equals(ColorsEnum.DARK_RED._nText)){
            return ColorsEnum.DARK_RED;
        }
        else if(text.equals(ColorsEnum.GREEN._nText)){
            return ColorsEnum.GREEN;
        }
        else if(text.equals(ColorsEnum.DARK_BLUE._nText)){
            return ColorsEnum.DARK_BLUE;
        }
        else if(text.equals(ColorsEnum.DARK_YELLOW._nText)){
            return ColorsEnum.DARK_YELLOW;
        }
        else if(text.equals(ColorsEnum.VIOLET._nText)){
            return ColorsEnum.VIOLET;
        }
        else if(text.equals(ColorsEnum.TEAL._nText)){
            return ColorsEnum.TEAL;
        }
        else if(text.equals(ColorsEnum.GREY_25_PERCENT._nText)){
            return ColorsEnum.GREY_25_PERCENT;
        }
        else if(text.equals(ColorsEnum.GREY_50_PERCENT._nText)){
            return ColorsEnum.GREY_50_PERCENT;
        }
        else if(text.equals(ColorsEnum.CORNFLOWER_BLUE._nText)){
            return ColorsEnum.CORNFLOWER_BLUE;
        }
        else if(text.equals(ColorsEnum.MAROON._nText)){
            return ColorsEnum.MAROON;
        }
        else if(text.equals(ColorsEnum.LEMON_CHIFFON._nText)){
            return ColorsEnum.LEMON_CHIFFON;
        }
        else if(text.equals(ColorsEnum.ORCHID._nText)){
            return ColorsEnum.ORCHID;
        }
        else if(text.equals(ColorsEnum.CORAL._nText)){
            return ColorsEnum.CORAL;
        }
        else if(text.equals(ColorsEnum.ROYAL_BLUE._nText)){
            return ColorsEnum.ROYAL_BLUE;
        }
        else if(text.equals(ColorsEnum.LIGHT_CORNFLOWER_BLUE._nText)){
            return ColorsEnum.LIGHT_CORNFLOWER_BLUE;
        }
        else if(text.equals(ColorsEnum.SKY_BLUE._nText)){
            return ColorsEnum.SKY_BLUE;
        }
        else if(text.equals(ColorsEnum.LIGHT_TURQUOISE._nText)){
            return ColorsEnum.LIGHT_TURQUOISE;
        }
        else if(text.equals(ColorsEnum.LIGHT_GREEN._nText)){
            return ColorsEnum.LIGHT_GREEN;
        }
        else if(text.equals(ColorsEnum.LIGHT_YELLOW._nText)){
            return ColorsEnum.LIGHT_YELLOW;
        }
        else if(text.equals(ColorsEnum.PALE_BLUE._nText)){
            return ColorsEnum.PALE_BLUE;
        }
        else if(text.equals(ColorsEnum.ROSE._nText)){
            return ColorsEnum.ROSE;
        }
        else if(text.equals(ColorsEnum.LAVENDER._nText)){
            return ColorsEnum.LAVENDER;
        }
        else if(text.equals(ColorsEnum.TAN._nText)){
            return ColorsEnum.TAN;
        }
        else if(text.equals(ColorsEnum.LIGHT_BLUE._nText)){
            return ColorsEnum.LIGHT_BLUE;
        }
        else if(text.equals(ColorsEnum.AQUA._nText)){
            return ColorsEnum.AQUA;
        }
        else if(text.equals(ColorsEnum.LIME._nText)){
            return ColorsEnum.LIME;
        }
        else if(text.equals(ColorsEnum.GOLD._nText)){
            return ColorsEnum.GOLD;
        }
        else if(text.equals(ColorsEnum.LIGHT_ORANGE._nText)){
            return ColorsEnum.LIGHT_ORANGE;
        } else if(text.equals(ColorsEnum.ORANGE._nText)){
            return ColorsEnum.ORANGE;
        }
        else if(text.equals(ColorsEnum.BLUE_GREY._nText)){
            return ColorsEnum.BLUE_GREY;
        }
        else if(text.equals(ColorsEnum.GREY_40_PERCENT._nText)){
            return ColorsEnum.GREY_40_PERCENT;
        }
        else if(text.equals(ColorsEnum.SEA_GREEN._nText)){
            return ColorsEnum.SEA_GREEN;
        }
        else if(text.equals(ColorsEnum.DARK_GREEN._nText)){
            return ColorsEnum.DARK_GREEN;
        }
        else if(text.equals(ColorsEnum.OLIVE_GREEN._nText)){
            return ColorsEnum.OLIVE_GREEN;
        }
        else if(text.equals(ColorsEnum.BROWN._nText)){
            return ColorsEnum.BROWN;
        }
        else if(text.equals(ColorsEnum.PLUM._nText)){
            return ColorsEnum.PLUM;
        }
        else if(text.equals(ColorsEnum.INDIGO._nText)){
            return ColorsEnum.INDIGO;
        }
        else if(text.equals(ColorsEnum.GREY_80_PERCENT._nText)){
            return ColorsEnum.GREY_80_PERCENT;
        }

        throw NotSupportException();
    }

    public short getIndex() {
        return this.index;
    }
}
