<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/23
  Time: 21:34
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
    <script type="text/javascript" src="${ctxpath}/Js/EngineeringFile.js?urltimestamp=${urlts}"></script>
</head>
<body>
    <div id="app" class="general-edit-page-wrap" v-cloak>
        <tabs type="card">
            <tab-pane label="附件资料" icon="social-windows">
            <div class="dialog-1-wrap-sy-nx scroll-div-wrap" style="height:590px ">
                <card style="width:100%;margin-bottom: 10px" v-for="mateItem in engConfMateJson">
                    <p slot="title" style="height: 25px">
                        <icon type="ios-film-outline"></icon>
                        上传材料[{{mateItem.mateName}}]【{{mateItem.mateDesc}}】
                    </p>
                    <upload :action="getURL(mateItem)" :on-success="updateList">
                        <i-button type="warning" icon="ios-cloud-upload-outline">选择并上传附件</i-button>
                    </upload>
                    <div name="mate-file-c" :bind-to-mate-type="mateItem.mateValue">
                        <%--<table class="general-table-1sty">
                            <colgroup>
                                <col width="50px" />
                                <col />
                                <col width="100px" />
                                <col width="100px" />
                                <col width="50px" />
                            </colgroup>
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>文件名称</th>
                                <th>上传单位</th>
                                <th>上传时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>
                                        <div class="list-row-button list-row-button-del" style="margin-left: 0px"></div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>--%>
                    </div>
                </card>
            </div>
        </tab-pane>
        </tabs>
    </div>
    <script>
        var Main = {
            data:function () {
                return {
                    formValidate:{},
                    engConfMateJson:${engConfMateJson}
                }
            },
            mounted:function(){
                EngineeringFile.Init("${sid}");
            },
            methods: {
                getURL:function (mateItem) {
                    return "${ctxpath}/project/engineeringfile/uploadfile.do?outer_table_name=iams_arol_eng_base_info&outer_sid=${sid}&groupValue="+mateItem.mateValue;
                },
                updateList:function () {
                    //alert("1");
                    EngineeringFile.Init("${sid}");
                }
            }
        }
        var Component = Vue.extend(Main)
        new Component().$mount('#app')
    </script>
</body>
</html>
