var commonJs = {
};



//全局变量
commonJs.constant = {
    host_url: "http://localhost:9000/mall",
    uploadUrl:"http://api.125966.com/qhb_admin/upload"
};

commonJs.getDataHandle = function(url, _func) {
    console.log("getdata" + url);
    var _data = {};
    $.get(url, function(data) {
        _func(data);
    });

};


commonJs.setCookie = function(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + JSON.stringify(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}





commonJs.createNode = function createNode(_tagName, _class, attr,_text) {

    var goodsElement = document.createElement(_tagName);

    if (_class != null) {
        goodsElement.setAttribute("class", _class);
    }

    if (attr != null) {
        for (_aName in attr) {
            goodsElement.setAttribute(_aName, attr[_aName]);
        }
    }

    if(_text!=null){
      var _textNode = document.createTextNode(_text);
      goodsElement.appendChild(_textNode);
    }

    return goodsElement;
}





commonJs.clone = function(obj) {
    function Clone() {}
    Clone.prototype = obj;
    var o = new Clone();
    for (var a in o) {
        if (typeof o[a] == "object") {
            o[a] = clone3(o[a]);
        }
    }
    return o;
}

commonJs.loadFormValue = function (__formId,__selector){
            _felementArray = $("input,textarea,select");
            var _jsonText = "{";
            for(var tmp in _felementArray){
                var obj = _felementArray[tmp];

                if(containts(__selector,obj.name)||__selector==null){
                    var valueObj = obj.value;
                    if(obj.name=="content"){
                        valueObj = encodeURI(obj.value);
                    }
                    _jsonText += '"'+obj.name+'"'+":"+'"'+valueObj+'",';
                }
            }
            _jsonText = _jsonText.slice(0,-1)+"}";
            return eval("("+_jsonText+")");
}


commonJs.fillForm = function(_jsonObj,_formObj){
    for(var tmp in _jsonObj){
       var inputObj =  _formObj.children().find("[name="+tmp+"]");

        if(tmp=="content"){
            $('.wysihtml5-sandbox').contents().find("body").html(decodeURI(_jsonObj[tmp]));
        }

        if(inputObj[0]!=null) {
            if (inputObj[0].tagName == "SELECT") {

            }
        }
        inputObj.val(_jsonObj[tmp]);
    }

}
commonJs.clearForm = function (_id){

    console.log($(_id));

    var inputs = $(_id).find("input");
    console.log(inputs);
    $.each(inputs,function(){
        this.value="";
    })
}

window.onload=function(){
    checkLogin();
}

function checkLogin(){

    if($.cookie("user_info")==null){
        window.location.href="/admin/pages/login/login.html";
        return ;
    }

    var c = JSON.parse($.cookie("user_info"));
    var user_pannel = $(".user-panel");

    user_pannel.find(".info").find("p").text(c.nick_name);


}

commonJs.getData = function(url,param){
    console.log("getData:"+url+","+JSON.stringify(param));
    var data = {};

    $.ajax({
        type: "GET",
        url:url,
        data:param,
        dataType: "json",
        async: false,
        contentType: "application/json",
        success: function (result) {
            data= result;
        }
    });

    return data.data;
};

commonJs.hide = function (__objId){
    var obj = document.getElementById(__objId);
    $(obj).style("display","none");
}
commonJs.show = function (__objId){
    var obj = document.getElementById(__objId);
    console.log(obj);
    $(obj).css("display","block");
}

function containts(__selector,obj){
    var str_array = __selector.split(",");
    for(var i in str_array){
        if( str_array[i]==obj){
            return true;
        }
    }
    return false;

}

commonJs.replace = function(oldStr,rep_char,target_char){
    var reg = new RegExp(rep_char,"g");
    var newStr = oldStr.replace(reg,target_char);

    return newStr;

}

commonJs.initTable = function (table_id,tableData) {

    console.log("渲染表格:"+table_id);
    if (this.datatable) {
        this.datatable.destroy();
    }
    this.datatable = $(table_id).DataTable(tableData);
    $(table_id+' tbody').on('click', 'tr', function () {
        $("tr").removeClass('selected');
        $(this).toggleClass('selected');
    });
}

commonJs.getTableCellValue = function (col,id){
    if(id){
        return  $(id)[0].cells[col].innerText;

    }
   return  $(".selected")[0].cells[col].innerText;
}


commonJs.checkDate = function (time){
    var timestamp = Date.parse(new Date());
    var diffStamp = timestamp - time;

    if(diffStamp<3600){
        return diffStamp/60 +"分";
    }else if(diffStamp<86400){
        return diffStamp/3600 +"小时";

    }else{
        return diffStamp/86400 +"天";
    }
    return 0;
}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}




function checkSelected(){
    if($(".selected")[0] ==undefined){
        alert("请选中一篇文章!");
        return false;
    }else{
        return true;
    }
}

function checkResult(data){
    if(data.status==0){
        alert("操作成功!");
    }else{
        alert(data.msg);
    }
}


