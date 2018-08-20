<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/26
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html style="height: 100%">
<head>
    <title>建设工程资料整理服务云平台</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <style>
    </style>
</head>
<body style="height: 100%">
<div id="app">
    <div class="layout">
        <layout :style="{minHeight: '100vh'}">
            <i-header style="padding: 0 10px" class="layout-header-wrap">
                <div style="width: 100%;height: 65px">
                    <div class="layout-logo">
                        <Icon type="ios-monitor-outline"></Icon>&nbsp;&nbsp;建设工程资料整理服务云平台
                    </div>
                    <div class="topMenuItem-warp">
                        <div class="topMenuItem-inner-warp">
                            <div :class="top_menu_item_class(item)" @click="top_menu_click(item.name)" v-for="(item, key) in topMenuArrayJson">
                                {{item.text}}
                            </div>
                        </div>
                    </div>
                    <div class="top-right-wrap">
                        <div class="yonghu">
                            <div class="touxiang"></div>
                            <div class="lanxian"></div>
                            <div style="float: left">
                                <div style="line-height:24px;margin-top: 8px;overflow-x: hidden;white-space: nowrap;text-overflow:  ellipsis;">用户名：{{userInfo.userName}}</div>
                                <div style="line-height: 24px;overflow-x: hidden;width: 130px;white-space: nowrap;text-overflow:  ellipsis;" :title="userInfo.organName">部&nbsp;&nbsp;&nbsp;&nbsp;门：{{userInfo.organName}}</div>
                            </div>
                        </div>
                        <div class="tuichu" onclick="window.location.href='${ctxpath}/login.do'"></div>
                    </div>
                    <%--<i-menu mode="horizontal" theme="dark" active-name="1">
                        <div class="layout-logo">
                            <Icon type="ios-monitor-outline"></Icon>&nbsp;&nbsp;城建档案综合管理信息系统</div>
                        <div class="layout-nav">
                            <i-menu mode="horizontal" active-name="Project-SystemManagement" theme="dark" @on-select="top_menu_click">
                                <menu-item :name="item.name" v-for="(item, key) in topMenuArrayJson">
                                    <icon :type="item.iconType" size="30" style="padding-top: 15px;"></icon>
                                    <span style="vertical-align: top;">{{item.text}}</span>
                                </menu-item>
                            </i-menu>
                            &lt;%&ndash;<div style="position: absolute;right: 4px;top: 12px;color: #ffffff;z-index: 999;border: #ffffff solid 1px;border-radius: 4px;height: 40px;padding: 5px;line-height: 40px">
                                <a href="${ctxpath}/login.do"><Icon type="power" size="30"></Icon></a>
                            </div>&ndash;%&gt;
                            <div class="top-right-wrap">
                                <div class="yonghu">
                                    <div class="touxiang"></div>
                                    <div class="lanxian"></div>
                                    <div style="float: left">
                                        <div style="line-height:24px;margin-top: 8px">用户名：黄亚平</div>
                                        <div style="line-height:24px">部&nbsp;&nbsp;&nbsp;&nbsp;门：项目部</div>
                                    </div>
                                </div>
                                <div class="tuichu" onclick="window.location.href='${ctxpath}/login.do'"></div>
                            </div>
                        </div>
                        <div class="layout-header-shadow-line" style="height: 4px;width: 100%"></div>
                    </i-menu>--%>
                </div>
                <div class="layout-header-shadow-line" style="height: 4px;width: 100%"></div>
            </i-header>
            <layout>
                <sider hide-trigger :style="sider_style">
                    <i-menu active-name="Project-SystemManagement-BusinessUsers" theme="light" width="auto" :open-names="['1']" @on-select="left_menu_click">
                        <menu-item :name="item.name" v-for="(item, key) in leftMenuArrayJson" v-if="item.items==undefined">
                            <icon :type="item.iconType==''?'shuffle':item.iconType" size="20" color="#2d8cf0"></icon>
                            {{item.text}}
                        </menu-item>
                       <Submenu :name="item.name"  v-else-if="item.items.length>0">
                            <template slot="title">
                                <Icon :type="item.iconType==''?'shuffle':item.iconType" color="#2d8cf0"></Icon>
                                {{ item.text }}
                            </template>
                            <template v-for="(i, k) in item.items">
                                <Menu-item v-bind:name="i.name">{{ i.text }}</Menu-item>
                            </template>
                        </Submenu>
                    </i-menu>
                </sider>
                <layout :style="{padding: '0 24px 0px 24px'}">
                    <breadcrumb :style="{margin: '10px 0'}">
                        <breadcrumb-item v-for="(item,key) in breadcrumbArrayJson">{{item.text}}</breadcrumb-item>
                    </breadcrumb>
                    <i-content :style="{padding: '10px', minHeight: '480px', background: '#fff'}">
                        <iframe name="iframe" :src="contentIframeUrl" width="100%" :height="frameHeight" frameborder="0"></iframe>
                    </i-content>
                </layout>
            </layout>
        </layout>
    </div>

