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
</head>
<body>
<div id="app" class="general-edit-page-wrap" v-cloak>
    <tabs type="card">
        <tab-pane label="基础信息" icon="social-apple">
            <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
                <Collapse v-model="panelArr">
                    <Panel name="1">
                        工程所属项目2
                        <p slot="content">
                            <row>
                                <i-col span="16">
                                    <form-item label="所属项目：" prop="projSid">
                                        <i-select number type="number" v-model.number="formValidate.projSid" placeholder="请选择所属项目">
                                            <i-option  v-for="item in proBaseInfo" :value="item.sid" :key="item.sid" >{{item.projName}}</i-option>
                                        </i-select>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="工程类别："   prop="engType">
                                        <i-select v-model="formValidate.engType" placeholder="请选择所属工程类别" disabled>
                                            <i-option value="1">房建工程类</i-option>
                                            <i-option value="2">道路工程类</i-option>
                                            <i-option value="3">桥梁工程类</i-option>
                                        </i-select>
                                    </form-item>
                                </i-col>
                            </row>
                        </p>
                    </Panel>
                    <Panel name="2">
                        工程信息
                        <p slot="content">
                            <row>
                                <i-col span="16">
                                    <form-item label="工程名称："   prop="engName">
                                        <i-input v-model="formValidate.engName" placeholder="请输入工程名称"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="工程整编号："  prop="engNo">
                                        <i-input v-model="formValidate.engNo" placeholder="请输入工程整编号"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                            <row>
                                <i-col span="8">
                                    <form-item label="接收机构："  prop="orgNoCode">
                                        <i-select v-model="formValidate.orgNoCode" placeholder="请选择单位名称">
                                            <i-option  v-for="item in orgData" :value="item.orgCode" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="工程区域："   prop="engZone">
                                        <i-input v-model="formValidate.engZone" placeholder="请输入工程区域"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="工程地址："  prop="engLocation">
                                        <i-input v-model="formValidate.engLocation" placeholder="请输入工程地址"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                            <row>
                                <i-col span="8">
                                    <form-item label="开工时间："  prop="engSpecInfo.startDate">
                                        <date-picker v-model="formValidate.engSpecInfo.startDate" type="date" placeholder="请输入开工时间" ></date-picker>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="竣工时间："   prop="engSpecInfo.endDate">
                                        <date-picker v-model="formValidate.engSpecInfo.endDate" type="date" placeholder="请输入竣工时间" ></date-picker>
                                    </form-item>
                                </i-col>
                                <i-col span="8">

                                </i-col>
                            </row>
                        </p>
                    </Panel>
                    <Panel name="3">
                        责任者项
                        <p slot="content">
                            <row>
                                <i-col span="8">
                                    <form-item label="建设单位："  prop="engOrgAndCode.landUseOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.landUseOrgSid" placeholder="请选择建设单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.landUseOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="立项批准单位："   prop="engOrgAndCode.initiationApprovalOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.initiationApprovalOrgSid" placeholder="请选择立项批准单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.initiationApprovalOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="设计单位："   prop="engOrgAndCode.designOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.designOrgSid" placeholder="请选择设计单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.designOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                            <row>
                                <i-col span="8">
                                    <form-item label="勘察单位："  prop="engOrgAndCode.reconnaissanceOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.reconnaissanceOrgSid" placeholder="请选择勘察单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.reconnaissanceOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="监理单位："   prop="engOrgAndCode.supervisionOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.supervisionOrgSid" placeholder="请选择监理单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.supervisionOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="施工单位："   prop="engOrgAndCode.constructOrgSid">
                                        <i-select v-model="formValidate.engOrgAndCode.constructOrgSid" placeholder="请选择施工单位">
                                            <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                        </i-select>
                                        <i-input v-model="formValidate.engOrgAndCode.reconnaissanceOrgName" style="display:none"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                        </p>
                    </Panel>
                    <Panel name="4">
                        文号项
                        <p slot="content">
                            <row>
                                <i-col span="12">
                                    <form-item label="规划许可证号："  prop="engOrgAndCode.landUsePlanningNo">
                                        <i-input v-model="formValidate.engOrgAndCode.landUsePlanningNo" placeholder="请输入规划许可证号"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="12">
                                    <form-item label="施工许可证号："   prop="engOrgAndCode.constructNo">
                                        <i-input v-model="formValidate.engOrgAndCode.constructNo" placeholder="请输入施工许可证号"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                            <row>
                                <i-col span="12">
                                    <form-item label="竣工验收备案号："  prop="engOrgAndCode.finishBah">
                                        <i-input v-model="formValidate.engOrgAndCode.finishBah" placeholder="请输入竣工验收备案号"></i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="12">
                                    <form-item label=" 其他证件："  prop="engOrgAndCode.otherCert">
                                        <i-input v-model="formValidate.engOrgAndCode.otherCert"></i-input>
                                    </form-item>
                                </i-col>
                            </row>
                        </p>
                    </Panel>
                    <Panel name="5" v-if="engType==='1'">
                        房屋建筑专业信息
                        <p slot="content">
                            <row>
                                <i-col span="8">
                                    <form-item label="建筑面积：">
                                        <input-number style="width: 100%" type="number"  v-model.number="formValidate.engSpecInfo.constructionArea" placeholder="请输入建筑面积"></input-number>
                                        <span slot="append">㎡</span>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="高度：">
                                        <input-number style="width: 150px" type="text" v-model.number="formValidate.engHouseSpec.height" placeholder="请输入高度"></input-number>
                                        <span slot="append">m</span>
                                    </form-item>
                                </i-col>
                                <i-col span="8">
                                    <form-item label="地上层数：">
                                        <input-number style="width: 150px" type="text" v-model.number="formValidate.engHouseSpec.overNums" placeholder="请输入地上层数"></input-number>
                                    </form-item>
                                </i-col>
                            </row>
                        </p>
                    </Panel>
                </Collapse>
                <br/>
                <form-item class="general-edit-page-bottom-wrap">
                    <i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保  存 </i-button>
                    <i-button type="ghost" @click="handleReset('formValidate')" style="margin-left: 8px"> 关  闭 </i-button>
                </form-item>
            </i-form>
        </tab-pane>
        <tab-pane label="附件资料" icon="social-windows">
            <card style="width:100%;margin-bottom: 10px" v-for="mateItem in engConfMateJson">
                <p slot="title">
                    <icon type="ios-film-outline"></icon>
                    上传材料[{{mateItem.mateName}}]
                    <upload action="//jsonplaceholder.typicode.com/posts/" style="float:right">
                        <i-button type="ghost" icon="ios-cloud-upload-outline">Upload files</i-button>
                    </upload>
                </p>
            </card>
        </tab-pane>
        <tab-pane label="标签三">
            <div>
                <iframe src="$(ctxpath)/project/engineering/baidumap.do"></iframe>
            </div>
        </tab-pane>
    </tabs>

