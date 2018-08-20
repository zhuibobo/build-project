<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/5/9
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
        <form-item label="项目名称：" prop="projName">
            <i-input v-model="formValidate.projName" placeholder="请输入项目名称"></i-input>
        </form-item>
        <row>
            <i-col span="12">
                <form-item label="项目编号："   prop="projId">
                    <i-input v-model="formValidate.projId" placeholder="请输入项目编号"></i-input>
                </form-item>
            </i-col>
            <i-col span="12">
                <form-item label="单位名称："  prop="conOrgSid">
                    <i-select disabled v-model="formValidate.conOrgSid" placeholder="请选择单位名称"  @on-change="handleChange(formValidate.conOrgSid,'orgSid',formValidate,['orgNoCode'],['orgCode'],orgData)">
                        <i-option  v-for="item in orgData" :value="item.orgSid"   :key="item.orgSid">{{item.orgName}}</i-option>
                    </i-select>
                </form-item>
            </i-col>
        </row>
        <form-item label="项目地址：" >
            <i-input v-model="formValidate.projLocation" placeholder="请输入项目地址"></i-input>
        </form-item>
        <form-item label="总用地面积：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.totalLandArea" placeholder="请输入总用地面积"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">总建筑面积：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.totalBuildingArea" placeholder="请输入总建筑面积"></input-number>
                </i-col>
            </row>
        </form-item>
        <form-item label="幢数：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.buildingNums" placeholder="请输入幢数"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">容积率：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.plotRatio" placeholder="请输入容积率"></input-number>
                </i-col>
            </row>
        </form-item>
        <form-item label="绿化率：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.greenCoverage" placeholder="请输入绿化率"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">建筑密度：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.buildingDensity" placeholder="请输入建筑密度"></input-number>
                </i-col>
            </row>
        </form-item>
        <form-item label="地上停车位：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.upParkingNums" placeholder="请输入地上停车位"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">地下停车位：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.downParkingNums" placeholder="请输入地下停车位"></input-number>
                </i-col>
            </row>
        </form-item>
        <form-item label="总工程预算：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.totalConsBudget" placeholder="请输入总工程预算"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">总工程结算：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.totalConsSettlement" placeholder="请输入总工程结算"></input-number>
                </i-col>
            </row>
        </form-item>
        <form-item label="总地价：">
            <row>
                <i-col span="10">
                    <input-number v-model="formValidate.totalLandPrice" placeholder="请输入总地价"></input-number>
                </i-col>
                <i-col span="4" style="text-align: center">版本号：</i-col>
                <i-col span="10">
                    <input-number v-model="formValidate.version" placeholder="请输入版本号"></input-number>
                </i-col>
            </row>
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
                formValidate: ${dataEnties},
                ruleValidate: {
                    projName: [
                        { required: true, message: '【项目名称】不能为空！', trigger: 'blur' }
                    ],
                    projId: [
                        { required: true, message: '【项目编号】不能为空！', trigger: 'blur' }
                    ],
                    conOrgSid: [
                        { required: true, type:'number', message: '【单位名称】不能为空！', trigger: 'change' }
                    ]
                },
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:${orgData}
            };
        },
        methods: {
            handleChange:function (currVal,currBindSourceCol,currBindData,updateCols,sourceCols,datasource) {
                var _self=this;
                $.each(datasource,function (k,v) {
                    if(v[currBindSourceCol]==currVal)
                    {
                        $.each(updateCols,function (k2,v2) {
                            currBindData[v2]=v[sourceCols[k2]]
                        })
                        return;
                    }
                })
            },
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var sendData=JSON.stringify(_self.formValidate);
                        debugger;
                        var url='/project/project/saveprojectdetail.do';
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
