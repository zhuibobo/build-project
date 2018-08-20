<%--
  Created by IntelliJ IDEA.
  User: bobo-sss
  Date: 2018/4/17
  Time: 14:31
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
            <i-col span="4">
                <i-menu active-name="/a/1" :open-names="['/a']" theme="light" width="auto">
                    <div class="layout-logo-left">Admin</div>
                    <Submenu :name="key" v-for="(item, key) in menus">
                        <template slot="title">
                            <Icon :type="item.icon"></Icon>
                            {{ item.title }}
                        </template>
                        <template v-for="(i, k) in item.items">
                            <Menu-item v-bind:name="i.name">{{ i.title }}</Menu-item>
                        </template>
                    </Submenu>
                </i-menu>
            </i-col>
        </row>
    </div>
    <script>
        var App = new Vue({
            data: {
                menus: [
                    {
                        name: '/a',
                        icon: 'ios-paper',
                        title: 'aaaaaa',
                        items: [
                            {
                                name: '/a/1',
                                icon: 'ico',
                                title: 'a1a1a1a1',
                            },
                            {
                                name: '/a/2',
                                icon: 'ico',
                                title: 'a1a1a1a1',
                            },
                            {
                                name: '/a/3',
                                icon: 'ico',
                                title: 'a1a1a1a1',
                            }
                        ]
                    },
                    {
                        name: '/b',
                        icon: 'ios-paper',
                        title: 'bbbbb',
                        items: [
                            {
                                name: '/b/1',
                                icon: 'ico',
                                title: 'a1a1a1a1',
                            }
                        ]
                    }
                ]
            },
            methods: {
            }
        });
        App.$mount('#app');
    </script>
</body>
</html>
