var curPage=1;//当前页
var totalPage=1;//总页数
var g_name="";//作业组名称
var curUserId=-1;//当前选中的userid
var customerteamList;//当前作业组列表
var headAtNo; //客户id
$(document).ready(function() {
	 headAtNo=$.trim($("#headAtNo").val());
	bindPage(getCustomerteamList);
	$(document).on("click", "#addCustomerteam", function(){
		addCustomerteam(-1);
	});
	
	//enter名称键搜索
	$(document).on("keydown", "#teamNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			cSure();
	});
	
	$(document).on("click", "#cSure", cSure);//模态框确定按钮
	$(document).on("click", ".edit", function(){
		var customerteamId=$(this).attr("atNo");
		addCustomerteam(customerteamId)
	});
	
	$(document).on("click", ".del", function(){
		var customerteamId=$(this).attr("atNo");
		delCustomerteam(customerteamId);
	});
	
});

//用户列表
function getCustomerteamList(headAtNo) {
	$.post("../customerteam/getCustomerteamList.do?", {
		headAtNo:headAtNo
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			customerteamList=data.data.customerteamList;
			$("#customerteamTmpl").tmpl(customerteamList).appendTo("#tbody");
			
		}
	}, "json");
}


function addCustomerteam(customerteamId){
	curUserId=customerteamId
	if(curUserId==-1){
		//添加客户
		$("#cHead").text("添加工作组");
		clearUModal();
	}else{
		$("#cHead").text('编辑工作组');
		setValueToUModal();
	}
	
	$("#cModal").modal();
}

//清空模态对话框
function clearUModal(){
	$("#teamNm").val("");
}



//模态对话框赋值
function setValueToUModal(){
	$.post("../customerteam/getCustomerteamById.do?", {
		customerteamId:curUserId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else{
			var u=data.data;
			$("#teamNm").val(u.teamNm);
		}
	}, "json");
}



function cSure(){
	var teamNm=$.trim($("#teamNm").val());
	if(teamNm==''){
		tips('名称不能为空');
		$("#teamNm").focus();
		return;
	}
	$.post("../customerteam/addCustomerteam.do?", {
		customerteamId:curUserId,
		teamNm:teamNm,
		headAtNo:headAtNo
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		     if(data.code==206)
				$("#teamNm").focus();
		} else{
			tips(data.msg);
			$("#cModal").modal('hide');
			getCustomerteamList(headAtNo);
		}
	}, "json");
}

function delCustomerteam(customerteamId){
	confirm("确定要删除该工作组吗？",function(){
		$.post("../customerteam/delCustomerteam.do?", {
			customerteamId:customerteamId
		}, function(data) {
			tips(data.msg);
			if (data.code==1000) {
				getCustomerteamList(headAtNo);
			}
		}, "json");
	});
}

