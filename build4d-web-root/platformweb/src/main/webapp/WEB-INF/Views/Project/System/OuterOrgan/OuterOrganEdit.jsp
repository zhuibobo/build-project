<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/26
  Time: 15:04
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
    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="140">
        <form-item label="公司名称(中文)：" prop="oorgNameCn">
            <row>
                <i-col span="10">
                    <form-item >
                        <i-input v-model="formValidate.oorgNameCn"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">公司名称(英文)：</i-col>
                <i-col span="10">
                    <form-item prop="oorgNameEn">
                        <i-input v-model="formValidate.oorgNameEn"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="组织机构代码：">
            <row>
                <i-col span="10">
                    <form-item prop="oorgCode">
                        <i-input v-model="formValidate.oorgCode"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">单位地址：</i-col>
                <i-col span="10">
                    <form-item prop="oorgAddr">
                        <i-input v-model="formValidate.oorgAddr"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="所属区域：">
            <row>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgArea"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">法人代表：</i-col>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgLegalName"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">身份证号：</i-col>
                <i-col span="6">
                    <form-item>
                        <i-input v-model="formValidate.oorgLegalIdcard"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="联系人：">
            <row>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgContacts"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">手机：</i-col>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgContMobile"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">电子邮件：</i-col>
                <i-col span="6">
                    <form-item>
                        <i-input v-model="formValidate.oorgEmail"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="电话：">
            <row>
                <i-col span="5">
                    <form-item >
                        <i-input v-model="formValidate.oorgContMobile"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">传真：</i-col>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgFax"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">邮政编码：</i-col>
                <i-col span="6">
                    <form-item>
                        <i-input v-model="formValidate.oorgPostalCode"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="登录帐号：">
            <row>
                <i-col span="5">
                    <form-item prop="oorgAccount">
                        <i-input v-model="formValidate.oorgAccount"></i-input>
                    </form-item>
                </i-col>
                <i-col span="4" style="text-align: center">密码：</i-col>
                <i-col span="5">
                    <form-item>
                        <i-input v-model="formValidate.oorgIntroduction"></i-input>
                    </form-item>
                </i-col>
            </row>
        </form-item>
        <form-item label="公司简介：">
            <i-input v-model="formValidate.roleDesc" type="textarea" :autosize="{minRows: 4,maxRows: 11}"></i-input>
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
                    oorgSid:"${sId}",
                    oorgNameCn:"${entity.oorgNameCn}",
                    oorgNameEn:"${entity.oorgNameEn}",
                    oorgType:"${oorg_type}",
                    oorgCode:"${entity.oorgCode}",
                    oorgArea:"${entity.oorgArea}",
                    oorgPostalCode:"${entity.oorgPostalCode}",
                    oorgAddr:"${entity.oorgAddr}",
                    oorgLegalName:"${entity.oorgLegalName}",
                    oorgLegalIdcard:"${entity.oorgLegalIdcard}",
                    oorgContacts:"${entity.oorgContacts}",
                    oorgContMobile:"${entity.oorgContMobile}",
                    oorgPhone:"${entity.oorgPhone}",
                    oorgEmail:"${entity.oorgEmail}",
                    oorgFax:"${entity.oorgFax}",
                    oorgIntroduction:"${entity.oorgIntroduction}",
                    oorgAccount:"${entity.oorgAccount}",
                    oorgPsw:"${entity.oorgPsw}"
                },
                ruleValidate: {
                    oorgNameCn: [
                        { required: true, message: '【公司名称(中文)】不能空！', trigger: 'blur' }
                    ],
                    oorgCode: [
                        { required: true, message: '【组织机构代码】不能空！', trigger: 'blur' }
                    ]
                },
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:'${orgData}'
            };
        },
        methods: {
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var sendData=JSON.stringify(_self.formValidate);
                        var url='/project/system/outerorgan/saveedit.do';
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
