var token = $('meta[name="_csrf"]').attr("content");
var header = $('meta[name="_csrf_hader"]').attr("content");
$(document).ajaxSend(function(e,xhr,opt){
    xhr.setRequestHeader(header,token);
});

iziToast.settings({
    timeout: 2000,
    // position: 'center',
    // imageWidth: 50,
    pauseOnHover: false,
    // resetOnHover: true,
    close: true,
    progressBar: true,
    // layout: 1,
    // balloon: true,
    // target: '.target',
    // icon: 'material-icons',
    // iconText: 'face',
    // animateInside: false,
    // transitionIn: 'flipInX',
    // transitionOut: 'flipOutX',
});

var pageClick = false;
function initMenu(menuDivId,menuType){
    var params = {"menuType" : menuType};
    $.ajax({
        url:"/sysMenuController/getMenuListShow",
        type:'POST',
        data : params,
        async : false,
        success:function (data) {
            //用户名
            var username = data.username;
            //菜单集合
            var menuList = data.menuList;
            var m = "";
            m +=  "<div class=\"container-fluid\">"+
                "<div class=\"navbar-header\">"+
                "<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\""+
                "data-target=\"#example-navbar-collapse\">"+
                "<span class=\"sr-only\">切换导航</span>"+
                "<span class=\"icon-bar\"></span>"+
                "<span class=\"icon-bar\"></span>"+
                "<span class=\"icon-bar\"></span>"+
                "</button>"+
                "<a class=\"navbar-brand\" href=\"/\">斤斤屋谜</a>"+
                "</div>"+
                "<div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">"+
                "<ul class=\"nav navbar-nav\">";
            if(menuList){
                for(var i=0;i<menuList.length;i++){
                    var menu = menuList[i];
                    if(menu.menuType == menuType){
                        m += "<li class=\"active\"><a href=\"" + menu.menuUrl + "\">" + menu.menuName + "</a></li>";
                    }else{
                        m += "<li><a href=\"" + menu.menuUrl + "\">" + menu.menuName + "</a></li>";
                    }
                }
            }
            m += "</ul>"+
                "<ul class=\"nav navbar-nav navbar-right\">"+
                "<li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span>"+
                "<span>" + username + "</span>"+
                "</a></li>"+
                "<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModel\"><span class=\"glyphicon glyphicon-log-in\"></span> 注销</a></li>"+
                "</ul> </div> </div>";
            var logout =  "<div class=\"modal fade\" id=\"logoutModel\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\"> " +
                "    <div class=\"modal-dialog\">                                                                                                    " +
                "        <div class=\"modal-content\">                                                                                               " +
                "            <div class=\"modal-header\">                                                                                            " +
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">                                " +
                "                    &times;                                                                                                         " +
                "                </button>                                                                                                           " +
                "                <h4 class=\"modal-title\" id=\"myModalLabel\">                                                                      " +
                "                   是否注销登录？                                                                                                     "      +
                "                </h4>                                                                                                               " +
                "            </div>                                                                                                                  " +
                "            <div class=\"modal-footer\">                                                                                            " +
                "                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消                                        "  +
                "                </button>                                                                                                           " +
                "                <button type=\"button\" class=\"btn btn-primary\" onclick=\"logoutform();\">                                        " +
                "                    确认                                                                                                            "   +
                "                </button>                                                                                                           " +
                "            </div>                                                                                                                  " +
                "        </div>                                                                                             " +
                "    </div>                                                                                                        " +
                "</div>";
            $("#"+menuDivId).html(m+logout);
        }
    });
}
//退出登录
function logoutform(){
    $("#logoutform").submit()
}

function getSearchParam(eid){
    var queryVo = {};
    var hasValue = false;
    $.each($('#' + eid + ' input,select'), function(i, v){
        hasValue = true;
        var value = v.value;
        if(value) {
            queryVo[v.id] = value ;
        };
    });
    return queryVo;
}

