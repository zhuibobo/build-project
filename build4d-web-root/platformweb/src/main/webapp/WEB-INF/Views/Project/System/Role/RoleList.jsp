<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
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
	<div style="width: 100%" id="list-button-wrap">
		<div style="float: right">
			<i-button type="primary" @click="add()"><Icon type="plus"></Icon> 新增 </i-button>
			<i-button type="primary" @click="edit()"><Icon type="edit"></Icon> 修改 </i-button>
			<i-button type="primary" @click="del()"><Icon type="trash-a"></Icon> 删除 </i-button>
			<i-button type="primary" @click="view()"><Icon type="android-open"></Icon> 浏览 </i-button>
			<i-button type="primary" @click="roleMember()"><Icon type="android-open"></Icon> 角色成员 </i-button>
			<i-button type="primary" @click="viewAuth()"><Icon type="android-open"></Icon> 角色权限 </i-button>
			<i-button type="primary" @click="moveUp()"><Icon type="android-open"></Icon> 上移 </i-button>
			<i-button type="primary" @click="moveDown()"><Icon type="android-open"></Icon> 下移 </i-button>
			<i-button type="primary" @click="roleEnable()"><Icon type="android-open"></Icon> 启用 </i-button>
			<i-button type="primary" @click="roleDisable()"><Icon type="android-open"></Icon> 禁用 </i-button>
		</div>
		<div style="clear: both"></div>
	</div>
	<i-table :height="list_height" stripe border :columns="columns_config" :data="table_data" :style="{marginTop:'10px',marginBottom:'10px'}" :highlight-row="true" @on-current-change="currentSelectedSingleRow"></i-table>
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
                    title: '角色名称',
                    key: 'roleName'
                },{
                    title: '创建时间',
                    key: 'roleCreateTime',
                    render: function (h, params) {
                        return B4D.ListPageUtility.IViewTableRenderer.ToDateYYYY_MM_DD(h,params.row.roleCreateTime);
                    }
                },{
                    title: '角色类型',
                    key: 'roleType'
                },{
                    title: '状态',
                    key: 'roleStatus',
					render: function (h, params) {
                        return B4D.ListPageUtility.IViewTableRenderer.ToStatusEnable(h,params.row.roleStatus);
                    }
                },{
                    title: '操作',
                    key: 'orgSid',
                    width:120,
                    render:function (h, params) {
                        return h('div',{class:"list-row-button-wrap"},[
                            h('div', {
                                class:"list-row-button list-row-button-view",
                                on: {
                                    click: function () {
                                        app.view(params.row.roleSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-edit",
                                on: {
                                    click: function () {
                                        app.edit(params.row.roleSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-del",
                                on: {
                                    click: function () {
                                        app.del(params.row.roleSid);
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
            list_height:B4D.ListPageUtility.GetGeneralPageHeight(B4D.ListPageUtility.GetFixHeightNotSearch())
        },
        methods:{
            currentSelectedSingleRow:function (currentRow,oldCurrentRow) {
                this.current_selected_row=currentRow;
            },
            reloadData:function () {
                var url='/project/system/role/listdata.do';
                var _self=this;
                //debugger;
                var senddata={
                    page_num:_self.page_num,
                    page_size:_self.page_size
                }
                AjaxUtility.Post(url, senddata , function (result) {
                    if (result.success) {
                        _self.table_data = new Array();
                        _self.table_data = result.data.list;
                        _self.page_total = result.data.total;
                    }
                },"json");
            },
            makingSureId:function (id) {
                //debugger;
                if(!id&&this.current_selected_row!=null) {
                    id = this.current_selected_row.roleSid
                }
                if(!id) {
                    B4D.DialogUtility.Alert(window, B4D.DialogUtility.DialogAlertId, {}, "请选中需要操作的行!", null);
                    return {
                        then:function (func) {
                        }
                    }
                }
                return {
                    then:function (func) {
						func(id);
                	}
                }
            },
            add:function () {
                var url=BaseUtility.BuildUrl("/project/system/role/detail.do");
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"角色管理"},2);
            },
            edit:function (id) {
                this.makingSureId(id).then(function (id) {
                    var url=BaseUtility.BuildUrl("/project/system/role/detail.do?sId="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"角色管理"},2);
                });
            },
            del:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            view:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            roleMember:function (id) {
                this.makingSureId(id).then(function (id) {
                    var url=BaseUtility.BuildUrl("/project/system/role/rolemember.do?sId="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"角色成员管理"},2);
                });
            },
            viewAuth:function (id) {
                this.makingSureId(id).then(function (id) {
                    var url=BaseUtility.BuildUrl("/project/system/role/rolepriv.do?sId="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"角色权限管理"},2);
                });
            },
            moveUp:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            moveDown:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            roleEnable:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            roleDisable:function (id) {
                this.makingSureId(id).then(function (id) {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"未实现!",null);
                });
            },
            changePage:function(page){
                this.page_num=page;
                this.reloadData();
            }
        }
    });
</script>
</body>
</html>

