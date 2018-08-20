<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/5/24 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>工程进度</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxpath}/PageDesign/ZXSJZ/css/css.css?urlts=${urlts}" />
    <style>
        .process{
            background: url(${ctxpath}/PageDesign/ZXSJZ/img/shijianjiantou.png); width:25px; margin-top:7px;
        }
        .wait{
            background:url(${ctxpath}/PageDesign/ZXSJZ/img/shijianhuidian.png);
        }
        .highlight{
            color:#2db6e3; font-size:30px;
        }
        .shijianjingdu{
            height: 100px;
        }
    </style>
</head>
<body>
    <div class="neirong" id="app">
        <div class="neirongyou">
            <%--<div class="shijiantou">工程[2018-00004]状态动态跟踪</div>--%>
            <div class="shijianmingcheng">
                <div class="lanshuxian" style="margin-left:5%;"></div>
                <div class="sjmingcheng">工程档案形成时间轴</div>
            </div>
            <div class="shijianhuixian" :style="{height: ProgressH}">
                <div class="shijianlanxian"></div>
                <div class="shijiantou">
                    <div class="shijiannian">{{currYear}}</div>
                    <div class="sjtou"></div>
                </div>
                <div class="shijianjingdu"  v-for="e in entities">
                    <div class="shijianriqi">{{FormatMD(e.opDate)}}</div>
                    <div class="jingdulandian"></div>
                    <div class="shijianxiangmu">
                        <div class="sjxiangmubiaoti">{{GetStatus(e.opStatus)}}</div>
                        <div class="sjneirong">提交单位：{{e.organName}}   </div>
                        <div class="sjneirong">提交人：{{e.userName}}&nbsp;&nbsp;&nbsp;&nbsp;{{FormatTime(e.opDate)}}</div>
                        <div class="sjneirong" v-if="e.opOpinion!=null && e.opOpinion!=''">反馈消息：{{e.opOpinion}} </div>
                    </div>
                </div>
                <div class="shijianjingdu" v-for="(s,index) in RemainStatus">
                    <div class="shijianriqi" style="color:#2db6e3; margin-top:7px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                    <div class="jingdulandian" :class="index===0?'process':'wait'"></div>
                    <div class="shijianxiangmu">
                        <div class="sjxiangmubiaoti" :class="index===0?'highlight':''">{{GetStatus(s)}}</div>
                        <div class="sjneirong"   v-if="index===0">处理中......</div>
                    </div>
                </div>
                <div class="shijiandi" :style="{top: ProgressH}">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
    var main={
        data:function () {
            var entities=${LogEntities};
            entities=$.grep(entities,function (v,k) {
                return v.opStatus>0
            });
            var firstYear=new Date(entities[0].opDate).getFullYear();
            return {
                currYear:firstYear,
                entities:entities
            };
        },
        computed:{
            RemainStatus:function () {
                var doneStatus=this.entities[this.entities.length-1].opStatus;
                var nomalStatus=ProjectUtil.FlowMethod.GetNomalFlowStatus();
                var remainStatus=[];
                return $.grep(nomalStatus,function (v,k) {
                    return v>=doneStatus
                });
            },
            ProgressH:function () {
               return ((this.RemainStatus.length+this.entities.length+1)*185)+"px";
            }
        },
        methods:{
            FormatMD:function (date) {
                return DateUtility.Format(new Date(date),"MM.dd");
            },
            FormatTime:function (date) {
                return DateUtility.Format(new Date(date),"yyyy年MM月dd日 hh:mm");
            },
            GetStatus:function (status) {
                if(status==0)
                {
                    status="工程登记";
                }
                else {
                    status = ProjectUtil.ProMenum.FlowStatus[status];
                    if (status.indexOf("待") >= 0)
                        status = "送" + status.replace("待", "");
                    status=status.replace("已","");
                }
                return status;
            }
        }
    }
    var component=Vue.extend(main);
    new component().$mount("#app");
</script>
