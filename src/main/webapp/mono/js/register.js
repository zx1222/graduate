window.onload = function(){

	$("#submit_btn").click(function(){
		var _formObj = document.getElementById("register-form");
		var _formValue = loadFormValue(_formObj);
		var url = commonJs.constant.host_url+"/user/register";
		$.post(url,_formValue,function(data){
			if(data.status==0){
				$("#register_result_text").text("注册成功!! 去邮箱激活");
				$("#register_result_text").removeClass("result-text-failure");

				$("#register_result_text").addClass("result-text-success");
			}else{
				$("#register_result_text").text("注册失败！ 请核对信息");
				$("#register_result_text").addClass("result-text-failure");
			}
		});

		
	});


}


	function loadFormValue(_formObj){
		
		var _inputArray = _formObj.getElementsByTagName("input");
		
		var _jsonText = "{";
		for(var tmp in _inputArray){
			var obj = _inputArray[tmp];
			if(obj.type=="text"||obj.type=="email"||obj.type=="passowrd"){
				
				_jsonText += '"'+obj.name+'"'+":"+'"'+obj.value+'",';
			}
		}
		_jsonText = _jsonText.slice(0,-1)+"}";

		return eval("("+_jsonText+")");
	}