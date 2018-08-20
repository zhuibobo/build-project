<%--
  Created by IntelliJ IDEA.
  User: zhuibobo,liwl
  Date: 2018/4/27
  Time: 22:09
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
        .ivu-form-item{
            margin-bottom:20px;
        }
    </style>
</head>
<body>
<div id="app" class="general-edit-page-wrap">
    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <Collapse v-model="panelArr" @on-change="handleCollapse">
            <Panel name="1">
                个人信息
                <p slot="content">
                    <row>
                        <i-col span="12">
                            <form-item label="单位名称：" prop="userOrgId">
                                <i-input v-model="organName" placeholder="请输入单位名称" disabled></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="登录账号：" prop="userAccount">
                                <i-input v-model="formValidate.userAccount" placeholder="请输入登录账号" disabled></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="用户名称：" prop="userUsername">
                                <i-input v-model="formValidate.userUsername" placeholder="请输入用户名称"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="个人电话：">
                                <i-input v-model="formValidate.userPhone" placeholder="请输入个人电话"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="手机号码：">
                                 <i-input v-model="formValidate.userMobile" placeholder="请输入手机号码"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="传真号：">
                                <i-input v-model="formValidate.userFax" placeholder="请输入传真号"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="微信号：">
                                <i-input v-model="formValidate.userWxno" placeholder="请输入微信号" disabled></i-input>
                             </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="用户状态：">
                                <radio-group v-model="formValidate.userStatus">
                                    <radio label="1">有效</radio>
                                    <radio label="2">无效</radio>
                                </radio-group>
                            </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="微信绑定号状态：" :label-width=120>
                                <radio-group v-model="formValidate.userWxbinding">
                                    <radio label="未绑定" disabled>未绑定</radio>
                                    <radio label="已绑定" disabled>已绑定</radio>
                                </radio-group>
                            </form-item>
                        </i-col>
                    </row>
                    <form-item label="备注：" prop="userDesc">
                        <i-input v-model="formValidate.userDesc" type="textarea" :autosize="{minRows:2,maxRows:2}"
                                 placeholder="请输入备注"></i-input>
                    </form-item>
                </p>
            </Panel>
            <Panel name="2">
                登录密码修改
                <p slot="content" v-if="updatePwd">
                    <row>
                        <i-col span="24">
                            <form-item label="原密码：" prop="oldUserPsw">
                                <i-input type="password" v-model="formValidate.oldUserPsw" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="新密码：" prop="userPsw">
                                <i-input type="password" v-model="formValidate.userPsw" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="确认密码：" prop="confirmPwd">
                                <i-input type="password" v-model="formValidate.confirmPwd" placeholder=""
                                         id="confirmPwd"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                </p>
            </Panel>
            <form-item class="general-edit-page-bottom-wrap">
                <i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保 存</i-button>
                <i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关 闭</i-button>
            </form-item>
        </Collapse>
    </i-form>
</div>
<script>
    var Main = {
        data: function () {
            return {
                updatePwd:false,
                panelArr: [1],
                organName: '${orgData.orgName}',
                formValidate: {
                    userSid: '${proUser.userSid}',
                    userOrgId:${proUser.userOrgId},
                    userType: '${proUser.userType}',
                    userAccount: '${proUser.userAccount}',
                    userUsername: '${proUser.userUsername}',
                    userPhone: '${proUser.userPhone}',
                    userMobile: '${proUser.userMobile}',
                    userFax: '${proUser.userFax}',
                    userWxno: '${proUser.userWxno}',
                    userWxbinding: '${proUser.userWxbinding}',
                    userOrder: '${proUser.userOrder}',
                    userStatus: '${proUser.userStatus}',
                    userDesc: '${proUser.userDesc}',
                    oldUserPsw: '',
                    userPsw: null,
                    confirmPwd: ''
                },
                ruleValidate: {
                    userOrgId: [
                        {required: true, message: '【单位名称】不能空！', type: 'number', trigger: 'change'}
                    ],
                    userUsername: [
                        {required: true, message: '【用户名称】不能空！', trigger: 'blur'}
                    ],
                    oldUserPsw: [
                        {required: true, message: '请输入【原密码】！', trigger: 'blur'},
                        {validator: this.checkOldPwd, trigger: "blur"}
                    ],
                    userPsw: [
                        {required: true, message: '请输入【新密码】！', trigger: 'blur'},
                        {validator: this.checkPwd, trigger: "blur"}
                    ],
                    confirmPwd: [{required: true, message: '请输入【确认密码】！', trigger: 'blur'},
                        {validator: this.checkPwd, trigger: "blur"}
                    ]
                },
                seen: (StringUtility.QueryString("op") != "view")
            };
        },
        methods: {
            handleCollapse:function () {
               var i=$.inArray("2",this.panelArr);
               this.updatePwd=i>=0;
            },
            checkOldPwd: function (rule, value, callback) {
                var r = false;
                var _self = this;
                var url = '/project/system/user/checkaccountpwd.do';
                AjaxUtility.PostSync(url, {account: this.formValidate.userAccount, pwd: value}, function (result) {
                    r = result.data;
                }, "json");
                if (r)
                    callback();
                else
                    callback(new Error("密码错误，请重新输入！"));
            },
            checkPwd: function (rule, value, callback) {
                var pwd = "";
                if (rule.field == "userPsw")
                    pwd = this.formValidate.confirmPwd;
                else
                    pwd = this.formValidate.userPsw;

                if (pwd != null && pwd != '' && value != null && value != '' && pwd != value)
                    callback(new Error("两次输入的密码不一致！"));
                else
                    callback();
            },
            validateAccount: function (rule, value, callback) {
                var userSid = StringUtility.QueryString("userSid");
                var account = value;
                var url = '/project/system/user/exists.do';
                var e = false;
                AjaxUtility.PostSync(url, {userSid: userSid, account: account}, function (result) {
                    e = result.data;
                }, "json");
                if (e)
                    callback(new Error("账号已经存在！"));
                else
                    callback();
            },
            handleSubmit: function (name) {
                var _self = this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var url = '/project/system/user/encryptpwd.do';
                        AjaxUtility.PostSync(url, {pwd: _self.formValidate.userPsw}, function (result) {
                            _self.formValidate.userPsw = result.data;
                        }, "json");

                        var sendData = JSON.stringify(_self.formValidate);
                        url = '/project/system/user/saveusereidt.do';
                        AjaxUtility.PostRequestBody(url, sendData, function (result) {
                            DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                                _self.formValidate.oldUserPsw=null;
                                _self.formValidate.confirmPwd=null;
                                _self.formValidate.userPsw=null;
                            });
                        }, "json");
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
