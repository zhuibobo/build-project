<%--
  Created by IntelliJ IDEA.
  User: bobo-sss
  Date: 2018/4/24
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html style="height: 100%">
<head>
	<title>Java基础构建系统</title>
	<%@ include file="/WEB-INF/Views/TagLibs/Builder/GeneralBMLib.jsp" %>
	<%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
</head>
<body style="height: 100%">
	<div id="app">
		<div class="layout">
			<layout :style="{minHeight: '100vh'}">
				<i-header style="padding: 0 10px">
					<i-menu mode="horizontal" theme="dark" active-name="1">
						<div class="layout-logo">
							<Icon type="ios-monitor-outline"></Icon>
							Java基础构建系统
						</div>
						<div class="layout-nav">
							<i-menu mode="horizontal" active-name="StorageManagement" theme="dark" @on-select="top_menu_click">
								<menu-item :name="item.name" v-for="(item, key) in topMenuArrayJson">
									<icon :type="item.iconType" size="30" style="padding-top: 15px;"></icon>
									<span style="vertical-align: top;">{{item.text}}</span>
								</menu-item>
							</i-menu>
                            <div style="position: absolute;right: 4px;top: 12px;color: #ffffff;z-index: 999;border: #ffffff solid 1px;border-radius: 4px;height: 40px;padding: 5px;line-height: 40px">
                                <a href="${ctxpath}/home.do"><Icon type="power" size="30"></Icon></a>
                            </div>
						</div>
					</i-menu>
				</i-header>
				<layout>
					<sider hide-trigger :style="sider_style">
						<i-menu active-name="StorageManagement-Table" theme="light" width="auto" :open-names="['1']" @on-select="left_menu_click">
							<menu-item :name="item.name" v-for="(item, key) in leftMenuArrayJson">
								<icon :type="item.iconType==''?'shuffle':item.iconType"></icon>
								{{item.text}}
							</menu-item>
						</i-menu>
					</sider>
					<layout :style="{padding: '0 24px 24px'}">
						<breadcrumb :style="{margin: '24px 0'}">
							<breadcrumb-item v-for="(item,key) in breadcrumbArrayJson">{{item.text}}</breadcrumb-item>
						</breadcrumb>
						<i-content :style="{padding: '24px', minHeight: '480px', background: '#fff'}">
							<iframe name="iframe" src="${ctxpath}/builder/managements/storage/tables/management.do" width="100%" :height="frameHeight" frameborder="0"></iframe>
						</i-content>
					</layout>
				</layout>
			</layout>
		</div>
	</div>
	<script>
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
                frameHeight: 0
			},
            mounted:function(){
                this.setFrameHeight();
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
				},
                setFrameHeight:function(){
                    //调整掉一些补白的值
                    this.mainHeight = (document.documentElement.scrollHeight || document.body.scrollHeight)-90-90;
                    this.frameHeight = this.mainHeight-30;
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