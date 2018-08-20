<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/26
  Time: 15:04
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
               /* {
                    title: '单位类别',
                    key: 'oorgType'
                },*/{
                    title: '单位名称',
                    key: 'oorgNameCn'
                },{
                    title: '组织机构代码',
                    key: 'oorgCode'
                },{
                    title: '联系人',
                    key: 'oorgContacts'
                },{
                    title: '手机',
                    key: 'oorgContMobile'
                },{
                    title: '登录帐号',
                    key: 'oorgAccount'
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
                                        app.view(params.row.oorgSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-edit",
                                on: {
                                    click: function () {
                                        app.edit(params.row.oorgSid);
                                    }
                                }
                            }),
                            h('div', {
                                class:"list-row-button list-row-button-del",
                                on: {
                                    click: function () {
                                        app.del(params.row.oorgSid);
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
                var url='/project/system/outerorgan/listdata.do?oorg_type=${oorg_type}';
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
                var url=BaseUtility.BuildUrl("/project/system/outerorgan/detail.do?oorg_type=${oorg_type}");
                DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"单位管理"},2);
            },
            edit:function (id) {
                this.makingSureId(id).then(function (id) {
                    var url=BaseUtility.BuildUrl("/project/system/outerorgan/detail.do?oorg_type=${oorg_type}&sId="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"单位管理"},2);
                });
            },
            del:function (id) {
                var _self=this;
                DialogUtility.ComfirmBy(window,"确认要删除当前组织吗？",{
                    okfunc:function(){
                        //alert("1");
                        _self.makingSureId(id).then(function (id) {
                            var url="/project/system/outerorgan/del.do?sId="+id;
                            AjaxUtility.Post(url,null,function (result) {
                                if(result.success) {
                                    DialogUtility.Alert(window, DialogUtility.DialogAlertId, {}, result.message, function () {
                                        _self.reloadData();
                                    });
                                }
                            },"json");
                        });
                    }
                });
            },
            view:function (id) {
                this.makingSureId(id).then(function (id) {
                    var url=BaseUtility.BuildUrl("/project/system/outerorgan/detail.do?oorg_type=${oorg_type}&sId="+id);
                    DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"单位管理"},2);
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