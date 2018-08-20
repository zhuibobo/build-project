<%--
  Created by IntelliJ IDEA.
  User: zhuangrb
  Date: 2018/4/15
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
</head>
<body>
    <div id="app">
        <i-button @click="show">Click me!</i-button>
        <Modal v-model="visible" title="Welcome">Welcome to iView</Modal>
    </div>
    <script>
        new Vue({
            el: '#app',
            data: {
                visible: false
            },
            methods: {
                show: function () {
                    this.visible = true;
                }
            }
        })
    </script>
</body>
</html>
