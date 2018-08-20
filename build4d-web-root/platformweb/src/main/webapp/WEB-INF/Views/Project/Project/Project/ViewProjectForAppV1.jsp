<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/13
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>城建档案综合管理信息系统</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
</head>
<body>
    <div id="app">
        <row style="background:#eee;padding:20px">
            <i-col span="24">
                <card :bordered="false">
                    <p slot="title">工程名称</p>
                    <p>${EngEnt.engName}</p>
                </card>
            </i-col>
        </row>
    </div>
    <script>
        var Main = {

        }

        var Component = Vue.extend(Main)
        new Component().$mount('#app')
    </script>
</body>
</html>
