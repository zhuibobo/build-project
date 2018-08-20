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
                <col style="">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row">
                <td>建设单位：</td>
                <td>
                    <i-input v-model="search_condition.organName.value" placeholder="请输入建设单位"></i-input>
                </td>
                <td>项目名称：</td>
                <td>
                    <i-input v-model="search_condition.projName.value" placeholder="请输入项目名称"></i-input>
                </td>
                <td>工程名称：</td>
                <td>
                    <i-input v-model="search_condition.engName.value" placeholder="请输入工程名称"></i-input>
                </td>
                <td>工程类型：</td>
                <td>
                    <i-select v-model="search_condition.engType.value" placeholder="请选择所属工程类别" >
                        <i-option value=""  >请选择所属工程类别</i-option>
                        <i-option  v-for="item in getEngTypeByPid('')" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                    </i-select>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="float: right"><%--status>800 && status!=900--%>
            <i-button type="primary" @click="viewQRCode" v-if="QRCodeStatus"><Icon type="android-open"></Icon> 生成二维码 </i-button>
            <i-button type="primary" @click="print" v-if="CheckStatus"><Icon type="printer"></Icon> 打印预验收意见书 </i-button>
            <i-button type="primary" @click="checkItem"><Icon type="hammer"></Icon> 处理 </i-button>
            <i-button type="primary" @click="viewItem"><Icon type="android-open"></Icon> 浏览 </i-button>
        </div>
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
                    title: '建设单位',
                    key: 'constructionOrgEntity.orgName',
                    render: function (h,params) {
                        return h('span',params.row.constructionOrgEntity.orgName);
                    }
                },
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
                        return h('span',ProjectUtil.ProMenum.FlowStatus[params.row.status] );
                    }
                },
                {
                    title: '操作',
                    key: 'sid',
                    width:120,
                    render:function (h, params) {
                        return h('div', {class: "list-row-button-wrap"}, [
                            h('div', {
                                class: "list-row-button list-row-button-view",
                                on: {
                                    click: function () {
                                        app.viewData(params.row.sid
                                            , params.row.engType,
                                            params.row.status,params.row.engName);
                                    }
                                }
                            }),
                            h('div', {
                                class: "list-row-button list-row-button-check-do",
                                on: {
                                    click: function () {
                                        app.checkData(params.row.sid,
                                            params.row.engType,
                                            params.row.status,params.row.engName);
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
            engType:ProjectUtil.ProMenum.EngType,
            orgType:"${orgType}",
            search_condition:{
                organName:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
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
                status:{
                    value:StringUtility.QueryString("status"),
                    type:SearchUtility.SearchFieldType.StringType
                },
                engType:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                currOrgId:{
                    value:'${currOrgId}',
                    type:SearchUtility.SearchFieldType.StringType
                }
            }
        },
        computed:{
            QRCodeStatus:function () {
                var s=StringUtility.QueryString("status");
                if(s==null)
                    return false;
                else
                    return ProjectUtil.EngMethod.QRCodePriv(parseInt(s))
            },
            CheckStatus:function () {
                var s=StringUtility.QueryString("status");
                if(s==null)
                    return false;
                else
                    return s==800;
            }
        },
        methods:{
            getEngTypeByPid:function (pid) {
               return ProjectUtil.EngMethod.GetEngTypeByPid(pid)
            },
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            checkData:function(id,engType,status,engName){
                this.handle(id,engType,status,engName,false);
            },
            viewData:function(id,engType,status,engName){
                this.handle(id,engType,status,engName,true);
            },
            handle:function (id,engType,status,engName,isView,config) {
                var title= ProjectUtil.ProMenum.FlowStatus[status];
                var url="";
                var urlOptions=ProjectUtil.CommMethod.GetWidthHeight(window.parent);
                url=ProjectUtil.FlowMethod.GetHandleUrl(id,engType,status,this.orgType,isView);
                var option=$.extend(true,{title:engName+"【工程当前状态："+title+"】",width:1024,height:768},urlOptions,config);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId02,url,option);
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
            checkItem:function () {
                this.checkData(this.current_selected_row.sid
                    ,this.current_selected_row.engType
                    ,this.current_selected_row.status,this.current_selected_row.engName);
            },
            viewItem:function () {
                this.viewData(this.current_selected_row.sid
                    ,this.current_selected_row.engType
                    ,this.current_selected_row.status,this.current_selected_row.engName);
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            },
            search:function () {
                this.page_num=1;
                this.reloadData();
            },
            print:function () {
                var row=this.current_selected_row;
                var id=row.sid;
                var url=BaseUtility.BuildUrl("/project/engineering/printtemp.do?sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId02,url,{title:"打印资料",width:1024,height:768});
            },
            viewQRCode:function () {
                ProjectUtil.EngMethod.BuildQRCode(this.current_selected_row.sid)
            }
        }
    });
</script>
</body>
</html>

