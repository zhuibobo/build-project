<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2018/4/12
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>建设工程资料整理服务云平台</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctxpath}/Css/Project/Login/css/css.css" />
    <script>
        var IsTopWorkaroundPage = true;
        var WXLoginKey="${WXLoginKey}";
        function validateLogin(_loginKey) {
            var account=$("#txtAccount").val();
            var pwd=$("#txtPwd").val();
            var url="/validateUser.do";
            AjaxUtility.Post(url,{
                account:account,
                pwd:pwd,
                loginKey:_loginKey
            },function (result) {
                //debugger;
                if(result.success) {
                    window.location.href=B4D.BaseUtility.BuildUrl("/project/frame.do");
                }
                else {
                    B4D.DialogUtility.Alert(window,B4D.DialogUtility.DialogAlertId,{},"账号或者密码错误！",null);
                }
            },"json");
        }
        function register()
        {
            var url=BaseUtility.BuildUrl("/project/system/organ/register.do");
            DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId,url,{title:"报建登记"},1);
            //window.open(url);
        }
        function ShowWXRQ() {
            var wxrqurl=BaseUtility.GetRootPath()+"/buildMyWXLoginCode.do";
            wxrqurl=StringUtility.GetTimeStampUrl(wxrqurl);
            DialogUtility.Alert(window,DialogUtility.DialogAlertId,{width:350,height:420,title:'微信扫码登陆'},"<div style='width: 100%;text-align: center'><img src='"+wxrqurl+"' /></div>");
        }
        $(function () {
            if(B4D.BrowserInfoUtility.IsChrome()||B4D.BrowserInfoUtility.IsIE11()){

            }
            else {
                alert("请安装IE11或google浏览器或360浏览器的极速模式。");
                $(".dengluanniu").hide();
                $(".zhuceanniu").hide();
            }
            //window.setInterval(validateWxLoginKeyLoginEnable,6000);
        });
        
        function validateWxLoginKeyLoginEnable() {
            var url="/validateWxLoginKeyLoginEnable.do";
            AjaxUtility.Post(url,{},function (result) {
                if(result.success) {
                    validateLogin(WXLoginKey);
                }
                else {
                    console.log(result.message);
                }
            },"json");
        }
    </script>
</head>
<body class="dengluye">

<p class="mingcheng"><img src="${ctxpath}/Css/Project/Login/img/denglubiaoti.png"/></p>
<p class="mingchengxian" style="width: 80%; height: 2px; background: #FFFFFF; margin: 10px auto;"></p>

<div style="text-align:center;">
    <div class="dengluyeneirong">

        <div class="hei">
            <div class="dengluyeyouce" style="opacity:1;">
                <div class="dengluyelogo"></div>
                <div class="zhanghaokuang">
                    <div class="zhanghaotubiao"></div>
                    <div class="zhanghaoxian"></div>
                    <div class="shuruzhanghao">

                        <input id="txtAccount" value="manager"  type="text" style=" border:0;outline:none;width: 250px;height: 45px; border: #daf3ff 0px solid;  padding: 4px; background: #daf3ff;" />
                    </div>
                </div>
                <div class="mimakuang">
                    <div class="mimatubiao"></div>
                    <div class="mimaxian"></div>
                    <div class="shurumima">
                        <input id="txtPwd" value="1" type="password" style=" border:0;outline:none;width: 250px;height: 45px; border: #daf3ff 0px solid;  padding: 4px; background: #daf3ff;" />
                    </div>
                </div>
                <div class="jizhumima">
                    <div class="jizhumimakuang"></div>
                    <div class="jizhumimazi">记住密码</div>
                </div>
                <div class="wangjimima">忘记密码</div>
                <div class="hongzi">请您输入正确的账号或密码</div>
                <div class="dengluanniu" onclick="validateLogin(null)" style="cursor: pointer"></div>
                <div class="zhuceanniu" onclick="register()" style="cursor: pointer"></div>
            </div>
            <div class="disanfang"></div>
            <div class="weixin" onclick="ShowWXRQ()"></div>
        </div>
    </div></div>
    <div id="reg" style="display: none">
        <iframe name="iframe" :src="contentIframeUrl" width="100%" :height="frameHeight" frameborder="0"></iframe>
    </div>
</body>
</html>