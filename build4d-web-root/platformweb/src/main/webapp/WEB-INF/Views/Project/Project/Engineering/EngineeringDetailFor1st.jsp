<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 20112/5/16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>项目信息</title>
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
    <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
        <Collapse v-model="panelArr">
            <Panel name="1">
                建设单位信息
                <p slot="content">
                    <row>
                        <i-col span="12">
                            <form-item label="单位名称：">
                                 <i-input v-model="formValidate.constructionOrgEntity.orgName" ></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="组织机构代码：">
                                 <i-input v-model="formValidate.constructionOrgEntity.orgCode" ></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="单位地址：">
                                    <i-input v-model="formValidate.constructionOrgEntity.orgAddr" ></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="联系人：">
                                 <i-input v-model="formValidate.constructionOrgEntity.orgContacts"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="联系电话：">
                                    <i-input v-model="formValidate.constructionOrgEntity.orgContMobile"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="Email：">
                                 <i-input type="email" v-model="formValidate.constructionOrgEntity.orgEmail" ></i-input>
                            </form-item>
                        </i-col>
                    </row>
                </p>
            </Panel>
            <Panel name="2">
                工程信息
                <p slot="content">
                    <row>
                        <i-col span="6">
                            <form-item label="工程类别："   prop="engType">
                                <i-select v-model="formValidate.engType" placeholder="请选择所属工程类别" >
                                    <i-option  v-for="item in getEngTypeByPid('',true)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                                </i-select>
                            </form-item>
                        </i-col>
                        <i-col span="6" >
                            <%--<i-select v-show="showEngType2" v-model="formValidate.engType2" placeholder="请选择所属二级工程类别" >
                                <i-option  v-for="item in getEngTypeByPid(formValidate.engType,false)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                            </i-select>--%>&nbsp;
                        </i-col>
                        <i-col span="12">
                            <form-item label="接收机构：">
                                <i-input v-model="receiveOrgName" ></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="项目名称：">
                                <i-input v-model="formValidate.proBaseInfo.projName" ></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="工程名称：">
                                <i-input v-model="formValidate.engName"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="工程区域：">
                                <i-input v-model="formValidate.engZone" ></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="开工时间：">
                                <date-picker v-model="formValidate.engSpecInfo.startDate" type="date" ></date-picker>
                            </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="竣工时间：">
                                <date-picker v-model="formValidate.engSpecInfo.endDate" type="date"  ></date-picker>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="规划许可证号：">
                                <i-input v-model="formValidate.engOrgAndCode.landUsePlanningNo" ></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="施工许可证号：">
                                 <i-input v-model="formValidate.engOrgAndCode.constructNo" ></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="24">
                            <form-item label="其他证件：">
                                <i-input v-model="formValidate.engOrgAndCode.otherCert"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                </p>
            </Panel>
        </Collapse>
        <br/>
        <form-item class="general-edit-page-bottom-wrap" id="btList">
        </form-item>
    </i-form>
    <div style="display: none;">
        <i-form id="fOpinion" ref="formValidate2" :model="formValidate2" :rules="ruleValidate2" >
            <form-item   prop="opinion">
                <i-input v-model="formValidate2.opinion" type="textarea" :rows="4" placeholder="请输入未通过原因"></i-input>
            </form-item>
        </i-form>
    </div>
    <div  style="display: none;">
        <i-form id="fInstructor" ref="formValidate3" :model="formValidate.engOrgAndCode" :rules="ruleValidate3" >
            <form-item   prop="instructorSid">
                <i-select v-model="formValidate.engOrgAndCode.instructorSid" placeholder="请选择业务指导员" @on-change="handleChange(formValidate.engOrgAndCode.instructorSid,'userSid',formValidate.engOrgAndCode,['instructor'],['userUsername'],instructors)">
                    <i-option  v-for="item in instructors" :key="item.userSid" :value="item.userSid"  >{{item.userUsername}}</i-option>
                </i-select>
            </form-item>
        </i-form>
    </div>
</div>
<script>
    var status=${status};
    var roleId='5';
    var orgType="${orgType}";
    var op=StringUtility.QueryString("op");
    ProjectUtil.FlowMethod.RenderingActions(op,"btList",orgType,status);
    var Main = {
        data:function () {
            var detailData=${dataEnties};
            detailData.constructionOrgEntity=${orgEntity};
            detailData.engType=detailData.engType==null?'':String(detailData.engType);
            if(detailData.engSpecInfo.startDate!=null)
                detailData.engSpecInfo.startDate=DateUtility.DateFormat(new Date(detailData.engSpecInfo.startDate),"-");
            if(detailData.engSpecInfo.endDate!=null)
                detailData.engSpecInfo.endDate=DateUtility.DateFormat(new Date(detailData.engSpecInfo.endDate),"-");
            return {
                formValidate:detailData,
                ruleValidate: {
                },
                formValidate2:{
                    opinion:""
                },
                ruleValidate2: {
                    opinion: [
                        { required: true, message: '请输入【未通过原因】！',  trigger: 'blur' }
                    ],
                },
                ruleValidate3: {
                    instructorSid: [
                        { required: true, type:"number",message: '请选择【业务指导员】！',  trigger: 'blur' }
                    ],
                },
                receiveOrgName:'${receiveOrgName}',
                panelArr:[1,2,3],
                showEngType2:true,
                instructors:{}
            }
        },
        mounted:function () {
          this.getInstructors();
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
            getInstructors:function () {
                var _self=this;
                var url='/project/system/user/getmemberbyroleid.do';
                var sendData={
                    roleId:roleId
                };
                B4D.AjaxUtility.Post(url,sendData,function (result) {
                    if (result.success) {
                        var r=[];
                        $.each(result.data,function (k,v) {
                            r.push(v.proUser);
                        })
                        _self.instructors=r;
                    }
                },"json");
            },
            saveInstructors:function () {
                var _self=this;
                var url='/project/engineering/setinstructors.do';
                var sendData={
                    engSid:this.formValidate.sid,
                    userId:this.formValidate.engOrgAndCode.instructorSid,
                    userName:this.formValidate.engOrgAndCode.instructor,
                };
                B4D.AjaxUtility.PostSync(url,sendData,function (result) {
                    if (result.success) {

                    }
                },"json");
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
                                 ProjectUtil.FlowMethod.UpdateStatus(StringUtility.QueryString("sid"),
                                     currStatus,nextStatus,_self.formValidate2.opinion);
                            }
                        });
                        return v;

                    });
                }
                else{
                    DialogUtility.ComfirmBy(window,$("#fInstructor"),{title:"分派业务指导员",height:230,width:300},function () {
                        var v=false;
                        _self.$refs["formValidate3"].validate(function (valid) {
                            v=valid;
                            if (valid) {
                                _self.saveInstructors();
                                ProjectUtil.FlowMethod.UpdateStatus(StringUtility.QueryString("sid"),
                                    currStatus,nextStatus,"");
                            }
                        });
                        return v;
                    });
                }
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

