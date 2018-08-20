<%--
  Created by IntelliJ IDEA.
  User: zhuangrb
  Date: 2018/6/22
  Time: 13:36
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
<body>
    <div id="app">
        <textarea style="width: 100%;height: 400px">
            {{message}}
        </textarea>
        <br />
        <input value="access_token" @click="getToken" type="button" />
        <img id="imgAttentionQR" src="" />
        <input value="生成关注的公众号【传说中测试账号得先关注才能授权登陆】" @click="createAttentionRQ" type="button" />
        <input value="生成授权二维码" @click="createRQ" type="button" />
        <img id="imgQR" src="" />
    </div>
    <script>
        var app=new Vue({
            data:{
                message:""
            },
            mounted:function(){
            },
            created:function () {
            },
            methods:{
                getToken:function () {
                    var self=this;
                    var url="/project/weixin/access_token.do";
                    AjaxUtility.Post(url,{
                    },function (result) {
                        if(result.success) {
                            self.message=result.message;
                        }
                        else {
                            self.message=result.message;
                        }
                    },"json");
                },
                createAttentionRQ:function () {
                    var self=this;
                    var url="/project/weixin/createAttentionRQUrl.do";
                    AjaxUtility.Post(url,{
                    },function (result) {
                        if(result.success) {
                            self.message=result.message;
                            var json=StringUtility.StringToJson(self.message);
                            alert(json.url);
                            $("#imgAttentionQR").attr("src","https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+json.ticket);
                        }
                        else {
                            self.message=result.message;
                        }
                    },"json");
                },
                createRQ:function () {
                    var self=this;
                    var url="/project/weixin/createRQUrl.do";
                    AjaxUtility.Post(url,{
                    },function (result) {
                        if(result.success) {
                            self.message=result.message;
                            $("#imgQR").attr("src","${ctxpath}/project/imagegeneral/buildurlqrcode.do?Url="+encodeURIComponent(self.message));
                        }
                        else {
                            self.message=result.message;
                        }
                    },"json");
                }
            },
            computed:{
            }
        });
        app.$mount('#app');
    </script>
</body>
</html>
