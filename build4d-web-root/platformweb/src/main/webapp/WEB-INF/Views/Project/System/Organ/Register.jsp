<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/5/16 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>在线业务指导系统—报建登记</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
</head>
<body>
<div id="app" class="general-edit-page-wrap" v-cloak>
    <div class="dialog-1-wrap-sy-nx scroll-div-wrap">
        <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="120">
        <Collapse v-model="panelArr">
            <Panel name="1">
                建设单位信息
                <p slot="content">
                    <row>
                        <i-col span="12">
                            <form-item label="单位名称："  prop="proOrganEntity.orgName">
                                <i-input v-model="formValidate.proOrganEntity.orgName" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="组织机构代码："  prop="proOrganEntity.orgCode">
                                <i-input v-model="formValidate.proOrganEntity.orgCode" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="单位地址："  prop="proOrganEntity.orgAddr">
                                <i-input v-model="formValidate.proOrganEntity.orgAddr" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="联系人："  prop="proOrganEntity.orgContacts">
                                <i-input v-model="formValidate.proOrganEntity.orgContacts" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="联系手机号码："  prop="proOrganEntity.orgContMobile">
                                <i-input v-model="formValidate.proOrganEntity.orgContMobile" placeholder="请填写手机号码，用于接送系统的短信通知"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="Email："  prop="proOrganEntity.orgEmail">
                                <i-input type="email" v-model="formValidate.proOrganEntity.orgEmail" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                </p>
            </Panel>
            <Panel name="2">
                工程信息
                <p slot="content">
                    <row>
                        <i-col span="7">
                            <form-item label="工程类别："   prop="engBaseInfoEntity.engType">
                                <i-select v-model="formValidate.engBaseInfoEntity.engType" placeholder="请选择所属工程类别" >
                                    <i-option  v-for="item in getEngTypeByPid('',true)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                                </i-select>
                            </form-item>
                        </i-col>
                        <i-col span="5">
                            <%--<i-select  v-show="showEngType2" v-model="formValidate.engBaseInfoEntity.engType2" placeholder="请选择所属二级工程类别" >
                                <i-option  v-for="item in getEngTypeByPid(formValidate.engBaseInfoEntity.engType,false)" :key="item.id" :value="item.id"  >{{item.caption}}</i-option>
                            </i-select>--%>
                                <span v-show="formValidate.engBaseInfoEntity.engType!=4">&nbsp;</span>
                                <Alert type="warning" style="margin-left: 5px;" show-icon v-show="formValidate.engBaseInfoEntity.engType==4">地下管线工程档案交接，施工管理的资料、图纸请另行提交！</Alert>
                        </i-col>
                        <i-col span="12">
                            <form-item label="接收机构："  prop="engBaseInfoEntity.recOrgSid">
                                <i-select ref="recOrg" :filterable=true  v-model="formValidate.engBaseInfoEntity.recOrgSid" placeholder="请选择" @on-change="handleChange(formValidate.engBaseInfoEntity.recOrgSid,'orgSid',formValidate.engBaseInfoEntity,['orgNoCode'],['orgCode'],orgData)">
                                    <i-option value="">请选择</i-option>
                                    <i-option  v-for="item in orgData" :value="item.orgSid" :key="item.orgSid">{{item.orgName}}</i-option>
                                </i-select>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="项目名称：" prop="projBaseInfoEntity.projName">
                                <i-input v-model="formValidate.projBaseInfoEntity.projName" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="工程名称："   prop="engBaseInfoEntity.engName">
                                <i-input v-model="formValidate.engBaseInfoEntity.engName" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="工程区域："   prop="engBaseInfoEntity.engZone">
                                <i-input v-model="formValidate.engBaseInfoEntity.engZone" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="开工时间："  prop="engBaseInfoEntity.engSpecInfo.startDate">
                                <date-picker v-model="formValidate.engBaseInfoEntity.engSpecInfo.startDate" type="date" placeholder="" ></date-picker>
                            </form-item>
                        </i-col>
                        <i-col span="6">
                            <form-item label="竣工时间："   prop="engBaseInfoEntity.engSpecInfo.endDate">
                                <date-picker v-model="formValidate.engBaseInfoEntity.engSpecInfo.endDate" type="date" placement="bottom-end" placeholder="" ></date-picker>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label=" 证件类型："  prop="certType">
                                <i-select v-model="formValidate.certType" placeholder="请选择证件类型" >
                                    <i-option value="规划许可证">规划许可证</i-option>
                                    <i-option value="施工许可证">施工许可证</i-option>
                                    <i-option value="责任书">责任书</i-option>
                                    <i-option value="其他">其他</i-option>
                                </i-select>
                            </form-item>
                        </i-col>
                        <i-col span="9">
                            <form-item ref="fCert" v-show="formValidate.certType!=''" label=" 证件号："  prop="engBaseInfoEntity.engOrgAndCode.landUsePlanningNo">
                                <i-input v-show="formValidate.certType=='规划许可证'" v-model="formValidate.engBaseInfoEntity.engOrgAndCode.landUsePlanningNo" placeholder=""></i-input>
                                <i-input v-show="formValidate.certType=='施工许可证'" v-model="formValidate.engBaseInfoEntity.engOrgAndCode.constructNo" placeholder=""></i-input>
                                <i-input v-show="formValidate.certType=='责任书' || formValidate.certType=='其他'" v-model="formValidate.engBaseInfoEntity.engOrgAndCode.otherCert"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="3" v-show="formValidate.certType!=''">
                            <upload ref="upload" :action="uploadUrl" :before-upload="handleUpload">
                                <i-button type="ghost" icon="ios-cloud-upload-outline">上传电子件</i-button>
                            </upload>
                        </i-col>
                    </row>

                </p>
            </Panel>
            <Panel name="3">
                建设单位登录账号
                <p slot="content">
                    <row>
                        <i-col span="12">
                            <form-item label="登录账号：" prop="proUserEntity.userAccount">
                                <i-input v-model="formValidate.proUserEntity.userAccount" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="用户名称：" prop="proUserEntity.userUsername">
                                <i-input v-model="formValidate.proUserEntity.userUsername" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">
                            <form-item label="登录密码：" prop="proUserEntity.userPsw">
                                <i-input type="password" v-model="formValidate.proUserEntity.userPsw" placeholder=""></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="12">
                            <form-item label="确认密码：" prop="confirmPwd">
                                <i-input type="password" v-model="formValidate.confirmPwd"  placeholder="" id="confirmPwd"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                    <row>
                        <i-col span="12">

                        </i-col>
                        <i-col span="12">

                        </i-col>
                    </row>
                </p>
            </Panel>
        </Collapse>
        <br/>
            <row>
                <i-col span="8">&nbsp;</i-col>
                <i-col span="8">
                    <form-item   prop="protocol">
                        <Checkbox v-model="formValidate.protocol" >
                            <Icon type="speakerphone"></Icon>
                            <span>同意</span>
                        </Checkbox>
                        <a href="javascript:void(0)" style="color:blue" onclick="showDisclaimer()">《免责声明》</a>
                    </form-item>
                </i-col>
                <i-col span="8">&nbsp;</i-col>
            </row>
    </i-form>
    </div>
    <div style="margin-top: 10px;text-align: center">
        <i-button type="primary" v-if="seen" @click="handleSubmit('formValidate')"> 保存并打印责任书 </i-button>
        <i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关  闭 </i-button>
    </div>
    <div style="display: none">
        <div id="disclaimer">
            工程资料在线管理报建协议
             <br/>  <br/>
            1、建设工程资料在线同步跟踪管理系统，是通过互联网实现建设工程资料的信息标准化管理， 旨在于城建档案馆为工程建设单位提供零距离服务， 真正意义上实现建设工程进度与建设工程资料同步管理的目的，
            <br/>  <br/>
            2、建设单位应按要求完成报建登记，电子邮箱的地址必须真实、准确，以确保建设工程档案在线咨 询互动畅通； 登录帐户名和登录密码的设置不得少于 6位的字符或数字，并妥善保管，以防信息丢失。
            <br/>  <br/>
            3、建设单位在工程报建确认后，应及时将建设工程过程中所形成的建设工程资料进行登记， 主动接受城建档案管理部门的业务指导，并负责检查和督促监理、 施工单位在线工程资料的登记。
            <br/>  <br/>
            4、建设单位在工程竣工验收前，应将登记的工程资料目录及时在线提交，确保建设工程档案在线预验收。
            <br/>  <br/>
            5、本系统具备电子文件网络上传功能，建设单位在使用时，应根据工程性质，考虑相关工程资料的 保密需求， 如不适于通过互联网上传的， 可采取其他方式向城建档案馆报送。
            <br/>  <br/>
            6、建设、监理、施工单位在使用本系统时，不得进行与本系统无关的操作，应符合互联网法律规定， 如违规，所造成后果法律自负。
        </div>
    </div>
