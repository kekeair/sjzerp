var curPage=1;//当前页
var totalPage=1;//总页数
var g_state=sessionStorage.managerDemandListState||-1;//状态
var g_stime=sessionStorage.managerDemandListStime||"";//开始时间
var g_etime=sessionStorage.managerDemandListEtime||"";//结束时间
var g_customerId=sessionStorage.managerDemandListCustomerId||"-1";
var submitBillId;
var billState;
var operType;
var teamBillId;
var printType;  //1:新录入方是的打印  0:之前的录入方式
var exportOutType; //0:打印 1:导出


$(document).ready(function() {
	$("#state").val(g_state);
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	$("#customer").val(g_customerId);
	if(roleId==9){//经理
		$("#add").show();
	}else{
		$("#add").hide();
	}
	getDemandListH();//需求上报列表
	bindPage(getDemandListH);
	$(document).on("click", "#search", search);

	$(document).on("click", ".edit", function(){
		var billId=$(this).attr('billId');
		var customerId=$(this).attr('customerId');
		var customerNm=$(this).attr('customerNm');
		var teamDemandId=$(this).attr('teamDemandId');
		sessionStorage.demandBillId=billId;
		sessionStorage.customerId=customerId;
		sessionStorage.customerNm=customerNm;
		sessionStorage.teamDemandId=teamDemandId;
		window.location.href="demandByGroupInit.do";
	});
	
	$(document).on("click", ".viewByCustomer", function(){
		var billId=$(this).attr('billId');
		sessionStorage.demandBillId=billId;
		window.location.href="viewByCustomerInit.do";
	});
	$(document).on("click", ".viewByGoods", function(){
		var billId=$(this).attr('billId');
		sessionStorage.billId=billId;
		window.location.href="viewByGoodsInit.do";
	});
	
	$(document).on("click", ".selectGoods", function(){
		var billId=$(this).attr('billId');
		var customerId=$(this).attr('customerId')
		sessionStorage.billId=billId;
		sessionStorage.customerId=customerId;
		window.location.href="selectGoodsInit.do";
	});
	
	$(document).on("click", ".reviewProcess", function(){
		var billId=$(this).attr('billId');
		getReviewProcess(billId);
	});
	$(document).on("click", ".del", function(){
		var billId=$(this).attr('billId');
		delDemandBill(billId);
	});
	$(document).on("click", ".submit", function(){
		submitBillId=$(this).attr('billId');
		billState=$(this).attr("billState");
		operType=0;
		$("#remark").val("");
		if(roleId==9 && billState==0){
			$("#varyTime").show();
			$("#submitTime").val(getDateStr(0));
		}else{
			$("#varyTime").hide();
		}
		$("#submitModal").modal();
	});
	$(document).on("click", "#excelOut", excelOut);//导出
	
	
	$(document).on("click", "#marketBill", marketBill);		 //销售单
	$(document).on("click", "#printSure", printSure);		 //打印销售单
	$(document).on("click", "#ExoertOutSure", ExoertOutSure);//导出销售单
	//按分类打印
	$(document).on("click", "#classTeam", function(){
		var customerTeamId = $("#customerTeam").val();//客户(工作组Id)
		if(customerTeamId == -1){
			tips('请选择一个客户');
			return;
		}
		sessionStorage.teamBillId=teamBillId;//订单Id
		sessionStorage.customerTeamId=customerTeamId;//客户(工作组Id)
		window.location.href="../demand/showGoodsByclassInit.do?teamBillId="+teamBillId+"&customerTeamId="+customerTeamId+"";
	});
	$(document).on("click", ".refuse", function(){
		submitBillId=$(this).attr('billId');
		billState=$(this).attr("billState");
		operType=1;
		$("#remark").val("");
		$("#varyTime").hide();
		$("#submitModal").modal();
	});
	//工作组
	$(document).on("click", ".daochu", function(){
		teamBillId=$(this).attr('billId');
		exportOutType=$(this).attr('exportOutType');
		$.post("../customerteam/getCustomTeamNameList.do",{teamBillId:teamBillId},function(data){
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				$("#customerTeam").empty();
				$("#customerTeamTmpl").tmpl(data.data).appendTo("#customerTeam");
			}
		},"json");
		$("#teamModal").modal();
	});
	
	//出库单
	$(document).on("click", ".OutBillD", function(){
		teamBillId=$(this).attr('billId');
		$.post("../customerteam/getCustomTeamNameList.do",{teamBillId:teamBillId},function(data){
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				$("#customTeam").empty();
				$("#customerTeamTmpl").tmpl(data.data).appendTo("#customTeam");
			}
		},"json");
		printType=0;
		$("#customTeamModal").modal();
	});
	
	
	//出库单
	$(document).on("click", ".OutBillDNew", function(){
		teamBillId=$(this).attr('billId');
		sessionStorage.customerNm = $(this).attr('customerNm');//客户名称
		sessionStorage.customerId = $(this).attr('customerId');//客户ID
		$.post("../customerteam/getCustomTeamNameList.do",{teamBillId:teamBillId},function(data){
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				$("#customTeam").empty();
				$("#customerTeamTmpl").tmpl(data.data).appendTo("#customTeam");
			}
		},"json");
		printType=1;
		$("#customTeamModal").modal();
		
	});
	
	
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
	//打印
	$(document).on("click", "#teamSure", function(){
		var bill_Id = $("#customerTeam").val();
		if(bill_Id == -1){
			tips('请选择一个客户');
			return;
		}
		
			window.open("../demand/exportOutDemandBill.do?bill_Id="+bill_Id+"&teamBillId="+teamBillId+"");
	
		$("#teamModal").modal("hide");
	});
	
	
	//导出
	$(document).on("click", "#exportSure", function(){
		var bill_Id = $("#customerTeam").val();
		if(bill_Id == -1){
			tips('请选择一个客户');
			return;
		}
		window.location="../demand/exportOutBill.do?bill_Id="+bill_Id+"&teamBillId="+teamBillId+"";
		$("#teamModal").modal("hide");
	});
	
	

	
	//出库详情(new)
	$(document).on("click", "#customTeamSure", function(){
		var customerTeamId = $("#customTeam").val();
		if(customerTeamId == -1){
			tips('请选择一个客户');
			return;
		}
		sessionStorage.demandBillId=teamBillId;//账单ID
		sessionStorage.customerTeamId=customerTeamId;//作业组id
		sessionStorage.customerTeamNm=$("#customTeam").find("option:selected").text();//作业组名称
		if(printType==0){
			window.location.href="excelOutDemandDetail.do";
		}
		if(printType==1){
			window.location.href="printOutDemandDetail.do";
		}
	});
	
	$(document).on("click", "#add", function(){
		sessionStorage.demandBillId=-1;
		window.location.href="customerDemandInit.do";
	});
	$(document).on("click", ".editOutStock", function(){
		var billId=$(this).attr("billId");
		sessionStorage.demandBillId=billId;
		window.location.href="editDemandInit.do";
	});
});


