<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/23
  Time: 21:34
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
    <script type="text/javascript" src="${ctxpath}/Js/EngineeringFile.js?urltimestamp=${urlts}"></script>
</head>
<body>
<div id="app">
    <Alert type="warning" closable show-icon>注：文件每天只可以上传分项的15%，最少上传一条！</Alert>
    <div class="list-simple-search-wrap">
        <table class="ls-table">
            <colgroup>
                <col style="width: 80px">
                <col style="">
                <col style="width: 80px">
            </colgroup>
            <tr class="ls-table-row">
                <td>文件名称：</td>
                <td>
                    <i-input v-model="search_condition.filename.value" placeholder="请输入文件名称"></i-input>
                </td>
                <td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
            </tr>
        </table>
    </div>
    <div style="width: 100%">
        <div style="float: right">
            <i-button type="primary" v-if="!isLock" @click="uploadItem"><Icon type="upload"></Icon> 新增 </i-button>
            <i-button type="primary" v-if="!isLock" @click="delItem"><Icon type="trash-a"></Icon> 删除 </i-button>
        </div>
        <div style="clear: both"></div>
    </div>
    <i-table border height="500" :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
    <div style="float: right;">
        <page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
    </div>
    <div style="display: none;">
        <div id="fUpload"  >
            <upload ref="fUpload" :action="getURL()"  :before-upload="handleUpload" multiple :show-upload-list="false" :on-progress="uploadProgress" :on-success="uploadSuccess" :format="['jpg','flv','tif']" :on-format-error="uploadError">
                <i-button type="warning" :loading="uploadLoadingStatus" icon="ios-cloud-upload-outline">选择并上传文件</i-button>
            </upload>
        </div>
    </div>
</div>
<script>
    var arolFileInfoEntity=${arolFileInfoEntity};
    var app=new Vue({
        el:"#app",
        mounted:function () {
            this.reloadData();
        },
        data:{
            columns_config: [
                {
                    title: '文件名称',
                    key: 'filename'
                },
                {
                    title: '上传时间',
                    key: 'createdDt',
                    width:200,
                    render: function (h,params) {
                        return h("span",DateUtility.Format(new Date(params.row.createdDt),"yyyy-MM-dd"))
                    }
                },
                {
                    title: '上传人',
                    key: 'createdBy',
                    width:200
                },
                {
                    title: '查看',
                    key: 'sid',
                    width:100,
                    render:function (h, params) {
                        return h('div',{
                            class:"list-row-button list-row-button-view",
                            on: {
                                click: function () {
                                    app.viewFileItem(params.row);
                                }
                            }
                        });
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:8,
            page_num:1,
            loading:false,
            uploadLoadingStatus:false,
            arolFileInfoEntity:arolFileInfoEntity,
            search_condition:{
                filename:{
                    value:'',
                    type:SearchUtility.SearchFieldType.StringType
                },
                fileSid:{
                    value:arolFileInfoEntity.sid,
                    type:SearchUtility.SearchFieldType.IntType
                }
            }
        },
        computed:{
            isLock:function () {
                if(StringUtility.QueryString("op")=="view")
                    return true;
                else if(arolFileInfoEntity.lockStatus==null)
                    return false;
                else
                    return arolFileInfoEntity.lockStatus;
            }
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            delItem:function(){
                var _self = this;
                DialogUtility.Comfirm(window, "确认要删除当前文件吗？", function () {
                    var url = "/project/engineering/catalogfile/deluploadfile.do?sid=" + _self.current_selected_row.sid;
                    AjaxUtility.Post(url, null, function (result) {
                        DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                            _self.updateParentData(result.data);
                            _self.reloadData();
                        });
                    }, "json");
                });
            },
            getURL:function () {
                return "${ctxpath}/project/engineering/catalogfile/uploadCatalogfile.do?engSid="+arolFileInfoEntity.engSid+"&catalogSid="+arolFileInfoEntity.sid+"&filedescription=";
            },
            uploadItem:function(){
                DialogUtility.ShowHTML(window,'uploadfile',{title:"文件上传",height:230,width:300},$("#fUpload"),function () {
                });
            },
            handleUpload:function (file) {
                this.$refs.fUpload.post(file);
                return false;
            },
            uploadSuccess:function (result) {
                this.updateParentData(result.data);
                this.uploadLoadingStatus=false;
                this.reloadData();
            },
            uploadProgress:function () {
                this.uploadLoadingStatus=true;
            },
            uploadError:function(file) {
                DialogUtility.Alert(window, DialogUtility.DialogAlertId, {},"只支持 FLV、JPG 和 TIF 文件的上传！");
            },
            reloadData:function () {
                var _self=this;
                _self.loading=true;
                var url='/project/engineering/catalogfile/getuploadfilelistdata.do';
                var senddata={
                    page_num:_self.page_num,
                    page_size:_self.page_size,
                    search_condition:JSON.stringify(_self.search_condition)
                }
                AjaxUtility.Get(url, senddata , function (result) {
                    if (result.success) {
                        _self.table_data = new Array();
                        _self.table_data = result.data.list;
                        _self.page_total = result.data.total;
                    }
                    _self.loading=false;
                },"json");
            },
            updateParentData:function (fileEntity) {
                var d= window.OpenerWindowObj.app.table_data;
                var uploadPageNums=fileEntity.uploadPageNums;
                var status=fileEntity.status;
                $.each(d,function (k,v) {
                    if(v.sid==arolFileInfoEntity.sid)
                    {
                        //更新文件登记列表的目录数据
                        window.OpenerWindowObj.app.table_data[k].uploadPageNums=uploadPageNums;
                        window.OpenerWindowObj.app.table_data[k].status=status;
                        return false;
                    }
                });
            },
            saveItems:function () {

            },
            viewFileItem:function (row) {
                if(row.filetype.toLowerCase()=="flv")
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId04,
                        BaseUtility.BuildUrl("/project/engineering/catalogfile/videoplay.do?sid="+row.sid),
                        {title:row.filename+"【声像资料】",width:800,height:600});
                else
                    ProjectUtil.CommMethod.viewFile(row.filename+"【原图】",row.filepath);
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            },
            search:function () {
                this.page_num=1;
                this.reloadData();
            }
        }
    });
</script>
</body>
</html>
