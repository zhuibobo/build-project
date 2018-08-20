<%--
  Created by IntelliJ IDEA.
  User: zhuibobo,liwl
  Date: 2018/4/27
  Time: 22:04
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
	<div class="list-simple-search-wrap">
		<table class="ls-table">
			<colgroup>
				<col style="width: 80px">
				<col style="">
				<col style="width: 80px">
				<col style="">
				<col style="width: 100px">
				<col style="">
				<col style="width: 80px">
			</colgroup>
			<tr class="ls-table-row">
				<td>单位名称：</td>
				<td>
					<i-input v-model="search_condition.orgName.value" placeholder="请输入单位名称"></i-input>
				</td>
				<td>用户名称：</td>
				<td>
					<i-input v-model="search_condition.userUsername.value" placeholder="请输入用户名称"></i-input>
				</td>
				<td>登录账号：</td>
				<td>
					<i-input v-model="search_condition.userAccount.value" placeholder="请输入登录账号"></i-input>
				</td>
				<td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
			</tr>
		</table>
	</div>
	<div style="width: 100%">
		<div style="float: right">
			<i-button type="primary" @click="addItem"><Icon type="plus"></Icon> 新增 </i-button>
			<i-button type="primary" @click="editItem"><Icon type="edit"></Icon> 修改 </i-button>
			<i-button type="primary" @click="deleteItem"><Icon type="trash-a"></Icon> 删除 </i-button>
			<i-button type="primary" @click="viewItem"><Icon type="android-open"></Icon> 浏览 </i-button>
			<i-button type="primary" @click="resetPwd"><Icon type="arrow-return-right"></Icon> 重置密码 </i-button>
		</div>
		<div style="clear: both"></div>
	</div>
	<i-table stripe border :loading="loading"  :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
	<div style="float: right;">
		<page @on-change="changePage" :current.sync="page_num" :page-size="page_size" show-total  :total="page_total"></page>
	</div>
</div>
<script>
    var app=new Vue({
        el:"#app",
        mounted:function () {
            this.reloadData();
        },
        data:{
            columns_config: [
                {
                    title: '单位类别',
                    key: 'proOrgan.orgType',
                    render: function (h,params) {
                        return h('span',params.row.proOrgan.orgType);
                    }
                },
                {
                    title: '单位名称',
                    key: 'proOrgan.orgName',
                    render: function (h,params) {
                        return h('span',params.row.proOrgan.orgName);
                    }
                },
                {
                    title: '组织机构代码',
                    key: 'proOrgan.orgCode',
                    render: function (h,params) {
                        return h('span',params.row.proOrgan.orgCode);
                    }
                },
                {
                    title: '登录账号',
                    key: 'userAccount'
                },
                {
                    title: '用户名称',
                    key: 'userUsername'
                },
                {
                    title: '个人电话',
                    key: 'userPhone'
                },
                {
                    title: '个人手机',
                    key: 'userMobile'
                },
                {
                    title: '是否有效',
                    key: 'userStatus',
                    render: function (h,params) {
                        return h('span',params.row.userStatus==1?"有效":"无效");
                    }
                },
                {
                    title: '操作',
                    key: 'orgSid',
                    width:120,
                    render:function (h, params) {
                        return h('div',{class:"list-row-button-wrap"},[
                            h('div', {
                                class:"list-row-button list-row-button-view",
                                on: {
                                    click: function () {
                                        app.viewData(params.row.userSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-edit",
                                on: {
                                    click: function () {
                                        app.editData(params.row.userSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-del",
                                on: {
                                    click: function () {
                                        app.deleteData(params.row.userSid);
                                    }
                                }
                            })
                        ]);
                    }
                }
            ],
            table_data: [],
            current_selected_row:null,
            page_total:0,
            page_size:5,
            page_num:1,
            loading:false,
            search_condition:{
                orgName:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                userAccount:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                userUsername:{
                    value:"",
                    type:SearchUtility.SearchFieldType.StringType
                },
                userType:{
                    value:decodeURIComponent(StringUtility.QueryString("userType")),
                    type:SearchUtility.SearchFieldType.StringType
				}
            }
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
			editData:function(id){
                var url=BaseUtility.BuildUrl("/project/system/user/usereidt.do?userSid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"用户维护"},1);
			},
			deleteData:function(id){
                var _self=this;
                DialogUtility.Comfirm(window,"确认要删除当前用户吗？",function(){
                    var url="/project/system/user/deluser.do?userSid="+id;
                    AjaxUtility.Post(url,null,function (result) {
                        //debugger;
                        if(result.success) {
                            DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                                _self.reloadData();
                            });
                        }
                    },"json");
                });
			},
		    viewData:function(id){
                var url=BaseUtility.BuildUrl("/project/system/user/usereidt.do?op=view&userSid="+id);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"用户维护"},1);
			},
            reloadData:function () {
                var url='/project/system/user/getuserlistdata4build.do?userType='+StringUtility.QueryString("userType");
                var _self=this;
                _self.loading=true;
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
            addItem:function () {
                var url=BaseUtility.BuildUrl("/project/system/user/usereidt.do?userType="+StringUtility.QueryString("userType"));
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"用户维护"},1);
            },
            editItem:function () {
              	this.editData(this.current_selected_row.userSid);
            },
            deleteItem:function () {
                this.deleteData(this.current_selected_row.userSid);
            },
            resetPwd:function () {
                var _self=this;
                DialogUtility.Comfirm(window,"确认要重置当前用户密码吗？",function(){
                    var url="/project/system/user/resetpwd.do?userSid="+_self.current_selected_row.userSid;
                    AjaxUtility.Post(url,null,function (result) {
                        //debugger;
                        if(result.success) {
                            DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                                _self.reloadData();
                            });
                        }
                    },"json");
                });
            },
            viewItem:function () {
                this.viewData(this.current_selected_row.userSid);
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
