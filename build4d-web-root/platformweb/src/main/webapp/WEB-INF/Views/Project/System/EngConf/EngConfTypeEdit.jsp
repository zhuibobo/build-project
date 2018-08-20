<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/15
  Time: 20:50
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
    <div id="app" class="general-edit-page-wrap">
        <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="120">
            <form-item label="工程类别编码：" prop="engValue">
                <i-input v-model="formValidate.engValue"></i-input>
            </form-item>
            <form-item label="工程类别名称：" prop="engName">
                <i-input v-model="formValidate.engName"></i-input>
            </form-item>
            <form-item label="创建时间：">
                <row>
                    <i-col span="10">
                        <form-item>
                            <date-picker type="date" placeholder="选择创建时间" v-model="formValidate.engCreateTime"></date-picker>
                        </form-item>
                    </i-col>
                    <i-col span="4" style="text-align: center">状态：</i-col>
                    <i-col span="10">
                        <form-item>
                            <radio-group v-model="formValidate.engStatus">
                                <radio label="1">启动</radio>
                                <radio label="0">禁用</radio>
                            </radio-group>
                        </form-item>
                    </i-col>
                </row>
            </form-item>
            <form-item label="备注：">
                <i-input v-model="formValidate.engDesc" type="textarea" :autosize="{minRows: 11,maxRows: 11}"></i-input>
            </form-item>
            <form-item class="general-edit-page-bottom-wrap">
                <i-button type="primary" v-if="status!='view'" @click="handleSubmit('formValidate')"> 保  存 </i-button>
                <i-button type="ghost" v-if="status!='view'" @click="handleReset('formValidate')" style="margin-left: 8px"> 关  闭 </i-button>
            </form-item>
        </i-form>
    </div>
    <script>
        var Main = {
            data:function () {
                return {
                    formValidate: {
                        engSid: '${sId}',
                        engValue: '${entity.engValue}',
                        engName: '${entity.engName}',
                        engDesc: '${entity.engDesc}',
                        engStatus: '${entity.engStatus}'==''?'1':'${entity.engStatus}',
                        engCreateTime:'${entity.engCreateTime}'==''?B4D.DateUtility.GetCurrentDataString("-"):new Date('${entity.engCreateTime}')
                    },
                    ruleValidate: {
                        engValue: [
                            { required: true, message: '【工程类别编码】不能空！', trigger: 'blur' }
                        ],
                        engName: [
                            { required: true, message: '【工程类别名称】不能空！', trigger: 'blur' }
                        ]
                    },
                    status:'${op}'
                };
            },
            methods: {
                handleSubmit: function (name) {
                    var _self=this;
                    this.$refs[name].validate(function (valid) {
                        if (valid) {
                            var sendData=JSON.stringify(_self.formValidate);
                            var url='/project/system/engconf/saveedit.do';
                            AjaxUtility.PostRequestBody(url,sendData,function (result) {
                                DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},result.message,function () {
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
                }
            }
        }
        var Component = Vue.extend(Main)
        new Component().$mount('#app')
    </script>
</body>
</html>
