<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/7/3
  Time: 15:50
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
    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <form-item label="咨询内容：" prop="advContent">
            <i-input v-model="formValidate.advContent" type="textarea" :autosize="{minRows: 15,maxRows: 15}"></i-input>
        </form-item>
        <form-item prop="outerId" label="绑定工程：">
            <i-select v-model="formValidate.outerId">
                <i-option :key="item.SID" :value="item.SID" v-for="item in aboutEnts">{{item.ENG_NAME}}</i-option>
            </i-select>
        </form-item>
        <form-item label="创建时间：">
            <row>
                <i-col span="10">
                    <form-item>
                        <date-picker type="date" placeholder="选择创建时间" v-model="formValidate.createtime" disabled></date-picker>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">咨询人：</i-col>
                <i-col span="10">
                    <form-item prop="userName">
                        <i-input v-model="formValidate.userName" disabled></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>

        <form-item class="general-edit-page-bottom-wrap">
            <i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保  存 </i-button>
            <i-button type="ghost" @click="handleReset('formValidate')" style="margin-left: 8px"> 关  闭 </i-button>
        </form-item>
    </i-form>
</div>
<script>
    var Main = {
        data:function () {
            return {
                formValidate: {
                    advSid: '',
                    advContent: '${entity.advContent}',
                    userName:'${userName}',
                    createtime:'${entity.createtime}'==''?B4D.DateUtility.GetCurrentDataString("-"):new Date('${entity.createtime}'),
                    outerId:null
                },
                ruleValidate: {
                    advContent: [
                        { required: true, message: '【咨询内容】不能空！', trigger: 'blur' }
                    ]/*,
                    outerId:[
                        { required: true, message: '【绑定工程】不能空！', trigger: 'blur' }
                    ]*/
                },
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:'${orgData}',
                aboutEnts:${aboutEnts}
            };
        },
        methods: {
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        if(_self.formValidate.outerId==null||_self.formValidate.outerId==""){
                            alert("请选择关联工程！");
                            return;
                        }
                        var sendData=JSON.stringify(_self.formValidate);
                        var url='/project/advisory/saveadvisory.do';
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
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    }
    var Component = Vue.extend(Main)
    new Component().$mount('#app')
</script>
</body>
</html>