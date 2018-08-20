<%--
  Created by IntelliJ IDEA.
  User: bobo-sss
  Date: 2018/4/17
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
</head>
<body>
    <div id="app">
        <row>
            <i-col span="8">
                <i-menu :theme="theme2">
                    <submenu name="1">
                        <template slot="title">
                            <icon type="ios-paper"></icon>
                            内容管理
                        </template>
                        <menu-item name="1-1">文章管理</menu-item>
                        <menu-item name="1-2">评论管理</menu-item>
                        <menu-item name="1-3">举报管理</menu-item>
                    </submenu>
                    <submenu name="2">
                        <template slot="title">
                            <icon type="ios-people"></icon>
                            用户管理
                        </template>
                        <menu-item name="2-1">新增用户</menu-item>
                        <menu-item name="2-2">活跃用户</menu-item>
                    </submenu>
                    <submenu name="3">
                        <template slot="title">
                            <icon type="stats-bars"></icon>
                            统计分析
                        </template>
                        <menu-group title="使用">
                            <menu-item name="3-1">新增和启动</menu-item>
                            <menu-item name="3-2">活跃分析</menu-item>
                            <menu-item name="3-3">时段分析</menu-item>
                        </menu-group>
                        <menu-group title="留存">
                            <menu-item name="3-4">用户留存</menu-item>
                            <menu-item name="3-5">流失用户</menu-item>
                        </menu-group>
                    </submenu>
                </i-menu>
            </i-col>
            <i-col span="8">
                <i-menu :theme="theme2" active-name="1-2" :open-names="['1']">
                    <submenu name="1">
                        <template slot="title">
                            <icon type="ios-paper"></icon>
                            内容管理
                        </template>
                        <menu-item name="1-1">文章管理</menu-item>
                        <menu-item name="1-2">评论管理</menu-item>
                        <menu-item name="1-3">举报管理</menu-item>
                    </submenu>
                    <submenu name="2">
                        <template slot="title">
                            <icon type="ios-people"></icon>
                            用户管理
                        </template>
                        <menu-item name="2-1">新增用户</menu-item>
                        <menu-item name="2-2">活跃用户</menu-item>
                    </submenu>
                    <submenu name="3">
                        <template slot="title">
                            <icon type="stats-bars"></icon>
                            统计分析
                        </template>
                        <menu-group title="使用">
                            <menu-item name="3-1">新增和启动</menu-item>
                            <menu-item name="3-2">活跃分析</menu-item>
                            <menu-item name="3-3">时段分析</menu-item>
                        </menu-group>
                        <menu-group title="留存">
                            <menu-item name="3-4">用户留存</menu-item>
                            <menu-item name="3-5">流失用户</menu-item>
                        </menu-group>
                    </submenu>
                </i-menu>
            </i-col>
            <i-col span="8">
                <i-menu :theme="theme2" :open-names="['1']" accordion>
                    <submenu name="1">
                        <template slot="title">
                            <icon type="ios-paper"></icon>
                            内容管理
                        </template>
                        <menu-item name="1-1">文章管理</menu-item>
                        <menu-item name="1-2">评论管理</menu-item>
                        <menu-item name="1-3">举报管理</menu-item>
                    </submenu>
                    <submenu name="2">
                        <template slot="title">
                            <icon type="ios-people"></icon>
                            用户管理
                        </template>
                        <menu-item name="2-1">新增用户</menu-item>
                        <menu-item name="2-2">活跃用户</menu-item>
                    </submenu>
                    <submenu name="3">
                        <template slot="title">
                            <icon type="stats-bars"></icon>
                            统计分析
                        </template>
                        <menu-group title="使用">
                            <menu-item name="3-1">新增和启动</menu-item>
                            <menu-item name="3-2">活跃分析</menu-item>
                            <menu-item name="3-3">时段分析</menu-item>
                        </menu-group>
                        <menu-group title="留存">
                            <menu-item name="3-4">用户留存</menu-item>
                            <menu-item name="3-5">流失用户</menu-item>
                        </menu-group>
                    </submenu>
                </i-menu>
            </i-col>
        </row>
        <br>
        <p>Change theme</p>
        <radio-group v-model="theme2">
            <radio label="light"></radio>
            <radio label="dark"></radio>
        </radio-group>
    </div>
    <script>
        var Main = {
            data:function () {
                return {
                    theme2: 'light'
                }
            }
        }

        var Component = Vue.extend(Main)
        new Component().$mount('#app')
    </script>
</body>
</html>
