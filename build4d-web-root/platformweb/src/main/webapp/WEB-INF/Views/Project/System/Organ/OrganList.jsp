<%--
  Created by IntelliJ IDEA.
  User: zhuibobo, liwl
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
					<col style="">
					<col style="width: 80px">
				</colgroup>
				<tr class="ls-table-row">
					<td>机构名称：</td>
					<td>
						<i-input v-model="search_condition.orgName.value" placeholder="请输入机构名称"></i-input>
					</td>
					<td>机构代码：</td>
					<td>
						<i-input v-model="search_condition.orgCode.value" placeholder="请输入机构代码"></i-input>
					</td>
					<td>创建时间（从）：</td>
					<td>
						<date-picker v-model="search_condition.orgCreateTime_s.value" type="date" placeholder="Select date" style="width: 100%"></date-picker>
					</td>
					<td>（到）：</td>
					<td>
						<date-picker v-model="search_condition.orgCreateTime_e.value" type="date" placeholder="Select date" style="width: 100%"></date-picker>
					</td>
					<td><i-button type="primary" @click="search"><Icon type="android-search"></Icon> 查询 </i-button></td>
				</tr>
			</table>
		</div>
		<div style="width: 100%">
			<div style="float: right">
				<i-button type="primary" @click="addOrgan()"><Icon type="plus"></Icon> 新增 </i-button>
				<i-button type="primary" @click="editOrgan()"><Icon type="edit"></Icon> 修改 </i-button>
				<i-button type="primary" @click="deleteOrgan"><Icon type="trash-a"></Icon> 删除 </i-button>
				<i-button type="primary" @click="viewOrgan"><Icon type="android-open"></Icon> 浏览 </i-button>
			</div>
			<div style="clear: both"></div>
		</div>
		<i-table stripe border :loading="loading" :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
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
						title: '机构名称',
						key: 'orgName'
					},
					{
						title: '组织机构代码',
						key: 'orgCode'
					},
					{
						title: '机构类型',
						key: 'orgType'
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
                                            app.viewData(params.row.orgSid);
                                        }
                                    }
                                }),
                                h('div', {
                                    class:"list-row-button list-row-button-edit",
                                    on: {
                                        click: function () {
                                            app.editData(params.row.orgSid);
                                        }
                                    }
                                }),
                                h('div', {
                                    class:"list-row-button list-row-button-del",
                                    on: {
                                        click: function () {
                                            app.deleteData(params.row.orgSid);
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
                page_size:10,
				page_num:1,
                loading:false,
				search_condition:{
                    orgName:{
                        value:"",
                        type:SearchUtility.SearchFieldType.StringType
                    },
                    orgCode:{
                        value:"",
                        type:SearchUtility.SearchFieldType.StringType
                    },
                    orgCreateTime_s:{
                        value:"",
                        type:SearchUtility.SearchFieldType.DataStringType
                    },
                    orgCreateTime_e:{
                        value:"",
                        type:SearchUtility.SearchFieldType.DataStringType
                    }
				}
			},
			methods:{
                currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
					this.current_selected_row=currentRow;
                },
                deleteData:function(id){
                    var _self=this;
                    DialogUtility.Comfirm(window,"确认要删除当前组织吗？",function(){
                        var url="/project/system/organ/delorgan.do?orgSid="+id;
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
                editData:function(id){
                    var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do?orgSid="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
				},
                viewData:function(id){
                    var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do?op=view&orgSid="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
                },
			    reloadData:function () {
                    var url='/project/system/organ/getorganlistdata.do';
                    var _self=this;
                    _self.loading=true;
                    //debugger;
                    var senddata={
                        page_num:_self.page_num,
                        page_size:_self.page_size,
                        search_condition:JSON.stringify(_self.search_condition)
					}
                    AjaxUtility.Post(url, senddata , function (result) {
                        if (result.success) {
                            _self.table_data = new Array();
                            _self.table_data = result.data.list;
                            _self.page_total = result.data.total;
                        }
                        _self.loading=false;
                    },"json");
                },
				addOrgan:function () {
				    var url=BaseUtility.BuildUrl("/project/system/organ/organeidt.do");
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"机构维护"},2);
                },
				editOrgan:function () {
                    this.editData(this.current_selected_row.orgSid);
                },
				deleteOrgan:function () {
                    this.deleteData(this.current_selected_row.orgSid);
                },
				viewOrgan:function () {
                    this.viewData(this.current_selected_row.orgSid);
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
