<%--
  Created by IntelliJ IDEA.
  User: zhuibobo
  Date: 2018/5/13
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv='Content-Language' content='en-us'>
    <meta http-equiv='Content-Type' content='text/html; charset={-ENCODE-}'>
    <meta name='GENERATOR' content='Microsoft FrontPage 4.0'>
    <meta name='ProgId' content='FrontPage.Editor.Document'>
    <title>报表</title>
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


    </script>
    <style type="text/css">
        td { FONT-FAMILY: 黑体; font-size: 14pt;height:40px;vertical-align:middle; } /*4号字体*/
        .title{Font-FAMILY:黑体;font-size:22pt;height:80px;vertical-align:middle;} /*2号字体*/
        .title1{Font-FAMILY:黑体;font-size:14pt;height:50px;vertical-align:middle;}

        .td_style{ FONT-FAMILY: 宋体; font-size: 12pt }
        .td_style10{ FONT-FAMILY: 宋体; font-size: 10pt }
        .td_bottom_style {border-top:none;border-bottom:none;}
        .p3 { FONT-FAMILY: 宋体; FONT-SIZE: 13pt;}
        .h01{
            position:absolute;
            top:35px;
            left:0px;
            margin:0;
            line-height:200%;
        }

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
<div align='center'>
    <table  id='rpttitle' border='0' cellpadding='0' cellspacing='0' width='640px'>
        <tr>
            <td width='100%' colspan='2'   align='center' valign='middle'  class="title">&nbsp;<br />合肥市建设工程档案验收申请书</td></tr>
        <tr>
            <td align='center' class="title1">&nbsp;</td>
            <td align='right'>&nbsp;<!--no--></td>
        </tr>

    </table>

    <table  id='rptcontent'  border='1' cellspacing='0' width='640px'  style='border-collapse: collapse' bordercolor='#111111' cellpadding='1'>
        <tr>
            <td width='20%' nowrap align='center'>工程名称</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;<!--prjname--></td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>工程地点</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;<!--no--></td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>规划许可证号</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>建设单位</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>勘察单位</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>设计单位</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>监理单位</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>施工单位</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>质量监督机构</td>
            <td width='75%' align='left' colspan ='3'>&nbsp;</td>
        </tr>

        <tr>
            <td width='20%' nowrap align='center'>面   积</td>
            <td width='25%' align='left'>&nbsp;平方米</td>
            <td width='20%' nowrap align='center'>造    价</td>
            <td width='25%' align='left'>&nbsp;万 元
            </td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>开工日期</td>
            <td width='25%' align='left'>&nbsp;</td>
            <td width='20%' nowrap align='center'>竣工日期</td>
            <td width='25%' align='left'>&nbsp;</td>
        </tr>
        <tr>
            <td width='20%' nowrap align='center'>档案联系人</td>
            <td width='25%' align='left'>&nbsp;</td>
            <td width='20%' nowrap align='center'>联系电话</td>
            <td width='25%' align='left'>&nbsp;</td>
        </tr>

        <tr>
            <td width='50%' align='center' colspan='2' valign='top'>
                <table border='0' cellpadding='0' cellspacing='0' width='97%'>
                    <tr>
                        <td width='100%' height='30px' align='left'>施工单位对档案的自检意见：</td>
                    </tr>
                    <tr>
                        <td width='100%' height='150px' align='left'>&nbsp;<br />&nbsp;<br />&nbsp;<br /></td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>（签字盖章）</td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>年   月    日</td>
                    </tr>
                </table>
            </td>
            <td width='50%' align='center' colspan='2' valign='top'>
                <table border='0' cellpadding='0' cellspacing='0' width='97%'>
                    <tr>
                        <td width='100%' height='30px' align='left'>建设单位对档案的审核意见：</td>
                    </tr>
                    <tr>
                        <td width='100%' height='150px' align='left'>&nbsp;<br />&nbsp;<br />&nbsp;<br /></td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>（签字盖章）</td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>年   月    日</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td width='50%' align='center' colspan='2' valign='top'>
                <table border='0' cellpadding='0' cellspacing='0' width='97%'>
                    <tr>
                        <td width='100%' height='30px' align='left'>监理单位对档案的审核意见：</td>
                    </tr>
                    <tr>
                        <td width='100%' height='150px' align='left'>&nbsp;<br />&nbsp;<br />&nbsp;<br /></td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>（签字盖章）</td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>年   月    日</td>
                    </tr>
                </table>
            </td>
            <td width='50%' align='center' colspan='2' valign='top'>
                <table border='0' cellpadding='0' cellspacing='0' width='97%'>
                    <tr>
                        <td width='100%' height='30px' align='left' nowrap>城建档案馆业务受理决定（意见）：</td>
                    </tr>
                    <tr>
                        <td width='100%' height='150px' align='left'>&nbsp;<br />&nbsp;<br />&nbsp;<br /></td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>（签字盖章）</td>
                    </tr>
                    <tr>
                        <td width='100%' height='30px' align='right'>年   月    日</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>

</html>
