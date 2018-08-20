<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/15
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <STYLE>
        #app{padding: 0px;}
        .layout-con{
            height: 100%;
            width: 100%;
        }
        .menu-item span{
            display: inline-block;
            overflow: hidden;
            width: 69px;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: bottom;
            transition: width .2s ease .2s;
        }
        .menu-item i{
            transform: translateX(0px);
            transition: font-size .2s ease, transform .2s ease;
            vertical-align: middle;
            font-size: 16px;
        }
        .collapsed-menu span{
            width: 0px;
            transition: width .2s ease;
        }
    </STYLE>
</head>
<body>
    <div id="app">
        <div class="layout">
            <layout :style="{minHeight: '100vh'}">
                <sider collapsible :collapsed-width="78" v-model="isCollapsed">
                    <i-menu active-name="1-2" theme="dark" width="auto" :class="menuitemClasses">
                        <menu-item name="1-1">
                            <icon type="ios-navigate"></icon>
                            <span>Option 1</span>
                        </menu-item>
                        <menu-item name="1-2">
                            <icon type="search"></icon>
                            <span>Option 2</span>
                        </menu-item>
                        <menu-item name="1-3">
                            <icon type="settings"></icon>
                            <span>Option 3</span>
                        </menu-item>
                    </i-menu>
                </sider>
                <layout>
                    <i-header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}"></i-header>
                    <i-content :style="{padding: '0 16px 16px'}">
                        <breadcrumb :style="{margin: '16px 0'}">
                            <breadcrumb-item>Home</breadcrumb-item>
                            <breadcrumb-item>Components</breadcrumb-item>
                            <breadcrumb-item>Layout</breadcrumb-item>
                        </breadcrumb>
                        <card>
                            <div style="height: 600px">Content</div>
                        </card>
                    </i-content>
                </layout>
            </layout>
        </div>
    </div>
    <script>
        var Main = {
            data:function () {
                return {
                    isCollapsed: false
                };
            },
            computed: {
                menuitemClasses: function () {
                    return [
                        'menu-item',
                        this.isCollapsed ? 'collapsed-menu' : ''
                    ]
                }
            }
        };

        var Component = Vue.extend(Main);
        new Component().$mount('#app');
    </script>
</body>
</html>