function initList(initParam){
    var params = getSearchParam(initParam.searchid);
    $.ajax({
        url:initParam.url,
        type:'POST',
        data : params,
        async : false,
        success:function (data) {
            pageClick = false;
            var result = data.result;
            if(result.length > 0){
                loadListData(result,initParam.tableid,initParam.opt);
                initPage(initParam,data.totalPage);
                //$(".trigger-success").trigger("click");
                showSuccessMsg('数据加载成功...');
                $("html,body").animate({scrollTop:$("#"+initParam.tableid).offset().top}, 800);
            }else{
                //数据为空清空显示区域
                $("#"+initParam.tableid + " tbody").html("");
                //数据为空隐藏分页插件
                $('#' + initParam.pageidc).remove();
                $("#" + initParam.pageidp).append("<ul id=\""+initParam.pageidc+"\" class=\"pagination-md\"></ul>");
                showErrorMsg('未查询到数据');
            }
        }
    });
}

function loadListData(result,tableid,opt){
    var fields = [];
    $('#' + tableid + ' thead th').each(function(){
        var field = $(this).attr("field");
        if($(this).attr("datatype")){
            fields.push(field + "|" + $(this).attr("datatype"));
        }else{
            fields.push(field);
        }
    });
    var coment = "";
    for(var i=0;i<result.length;i++){
        coment += "<tr>";
        for(var j=0;j<fields.length;j++){
            var f2 = fields[j].split("|");
            var field = f2[0];
            var v = result[i][''+ field +''];
            if(field == "opt"){
                if(opt && opt != ""){
                    coment += "<td>";
                    for(var x=0;x<opt.length;x++){
                        if(x == 0){
                            coment += "<a style=\"cursor: pointer\" onclick=\"" + opt[x].functionName + "("+ result[i]['id'] +")\">"+ opt[x].showContent +"</a>";
                        }else{
                            coment += "&nbsp;<a style=\"cursor: pointer\" onclick=\"" + opt[x].functionName + "("+ result[i]['id'] +")\">"+ opt[x].showContent +"</a>";
                        }
                    }
                    //alert("<span onclick='add('1');' ></span></td>");
                    coment += "</td>";
                }
            }else{
                if(v){
                    if(field == "id"){
                        coment += "<td style=\"display: none\">" + v + "</td>";
                    }else{
                        if(f2.length > 1){
                            var datatype = f2[1];
                            if(datatype == "date"){
                                var newtime = new Date(v);
                                coment += "<td>" + formatDate(newtime,"yyyy-MM-dd") + "</td>";
                            }else if(datatype = "datetime"){
                                var newtime = new Date(v);
                                coment += "<td>" + formatDate(newtime,"yyyy-MM-dd HH:mm:ss") + "</td>";
                            }
                        }else{
                            coment += "<td>" + v + "</td>";
                        }
                    }
                }else{
                    coment += "<td></td>";
                }
            }
        }
        coment += "</tr>";
    }
    $("#"+initParam.tableid + " tbody").html(coment);
}

function initPage(initParam,totalPages){
    $('#' + initParam.pageidc).remove();
    $("#" + initParam.pageidp).append("<ul id=\""+initParam.pageidc+"\" class=\"pagination-md\"></ul>");
    $('#' + initParam.pageidc).twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        first:'&lt;&lt;',
        prev:'&lt;',
        next:'&gt;',
        last:'&gt;&gt;',
        onPageClick: function (event, page) {
            if(pageClick){
                reloadTable(initParam,page);
            }else{
                pageClick = true;
            }
        }
    });
}

function reloadTable(initParam,page){
    var params = getSearchParam(initParam.searchid);
    params.page = page;
    $.ajax({
        url:initParam.url,
        type:'POST',
        data : params,
        async : false,
        success:function (data) {
            pageClick = true;
            var result = data.result;
            if(result.length > 0){
                loadListData(result,initParam.tableid,initParam.opt);
                showSuccessMsg('数据加载成功...');
                $("html,body").animate({scrollTop:$("#"+initParam.tableid).offset().top}, 800);
            }else{
                //数据为空清空显示区域
                $("#"+initParam.tableid + " tbody").html("");
                //数据为空隐藏分页插件
                $('#' + initParam.pageidc).remove();
                $("#" + initParam.pageidp).append("<ul id=\""+initParam.pageidc+"\" class=\"pagination-md\"></ul>");
                showErrorMsg('未查询到数据');
            }
        }
    });
}

