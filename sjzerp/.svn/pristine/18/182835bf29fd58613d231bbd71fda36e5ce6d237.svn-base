$(document).ready(function() {
	local();// 读取本地信息
	$(document).on("click", "#login", login);
	$(document).on("click", "#relSure", relSure);// 确定选择一个职务
	
	$(document).on("keydown", "#pwd", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			login();
	});// 确定选择一个职务
	$(document).on("keydown", "#userAccount", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			login();
	});// 确定选择一个职务
	
});

// 读取本地信息
function local() {
	var pwd = localStorage.getItem("pwd");
	if (pwd != null && pwd != '') {
		$("#remPwd").attr("checked", true);
		$("#pwd").val(pwd);
	} else {
		$("#remPwd").attr("checked", false);
		$("#pwd").val("");
	}
	var userAccount = localStorage.getItem("userAccount");
	$("#userAccount").val(userAccount);
}


function login() {
	var userAccount = $.trim($("#userAccount").val());
	var pwd = $.trim($("#pwd").val());
	if (userAccount == '') {
		tips('请填写用户名');
		return;
	}
	if (pwd == '') {
		tips('请填写密码');
		return;
	}
	load();
	$.post("login/login.do?", {
		userAccount : userAccount,
		pwd : pwd
	}, function(data) {
		closeAll();
		if (data.code == 204) {// 单角色
			saveLocal(userAccount, pwd);// 保存账户名和密码
			goMain();// 跳转主画面
		} else if (data.code == 205) {// 多角色
			// 弹出角色选择框
			$("#relbody").empty();
			$("#relTmpl").tmpl(data.data).appendTo("#relbody");
			$("#selectRel").modal();
		} else {
			tips(data.msg);
		}
	}, "json");
}

// 保存账户名和密码
function saveLocal(userAccount, pwd) {
	localStorage.setItem("userAccount", userAccount);
	if ($("#remPwd").get(0).checked)// 如果勾选了保存密码
		localStorage.setItem("pwd", pwd);
	else
		localStorage.setItem("pwd", "");
}

// 跳转主画面
function goMain() {
	window.location.href = "home/home.do";
}

// 选择职务
function relSure() {
	var selectRadio = $('input:radio[name="relItem"]:checked');
	if (selectRadio.length == 0) {
		tips("请选择一个职务");
		return;
	}
	$.post("login/selectJob.do?", {
		relId : selectRadio.val(),
		userId : selectRadio.attr("u")
	}, function(data) {
		if (data.code == 1000) {
			saveLocal(data.data.userAccount, data.data.pwd);
			goMain();// 跳转主画面
		} else {
			tips(data.msg);
		}
	}, "json");
}