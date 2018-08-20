<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/15
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html style="height: 100%">
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <style>
        #app{
            /*padding: 32px;*/
        }
        .layout{
            border: 0px solid #d7dde4;
            background: #f5f7f9;
            position: relative;
            border-radius: 4px;
            overflow:auto;
        }
        .layout-logo{
            width: 240px;
            height: 40px;
            background: #5b6270;
            border-radius: 3px;
            float: left;
            position: relative;
            top: 10px;
            left: 10px;
            color:#ffffff;
            font-size: 20px;
            line-height: 40px;
            padding-left: 10px;
        }
        .layout-nav{
            width: 1100px;
            margin: 0 auto;
            margin-right: 0px;
        }
    </style>
</head>
<body style="height: 100%">
<div id="app">
    <%--<i-button type="primary" @click="modal1 = true">Display dialog box</i-button>--%>
    <modal
            v-model="modal1"
            title="Common Modal dialog box title"
            @on-ok="ok"
            @on-cancel="cancel">
        <i-form :model="formItem" :label-width="80">
            <form-item label="Input">
                <i-input v-model="formItem.input" placeholder="Enter something..."></i-input>
            </form-item>
            <form-item label="Select">
                <i-select v-model="formItem.select">
                    <i-option value="beijing">New York</i-option>
                    <i-option value="shanghai">London</i-option>
                    <i-option value="shenzhen">Sydney</i-option>
                </i-select>
            </form-item>
            <form-item label="DatePicker">
                <row>
                    <i-col span="11">
                        <date-picker type="date" placeholder="Select date" v-model="formItem.date"></date-picker>
                    </i-col>
                    <i-col span="2" style="text-align: center">-</i-col>
                    <i-col span="11">
                        <time-picker type="time" placeholder="Select time" v-model="formItem.time"></time-picker>
                    </i-col>
                </row>
            </form-item>
            <form-item label="Radio">
                <radio-group v-model="formItem.radio">
                    <radio label="male">Male</radio>
                    <radio label="female">Female</radio>
                </radio-group>
            </form-item>
            <form-item label="Checkbox">
                <checkbox-group v-model="formItem.checkbox">
                    <checkbox label="Eat"></checkbox>
                    <checkbox label="Sleep"></checkbox>
                    <checkbox label="Run"></checkbox>
                    <checkbox label="Movie"></checkbox>
                </checkbox-group>
            </form-item>
            <form-item label="Switch">
                <i-switch v-model="formItem.switch" size="large">
                    <span slot="open">On</span>
                    <span slot="close">Off</span>
                </i-switch>
            </form-item>
            <form-item label="Slider">
                <slider v-model="formItem.slider" range></slider>
            </form-item>
            <form-item label="Text">
                <i-input v-model="formItem.textarea" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></i-input>
            </form-item>
            <form-item>
                <i-button type="primary">Submit</i-button>
                <i-button type="ghost" style="margin-left: 8px">Cancel</i-button>
            </form-item>
        </i-form>
    </modal>
    <div class="layout">
        <layout :style="{minHeight: '100vh'}">
            <i-header>
                <i-menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo">构建系统</div>
                    <div class="layout-nav">
                        <menu-item name="1">
                            <icon type="ios-navigate"></icon>
                            工作平台
                        </menu-item>
                        <menu-item name="2">
                            <icon type="ios-keypad"></icon>
                            工程跟踪管理
                        </menu-item>
                        <menu-item name="3">
                            <icon type="ios-analytics"></icon>
                            信息管理
                        </menu-item>
                        <menu-item name="4">
                            <icon type="ios-paper"></icon>
                            工程跟踪动态统计
                        </menu-item>
                        <menu-item name="4">
                            <icon type="ios-analytics"></icon>
                            档案员管理
                        </menu-item>
                        <menu-item name="4">
                            <icon type="ios-keypad"></icon>
                            档案查询
                        </menu-item>
                        <menu-item name="4">
                            <icon type="ios-navigate"></icon>
                            档案统计
                        </menu-item>
                        <menu-item name="4">
                            <icon type="ios-paper"></icon>
                            系统管理
                        </menu-item>
                    </div>
                </i-menu>
            </i-header>
            <layout>
                <sider hide-trigger :style="sider_style">
                    <i-menu active-name="1-2" theme="light" width="auto" :open-names="['1']" @on-select="menu_click">
                        <menu-item name="1-1">
                            <icon type="document-text"></icon>
                            表设计
                        </menu-item>
                        <menu-item name="1-2">
                            <icon type="document-text"></icon>
                            数据集设计
                        </menu-item>
                        <menu-item name="1-3">
                            <icon type="document-text"></icon>
                            模块设计
                        </menu-item>
                        <menu-item name="1-4">
                            <icon type="document-text"></icon>
                            数据字典
                        </menu-item>
                    </i-menu>
                </sider>
                <layout :style="{padding: '0 24px 24px'}">
                    <breadcrumb :style="{margin: '24px 0'}">
                        <breadcrumb-item>Home</breadcrumb-item>
                        <breadcrumb-item>Components</breadcrumb-item>
                        <breadcrumb-item>Layout</breadcrumb-item>
                    </breadcrumb>
                    <i-content :style="{padding: '24px', minHeight: '480px', background: '#fff'}">
                        <i-button @click="handleSelectAll(true)">Set all selected</i-button>
                        <i-button @click="handleSelectAll(false)">Cancel all selected</i-button>
                        <i-table border :columns="columns5" :data="data5" :style="{marginTop:'10px',marginBottom:'10px'}"></i-table>
                        <div style="float: right;">
                            <page :total="100"></page>
                        </div>
                    </i-content>
                </layout>
            </layout>
        </layout>
    </div>

</div>
<script>
    var Main = {
        data:function() {
            return {
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
                ],
                modal1: false,
                formItem: {
                    input: '',
                    select: '',
                    radio: 'male',
                    checkbox: [],
                    switch: true,
                    date: '',
                    time: '',
                    slider: [20, 50],
                    textarea: ''
                }
            }
        },
        methods:{
            menu_click:function (name) {
                //alert(name);
                this.modal1=true;
            },
            handleSelectAll:function (status) {
                alert("1");
            }
        },
        computed:{
            sider_style:function () {
                var height=$(window).height()-70;
                return {
                    background:"#fff",
                    height:height+"px",
                    overflow:"auto"
                }
            }
        }
    };

    var Component = Vue.extend(Main);
    new Component().$mount('#app');
</script>
</body>
</html>
