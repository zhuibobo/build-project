<%--
  Created by IntelliJ IDEA.
  User: BBHome
  Date: 2018/7/7
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>登录页</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script>
        $(function () {
            B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"请重新登陆系统",function () {
                BaseUtility.RedirectToLogin();
            });
        })
    </script>
</head>
<body>
    Session过期，请重新登陆
</body>
</html>
