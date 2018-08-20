<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/4/27
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
	<title>Title</title>
	<%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
	<%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
	<%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
</head>
<body>
<div id="app">
	<!-- 3. #app是Vue实例挂载的元素，应该在挂载元素范围内使用组件-->
	{{message}}:<input v-model="message">
	<my-component v-bind:title="message"></my-component>
	<my-component v-bind:title="message"></my-component>
	<ol>
		<li v-for="todo in todos">
			{{ todo.text }}
		</li>
	</ol>
	<button v-on:click="reverseMessage">逆转消息</button>
</div>
<script>

    // 1.创建一个组件构造器
    var myComponent = Vue.extend({
        template: '<div>This is my first component!</div>'
    })

    // 2.注册组件，并指定组件的标签，组件的HTML标签为<my-component>
     Vue.component('my-component', myComponent)

    new Vue({
        el: '#app',
		data:{
            message:"hello!",
            todos: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ]
		},
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    });

</script>
</body>
</html>
