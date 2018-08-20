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
</head>
<body>
<div id="app" class="general-edit-page-wrap">
	<Alert type="warning" closable show-icon>注：默认密码为1，用户可登录个人账号修改密码！</Alert>
	<i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
		<form-item label="单位名称：" prop="userOrgId">
			<i-select v-model="formValidate.userOrgId" placeholder="请选择单位名称">
				<i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
			</i-select>
		</form-item>
		<form-item label="登录账号：" prop="userAccount">
			<i-input v-model="formValidate.userAccount" placeholder="请输入登录账号"></i-input>
		</form-item>
		<form-item label="用户名称：" prop="userUsername">
			<i-input v-model="formValidate.userUsername" placeholder="请输入用户名称"></i-input>
		</form-item>
		<form-item label="个人电话：">
			<row>
				<i-col span="10">
					<i-input v-model="formValidate.userPhone" placeholder="请输入个人电话"></i-input>
				</i-col>
				<i-col span="4" style="text-align: center">手机号码：</i-col>
				<i-col span="10">
					<i-input v-model="formValidate.userMobile" placeholder="请输入联系电话"></i-input>
				</i-col>
			</row>
		</form-item>
		<form-item label="传真号：">
			<row>
				<i-col span="10">
					<i-input v-model="formValidate.userFax" placeholder="请输入传真号"></i-input>
				</i-col>
				<i-col span="4" style="text-align: center">微信号：</i-col>
				<i-col span="10">
					<i-input v-model="formValidate.userWxno" placeholder="请输入微信号"></i-input>
				</i-col>
			</row>
		</form-item>
		<form-item label="微信绑定号状态：">
			<row>
				<i-col span="10">
					<radio-group v-model="formValidate.userWxbinding">
						<radio label="未绑定">未绑定</radio>
						<radio label="已绑定">已绑定</radio>
					</radio-group>
				</i-col>
				<i-col span="4" style="text-align: center">用户状态：</i-col>
				<i-col span="10">
					<radio-group v-model="formValidate.userStatus">
						<radio label="1">有效</radio>
						<radio label="2">无效</radio>
					</radio-group>
				</i-col>
			</row>
		</form-item>
		<form-item label="备注：" prop="userDesc">
			<i-input v-model="formValidate.userDesc" type="textarea" :autosize="{minRows: 3,maxRows: 3}" placeholder="请输入备注"></i-input>
		</form-item>
		<form-item class="general-edit-page-bottom-wrap">
			<i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保  存 </i-button>
			<i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关  闭 </i-button>
		</form-item>
	</i-form>
</div>
<script>
    var Main = {
        data:function () {
            return {
                formValidate: {
                    userSid :'${userSid}',
                    userOrgId :${proUser.userOrgId},
					userType :'${proUser.userType}',
					userAccount :'${proUser.userAccount}',
                    userUsername :'${proUser.userUsername}',
					userPhone :'${proUser.userPhone}',
					userMobile :'${proUser.userMobile}',
					userFax :'${proUser.userFax}',
					userWxno :'${proUser.userWxno}',
					userWxbinding :'${proUser.userWxbinding}',
					userOrder :'${proUser.userOrder}',
					userStatus :'${proUser.userStatus}',
					userDesc :'${proUser.userDesc}'
                },
                ruleValidate: {
                    userOrgId: [
                        { required: true, message: '【单位名称】不能空！',type: 'number', trigger: 'change' }
                    ],
                    userAccount: [
                        { required: true, message: '【登录账号】不能空！', trigger: 'blur' },
						{validator:this.validateAccount,trigger:"blur"}
                    ],
                    userUsername: [
                        { required: true, message: '【用户名称】不能空！', trigger: 'blur' }
                    ]
                },
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:${orgData}
            };
        },
        methods: {
            validateAccount:function(rule,value,callback){
					var userSid=StringUtility.QueryString("userSid");
					var account=value;
					var url='/project/system/user/exists.do';
					var e=false;
					AjaxUtility.PostSync(url,{userSid:userSid,account:account},function (result) {
						e=result.data;
					},"json");
					if(e)
						callback(new Error("账号已经存在！"));
					else
						callback();
    		},
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var sendData=JSON.stringify(_self.formValidate);
                        debugger;
                        var url='/project/system/user/saveusereidt.do';
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
