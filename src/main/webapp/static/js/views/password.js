$(function(){
	$("#varifyCode").hide();
	var passwordDialog,passwordForm,getVerify,data;
	passwordDialog = $("#passwordDialog");
	passwordForm= $("#passwordForm");
	getVerify = $("#getVerify");
	passwordDialog.dialog({

	})

	getVerify.linkbutton({
		onClick:function (){
			passwordForm.form("submit",{
				url:"/changePassword",
				success :function (param){
					data = $.parseJSON(param);
				}
			})
			$("#info").text("语音验证码发送成功")
		}
	})

	$("#submit").linkbutton({
		width:120,
		height: 50,
		onClick: function (){
			var code = $("#varifyCode").textbox("getValue");
			if(code != data.XXXXXXX){
				 $("#errorMsg").text("验证码错误,请重新发送")
				return;
			}
            passwordForm.form("submit",{
            	url:"/change",
				success: function (data){
            		data = $.parseJSON(data)
					if(data.success){
						 $.messager.alert("温馨提示",data.msg,"info",function (){
						 	window.location.href="/logout";
						 });
					}else{
						 $.messager.alert("温馨提示",data.msg,"error");
					}
				}
			})
		}
	})


	$("#varifyCode").textbox({
	})
})

function checkPassword(){
	
	var inputPassword = $(this).val();
	var oldPassword  = $(this).data("pwd");
	console.log(oldPassword);
	console.log(inputPassword);
	if(oldPassword != inputPassword){
		$("#error").text("密码错误");
	}else{
		$("#error").text("");
	}

}
