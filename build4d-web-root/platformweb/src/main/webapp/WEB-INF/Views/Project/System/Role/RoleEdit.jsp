<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
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
</head>
<body>
<div id="app" class="general-edit-page-wrap">
	<i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
		<form-item label="角色名称：" prop="roleName">
			<i-input v-model="formValidate.roleName"></i-input>
		</form-item>
		<form-item label="创建时间：">
			<row>
				<i-col span="10">
					<form-item prop="date">
						<date-picker type="date" placeholder="选择创建时间" v-model="formValidate.roleCreateTime"></date-picker>
					</form-item>
				</i-col>
				<i-col span="4" style="text-align: center">角色类型：</i-col>
				<i-col span="10">
					<form-item prop="orgType">
						<i-select v-model="formValidate.roleType">
							<i-option value="一般角色">一般角色</i-option>
						</i-select>
					</form-item>
				</i-col>
			</row>
		</form-item>
		<form-item label="状态：">
			<radio-group v-model="formValidate.roleStatus">
				<radio label="1">启动</radio>
				<radio label="2">禁用</radio>
			</radio-group>
		</form-item>
		<form-item label="备注：">
			<i-input v-model="formValidate.roleDesc" type="textarea" :autosize="{minRows: 11,maxRows: 11}"></i-input>
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
                    roleSid: '${sId}',
                    roleName: '${entity.roleName}',
                    roleDesc: '${entity.roleDesc}',
                    roleStatus: '${entity.roleStatus}'==''?'1':'${entity.roleStatus}',
                    roleType:'${entity.roleType}'==''?'一般角色':'${entity.roleType}',
                    roleCreateTime:'${entity.roleCreateTime}'==''?B4D.DateUtility.GetCurrentDataString("-"):new Date('${entity.roleCreateTime}')
                },
                ruleValidate: {
                    roleName: [
                        { required: true, message: '【角色名称】不能空！', trigger: 'blur' }
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
                        var url='/project/system/role/saveedit.do';
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