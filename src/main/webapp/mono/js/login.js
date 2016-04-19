window.onload = function(){

	$("#submit_btn").click(function(){
		var _formObj = document.getElementById("login_form");
		var _formValue = loadFormValue(_formObj);
		var url = "/user/login/email";
		
		$.post(url,_formValue,function(result){
			if(result.status==0){
				alert("登录成功！！");
				console.log(result.data);
				$.cookie("user_info",JSON.stringify(result.data),{expired:1,path:"/"});
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

		return eval("("+_jsonText+")");
	}