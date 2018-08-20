<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/15
  Time: 21:55
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
    <link rel="stylesheet" type="text/css" href="${ctxpath}/PageDesign/DYJSCZT/css/css.css"/>
    <style>
        .daibananniu{
            margin-bottom: 20px;
        }
    </style>
    <script>
        function processList(s,title)
        {
            var url=BaseUtility.BuildUrl("/project/engineering/listfordesktop.do?status="+s);
            DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:title,width:1024,height:600});
        }
    </script>
</head>
<body>
    <div class="neirong" id="app">
        <div class="neirongyou">
            <div class="gongneng">
                <div class="biaotida">
                    <div class="lanshuxian"></div>
                    <div class="biaoti">业务流程</div>
                </div>
                <div class="gongnengtubiao"></div>
                <div class="jiantou"></div>
                <div class="liucheng">
                    <div class="yvyanshou"></div>
                    <div style="display:inline-block; float:left;">
                        <div class="yvshenzi">预验收检查</div>
                        <div class="yvshenxian"></div>
                        <div class="yvshenxiaozi">·预验收审核</div>
                    </div>
                </div>

                <div class="jiantou"></div>
                <div class="liucheng">
                    <div class="zhenglizujuan"></div>
                    <div style="display:inline-block; float:left;">
                        <div class="yvshenzi">工程组卷</div>
                        <div class="yvshenxian"></div>
                        <div class="yvshenxiaozi">·案卷审核</div>
                    </div>
                </div>

                <div class="jiantou"></div>
                <div class="liucheng">
                    <div class="danganjieshou"></div>
                    <div style="display:inline-block; float:left;">
                        <div class="yvshenzi">档案报送</div>
                        <div class="yvshenxian"></div>
                        <div class="yvshenxiaozi">·接受审核</div>
                    </div>
                </div>

                <div class="jiantou"></div>
                <div class="liucheng">
                    <div class="danganguidang"></div>
                    <div style="display:inline-block; float:left;">
                        <div class="yvshenzi">档案移交</div>
                        <div class="yvshenxian"></div>
                        <div class="yvshenxiaozi">·档案归档</div>
                    </div>
                </div>

                <div class="jiantou"></div>
                <div class="gongnengtubiao2"></div>
            </div>

            <div class="dairenwu">
                <div class="biaotixia">
                    <div class="lanshuxian"></div>
                    <div class="biaoti">我的待办任务</div>
                </div>
                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti">报建确认</div>
                        <div class="daibanshuju">{{wait[1]}}</div>
                        <div class="ge">个</div>
                        <div class="daibantubiao"></div>
                        <div class="jindu">
                            <div class="wanchengjindu"></div>
                        </div>
                        <div class="yiwancheng">已完成:{{done[0]}}个</div>
                    </div>
                    <div class="daibananniu" style="cursor:pointer" onclick="processList(1,'报建确认')">立即处理</div>
                </div>

                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti" style="color:#ff9560;">预验收</div>
                        <div class="daibanshuju" style="color:#ff9560;">{{wait[4]}}</div>
                        <div class="ge" style="color:#ff9560;">个</div>
                        <div class="yvyanshoutubiao"></div>
                        <div class="jindu" style="background:#fec6a9;">
                            <div class="wanchengjindu" style="background:#ff9560;"></div>
                        </div>
                        <div class="yiwancheng">已完成:{{done[1]}}个</div>
                    </div>
                    <div class="daibananniu" style="background:#ff9560;cursor:pointer" onclick="processList(4,'预验收指导审核')">立即处理</div>
                </div>

                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti" style="color:#4ec3bb;">整理组卷</div>
                        <div class="daibanshuju" style="color:#4ec3bb;">{{wait[7]}}</div>
                        <div class="ge" style="color:#4ec3bb;">个</div>
                        <div class="zhenglitubiao"></div>
                        <div class="jindu" style="background:#9eede8;">
                            <div class="wanchengjindu" style="background:#0f9087;"></div>
                        </div>
                        <div class="yiwancheng">已完成:{{done[2]}}个</div>
                    </div>
                    <div class="daibananniu" style="background:#4ec3bb;cursor:pointer" onclick="processList(7,'整理组卷审核')">立即处理</div>
                </div>

                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti" style="color:#f67664;">档案接收</div>
                        <div class="daibanshuju" style="color:#f67664;">{{wait[13]}}</div>
                        <div class="ge" style="color:#f67664;">个</div>
                        <div class="jieshoutubiao"></div>
                        <div class="jindu" style="background:#f4c3bd;">
                            <div class="wanchengjindu" style="background:#cf331d;"></div>
                        </div>
                        <div class="yiwancheng">已完成:{{done[3]}}个</div>
                    </div>
                    <div class="daibananniu" style="background:#f67664;cursor:pointer" onclick="processList(13,'档案接收审核')">立即处理</div>
                </div>

                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti" style=" color:#d64ef9;">档案归档</div>
                        <div class="daibanshuju" style=" color:#d64ef9;">{{wait[14]}}</div>
                        <div class="ge" style=" color:#d64ef9;">个</div>
                        <div class="guidangtubiao" style="top: 720px;"></div>
                        <div class="jindu" style=" background:#ebc2f5;" ><div class="wanchengjindu" style=" background:#d64ef9;"></div></div>
                        <div class="yiwancheng">已完成:{{done[4]}}个</div>
                    </div>
                    <div class="daibananniu" style="background:#d64ef9;cursor:pointer" onclick="processList(14,'档案接收审核')">立即处理</div>
                </div>

                <div class="baojian">
                    <div class="daibanrenwu">
                        <div class="baojianbiaoti" style="color:#f1c832;">审定发证</div>
                        <div class="daibanshuju" style="color:#f1c832;">{{wait[17]}}</div>
                        <div class="ge" style="color:#f1c832;">个</div>
                        <div class="fazhengtubiao" style="top: 720px;"></div>
                        <div class="jindu" style="background:#e5d8b3;"><div class="wanchengjindu" style="background:#f1c832;"></div></div>
                        <div class="yiwancheng">已完成:{{done[5]}}个</div>
                    </div>
                    <div class="daibananniu" style="background:#e3ac15;cursor:pointer" onclick="processList(17,'档案接收审核')">立即处理</div>
                </div>
            </div>


        </div>
    </div>
</body>
</html>

<script type="text/javascript">
    var app=new Vue({
        el:"#app",
        data:function(){
            var data=${statusData};
            var wait=[];
            var done=[0,0,0,0,0,0];
            for(var i=0;i<22;i++)
            {
                var t=0;
                for(var j=0;j<data.length;j++)
                {
                    if(i==data[j].STATUS)
                    {
                        t=data[j].TOTAL;
                        break;
                    }
                }
                if(i>=2 && i!=3)
                    done[0]+=t;
                if(i>=5 && i!=6)
                    done[1]+=t;
                if(i>=8 && i!=9)
                    done[2]+=t;
                if(i>14 && i!=15)
                    done[3]+=t;
                if(i>=17 && i!=18)
                    done[4]+=t;
                if(i==20)
                    done[5]+=t;
                wait[i]=t;
            }
            return {
                wait:wait,
                done:done
            };
        }
    });
</script>

