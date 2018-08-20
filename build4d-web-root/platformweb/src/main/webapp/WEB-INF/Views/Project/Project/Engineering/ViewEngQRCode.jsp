<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/13
  Time: 16:54
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
    <script>
        $(function () {
            var isBuild=${isBuild};
            if(isBuild){
                var url="${url}";
                $("#imgQR").attr("src","${ctxpath}/project/imagegeneral/buildurlqrcode.do?Url="+encodeURIComponent(url));
            }
            else
            {
                DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},"抱歉, 请在工程信息【预验收审核通过】后再生成！",function () {
                    DialogUtility.Frame_CloseDialog(window);
                });
            }
        })
    </script>
</head>
<body>
    <img id="imgQR" src="" />
</body>
</html>
