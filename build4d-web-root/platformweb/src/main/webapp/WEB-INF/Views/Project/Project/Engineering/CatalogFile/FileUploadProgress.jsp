<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/5/24 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>工程进度</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxpath}/PageDesign/FileUploadProgress/css/css.css" />
    <style>
        .jingdutiao{
            width:93%;
        }
        .neirongyou{
            width: 100%;
        }
        .jinduquan1,.huiyuan{
            margin-left: 0px;
        }
        .baifenshi{
            z-index:100;
        }
        .lin{
            background:#ffffff;
        }
    </style>
</head>
<body>
    <div class="neirong" id="app">
        <div class="neirongyou">
            <div class="jingdu">
                <div class="lanxianshu"></div>
                <div class="gongcheng">{{engName}}</div>
            </div>
            <div class="jingdubeijin" v-for="(catItem,itemIndex) in getCatalogFileData()">
                <div v-for="c in countUpload(catItem.fileNo)">
                    <div class="xiangmujingduming">{{catItem.fileNo+"|"+catItem.fileTitle}} （{{c.upload}} / {{c.total}}）</div>
                    <div class="jingdufenge"></div>
                    <div class="jingdutiao">
                    <div class="jingduxian1" :style="{background:getColor(itemIndex,false),width:(c.upload==0?0:(c.upload/c.total).toFixed(2))*100+'%'}"></div>
                    <div class="baifenlin">
                        <div class="lin">0%</div>
                        <div class="jinduquan1" :style="{background:'url('+getColor(itemIndex,true)+')'}"></div>
                    </div>

                    <div v-if="((c.upload/c.total).toFixed(2))*100>0" class="baifenshi" :style="{left:(c.upload==0?0:(c.upload/c.total).toFixed(2))*100+'%'}">
                        <div class="lin">{{((c.upload/c.total).toFixed(2))*100}}%</div>
                        <div class="jinduquan1" :style="{background:'url('+getColor(itemIndex,true)+')'}">
                            <div class="baiyuan"></div>
                        </div>
                    </div>

                    <div class="baifenyibai" v-if="c.upload==0 || ((c.upload/c.total).toFixed(2))*100<100">
                        <div class="lin">100%</div>
                        <div class="huiyuan">
                            <div class="baiyuan"></div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
    var engSid=${engSid};
    var main={
        data:function () {
            return {
                engName:'${engName}',
                search_condition:{
                    engSid:{
                        value:engSid,
                        type:SearchUtility.SearchFieldType.IntType
                    },
                    /*unitType:{
                        value:organEntity.orgType=="档案管理单位"?"":organEntity.orgType,
                        type:SearchUtility.SearchFieldType.StringType
                    },*/
                    isdirectory:{
                        value:'',
                        type:SearchUtility.SearchFieldType.IntType
                    },
                    fileNo:{
                        value:'',
                        type:SearchUtility.SearchFieldType.StringType
                    },
                    matePSid:{
                        value:'',
                        type:SearchUtility.SearchFieldType.StringType
                    }
                }
            };
        },
        computed:{

        },
        methods:{
            getColor:function (index,isIco) {
               var index=index%10+1;
                var color=["#4ba3c8","#e7814e","#4db2ab","#cc6ea5",
                    "#6d9cd7","#d86555","#499cdd","#b19a33","#916ab4",
                    "#42935b"];
                var ico="${ctxpath}/PageDesign/FileUploadProgress/img/jinduquan"+index+".png";
                return isIco?ico:color[index-1];
            },
            getCatalogFileData:function (pid) {
                var _self=this;
                this.search_condition.isdirectory.value=1;
                this.search_condition.matePSid.value=pid;
                _self.loading=true;
                var url='/project/engineering/catalogfile/getcataloglistdata2.do';
                var senddata={
                    search_condition:JSON.stringify(_self.search_condition)
                }
                var r=null;
                AjaxUtility.PostSync(url, senddata , function (result) {
                    if (result.success) {
                        r= result.data;
                    }
                },"json");
                return r;
            },
            countUpload:function (fileNo) {
                var _self=this;
                var url='/project/engineering/catalogfile/countuploadbyeng.do';
                var r=null;
                AjaxUtility.PostSync(url, {engSid:engSid,fileNo:fileNo} , function (result) {
                    if (result.success) {
                        r= result.data;
                    }
                },"json");
                return r;
            },
            FormatMD:function (date) {
                return DateUtility.Format(new Date(date),"MM.dd");
            },
            FormatTime:function (date) {
                return DateUtility.Format(new Date(date),"yyyy年MM月dd日 hh:mm");
            },
            GetStatus:function (status) {
                if(status==0)
                {
                    status="工程登记";
                }
                else {
                    status = ProjectUtil.ProMenum.FlowStatus[status];
                    if (status.indexOf("待") >= 0)
                        status = "送" + status.replace("待", "");
                    status=status.replace("已","");
                }
                return status;
            }
        }
    }
    var component=Vue.extend(main);
    new component().$mount("#app");
</script>
