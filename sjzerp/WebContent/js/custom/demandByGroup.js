var billId=sessionStorage.demandBillId;
var customerId=sessionStorage.customerId;
var customerNm=sessionStorage.customerNm;
var teamDemandId=sessionStorage.teamDemandId;
var billState;
var operType;//0通过1驳回

$(document).ready(function() {
	getDemandByGroup();//获取页面数据
	//编辑
	$(document).on("click", ".editNew", function(){
		var teamId=$(this).attr("teamId");
		sessionStorage.customNm=customerNm;
		window.location.href="addDemandNewInit.do?customerId="+customerId
		+"&teamId="+teamId+"&type=edit";
	});
	
	$(document).on("click", ".instead", function(){
		var teamId=$(this).attr("teamId");
		sessionStorage.customNm=customerNm;
		window.location.href="addDemandNewInit.do?customerId="+customerId
		+"&teamId="+teamId+"&type=add";
	});
	$(document).on("click", ".clear", function(){
		var teamId=$(this).attr("teamId");
		clear(teamId);
	});
});

//按作业组获取需求数据
function getDemandByGroup() {
	$.post("getDemandByGroup.do?", {
		billId:billId,
		customerId:customerId,
		teamDemandId:teamDemandId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			var result=data.data;
			result.roleId=roleId;
			//头部
//			$("#totalMoney").text(result.totalMoney)
			//班组需求明细
			$("#mainrow").empty();
			$("#detailTmpl").tmpl(result).appendTo("#mainrow");
		}
	}, "json");
}

function clear(teamId){
	confirm("确定要清空该作业组上报的物料吗？",function(){
		$.post("clearTeamDemand.do?", {
			teamDemandId:teamDemandId,
			teamId:teamId
		}, function(data) {
			tips(data.msg);
			setTimeout(function(){
				location.reload();
			},1000);
		}, "json");
	});
}
