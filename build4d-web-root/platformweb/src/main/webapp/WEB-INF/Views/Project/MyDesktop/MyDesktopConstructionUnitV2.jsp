<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/15
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html style="height: 100%">
<head>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxpath}/Css/Project/MyDesktopV1.css?urlts=${urlts}">
    <script>
        function processList(s,title,isArch)
        {
            var url="/project/engineering/listfordesktop.do?status="+s;
            if(isArch)
                url="/project/engineering/archive/archlist.do?status="+s;
            var url=BaseUtility.BuildUrl(url);
            DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:title,width:1024,height:600});
        }
    </script>
</head>
<body style="height: 100%">
<div id="app" style="height: 100%">
    <div class="business-flow-process-wrap">
        <div class="title">
            业务流程
        </div>
        <div class="flow-process-wrap">
            <table style="height: 100%;width: 100%; zoom: 0.75;">
                <tr>
                    <td valign="middle">
                        <img src="${ctxpath}/Images/Project/baojiandengjientubiao.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg"></div>
                            <div class="bottomMsg"></div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/gongchengzucetubiao.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">审核依据</div>
                            <div class="bottomMsg">账号确认</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/wenjiandengjitubiao.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">文件填报</div>
                            <div class="bottomMsg">业务指导</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/zhenglizhujuan.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">工程组卷</div>
                            <div class="bottomMsg">案卷审核</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png">
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div style="height: 60%" class="business-flow-list-entrance-wrap">
        <div class="title">
            业务流程
        </div>
        <div class="flow-entrance-wrap">
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">文件登记</div>
                    <div class="entrance-num" style="padding-top: 0%;color:#4ba3c8; margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{wait[200]+wait[600]}}</span><span>个</span>
                    </div>
                    <div style="background-color:rgb(210, 215, 217)" class="entrance-process-line-bg">
                        <div style="background-color:#2ca0d0;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/baojianqueren2.png" width="96px" height="105px">
                    </div>
                    <div class="entrance-completed">已完成:{{done[0]}}个</div>
                </div>
                <div class="button" style="background-color: #4ba3c8;"  onclick="processList('200,600','文件登记')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title" >工程组卷</div>
                    <div class="entrance-num" style="padding-top: 0%;color:rgb(78, 195, 187); margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{wait[800]+wait[1200]}}</span><span>个</span>
                    </div>
                    <div style="background-color: rgb(210, 215, 217);" class="entrance-process-line-bg">
                        <div style="background-color:rgb(15, 144, 135);"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/danganyijiao2.png" width="96px" height="105px">
                    </div>
                    <div class="entrance-completed">已完成:{{done[2]}}个</div>
                </div>
                <div class="button" style="background-color:  rgb(78, 195, 187);" onclick="processList('800,1200','工程组卷')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title" >档案报送</div>
                    <div class="entrance-num" style="padding-top: 0%;color:rgb(246, 118, 100); margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{wait[1100]+wait[1500]}}</span><span>个</span>
                    </div>
                    <div style="background-color: rgb(210, 215, 217);" class="entrance-process-line-bg">
                        <div style="background-color:rgb(207, 51, 29);"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/zhenglizujuan2.png" width="96px" height="105px">
                    </div>
                    <div class="entrance-completed">已完成:{{done[3]}}个</div>
                </div>
                <div class="button" style="background-color: rgb(246, 118, 100);" onclick="processList('1100,1500','档案报送')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title" >工程管理</div>
                    <div class="entrance-num" style="padding-top: 0%;color:rgb(214, 78, 249); margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{total}}</span><span>个</span>
                    </div>
                    <div style="background-color: rgb(210, 215, 217);" class="entrance-process-line-bg">
                        <div style="background-color:rgb(214, 78, 249);"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/danganguidang2.png" width="96px" height="105px">
                    </div>
                    <div class="entrance-completed">已完成:{{done[4]}}个</div>
                </div>
                <div class="button" style="background-color: rgb(214, 78, 249);" onclick="processList('','工程管理')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title" >案卷管理</div>
                    <div class="entrance-num" style="padding-top: 0%;color:rgb(227, 172, 21); margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{archWait}}</span><span>个</span>
                    </div>
                    <div style="background-color: rgb(210, 215, 217);" class="entrance-process-line-bg">
                        <div style="background-color:rgb(241, 200, 50);"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/yvyanshou3.png" width="96px" height="105px">
                    </div>
                    <div class="entrance-completed">已完成:{{archDone}}个</div>
                </div>
                <div class="button" style="background-color: rgb(227, 172, 21);" onclick="processList('0,2','案卷管理',true)">立即进入</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    var app=new Vue({
        el:"#app",
        mounted:function () {
            /*var entrance_num_font_size=($(".entrance").height()-50)/2;
            $(".entrance-num-font-size").css("fontSize",entrance_num_font_size);
            $(".button").css("lineHeight",$(".button").height()+"px");

            $(".entrance-img").width($(".entrance").height()*0.5);
            $(".entrance-img").height($(".entrance").height()*0.6);
            $(".entrance-title").css("fontSize",entrance_num_font_size-90);
            $(".entrance-completed").css("fontSize",entrance_num_font_size-110);*/
        },
        data:function(){
            var data=${statusData};
            var archData=${archStatusData};
            var total=0;
            var wait=[];
            var done=[0,0,0,0,0,0];
            var archWait=0;
            var archDone=0;
            $.each(ProjectUtil.ProMenum.FlowStatus,function (k,v) {
                var t=0;
                for(var i=0;i<data.length;i++)
                {
                    if(data[i].STATUS==k)
                    {
                        t=data[i].TOTAL;
                        break;
                    }
                }
                wait[k]=t;
                if(k>=400 && k!=600)
                    done[0]+=t; //已登记
                if(k>=700 && k!=900)
                    done[1]+=t;//已申请预验收
                if(k>=1000 && k!=1200)
                    done[2]+=t;//已送案卷审核或审核通过
                if(k>=1300 && k!=1500)
                    done[3]+=t;//已档案报送或接收审核通过
                if(k==2300)
                    done[4]+=t; //工程管理已完成,即,审定发证
                wait[i]=t;
                total+=t;
            });
            for(var i=0;i<archData.length;i++)
            {
                if(archData[i].STATUS==1)
                    archDone+=archData[i].TOTAL;
                else
                    archWait+=archData[i].TOTAL;
            }

            return {
                total:total,
                wait:wait,
                done:done,
                archDone:archDone,
                archWait:archWait
            };
        }
    });
</script>