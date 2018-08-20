<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/10
  Time: 23:57
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
    <%@ include file="/WEB-INF/Views/TagLibs/ZTreeLib.jsp" %>
</head>
<body>
    <div id="app">
        <div style="height: 480px;overflow: auto" class="scroll-div-wrap">
            <ul id="zTreeObj" class="ztree"></ul>
        </div>
        <div style="margin-top: 20px;text-align: center">
            <i-button type="primary" @click="handleSubmit()"> 保  存 </i-button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <i-button type="ghost" @click="close()" style="margin-left: 8px"> 关  闭 </i-button>
        </div>
    </div>
    <script>
        var setting = {
            check: {
                enable: true
            },
            data: {
                key:{
                    name:"title"
                },
                simpleData: {
                    enable: true,
                    idKey : "id", // id编号命名
                    pIdKey : "parentId",  // 父id编号命名
                    rootId : 0
                }
            }
        };

        var app=new Vue({
            el:"#app",
            mounted:function () {
                var url='/project/system/menu/allmenutoztreetitleincludeurl.do';
                //var _self=this;
                AjaxUtility.Post(url, {roleId: B4D.StringUtility.QueryString("sId")} , function (result) {
                    if (result.success) {
                        //debugger;
                        for(var i=0;i<result.data.length;i++){
                            result.data[i].open=true;
                        }
                        $.fn.zTree.init($("#zTreeObj"), setting, result.data);
                    }
                },"json");
            },
            data: {
            },
            methods:{
                handleSubmit:function () {
                    var zTree = $.fn.zTree.getZTreeObj("zTreeObj")
                    var nodes=zTree.getCheckedNodes();
                    if(nodes!=null&&nodes.length>0) {
                        var menuIdArray = new Array();
                        for (var i = 0; i < nodes.length; i++) {
                            menuIdArray.push(nodes[i].id);
                        }
                        var senddata = {
                            roleId: B4D.StringUtility.QueryString("sId"),
                            menuIds: menuIdArray.join(";")
                        }
                        var url='/project/system/role/saverolemenupriv.do';
                        AjaxUtility.Post(url, senddata , function (result) {
                            if (result.success) {
                                B4D.DialogUtility.Alert(window, B4D.DialogUtility.DialogAlertId, {}, result.message, function () {
                                    DialogUtility.Frame_CloseDialog(window);
                                });
                            }
                        },"json");
                    }
                    else {
                        B4D.DialogUtility.Alert(window, B4D.DialogUtility.DialogAlertId, {}, "请选中相关的权限!", null);
                    }
                    //var checkedNodes=this.$refs.treeObj.getCheckedNodes();
                    //debugger;
                },
                close:function () {
                    
                }
            }
        });
    </script>
</body>
</html>
