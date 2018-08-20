<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/6/10
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
    <div class="list-simple-search-wrap" style="width: 98%; margin:10px 10px;   ">
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
                <td>工程名称：</td>
                <td>
                    <i-input v-model="search_condition.engName.value" placeholder="请输入工程名称"></i-input>
                </td>
                <td>工程编号：</td>
                <td>
                    <i-input v-model="search_condition.engNo.value" placeholder="请输入工程编号"></i-input>
                </td>
                <td>案卷名称：</td>
                <td>
                    <i-input v-model="search_condition.archTitle.value" placeholder="请输入案卷名称"></i-input>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 98%; margin:10px 10px;   ">
        <div style="float: right" id="btList">
            <i-button type="primary" @click="viewEngItem"><Icon type="android-open"></Icon> 工程信息 </i-button>
            <i-button type="primary" @click="viewArchItem"><Icon type="android-open"></Icon> 案卷信息 </i-button>
            <i-button type="primary" @click="viewArchFiles"><Icon type="android-open"></Icon> 文件明细 </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <div style="width: 98%; margin:10px 10px;   ">
        <i-table border  :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
        <div style="float: right;">
            <page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
        </div>
    </div>
    <div style="display: none;">
        <i-form id="fOpinion" ref="formValidate2" :model="formValidate2" :rules="ruleValidate2" >
            <form-item   prop="opinion">
                <i-input v-model="formValidate2.opinion" type="textarea" :rows="4" placeholder="请输入未通过原因"></i-input>
            </form-item>
        </i-form>
    </div>
</div>
<script>
    var organEntity=${organEntity};
    var op = StringUtility.QueryString("op");
    var status = StringUtility.QueryString("status");
    status=status==null?1:status;
    var app=new Vue({
        el:"#app",
        mounted:function () {
            this.reloadData();
        },
        data:{
            columns_config: [
                {
                    title: '工程编号',
                    key: 'engBaseInfoEntity.engNo',
                    width:150,
                    render: function (h,params) {
                        return h("span",params.row.engBaseInfoEntity.engNo);
                    }
                },
                {
                    title: '工程名称',
                    key: 'engBaseInfoEntity.engName',
                    render: function (h,params) {
                        return h("span",params.row.engBaseInfoEntity.engName    );
                    }
                },
                {
                    title: '案卷题名',
                    key: 'archTitle'
                },
                {
                    title: '编制单位',
                    key: 'makeOrgName',
                    width:150
                },
                {
                    title: '案卷类型',
                    key: 'archTypeCode',
                    width:80,
                    render: function (h,params) {
                        return h("span",params.row.archTypeCode);
                    }
                },
                {
                    title: '立卷人',
                    key: 'createdBy',
                    width:100
                },
                {
                    title: '编制日期',
                    key: 'createdDt',
                    width:90,
                    render: function (h,params) {
                        return h("span",DateUtility.Format(new Date(params.row.createdDt),"yyyy-MM-dd"));
                    }
                },
                {
                    title: '案卷序号',
                    key: 'engArchNo',
                    width:80
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:20,
            page_num:1,
            loading:false,
            organEntity:organEntity,
            updateItems:[],
            search_condition:{
                engNo:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                engName:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                archTitle:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                makeOrgName:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                status:{
                    value:status,
                    type:SearchUtility.SearchFieldType.IntType
                },
                organSid:{
                    value:organEntity.orgType=="档案管理单位"?'':organEntity.orgSid,
                    type:SearchUtility.SearchFieldType.IntType
                }
            },
            formValidate2:{
                opinion:""
            },
            ruleValidate2: {
                opinion: [
                    { required: true, message: '请输入【未通过原因】！',  trigger: 'blur' }
                ],
            }
        },
        computed:{

        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            viewEngItem:function(){
                var id=this.current_selected_row.engSid;
                var engType=this.current_selected_row.engBaseInfoEntity.engType;
                var url=BaseUtility.BuildUrl("/project/engineering/engineeringdetail.do?op=view&engType="+engType+"&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:"工程详细",width:1024,height:768});
            },
            viewArchFiles:function(){
                //DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"功能开发中...！");
                var id=this.current_selected_row.sid;
                var title=this.current_selected_row.engBaseInfoEntity.engName+"【案卷文件明细】";
                var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/cataloglistbyarch.do?op=view&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:title,width:1024,height:768});
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
            viewArchItem:function(){
                var id=this.current_selected_row.sid;
                var title=this.current_selected_row.archTitle;
                var url=BaseUtility.BuildUrl("/project/engineering/archive/archdetail.do?op=view&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId04,url,{title:title+"【案卷信息】",width:1024,height:600});
            },
            reloadData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/archive/getarchivelistjoinengdata.do';
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
