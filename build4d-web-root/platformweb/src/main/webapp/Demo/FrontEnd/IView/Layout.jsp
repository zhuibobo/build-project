<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/15
  Time: 20:49
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
		<div class="layout">
			<layout>
				<i-header>Header</i-header>
				<i-content>Content</i-content>
				<i-footer>Footer</i-footer>
			</layout>

			<layout>
				<i-header>Header</i-header>
				<layout>
					<sider hide-trigger>Sider</sider>
					<i-content>Content</i-content>
				</layout>
				<i-footer>Footer</i-footer>
			</layout>

			<layout>
				<i-header>Header</i-header>
				<layout>
					<i-content>Content</i-content>
					<sider hide-trigger>Sider</sider>
				</layout>
				<i-footer>Footer</i-footer>
			</layout>

			<layout>
				<sider hide-trigger>Sider</sider>
				<layout>
					<i-header>Header</i-header>
					<i-content>Content</i-content>
					<i-footer>Footer</i-footer>
				</layout>
			</layout>
		</div>
	</div>
	<script>
        var Main = {

        }
        var Component = Vue.extend(Main)
        new Component().$mount('#app')
	</script>
</body>
</html>
