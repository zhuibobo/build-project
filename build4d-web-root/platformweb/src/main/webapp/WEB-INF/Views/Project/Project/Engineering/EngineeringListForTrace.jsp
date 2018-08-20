<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/5/9
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
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
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
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row">
                <td>项目名称：</td>
                <td>
                    <i-input v-model="search_condition.projName.value" placeholder="请输入项目名称"></i-input>
                </td>
                <td>工程名称：</td>
                <td>
                    <i-input v-model="search_condition.engName.value" placeholder="请输入工程名称"></i-input>
                </td>
                <td>工程编号：</td>
                <td>
                    <i-input v-model="search_condition.engNo.value" placeholder="请输入工程编号"></i-input>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="float: right">
            <i-button type="primary" v-if="seen=='' || seen=='ifEngProgress'" @click="viewItem('ifEngProgress')"><Icon type="android-open"></Icon> 工程状态跟踪 </i-button>
            <i-button type="primary" v-if="seen=='' || seen=='ifFileProgress'" @click="viewItem('ifFileProgress')"><Icon type="android-open"></Icon> 文件收集进度 </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <i-table stripe border :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
    <div style="float: right;">
        <page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
    </div>
</div>
<script>
    var progress=StringUtility.QueryString("progress");
    var app=new Vue({
        el:"#app",
        mounted:function () {
            this.reloadData();
        },
        data:{
            seen:progress,
            columns_config: [
                {
                    title: '项目名称',
                    key: 'proBaseInfo.projName',
                    render: function (h,params) {
                        return h('span',params.row.proBaseInfo.projName);
                    }
                },
                {
                    title: '工程类型',
                    key: 'engType',
                    render: function (h,params) {
                        return h('span', ProjectUtil.EngMethod.GetEngTypeNameById(params.row.engType,params.row.engType2));
                    }
                },
                {
                    title: '工程编号',
                    key: 'engNo'
                },
                {
                    title: '工程名称',
                    key: 'engName'
                },
                {
                    title: '规划许可证',
                    key: 'engOrgAndCode.landUsePlanningNo',
                    render: function (h,params) {
                        return h('span',params.row.engOrgAndCode.landUsePlanningNo);
                    }
                },
                {
                    title: '施工许可证',
                    key: 'engOrgAndCode.constructNo',
                    render: function (h,params) {
                        return h('span',params.row.engOrgAndCode.constructNo);
                    }
                },
                {
                    title: '业务指导人员',
                    key: 'engOrgAndCode.instructor',
                    render: function (h,params) {
                        return h('span',params.row.engOrgAndCode.instructor);
                    }
                },
                {
                    title: '工程状态',
                    key: 'status',
                    render: function (h,params) {
                        return h('span',ProjectUtil.ProMenum.FlowStatus[params.row.status]);
                    }
                },
                {
                    title: '查看进度',
                    key: 'sid',
                    width:90,
                    render:function (h, params) {
                        return h('div',{class:"list-row-button-wrap"},[
                            h('div', {
                                class:"list-row-button list-row-button-view",
                                on: {
                                    click: function () {
                                        app.viewData(params.row,progress);
                                    }
                                }
                            })
                        ]);
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:5,
            page_num:1,
            loading:false,
            search_condition:{
                projName:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                engNo:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                engName:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                engType:{
                    value:StringUtility.QueryString("engType"),
                    type:SearchUtility.SearchFieldType.IntType
                },
                /*currOrgNoCode:{
                    value:'${currOrgCode}',
                    type:SearchUtility.SearchFieldType.StringType
                },*/
                currOrgId:{
                    value:'${currOrgId}',
                    type:SearchUtility.SearchFieldType.IntType
                }
            }
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            viewData:function(row,progress){
                var url=BaseUtility.BuildUrl("/project/engineering/engineeringdetail.do?op=view&tab="+progress+"&engType="+row.engType+"&sid="+row.sid);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"工程信息",width:1024,height:768});
            },
            reloadData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/getengineeringlistdata.do';
                var senddata={
                    page_num:_self.page_num,
                    page_size:_self.page_size,
                    search_condition:JSON.stringify(_self.search_condition)
                }
                AjaxUtility.Get(url, senddata , function (result) {
                    if (result.success) {
                        _self.table_data = new Array();
                        _self.table_data = result.data.list;
                        _self.page_total = result.data.total;
                    }
                    _self.loading=false;
                },"json");
            },
            viewItem:function (progress) {
                this.viewData(this.current_selected_row,progress);
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

