<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/18
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=INYwE1UwUBifu662B5430kWk"></script>
    <title>地图展示</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(113.954291,22.546112);
    map.centerAndZoom(point, 14);
    map.enableScrollWheelZoom();
    map.enableInertialDragging();

    map.enableContinuousZoom();

    var size = new BMap.Size(10, 20);
    map.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size
        // 切换城市之间事件
        // onChangeBefore: function(){
        //    alert('before');
        // },
        // 切换城市之后事件
        // onChangeAfter:function(){
        //   alert('after');
        // }
    }));

    map.addEventListener("click",function(e){
        var url="/project/mapobject/newobjmark.do";
        AjaxUtility.Post(url,{
            bindto:"eng",
            bindtoid:StringUtility.QueryString("sId"),
            LAT:e.point.lat,
            LNG:e.point.lng,
            DISTEXT:decodeURIComponent(StringUtility.QueryString("engName"))
        },function (result) {
            //debugger;
            if(result.success) {
                alert("设置标识成功！")
                window.location.href=window.location.href;
            }
            else {
                alert("设置标识失败！")
            }
        },"json");

        //prompt("鼠标单击地方的经纬度为：",e.point.lng + "," + e.point.lat);
    });

    $(function () {
        var url="/project/mapobject/getobjmark.do";
        AjaxUtility.Post(url,{
            bindto:"eng",
            bindtoid:StringUtility.QueryString("sId")
        },function (result) {
            if(result.success) {
                //alert("设置标识成功！")
                //window.location.href=window.location.href;
                if(result.message=="suc"){
                    //console.log(result);
                    var point = new BMap.Point(result.data.lng,result.data.lat);
                    map.centerAndZoom(point, 14);
                    var marker = new BMap.Marker(point);  // 创建标注
                    map.addOverlay(marker);              // 将标注添加到地图中

                    var label = new BMap.Label(result.data.distext,{offset:new BMap.Size(20,-10)});
                    marker.setLabel(label);
                }
            }
            else {
                alert("获取标识异常！")
            }
        },"json");
    })
</script>
