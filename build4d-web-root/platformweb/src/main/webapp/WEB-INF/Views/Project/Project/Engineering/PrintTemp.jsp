<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/18
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/Views/TagLibs/TagLib.jsp" %>
<html>

<head>
    <meta http-equiv='Content-Language' content='en-us'>
    <meta http-equiv='Content-Type' content='text/html; charset={-ENCODE-}'>
    <meta name='GENERATOR' content='Microsoft FrontPage 4.0'>
    <meta name='ProgId' content='FrontPage.Editor.Document'>
    <title>报表</title>
    <%@ include file="/WEB-INF/Views/TagLibs/GeneralLib.jsp" %>
    <script language="javascript">
        /*
        根据Json数据自动填充，将标题和内容分成两个表格，里面用<!--字段-->标签，设置好Json数组，即可自动替换。
        设计：黄亚平
        2018-1-10
        */
        var rptinfo = [
            { "no": "合城档验（2018）1号", "prjname": "四季花城一期" }
        ];


        //
        function getrptinfo() {
            /*

            */
            var obj1 = eval(rptinfo);
            for (var i = 0; i < obj1.length; i++) {
                var obj2 = obj1[i];
                var table = document.getElementById("rpttitle");
                var rowsLength = table.rows.length;

                for (var i0 = 0; i0 < table.rows.length; i0++) {
                    for (var j0 = 0; j0 < table.rows[i0].cells.length; j0++) {
                        for (var keys in obj2) {
                            table.rows[i0].cells[j0].innerHTML = table.rows[i0].cells[j0].innerHTML.replace("<!--" + keys + "-->", obj2[keys]);
                        }
                    }
                }
                var table1 = document.getElementById("rptcontent");
                var rowsLength1 = table1.rows.length;

                for (var i0 = 0; i0 < table1.rows.length; i0++) {

                    for (var j0 = 0; j0 < table1.rows[i0].cells.length; j0++) {
                        for (var keys in obj2) {
                            table1.rows[i0].cells[j0].innerHTML = table1.rows[i0].cells[j0].innerHTML.replace("<!--" + keys + "-->", obj2[keys]);
                        }
                    }
                }
            }
        }

        $(function () {
            var url="${url}";
            $("#imgQR").attr("src","${ctxpath}/project/imagegeneral/buildurlqrcode.do?Url="+encodeURIComponent(url));
        })
    </script>
    <style type="text/css">
        td { FONT-FAMILY: 黑体; font-size: 14pt;height:40px;vertical-align:middle; } /*4号字体*/
        .title{Font-FAMILY:黑体;font-size:22pt;height:80px;vertical-align:middle;} /*2号字体*/
        .title1{Font-FAMILY:黑体;font-size:10.5pt;height:30px;vertical-align:middle;}
        .foot0{Font-FAMILY:宋体;font-size:10.5pt;height:30px;vertical-align:middle;}
        .td_style{ FONT-FAMILY: 宋体; font-size: 12pt }
        .td_style10{ FONT-FAMILY: 宋体; font-size: 10pt }
        .td_bottom_style {border-top:none;border-bottom:none;}
        .p3 { FONT-FAMILY: 宋体; FONT-SIZE: 13pt;}
        .h01{position:absolute;	top:15px;	left:50px;	margin:0;line-height:200%;display:block; border:1px; solid #00f; width:100px;height:100px;}
        .st4s{Font-FAMILY:宋体;font-size:12pt;height:30px;vertical-align:middle;}
        .st4{Font-FAMILY:宋体;font-size:14pt;height:30px;vertical-align:middle;}
        .thin{background:#000000}
        .thin td{background:#FFFFFF}
        /*
        width　=　(8.27 - 0.75 * 2) * 96 / 600 = 650 px
        height =  (11.69 - 0.75 * 2)* 96 / 600 = 978 px
                初号字         42pt
                小初号字       36pt
                一号字         26pt
                小一号字       24pt
                二号字         22pt
                小二号字       18pt
                三号字         16pt
                小三号字       15pt
                四号字         14pt
                小四号字       12pt
                五号字         10.5pt
                小五号字       9pt
                六号字         7.5pt
                小六号字       6.5pt
                七号字         5.5pt
                八号字         5pt
        */

    </style>
</head>

<body  onload="getrptinfo()">
<input value="打印" type="button" onclick="$(this).hide();window.print()" />
<div align='center'>
    <div class='h01'>
        <img id="imgQR" src="" style="width: 100px;height: 100px" />
    </div>
    <table  id='rpttitle' border='0' cellpadding='0' cellspacing='0' width='640px'>
        <tr>
            <td width='100%' colspan='2'   align='center' valign='middle'  class="title">&nbsp;<br />&nbsp;<br />建设工程档案预验收意见书</td></tr>
        <tr>
            <td align='center' class="title1">&nbsp;</td>
            <td align='right'>验收编号：&nbsp;<!--no--></td>
        </tr>

    </table>

    <table  id='rptcontent'  border='1' cellspacing='0' width='640px'  style='border-collapse: collapse' bordercolor='#111111' cellpadding='1'>
        <tr>
            <td width='20%' nowrap align='center'>工程名称</td>
            <td width='25%' align='left'>${entity.engName}</td>
            <td width='20%' nowrap align='center'>工程地点</td>
            <td width='25%' align='left'>${entity.engZone} ${entity.engLocation}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>开工日期</td>
            <td width='25%' align='left'><script>document.write(DateUtility.Format(new Date("${entity.engSpecInfo.startDate}"),"yyyy-MM-dd"))</script></td>
            <td width='20%' nowrap align='center'>竣工日期</td>
            <td width='25%' align='left'><script>document.write(DateUtility.Format(new Date("${entity.engSpecInfo.endDate}"),"yyyy-MM-dd"))</script></td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>建设单位</td>
            <td width='75%' align='left' colspan='3'>${entity.constructionOrgEntity.orgName}</td>

        </tr>
        <tr>
            <td width='20%' nowrap align='center'>勘察单位</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.reconnaissanceOrgName}</td>
            <td width='20%' nowrap align='center'>设计单位</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.designOrgName}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>施工单位</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.constructOrgName}</td>
            <td width='20%' nowrap align='center'>监理单位</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.supervisionOrgName}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>建设工程规划<br />许可证号</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.landUsePlanningNo}</td>
            <td width='20%' nowrap align='center'>建设工程施工<br />许可证号</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.constructNo}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>建设规模</td>
            <td width='25%' align='left'>${entity.engSpecInfo.engBudget}（万元）</td>
            <td width='20%' nowrap align='center'>结构类型</td>
            <td width='25%' align='left'>${entity.engHouseSpec.consTypeCode}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>建设单位<br />负责人</td>
            <td width='25%' align='left'>${entity.constructionOrgEntity.orgContacts}</td>
            <td width='20%' nowrap align='center'>联系电话</td>
            <td width='25%' align='left'>${entity.constructionOrgEntity.orgContMobile}</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>档案员姓名</td>
            <td width='25%' align='left'>${entity.engOrgAndCode.instructor}</td>
            <td width='20%' nowrap align='center'>联系电话</td>
            <td width='25%' align='left'>&nbsp;</td>
        </tr>

        <tr>
            <td width='50%' align='center' colspan='4' valign='top'>
                <table border='0' cellpadding='0' cellspacing='0' width='97%'>
                    <tr>
                        <td width='100%' height='30px' align='left' colspan='2' class='st4s'>预验收意见：</td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='left' colspan='2' class='st4s'>　　经查验，该项建设工程档案基本符合《建设工程文件归档整理规范》（GB/T50328-2001）、《建设电子文件与电子档案管理规范》（CJJ/T117-2007）、《安徽省建筑工程资料管理规程》（DB34/T918.4-2009）等文件规定，验收合格，特此证明。请按规定抓紧向城建档案管理机构报送工程档案。</td>
                    </tr>


                    <tr>
                        <td width='100%' height='80px' align='left' colspan='2'>&nbsp;<br />&nbsp;<br />&nbsp;<br /></td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right' colspan='2' class='st4'>合肥市城建档案管理处（盖章）<br />&nbsp;<br />&nbsp;</td>
                    </tr>
                    <tr>
                        <td width='50%' height='30px' align='left' class='st4'>专项验收负责人签字：</td><td width='50%' height='30px' align='right' class='st4'>年   月    日<br />&nbsp;<br />&nbsp;</td>
                    </tr>

                </table>
            </td>
        </tr>

    </table>
    <table  id='tblfoot' border='0' cellpadding='0' cellspacing='0' width='640px'>
        <tr>
            <td width='100%' colspan='2'   align='left' valign='middle'  class="foot0">
                说明： <br />
                　　1、本意见书未经城建档案管理机构盖章无效；<br />
                　　2、本意见书不得涂改；<br />
                　　3、本意见书一式三份（市城建档案馆、建设单位，建设工程竣工备案部门各一份）；<br />
                　　4、本意见书为建设单位组织工程竣工验收、办理建设工程竣工备案手续的必要认可文件，不作为其他用途凭证。</td></tr>
    </table>


</div>
</body>

</html>