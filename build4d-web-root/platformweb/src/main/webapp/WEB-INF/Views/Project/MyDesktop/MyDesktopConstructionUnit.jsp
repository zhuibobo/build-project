<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/15
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctxpath}/PageDesign/JSJSKZT/css/css.css"/>
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
<div class="neirong"  id="app">
    <div class="neirongyou">
        <div class="gongneng">
            <div class="biaotida">
                <div class="lanshuxian"></div>
                <div class="biaoti">业务流程</div>
            </div>
            <div class="gongnengtubiao"></div>
            <div class="jiantou"></div>
            <div class="liucheng">
                <div class="gongchengzuce"></div>
                <div style="display:inline-block; float:left;">
                    <div class="yvshenzi">审核依据</div>
                    <div class="yvshenxian"></div>
                    <div class="yvshenxiaozi">·账号确认</div>
                </div>
            </div>
            <div class="jiantou"></div>
            <div class="liucheng">
                <div class="wenjiandengji"></div>
                <div style="display:inline-block; float:left;">
                    <div class="yvshenzi">文件填报</div>
                    <div class="yvshenxian"></div>
                    <div class="yvshenxiaozi">·业务指导</div>
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
        </div>
        <div class="dairenwu">
            <div class="biaotixia">
                <div class="lanshuxian"></div>
                <div class="biaoti">我的待办任务</div>
            </div>
            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti">文件登记</div>
                    <div class="daibanshuju">{{wait[2]}}</div>
                    <div class="ge">个</div>
                    <div class="dengjitubiao"></div>
                    <div class="jindu">
                        <div class="wanchengjindu"></div>
                    </div>
                    <div class="yiwancheng">已完成:{{done[0]}}个</div>
                </div>
                <div class="daibananniu" style="cursor:pointer" onclick="processList(2,'文件登记')">立即进入</div>
            </div>
            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti" style="color:#ff9560;">文件整理</div>
                    <div class="daibanshuju" style="color:#ff9560;">{{wait[5]}}</div>
                    <div class="ge" style="color:#ff9560;">个</div>
                    <div class="wenjianzhenglitubiao"></div>
                    <div class="jindu" style="background:#fec6a9;">
                        <div class="wanchengjindu" style="background:#ff9560;"></div>
                    </div>
                    <div class="yiwancheng">已完成:{{done[1]}}个</div>
                </div>
                <div class="daibananniu" style="background:#ff9560;cursor:pointer" onclick="processList(5,'文件整理')">立即进入</div>
            </div>
            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti" style="color:#4ec3bb;">预验收申请</div>
                    <div class="daibanshuju" style="color:#4ec3bb;">{{wait[8]}}</div>
                    <div class="ge" style="color:#4ec3bb;">个</div>
                    <div class="yvshoushenqingtubiao"></div>
                    <div class="jindu" style="background:#9eede8;">
                        <div class="wanchengjindu" style="background:#0f9087;"></div>
                    </div>
                    <div class="yiwancheng">已完成:{{done[2]}}个</div>
                </div>
                <div class="daibananniu" style="background:#4ec3bb;cursor:pointer" onclick="processList(8,'预验收申请')">立即进入</div>
            </div>
            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti" style="color:#f67664;">移交档案</div>
                    <div class="daibanshuju" style="color:#f67664;">{{wait[11]}}</div>
                    <div class="ge" style="color:#f67664;">个</div>
                    <div class="yijiaotubiao"></div>
                    <div class="jindu" style="background:#f4c3bd;">
                        <div class="wanchengjindu" style="background:#cf331d;"></div>
                    </div>
                    <div class="yiwancheng">已完成:{{done[3]}}个</div>
                </div>
                <div class="daibananniu" style="background:#f67664;cursor:pointer" onclick="processList(11,'移交档案')">立即进入</div>
            </div>
            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti" style=" color:#d64ef9;">工程管理</div>
                    <div class="daibanshuju" style=" color:#d64ef9;">{{total}}</div>
                    <div class="ge" style=" color:#d64ef9;">个</div>
                    <div class="gongchenggguanlitubiao" style="top: 750px;"></div>
                    <div class="jindu" style=" background:#ebc2f5;" ><div class="wanchengjindu" style=" background:#d64ef9;"></div></div>
                    <div class="yiwancheng">已完成:{{done[4]}}个</div>
                </div>
                <div class="daibananniu" style="background:#d64ef9;cursor:pointer" onclick="processList('','工程管理')">工程管理</div>
            </div>

            <div class="baojian">
                <div class="daibanrenwu">
                    <div class="baojianbiaoti" style="color:#f1c832;">案卷管理</div>
                    <div class="daibanshuju" style="color:#f1c832;">{{wait[17]}}</div>
                    <div class="ge" style="color:#f1c832;">个</div>
                    <div class="anjuanzhenglitubiao" style="top: 750px;"></div>
                    <div class="jindu" style="background:#e5d8b3;"><div class="wanchengjindu" style="background:#f1c832;"></div></div>
                    <div class="yiwancheng">已完成:{{done[5]}}个</div>
                </div>
                <div class="daibananniu" style="background:#e3ac15;cursor:pointer" onclick="processList(17,'案卷管理')">立即处理</div>
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
            var total=0;
            var wait=[];
            var done=[0,0,0,0,0,0];
            for(var i=0;i<18;i++)
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
                if(i>=4)
                    done[0]+=t;
                if(i>=7)
                    done[1]+=t;
                if(i>=10)
                    done[2]+=t;
                if(i>=13)
                    done[3]+=t;
                if(i>=11 && i!=12)
                    done[4]+=t;
                if(i==20)
                    done[5]+=t;
                wait[i]=t;
                total+=t;
            }
            return {
                total:total,
                wait:wait,
                done:done
            };
        }
    });
</script>