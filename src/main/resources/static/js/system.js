var token = $('meta[name="_csrf"]').attr("content");
        var header = $('meta[name="_csrf_hader"]').attr("content");
$(document).ajaxSend(function(e,xhr,opt){
            xhr.setRequestHeader(header,token);
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
            if(username){

            }else{
                alert("登录信息已失效，请重新登录");
                //window.location.href="/login";
                logoutform();
            }
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
        var queryVo = "{";
        var hasValue = false;
        $.each($('#' + eid + ' input,select'), function(i, v){
            hasValue = true;
            var value = v.value;
            if(value) {
                queryVo += "\"" + v.id + "\":\"" + value + "\","
            };
        });
        if(hasValue){
            queryVo = queryVo.substring(0,queryVo.length-1);
        }
        return $.parseJSON(queryVo+"}");
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
            loadListData(result,initParam.tableid);
            initPage(initParam,data.totalPage + 1);
         }else{
            //数据为空清空显示区域
            $("#"+initParam.tableid + " tbody").html("");
            //数据为空隐藏分页插件
            $('#' + initParam.pageidc).remove();
            $("#" + initParam.pageidp).append("<ul id=\""+initParam.pageidc+"\" class=\"pagination-md\"></ul>");
         }
      }
    });
}

function loadListData(result,tableid){
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
            if(v){
                if(field == "id"){
                    coment += "<td style=\"display: none\">" + v + "</td>";
                }else{
                    if(f2.length > 1){
                       var datatype = f2[1];
                       if(datatype == "date"){
                          var newtime = new Date(v);
                          coment += "<td>" + formatDate(newtime,"yyyy-MM-dd") + "</td>";
                       }
                    }else{
                       coment += "<td>" + v + "</td>";
                    }
                }
            }else{
                coment += "<td></td>";
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
                loadListData(result,initParam.tableid);
             }else{
                //数据为空清空显示区域
                $("#"+initParam.tableid + " tbody").html("");
                //数据为空隐藏分页插件
                $('#' + initParam.pageidc).remove();
                $("#" + initParam.pageidp).append("<ul id=\""+initParam.pageidc+"\" class=\"pagination-md\"></ul>");
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

        str=str.replace(/MM/,fd.getMonth()>9?fd.getMonth().toString():'0' + fd.getMonth());
        str=str.replace(/M/g,fd.getMonth());

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