</div>
<script>
    var IsTopWorkaroundPage = true;
    var l1MenuArrayJson=null;
    l1MenuArrayJson=${menuJson};
    var menuArrayJson=JsonUtility.ResolveSimpleArrayJsonToTreeJson({
        KeyField: "name",
        RelationField: "parentName",
        ChildFieldName: "items"
    },l1MenuArrayJson,"0");
    console.log(menuArrayJson);
    var app=new Vue({
        data:{
            l1MenuArrayJson:l1MenuArrayJson,
            topMenuArrayJson:menuArrayJson.items,
            leftMenuArrayJson:menuArrayJson.items[0].items,
            breadcrumbArrayJson:[menuArrayJson.items[0],menuArrayJson.items[0].items[0]],
            frameHeight: 0,
            contentIframeUrl:"",
            userInfo:${currUserEntity}
        },
        mounted:function(){
            this.setFrameHeight();
            this.contentIframeUrl=BaseUtility.ReplaceUrlVariable(this.contentIframeUrl);
            //alert(this.contentIframeUrl);
        },
        created:function () {
            this.contentIframeUrl=BaseUtility.ReplaceUrlVariable(menuArrayJson.items[0].items[0].url);
        },
        methods:{
            get_menu:function (name) {
                for(var i=0;i<this.l1MenuArrayJson.length;i++){
                    if(this.l1MenuArrayJson[i].name==name){
                        return this.l1MenuArrayJson[i];
                    }
                }
                return null;
            },
            buildBreadcrumbArrayJson:function (name) {
                this.breadcrumbArrayJson=new Array();
                var lastMenu=this.get_menu(name);
                if(lastMenu!=null){
                    this.breadcrumbArrayJson.push(lastMenu);
                    if(lastMenu.parentName!="0"){
                        var lastMenuL1=this.get_menu(lastMenu.parentName);
                        this.breadcrumbArrayJson.push(lastMenuL1);
                    }
                }
                this.breadcrumbArrayJson.reverse();
            },
            top_menu_click:function (name) {
                for(var i=0;i<this.topMenuArrayJson.length;i++) {
                    if(this.topMenuArrayJson[i].name==name){
                        this.leftMenuArrayJson = this.topMenuArrayJson[i].items;
                    }
                }
            },
            left_menu_click:function (name) {
                this.buildBreadcrumbArrayJson(name);
                var menu=this.get_menu(name);
                var url=BaseUtility.ReplaceUrlVariable(menu.url);
                this.contentIframeUrl=url;
            },
            setFrameHeight:function(){
                //调整掉一些补白的值
                //debugger;
                this.mainHeight = PageStyleUtility.GetWindowHeigth()-90-35;
                this.frameHeight = this.mainHeight-10;
            },
            top_menu_item_class:function (item) {
                //debugger;
                return "topMenuItem "+item.iconType;
            }
        },
        computed:{
            sider_style:function () {
                var height=$(window).height()-65;
                return {
                    background:"#fff",
                    height:height+"px",
                    overflow:"auto"
                }
            }
        }
    });
    app.$mount('#app');
</script>
</body>
</html>