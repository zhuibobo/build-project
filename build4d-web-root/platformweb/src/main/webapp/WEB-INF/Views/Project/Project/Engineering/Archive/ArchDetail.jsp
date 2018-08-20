<%--
  Created by IntelliJ IDEA.
  User: zhuibobo,liwl
  Date: 2018/4/27
  Time: 22:13
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
    <style>
        .ivu-input-number{
            width: 100%;
        }
    </style>
</head>
<body>
<div id="app" class="general-edit-page-wrap">
    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <row>
            <i-col span="24">
                <form-item label="案卷题名：" prop="archTitle">
                    <i-input v-model="formValidate.archTitle"></i-input>
                </form-item>
            </i-col>
        </row>
        <row>
            <i-col span="12">
                <form-item label="编制单位：" prop="makeOrgName">
                    <i-input v-model="formValidate.makeOrgName"></i-input>
                </form-item>
             </i-col>
            <i-col span="12">
                <form-item label="案卷厚度：" prop="archWidth">
                    <i-select v-model="formValidate.archWidth" placeholder="选择案卷厚度">
                        <i-option  v-for="item in getDict(30)" :value="item.dictValue">{{item.dictText}}</i-option>
                    </i-select>
                </form-item>
            </i-col>
        </row>
        <row>
            <i-col span="12">
                <form-item label="立卷人：" prop="createdBy">
                    <i-input v-model="formValidate.createdBy"></i-input>
                </form-item>
            </i-col>
            <i-col span="12">
                <form-item label="编制日期：" prop="createdDt">
                    <date-picker   type="date" v-model="formValidate.createdDt"></date-picker>
                </form-item>
            </i-col>
        </row>
        <row>
            <i-col span="12">
                <form-item label="案卷类型：" prop="archTypeCode">
                    <i-select v-model="formValidate.archTypeCode" placeholder="选择案卷类型">
                        <i-option  v-for="item in getDict(32)" :value="item.dictValue">{{item.dictText}}</i-option>
                    </i-select>
                </form-item>
            </i-col>
            <i-col span="12">
                <form-item label="序号：" prop="engArchNo">
                    <input-number v-model="formValidate.engArchNo"></input-number>
                </form-item>
            </i-col>
        </row>
        <row>
            <i-col span="24">
            <form-item label="卷内备考：" prop="remark">
                <i-input v-model="formValidate.remark" type="textarea" :rows="3"></i-input>
            </form-item>
            </i-col>
        </row>
        <form-item class="general-edit-page-bottom-wrap">
            <i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保  存 </i-button>
            <i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关  闭 </i-button>
        </form-item>
    </i-form>
</div>
<script>
    var archEntity=${arolArchInfoEntity};
    archEntity.createdDt=archEntity.createdDt==null?null:DateUtility.Format(new Date(archEntity.createdDt),"yyyy-MM-dd");
    var Main = {
        data:function () {
            return {
                formValidate: archEntity,
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
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:'${orgData}'
            };
        },
        methods: {
            getDict:function (dictId) {
                return SelectComponentUtil.GetDictionary(dictId);
            },
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var sendData=JSON.stringify(_self.formValidate);
                        //debugger;
                        var url = '/project/engineering/archive/savesinglearchivedata.do';
                        AjaxUtility.PostRequestBody(url,sendData,function (result) {
                            DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},result.message,function () {
                                //debugger;
                                window.OpenerWindowObj.app.reloadData();
                                DialogUtility.Frame_CloseDialog(window);
                            });
                        },"json");
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
            },
            handleReset: function (name) {
                this.$refs[name].resetFields();
            },
            handleClose: function (name) {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    }
    var Component = Vue.extend(Main)
    new Component().$mount('#app')
</script>
</body>
</html>
