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
				<i-col span="8">
					<form-item label="文件编号：" prop="fileNo">
						<i-input v-model="formValidate.fileNo" disabled></i-input>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="责任者：" prop="responsibility">
						<i-input v-model="formValidate.responsibility" ></i-input>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="文（图）号：" prop="fileImageNo">
						<i-input v-model="formValidate.fileImageNo" ></i-input>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="24">
					<form-item label="文件题名：" prop="fileTitle">
						<i-input v-model="formValidate.fileTitle" ></i-input>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="8">
					<form-item label="实体页数：" prop="realPageNums">
						<input-number v-model="formValidate.realPageNums"></input-number>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="上传页数：" prop="uploadPageNums">
						<input-number v-model="formValidate.uploadPageNums" disabled></input-number>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="载体类型：" prop="mediaTypeCode">
						<i-select v-model="formValidate.mediaTypeCode" placeholder="选择载体类型">
							<i-option  v-for="item in getDict(21)" :value="item.dictValue">{{item.dictText}}</i-option>
						</i-select>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="8">
					<form-item label="规格：" prop="specCode">
						<i-input v-model="formValidate.specCode" ></i-input>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="文字(页数)：" prop="textNums">
						<input-number v-model="formValidate.textNums" ></input-number>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="图纸(张数)：" prop="drawingNums">
						<input-number v-model="formValidate.drawingNums" ></input-number>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="8">
					<form-item label="照片(张数)：" prop="phoneNums">
						<input-number v-model="formValidate.phoneNums" ></input-number>
					</form-item>
				</i-col>

				<i-col span="8">
					<form-item label="底片(张数)：" prop="negativeNums">
						<input-number v-model="formValidate.negativeNums" ></input-number>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="底图(张数)：" prop="baseMapNums">
						<input-number v-model="formValidate.baseMapNums" ></input-number>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="8">
					<form-item label="文本稿本代码：" prop="manuscriptCode">
						<i-select v-model="formValidate.manuscriptCode" placeholder="选择文本稿本代码">
							<i-option  v-for="item in getDict(27)" :value="item.dictValue">{{item.dictText}}</i-option>
						</i-select>
					</form-item>
				</i-col>

				<i-col span="8">
					<form-item label="形成开始时间：" prop="createdStart">
						<date-picker v-model="formValidate.createdStart" ></date-picker>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="形成结束时间：" prop="createdEnd">
						<date-picker v-model="formValidate.createdEnd"></date-picker>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="24">
					<form-item label="附注：" prop="remark">
						<i-input v-model="formValidate.remark"></i-input>
					</form-item>
				</i-col>
			</row>
			<row>
				<i-col span="8">
					<form-item label="文件类型代码：" prop="fileTypeCode">
						<i-select v-model="formValidate.fileTypeCode" placeholder="选择文件类型代码">
							<i-option  v-for="item in getDict(28)" :value="item.dictValue">{{item.dictText}}</i-option>
						</i-select>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="文件格式：" prop="fileType">
						<i-input v-model="formValidate.fileType" ></i-input>
					</form-item>
				</i-col>
				<i-col span="8">
					<form-item label="文件大小：" prop="fileSize">
						<input-number v-model="formValidate.fileSize" ></input-number>
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
        var Main = {
            data:function () {
                return {
                    formValidate: ${fileInfoEntity},
                    ruleValidate: {
                        fileImageNo: [
                            { required: true, message: '【文(图)号】不能空！', trigger: 'blur' }
                        ],
						responsibility: [
                            { required: true, message: '【责任者】不能空！', trigger: 'blur' }
                        ],
                        fileTitle: [
                            { required: true, message: '【文件题名】不能空！', trigger: 'change' },
                            { validator:this.validateOrganCode,trigger:"blur"}
                        ],
                        realPageNums: [
                            { required: true, type:'number', message: '【实体页数】不能空！', trigger: 'change' }
                        ]
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
                            debugger;
                            var url='/project/engineering/catalogfile/savecatalogdetail.do';
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
