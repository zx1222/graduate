window.onload = function(){

	$("#submit-btn").click(function(){
		var _formObj = document.getElementById("register-form");
		var _formValue = loadFormValue(_formObj);
		var url = "/user/register";
		$.post(url,_formValue,function(data){
			if(data.status==0) {
				alert("注册成功!请去邮箱激活!");
				window.location.href="/mono/html/home.html";
			}
		});

		
	});


}


	function loadFormValue(_formObj){
		
		var _inputArray = _formObj.getElementsByTagName("input");
		
		var _jsonText = "{";
		for(var tmp in _inputArray){
			var obj = _inputArray[tmp];
			if(obj.type=="text"||obj.type=="email"||obj.type=="password"){
				
				_jsonText += '"'+obj.name+'"'+":"+'"'+obj.value+'",';
			}
		}
		_jsonText = _jsonText.slice(0,-1)+"}";
		console.log(_jsonText);
		return eval("("+_jsonText+")");
	}