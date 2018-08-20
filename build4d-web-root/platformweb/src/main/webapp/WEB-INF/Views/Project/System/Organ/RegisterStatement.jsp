<%--
  Created by IntelliJ IDEA.
  User: liwl
  Date: 2018/6/13 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>
<head>
    <title>在线业务指导系统—报建登记</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/IViewLib.jsp" %>
    <%@ include file="/WEB-INF/Views/TagLibs/JQueryUILib.jsp" %>
    <script type="text/javascript" src="${ctxpath}/Js/ProProcessLib.js"></script>
    <style>
        p{
            text-indent: 2em;
        }
    </style>

</head>
<body>
<div id="app" class="general-edit-page-wrap" v-cloak>
    <div style="margin-top: 10px;text-align: right" id="divBt">
        <i-button type="primary" @click="print"> 打印责任书</i-button>
        <i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关 闭</i-button>
    </div>
    <div id="divContent">
        <div  class="dialog-1-wrap-sy-nx scroll-div-wrap" style="text-align: center; height: auto;padding: 10px 20px; overflow:hidden ">
            <table id='rpttitle' border='0' cellpadding='5' cellspacing='5'   style="width: 98%;padding: 5px 5px;">
                <tr>
                    <td width='100%' align='center' valign='middle' class="title">&nbsp;<br/><h2>建设工程档案移交责任书</h2></td>
                </tr>
                <tr style="height: 80px;">
                    <td align='right' class="title1">编号：合城档验（{{currYear}}）{{engBaseInfoEntity.sid}}号&nbsp;</td>
                </tr>

            </table>

            <table id='rptcontent' border='0' cellpadding='5' cellspacing='5'  style="width: 98%;padding: 5px 5px; border-collapse: collapse"
                   bordercolor='#111111' cellpadding='1'>
                <tr>
                    <td align='left'>工程名程：{{engBaseInfoEntity.engName}}</td>
                </tr>
                <tr>
                    <td align='left'>开竣工时间：{{formatDate(engBaseInfoEntity.engSpecInfo.startDate)}} 至 {{formatDate(engBaseInfoEntity.engSpecInfo.endDate)}}</td>
                </tr>
                <tr>
                    <td align='left'>&nbsp;</td>
                </tr>

                <tr>
                    <td align='left'>
                        　　<p>为贯彻《城市建设档案管理规定〉细则》，切实加强工程档案的管理，确保工程档案完整、准确、及时地报送城建档案馆，根据有关文件精神，建设单位与城乡建设档案馆签订建设工程档案移交责任书；</p>
                        <br/>
                        　 <p>一、建设单位责任</p>
                        <br/>
                        　　<p>1、移交工程档案内容包括：基建文件；原材料成品及半成品合格证；试验报告；施工试验；施工记录；技术复核；隐蔽工程验收记录；图纸会审记录及施工组织设计；施工技术交底及施工日志；建筑给排水采暖工程技术资料、低温热水地板辐射采暖工程资料；建筑电气工程资料；工程质量验收记录；竣工类资料；竣工图；工程竣工后的正面、背面、侧面六寸彩色照片各一张（放在第一卷的第一页，不编页码）。</p>
                        <br/>
                        　　<p>2、编制方法与质量要求：按照《建设工程文件归档整理规范》来立卷、编目、装订。</p>
                        <br/>
                        　　<p>二、城乡建设档案馆责任：对工程档案的内容、编制方法和要求进行业务指导，监督建设单位及时收集、编制工程档案。城乡建设档案馆对接受的工程档案要及时登记；做好保管、保护工作，开发档案信息资源，热情为建设单位服务。</p>
                        <br/>
                        　　<p>三、建设单位在组织竣工验收前，应提请城乡建设档案馆对工程档案进行预验收，工程档案应包括纸质、照片及电子档案三合一，对验收合格的工程档案予以接收，并出具《建设工程档案认可证》，不符合条件的，责令整改，重新进行预验收。</p>
                        <br/>
                        　　<p>四、建设单位在取得《建设工程档案认可证》后，方可到建筑工程质量监督站办理竣工验收相关手续。</p>
                        <br/>
                        　　<p>五、建设工程档案预验收后形成的档案，应当在建设工程竣工验收后十五天内向城乡建设档案馆补充移交。工程档案全部移交后，城乡建设档案馆出具《建设工程档案接收证明书》，并将《建设工程档案认可证》收回。</p>
                        <br/>
                        　　<p>六、建设单位工程竣工验收后三个月内未按规定向城乡建设档案馆移交工程档案的，城乡建设档案馆应发出限期改正通知书，逾期仍不改正的，建设局依据《建设工程管理条例》做出罚款决定。</p></td>
                </tr>
                <tr>
                    <td align='center' valign='top' style="padding: 8px 8px;">
                        <table border='0' cellpadding='5' cellspacing='5' width='97%'>
                            <tr>
                                <td width='50%' align='left'>甲方（盖章）：{{engBaseInfoEntity.constructionOrgEntity.orgName}} </td>
                                <td width='50%' align='left'>乙方（盖章）：{{archOrgEntity.orgName}}  </td>
                            </tr>
                            <tr>
                                <td width='50%' align='left'>负责人（签字）：</td>
                                <td width='50%' align='left'>负责人（签字）：</td>
                            </tr>
                            <tr>
                                <td width='50%' align='left'>联系人：</td>
                                <td width='50%' align='left'>联系人：</td>
                            </tr>
                            <tr>
                                <td width='50%' align='left'>联系电话：</td>
                                <td width='50%' align='left'>联系电话：2162331</td>
                            </tr> 
                        </table>
			<div style="height:50px;text-align:right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script>

    var app = new Vue({
        el:"#app",
        data:{
            engBaseInfoEntity:${engBaseInfoEntity},
            archOrgEntity:${archOrgEntity}
        },
        computed:{
            currYear:function(){
               return DateUtility.Format(new Date(),"yyyy")
            }
        },
        methods:{
            formatDate:function(d,fmt){
                if(fmt==null) fmt="yyyy-MM-dd";
                var date=new Date();
                if(d) date=new Date(d);
                return DateUtility.Format(date,fmt)
            },
            print:function () {
                $("#divBt").hide();
                window.print();
                $("#divBt").show();
            },
            handleClose: function (name) {
                DialogUtility.Frame_CloseDialog(window);
            }
        }
    });
</script>
</body>
</html>
