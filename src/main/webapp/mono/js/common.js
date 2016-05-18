var commonJs = {
};



//全局变量
commonJs.constant = {
    uploadUrl:"http://api.125966.com/qhb_admin/upload"
};

commonJs.getData = function(url, _func) {
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
    console.log(_jsonText);
    return eval("("+_jsonText+")");
}


commonJs.fillForm = function(_jsonObj,_formObj){
    for(var tmp in _jsonObj){
        var inputObj =  _formObj.children().find("[name="+tmp+"]");

        if(tmp=="content"){
            $('.wysihtml5-sandbox').contents().find("body").html(decodeURI(_jsonObj[tmp]));
        }
        inputObj.val(_jsonObj[tmp]);
    }



}

window.onload=function(){

    checkLogin();
    commonJs.user_info = JSON.parse(decodeURI($.cookie("user_info")));
}

function checkLogin(){
    var c = JSON.parse($.cookie("user_info"));
    if(c==null){
        window.location.href="/admin/pages/login/login.html";
    }


}

commonJs.getData = function(url,param){

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
    $(obj).style("display","display");
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

function logout (){
    $.cookie('cookieName',null,{path:'/'})
    window.location.href="/index.html";
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


