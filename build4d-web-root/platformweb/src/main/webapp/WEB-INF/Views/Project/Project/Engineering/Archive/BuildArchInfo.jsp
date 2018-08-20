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
        .ivu-transfer-list-body-with-search{
            padding: 5px 10px;
        }
        .ivu-form-item{
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div id="app">
    <div class="list-simple-search-wrap" style="width: 98%; margin:10px 10px;   ">
        <table class="ls-table">
            <colgroup>
                <col style="width: 80px">
                <col style="width: 45%">
                <col style="width: 80px">
                <col style="width: 45%">
            </colgroup>
            <tr class="ls-table-row">
                <td>工程名称：</td>
                <td style="text-align: left;">{{engBaseEntity.engName}}<span v-if="engBaseEntity.engNo!=null">【{{engBaseEntity.engNo}}】</span></td>
                <td>工程地址：</td>
                <td style="text-align: left;">{{engBaseEntity.engZone}} {{engBaseEntity.engLocation}}</td>
            </tr>
        </table>
    </div>
    <div style="width: 98%; margin-left:10px; margin-bottom: 10px;">
        <div style="float: right"  id="btList">
            <i-button type="primary" ref="newItem" @click="newItem" :disabled="disabledBt"><Icon type="android-open"></Icon> 按所选节点组卷 </i-button>
            <i-button type="primary" ref="joinItem" @click="joinItem" :disabled="disabledBt"><Icon type="edit"></Icon> 加入到指定案卷 </i-button>
        </div>
        <div style="width: 188px; float:right;margin-right: 5px;">
            <row style="background-color:#dddee1;  border-top-left-radius: 5px;border-bottom-left-radius: 5px;">
                <i-col span="9" style="height:32px;line-height: 32px;">&nbsp;业务单位：</i-col>
                <i-col span="15">
                    <i-select v-model="engUnit" placeholder="请选择业务单位"
                              style="text-align: left;width:120px;" @on-change="handleChange">
                        <i-option v-if="unitTypePriv('建设单位')" :value="'建设单位|'+engBaseEntity.constructionOrgEntity.orgSid">{{engBaseEntity.constructionOrgEntity.orgName}}</i-option>
                        <i-option v-if="unitTypePriv('施工单位')" :value="'施工单位|'+engBaseEntity.engOrgAndCode.constructOrgSid">{{engBaseEntity.engOrgAndCode.constructOrgName}}</i-option>
                        <i-option v-if="unitTypePriv('监理单位')" :value="'监理单位|'+engBaseEntity.engOrgAndCode.supervisionOrgSid">{{engBaseEntity.engOrgAndCode.supervisionOrgName}}</i-option>
                    </i-select>
                </i-col>
            </row>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="ivu-transfer" style="margin-left: 10px;">
        <div class="ivu-transfer-list ivu-transfer-list-with-footer" style="width: 33%; height: 490px;">
            <div class="ivu-transfer-list-header">
                <span class="ivu-transfer-list-header-title">未组卷文件列表：{{engBaseEntity.engName}}</span>
                <span class="ivu-transfer-list-header-count">4</span>
            </div>
            <div class="ivu-transfer-list-body ivu-transfer-list-body-with-search ivu-transfer-list-body-with-footer">
                <div class="ivu-transfer-list-body-search-wrapper" v-if="false">
                    <div class="ivu-transfer-list-search">
                        <div class="ivu-input-wrapper ivu-input-wrapper-small ivu-input-type"><!---->
                            <i class="ivu-icon ivu-icon-ios-search ivu-input-icon ivu-input-icon-normal"></i> <!---->
                            <input autocomplete="off" spellcheck="false" type="text" placeholder="请输入搜索内容"
                                   class="ivu-input ivu-input-small"> <!----></div>
                    </div>
                </div>
                <ul class="ivu-transfer-list-content">
                    <Tree ref="noSelTree" :data="noSelectData" :load-data="loadNoSelectData" show-checkbox></Tree>
                </ul>
            </div>
        </div>
        <div class="ivu-transfer-list ivu-transfer-list-with-footer" style="width:33%; height: 490px;">
            <div class="ivu-transfer-list-header">
                <span class="ivu-transfer-list-header-title">案卷信息</span>
            </div>
            <div class="ivu-transfer-list-body ivu-transfer-list-body-with-search ivu-transfer-list-body-with-footer">
                <ul class="ivu-transfer-list-content">
                    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
                        <form-item label="案卷题名：" prop="archTitle">
                            <i-input v-model="formValidate.archTitle"></i-input>
                        </form-item>
                        <form-item label="编制单位：" prop="makeOrgName">
                            <i-input v-model="formValidate.makeOrgName"></i-input>
                        </form-item>
                        <form-item label="案卷厚度：" prop="archWidth">
                            <i-select v-model="formValidate.archWidth" placeholder="选择案卷厚度">
                                <i-option  v-for="item in getDict(30)" :value="item.dictValue">{{item.dictText}}</i-option>
                            </i-select>
                        </form-item>
                        <form-item label="立卷人：" prop="createdBy">
                            <i-input v-model="formValidate.createdBy"></i-input>
                        </form-item>
                        <form-item label="编制日期：" prop="createdDt">
                            <date-picker   type="date" v-model="formValidate.createdDt"></date-picker>
                        </form-item>
                        <form-item label="案卷类型：" prop="archTypeCode">
                            <i-select v-model="formValidate.archTypeCode" placeholder="选择案卷类型">
                                <i-option  v-for="item in getDict(32)" :value="item.dictValue">{{item.dictText}}</i-option>
                            </i-select>
                        </form-item>
                        <form-item label="序号：" prop="engArchNo">
                            <input-number v-model="formValidate.engArchNo"></input-number>
                        </form-item>
                        <form-item label="卷内备考：" prop="remark">
                            <i-input v-model="formValidate.remark" type="textarea" :rows="3"></i-input>
                        </form-item>
                    </i-form>
                </ul>
            </div>
        </div>
        <div class="ivu-transfer-list ivu-transfer-list-with-footer" style="width:33%; height: 490px;">
            <div class="ivu-transfer-list-header">
                <span class="ivu-transfer-list-header-title">已组卷文件列表：{{engBaseEntity.engName}}</span> <span
                    class="ivu-transfer-list-header-count">7</span>
            </div>
            <div class="ivu-transfer-list-body ivu-transfer-list-body-with-search ivu-transfer-list-body-with-footer">
                <div class="ivu-transfer-list-body-search-wrapper" v-if="false">
                    <div class="ivu-transfer-list-search">
                        <div class="ivu-input-wrapper ivu-input-wrapper-small ivu-input-type"><!----> <i
                                class="ivu-icon ivu-icon-ios-search ivu-input-icon ivu-input-icon-normal"></i> <!---->
                            <input autocomplete="off" spellcheck="false" type="text" placeholder="请输入搜索内容"
                                   class="ivu-input ivu-input-small" > <!----></div>
                    </div>
                </div>
                <ul class="ivu-transfer-list-content">
                    <Tree ref="selTree"  :data="selectedData" :load-data="loadSelectedData" show-checkbox></Tree>
                </ul>
            </div>
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
    var currUser=${currUser};
    var archNo=1;
    var op=StringUtility.QueryString("op");
    ProjectUtil.FlowMethod.RenderingActions(op,"btList",organEntity.orgType,engBaseEntity.status);
    var app=new Vue({
        el:"#app",
        mounted:function () {
            document.title=engBaseEntity.engName;
            var r=this.engUnit.split("|");;
            this.reloadData(r[0],r[1]);
            archNo=this.selectedData[this.selectedData.length-1].engArchNo;
            if(archNo==null)
                archNo=1;
            else
                archNo=parseInt(archNo)+1;
            this.formValidate.engArchNo=archNo;
        },
        data:{
            noSelectData:[],
            selectedData:[],
            organEntity:organEntity,
            engBaseEntity:engBaseEntity,
            engUnit:organEntity.orgType+"|"+organEntity.orgSid,
            disabledBt:false,
            formValidate:{
                engSid:engBaseEntity.sid,
                archTitle:engBaseEntity.engName,
                makeOrgName:organEntity.orgName,
                archWidth:"小于2CM",
                createdBy:currUser.userName,
                createdDt:DateUtility.Format(new Date(),"yyyy-MM-dd"),
                archTypeCode:"文字卷",
                engArchNo:archNo,
                remark:"",
            },
            ruleValidate:{
                archTitle: [
                    { required: true, message: '【案卷题名】不能空！', trigger: 'blur' }
                ],
                makeOrgName: [
                    { required: true, message: '【编制单位】不能空！', trigger: 'blur' }
                ],
                createdBy: [
                    { required: true, message: '【立卷人】不能空！', trigger: 'blur' }
                ],
                createdDt: [
                    { required: true, type:"date", message: '【编制日期】不能空！', trigger: 'blur' }
                ],
                engArchNo: [
                    { required: true, type:"number", message: '【序号】不能空！', trigger: 'blur' }
                ],
            },
            formValidate2:{
                opinion:""
            },
            ruleValidate2: {
                opinion: [
                    { required: true, message: '请输入【未通过原因】！',  trigger: 'blur' }
                ],
            },
            catalog_condition:{
                engSid:{
                    value:${sid},
                    type:SearchUtility.SearchFieldType.IntType
                },
                unitType:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                fileTitle:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                isSelect:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                archSid:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                matePSid:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                },
                isdirectory:{
                    value:'',
                    type:SearchUtility.SearchFieldType.IntType
                }
            },
            archive_condition:{
                engSid:{
                    value:${sid},
                    type:SearchUtility.SearchFieldType.IntType
                },
                organSid:{
                    value:organEntity.orgSid,
                    type:SearchUtility.SearchFieldType.StringType
                },
                archTitle:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                }
            }
        },
        computed:{
           /* noSelectData: function () {
                return this.formatCataNodeData(this.getCatalogFileData(0,null,0),0);
            },
            selectedData: function () {
                return this.formatArchNodeData(this.getArchData());
            }*/
        },
        methods:{
            getDict:function (dictId) {
                return SelectComponentUtil.GetDictionary(dictId);
            },
            handleChange:function(v){
                var r=v.split("|");
                this.reloadData(r[0],r[1]);
                var selUnit=organEntity.orgType+"|"+organEntity.orgSid;
                if(this.engUnit!=selUnit)
                    this.disabledBt=true;
                else
                    this.disabledBt=false;
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
            formatCataNodeData:function(treeData)
            {
                treeData=treeData==null?[]:treeData;
                $.each(treeData,function (k,v) {
                    v.title=v.fileNo+"|"+v.fileTitle;
                    v.children=[];
                    v.loading=false;
                });
                return treeData;
            },
            formatArchNodeData:function(treeData)
            {
                treeData=treeData==null?[]:treeData;
                if($.isArray(treeData)) {
                    $.each(treeData, function (k, v) {
                        v.title = "【案卷】" + v.archTitle;
                        v.children = [];
                        v.loading = false;
                    });
                }
                else
                {
                    treeData.title = "【案卷】" + treeData.archTitle;
                    treeData.children = [];
                    treeData.loading = false;
                }
                return treeData;
            },
            newItem:function(){
                var _self=this;
                var selNodeData=this.$refs.noSelTree.getCheckedNodes();
                var selArchData=this.formValidate;
                if(selNodeData.length==0)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, "请在左侧选择待组卷的文件！");
                }
                else
                {
                    this.$refs.formValidate.validate(function (valid) {
                            if (valid) {
                                _self.saveData(selNodeData,selArchData);
                            }
                    });
                }
            },
            joinItem:function () {
                var selNodeData=this.$refs.noSelTree.getCheckedNodes();
                var selArchData=this.$refs.selTree.getCheckedNodes();
                for(var i=selArchData.length-1;i>=0;i--){
                    if(!selArchData[i].archTitle)
                        selArchData.splice(i,1);
                }
               /* $.each(selArchData,function (k,v) {
                   if(v && !v.archTitle)
                       selArchData.splice(k,1);
                });*/
                if(selNodeData.length==0)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, "请在左侧选择待组卷的文件！");
                    return false;
                }
                else if(selArchData.length==0)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, "请在右侧选择待加入的案卷！");
                    return false;
                }
                else if(selArchData.length>1)
                {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, "不能同时加入到多个案卷！");
                    return false;
                }
                this.saveData(selNodeData,selArchData[0]);
            },
            moveNode:function (selNodes,targetNode) {
                var _self=this;
                //移除已选中文件
                $.each(selNodes,function (k,v) {
                    if(v==undefined)
                        return ;
                    _self.removeJson(_self.noSelectData,v,"sid");
                });
                //案卷不存在则添加到案卷树
                var existArch=false;
                $.each(_self.selectedData,function (k,v) {
                   if(v.sid==targetNode.sid)
                       existArch=true;
                });
                if(!existArch)
                {
                    _self.selectedData.push(this.formatArchNodeData(targetNode));
                }
                //将已选文件添加到案卷
                _self.addJson(_self.selectedData,targetNode,"sid",selNodes);
            },
            addJson:function (arr,arrItem,Itemkey,newItems) {
                var _self=this;
                $.each(arr,function (k,v) {
                    if(v==undefined)
                        return ;
                    if(v[Itemkey]==arrItem[Itemkey])
                        arr[k].children=arr[k].children.concat(_self.formatCataNodeData(newItems));
                    else if(v.children.length>0)
                        _self.addJson(v.children,arrItem,Itemkey);
                });
            },
            removeJson:function (arr,arrItem,Itemkey) {
                var _self=this;
                $.each(arr,function (k,v) {
                    if(v==undefined)
                        return ;
                    if(v[Itemkey]==arrItem[Itemkey])
                        arr.splice(k,1);
                     else if(v.children.length>0)
                        _self.removeJson(v.children,arrItem,Itemkey);
                });
            },
            saveData:function (selNodeData,selArchData) {
                var _self=this;
                var sendData =  {fileInfoEntityList:selNodeData,archInfoEntity:selArchData};
                sendData = JSON.stringify(sendData);
                var url = '/project/engineering/archive/savearchivedata.do';
                AjaxUtility.PostRequestBody(url,sendData, function (result) {
                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                        if(result.success){
                            _self.moveNode(result.data.fileInfoEntityList,result.data.archInfoEntity);
                            _self.formValidate.engArchNo=parseInt(_self.formValidate.engArchNo)+1;
                        }
                    });
                }, "json");
            },
            reloadData:function (unitType,organSid) {
                this.catalog_condition.unitType.value=unitType;
                this.archive_condition.organSid.value=organSid;
                this.noSelectData=this.formatCataNodeData(this.getCatalogFileData(0,null,0))
                this.selectedData=this.formatArchNodeData(this.getArchData());
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
                                            //保存本页数据
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
                                //保存本页数据
                            }
                        });
                }
            },
            loadNoSelectData:function (item, callback) {
                var d=this.formatCataNodeData(this.getCatalogFileData(0,null,item.catalogid));//未选文件
                callback(d);
            },
            loadSelectedData:function (item, callback) {//已选文件
                var d=this.formatCataNodeData(this.getCatalogFileData(1,item.sid,null,1));
                callback(d);
            },
            getCatalogFileData:function (isSelect, archSid, matePSid,isArch) {
                var _self=this;
                this.catalog_condition.isSelect.value=isSelect;
                this.catalog_condition.archSid.value=archSid;
                this.catalog_condition.matePSid.value=matePSid;
                if(isArch)
                    this.catalog_condition.isdirectory.value=0;
                else
                    this.catalog_condition.isdirectory.value='';
                _self.loading=true;
                var url='/project/engineering/catalogfile/getcataloglistdata2.do';
                var senddata={
                    search_condition:JSON.stringify(_self.catalog_condition)
                }
                var r=null;
                AjaxUtility.PostSync(url, senddata , function (result) {
                    if (result.success) {
                        r= result.data;
                    }
                },"json");
                return r;
            },
            getArchData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/archive/getarchivelistdata.do';
                var senddata={
                    search_condition:JSON.stringify(_self.archive_condition)
                }
                var r=null;
                AjaxUtility.PostSync(url, senddata , function (result) {
                    if (result.success) {
                        r= result.data;
                    }
                },"json");
                return r;
            },
            handleClose: function (name) {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    });
</script>
</body>
</html>
