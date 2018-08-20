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
    <Alert type="warning" closable show-icon>注：文件每天只可以上传分项的15%，最少上传一条！</Alert>
    <div class="list-simple-search-wrap">
        <table class="ls-table">
            <colgroup>
                <col style="width: 80px">
                <col style="width: 120px">
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
                <col style="width: 120px">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row">
                <td>业务单位：</td>
                <td>
                    <i-select v-model="search_condition.unitType.value" placeholder="请选择单位类型" style="text-align: left;width: 120px">
                        <i-option value=""  v-if="unitTypePriv('建设单位')">请选择单位类型</i-option>
                        <i-option v-if="unitTypePriv('建设单位')" value="建设单位">建设单位</i-option>
                        <i-option v-if="unitTypePriv('施工单位')" value="施工单位">施工单位</i-option>
                        <i-option v-if="unitTypePriv('监理单位')" value="监理单位">监理单位</i-option>
                    </i-select>
                </td>
                <td>文件类型：</td>
                <td>
                    <i-select v-model="search_condition.fileNo.value" placeholder="请选择文件类型" style="text-align: left">
                        <i-option value=""  >请选择文件类型</i-option>
                        <i-option  v-for="item in catalogDirEntities" :key="item.mateValue" :value="item.mateValue"  >{{item.mateValue}}：{{item.mateName}}</i-option>
                    </i-select>
                </td>
                <td>文件名称：</td>
                <td>
                    <i-input v-model="search_condition.fileTitle.value" placeholder="请输入文件名称"></i-input>
                </td>
                <td>文件状态：</td>
                <td>
                    <i-select v-model="search_condition.status.value" placeholder="请选择文件状态" style="text-align: left;width: 120px">
                        <i-option value=""  >请选择文件状态</i-option>
                        <i-option value="0"><span class="statusColor" :style="getStatusColor(0)"></span>未登记</i-option>
                        <i-option value="1"><span class="statusColor" :style="getStatusColor(1)"></span>已登记</i-option>
                        <i-option value="2"><span class="statusColor" :style="getStatusColor(2)"></span>预验通过</i-option>
                        <i-option value="3"><span class="statusColor" :style="getStatusColor(3)"></span>未通过</i-option>
                        <i-option value="4"><span class="statusColor" :style="getStatusColor(4)"></span>再次提交</i-option>
                    </i-select>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="float: right" id="btList">
            <i-button type="primary" @click="viewEngItem"><Icon type="android-open"></Icon> 工程信息 </i-button>
            <i-button type="primary" @click="addItem"><Icon type="android-open"></Icon> 新增目录 </i-button>
            <i-button type="primary" @click="saveItems"><Icon type="edit"></Icon> 保存目录 </i-button>
            <i-button type="primary" @click="uploadItem"><Icon type="upload"></Icon> 上传电子件</i-button>
            <i-button type="primary" @click="editFileItem"><Icon type="android-document"></Icon> 著录 </i-button>
            <i-button type="primary" @click="BuildPDFFileItem"><Icon type="android-attach"></Icon> 生成PDF </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <i-table border :height="listHeight" :row-class-name="rowClassName" :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
    <div style="float: right;">
        <page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
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
    var engSid=StringUtility.QueryString("sid");
    var op = StringUtility.QueryString("op");

    function getEngData() {
        var r={};
        var url='/project/engineering/getengineeringbasedata.do';
        var sendData={sid:engSid};
        AjaxUtility.PostSync(url,sendData,function (result) {
            r=result.data;
        },"json")
        return r;
    }
    var organEntity=${organEntity};
    var engBaseEntity=getEngData();
    var catalogDirEntities=${catalogDirEntities};
    ProjectUtil.FlowMethod.RenderingActions(op,"btList",organEntity.orgType,engBaseEntity.status);

    var app=new Vue({
        el:"#app",
        mounted:function () {
            document.title=this.engBaseEntity.engName;
            this.reloadData2();
            if(this.engBaseEntity.engOrgAndCode.supervisionOrgSid==null || this.engBaseEntity.engOrgAndCode.constructOrgSid==null)
            {
                var url=BaseUtility.BuildUrl("/project/engineering/engineeringdetail.do?op=onlysave&sid="+this.engBaseEntity.sid+"&engType="+this.engBaseEntity.engType);
                var urlOptions=ProjectUtil.CommMethod.GetWidthHeight(window.parent);
                var option=$.extend(true,urlOptions,{title:"工程信息补全",height:768,show:true,modal:true});
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId04,url, option);
            }
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
                    key: 'fileTitle',
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'fileTitle','on-input-change');
                    }
                },
                {
                    title: '责任者',
                    key: 'responsibility',
                    width:90,
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'responsibility','on-input-change');
                    }
                },
                {
                    title: '文（图）号',
                    key: 'fileImageNo',
                    width:90,
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'fileImageNo','on-input-change');
                    }
                },
                {
                    title: '形成日期',
                    key: 'createdStart',
                    width:130,
                    render: function (h,params) {
                        return app.buildCell(h,'date-picker',params,'createdStart','on-change');
                    }
                },
                {
                    title: '实体页数',
                    key: 'realPageNums',
                    width:90,
                    render: function (h,params) {
                        return app.buildCell(h,'input-number',params,'realPageNums','on-change');
                    }
                },
                {
                    title: '上传页数',
                    key: 'uploadPageNums',
                    width:90,
                    render: function (h,params) {
                        return app.buildCell(h,'input-number',params,'uploadPageNums','on-input-change');
                    }
                },
                {
                    title: '审核信息',
                    key: 'checkOpinion',
                    width:90,
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'checkOpinion','on-input-change');
                    }
                },
                {
                    title: '业务指导备注',
                    key: 'instructorRemark',
                    width:100,
                    render: function (h,params) {
                        return app.buildCell(h,'i-input',params,'instructorRemark','on-input-change');
                    }
                },
                {
                    title: '状态',
                    key: 'status',
                    width:50,
                    render: function (h,params) {
                        if(params.row.proEngConfMateEntity.isdirectory)
                            return null;
                        else{
                            var s=app.getStatusColor(params.row.status);
                            s.marginLeft="10px"
                            return h("Tooltip",{props:{
                                content:app.getStatus(params.row.status),placement:'top'
                            }},[h('span',{
                                class:'statusColor',
                                style:s
                            })]);
                        }
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
                    })(),
                    render:function (h, params) {
                        if(params.row.proEngConfMateEntity.isdirectory)
                            return null;
                        else {
                            if (organEntity.orgType == "档案管理单位") {
                                return h('div', {class: "list-row-button-wrap"}, [
                                    h('div', {
                                        class:app.pdfIco(params.row.pdfCreateFlag),
                                        attrs: {
                                            title: "PDF原文"
                                        },
                                        on: {
                                            click: function () {
                                                app.viewPDFFileItem(params.row);
                                            }
                                        }
                                    }),
                                    h('div', {
                                        class: "list-row-button list-row-button-check-pass",
                                        attrs: {
                                            title: "此文件审核通过"
                                        },
                                        on: {
                                            click: function () {
                                                app.singleCatalogCheck(2, params.row);
                                            }
                                        }
                                    }),
                                    h('div', {
                                        class: "list-row-button list-row-button-check-nopass",
                                        attrs: {
                                            title: "此文件审核不通过"
                                        },
                                        on: {
                                            click: function () {
                                                app.singleCatalogCheck(3, params.row);
                                            }
                                        }
                                    })
                                ]);
                            }
                            else {
                                return h('div', {
                                    class:app.pdfIco(params.row.pdfCreateFlag),
                                    attrs: {
                                        title: "PDF原文",
                                        id:params.row.sid
                                    },
                                    on: {
                                        click: function () {
                                            app.viewPDFFileItem(params.row);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:1000,
            page_num:1,
            loading:false,
            organEntity:organEntity,
            engBaseEntity:engBaseEntity,
            catalogDirEntities:catalogDirEntities,
            updateItems:[],
            search_condition:{
                engSid:{
                    value:${sid},
                    type:SearchUtility.SearchFieldType.IntType
                },
                unitType:{
                    value:this.organEntity.orgType=="档案管理单位"?"":this.organEntity.orgType,
                    type:SearchUtility.SearchFieldType.StringType
                },
                engType:{
                    value:this.engBaseEntity.engType,
                    type:SearchUtility.SearchFieldType.IntType
                },
                fileNo:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                fileTitle:{
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
            listHeight:function () {
                return ProjectUtil.CommMethod.GetWidthHeight(window).height-150;
            }
        },
        methods:{
            pdfIco:function (isBuild) {
                return "list-row-button list-row-button-pdf"+(isBuild==null?0:isBuild)
            },
            getStatus:function (i) {
                var s=['未登记','已登记','预验通过','未通过','再次提交'];
                return s[i];
            },
            getStatusColor:function (i) {
                var c=['lightgray','greenyellow','green','red','blueviolet'];
                return {backgroundColor:c[i]};
            },
            buildCell:function (h,el,params,field,handle) {
                var t=this.organEntity.orgType;
                var ct=params.row.proEngConfMateEntity.unitType
                var isEdit=false;

                if(field=="uploadPageNums")
                {
                    isEdit=false;
                }
                else if(t=="档案管理单位")
                {
                    isEdit='instructorRemark,checkOpinion,'.indexOf(field+",")>=0
                }
                else if(params.row.proEngConfMateEntity.isdirectory)
                {
                    isEdit=false;
                }
                else
                {
                    if('instructorRemark,checkOpinion,'.indexOf(field+",")<0)
                        isEdit=ct.indexOf(t)>=0;
                }

                if(handle==null)
                {
                    handle="on-change";
                }

                var fVal=params.row[field];
                if(el.toLowerCase()=='date-picker')
                {
                    if(fVal!=null)
                        fVal=DateUtility.Format(new Date(fVal),"yyyy-MM-dd");
                    else
                        fVal="";
                }

                if(isEdit) {
                    //app.$emit('on-change',event,params);
                    return h(el, {
                        attrs: {
                            title: fVal
                        },
                        props: {
                            value: fVal
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
                    return h('span', fVal);
                }
            },
            rowClassName:function (row,index) {
                if(row.proEngConfMateEntity.isdirectory)
                {
                    return 'tr-disabled';
                }
                return '';
            },
            unitTypePriv:function (type) {
                var t=this.organEntity.orgType;
                if(t=="建设单位")
                    return true;
                else if(type==t)
                    return true;
                else
                    return false;
            },
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            viewEngItem:function(){
                var title="工程信息";
                var op="view";
                if(this.engBaseEntity.engOrgAndCode.supervisionOrgSid==null || this.engBaseEntity.engOrgAndCode.constructOrgSid==null)
                {
                    op="onlysave";
                    title="工程信息补全";
                }
                var url=BaseUtility.BuildUrl("/project/engineering/engineeringdetail.do?op="+op+"&sid="+this.engBaseEntity.sid+"&engType="+this.engBaseEntity.engType);
                var urlOptions=ProjectUtil.CommMethod.GetWidthHeight(window.parent);
                var option=$.extend(true,urlOptions,{title:title,height:768,show:true,modal:true});
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url, option);
            },
            uploadItem:function () {
                if(!this.current_selected_row.proEngConfMateEntity.isdirectory)
                {
                    var id=this.current_selected_row.sid;
                    var t=this.organEntity.orgType;
                    var ct=this.current_selected_row.proEngConfMateEntity.unitType;
                    var view="";
                    if(t!=ct)
                        view="&op=view";
                    var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/engineeringuploadfile.do?sid="+id+view);
                    var title=this.engBaseEntity.engName+" ~ "+this.current_selected_row.fileTitle;
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:title+"【文件上传】",width:1024,height:768});
                }
                else
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"此项为目录, 无法文件上传！");
                }
            },
            editFileItem:function(){
                if(!this.current_selected_row.proEngConfMateEntity.isdirectory)
                {
                    var id=this.current_selected_row.sid;
                    var t=this.organEntity.orgType;
                    var ct=this.current_selected_row.proEngConfMateEntity.unitType;
                    var title=this.engBaseEntity.engName+" ~ "+this.current_selected_row.fileTitle;
                    var view="";
                    if(t!=ct)
                        view="&op=view";
                    var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/catalogdetail.do?sid="+id+view);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:title+"【著录信息】",width:1024,height:600});
                }
                else
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"此项为目录, 无法著录！");
                }
            },
            reloadData:function(){
                this.engBaseEntity=getEngData();
             },
            reloadData2:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/catalogfile/getcataloglistdata.do';
                var senddata={
                    engSid:_self.engBaseEntity.sid,
                    engType:_self.engBaseEntity.engType,
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
            addItem:function () {
                var row=this.current_selected_row;
                var t=this.organEntity.orgType;
                var ct=this.current_selected_row.proEngConfMateEntity.unitType
                var isEdit=false;
                var _self=this;

                if(row==null)
                {
                    isEdit=false;
                }
                else if(row.proEngConfMateEntity.isdirectory)
                {
                    isEdit=false;
                }
                else if(ct.indexOf(t)<0)
                {
                    isEdit=false;
                }
                else
                {
                    isEdit=true;
                }

                if(isEdit)
                {
                    var sendData = JSON.stringify(row);
                    var url = '/project/engineering/catalogfile/copyengcatalogdata.do';
                    AjaxUtility.PostRequestBody(url, sendData, function (result) {
                        var msg=result.message;
                        if(result.success)
                            msg="新增成功！";
                        DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},msg);
                        _self.reloadData2();
                    }, "json");
                }
                else
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"请选择一个可编辑的目录进行新增！");
                }
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
                        if(isAlert){
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
            singleCatalogCheck:function(status,row){
                var _self=this;
                row.status=status;
                var sendData = JSON.stringify(row);
                var url = '/project/engineering/catalogfile/savesingleengcatalogdata.do';
                AjaxUtility.PostRequestBody(url, sendData, function (result) {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                    });
                }, "json");
            },
            handleFlowSubmit: function (currStatus,nextStatus,needOpinion,saveForm) {
                var _self=this;
                if(this.engBaseEntity.engOrgAndCode.supervisionOrgSid==null || this.engBaseEntity.engOrgAndCode.constructOrgSid==null)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogId04, {},"请点击按钮【工程信息】，补全工程信息后，再操作此按钮！");
                    return false;
                }
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
            BuildPDFFileItem:function () {
                var _self=this;
                var url='/project/engineering/catalogfile/bulidpdfbyarch.do';
                var senddata={
                    engSid:_self.engBaseEntity.sid,
                    fileSid:_self.current_selected_row.sid
                }
                AjaxUtility.Get(url, senddata , function (result) {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                        if(result.success)
                        {debugger;
                            _self.current_selected_row.pdfCreateFlag=1;
                            _self.reloadData2();
                        }
                    });
                },"json");
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData2();
            },
            search:function () {
                this.page_num=1;
                this.reloadData2();
            },
            handleClose: function (name) {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    });
</script>
</body>
</html>
