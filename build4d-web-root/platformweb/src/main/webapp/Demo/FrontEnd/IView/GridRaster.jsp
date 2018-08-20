<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/15
  Time: 20:09
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
			<i-col span="12">col-12</i-col>
			<i-col span="12">col-12</i-col>
		</row>
		<br>
		<row>
			<i-col span="8">col-8</i-col>
			<i-col span="8">col-8</i-col>
			<i-col span="8">col-8</i-col>
		</row>
		<br>
		<row>
			<i-col span="6">col-6</i-col>
			<i-col span="6">col-6</i-col>
			<i-col span="6">col-6</i-col>
			<i-col span="6">col-6</i-col>
		</row>
	</div>
	</template>
	<script>
        var Main = {

        }

        var Component = Vue.extend(Main)
        new Component().$mount('#app')
	</script>
</body>
</html>
