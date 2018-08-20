<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/6/6
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
    <style>
        .ivu-table-cell{
            padding-left: 10px;
            padding-right: 10px;
        }
        .ivu-table .tr-disabled td{
            background-color: #f8f8f9;
        }
        .ivu-input-number
        {
            width: 100%;
        }
        .statusColor{
            display: inline-block; border-radius: 50%;
            background:lightgray ; height: 12px; width: 12px;
            top:1px; position:relative;margin-right: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="app">
    <div class="list-simple-search-wrap">
        <table class="ls-table">
            <colgroup>
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row">
                <td>文件名称：</td>
                <td>
                    <i-input v-model="search_condition.fileTitle.value" placeholder="请输入文件名称"></i-input>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="float: right" id="btList">
            <i-button type="primary" @click="viewFileItem"><Icon type="android-document"></Icon> 文件信息 </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <i-table stripe border height="500" :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
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
                    title: '编号',
                    key: 'fileNo',
                    width:80,
                },
                {
                    title: '文件名称',
                    key: 'fileTitle'
                },
                {
                    title: '责任者',
                    key: 'responsibility',
                    width:100
                },
                {
                    title: '文（图）号',
                    key: 'fileImageNo',
                    width:90
                },
                {
                    title: '形成日期',
                    key: 'createdStart',
                    width:130,
                    render: function (h,params) {
                        return h("span",app.fomateDate(params.row.createdStart));
                    }
                },
                {
                    title: '实体页数',
                    key: 'realPageNums',
                    width:90
                },
                {
                    title: '上传页数',
                    key: 'uploadPageNums',
                    width:90
                },
                {
                    title: '排序编号',
                    key: 'engArchNo',
                    width:80
                },
                {
                    title: '原文',
                    key: 'sid',
                    width:50,
                    render: function (h,params) {
                        return  h('div', {
                            class:"list-row-button list-row-button-pdf"+(params.row.pdfCreateFlag==null?0:params.row.pdfCreateFlag),
                            attrs: {
                                title: "PDF原文"
                            },
                            on: {
                                click: function () {
                                    app.viewPDFFileItem(params.row);
                                }
                            }
                        })
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:20,
            page_num:1,
            loading:false,
            search_condition:{
                fileTitle:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                isSelect:{
                    value:1,
                    type:SearchUtility.SearchFieldType.IntType
                },
                archSid:{
                    value:${sid},
                    type:SearchUtility.SearchFieldType.IntType
                },
                isdirectory:{
                    value:0,
                    type:SearchUtility.SearchFieldType.IntType
                }
            }
        },
        computed:{

        },
        methods:{
            fomateDate:function (date) {
                if(date)
                    return DateUtility.Format(new Date(date),"yyyy-MM-dd");
                else
                    return '';
            },
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            reloadData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/catalogfile/getcataloglistdatabypage.do';
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
            viewFileItem:function(){
                var id=this.current_selected_row.sid;
                var title=this.current_selected_row.fileTitle;
                var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/catalogdetail.do?op=view&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId04,url,{title:title+"【文件信息】",width:1024,height:600});
            },
            viewPDFFileItem:function (row) {
                if(row.pdfCreateFlag==1)
                {
                    ProjectUtil.CommMethod.viewFile(row.fileTitle+"【PDF原文】",row.pdfPath);
                }
                else
                {
                    DialogUtility.Alert(window, DialogUtility.DialogId04, {},"文件不存在！");
                }
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            },
            search:function () {
                this.page_num=1;
                this.reloadData();
            },
            handleClose: function (name) {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    });
</script>
</body>
</html>
