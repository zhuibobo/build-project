<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/16
  Time: 10:22
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
    <div id="app">
        <card style="width:100%">
            <p slot="title">
                <icon type="ios-film-outline"></icon>
                编辑上传材料[{{engName}}]
            </p>
            <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
                <form-item label="上级分类：">
                    <row>
                        <i-col span="24">
                            <form-item prop="matePSid">
                                <i-select v-model="formValidate.matePSid" placeholder="请选择上级分类" >
                                    <i-option  v-for="item in table_data" :key="item.mateSid" :value="item.mateSid"  >{{item.mateValue}}----{{item.mateName}}</i-option>
                                </i-select>
                            </form-item>
                        </i-col>
                    </row>
                </form-item>
                <form-item label="材料编码：">
                    <row>
                        <i-col span="10">
                            <form-item prop="mateValue">
                                <i-input v-model="formValidate.mateValue"></i-input>
                            </form-item>
                        </i-col>
                        <i-col span="4" style="text-align: center">材料名称：</i-col>
                        <i-col span="10">
                            <form-item prop="mateName">
                                <i-input v-model="formValidate.mateName"></i-input>
                            </form-item>
                        </i-col>
                    </row>
                </form-item>
                <form-item label="是否目录：">
                    <row>
                        <i-col span="24">
                            <radio-group v-model="formValidate.isdirectory">
                                <radio :label="1">是</radio>
                                <radio :label="0">否</radio>
                            </radio-group>
                            (注：如果选中【是】则表明该文件是目录节点，否则是实体文件)
                        </i-col>
                    </row>
                </form-item>
                <form-item label="是否必须：">
                    <row>
                        <i-col span="10">
                            <form-item>
                                <radio-group v-model="formValidate.mateNeed">
                                    <radio :label="1">是</radio>
                                    <radio :label="0">否</radio>
                                </radio-group>
                            </form-item>
                        </i-col>
                        <i-col span="4" style="text-align: center">状态：</i-col>
                        <i-col span="10">
                            <radio-group v-model="formValidate.mateStatus">
                                <radio :label="1">启动</radio>
                                <radio :label="2">禁用</radio>
                            </radio-group>
                        </i-col>
                    </row>
                </form-item>
                <form-item label="归档单位：">
                    <checkbox-group v-model="formValidate.unitTypeArray">
                        <checkbox label="建设单位"></checkbox>
                        <checkbox label="施工单位"></checkbox>
                        <checkbox label="监理单位"></checkbox>
                        <checkbox label="签章单位"></checkbox>
                        <checkbox label="外协单位"></checkbox>
                        <checkbox label="档案管理单位"></checkbox>
                    </checkbox-group>
                </form-item>
                <form-item label="备注：">
                    <i-input v-model="formValidate.mateDesc" type="textarea" :autosize="{minRows: 2,maxRows: 2}"></i-input>
                </form-item>
                <form-item class="general-edit-page-bottom-wrap">
                    <i-button type="primary" @click="saveEditMate('formValidate','add')"> 新  增 </i-button>
                    <i-button type="primary" @click="saveEditMate('formValidate','update')" style="margin-left: 8px"> 修  改 </i-button>
                    <i-button type="ghost" @click="close()" style="margin-left: 8px"> 关  闭 </i-button>
                </form-item>
            </i-form>
        </card>
        <i-table stripe border :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
    </div>
    <script>
        var app=new Vue({
            el:"#app",
            mounted:function () {
                this.reloadData();
            },
            data:{
                current_selected_row:null,
                table_data:[],
                columns_config: [
                    {
                        title: '材料编码',
                        key: 'mateValue'
                    },{
                        title: '材料名称',
                        key: 'mateName'
                    },{
                        title: '归档单位',
                        key: 'unitType'
                    },{
                        title: '是否目录',
                        key: 'isdirectory',
                        render: function (h, params) {
                            return B4D.ListPageUtility.IViewTableRenderer.ToYesNoEnable(h,params.row.isdirectory);
                        }
                    },{
                        title: '操作',
                        key: 'mateSid',
                        width:120,
                        render:function (h, params) {
                            return h('div',{class:"list-row-button-wrap"},[
                                h('div', {
                                    class:"list-row-button list-row-button-edit",
                                    on: {
                                        click: function () {
                                            app.beginEditMate(params.row.mateSid);
                                        }
                                    }
                                }),
                                h('div', {
                                    class:"list-row-button list-row-button-del",
                                    on: {
                                        click: function () {
                                            app.delMate(params.row.mateSid);
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                engName:'${eng_entity.engName}',
                formValidate: {
                    mateSid: '',
                    engSid:'${eng_entity.engSid}',
                    engValue: '${eng_entity.engValue}',
                    mateValue: '',
                    mateName: '',
                    unitTypeArray: ['建设单位'],
                    unitType:"",
                    mateNeed:0,
                    mateCreateTime:'',
                    mateOrder:'',
                    mateStatus:1,
                    mateType:'',
                    mateDesc:'',
                    isdirectory:0
                },
                ruleValidate: {
                    mateValue: [
                        { required: true, message: '【材料编码】不能空！', trigger: 'blur' }
                    ],
                    mateName: [
                        { required: true, message: '【材料名称】不能空！', trigger: 'blur' }
                    ]
                }
            },
            methods:{
                currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                    this.current_selected_row=currentRow;
                },
                reloadData:function () {
                    var url='/project/system/engconf/listmatedata.do';
                    var _self=this;
                    var senddata={
                        engSid:"${eng_entity.engSid}"
                    }
                    AjaxUtility.Post(url, senddata , function (result) {
                        if (result.success) {
                            _self.table_data = new Array();
                            _self.table_data = result.data;
                        }
                    },"json");
                },
                getMateFromTableData:function (mateSid) {
                    if(this.table_data!=null&&this.table_data.length>0){
                        for(var i=0;i<this.table_data.length;i++){
                            if(this.table_data[i].mateSid==mateSid){
                                return this.table_data[i];
                            }
                        }
                    }
                    return null;
                },
                saveEditMate:function (name,type) {
                    var _self=this;
                    this.$refs[name].validate(function (valid) {
                        if (valid) {
                            if(type=="add") {
                                _self.formValidate.mateSid = "";
                            }
                            _self.formValidate.unitType=_self.formValidate.unitTypeArray.join(";");
                            var sendData=JSON.stringify(_self.formValidate);
                            var url='/project/system/engconf/savemateedit.do';
                            AjaxUtility.PostRequestBody(url,sendData,function (result) {
                                if(result.success) {
                                    _self.reloadData();
                                    _self.formValidate.mateSid="";
                                    _self.formValidate.mateValue="";
                                    _self.formValidate.mateName="";
                                }
                                else{
                                    alert(result.message);
                                }
                            },"json");
                        } else {
                            this.$Message.error('Fail!');
                        }
                    })
                },
                close:function () {
                    DialogUtility.Frame_CloseDialog(window);
                },
                beginEditMate:function (mateSid) {
                    var mateSingleData=this.getMateFromTableData(mateSid);
                    this.formValidate=mateSingleData;
                    this.formValidate.unitTypeArray=mateSingleData.unitType.split(";");
                },
                delMate:function (mateSid) {
                    var url = '/project/system/engconf/delmatedata.do';
                    var _self = this;
                    AjaxUtility.Post(url, {mateSid: mateSid}, function (result) {
                        if (result.success) {
                            _self.reloadData();
                        }
                    }, "json");
                }
            }
        })
    </script>
</body>
</html>
