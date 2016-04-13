var commonJs = {};


//全局变量
commonJs.constant = {
    host_url: "http://localhost:9000/mall"
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
    document.cookie = c_name + "=" + escape(value) +
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

commonJs.loadFormValue = function (_formObj){
        
        var _inputArray = _formObj.getElementsByTagName("input");
        
        var _jsonText = "{";
        for(var tmp in _inputArray){
            var obj = _inputArray[tmp];
            if(obj.type=="text"||obj.type=="email"||obj.type=="password"){
                
                _jsonText += '"'+obj.name+'"'+":"+'"'+obj.value+'",';
            }
        }
        _jsonText = _jsonText.slice(0,-1)+"}";

        return eval("("+_jsonText+")");
    }