//需求上报列表
function getDemandListH() {
	$.post("getDemandListH.do?", {
		state:g_state,
		stime:g_stime,
		etime:g_etime,
		page:curPage,
		customerId:g_customerId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#demandTmpl").tmpl(data.data).appendTo("#tbody");
			totalPage=data.data.totalPage;
			if(curPage==1)
				$("#prePage").attr("disabled","disabled");
			else
				$("#prePage").removeAttr("disabled");
			if(curPage==totalPage)
				$("#nextPage").attr("disabled","disabled");
			else
				$("#nextPage").removeAttr("disabled");
			$("#curPage").text(curPage);
			$("#totalPage").text(totalPage);
		}
	}, "json");
}


//导出
function excelOut(){
	g_state=$("#state").val();
	window.location.href="../demand/exportOutDemandBillByStateAndTimeAction.do?stime="
		+g_stime+"&etime="+g_etime+"&state="+g_state+"&page="+curPage
		+"&customerId="+g_customerId;
}
function search(){
	g_state=$("#state").val();
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	g_customerId=$("#customer").val();
	sessionStorage.managerDemandListState=g_state;
	sessionStorage.managerDemandListStime=g_stime;
	sessionStorage.managerDemandListEtime=g_etime;
	sessionStorage.managerDemandListCustomerId=g_customerId;
	curPage=1;
	totalPage=1;
	getDemandListH();
}

function getReviewProcess(billId){
	$.post("../common/getReviewProcess.do?", {
		billId:billId,
		billType:0
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#reviewbody").empty();
			$("#reviewTmpl").tmpl(data.data).appendTo("#reviewbody");
			$("#uModal").modal();
		}
	}, "json");
}

function delDemandBill(billId){
	confirm("确定要删除该订单吗？",function(){
		$.post("delDemandBill.do?", {
			billId:billId
		}, function(data) {
			tips(data.msg);
			if(data.code==1000){
				setTimeout(function(){
					location.reload();
				},1000);
			}
		}, "json");
	});
}

function dealBill(){
	var reviewState;
	var billDate='';
	if(roleId==9&&billState==0){
		reviewState=1;
		billDate = $("#submitTime").val();
	}else if(roleId==5&&billState==1){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealDemandBill.do?", {
		billId:submitBillId,
		reviewState:reviewState,
		operType:0,
		billDate:billDate,
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			setTimeout(function(){
				window.location.href="demandInit.do";
			},1000);
		}else if(data.code==209){
			setTimeout(function(){
				window.location.href="../purchase/purchaseInit.do";
			},1000);
		}
	}, "json");	
}

function refuseBill(){
	var reviewState;
	if(roleId==5&&billState==1){
		reviewState=0;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealDemandBill.do?", {
		billId:submitBillId,
		reviewState:reviewState,
		operType:1,
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			setTimeout(function(){
				window.location.href="demandInit.do";
			},1000);
		}
	}, "json");	
}

//销售单
function marketBill(){
	$("#stimeM").val(getDateStr(0)+" 00:00:00");
	$("#etimeM").val(getDateStr(0)+" 23:00:00");
	$("#marketModal").modal();
}

//检查客户选择是否为空
function checkCustomer(){
	var customerId = $("#customerId").val();
	if(customerId==-1){
		tips('请选择一个客户');
		return;
	}else{
		return customerId;
	}
}

//打印销售单
function printSure(){
	var customerId = $("#customerId").val();
	if(customerId==-1){
		tips('请选择一个客户');
		return;
	}
	window.open("../demand/printSureBill.do?stimeM="+$("#stimeM").val()+"&etimeM="+$("#etimeM").val()+"&customerId="+customerId+"");
	$("#marketModal").modal("hide");
}

//导出销售单
function ExoertOutSure(){
	var customerId = $("#customerId").val();
	if(customerId==-1){
		tips('请选择一个客户');
		return;
	}
	window.location="../demand/exportOutDemandListDBill.do?stimeM="+$("#stimeM").val()+"&etimeM="+$("#etimeM").val()+"&customerId="+customerId+"";
	$("#marketModal").modal("hide");
}

