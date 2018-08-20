/**
 * Created with IntelliJ IDEA.
 * Description: 项目信息
 * User: liwl
 * Date: 2018-05-17
 * To change this template use File | Settings | File Templates.
 */

var ProjectUtil={
    ProMenum:{
        FlowStatus:{
            //【建设、施工、监理单位】（A）、档案馆（B）
            //流程： 工程登记(A) -> 报建确认（B） -> 文件登记（A） -> 预验收检查（B）-> 预验收审核（B） ->
            // 工程组卷（A）->案卷审核（B） -> 档案报送（A） -> 接收审核（B） -> 档案移交（B）-> 归档入库（B） -> 审定发证（B） -> 结束
            0:"工程登记"/*即：暂存*/,
            100:"报建待审核",                             200:"报建审核通过" /*即：自动进入文件登记,准备预验收申请*/,         300:"报建审核不通过",
            400:"待预验收检查",                         500:"预验收检查通过" /*即：自动进入预验收审核 */,             600:"预验收检查不通过",
            700:"待预验收审核"/*预留,未用*/ ,         800:"预验收审核通过"/*即：自动进入工程组卷,准备案卷审核*/,            900:"预验收审核未通过" ,
            1000:"案卷待审核",                            1100:"案卷审核通过" /*即：自动进入档案待报送         */,           1200:"案卷审核不通过",
            1300:"档案接收待审核" /*即：进入档案接收, 准备接收*/,  1400:"档案接收审核通过"/*即：自动进入档案移交, 准备移交*/,       1500:"档案接收审核未通过" /*预留,未用*/,
            1600:"档案待移交" /*预留,未用*/ ,              1700:"档案已移交" /*即：自动进入档案归档，准备归档*/,                  1800:"拒绝档案移交" /*预留,未用*/,
            1900:"档案待归档" /*预留,未用*/ ,              2000:"档案已归档" /*即：自动进入审定发证，准备审定*/,                  2100:"拒绝档案归档" /*预留,未用*/,
            2200:"待审定发证" /*预留,未用*/,               2300:"审定发证",                                            240:"拒绝发证" /*预留,未用*/
        },
       /* FlowStatus:{
            0:"报建待审核",1:"报建审核通过",2:"报建审核不通过",
            3:"预验收待审核", 4:"预验收审核通过", 5:"预验收审核不通过",
            6:"组卷待审核", 7:"组卷审核通过", 8:"组卷审核不通过",
            9:"档案接收待审核",10:"档案接收审核通过",11:"档案接收审核不通过",
            12:"归档待审核",13:"归档审核通过",14:"归档审核不通过",
            15:"审定发证待审核",16:"审定发证审核通过",17:"审定发证审核不通"
        },*/
        EngType:[
            {id:'1',caption:'房建工程类',pid:''},
            {id:'2',caption:'道路工程类',pid:''},
            {id:'3',caption:'桥梁工程类',pid:''},
            {id:'4',caption:'管线工程类',pid:''},
        ]
    },
    EngMethod:{
        QRCodePriv:function (status) {
            return (status>=800 && status!=900)
        },
        BuildQRCode:function (engSid) {
            var url=BaseUtility.BuildUrl("/project/engineering/viewengqrcode.do?id="+engSid);
            DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId03,url,{title:"二维码"},3);
        },
        GetEngTypeById:function (id) {
            var arr= $.grep(ProjectUtil.ProMenum.EngType,function (v) {
                return v.id==id;
            });
            return arr.length>0?arr[0]:{id:'',caption:'',pid:''};
        },
        GetEngTypeByPid:function (pid) {
            var arr= $.grep(ProjectUtil.ProMenum.EngType,function (v) {
                return v.pid==pid;
            });
            return arr;
        },
        GetEngTypeCountByPid:function (pid) {
            return this.GetEngTypeByPid(pid).length;
        },
        GetEngTypeNameById:function (id,id2) {
            if(id2==null)
            {
                return this.GetEngTypeById(id).caption;
            }
            else
            {
                return this.GetEngTypeById(id2).caption;
            }
        }
    },
    FlowMethod:{
        GetNomalFlowStatus:function(){
            return [100,200,400,500,800,1000,1100,1300,1400,1700,2000,2300];
        },
        GetFlowActions:function () {
            //【建设、施工、监理单位】（A）、档案馆（B）
            //流程： 工程登记(A) -> 报建确认（B） -> 文件登记（A） -> 预验收检查（B）-> 预验收审核（B） ->
            // 工程组卷（A）->案卷审核（B） -> 档案报送（A） -> 接收审核（B） -> 档案移交（B）-> 归档入库（B） -> 审定发证（B） -> 结束
            return [
                //登记待报建, 保存或送报建审核【业务单位】
                {actName:"送报建确认",orgType:"建设单位",currStatus:0,nextStatus:100,needOpinion:false,saveForm:true},
                {actName:"保存",orgType:"建设单位",currStatus:0,nextStatus:0,needOpinion:false,saveForm:true},
                //报建待审核
                {actName:"审核通过",orgType:"档案管理单位",currStatus:100,nextStatus:200,needOpinion:false,saveForm:false},
                {actName:"审核不通过",orgType:"档案管理单位",currStatus:100,nextStatus:300,needOpinion:true,saveForm:false},
                //调整后重新送报建审核
                {actName:"重新送报建确认",orgType:"建设单位",currStatus:300,nextStatus:100,needOpinion:false,saveForm:true},


                //报建审核通过, 自动进入文件登记,准备预验收申请
                {actName:"申请预验收",orgType:"建设单位",currStatus:200,nextStatus:400,needOpinion:false,saveForm:true},
                //预验收检查
                {actName:"预验收检查通过",orgType:"档案管理单位",currStatus:400,nextStatus:500,needOpinion:false,saveForm:false},
                {actName:"预验收检查不通过",orgType:"档案管理单位",currStatus:400,nextStatus:600,needOpinion:true,saveForm:false},
                //调整后重新预验收申请
                {actName:"重新预验收申请",orgType:"建设单位",currStatus:600,nextStatus:400,needOpinion:false,saveForm:false},


                //预验收检查通过,准备进入预验收审核
                /*{actName:"送预验收审核",orgType:"建设单位",currStatus:500,nextStatus:700,needOpinion:false,saveForm:false},*/
                //预验收审核（预验收检查通过后,省略送审动作,直接进入领导预验收审核环节）
                {actName:"审核通过",orgType:"档案管理单位",currStatus:500,nextStatus:800,needOpinion:false,saveForm:false},
                {actName:"审核不通过",orgType:"档案管理单位",currStatus:500,nextStatus:900,needOpinion:true,saveForm:false},
                //调整后重新预验收申请
                {actName:"重新预验收检",orgType:"建设单位",currStatus:900,nextStatus:500,needOpinion:false,saveForm:false},

                //预验收审核通过,自动进入工程组卷,准备送案卷审核
                {actName:"送案卷审核",orgType:"建设单位",currStatus:800,nextStatus:1000,needOpinion:false,saveForm:false},
                //案卷审核
                {actName:"审核通过",orgType:"档案管理单位",currStatus:1000,nextStatus:1100,needOpinion:false,saveForm:false},
                {actName:"审核不通过",orgType:"档案管理单位",currStatus:1000,nextStatus:1200,needOpinion:true,saveForm:false},
                //调整后重新送案卷审核
                {actName:"重新送案卷审核",orgType:"建设单位",currStatus:1200,nextStatus:1000,needOpinion:false,saveForm:false},


                //案卷审核通过,进入档案待报送,准备档案接收审核
                {actName:"档案报送",orgType:"建设单位",currStatus:1100,nextStatus:1300,needOpinion:false,saveForm:false},
                //档案接收审核
                {actName:"审核通过",orgType:"档案管理单位",currStatus:1300,nextStatus:1400,needOpinion:false,saveForm:false},
                {actName:"审核不通过",orgType:"档案管理单位",currStatus:1300,nextStatus:1500,needOpinion:true,saveForm:false},
                //调整后重新档案报送
                {actName:"重新档案报送",orgType:"建设单位",currStatus:1500,nextStatus:1300,needOpinion:false,saveForm:false},


                //档案接收审核通过,自动进入档案移交, 准备移交
                {actName:"移交档案",orgType:"档案管理单位",currStatus:1400,nextStatus:1700,needOpinion:false,saveForm:false},
                //自动进入档案归档，准备归档
                {actName:"档案归档",orgType:"档案管理单位",currStatus:1700,nextStatus:2000,needOpinion:false,saveForm:false},


                //档案已归档,进入审定发证,审定发证
                {actName:"审定发证",orgType:"档案管理单位",currStatus:2000,nextStatus:2300,needOpinion:false,saveForm:false}
            ];
        },
        GetCurrActions:function(orgType,currStatus){
           var acts = this.GetFlowActions();
           var currActs=$.grep(acts,function(a){
               return (orgType==a.orgType && currStatus==a.currStatus);
           });
           return currActs;
        },
        RenderingActions:function(op,containerId,orgType,currStatus){
            //<i-button type="primary" @click="handleSubmit('formValidate',false,true,true)"> 审核通过 </i-button>
            //{actName:"送报建确认",orgType:"建设单位",currStatus:0,nextStatus:1,needOpinion:false},
            var $c=$("#"+containerId);
            var currActs=this.GetCurrActions(orgType,currStatus);
            if(op=="view")
            {
                $("input").attr("readonly", true);
                $("select").attr("disabled", true);
            }
            else if(op=="onlysave")
            {
                $c.append('<i-button type="primary" style="margin-left: 8px;"  @click="handleFlowSubmit('+currStatus+','+currStatus+',false,true)" >保  存</i-button>');
            }
            else
            {
                $.each(currActs,function(i,o){
                    $c.append('<i-button type="primary" style="margin-left: 8px;"  @click="handleFlowSubmit('+o.currStatus+','+o.nextStatus+','+o.needOpinion+','+o.saveForm+')" >'+o.actName+'</i-button>');
                });
            }
            $c.append('<i-button type="ghost" @click="handleClose" style="margin-left: 8px"> 关  闭 </i-button>');
        },
        ///描述：更新审核过程的状态
        ///参数：currStatus 当前状态
        ///参数：nextStatus 下一个状态
        UpdateStatus:function(id,currStatus,nextStatus,opinion,isClose,callback){
            var sendData={sid:id,currStatus:currStatus,nextStatus:nextStatus,opinion:opinion};
            var url='/project/engineering/updatestatus.do';
            if(isClose==null || isClose==undefined) isClose=true;
            AjaxUtility.Post(url,sendData,function (result) {
                if(result.success){
                    if(typeof(callback)=='function')
                    {
                        callback(result);
                    }
                    var msg=ProjectUtil.ProMenum.FlowStatus[result.data];
                    if(msg.indexOf("待")>=0)
                        msg="工程已成功送"+msg.replace("待","");
                    else
                        msg="工程"+msg;
                    DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},msg+"！",function () {
                        debugger;
                        var pWindow=window.OpenerWindowObj;
                        if(pWindow){
                            pWindow.location.href=pWindow.location.href.replace("#","");
                            if(pWindow.OpenerWindowObj)
                                pWindow.OpenerWindowObj.location.href=pWindow.OpenerWindowObj.location.href.replace("#","");
                            if(isClose)
                                DialogUtility.Frame_CloseDialog(window);
                        }
                    });
                }
                else
                {
                    DialogUtility.Alert(window,DialogUtility.DialogAlertId,{},result.message);
                }
            },"json");
        },
        GetHandleUrl:function (id,engType,status,orgType,isView) {
            if(status<=300 && status!=200) {
                if(orgType=='档案管理单位')
                    url = "/project/engineering/detailfor1st.do?sid=" + id + "&engType=" + engType;
                else
                    url="/project/engineering/engineeringdetail.do?sid="+id+"&engType="+engType;
            }
            else if(status>=200 && status<=900 && status!=300 && status!=800)
            {
                url="/project/engineering/catalogfile/cataloglist.do?sid="+id;
            }
            else if(status==800 || status==1200)
                url="/project/engineering/archive/buildarchinfo.do?sid="+id;
            else if(status>=1000 && status!=1200)
                url="/project/engineering/archive/archlistbyeng.do?sid="+id;
            url=BaseUtility.BuildUrl(isView?url+"&op=view":url);
            return url;
        }
    },
    CommMethod:{
        //获取宽高
        GetWidthHeight:function(windowObj) {
            var w = 0;
            var h = 0;
            if(windowObj==null)windowObj=window;
            if (windowObj.innerWidth) {
                w = windowObj.innerWidth;
                h = windowObj.innerHeight;
            }
            else if ((windowObj.document.body) && (windowObj.document.body.clientWidth))
            {
                w = windowObj.document.body.clientWidth;
                h = windowObj.document.body.clientHeight;
            }
            return { width: w-50, height: h-50 };
        } ,
        viewFile:function (title,path) {
            var url=BaseUtility.BuildUrl("/project/engineering/catalogfile/downfile.do?path="+encodeURIComponent(path));
            DialogUtility.Frame_OpenIframeWindow(window,DialogUtility.DialogId04,
                url, {title:title,width:1024,height:768});
        }
    }
};


