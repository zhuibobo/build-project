package com.build4d.platform.export.excel.datasource;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IExportDataSource;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.vo.CategoryParaVo;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuangrb
 * @Date: 2017/1/3
 * @Description:
 * @Version 1.0.0
 */
public class CategoryViewDataSourceBuilder implements IExportDataSource {

    private static String FromCategoryView = "CategoryView";
    private static String FromText = "Text";

    public MainDataSourceVo getDataSource(Document document, Node exportNode, Node dataSourceNode, ExportSetTypeEnum exportSetType, String[] recordIdArray, CategoryParaVo categoryParaVo) throws Build4DGenerallyException {
        try {
            MainDataSourceVo mainDataSource = new MainDataSourceVo();
            Node sqlNode = XMLUtility.parseForNode(dataSourceNode, "SQL");
            String sqlFrom = XMLUtility.getAttribute(sqlNode, "From");
            boolean enable = XMLUtility.getAttribute(dataSourceNode, "Enable").equals("True") ? true : false;
            String key = XMLUtility.getAttribute(dataSourceNode, "Key");
            mainDataSource.setEnable(enable);
            mainDataSource.setKey(key);
            mainDataSource.setName("Main");
            if (!enable) {
                return mainDataSource;
            }

            //todo
            /*if (sqlFrom.equals(FromCategoryView)) {
                String view_SQL = SSSSessionUtil.getSessionAttr(SSSSessionUtil.ExprotToExcel_CategoryView_SQL).toString();
                List<Object> sourceparas = (List<Object>) SSSSessionUtil.getSessionAttr(SSSSessionUtil.ExprotToExcel_CategoryView_UserInputConditionParasKey);
                List<Object> neweparas = new ArrayList<Object>();
                for (Object sourcepara : sourceparas) {
                    neweparas.add(sourcepara);
                }

                if (exportSetType == ExportSetTypeEnum.LISTSETWITHSELECTED) {
                    if (recordIdArray != null && recordIdArray.length > 0) {
                        //解决视图中多表链接查询时ID不明确，以主表名称为准
                        int formIndex = view_SQL.indexOf("FROM ") + 5;
                        String tableName = "";
                        if (formIndex > 0) {
                            int tableLastIndex = view_SQL.indexOf(" ", formIndex);
                            tableName = view_SQL.substring(formIndex, tableLastIndex);
                        }
                        String idCondition = " AND ID IN (";
                        if (!tableName.equals("")) {
                            idCondition = " AND " + tableName + ".ID IN (";
                        }
                        for (String id : recordIdArray) {
                            idCondition += "?,";
                            neweparas.add(id);
                        }
                        idCondition = idCondition.substring(0, idCondition.length() - 1) + ")";

                        // ORDER BY
                        if (view_SQL.lastIndexOf(" ORDER BY ") > 0) {
                            //view_SQL.lastIndexOf()
                            String[] tempSQL = view_SQL.split(" ORDER BY ");
                            view_SQL = tempSQL[0] + idCondition + " ORDER BY " + tempSQL[1];
                            //view_SQL=view_SQL.substring(0,view_SQL.lastIndexOf(" ORDER BY "))+
                        } else {
                            view_SQL += idCondition;
                        }
                    }
                }
                mainDataSource.setDataSource(HibernateStaticUtil.findByMultiRecordMapSQLString(view_SQL, neweparas.toArray()));
            } else if (sqlFrom.equals(FromText)) {
                String sql = sqlNode.getTextContent().trim();
                mainDataSource.setDataSource(HibernateStaticUtil.findByMultiRecordMapSQLString(sql));
            }*/
            return mainDataSource;
        } catch (Exception ex) {
            throw new Build4DGenerallyException(0, ex.getMessage());
        }
    }
}
