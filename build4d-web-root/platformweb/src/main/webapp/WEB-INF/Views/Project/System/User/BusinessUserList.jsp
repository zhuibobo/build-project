<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
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
					<i-input placeholder="请输入单位名称"></i-input>
				</td>
				<td>用户名称：</td>
				<td>
					<i-input placeholder="请输入用户名称"></i-input>
				</td>
				<td>登录账号：</td>
				<td>
					<date-picker type="date" placeholder="Select date" style="width: 100%"></date-picker>
				</td>
				<td><i-button type="primary"><Icon type="android-search"></Icon> 查询 </i-button></td>
			</tr>
		</table>
	</div>
	<div style="width: 100%">
		<div style="float: right">
			<i-button type="primary" @click="add"><Icon type="plus"></Icon> 新增 </i-button>
			<i-button type="primary" @click="edit"><Icon type="edit"></Icon> 修改 </i-button>
			<i-button type="primary" @click="delete"><Icon type="trash-a"></Icon> 删除 </i-button>
			<i-button type="primary" @click="view"><Icon type="android-open"></Icon> 浏览 </i-button>
		</div>
		<div style="clear: both"></div>
	</div>
	<i-table border :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
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
                    key: 'proOrgan.orgType'
                },
                {
                    title: '单位名称',
                    key: 'proOrgan.orgName'
                },
                {
                    title: '组织机构代码',
                    key: 'proOrgan.orgCode'
                },
                {
                    title: '登录账号',
                    key: 'userAccount'
                },
                {
                    title: '用户名称',
                    key: 'userUserName'
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
                    key: 'userStatus'
                },
                {
                    title: '操作',
                    key: 'orgSid',
                    width:100,
                    render:function (h, params) {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'success',
                                    shape:"circle",
                                    icon:"ios-search",
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: function () {
                                        this.show(params.index)
                                    }
                                }
                            }),
                            h('Button', {
                                props: {
                                    type: 'success',
                                    shape:"circle",
                                    icon:"close",
                                    size: 'small'
                                },
                                on: {
                                    click: function () {
                                        this.show(params.index)
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
            search_condition:{
                orgName:{
                    value:"",
                    type:""
                },
                userAccount:{
                    value:"",
                    type:""
                },
                userUserName:{
                    value:"",
                    type:""
                }
            }
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            reloadData:function () {
                var url='/project/system/organ/getorganlistdata.do';
                var _self=this;
                var senddata={
                    page_num:_self.page_num,
                    page_size:_self.page_size,
                    search_condition:JSON.stringify(_self.search_condition)
                }
                AjaxUtility.Get(url, senddata , function (result) {
                    if (result.success) {
                        _self.table_data = new Array();
                        debugger;
                        _self.table_data = result.data.list;
                        _self.page_total = result.data.total;
                    }
                },"json");
            },
            add:function () {
                var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do");
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
            },
            edit:function () {
                //alert(this.current_selected_row.orgSid);
                var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do?orgSid="+this.current_selected_row.orgSid);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
            },
            delete:function () {
                var _self=this;
                DialogUtility.Comfirm(window,"确认要删除当前组织吗？",function(){
                    var url="/project/system/organ/delorgan.do?orgSid="+_self.current_selected_row.orgSid;
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
            view:function () {
                var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do?op=view&orgSid="+this.current_selected_row.orgSid);
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            },
            search:function () {
				
            }
        }
    });
</script>
</body>
</html>
