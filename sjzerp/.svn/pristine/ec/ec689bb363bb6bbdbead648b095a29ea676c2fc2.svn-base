$(document).ready(function() {
	$(document).on("click", "#home", goHome);
	$(document).on("click", "#logout", logout);
	$(document).on("click", "#mpwd", mpwd);
	$(document).on("click", "#sub", sub);
	$(document).on("click", "#editNm", editNm);
	$(document).on("click", "#edit", edit);
});

function goHome(){
	window.location.href="../home/home.do";
}

function logout(){
	sessionStorage.clear();
	$.post("../login/logout.do?", {
	}, function(data) {
		if(data.code==1000){
			window.location.href="../";
		}else{
			tips(data.msg);
		}
	}, "json");
}

//修改密码弹框
function mpwd(){
	$("#pModal").modal();
	//清空输入框内容
	$("#oldPwd").val("");		 
	$("#newPwd").val("");   	  
	$("#confirmPwd").val("");
}
//修改名称弹框
function editNm(){
	$("#nModal").modal();
}

function edit(){
	var userName = $.trim($("#userName").val());//获取名称
	if(userName == ''){
		tips('名称不能为空');
		$("userName").focus();
		return;
	}
	$.post("../user/updateNm.do?",{
		userName:userName,
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
			if(data.code==207)
				$("#userName").focus();
		} else{
			$("#nModal").modal('hide');
			$("#headUserNm").text(userName);
		}
	}, "json");
}

function sub(){
	var oldPwd=$.trim($("#oldPwd").val());		  //原始密码
	var newPwd=$.trim($("#newPwd").val());   	  //新密码
	var confirmPwd=$.trim($("#confirmPwd").val());//确认密码
	
	if(oldPwd==''){
		tips('密码不能为空');
		$("#oldPwd").focus();
		return;
	}
	if(newPwd=='' && confirmPwd==''){
		tips('密码不能为空');
		$("#confirmPwd").focus();
		return;
	}
	//判断密码是否一致
	if(newPwd != confirmPwd){
		tips('输入密码不一致');
		return;
	}
	$.post("../user/updatePwd.do?", {
		oldPwd:oldPwd,
		confirmPwd:confirmPwd,
	
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
			if(data.code==207)
				$("#oldPwd").focus();
		} else{
			$("#pModal").modal('hide');
		}
	}, "json");
}