//---------------------------------------------------
// 日期格式化
// 格式 YYYY/yyyy/YY/yy 表示年份
// MM/M 月份
// W/w 星期
// dd/DD/d/D 日期
// hh/HH/h/H 时间
// mm/m 分钟
// ss/SS/s/S 秒
//---------------------------------------------------
function formatDate(fd,formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,fd.getFullYear());
    str=str.replace(/yy|YY/,(fd.getYear() % 100)>9?(fd.getYear() % 100).toString():'0' + (fd.getYear() % 100));
    var month = fd.getMonth()+1;
    str=str.replace(/MM/,month>9?month.toString():'0' + month);
    str=str.replace(/M/g,month);

    str=str.replace(/w|W/g,Week[fd.getDay()]);

    str=str.replace(/dd|DD/,fd.getDate()>9?fd.getDate().toString():'0' + fd.getDate());
    str=str.replace(/d|D/g,fd.getDate());

    str=str.replace(/hh|HH/,fd.getHours()>9?fd.getHours().toString():'0' + fd.getHours());
    str=str.replace(/h|H/g,fd.getHours());
    str=str.replace(/mm/,fd.getMinutes()>9?fd.getMinutes().toString():'0' + fd.getMinutes());
    str=str.replace(/m/g,fd.getMinutes());

    str=str.replace(/ss|SS/,fd.getSeconds()>9?fd.getSeconds().toString():'0' + fd.getSeconds());
    str=str.replace(/s|S/g,fd.getSeconds());

    return str;
}

function showSuccessMsg(msg){
    iziToast.success({
        title: 'OK',
        position: 'topCenter',
        //transitionIn: 'bounceInLeft'
        // iconText: 'star',
        //onOpen: function(){
        //    console.log('callback abriu! success');
        //},
        //onClose: function(){
        //    console.log("callback fechou! success");
        //}
        message: msg
    });
}

function showErrorMsg(msg){
    iziToast.error({
        title: 'Error',
        message: msg,
        position: 'topCenter',
        transitionIn: 'fadeInDown'
    });
}
//表单非空校验
function validIsEmpty(formId){
    var hasEmpty = true;
    $.each($('#' + formId + ' input,select'), function(i, v){
        if(v.id != ''){
            var type = $("#" + v.id).attr("type");
            if(type != "hidden"){
                //不校验隐藏域
                var allowEmpty = $("#" + v.id).attr("allowEmpty");
                if(allowEmpty != "true"){
                    //不验证允许为空字段
                    var value = v.value;
                    if(value == '') {
                        $("#"+v.id).parent().parent("div").addClass("has-error");
                        $("#"+v.id).parent().parent("div").removeClass("has-success");
                        if(hasEmpty){
                            //alert(v.id);
                            hasEmpty = false;
                        }
                    }else{
                        $("#"+v.id).parent().parent("div").addClass("has-success");
                        $("#"+v.id).parent().parent("div").removeClass("has-error");
                    }
                }else{
                    $("#"+v.id).parent().parent("div").addClass("has-success");
                }
            }
        }
    });
    return hasEmpty;
}
//确认对话框
function showConfirmDialog(title,content,confirmFunction,dialogId){
    var m = "";
    var body = document.body;
    if(dialogId != undefined){ }else{dialogId = "confirmModal";}
    m+="<div class=\"modal fade\" id=\""+ dialogId +"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"
    +"<div class=\"modal-dialog\"> <div class=\"modal-content\">";
    if(title != ''){
        m+="<div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"
        +"<h4 class=\"modal-title\" id=\"myModalLabel\">" + title + "</h4></div>";
    }
    if(content != ''){
        m+="<div class=\"modal-body\">"+content+"</div>";
    }
    m+="<div class=\"modal-footer\"><button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>";
    if(confirmFunction != ''){
        m+="<button type=\"button\" onclick=\""+ confirmFunction +";\" class=\"btn btn-primary\">确定</button>";
    }
    m+="</div></div></div></div>";
    var defaultConfirmDialog = document.getElementById("defaultConfirmDialog");
    if(defaultConfirmDialog != null){
        defaultConfirmDialog.innerHTML = m;
    }else{
        var body = document.body;
        var defaultConfirmDialog = document.createElement("div");
        defaultConfirmDialog.id = "defaultConfirmDialog";
        defaultConfirmDialog.innerHTML = m;
        body.appendChild(defaultConfirmDialog);
    }
    $("#" + dialogId).modal("show");
}

