var EngineeringFile={
    Init:function (engId) {
        this.BuildTable(this.GetEngineeringFileJson(engId));
    },
    GetEngineeringFileJson:function (engId) {
        var url="/project/engineeringfile/getfilelist.do?outer_sid="+engId+"&outer_table_name=iams_arol_eng_base_info";
        return B4D.AjaxUtility.PostSync(url,{},function () {},"json",null)
    },
    BuildTable:function (resultJson) {
        var _self=this;
        //debugger;
        if(resultJson!=null&&resultJson.success) {
            var fileJson=resultJson.data;
            $("div[name='mate-file-c']").each(
                function () {
                    $(this).html("");
                    var mateValue = $(this).attr("bind-to-mate-type");
                    if (fileJson != null && fileJson.length > 0) {
                        var tableElem = _self.GetTableElemTemplate();
                        var tableBody = tableElem.find("tbody");
                        var num=1;
                        for (var i = 0; i < fileJson.length; i++) {
                            var singleItem = fileJson[i];
                            if (mateValue == singleItem.fileGroup1) {
                                tableBody.append($("<tr><td>" + (num++) + "</td><td>"+ singleItem.fileName +"</td><td>"+singleItem.fileUnitName+"</td><td>"+singleItem.fileCreateTime+"</td><td>删除</td></tr>"))
                            }
                        }
                        $(this).append(tableElem);
                    }
                }
            );
        }
    },
    GetTableElemTemplate:function () {
        var elem=$("<table class=\"general-table-1sty\">" +
            "                            <colgroup>" +
            "                                <col width=\"50px\" />" +
            "                                <col />" +
            "                                <col width=\"100px\" />" +
            "                                <col width=\"100px\" />" +
            "                                <col width=\"50px\" />" +
            "                            </colgroup>" +
            "                            <thead>" +
            "                            <tr>" +
            "                                <th>序号</th>" +
            "                                <th>文件名称</th>" +
            "                                <th>上传单位</th>" +
            "                                <th>上传时间</th>" +
            "                                <th>操作</th>" +
            "                            </tr>" +
            "                            </thead>" +
            "                            <tbody>" +
            "                            </tbody>" +
            "                        </table>");
        return elem;
    }
}