<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/27
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>视频播放</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/Video/swfobject.js"></script>
</head>
<body>
<div id="app">
    <div style="width: 700px; height: 500px; border: 1px solid #D5E6EE" id="voidePlay">
    </div>
    <input type="hidden" id="videoPath" value="${arolFileInfoEntity.filepath}">
</div>
</body>
</html>
<script type="text/javascript">
    var videoUrl = "${ctxpath}/project/engineering/catalogfile/downfile.do?path="+encodeURIComponent($("#videoPath").val());
    var img = videoUrl.replace(/^\s+|\s+$/i, "").replace(/\.[a-zA-Z]+/i, ".jpg");
    var so = new SWFObject("${ctxpath}/Js/Video/CuPlayerMiniV20_Black_S.swf", "voidePlay", "100%", "451", "9", "#000000");
    so.addParam("allowfullscreen", "true");
    so.addParam("allowscriptaccess", "always");
    so.addParam("wmode", "opaque");
    so.addParam("quality", "high");
    so.addParam("salign", "lt");
    so.addVariable("CuPlayerFile", videoUrl);
    so.addVariable("CuPlayerImage", img);
    so.addVariable("CuPlayerShowImage", "true");
    so.addVariable("CuPlayerWidth", "514");
    so.addVariable("CuPlayerHeight", "451");
    so.addVariable("CuPlayerAutoPlay", "true");
    so.addVariable("CuPlayerAutoRepeat", "false");
    so.addVariable("CuPlayerShowControl", "true");
    so.addVariable("CuPlayerAutoHideControl", "false");
    so.addVariable("CuPlayerAutoHideTime", "6");
    so.addVariable("CuPlayerVolume", "80");
    so.write("voidePlay");
</script>