</div>
<script>
    var Main = {
        data:function () {
            var detailData=${dataEnties};
            var q_engType=StringUtility.QueryString("engType");
            detailData.engType=detailData.engType?String(detailData.engType):q_engType;
            detailData.status=detailData.status?String(detailData.status):'0';
            return {
                formValidate:detailData,
                ruleValidate: {
                    /*projSid: [
                        { required: true, message: '请选择【所属项目】！', type:"number", trigger: 'blur' }
                    ],
                    orgNoCode: [
                        { required: true, message: '请选择【单位名称】！', trigger: 'blur' }
                    ],
                    engName: [
                        { required: true, message: '【工程名称】不能为空！', trigger: 'blur' }
                    ],
                    engZone: [
                        { required: true, message: '【工程区域】不能为空！', trigger: 'blur' }
                    ],
                   'engSpecInfo.constructionArea':[{ required: true, type:'number', message: '【建筑面积】不能为空！', trigger: 'blur' }]*/
                },
                seen:(StringUtility.QueryString("op")!="view"),
                engType:q_engType,
                proBaseInfo:${proBaseInfo},
                orgData:${orgData},
                archCategoryData:SelectComponentUtil.GetDictionary('1'),
                sourceData:SelectComponentUtil.GetDictionary('5'),
                specTypeData:SelectComponentUtil.GetDictionary('9'),
                panelArr:[1,2,3,4,5,6,7,8],
                engConfMateJson:${engConfMateJson}
            }
        },
        computed:{

        },
        methods: {
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        var sendData=JSON.stringify(_self.formValidate);
                        debugger;
                        var url='/project/engineering/saveengineeringdetail.do';
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
            }
        }
    }
    var Component = Vue.extend(Main)
    new Component().$mount('#app')
</script>
</body>
</html>
