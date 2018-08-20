package com.build4d.platform.export.excel.builder;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.base.tools.common.list.IListWhereCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.platform.export.excel.constants.*;
import com.build4d.platform.export.excel.utility.PinYinUtility;
import com.build4d.platform.export.excel.vo.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;
import java.util.*;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/4
 * @Description:
 * @Version 1.0.0
 */
public class TemplateBuilder {
    PinYinUtility pinYinUtil=new PinYinUtility();
    public TemplateVo getTemplateVo(ExportExcelVo exportExcelVo, Document document, Node exportNode, Node templateNode) throws XPathExpressionException, Build4DGenerallyException {
        TemplateVo templateVo=new TemplateVo();

        boolean autoSizeColumn= XMLUtility.getAttribute(templateNode,"autoSizeColumn").toLowerCase().equals("true")?true:false;

        templateVo.setAutoSizeColumn(autoSizeColumn);

        this.captionBuilder(templateVo,document,exportNode,templateNode);
        this.userCustomizeGroupOrderBuilder(templateVo,document,exportNode,templateNode);
        this.rowBuilder(templateVo,document,exportNode,templateNode);
        //this.validateVo(exportExcelVo,templateVo);
        return templateVo;
    }

    private void userCustomizeGroupOrderBuilder(TemplateVo templateVo,Document document,Node exportNode, Node templateNode) throws XPathExpressionException {
        List<Node> userCustomizeGroupOrderNode=XMLUtility.parseForNodeList(templateNode,"UserCustomizeGroupOrder/Group");
        List<String> userCustomizeGroupKeyOrderList=new ArrayList<String>();
        if(userCustomizeGroupOrderNode!=null&&userCustomizeGroupOrderNode.size()>0){
            for (Node node : userCustomizeGroupOrderNode) {
                String name=XMLUtility.getAttribute(node,"Name");
                if(name!=null){
                    userCustomizeGroupKeyOrderList.add(pinYinUtil.getStringPinYin(name));
                }
            }
        }
        templateVo.setUserCustomizeGroupKeyOrderList(userCustomizeGroupKeyOrderList);
    }