</div>
<script>
    function showDisclaimer() {
        DialogUtility.Alert(window,DialogUtility.DialogId,{title:"免责声明",width:600,height:400},$("#disclaimer"));
    }
</script>
<script>
    var Main = {
        mounted:function () {
        },
        watch:{
            "file":{
                handler: function (val, oldVal) {
                    this.$refs['formValidate'].validateField("engBaseInfoEntity.engOrgAndCode.landUsePlanningNo",function(errMsg){
                    });
                }
            }
        },
        data:function () {
            var detailData=${dataEnties};
            detailData.confirmPwd="";
            detailData.certType='';
            detailData.protocol=false;
            detailData.isUpload=false;
            if(!detailData.engBaseInfoEntity.recOrgSid) detailData.engBaseInfoEntity.recOrgSid='';
            detailData.engBaseInfoEntity.status=detailData.engBaseInfoEntity.status?String(detailData.engBaseInfoEntity.status):'100';
            return {
                file: null,
                formValidate:detailData,
                ruleValidate: {
                    protocol:[
                        {
                            validator:function(rule,value,callback){
                                if(!value)
                                    callback(new Error('必须同意《免责声明》才能继续报建'));
                                else
                                    callback();
                            },trigger:'change'
                        }
                    ],
                    'proOrganEntity.orgName':[
                        { required: true, message: '请输入【单位名称】！', trigger: 'blur' },
                        { validator:this.validateOrganName,trigger:"blur"}],
                    'proOrganEntity.orgContMobile':[ { required: true, message: '请输入【联系手机号码】！', trigger: 'blur' }],
                    'proOrganEntity.orgCode':[
                        { required: true, message: '请输入【组织机构代码】！', trigger: 'blur' },
                        { validator:this.validateOrganCode,trigger:"blur"}],
                    'proOrganEntity.orgAddr':[ { required: true, message: '请输入【单位地址】！', trigger: 'blur' }],
                    'proOrganEntity.orgContacts':[ { required: true, message: '请输入【联系人】！', trigger: 'blur' }],
                    'proOrganEntity.orgPhone':[ { required: true, message: '请输入【联系电话】！', trigger: 'blur' }],
                    'proOrganEntity.orgEmail':[ { required: true, type:'email', message: '请输入【Email】！', trigger: 'blur' }],
                    'engBaseInfoEntity.engType':[ { required: true, message: '请选择【工程类别】！', trigger: 'change' }],
                    'engBaseInfoEntity.orgNoCode':[ { required: true, message: '请选择【接收机构】！', trigger: 'blur' }],
                    'projBaseInfoEntity.projName':[ { required: true, message: '请输入【项目名称】！', trigger: 'blur' }],
                    'engBaseInfoEntity.engName':[ { required: true, message: '请输入【工程名称】！', trigger: 'blur' }],
                    'engBaseInfoEntity.engZone':[ { required: true, message: '请输入【工程区域】！', trigger: 'blur' }],
                    'engBaseInfoEntity.engSpecInfo.startDate':[ { required: true,type:'date', message: '请输入开工时间！', trigger: 'blur' }],
                    'engBaseInfoEntity.engSpecInfo.endDate':[ { required: true, type:'date',message: '请输入竣工时间！', trigger: 'blur' }],
                    'engBaseInfoEntity.engOrgAndCode.landUsePlanningNo':[ { validator:this.validateCert, trigger: 'blur' }],
                    'certType':[ { required: true, message: '请选择【证件类型】！', trigger: 'change' }],
                    'engBaseInfoEntity.engOrgAndCode.constructNo':[ { validator:this.validateCert, trigger: 'blur' }],
                    'engBaseInfoEntity.engOrgAndCode.otherCert':[ { validator:this.validateCert,  trigger: 'blur' }],
                    'proUserEntity.userAccount':[
                        { required: true, message: '请输入【登录账号】！', trigger: 'blur' },
                        {validator:this.validateAccount,trigger:"blur"}
                        ],
                    'proUserEntity.userUsername':[ { required: true, message: '请输入【用户名称】！', trigger: 'blur' }],
                    'proUserEntity.userPsw':[
                        { required: true, message: '请输入【登录密码】！', trigger: 'blur' },
                        {validator:this.checkPwd,trigger:"blur"}
                    ],
                    'confirmPwd':[ { required: true, message: '请输入【确认密码】！', trigger: 'blur' },
                        {validator:this.checkPwd,trigger:"blur"}
                    ]
                },
                seen:(StringUtility.QueryString("op")!="view"),
                orgData:${orgData},
                panelArr:[1,2,3],
                showEngType2:true
            }
        },
        computed:{
            uploadUrl:function () {
               return "${ctxpath}/project/engineering/catalogfile/uploadCatalogfile.do?engSid="+this.formValidate.engBaseInfoEntity.sid+"&catalogSid=&filedescription="+this.formValidate.certType;
            }
        },
        methods: {
            handleUpload :function (file) {
                this.file = file;
                return false;
            },
            getURL:function (mateItem) {

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
            getEngTypeByPid:function (pid, isRoot) {
                var arr= ProjectUtil.EngMethod.GetEngTypeByPid(pid);
                if(!isRoot)
                {
                    if(arr.length>0 && this.formValidate.engType2==null)
                        this.formValidate.engBaseInfoEntity.engType2=arr[0].id;
                    else
                        this.formValidate.engBaseInfoEntity.engType2=null;
                    this.showEngType2=(arr.length>0);
                }
                return arr;
            },
            validateCert:function(rule,value,callback){
                var type=this.formValidate.certType;
                var c1=this.formValidate.engBaseInfoEntity.engOrgAndCode.landUsePlanningNo;
                var c2=this.formValidate.engBaseInfoEntity.engOrgAndCode.constructNo;
                var c3=this.formValidate.engBaseInfoEntity.engOrgAndCode.otherCert;

                //this.$refs["fCert"]  施工许可证 责任书 其他
                if((type=='规划许可证' && (c1==null || c1==""))
                    || (type=='施工许可证' && (c2==null || c2==""))
                    || ((type=='责任书' || type=='其他') && (c3==null || c3=="")))
                    callback(new Error('请输入证件的证件号！'));
                else if(this.formValidate.certType!='' && this.file==null)
                    callback(new Error('请上传证件的电子件！'));
                else
                    callback();
            },
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
            validateOrganName:function(rule,value,callback){
                this.validateOrgan(value,'','【机构名称】',callback);
            },
            validateOrganCode:function(rule,value,callback){
                this.validateOrgan('',value,'【机构代码】',callback);
            },
            validateOrgan:function(orgName,orgCode,caption,callback){
                var orgSid=null;
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
            checkPwd:function(rule,value,callback){
                var pwd="";
                if(rule.field=="proUserEntity.userPsw")
                    pwd=this.formValidate.confirmPwd;
                else
                    pwd=this.formValidate.proUserEntity.userPsw;

                if(pwd!=null && pwd!='' && value!=null && value!=''  && pwd!=value)
                    callback(new Error("两次输入的密码不一致！"));
                else
                    callback();
            },
            handleSubmit: function (name) {
                var _self=this;
                this.$refs[name].validate(function (valid) {
                    if (valid) {
                        //规划、施工、其他证件号至少输入一个！
                        var sendData=JSON.stringify(_self.formValidate);
                        //debugger;
                        var url='/project/system/organ/saveregister.do';
                        AjaxUtility.PostRequestBody(url,sendData,function (result) {
                            _self.formValidate.engBaseInfoEntity.sid=result.data;
                            DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},"报建登记成功,待审核通过后,您将接收到短信通知,之后再登录系统补全资料！",function () {
                                _self.$refs.upload.post(_self.file);
                                url="project/system/organ/registerstatement.do?sid="+result.data;
                                DialogUtility.Frame_OpenIframeWindow(window.parent,DialogUtility.DialogId02,url,{title:"建设工程档案移交责任书"},1);
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
            handleClose: function () {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    }
    var Component = Vue.extend(Main)
    var app=new Component().$mount('#app');
</script>
</body>
</html>

