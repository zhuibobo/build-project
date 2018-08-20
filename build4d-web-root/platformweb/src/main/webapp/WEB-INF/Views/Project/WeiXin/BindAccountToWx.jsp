<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/6/23
  Time: 19:54
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
<body style="text-align: center;">
    <p style=" font-size: 18px; margin-top: 20px; margin-bottom: 5px;">1：请先使用微信扫码关注公众号：</p>

    <img id="imgAttentionQR" src="" style="width: 230px;height: 230px; margin-bottom: 30px;" />

    <div style="background: #eaeaea; width: 100%; height: 1px; margin: 0 auto;"></div>
    <p style=" font-size: 18px; margin-top: 30px;">2：关注公众号后,请使用微信扫码，绑定微信账号：</p>

    <img id="imgBindAccountQR" src="" style="width: 260px;height: 260px" />
    <script>
        $(function () {
            createAttentionRQ();
            createBindAccountWXRQ();
        })
        function createAttentionRQ() {
            var self=this;
            var url="/project/weixinbindaccount/createAttentionRQUrl.do";
            AjaxUtility.Post(url,{
            },function (result) {
                if(result.success) {
                    self.message=result.message;
                    var json=StringUtility.StringToJson(self.message);
                    //alert(json.url);
                    $("#imgAttentionQR").attr("src","https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+json.ticket);
                }
                else {
                    self.message=result.message;
                }
            },"json");
        }
        function createBindAccountWXRQ() {
            var self=this;
            var url="/project/weixinbindaccount/createBindAccountRQUrl.do";
            AjaxUtility.Post(url,{
            },function (result) {
                if(result.success) {
                    self.message=result.message;
                    $("#imgBindAccountQR").attr("src","${ctxpath}/project/imagegeneral/buildurlqrcode.do?Url="+encodeURIComponent(self.message));
                }
                else {
                    self.message=result.message;
                }
            },"json");
        }
    </script>
</body>
</html>