    public void rebuildWithUserCustomize(ExportExcelVo exportExcelVo, String[] userCustomizeArray) throws Build4DGenerallyException {
        this.validateVo(exportExcelVo,exportExcelVo.getTemplateVo());
        //如果UserCustomize为true，则不能使用Cell中的MergerCell属性，并且必须为每个可选到导出项配置UserCustomizeName属性。
        if(exportExcelVo.isUserCustomize()){
            TemplateVo templateVo=exportExcelVo.getTemplateVo();
            //重新构建头部模板
            List<String> userCustomizeList= Arrays.asList(userCustomizeArray);
            List<RowVo> rowVoList = templateVo.getRowVoList();
            for (RowVo rowVo : rowVoList) {
                List<CellVo> cellVoList=rowVo.getCellVoList();
                for (int i=cellVoList.size()-1;i>=0;i--) {
                    CellVo cellVo=cellVoList.get(i);
                    //移除掉指定的列
                    if(cellVo.isUserCustomize()){
                        final String userCustomizeName=cellVo.getUserCustomizeName();
                        boolean isExistInUserCustomize= ListUtility.Exist(userCustomizeList, new IListWhereCondition<String>() {
                            public boolean Condition(String item) {
                                return item.equals(userCustomizeName);
                            }
                        });
                        if(!isExistInUserCustomize){
                            cellVoList.remove(cellVo);
                            //重新计算包含改列的合并单元格。
                            for (RowVo rowVospan : rowVoList) {
                                List<CellVo> cellVoListspan=rowVospan.getCellVoList();
                                for (int i1 = cellVoListspan.size()-1; i1 >=0; i1--) {
                                    CellVo vo = cellVoListspan.get(i1);
                                    boolean isIncludeCell = ListUtility.Exist(vo.getUserCustomincludeCellName(), new IListWhereCondition<String>() {
                                        public boolean Condition(String item) {
                                            return item.equals(userCustomizeName);
                                        }
                                    });
                                    if (isIncludeCell) {
                                        vo.getUserCustomincludeCellName().remove(userCustomizeName);
                                        vo.setAutoMergerCell(vo.getUserCustomincludeCellName().size());
                                        if (vo.getUserCustomincludeCellName().size() == 0) {
                                            cellVoListspan.remove(vo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //重新构建数据绑定列模板
            List<BindDataCellVo> bindDataCellVoList=this.getBindDataCellBuilder(exportExcelVo);
            exportExcelVo.getTemplateVo().setBindDataCellVoList(bindDataCellVoList);
        }
    }

    public List<BindDataCellVo> getBindDataCellBuilder(ExportExcelVo exportExcelVo){
        List<BindDataCellVo> bindDataCellVoList=new ArrayList<BindDataCellVo>();
        List<RowVo> rowVoList = exportExcelVo.getTemplateVo().getRowVoList();

        int tempxlength=100;
        CellVo[][] tempvo=new CellVo[rowVoList.size()][tempxlength];

        int tempi=0;
        int tempj=0;
        for(int i=0;i<rowVoList.size();i++){
            RowVo rowVo=rowVoList.get(i);
            List<CellVo> cellVoList = rowVo.getCellVoList();
            for (int j = 0; j < cellVoList.size(); j++) {
                CellVo cellVo = cellVoList.get(j);
                int mergerRow=cellVo.getMergerRow()-1;
                int mergerCell=cellVo.getMergerCell()-1;

                if(cellVo.getBindField().equals("")){
                    cellVo=new CellVo();
                    cellVo.setForMerged(true);
                }

                tempi=i;
                tempj=j;
                //判断i，j位置是否已经被填充，如果被填充了元素，则在当前行往后排
                if(tempvo[tempi][tempj]==null) {
                    tempvo[tempi][tempj]=cellVo;
                }
                else
                {
                    while (++tempj>0){
                        if(tempvo[tempi][tempj]==null) {
                            tempvo[tempi][tempj]=cellVo;
                            break;
                        }
                    }
                }
                //填占领合并行列
                int firstMerRow=i;
                int lastMerRow=i+mergerRow;
                int firstColumn=tempj;
                int lastMerColumn=tempj+mergerCell;
                for(int mr=firstMerRow;mr<=lastMerRow;mr++){
                    for(int mc=firstColumn;mc<=lastMerColumn;mc++){
                        if(tempvo.length>mr){
                            if(tempvo[mr][mc]==null) {
                                CellVo nullCellVo = new CellVo();
                                nullCellVo.setForMerged(true);
                                tempvo[mr][mc] = nullCellVo;
                            }
                        }
                    }
                }
            }
        }

        //将二维数组的数据转存入bindDataCellVoList
        BindDataCellVo bindDataCellVo=null;

        for(int columnIndex=0;columnIndex<tempxlength;columnIndex++){
            CellVo cellVo=null;
            for(int rowIndex=0;rowIndex<tempvo.length;rowIndex++){
                if(tempvo[rowIndex][columnIndex]!=null){
                    if(tempvo[rowIndex][columnIndex].isForMerged()==false){
                        cellVo=tempvo[rowIndex][columnIndex];
                    }
                }
            }
            if(cellVo!=null){
                bindDataCellVo=new BindDataCellVo();
                bindDataCellVo.setBindDataSource(cellVo.getBindDataSource());
                bindDataCellVo.setDataSourceType(cellVo.getDataSourceType());
                bindDataCellVo.setBindField(cellVo.getBindField());
                bindDataCellVo.setUserCustomincludeCellName(cellVo.getUserCustomincludeCellName());
                bindDataCellVo.setDefaultValue(cellVo.getDefaultValue());
                bindDataCellVo.setFormaterRef(cellVo.getFormaterRef());
                bindDataCellVo.setVtConverter(cellVo.getVtConverter());
                bindDataCellVo.setData_StyleVo(cellVo.getData_StyleVo());
                bindDataCellVo.setTitle_StyleVo(cellVo.getTitle_StyleVo());
                bindDataCellVo.setDefaultConverter(cellVo.getDefaultConverter());
                //bindDataCellVo.setType(cellVo.getType());
                bindDataCellVoList.add(bindDataCellVo);
            }
        }

        /*for (RowVo rowVo : rowVoList) {
            List<CellVo> cellVoList = rowVo.getCellVoList();
            for (CellVo cellVo : cellVoList) {
                if(!cellVo.getBindField().equals("")){
                    BindDataCellVo bindDataCellVo=new BindDataCellVo();
                    bindDataCellVo.setBindDataSource(cellVo.getBindDataSource());
                    bindDataCellVo.setBindField(cellVo.getBindField());
                    bindDataCellVo.setIncludeCellName(cellVo.getIncludeCellName());
                    bindDataCellVo.setDefaultValue(cellVo.getDefaultValue());
                    bindDataCellVo.setFormaterRef(cellVo.getFormaterRef());
                    bindDataCellVo.setType(cellVo.getType());
                    bindDataCellVoList.add(bindDataCellVo);
                }
            }
        }*/
        //bindDataCellVoList;
        //bindDataCellVo=new BindDataCellVo();
        //List<BindDataCellVo> temp= new ArrayList<BindDataCellVo>();
        //temp.add(bindDataCellVo);
        return bindDataCellVoList;
    }

    public boolean validateVo(ExportExcelVo exportExcelVo,TemplateVo templateVo) throws Build4DGenerallyException {
        if(exportExcelVo.isUserCustomize()){
            List<RowVo> rowVoList = exportExcelVo.getTemplateVo().getRowVoList();
            Map<String,String> tempUCMap=new HashMap<String, String>();
            for (RowVo rowVo : rowVoList) {
                List<CellVo> cellVoList = rowVo.getCellVoList();
                for (CellVo cellVo : cellVoList) {
                    if(cellVo.isUserCustomize()){
                        if(cellVo.getUserCustomizeName().equals("")){
                            throw new Build4DGenerallyException(0,"绑定【"+cellVo.getCaption()+"】的Cell设置为UserCustomize=True，但未设置UserCustomizeName！");
                        }
                        else {
                            if(!tempUCMap.containsKey(cellVo.getUserCustomizeName())) {
                                tempUCMap.put(cellVo.getUserCustomizeName(),"exist");
                            }
                            else {
                                throw new Build4DGenerallyException(0,"绑定【"+cellVo.getCaption()+"】的Cell包含的属性【UserCustomizeName="+cellVo.getUserCustomizeName()+"】重复！");
                            }
                        }
                    }
                }
            }

            //校验是否有重复的UserCustomizeName

        }
        return true;
    }

    private void captionBuilder(TemplateVo templateVo,Document document,Node exportNode, Node templateNode) throws XPathExpressionException, Build4DGenerallyException {
        Node captionNode=XMLUtility.parseForNode(templateNode,"ExportCaption/Caption");
        String caption= captionNode.getTextContent().trim();

        Node styleNode=XMLUtility.parseForNode(templateNode,"ExportCaption/Style");

        CellStyleVo cellStyleVo=resolveStyleNodeToCellStyle(styleNode);

        templateVo.setExportCaption(caption);
        templateVo.setCaptionStyle(cellStyleVo);
    }

    public CellStyleVo resolveStyleNodeToCellStyle(Node styleNode) throws Build4DGenerallyException {
        String Border=getNodeAttr(styleNode,"Border","Thin");
        String Width=getNodeAttr(styleNode,"Width","0");
        String Height=getNodeAttr(styleNode,"Height","0");
        String ForegroundColor=getNodeAttr(styleNode,"ForegroundColor","白色");
        String FontSize=getNodeAttr(styleNode,"FontSize","0");
        String FontColor=getNodeAttr(styleNode,"FontColor","黑色");
        String HorizontalAlignment=getNodeAttr(styleNode,"HorizontalAlignment","Center");
        String VerticalAlignment=getNodeAttr(styleNode,"VerticalAlignment","Center");

        CellStyleVo cellStyleVo=new CellStyleVo();
        cellStyleVo.setBorder(CellBorderEnum.parseText(Border));
        cellStyleVo.setWidth(Integer.parseInt(Width));
        if(cellStyleVo.getWidth()>0){
            cellStyleVo.setWidth(cellStyleVo.getWidth()*256);
        }
        cellStyleVo.setHeight(Integer.parseInt(Height));
        cellStyleVo.setFontSize(Integer.parseInt(FontSize));
        cellStyleVo.setFontColor(ColorsEnum.parseText(FontColor));
        cellStyleVo.sethAlign(CellHAlignEnum.parseText(HorizontalAlignment));
        cellStyleVo.setvAlign(CellVAlignEnum.parseText(VerticalAlignment));
        cellStyleVo.setForegroundColor(ColorsEnum.parseText(ForegroundColor));
        return cellStyleVo;
    }

    private String getNodeAttr(Node node,String attr,String defaultValue){
        String result=XMLUtility.getAttribute(node,attr);
        if(result.equals("")){
            result=defaultValue;
        }
        return result;
    }

    private void rowBuilder(TemplateVo templateVo,Document document,Node exportNode, Node templateNode) throws XPathExpressionException, Build4DGenerallyException {
        List<Node> rowNodeList=XMLUtility.parseForNodeList(templateNode,"Row");
        List<RowVo> rowVoList=new ArrayList<RowVo>();
        templateVo.setRowVoList(rowVoList);
        for (Node rowNode : rowNodeList) {
            RowVo rowVo=new RowVo();
            rowVoList.add(rowVo);
            String tempRowHeigth = XMLUtility.getAttribute(rowNode, "RowHeigth");
            if(tempRowHeigth.equals("")){
                tempRowHeigth="0";
            }
            int rowHeigth= Integer.parseInt(tempRowHeigth);
            rowVo.setRowHeigth(rowHeigth);
            this.cellBuilder(templateVo,document,exportNode,templateNode,rowVo,rowNode);
        }
    }

    private void cellBuilder(TemplateVo templateVo,Document document,Node exportNode, Node templateNode,RowVo hostRow,Node rowNode) throws XPathExpressionException, Build4DGenerallyException {
        List<CellVo> cellVoList=new ArrayList<CellVo>();
        List<Node> cellNodeList=XMLUtility.parseForNodeList(rowNode,"Cell");
        for (Node cellNode : cellNodeList) {
            CellVo cellVo = new CellVo();

            String tempDataSourceType = "";

            String tempMergerCell = XMLUtility.getAttribute(cellNode, "MergerCell");
            String tempMergerRow = XMLUtility.getAttribute(cellNode, "MergerRow");


            String tempUserCustomizeEnable = "";
            String tempuserCustomizeName = "";
            String tempIncludeCellCustomizeName = "";
            String tempUserCustomizeGroup="";
            String tempUserCustomizeCaption="";
            //获取存放在Cell节点属性上的设置
            tempUserCustomizeEnable = XMLUtility.getAttribute(cellNode, "UserCustomizeEnable");
            tempuserCustomizeName=XMLUtility.getAttribute(cellNode, "UserCustomizeName");
            tempIncludeCellCustomizeName=XMLUtility.getAttribute(cellNode, "UserCustomizeIncludeName");
            tempUserCustomizeGroup=XMLUtility.getAttribute(cellNode, "UserCustomizeGroup");
            tempUserCustomizeCaption=XMLUtility.getAttribute(cellNode, "UserCustomizeCaption");
            //获取独立配置节点上的属性
            Node userCustomizeNode = XMLUtility.parseForNode(cellNode, "UserCustomize");
            if (userCustomizeNode != null) {
                tempUserCustomizeEnable = XMLUtility.getAttribute(userCustomizeNode, "Enable");
                tempuserCustomizeName = XMLUtility.getAttribute(userCustomizeNode, "Name");
                tempIncludeCellCustomizeName = XMLUtility.getAttribute(userCustomizeNode, "IncludeName");
                tempUserCustomizeGroup=XMLUtility.getAttribute(userCustomizeNode, "Group");
                tempUserCustomizeCaption=XMLUtility.getAttribute(userCustomizeNode, "Caption");
            }

            //获取存放在Cell节点属性上的设置
            String tempBindDataSource = "";
            String tempBindField = "";
            String tempDefaultValue = "";
            String tempFormaterRef = "";
            String tempVTConverter="";
            String tempDefaultConverter="";
            tempBindDataSource = XMLUtility.getAttribute(cellNode, "BindDataSource");
            tempBindField = XMLUtility.getAttribute(cellNode, "BindField");
            tempDefaultValue = XMLUtility.getAttribute(cellNode, "DefaultValue");
            tempFormaterRef = XMLUtility.getAttribute(cellNode, "FormaterRef");
            tempDefaultConverter= XMLUtility.getAttribute(cellNode, "DefaultConverter");
            tempVTConverter= XMLUtility.getAttribute(cellNode, "VTConverterRef");
            //获取独立配置节点上的属性
            Node bindDataNode = XMLUtility.parseForNode(cellNode, "Bind");
            if (bindDataNode != null) {
                tempBindDataSource = XMLUtility.getAttribute(bindDataNode, "DataSource");
                tempBindField = XMLUtility.getAttribute(bindDataNode, "Field");
                tempDefaultValue = XMLUtility.getAttribute(bindDataNode, "DefaultValue");
                tempFormaterRef = XMLUtility.getAttribute(bindDataNode, "FormaterRef");
                tempVTConverter=XMLUtility.getAttribute(bindDataNode, "VTConverterRef");
                tempDefaultConverter=XMLUtility.getAttribute(bindDataNode, "DefaultConverter");
            }

            String caption = "";
            caption= XMLUtility.getAttribute(cellNode, "Caption");
            Node captionNode = XMLUtility.parseForNode(cellNode, "Caption");
            if (captionNode != null) {
                caption = captionNode.getTextContent().trim();
            }

            //DefaultValue;
            if (tempMergerCell.equals("")) {
                tempMergerCell = "1";
            }
            if (tempMergerRow.equals("")) {
                tempMergerRow = "1";
            }
            if (tempUserCustomizeEnable.equals("")) {
                tempUserCustomizeEnable = "False";
            }
            if(tempUserCustomizeGroup.equals("")){
                tempUserCustomizeGroup="默认分组";
            }
            if (tempBindDataSource.equals("")) {
                tempBindDataSource = "Main";
            }

            if (!tempBindDataSource.equals("Main")) {
                tempDataSourceType = "Sub";
            } else {
                tempDataSourceType = "Main";
            }

            cellVo.setForMerged(false);
            cellVo.setMergerRow(Integer.parseInt(tempMergerRow));
            cellVo.setMergerCell(Integer.parseInt(tempMergerCell));
            cellVo.setAutoMergerCell(cellVo.getMergerCell());

            cellVo.setUserCustomize(tempUserCustomizeEnable.toLowerCase().equals("true") ? true : false);
            cellVo.setUserCustomizeName(tempuserCustomizeName);
            if(tempUserCustomizeCaption.equals("")) {
                cellVo.setUserCustomizeCaption(cellVo.getUserCustomizeName());
            }
            else {
                cellVo.setUserCustomizeCaption(tempUserCustomizeCaption);
            }
            cellVo.setUserCustomGroup(tempUserCustomizeGroup);
            cellVo.setUserCustomGroupKey(pinYinUtil.getStringPinYin(cellVo.getUserCustomGroup()));
            String[] tempIncludeArray = tempIncludeCellCustomizeName.split(";");
            List<String> stringList = new ArrayList<String>();
            for (String s : tempIncludeArray) {
                stringList.add(s);
            }
            cellVo.setUserCustomincludeCellName(stringList);

            cellVo.setDataSourceType(DataSourceTypeEnum.parseText(tempDataSourceType));
            cellVo.setBindDataSource(tempBindDataSource);
            cellVo.setBindField(tempBindField);
            cellVo.setDefaultValue(tempDefaultValue);
            cellVo.setFormaterRef(tempFormaterRef);
            cellVo.setVtConverter(tempVTConverter);
            cellVo.setDefaultConverter(tempDefaultConverter);

            Node titleStyleNode=XMLUtility.parseForNode(cellNode,"TitleStyle");
            CellStyleVo titleStyleStyleVo=resolveStyleNodeToCellStyle(titleStyleNode);
            cellVo.setTitle_StyleVo(titleStyleStyleVo);

            Node dataStyleNode=XMLUtility.parseForNode(cellNode,"DataStyle");
            CellStyleVo dataStyleStyleVo=resolveStyleNodeToCellStyle(dataStyleNode);
            cellVo.setData_StyleVo(dataStyleStyleVo);

            cellVo.setCaption(caption);

            cellVoList.add(cellVo);
        }
        hostRow.setCellVoList(cellVoList);
    }
}
