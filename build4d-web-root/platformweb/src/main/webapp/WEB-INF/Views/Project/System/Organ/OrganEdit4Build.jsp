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
</head>
<body>
	<div id="app" class="general-edit-page-wrap">
		<i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
			<form-item label="机构名称：" prop="orgName">
				<i-input v-model="formValidate.orgName" placeholder="请输入机构名称"></i-input>
			</form-item>
			<form-item label="机构编码：" prop="orgNo">
				<i-input v-model="formValidate.orgNo" placeholder="请输入机构编码"></i-input>
			</form-item>
			<form-item label="机构代码：" prop="orgCode">
				<i-input v-model="formValidate.orgCode" placeholder="请输入组织机构代码"></i-input>
			</form-item>
			<form-item label="联系人：">
				<row>
					<i-col span="10">
						<i-input v-model="formValidate.orgContacts" placeholder="请输入联系人"></i-input>
					</i-col>
					<i-col span="4" style="text-align: center">联系电话：</i-col>
					<i-col span="10">
						<i-input v-model="formValidate.orgContMobile" placeholder="请输入联系电话"></i-input>
					</i-col>
				</row>
			</form-item>
			<form-item label="创建时间：" prop="orgCreateTime">
				<row>
					<i-col span="10">
						<form-item prop="date">
							<date-picker type="date" placeholder="选择创建时间" v-model="formValidate.orgCreateTime"></date-picker>
						</form-item>
					</i-col>
					<i-col span="4" style="text-align: center">机构类别：</i-col>
					<i-col span="10">
						<form-item prop="orgType">
							<i-select v-model="formValidate.orgType" placeholder="选择机构类别">
								<i-option value="施工单位">施工单位</i-option>
								<i-option value="监理单位">监理单位</i-option>
								<%--<i-option value="签章单位">签章单位</i-option>
								<i-option value="外协单位">外协单位</i-option>--%>
							</i-select>
						</form-item>
					</i-col>
				</row>
			</form-item>
			<form-item label="状态：" prop="orgStatus">
				<radio-group v-model="formValidate.orgStatus">
					<radio label="1">启动</radio>
					<radio label="2">禁用</radio>
				</radio-group>
			</form-item>
			<form-item label="备注：" prop="desc">
				<i-input v-model="formValidate.orgDesc" type="textarea" :autosize="{minRows: 3,maxRows: 3}" placeholder="请输入备注"></i-input>
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
                    /*formValidate: {
                        orgSid: '${orgSid}',
                        orgNo: 'orgNo',
                        orgCode: 'orgCode',
                        orgName: 'orgName',
                        orgDesc: 'orgDesc',
                        orgCreateTime: '2018-05-03',
                        orgContacts:'orgContacts',
                        orgPhone: '1392874254',
                        orgContMobile: '1392874254',
                        orgStatus:'1',
                        orgType:'管理机构'
                    },*/
                    formValidate: {
                        orgSid: '${orgSid}',
                        orgNo: '${proOrgan.orgNo}',
                        orgCode: '${proOrgan.orgCode}',
                        orgName: '${proOrgan.orgName}',
                        orgDesc: '${proOrgan.orgDesc}',
                        orgCreateTime: '${proOrgan.orgCreateTime}',
                        orgContacts:'${proOrgan.orgContacts}',
                        orgPhone: '${proOrgan.orgPhone}',
                        orgContMobile: '${proOrgan.orgContMobile}',
                        orgStatus:'${proOrgan.orgStatus}',
                        orgType:'${proOrgan.orgType}'
                    },
                    ruleValidate: {
                        orgName: [
                            { required: true, message: '【机构名称】不能空！', trigger: 'blur' },
                            { validator:this.validateOrganName,trigger:"blur"}
                        ],
                        orgNo: [
                            { required: true, message: '【机构编码】不能空！', trigger: 'blur' }
                        ],
                        orgCode: [
                            { required: true, message: '【机构代码】不能空！', trigger: 'change' },
                            { validator:this.validateOrganCode,trigger:"blur"}
                        ],
                        orgStatus: [
                            { required: true, message: '【状态】不能空！', trigger: 'change' }
                        ]
                    },
                    seen:(StringUtility.QueryString("op")!="view"),
					orgData:'${orgData}'
                };
            },
            methods: {
                validateOrganName:function(rule,value,callback){
                    this.validateOrgan(value,'','【机构名称】',callback);
                },
                validateOrganCode:function(rule,value,callback){
                    this.validateOrgan('',value,'【机构代码】',callback);
                },
                validateOrgan:function(orgName,orgCode,caption,callback){
                    var orgSid=StringUtility.QueryString("orgSid");
                    var url='/project/system/organ/existOrgan.do';
                    var e=false;
                    AjaxUtility.PostSync(url,{orgSid:orgSid,orgName:orgName,orgCode:orgCode},function (result) {
                        e=result.data;
                    },"json");
                    if(e)
                        callback(new Error(caption+"已经存在！"));
                    else
                        callback();
                },
                handleSubmit: function (name) {
                    var _self=this;
                    this.$refs[name].validate(function (valid) {
                        if (valid) {
                            if(_self.formValidate.orgType==null||_self.formValidate.orgType==""){
                                alert("请选择机构类别");
                                return;
							}
                            var sendData=JSON.stringify(_self.formValidate);
                            //debugger;
                            var url='/project/system/organ/saveorganeidt.do';
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
