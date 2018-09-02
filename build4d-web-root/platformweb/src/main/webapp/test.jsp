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
	<my-component1 v-bind:title="message"></my-component1>
	<my-component2 v-bind:title="message"></my-component2>
	<my-component3 v-bind:title="message"></my-component3>
	<my-component4 v-bind:title="message"></my-component4>
	<my-component5 v-bind:title="message" :p1="message"></my-component5>
	<my-component6 v-bind:title="message" :p1="message"></my-component6>
	<my-component7 ><h1 slot="header">头部内容</h1>fdfdff<h3 slot="footer">底部内容</h3></my-component7>
	筛选1：<input v-model="filterKey">
	<ol>
		<li v-for="todo in todos">
			{{ todo.text }}
		</li>
	</ol>
	<button v-on:click="reverseMessage">逆转消息</button>


	<i-form   :label-width="100">
		<form-item label="项目名称：">
			<input-number placeholder="请输入总工程预算"></input-number>
		</form-item>

	</i-form>


</div>
<div id="app2"></div>
<script type="text/x-template" id="compont4">
	<div>This is my component4!</div>
</script>
<template id="compont5">
	<div>{{p1}},This is my component5!</div>
</template>
<template id="compont6">
	<div>父子组件数据单向绑定：<input type="text" :value="p1"> </div>
</template>
<template id="compont7">
	<div>
		<br/><br/>插槽测试：<br/>
	<slot name="header"><div>插槽1：头部内容 </div></slot>
	<slot><div>插槽2：默认内容</div> </slot>
	<slot name="footer"><div>插槽3：底部内容 </div></slot>
        <br/><br/>
	</div>
</template>
<script type="text/javascript"  >
   /* import Vue from 'Vue';
    import Input2 from '${ctxpath}/UIComponent/IView-2.13.0/src/components/tree-grid';
    //Vue.use(App);
    new Vue({
        el: '#app2',
		template:"<Input2/>",
        components: { Input2 }
    })*/
</script>
<script>

    // 1.创建一个组件构造器
    var myComponent = Vue.extend({
        template: '<div>This is my component1!</div>'
    })

    // 2.注册组件，并指定组件的标签，组件的HTML标签为<my-component>
    Vue.component('my-component1',myComponent)
	//语法糖
    Vue.component('my-component2',{ template: '<div>This is my component2!</div>'})

    new Vue({
        el: '#app',
        components:{
            'my-component3':{template: '<div>This is my component3!</div>'},
            'my-component4':{template: '#compont4'},
            'my-component5':{template: '#compont5',props:['p1']},
            'my-component6':{template: '#compont6',props:['p1']},
            'my-component7':{template: '#compont7'}
		},
		data:{
            message:"hello!",
            todolist: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ],
			filterKey:'学'
		},
		computed:{
            todos:function(){
                var _self=this;
                return _self.todolist.filter((item)=>{return item.text.indexOf(_self.filterKey)>=0});
			}
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
