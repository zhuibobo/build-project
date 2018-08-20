<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/25
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html style="height: 100%">
<head>
    <title></title>
    <%@ include file="/WEB-INF/Views/TagLibs/Builder/GeneralBMLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
</head>
<body style="height: 100%">
    <div id="app" class="page2c">
        <div class="c1" style="width: 30%">
            <card style="height: 100%" :padding="10" class="card-title-include-button">
                <template slot="title">
                    <dropdown trigger="click" style="float: left;margin-right: 6px;margin-top: 8px" placement="bottom-start">
                        <a href="javascript:void(0)">
                            链接
                            <icon type="arrow-down-b"></icon>
                        </a>
                        <dropdown-menu slot="list">
                            <dropdown-item>本地</dropdown-item>
                            <dropdown-item>[120.02.01.222]</dropdown-item>
                            <dropdown-item>[120.02.01.122]</dropdown-item>
                            <dropdown-item>[120.02.21.222]</dropdown-item>
                            <dropdown-item>[120.02.21.222]</dropdown-item>
                        </dropdown-menu>
                    </dropdown>
                    <i-input placeholder="Enter Search..." style="float: left;width: 38%;margin-right: 4px"></i-input>
                    <button-group style="float: left;width: 50%">
                        <i-button type="ghost" icon="plus"></i-button>
                        <i-button type="ghost" icon="edit"></i-button>
                        <%--<i-button type="ghost" icon="ios-search-strong"></i-button>--%>
                        <i-button type="ghost" icon="arrow-up-b"></i-button>
                        <i-button type="ghost" icon="arrow-down-b"></i-button>
                        <i-button type="ghost" icon="android-close"></i-button>
                    </button-group>
                    <div style="clear: both"></div>
                </template>
                <p>
                    <tree :data="data2" show-checkbox></tree>
                </p>
            </card>
        </div>
        <div class="c2" style="width: 69%">
            <card style="height: 100%">
                <p slot="title">表详情</p>
                <div>
                    <i-table border :columns="columns5" :data="data5" :style="{marginTop:'10px',marginBottom:'10px'}"></i-table>
                    <div style="float: right;">
                        <page :total="100"></page>
                    </div>
                </div>
            </card>
        </div>
    </div>
    <script>
        var app=new Vue({
            el:"#app",
            data:{
                value2: '1',
                data2: [
                    {
                        title: '本地链接',
                        expand: true,
                        children: [
                            {
                                title: 'TB4D_ORGAN[组织机构表]',
                                expand: true,
                                children: [
                                    {
                                        title: 'leaf 1-1-1'
                                    },
                                    {
                                        title: 'leaf 1-1-2'
                                    }
                                ]
                            },
                            {
                                title: 'parent 1-2',
                                expand: true,
                                children: [
                                    {
                                        title: 'leaf 1-2-1'
                                    },
                                    {
                                        title: 'leaf 1-2-1'
                                    }
                                ]
                            }
                        ]
                    }
                ],
                columns5: [
                    {
                        title: 'Date',
                        key: 'date',
                        sortable: true
                    },
                    {
                        title: 'Name',
                        key: 'name'
                    },
                    {
                        title: 'Age',
                        key: 'age',
                        sortable: true
                    },
                    {
                        title: 'Address',
                        key: 'address',
                        width:100,
                        render:function (h, params) {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.show(params.index)
                                        }
                                    }
                                }, 'View')
                            ]);
                        }
                    }
                ],
                data5: [
                    {
                        name: 'John Brown',
                        age: 18,
                        address: 'New York No. 1 Lake Park',
                        date: '2016-10-03'
                    },
                    {
                        name: 'Jim Green',
                        age: 24,
                        address: 'London No. 1 Lake Park',
                        date: '2016-10-01'
                    },
                    {
                        name: 'Joe Black',
                        age: 30,
                        address: 'Sydney No. 1 Lake Park',
                        date: '2016-10-02'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    }
                ]
            }
        });
    </script>
</body>
</html>
