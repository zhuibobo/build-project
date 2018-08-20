/**
 * Created with IntelliJ IDEA.
 * Description:项目公用的选择页面
 * User: liwl
 * Date: 2018-05-12
 * To change this template use File | Settings | File Templates.
 */
var SelectComponentUtil={
    GetDictionary:function(pid,haveParent){
        var url='/project/system/dictionary/getlistdatabypid.do';
        var r=null;
        if(haveParent==undefined || haveParent==null)
            haveParent=0;
        AjaxUtility.PostSync(url,{pid:pid,haveParent:haveParent},function (result) {
            r=result.data;
        },"json");
        return r;
    }
};