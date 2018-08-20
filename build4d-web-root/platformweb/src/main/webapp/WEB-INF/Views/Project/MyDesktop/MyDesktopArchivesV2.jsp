<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/28
  Time: 21:58
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
        function processList(s,title)
        {
            var url=BaseUtility.BuildUrl("/project/engineering/listfordesktop.do?status="+s);
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
        <div class="flow-process-wrap" style="zoom:0.75;">
            <table style="height: 100%;width: 100%">
                <tr>
                    <td valign="middle">
                        <img src="${ctxpath}/Images/Project/baojianquerentubiao.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg"></div>
                            <div class="bottomMsg"></div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png" width="128px">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/yanyanshou.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">预验检查</div>
                            <div class="bottomMsg">预验审核</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png" width="128px">
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
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png" width="128px">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/danganjieshou.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">档案报送</div>
                            <div class="bottomMsg">接受审核</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png" width="128px">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/danganguidang.png">
                    </td>
                    <td valign="middle">
                        <div class="line-msg-wrap">
                            <div class="topMsg">档案移交</div>
                            <div class="bottomMsg">档案归档</div>
                        </div>
                        <img src="${ctxpath}/Images/Project/kongzhitaixian.png" width="128px">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/jiantou.png">
                    </td>
                    <td>
                        <img src="${ctxpath}/Images/Project/shendingfazheng.png">
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
                    <div class="entrance-title  ">报建确认</div>
                    <div class="entrance-num "  style="padding-top: 0%;color:#4ba3c8; margin-bottom:90px;">
                        <span class="entrance-num-font-size " style="font-size: 135px; line-height: 135px; position: relative; z-index: 999; ">{{wait[100]}}</span><span style="color: #b8b8b8; position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#4ba3c8;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%; z-index:0;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/baojianqueren3.png" width="96px" height="105px;" >
                    </div>
                    <div class="entrance-completed">已完成:{{done[0]}}个</div>
                </div>
                <div class="button" style="background-color: #4ba3c8;" onclick="processList(100,'报建确认')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">预验收检查</div>
                    <div class="entrance-num" style="padding-top: 0%; color: #e7814e; margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[400]+wait[900]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#e7814e;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/yewuzhidao3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[1]}}个</div>
                </div>
                <div class="button" style="background-color: #e7814e" onclick="processList('400,900','预验收检查')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">预验收审核</div>
                    <div class="entrance-num" style="padding-top: 0%;margin-bottom:90px; color: #4db2ab;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[500]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#4db2ab;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/yvyanshou3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[2]}}个</div>
                </div>
                <div class="button" style="background-color: #4db2ab" onclick="processList(500,'预验收申请审核')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">案卷审核</div>
                    <div class="entrance-num" style="padding-top: 0%;color:#cc6ea5;margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[1000]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#cc6ea5;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/zhenglizujuan3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[3]}}个</div>
                </div>
                <div class="button" style="background-color:#cc6ea5;" onclick="processList(1000,'案卷审核')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">档案接收</div>
                    <div class="entrance-num" style="padding-top: 0%;color: #6d9cd7;margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[1300]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#6d9cd7;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/danganjieshou3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[4]}}个</div>
                </div>
                <div class="button" style="background-color:#6d9cd7;" onclick="processList(1300,'档案接收审核')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">档案移交</div>
                    <div class="entrance-num" style="padding-top: 0%;color:#d86555;margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[1400]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#d86555;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/danganyijiao3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[5]}}个</div>
                </div>
                <div class="button" style="background-color: #d86555;" onclick="processList(1400,'档案移交')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">档案归档</div>
                    <div class="entrance-num" style="padding-top: 0%;color:#b19a33;margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[1700]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#b19a33;"></div>
                    </div>
                    <div style="position: absolute;top: 140px; right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/danganguidang3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[6]}}个</div>
                </div>
                <div class="button" style="background-color:#b19a33;" onclick="processList(1700,'档案归档')">立即进入</div>
            </div>
            <div class="entrance-wrap">
                <div class="entrance">
                    <div class="entrance-title">审定发证</div>
                    <div class="entrance-num" style="padding-top: 0%;color: #916ab4;margin-bottom:90px;">
                        <span class="entrance-num-font-size" style="font-size: 135px; line-height: 135px;position: relative; z-index: 999;">{{wait[2000]}}</span><span style="color: #b8b8b8;position: relative; z-index: 999;">个</span>
                    </div>
                    <div  class="entrance-process-line-bg">
                        <div style="background-color:#916ab4;"></div>
                    </div>
                    <div style="position: absolute;top: 140px;right: 5%;">
                        <img class="entrance-img" src="${ctxpath}/Images/Project/shendingfazheng3.png" width="96px" height="105px;">
                    </div>
                    <div class="entrance-completed">已完成:{{done[7]}}个</div>
                </div>
                <div class="button" style="background-color:#916ab4;" onclick="processList(2000,'审定发证')">立即进入</div>
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
            //var entrance_num_font_size=($(".entrance").height()-50)/2;
            //$(".entrance-num-font-size").css("fontSize",entrance_num_font_size);
            //$(".button").css("lineHeight",$(".button").height()+"px");

            //$(".entrance-img").width($(".entrance").height()*0.5);
            //$(".entrance-img").height($(".entrance").height()*0.6);
            //$(".entrance-title").css("fontSize",entrance_num_font_size-90);
            //$(".entrance-completed").css("fontSize",entrance_num_font_size-110);

            //修订顶部线

        },
        data:function(){
            var data=${statusData};
            var wait={};
            var done=[0,0,0,0,0,0,0,0];
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
                if(k>=200 && k!=300)
                    done[0]+=t; //报建审核通过
                if(k>=500 && k!=600)
                    done[1]+=t;//预验收检查通过
                if(k>=800 && k!=900)
                    done[2]+=t;//预验收申请通过
                if(k>=1100 && k!=1200)
                    done[3]+=t;//案卷审核通过
                if(k>1400 && k!=1500)
                    done[4]+=t;//档案接收审核通过
                if(k>=1700 && k!=1800)
                    done[5]+=t;//档案已移交
                if(k>=2000 && k!=2100)
                    done[6]+=t;//档案已归档
                if(i==23)
                    done[7]+=t;//审定发证
            })
            return {
                wait:wait,
                done:done
            };
        }
    });
</script>

