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
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
    <style>
        .ivu-input-number{
            width: 100%;
        }
    </style>
</head>
<body>
<div id="app" class="general-edit-page-wrap" v-cloak>

        <tabs type="card">
            <tab-pane label="基础信息" icon="social-apple">
                <div class="dialog-1-wrap-sy-nx scroll-div-wrap" style="height:590px ">
                    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
                        <Collapse v-model="panelArr">
                            <Panel name="1">
                                工程所属项目
                                <p slot="content">
                                    <row>
                                        <i-col span="16">
                                            <form-item label="所属项目："   prop="projSid">
                                                <i-select number type="number" v-model.number="formValidate.projSid" placeholder="请选择所属项目">
                                                    <i-option  v-for="item in proBaseInfo" :value="item.sid" :key="item.sid" >{{item.projName}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="5">
                                            <form-item label="工程类别："   prop="engType">
                                                <i-select v-model="formValidate.engType" placeholder="请选择所属工程类别" disabled>
                                                    <i-option  v-for="item in getEngTypeByPid('',true)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="3" v-if="showEngType2">
                                           <%-- <i-select v-model="formValidate.engType2" placeholder="请选择所属二级工程类别" >
                                                <i-option  v-for="item in getEngTypeByPid(formValidate.engType,false)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                                            </i-select>--%>
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
                                                <i-input v-model="formValidate.engNo" placeholder="工程编号保存后自动产生" disabled></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="接收机构："  prop="orgNoCode">
                                                <i-select v-model="formValidate.recOrgSid" placeholder="请选择单位名称"  @on-change="handleChange(formValidate.recOrgSid,'orgSid',formValidate,['orgNoCode'],['orgCode'],recOrg)">
                                                    <i-option value="">请选择单位名称</i-option>
                                                    <i-option  v-for="item in recOrg" :value="item.orgSid"   :key="item.orgSid">{{item.orgName}}</i-option>
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
                                                <i-select v-model="formValidate.engOrgAndCode.landUseOrgSid" disabled placeholder="请选择建设单位" @on-change="handleChange(formValidate.engOrgAndCode.landUseOrgSid,'orgSid',formValidate.engOrgAndCode,['landUseOrgName'],['orgName'],landUseOrg)">
                                                    <i-option  v-for="item in landUseOrg" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>
                                                <i-input v-model="formValidate.engOrgAndCode.landUseOrgName" style="display:none"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="立项批准单位："   prop="engOrgAndCode.initiationApprovalOrgSid">
                                               <%-- <i-select v-model="formValidate.engOrgAndCode.initiationApprovalOrgSid" placeholder="请选择立项批准单位" @on-change="handleChange(formValidate.engOrgAndCode.initiationApprovalOrgSid,'orgSid',formValidate.engOrgAndCode,['initiationApprovalOrgName'],['orgName'],orgData)">
                                                    <i-option  v-for="item in orgData" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>--%>
                                                <i-input v-model="formValidate.engOrgAndCode.initiationApprovalOrgName" ></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="设计单位："   prop="engOrgAndCode.designOrgSid">
                                                <i-select ref="designOrg" :clearable="true" :filterable="true" v-model="formValidate.engOrgAndCode.designOrgSid" placeholder="请选择设计单位" @on-change="handleChange(formValidate.engOrgAndCode.designOrgSid,'orgSid',formValidate.engOrgAndCode,['designOrgName'],['orgName'],designOrg)">
                                                    <i-option value="">请选择设计单位</i-option>
                                                    <i-option  v-for="item in designOrg" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>
                                                <i-input v-model="formValidate.engOrgAndCode.designOrgName" style="display:none"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="勘察单位："  prop="engOrgAndCode.reconnaissanceOrgSid">
                                               <%-- <i-select v-model="formValidate.engOrgAndCode.reconnaissanceOrgSid" placeholder="请选择勘察单位" @on-change="handleChange(formValidate.engOrgAndCode.reconnaissanceOrgSid,'orgSid',formValidate.engOrgAndCode,['reconnaissanceOrgName'],['orgName'],orgData)">
                                                    <i-option  v-for="item in orgData" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>--%>
                                                <i-input v-model="formValidate.engOrgAndCode.reconnaissanceOrgName"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="监理单位："   prop="engOrgAndCode.supervisionOrgSid">
                                                <i-select ref="supervisionOrg" :clearable="true" :filterable="true" v-model="formValidate.engOrgAndCode.supervisionOrgSid" placeholder="请选择监理单位" @on-change="handleChange(formValidate.engOrgAndCode.supervisionOrgSid,'orgSid',formValidate.engOrgAndCode,['supervisionOrgName'],['orgName'],supervisionOrg)">
                                                    <i-option value="">请选择监理单位</i-option>
                                                    <i-option  v-for="item in supervisionOrg" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>
                                                <i-input v-model="formValidate.engOrgAndCode.supervisionOrgName" style="display:none"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="施工单位："   prop="engOrgAndCode.constructOrgSid">
                                                <i-select ref="constructOrg" :clearable="true" :filterable="true" v-model="formValidate.engOrgAndCode.constructOrgSid" placeholder="请选择施工单位" @on-change="handleChange(formValidate.engOrgAndCode.constructOrgSid,'orgSid',formValidate.engOrgAndCode,['constructOrgName'],['orgName'],constructOrg)">
                                                    <i-option value="">请选择施工单位</i-option>
                                                    <i-option  v-for="item in constructOrg" :value="item.orgSid"  :key="item.orgSid">{{item.orgName}}</i-option>
                                                </i-select>
                                                <i-input v-model="formValidate.engOrgAndCode.constructOrgName" style="display:none"></i-input>
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
                                            <form-item label="竣工验收备案号：" :label-width="110" prop="engOrgAndCode.finishBah">
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
                            <Panel name="5" v-if="showPanel(1)">
                                房屋建筑专业信息
                                <p slot="content">
                                    <row>
                                        <i-col span="8">
                                            <form-item label="建筑面积："  prop="engSpecInfo.constructionArea">
                                                <input-number v-model="formValidate.engSpecInfo.constructionArea" placeholder="请输入建筑面积">
                                                    <span slot="append">㎡</span>
                                                </input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="高度："   prop="engHouseSpec.height" >
                                                <input-number v-model="formValidate.engHouseSpec.height" placeholder="请输入高度"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="地上层数："   prop="engHouseSpec.overNums">
                                                <input-number v-model="formValidate.engHouseSpec.overNums" @on-change="function(v){convertToInt(v,formValidate.engHouseSpec,'overNums')}"  placeholder="请输入地上层数"></input-number>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="地下层数："  prop="engHouseSpec.underNums">
                                                <input-number v-model="formValidate.engHouseSpec.underNums" @on-change="function(v){convertToInt(v,formValidate.engHouseSpec,'underNums')}" placeholder="请输入地下层数"></input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="结构类型："   prop="engHouseSpec.consTypeCode">
                                                <i-select v-model="formValidate.engHouseSpec.consTypeCode" placeholder="请选择结构类型">
                                                    <i-option  v-for="item in getDict(16)" :value="item.dictValue">{{item.dictText}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="工程预算："   prop="engSpecInfo.engBudget">
                                                <input-number v-model="formValidate.engSpecInfo.engBudget" placeholder="请输入工程预算"></input-number>
                                                <span slot="append">万元</span>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="抗震等级："  prop="engSpecInfo.seismicGrade">
                                                <input-number v-model="formValidate.engSpecInfo.seismicGrade" placeholder="请输入抗震等级"></input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="人防面积："   prop="engSpecInfo.defenseArea">
                                                <input-number v-model="formValidate.engSpecInfo.defenseArea" placeholder="请输入人防面积"></input-number>
                                                <span slot="append">㎡</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="防火等级："   prop="engSpecInfo.fireGrade">
                                                <input-number v-model="formValidate.engSpecInfo.fireGrade" placeholder="请输入防火等级"></input-number>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="车位数："  prop="engSpecInfo.parkingNum">
                                                <input-number v-model="formValidate.engSpecInfo.parkingNum"  @on-change="function(v){convertToInt(v,formValidate.engSpecInfo,'parkingNum')}" placeholder="请输入车位数"></input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="工程质量："   prop="engSpecInfo.engQuality">
                                                <input-number v-model="formValidate.engSpecInfo.engQuality" placeholder="请输入工程质量"></input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">

                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="24">
                                            <form-item label="获奖情况："  prop="engSpecInfo.awardsInfo" >
                                                <i-input v-model="formValidate.engSpecInfo.awardsInfo" type="textarea" :rows="4" placeholder="请输入获奖情况"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="项目总监："  prop="engOrgAndCode.pmDirector">
                                                <i-input v-model="formValidate.engOrgAndCode.pmDirector" placeholder="请输入项目总监"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="现场经理："   prop="engOrgAndCode.fieldManager">
                                                <i-input v-model="formValidate.engOrgAndCode.fieldManager" placeholder="请输入现场经理"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="分包单位："   prop="engOrgAndCode.subUnit">
                                                <i-input v-model="formValidate.engOrgAndCode.subUnit" placeholder="请输入分包单位"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="项目经理："  prop="engOrgAndCode.projectManager">
                                                <i-input v-model="formValidate.engOrgAndCode.projectManager" placeholder="请输入项目经理"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="监理单位审核人：" :label-width="110"  prop="engOrgAndCode.supervionChecker">
                                                <i-input v-model="formValidate.engOrgAndCode.supervionChecker" placeholder="请输入监理单位审核人"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="建设单位审核人："  :label-width="110" prop="engOrgAndCode.consUnitChecker">
                                                <i-input v-model="formValidate.engOrgAndCode.consUnitChecker" placeholder="请输入建设单位审核人"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                </p>
                            </Panel>
                            <Panel name="6" v-if="showPanel(2)">
                                市政道路工程专业信息
                                <p slot="content">
                                    <row>
                                        <i-col span="8">
                                            <form-item label="道路面积："  prop="engSpecInfo.constructionArea">
                                                <input-number v-model="formValidate.engSpecInfo.constructionArea" placeholder="请输入道路面积"></input-number>
                                                <span slot="append">㎡</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="道路等级："   prop="engSpecInfo.levelCode">
                                                <i-input v-model="formValidate.engSpecInfo.levelCode" placeholder="请输入道路等级"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="道路总长度："   prop="engFacilitySpec.length">
                                                <input-number v-model="formValidate.engFacilitySpec.length" placeholder="请输入道路总长度"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="路宽："  prop="engFacilitySpec.width">
                                                <input-number v-model="formValidate.engFacilitySpec.width" placeholder="请输入路宽"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="路面种类："   prop="engFacilitySpec.category">
                                                <i-input v-model="formValidate.engFacilitySpec.category" placeholder="请输入路面种类"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="工程起点："   prop="engFacilitySpec.startX">
                                                <i-input v-model="formValidate.engFacilitySpec.startX" placeholder="请输入工程起点"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="工程止点："  prop="engFacilitySpec.endX">
                                                <i-input v-model="formValidate.engFacilitySpec.endX" placeholder="请输入工程止点"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="总预算："   prop="engSpecInfo.engBudget">
                                                <input-number v-model="formValidate.engSpecInfo.engBudget" placeholder="请输入总预算"></input-number>
                                                <span slot="append">万元</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="总决算："   prop="engSpecInfo.engCost">
                                                <input-number v-model="formValidate.engSpecInfo.engCost" placeholder="请输入总决算"></input-number>
                                                <span slot="append">万元</span>
                                            </form-item>
                                        </i-col>
                                    </row
                                    <row>
                                        <i-col span="8">
                                            <form-item label="总长度："  prop="engSpecInfo.totalLength">
                                                <input-number v-model="formValidate.engSpecInfo.totalLength" placeholder="请输入总长度"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="工程质量："   prop="engSpecInfo.engQuality">
                                                <input-number v-model="formValidate.engSpecInfo.engQuality" placeholder="请输入工程质量"></input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="其他："   prop="engFacilitySpec.other">
                                                <i-input v-model="formValidate.engFacilitySpec.other"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="24">
                                            <form-item label="获奖情况："  prop="engSpecInfo.awardsInfo" >
                                                <i-input v-model="formValidate.engSpecInfo.awardsInfo" type="textarea" :rows="4" placeholder="请输入获奖情况"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                </p>
                            </Panel>
                            <Panel name="7" v-if="showPanel(3)">
                                市政桥梁工程专业信息
                                <p slot="content">
                                    <row>
                                        <i-col span="8">
                                            <form-item label="桥梁长度："  prop="engFacilitySpec.length">
                                                <input-number v-model="formValidate.engFacilitySpec.length" placeholder="请输入桥梁长度">
                                                    <span slot="append">m</span>
                                                </input-number>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="桥梁宽度："  prop="engFacilitySpec.width">
                                                <input-number v-model="formValidate.engFacilitySpec.width" placeholder="请输入桥梁宽度"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="载荷标准："   prop="engSpecInfo.loadStandard">
                                                <i-input v-model="formValidate.engSpecInfo.loadStandard" placeholder="请输入载荷标准"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="桥梁类别："  prop="engFacilitySpec.category">
                                                <i-input v-model="formValidate.engFacilitySpec.category" placeholder="请输入桥梁类别"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="主体结构："  prop="engFacilitySpec.consTypeCode">
                                                <i-select v-model="formValidate.engFacilitySpec.consTypeCode" placeholder="请选择主体结构">
                                                    <i-option  v-for="item in getDict(19)" :value="item.dictValue">{{item.dictText}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="桥梁跨度："   prop="engSpecInfo.span">
                                                <input-number v-model="formValidate.engSpecInfo.span" placeholder="请输入桥梁跨度"></input-number>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="抗震等级："  prop="engSpecInfo.seismicGrade">
                                                <i-input v-model="formValidate.engSpecInfo.seismicGrade" placeholder="请输入抗震等级"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="桥梁级别："  prop="engFacilitySpec.levelCode">
                                                <i-input v-model="formValidate.engFacilitySpec.levelCode" placeholder="请输入桥梁级别"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="桥梁净空："   prop="engSpecInfo.headroom">
                                                <input-number v-model="formValidate.engSpecInfo.headroom" placeholder="请输入桥梁净空"></input-number>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="桥梁载荷："  prop="engFacilitySpec.bridgeLoad">
                                                <i-input v-model="formValidate.engFacilitySpec.bridgeLoad" placeholder="请输入桥梁载荷"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="总预算："   prop="engSpecInfo.engBudget">
                                                <input-number v-model="formValidate.engSpecInfo.engBudget" placeholder="请输入总预算"></input-number>
                                                <span slot="append">万元</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="总决算："   prop="engSpecInfo.engCost">
                                                <input-number v-model="formValidate.engSpecInfo.engCost" placeholder="请输入总决算"></input-number>
                                                <span slot="append">万元</span>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="总长度："  prop="engSpecInfo.totalLength">
                                                <input-number v-model="formValidate.engSpecInfo.totalLength" placeholder="请输入总长度"></input-number>
                                                <span slot="append">m</span>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="工程质量："   prop="engSpecInfo.engQuality">
                                                <i-input v-model="formValidate.engSpecInfo.engQuality" placeholder="请输入工程质量"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="其他："   prop="engFacilitySpec.other">
                                                <i-input v-model="formValidate.engFacilitySpec.other"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="24">
                                            <form-item label="获奖情况："  prop="engSpecInfo.awardsInfo" >
                                                <i-input v-model="formValidate.engSpecInfo.awardsInfo" type="textarea" :rows="4" placeholder="请输入获奖情况"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                </p>
                            </Panel>
                           <%-- <Panel name="8">
                                档案信息
                                <p slot="content">
                                    <row>
                                        <i-col span="8">
                                            <form-item label="档案分类："   prop="archCategoryCode">
                                                <i-select v-model="formValidate.archCategoryCode" placeholder="请选择档案分类">
                                                    <i-option  v-for="item in getDict(1)" :value="item.dictValue">{{item.dictText}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="建设工程分类："  prop="engCategoryCode">
                                                <i-select v-model="formValidate.engCategoryCode" placeholder="请选择建设工程分类">
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="原编档号："   prop="oldEngId">
                                                <i-input v-model="formValidate.projId" placeholder="请输入原编档号"></i-input>
                                            </form-item>
                                        </i-col>

                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="进馆日期："  prop="archivingDate">
                                                <date-picker v-model="formValidate.archivingDate" type="date" placeholder="请输入进馆日期" ></date-picker>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="移交单位："   prop="handoverOrgName">
                                                <i-input v-model="formValidate.handoverOrgName" placeholder="请输入移交单位"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                        <form-item label="产生来源："   prop="sourceCode">
                                            <i-select v-model="formValidate.sourceCode" placeholder="请输入产生来源">
                                                <i-option  v-for="item in getDict(5)" :value="item.dictValue">{{item.dictText}}</i-option>
                                            </i-select>
                                        </form-item>
                                    </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="缩微(光盘)号："   prop="microNo">
                                                <i-input v-model="formValidate.microNo" placeholder="请输入缩微(光盘)号"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="标引人："  prop="indexedBy">
                                                <i-input v-model="formValidate.indexedBy" placeholder="请输入标引人"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="标引时间："   prop="indexedDt">
                                                <date-picker v-model="formValidate.indexedDt" type="date" placeholder="请输入标引时间" ></date-picker>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="整理人："  prop="arrangedBy">
                                                <i-input v-model="formValidate.arrangedBy" placeholder="请输入整理人"></i-input>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="整理时间："   prop="arrangedDt">
                                                <date-picker v-model="formValidate.arrangedDt" type="date" placeholder="请输入整理时间" ></date-picker>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="审核人："  prop="checkedBy">
                                                <i-select v-model="formValidate.checkedBy" placeholder="请选择审核人">
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label=" 审核时间："   prop="checkedDt">
                                                <date-picker v-model="formValidate.checkedDt" type="date" placeholder="请输入审核时间" ></date-picker>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="专业记载类："  prop="specType">
                                                <i-select  v-model="formValidate.specType" placeholder="请选择专业记载类">
                                                    <i-option  v-for="item in getDict(9)" :value="item.dictValue">{{item.dictText}}</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="工程曾用名："   prop="engFormerName">
                                                <i-input v-model="formValidate.engFormerName" placeholder="请输入工程曾用名"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="规划档案的分配档号的年度："  prop="archiveYear">
                                                <i-input v-model="formValidate.archiveYear" placeholder="请输入规划档案的分配档号的年度"></i-input>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="移交时间："   prop="transferDate">
                                                <date-picker v-model="formValidate.transferDate" type="date" placeholder="请输入移交时间" ></date-picker>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="接收人："  prop="receiveBy">
                                                <i-input v-model="formValidate.receiveBy" placeholder="请输入接收人"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="接受时间："   prop="receiveDt">
                                                <date-picker v-model="formValidate.receiveDt" type="date" placeholder="请输入接受时间" ></date-picker>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="临时存放位置："  prop="deposit">
                                                <i-input v-model="formValidate.deposit" placeholder="请输入临时存放位置"></i-input>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="密级："   prop="securityLevelCode">
                                                <i-select v-model="formValidate.securityLevelCode" placeholder="请选择密级">
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="保管期限："  prop="storageTypeCode">
                                                <i-select v-model="formValidate.storageTypeCode" placeholder="请选择保管期限">
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                        <i-col span="8">
                                            <form-item label="业务指导工程流水号："   prop="engGuideSid">
                                                <i-input  type="number" v-model.number="formValidate.engGuideSid" placeholder="请输入业务指导工程流水号"></i-input>
                                            </form-item>
                                        </i-col>

                                        <i-col span="8">
                                            <form-item label="路："  prop="roadName">
                                                <i-input v-model="formValidate.roadName" placeholder="请输入路名称"></i-input>
                                            </form-item>
                                        </i-col>
                                    </row>
                                    <row>
                                        <i-col span="8">
                                            <form-item label="状态："  prop="status">
                                                <i-select  v-model="formValidate.status" placeholder="请选择状态">
                                                    <i-option value="0" selected>待审核</i-option>
                                                </i-select>
                                            </form-item>
                                        </i-col>
                                    <i-col span="8">
                                        <form-item label="版本号："   prop="version">
                                            <input-number v-model="formValidate.version"  ></input-number>
                                        </form-item>
                                    </i-col>

                                    <i-col span="8">
                                    </i-col>
                                </row>
                                <row>
                                    <i-col span="24">
                                        <form-item label="附注："  prop="note">
                                            <i-input v-model="formValidate.note" type="textarea" :rows="4"></i-input>
                                        </form-item>
                                    </i-col>
                                </row>
                                </p>
                            </Panel>--%>
                        </Collapse>
                    </i-form>
                </div>
            </tab-pane>
          <%--  <tab-pane label="附件资料" icon="social-windows" style="display: none">

            </tab-pane>--%>
            <tab-pane label="工程进度" v-if="showProgress('ifEngProgress','${ctxpath}/project/engineering/engprogress.do?sid=')">
                <div>
                    <iframe id="ifEngProgress" style="width: 100%;height: 550px" frameborder="0"></iframe>
                </div>
            </tab-pane>
            <tab-pane label="文件收集进度" v-if="showProgress('ifFileProgress','${ctxpath}/project/engineering/catalogfile/fileuploadprogress.do?engSid=')">
                <div>
                    <iframe id="ifFileProgress" style="width: 100%;height: 550px" frameborder="0"></iframe>
                </div>
            </tab-pane>
           <tab-pane label="扩展标注">
                <div>
                    <iframe id="mapIframe" src="" style="width: 100%;height: 550px"></iframe>
                </div>
            </tab-pane>
        </tabs>
    <div style="margin-top: 10px;text-align: center" id="btList">
    </div>
    <div style="display: none;">
        <i-form id="fOpinion" ref="formValidate2" :model="formValidate2" :rules="ruleValidate2" >
            <form-item   prop="opinion">
                <i-input v-model="formValidate2.opinion" type="textarea" :rows="4" placeholder="请输入未通过原因"></i-input>
            </form-item>
        </i-form>
    </div>
</div>
<script>
    var status=${status};
    var orgType="${orgType}";
    var op=StringUtility.QueryString("op");
    ProjectUtil.FlowMethod.RenderingActions(op,"btList",orgType,status);
    var Main = {
        mounted:function () {
        },
        data:function () {
            var detailData=${dataEnties};
            var q_engType=StringUtility.QueryString("engType");
            detailData.engType=detailData.engType?String(detailData.engType):q_engType;
            detailData.status=detailData.status?String(detailData.status):'0';
            if(!detailData.recOrgSid) detailData.recOrgSid='';
            if(!detailData.engOrgAndCode.designOrgSid) detailData.engOrgAndCode.designOrgSid='';
            if(!detailData.engOrgAndCode.supervisionOrgSid) detailData.engOrgAndCode.supervisionOrgSid='';
            if(!detailData.engOrgAndCode.constructOrgSid) detailData.engOrgAndCode.constructOrgSid='';
            if(detailData.engSpecInfo.startDate!=null)
                detailData.engSpecInfo.startDate=DateUtility.DateFormat(new Date(detailData.engSpecInfo.startDate),"-");
            if(detailData.engSpecInfo.endDate!=null)
                detailData.engSpecInfo.endDate=DateUtility.DateFormat(new Date(detailData.engSpecInfo.endDate),"-");
            return {
                formValidate:detailData,
                ruleValidate: {
                    projSid: [
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
                   'engSpecInfo.constructionArea':[{ required: true, type:'number', message: '【建筑面积】不能为空！', trigger: 'blur' }],
                    'engOrgAndCode.supervisionOrgSid':[{ required: true, type:'number', message: '请选择【监理单位】！', trigger: 'blur' }],
                    'engOrgAndCode.constructOrgSid':[{ required: true, type:'number', message: '请选择【施工单位】！', trigger: 'blur' }]
                },
                formValidate2:{
                    opinion:""
                },
                ruleValidate2: {
                    opinion: [
                        { required: true, message: '请输入【未通过原因】！',  trigger: 'blur' }
                    ],
                },
                showEngType2:true,
                proBaseInfo:${proBaseInfo},
                orgData:${orgData},
                panelArr:[1,2,3,4,5,6,7,8],
                orgType:"${orgType}",
                columns_config:[
                    {
                        title: '文件编码',
                        key: 'mateValue'
                    },{
                        title: '文件名称',
                        key: 'mateName'
                    }],
                table_data:null
            }
        },
        mounted:function(){
            $("#mapIframe").attr("src","${ctxpath}/project/engineering/baidumap.do?sId="+StringUtility.QueryString("sid")+"&engName="+encodeURIComponent(this.formValidate.engName));
        },
        computed:{
            recOrg:function () {
                return this.getOrganByType(this.orgData,"档案管理单位")
            },
            landUseOrg:function () {
                return this.getOrganByType(this.orgData,"建设单位")
            },
            designOrg:function () {
                return this.getOrganByType(this.orgData,"设计单位")
            },
            supervisionOrg:function () {
                return this.getOrganByType(this.orgData,"监理单位")
            },
            constructOrg:function () {
                return this.getOrganByType(this.orgData,"施工单位")
            }
        },
        methods: {
            getDict:function (dictId) {
                return SelectComponentUtil.GetDictionary(dictId);
            },
            convertToInt:function (v,modelObj,fieldName) {
                var reg=new RegExp("\\d+\\.\\d+")
                if(reg.test(v))
                {
                    v=parseInt(v);
                    setTimeout(function () {
                        modelObj[fieldName]=v;
                    },10);
                }
            },
            getOrganByType:function (orgData,orgType) {
                var orgList=$.grep(orgData,function(o){
                    return orgType==o.orgType;
                });
                return orgList;
            },
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
            showPanel:function (index) {
                if(this.formValidate.engType2==null)
                {
                    return this.formValidate.engType==index;
                }
                else
                {
                    return this.formValidate.engType2==index;
                }
            },
            getEngTypeByPid:function (pid, isRoot) {
                var arr= ProjectUtil.EngMethod.GetEngTypeByPid(pid);
                if(!isRoot)
                {
                    if(arr.length>0 && this.formValidate.engType2==null)
                        this.formValidate.engType2=arr[0].id;
                    this.showEngType2=(arr.length>0);
                }
               return arr;
            },
            showProgress:function (iframeId,src) {
                var id=this.formValidate.sid;
                var tab=StringUtility.QueryString("tab")
                if(tab!="")
                {
                    if(tab!=iframeId)
                    {
                        return false;
                    }
                }
                if(id!=null){
                     src=src+id;
                    $("#"+iframeId).attr("src",src)
                    return true;
                }
                return false;
            },
            saveData:function (currStatus,nextStatus,saveForm,opinion){
                var _self=this;
                if(saveForm){
                    this.$refs['formValidate'].validate(function (valid) {
                        if (valid) {
                            var sendData=JSON.stringify(_self.formValidate);
                            var url='/project/engineering/saveengineeringdetail.do';
                            AjaxUtility.PostRequestBody(url,sendData,function (result) {
                                if(currStatus==nextStatus)
                                {
                                    DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},result.message,function () {
                                        //debugger;
                                        window.OpenerWindowObj.app.reloadData();
                                        DialogUtility.Frame_CloseDialog(window);
                                    });
                                }
                                else{
                                    ProjectUtil.FlowMethod.UpdateStatus(result.data,
                                        currStatus,nextStatus,opinion);
                                }
                            },"json");
                        } else {
                            this.$Message.error('Fail!');
                        }
                    })
                }
                else{
                    if(StringUtility.QueryString("sid")==null)
                        DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},"工程主键为空，保存失败！");
                    else
                        ProjectUtil.FlowMethod.UpdateStatus(StringUtility.QueryString("sid"),
                            currStatus,nextStatus,opinion);
                }
            },
            handleFlowSubmit: function (currStatus,nextStatus,needOpinion,saveForm) {
                var _self=this;
                if(needOpinion)
                {
                    DialogUtility.ComfirmBy(window,$("#fOpinion"),{title:"未通过原因",height:230,width:300},function () {
                        var v=false;
                        _self.$refs["formValidate2"].validate(function (valid) {
                            v=valid;
                            if (valid) {
                                _self.saveData(currStatus,nextStatus,saveForm,_self.formValidate2.opinion);
                            }
                        });
                        return v;
                    });
                }
                else
                {
                    _self.saveData(currStatus,nextStatus,saveForm,"");
                }
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
    var app=new Component();
        app.$mount('#app')
</script>
</body>
</html>
