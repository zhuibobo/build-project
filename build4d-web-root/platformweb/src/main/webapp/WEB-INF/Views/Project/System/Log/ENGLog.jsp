<%--
  Created by IntelliJ IDEA.
  User: zhuangrb
  Date: 2018/7/21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
</head>
<body>
<div id="app">
    <div class="list-simple-search-wrap">
        <table class="ls-table">
            <colgroup>
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
                <col style="">
                <col style="width: 100px">
                <col style="">
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row" style="display: none">
                <td>机构名称：</td>
                <td>
                    <i-input v-model="search_condition.orgName.value" placeholder="请输入机构名称"></i-input>
                </td>
                <td>机构代码：</td>
                <td>
                    <i-input v-model="search_condition.orgCode.value" placeholder="请输入机构代码"></i-input>
                </td>
                <td>创建时间（从）：</td>
                <td>
                    <date-picker v-model="search_condition.orgCreateTime_s.value" type="date" placeholder="Select date" style="width: 100%"></date-picker>
                </td>
                <td>（到）：</td>
                <td>
                    <date-picker v-model="search_condition.orgCreateTime_e.value" type="date" placeholder="Select date" style="width: 100%"></date-picker>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="clear: both"></div>
    </div>
    <i-table stripe border :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
    <div style="float: right;">
        <page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
    </div>
</div>
<script>
    var app=new Vue({
        el:"#app",
        mounted:function () {
            this.reloadData();
        },
        data:{
            columns_config: [
                {
                    title: '工程名称',
                    key: 'ENG_NAME'
                },
                {
                    title: '单位名称',
                    key: 'ORGAN_NAME'
                },
                {
                    title: '单位类型',
                    key: 'ORGAN_TYPE'
                },
                {
                    title: '用户名称',
                    key: 'USER_NAME'
                },
                {
                    title: '操作时间',
                    key: 'OP_DATE',
                    render: function (h, params) {
                        return B4D.ListPageUtility.IViewTableRenderer.ToDateYYYY_MM_DD(h,params.row.OP_DATE);
                    }
                },
                {
                    title: '状态名称',
                    key: 'STATUS_NAME'
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:10,
            page_num:1,
            loading:false,
            search_condition:{
                orgName:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                orgCode:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                orgCreateTime_s:{
                    value:"",
                    type:SearchUtility.SearchFieldType.DataStringType
                },
                orgCreateTime_e:{
                    value:"",
                    type:SearchUtility.SearchFieldType.DataStringType
                }
            }
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            reloadData:function () {
                var url='/project/log/listdata.do';
                var _self=this;
                _self.loading=true;
                //debugger;
                var senddata={
                    page_num:_self.page_num,
                    page_size:_self.page_size,
                    search_condition:JSON.stringify(_self.search_condition)
                }
                AjaxUtility.Post(url, senddata , function (result) {
                    if (result.success) {
                        _self.table_data = new Array();
                        _self.table_data = result.data.list;
                        _self.page_total = result.data.total;
                    }
                    _self.loading=false;
                },"json");
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            },
            search:function () {
                this.page_num=1;
                this.reloadData();
            }
        }
    });
</script>
</body>
</html>