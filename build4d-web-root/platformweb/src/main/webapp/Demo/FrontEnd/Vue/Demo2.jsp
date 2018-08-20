<%--
  Created by IntelliJ IDEA.
  User: bobo-sss
  Date: 2018/4/20
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <style>
        .fade-enter-active, .fade-leave-active {
            transition: opacity .5s;
        }
        .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
            opacity: 0;
        }
    </style>
</head>
<body>
    <div id="app">
        {{message}}
        <app-header :parent-message="message">
            <div slot-scope="props">我是父级别分发{{props.out.name}}</div>
        </app-header>
        <button v-on:click="show = !show">
            Toggle
        </button>
        <transition name="fade">
            <p v-if="show">hello</p>
        </transition>
    </div>
    <script>
        Vue.component("app-header",{
            template:"<div style=\"color:red\">\
                        {{appHeaderMessage}},{{parentMessage}}\
                        <slot :out=\"childData\">我是无分发</slot>\
                      </div>",
            props:["parentMessage"],
            render: function (createElement) {
                alert(this.appHeaderMessage);
                return createElement("div", {
                    on: {
                        click: this.updatedata
                    },
                    domProps: {
                        innerHTML: this.appHeaderMessage
                    }
                }, "{{appHeaderMessage}}");
            },
            methods:{
                updatedata:function () {
                    this.appHeaderMessage="我是HeaderClick";
                }
            },
            data:function () {
                return {
                    appHeaderMessage:"我是Header",
                    childData:{
                        name:"ZhuangRuiBo"
                    }
                }
            }
        });
        var app=new Vue({
            el:"#app",
            data:{
                message:"你好不好~",
                show:true
            }
        });
    </script>
</body>
</html>
