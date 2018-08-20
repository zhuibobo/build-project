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
                <td>案卷名称：</td>
                <td>
                    <i-input v-model="search_condition.archTitle.value" placeholder="请输入案卷名称"></i-input>
                </td>
                <td>编制单位：</td>
                <td>
                    <i-input v-model="search_condition.makeOrgName.value" placeholder="请输入编制单位"></i-input>
                </td>
                <td>审核状态：</td>
                <td>
                    <i-select v-model="search_condition.status.value" placeholder="请选择审核状态" style="text-align: left; width:120px;">
                        <i-option value=""  >请选择审核状态</i-option>
                        <i-option value="0"><span class="statusColor" :style="getStatusColor(0)"></span>待审核</i-option>
                        <i-option value="1"><span class="statusColor" :style="getStatusColor(1)"></span>审核通过</i-option>
                        <i-option value="2"><span class="statusColor" :style="getStatusColor(2)"></span>审核未通过</i-option>
                    </i-select>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 98%; margin:10px 10px;   ">
        <div style="float: right" id="btList">
            <i-button type="primary" @click="viewEngItem"><Icon type="android-open"></Icon> 工程信息 </i-button>
            <i-button type="primary" @click="viewArchItem"><Icon type="android-open"></Icon> 文件明细 </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <div style="width: 98%; margin:10px 10px;   ">
        <i-table border height="500" :row-class-name="rowClassName" :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
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
    var engBaseEntity=${engBaseEntity};
    var op=StringUtility.QueryString("op");
    ProjectUtil.FlowMethod.RenderingActions(op,"btList",organEntity.orgType,engBaseEntity.status);
    var app=new Vue({
        el:"#app",
        mounted:function () {
            document.title=engBaseEntity.engName;
            this.reloadData();
        },
        data:{
            columns_config: [
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
                    width:90,
                    render: function (h,params) {
                        return h("span",params.row.archTypeCode);
                    }
                },
                {
                    title: '立卷人',
                    key: 'createdBy',
                    width:90
                },
                {
                    title: '编制日期',
                    key: 'createdDt',
                    width:130,
                    render: function (h,params) {
                        return h("span",DateUtility.Format(new Date(params.row.createdDt),"yyyy-MM-dd"));
                    }
                },
                {
                    title: '案卷序号',
                    key: 'engArchNo',
                    width:90
                },
                {
                    title: '状态',
                    key: 'status',
                    width:50,
                    render: function (h,params) {
                        var s=app.getStatusColor(params.row.status);
                        s.marginLeft="10px"
                        return h("Tooltip",{props:{
                                content:app.getStatus(params.row.status),placement:'top'
                            }},[h('span',{
                            class:'statusColor',
                            style:s
                        })]);
                    }
                },
                {
                    title: '审核信息',
                    key: 'checkOpinion',
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'checkOpinion','on-input-change');
                    }
                },
                {
                    title: '操作',
                    key: 'sid',
                    width:(function () {
                            if(organEntity.orgType=="档案管理单位")
                                return 100;
                            else
                                return 50;
                        })()
                    ,
                    render:function (h, params) {
                        if(organEntity.orgType=="档案管理单位")
                        {
                            return h('div',{class:"list-row-button-wrap"},[
                                h('div', {
                                    class:"list-row-button list-row-button-view",
                                    attrs: {
                                        title: "查看文件明细"
                                    },
                                    on: {
                                        click: function () {
                                            app.viewArchItem(params.row.sid);
                                        }
                                    }
                                }),
                                h('div', {
                                    class: "list-row-button list-row-button-check-pass",
                                    attrs: {
                                        title: "此案卷审核通过"
                                    },
                                    on: {
                                        click: function () {
                                            app.singleArchCheck(1,params.row);
                                        }
                                    }
                                }),
                                h('div', {
                                    class: "list-row-button list-row-button-check-nopass",
                                    attrs: {
                                        title: "此案卷审核不通过"
                                    },
                                    on: {
                                        click: function () {
                                            app.singleArchCheck(2,params.row);
                                        }
                                    }
                                })
                            ]);
                        }
                        else
                        {
                            return  h('div', {
                                class:"list-row-button list-row-button-view",
                                attrs: {
                                    title: "查看文件明细"
                                },
                                on: {
                                    click: function () {
                                        app.viewArchItem(params.row.sid);
                                    }
                                }
                            });
                        }
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:20,
            page_num:1,
            loading:false,
            organEntity:organEntity,
            engBaseEntity:engBaseEntity,
            updateItems:[],
            search_condition:{
                engSid:{
                    value:${sid},
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
                    value:'',
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
            getStatus:function (i) {
                if(i==null)
                    return '待审核';
                else{
                    var s=['待审核','审核通过','审核未通过'];
                    return s[i];
                }
            },
            getStatusColor:function (i) {
                var c=['lightgray','green','red'];
                return {backgroundColor:c[i]};
            },
            buildCell:function (h,el,params,field,handle) {
                var t=this.organEntity.orgType;
                var isEdit=false;
                if(t=="档案管理单位")
                {
                    isEdit='checkOpinion,'.indexOf(field+",")>=0
                }
                else
                {
                    if('checkOpinion,'.indexOf(field+",")<0)
                        isEdit=ct.indexOf(t)>=0;
                }

                if(isEdit) {
                    //app.$emit('on-change',event,params);
                    return h(el, {
                        attrs: {
                            title: params.row[field]
                        },
                        props: {
                            value: params.row[field]
                        },
                        on: (function(handle){
                            var fun={};
                            fun[handle]= function (event) {
                                var v =event;
                                if(event.target)
                                    v = event.target.value;

                                app.table_data[params.index][field] = v;
                                if($.inArray(params.index,app.updateItems)<0)
                                    app.updateItems[app.updateItems.length]=params.index;
                            };
                            return fun
                        })(handle)
                    });
                }
                else {
                    return h('span', params.row[field]);
                }
            },
            rowClassName:function (row,index) {
                /*if(row.status==1)
                {
                    return 'tr-disabled';
                }*/
                return '';
            },
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            viewEngItem:function(){
                var id=this.engBaseEntity.sid;
                var url=BaseUtility.BuildUrl("/project/engineering/engineeringdetail.do?op=view&engType="+this.engBaseEntity.engType+"&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:"工程详细",width:1024,height:768});
            },
            viewArchItem:function(){
                //DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"功能开发中...！");
                var id=this.current_selected_row.sid;
                var title=this.engBaseEntity.engName+"【目录文件明细】";
                var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/cataloglistbyarch.do?op=view&sid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:title,width:1024,height:768});
            },
            uploadItem:function () {
                if(!this.current_selected_row.proEngConfMateEntity.isdirectory)
                {
                    var id=this.current_selected_row.sid;
                    var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/engineeringuploadfile.do?sid="+id);
                    var title=this.engBaseEntity.engName+" ~ "+this.current_selected_row.fileTitle;
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:title+"【文件上传】",width:1024,height:768});
                }
                else
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"此项为目录, 无法文件上传！");
                }
            },
            reloadData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/archive/getarchivelistdatabypage.do';
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
            singleArchCheck:function(status,row){
                var _self=this;
                row.status=status;
                var sendData = JSON.stringify(row);
                var url = '/project/engineering/archive/singleArchCheck.do';
                AjaxUtility.PostRequestBody(url, sendData, function (result) {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                    });
                }, "json");
            },
            saveItems:function (isAlert) {
                var _self=this;
                var items=[];
                if(isAlert==undefined)isAlert=true;
                $.each(this.updateItems,function (k,v) {
                    items[items.length]=_self.table_data[v];
                });
                if(items.length>0) {
                    var sendData = JSON.stringify(items);
                    var url = '/project/engineering/catalogfile/saveengcataloglistdata.do';
                    AjaxUtility.PostRequestBody(url, sendData, function (result) {
                        if(isAlert) {
                            DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                            });
                        }
                    }, "json");
                }
                else if(isAlert)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"无更新内容！");
                }
            },
            handleFlowSubmit: function (currStatus,nextStatus,needOpinion,saveForm) {
                var _self=this;
                if(needOpinion)
                {
                    DialogUtility.ComfirmBy(window,$("#fOpinion"),{title:"未通过原因",height:230,width:300},function () {
                        var v=false;
                        _self.$refs["formValidate2"].validate(function (valid) {
                            v=valid;
                            if (valid) {
                                ProjectUtil.FlowMethod.UpdateStatus(StringUtility.QueryString("sid"),
                                    currStatus,nextStatus,_self.formValidate2.opinion,true,function (result) {
                                        if(saveForm)
                                        {
                                            _self.saveItems(false);
                                        }
                                    });
                            }
                        });
                        return v;

                    });
                }
                else{
                    ProjectUtil.FlowMethod.UpdateStatus(StringUtility.QueryString("sid"),
                        currStatus,nextStatus,"",true,function (result) {
                            if(saveForm)
                            {
                                _self.saveItems(false);
                            }
                        });
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
