<%--
  Created by IntelliJ IDEA.
  User: bobo-sss
  Date: 2018/4/18
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
</head>
<body>
    -------app1----------
    <br />
    <div id="app1">
        {{message}}
        <br />
        <span :title="title">悬停提示</span>
        <br />
        <span v-if="false_not_show">不显示</span>
        <br />
        <span v-if="true_show">显示</span>
        <ul>
            <li v-for="(item,index) in items">{{index}}-{{item.name}}</li>
        </ul>
        <input value="更新绑定的数据" type="button" @click="updateul" />
        <br />
        <input v-model="inputValue" type="text" />
        <br />
        {{inputValue}}
        <br />
        <ol>
            <todo-item1 :todo_title="inputValue"></todo-item1>
            <todo-item2 v-for="foritem in items" :todo_item="foritem"></todo-item2>
        </ol>
    </div>
    <input type="button" onclick="buttonClick()" value="change" />
    <br />
    -------app2----------
    <br />
    <div id="app2">
        <span>{{message}}</span>
        <input value="更新消息" @click="updateMessage" type="button" />
    </div>
    <script>
        Vue.component('todo-item1', {
            props:['todo_title'],
            template: '<li>{{todo_title}}</li>'
        });
        Vue.component('todo-item2', {
            props:['todo_item'],
            template: '<li>{{todo_item.name}}</li>'
        });
        var app1=new Vue({
            el:"#app1",
            data:{
                message:"hello 酷狗~",
                title:'页面加载于 ' + new Date().toLocaleString(),
                false_not_show:false,
                true_show:true,
                items:[
                    {name:"name1"},
                    {name:"name2"},
                    {name:"name3"}
                ],
                inputValue:"我是输入框1号"
            },
            methods:{
                updateul:function () {
                    this.items.push({name:"name4"});
                }
            }
        });
        function buttonClick() {
           app1.message="再见 酷狗~";
           window.setTimeout(function () {
               app1.message="过了2秒酷狗~";
           },2000);
        }
    </script>
    <script>
        var app2data={
            message:"测试中断绑定"
        }
        //设置为自读
        Object.freeze(app2data);
        var app2=new Vue({
            el:"#app2",
            data:app2data,
            methods:{
                updateMessage:function () {
                    this.message="测试中断绑定UPDATE";
                }
            }
        });
    </script>
</body>
</html>
