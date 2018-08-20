<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/7/3
  Time: 15:50
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
    <style>
        .Advisory{
            border: #2ca0d0 1px solid;
            border-radius: 4px;
            margin: 4px;
            padding: 4px;
        }
        .Advisory-Content{
            border-bottom: #2ca0d0 1px solid;
            padding: 4px;
        }
        .Advisory-Bottom{
            text-align: right;
            margin: 4px;
        }

        .Replay{
            border: #8bb8d0 1px solid;
            border-radius: 4px;
            margin: 4px;
            padding: 4px;
        }
        .Replay-Content{
            border-bottom: #c9d0cf 1px solid;
            padding: 4px;
        }
        .Replay-Bottom{
            text-align: right;
            margin: 4px;
        }
    </style>
</head>
<body>
<div id="app" class="general-edit-page-wrap">
    <div style="width: 100%;height: 420px;border: #d7d7e4 1px solid;overflow-y: scroll;overflow-x: hidden">
        <div class="Advisory">
            <div class="Advisory-Content">
                {{proAdvisoryEntity.advContent}}
            </div>
            <div class="Advisory-Bottom">
               绑定工程 {{arolEngBaseInfoEntity.engName}} ； 咨询人：{{proAdvisoryEntity.userName}} ；咨询时间：{{dateCv(proAdvisoryEntity.createtime)}}
            </div>
        </div>
        <div class="Replay" v-for="item in proAdvisoryReplyEntities">
            <div class="Replay-Content">
                {{item.replyContent}}
            </div>
            <div class="Replay-Bottom">
                人员：{{item.userName}} ；时间：{{dateCv(item.createtime)}}
            </div>
        </div>
    </div>
    <div style="width: 100%;height: 100px;border: #2b85e4 1px solid">
        <div style="float: left;width: 85%;height: 100%">
            <textarea style="width: 100%;height: 90%;margin: 4px;border-radius: 4px" v-model="NewReply">

            </textarea>
        </div>
        <div style="float: left;width: 10%;margin-left: 10px;margin-top: 30px">
            <i-button type="primary" @click="newReply()"> 回 复 </i-button>
        </div>
    </div>
</div>
<script>
    var Main = {
        data:function () {
            return {
                NewReply:"",
                AdvisoryId:"${AdvisoryId}",
                proAdvisoryEntity:${proAdvisoryEntity},
                arolEngBaseInfoEntity:${arolEngBaseInfoEntity},
                proAdvisoryReplyEntities:${proAdvisoryReplyEntities}
            };
        },
        methods: {
            dateCv:function (datetime) {
                var date=new Date(datetime);
                var dateStr=B4D.DateUtility.Format(date,'yyyy-MM-dd');
                return dateStr;
            },
            newReply: function () {
                if(this.NewReply==""){
                    alert("请输入内容");
                    return;
                }
                var url='/project/advisory/saveadvisoryreply.do';
                var _self=this;
                //debugger;
                var senddata={
                    AdvisoryId:_self.AdvisoryId,
                    NewReply:_self.NewReply
                }
                AjaxUtility.Post(url, senddata , function (result) {
                    if (result.success) {
                        alert("回复成功！")
                        _self.proAdvisoryReplyEntities.push(result.data);
                    }
                },"json");
            }
        }
    }
    var Component = Vue.extend(Main)
    new Component().$mount('#app')
</script>
</body>
</html>