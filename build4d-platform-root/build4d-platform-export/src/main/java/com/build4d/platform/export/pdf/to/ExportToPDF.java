package com.build4d.platform.export.pdf.to;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.service.general.B4DSession;
import com.build4d.base.service.general.B4DSessionUtility;
import com.build4d.base.tools.common.DateUtility;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.base.tools.common.list.IListWhereCondition;
import com.build4d.base.tools.common.list.ListUtility;
import com.build4d.platform.export.general.vo.SubDataSourceVo;
import com.build4d.platform.export.pdf.IValueFormater;
import com.build4d.platform.export.pdf.vo.ExportPDFVo;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.AreaBreakType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo-sss on 2017/9/4.
 */
public class ExportToPDF {
    StyleRenderer styleRenderer=new StyleRenderer();
    public byte[] ToPDF(ExportPDFVo vo) throws XPathExpressionException, IOException, Build4DGenerallyException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(bos);
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        // Initialize document
        Document document = GetSizeDocument(pdf, vo);

        URL url = this.getClass().getResource("/resources/fonts/simsun.ttc");
        PdfFont sysFont = PdfFontFactory.createFont(url.getPath().replaceFirst("/", "") + ",1", PdfEncodings.IDENTITY_H, false);//中文设置,解决特殊字符错误，
        document.setFont(sysFont);

