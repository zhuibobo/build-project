<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/10
  Time: 22:47
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
        <transfer
                :data="leftData"
                <%--:target-keys="targetKeys3"--%>
                :list-style="{width: '340px',height:'490px'}"
                :target-keys="targetKeys"
                filterable
                :titles="titles"
                @on-change="leftToRight" style="margin-left: 13px">
            <div :style="{float: 'right', margin: '5px'}">
                <i-button type="ghost" size="small">Refresh</i-button>
            </div>
        </transfer>
        <div style="margin-top: 10px;text-align: center">
        <i-button type="primary" @click="handleSubmit()"> 保  存 </i-button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关  闭 </i-button>
        </div>
    </div>
    <script>
        var app=new Vue({
            el:"#app",
            mounted:function () {
                var _self=this;
                var url='/project/system/user/getalluserforiviewtransfer.do';
                B4D.AjaxUtility.Post(url,{},function (result) {
                    if (result.success) {
                        _self.leftData = result.data;

                        var url='/project/system/user/getrolemember.do?roleId='+B4D.StringUtility.QueryString("sId");
                        B4D.AjaxUtility.Post(url,{},function (result) {
                            if (result.success) {
                                //debugger;
                                _self.targetKeys = result.data;

                            }
                        },"json");

                    }
                },"json");
            },
            data: {
                leftData: [],
                targetKeys:[]
            },
            computed:{
                titles: function () {
                    return ['可选人员','当前角色中的人员'];
                }
            },
            methods:{
                leftToRight:function (newTargetKeys) {
                    this.targetKeys = newTargetKeys;
                },
                handleSubmit:function () {
                    var _self=this;
                    var url='/project/system/user/bindusertorole.do';
                    var sendData={
                        roleId:B4D.StringUtility.QueryString("sId"),
                        userIds:this.targetKeys.join(";")
                    };
                    debugger;
                    B4D.AjaxUtility.Post(url,sendData,function (result) {
                        if (result.success) {
                            DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},result.message,function () {
                                window.OpenerWindowObj.app.reloadData();
                                DialogUtility.Frame_CloseDialog(window);
                            });
                        }
                    },"json");
                },
                close:function () {
                    
                },
                handleClose: function (name) {
                    DialogUtility.Frame_CloseDialog(window);
                }
            }
        });
    </script>
</body>
</html>
