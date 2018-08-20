package com.build4d.platform.export.pdf.datasource;

import com.build4d.base.service.exception.Build4DGenerallyException;
import com.build4d.base.tools.common.XMLUtility;
import com.build4d.platform.export.general.IExportDataSource;
import com.build4d.platform.export.general.constants.ExportSetTypeEnum;
import com.build4d.platform.export.general.vo.CategoryParaVo;
import com.build4d.platform.export.general.vo.MainDataSourceVo;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.ContextLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo-sss on 2017/9/6.
 */
public class CategoryTableDataSourceBuilder implements IExportDataSource {

    private static String FromCategoryTable = "CategoryTable";
    private static String FromText = "Text";

    public MainDataSourceVo getDataSource(Document document, Node exportNode, Node dataSourceNode, ExportSetTypeEnum exportSetType, String[] recordIdArray, CategoryParaVo categoryParaVo) throws Build4DGenerallyException {

        try {
            MainDataSourceVo mainDataSource = new MainDataSourceVo();
            Node sqlNode = XMLUtility.parseForNode(dataSourceNode, "SQL");
            String orderByAttrStr=XMLUtility.getAttribute(sqlNode,"OrderBy");
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
           /* if (sqlFrom.equals(FromCategoryTable)) {
                ITableService tableService=(ITableService) ContextLoader.getCurrentWebApplicationContext().getBean("tableService");
                String mainTableName=tableService.getMainTableNameByCategoryID(categoryParaVo.getCurrentCategoryId());
                String sql="select * from "+mainTableName +" where id in (";
                List<Object> neweparas = new ArrayList<Object>();
                for (String id : recordIdArray) {
                    sql += "?,";
                    neweparas.add(id);
                }
                sql = sql.substring(0, sql.length() - 1) + ")";
                if(!orderByAttrStr.equals("")){
                    sql=sql +" "+orderByAttrStr;
                }
                mainDataSource.setDataSource(HibernateStaticUtil.findByMultiRecordMapSQLString(sql, neweparas.toArray()));

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