        Node templdateNode = XMLUtility.parseForNode(vo.getExportNode(), "Template");
        //以主记录为循环,构建文档.
        if(vo.getMainDataSourceVo()!=null&&!vo.getMainDataSourceVo().getDataSource().isEmpty()) {
            List<Map> mainRecordList=vo.getMainDataSourceVo().getDataSource();
            for(int j=0;j<mainRecordList.size();j++){
                int x=j;
                Map mapRecord=mainRecordList.get(j);
                NodeList firstLevelNodeList = templdateNode.getChildNodes();
                for (int i = 0; i < firstLevelNodeList.getLength(); i++) {
                    Node node = firstLevelNodeList.item(i);
                    Paragraph paragraph = BuildParagraph(vo, document, node,mapRecord,i);
                    if (paragraph != null) {
                        document.add(paragraph);
                    }
                    Table table = BuildTable(vo, document, node,mapRecord,i);
                    if (table != null) {
                        document.add(table);
                    }
                    AreaBreak nextPage = NextPage(vo, document, node,mapRecord,i);
                    if (nextPage != null) {
                        if(x<mainRecordList.size()-1) {
                            document.add(nextPage);
                        }
                    }
                }
            }
            document.close();
            return bos.toByteArray();
        }
        return null;
    }

    private Document GetSizeDocument(PdfDocument pdf,ExportPDFVo vo){
        String pageSizeAttr= XMLUtility.getAttribute(vo.getExportNode(),"PageSize");
        if(pageSizeAttr.equals("")){
            return new Document(pdf,PageSize.A4);
        }
        else {
            if (pageSizeAttr.equals("A0")) {
                return new Document(pdf, PageSize.A0);
            } else if (pageSizeAttr.equals("A1")) {
                return new Document(pdf, PageSize.A1);
            } else if (pageSizeAttr.equals("A2")) {
                return new Document(pdf, PageSize.A2);
            } else if (pageSizeAttr.equals("A3")) {
                return new Document(pdf, PageSize.A3);
            } else if (pageSizeAttr.equals("A4")) {
                return new Document(pdf, PageSize.A4);
            } else if (pageSizeAttr.equals("A5")) {
                return new Document(pdf, PageSize.A5);
            }
        }
        return new Document(pdf,PageSize.A4);
    }

    private Paragraph BuildParagraph(ExportPDFVo vo,Document document,Node node,Map mapRecord, int rowIndex) throws IOException, XPathExpressionException {
        if(node.getNodeName().equals("Paragraph")){
            if(XMLUtility.parseForNodeList(node,"Text").isEmpty()){
                String text=GetValue(vo,document,node,mapRecord,rowIndex);
                Paragraph paragraph = new Paragraph(text);
                String styleRef=XMLUtility.getAttribute(node,"StyleRef");
                if(!styleRef.equals("")){
                    styleRenderer.SetBlockElementStyle(vo,document,node,paragraph,styleRef);
                }
                return paragraph;
                //document.add(paragraph);
            }
            else
            {
                List<Node> textNodeList=XMLUtility.parseForNodeList(node,"Text");
                Paragraph paragraph = new Paragraph();
                for (Node textNode : textNodeList) {
                    Text text=BuildParagraphText(vo,document,node,textNode,mapRecord,rowIndex);
                    paragraph.add(text);
                }
                return paragraph;
            }
        }
        return null;
    }

    private Text BuildParagraphText(ExportPDFVo vo,Document document,Node parentNode,Node textNode,Map mapRecord, int rowIndex){
        Text text=new Text(GetValue(vo,document,textNode,mapRecord,rowIndex));
        String styleRef=XMLUtility.getAttribute(textNode,"StyleRef");
        styleRenderer.SetAbstractElementStyle(vo,document,textNode,text,styleRef);
        return text;
    }

    private Table BuildTable(ExportPDFVo vo,Document document, Node node,Map mapRecord, int rowIndex) throws IOException, Build4DGenerallyException, XPathExpressionException {
        if(node.getNodeName().equals("Table")){

            String columnWidthsAttr=XMLUtility.getAttribute(node,"ColumnWidths");
            if(columnWidthsAttr.equals("")){
                throw new Build4DGenerallyException(0,"表格必须设置ColumnWidths属性");
            }
            String[] columnWidthAttrArrary=columnWidthsAttr.split(",");
            float[] columnWidths = new float[columnWidthAttrArrary.length];
            for(int i=0;i<columnWidthAttrArrary.length;i++){
                columnWidths[i]= Float.parseFloat(columnWidthAttrArrary[i]);
            }

            Table table = new Table(columnWidths);
            //table.seth(1);
            //table.setSkipFirstHeader(true);
            String styleRef=XMLUtility.getAttribute(node,"StyleRef");
            styleRenderer.SetBlockElementStyle(vo,document,node,table,styleRef);
            List<Node> headerCellNodeList=XMLUtility.parseForNodeList(node,"HeaderCell");
            if(!headerCellNodeList.isEmpty()){
                for (Node cellNode : headerCellNodeList) {
                    Cell headerCell=BuildTableCell(vo,document,node,cellNode,mapRecord,0);
                    table.addHeaderCell(headerCell);
                }
            }

            String eachForDataSource=XMLUtility.getAttribute(node,"EachForDataSource");

            if(eachForDataSource.equals("")) {
                List<Node> cellNodeList = XMLUtility.parseForNodeList(node, "Cell");
                if (!cellNodeList.isEmpty()) {
                    for (Node cellNode : cellNodeList) {
                        Cell cell = BuildTableCell(vo, document, node, cellNode, mapRecord, 0);
                        table.addCell(cell);
                    }
                }
            }
            else
            {
                if(vo.getSubDataSourceVoMap().containsKey(eachForDataSource)) {
                    SubDataSourceVo subDataSourceVo = vo.getSubDataSourceVoMap().get(eachForDataSource);
                    List<Map> subDataSourceList = subDataSourceVo.getDataSource();
                    List<Map> filterSubDataSourceList=FilterDataByFK(vo,mapRecord,subDataSourceList,subDataSourceVo.getFkKey());
                    if(filterSubDataSourceList!=null&&filterSubDataSourceList.size()>0) {
                        List<Node> cellNodeList = XMLUtility.parseForNodeList(node, "Cell");
                        if (!cellNodeList.isEmpty()) {
                            for (int i = 0; i < filterSubDataSourceList.size(); i++) {
                                Map subRecordMap = filterSubDataSourceList.get(i);
                                for (Node cellNode : cellNodeList) {
                                    Cell cell = BuildTableCell(vo, document, node, cellNode, subRecordMap, i);
                                    table.addCell(cell);
                                }
                            }
                        }
                    }
                }
                else
                {
                    Cell cell = new Cell(1,1);
                    //cell.add("在子级数据中找不到名称为"+eachForDataSource+"的数据源!");

                    table.addCell(cell);
                }
            }

            return table;
        }
        return null;
    }

    private List<Map> FilterDataByFK(ExportPDFVo vo, final Map mapRecord, List<Map> subDataSourceList, final String fkKey){
        final String mainDataKey=vo.getMainDataSourceVo().getKey();
        //List<Map> filterDataSource=new ArrayList<Map>();
        return ListUtility.Where(subDataSourceList, new IListWhereCondition<Map>() {
            public boolean Condition(Map item) {
                if(item.containsKey(fkKey)) {
                    return item.get(fkKey).equals(mapRecord.get(mainDataKey));
                }
                return false;
                //throw new SSSGenerallyException(0,"");
            }
        });
    }

    private AreaBreak NextPage(ExportPDFVo vo,Document document, Node node,Map mapRecord, int rowIndex){
        if(node.getNodeName().equals("NextPage")){
            return new AreaBreak(AreaBreakType.NEXT_PAGE);
        }
        return null;
    }

    private Cell BuildTableCell(ExportPDFVo vo,Document document,Node parentNode,Node cellNode,Map mapRecord, int rowIndex){
        int rowSpan=1;
        int colSpan=1;
        String rowSpanAttr=XMLUtility.getAttribute(cellNode,"RowSpan");
        if(!rowSpanAttr.equals("")){
            rowSpan= Integer.parseInt(rowSpanAttr);
        }
        String colSpanAttr=XMLUtility.getAttribute(cellNode,"ColSpan");
        if(!colSpanAttr.equals("")){
            colSpan= Integer.parseInt(colSpanAttr);
        }
        Cell cell = new Cell(rowSpan, colSpan);
        String styleRef=XMLUtility.getAttribute(cellNode,"StyleRef");
        styleRenderer.SetBlockElementStyle(vo,document,cellNode,cell,styleRef);
        //cell.add(GetValue(vo,document,cellNode,mapRecord,rowIndex));
        //todo
        return cell;
    }

    private String GetValue(ExportPDFVo vo,Document document,Node node, Map record, int rowIndex){
        //1:使用API值
        //2:使用转换值
        //3:使用字段值
        //4:使用变量值
        //5:使用内容文本
        String value = "";
        String sourceValue = node.getTextContent();
        String bindFieldName=XMLUtility.getAttribute(node,"BindField");
        String formaterRef=XMLUtility.getAttribute(node,"FormaterRef");
        String vtConverterRef=XMLUtility.getAttribute(node,"VTConverterRef");
        String defaultConverter=XMLUtility.getAttribute(node,"DefaultConverter");
        if(!bindFieldName.equals("")){
            if (record.containsKey(bindFieldName)) {
                //数据优先级API>默认值>转换值>原始值
                Object obj = record.get(bindFieldName);
                sourceValue = obj == null ? "" : obj.toString();
            }
        }
        if(!formaterRef.equals("")) {
            value = this.GetFormaterValue(vo,formaterRef);
        } else if (!vtConverterRef.equals("")) {
            value = this.GetVTConvertValue(vo, sourceValue, sourceValue);
        } else if (!defaultConverter.equals("")) {
            value = this.GetDefaultConverterValue(vo, sourceValue,defaultConverter, rowIndex);
        } else {
            value = sourceValue;
        }
        return value;
    }

    private String GetFormaterValue(ExportPDFVo exportPDFVo,String formatRef) {
        IValueFormater valueFormater = exportPDFVo.getValueFormater().get(formatRef);
        if (valueFormater != null) {
            return valueFormater.Formater(exportPDFVo);
        }
        return "未找到格式化对象！";
    }

    private String GetVTConvertValue(ExportPDFVo exportPDFVo, String sourceValue,String vtConverterRef) {
        String VTKey = vtConverterRef.trim();
        if (exportPDFVo.getVtConvertDataSource().containsKey(VTKey)) {
            return exportPDFVo.getVtConvertDataSource().get(VTKey).get(sourceValue);
        }
        return "找不到数据源";
    }

    private String GetDefaultConverterValue(ExportPDFVo exportPDFVo, String sourceValue,String defaultConverterName, int RowIndex) {
        if (defaultConverterName.equals("ToShortDateTime")) {
            if (sourceValue != null && !sourceValue.equals("") && sourceValue.indexOf(" ") > 0) {
                return sourceValue.split(" ")[0];
            }
        }
        if (defaultConverterName.equals("GetCurrentShortDateTime")) {
            return DateUtility.getDate_yyyy_MM_dd();
        }
        if (defaultConverterName.equals("GetCurrentLongDateTime")) {
            return DateUtility.getDate_yyyy_MM_dd_HH_mm_ss();
        }
        if (defaultConverterName.equals("ToRowNumber")) {
            return String.valueOf(RowIndex + 1);
        }
        if(defaultConverterName.indexOf("GetSession")==0){
            B4DSession session= B4DSessionUtility.getSession();
            if(defaultConverterName.equals("GetSession.UserId")){
                return session.getUserId();
            }
            if(defaultConverterName.equals("GetSession.UserName")){
                return session.getUserName();
            }
            if(defaultConverterName.equals("GetSession.OrganId")){
                return session.getOrganId();
            }
            if(defaultConverterName.equals("GetSession.OrganName")){
                return session.getOrganName();
            }
            if(defaultConverterName.equals("GetSession.MainDepartmentId")){
                return session.getMainDepartmentId();
            }
            if(defaultConverterName.equals("GetSession.MainDepartmentName")){
                return session.getMainDepartmentName();
            }
        }
        return sourceValue;
    }

}
