var curPage=1;//当前页
var totalPage=1;//总页数
var g_state=sessionStorage.purchaseListState||-1;//状态
var g_stime=sessionStorage.purchaseListStime||"";//开始时间
var g_etime=sessionStorage.purchaseListEtime||"";//结束时间
var g_customer=sessionStorage.purchaseListCustomer||-1;
var operType;//0通过1驳回
var dealBillId;
var billState;
var teamBillId;
var exportBillId;
var exportType;
var billIds;
$(document).ready(function() {
	$("#state").val(g_state);
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	$("#customer").val(g_customer);
	getPurchaseList();//采购列表
	bindPage(getPurchaseList);
	$(document).on("click", "#search", search);
	$(document).on("click", ".viewByGroup", function(){
		var billId=$(this).attr('billId');
		sessionStorage.purchaseBillId=billId;
		window.location.href="purchaseByGroupInit.do";
	});
	$(document).on("click", ".viewByGoods", function(){
		var billId=$(this).attr('billId');
		sessionStorage.purchaseBillId=billId;
		window.location.href="purchaseByGoodsInit.do";
	});
	//查看
	$(document).on("click", ".viewByGoodsAndGroup", function(){
		var billId=$(this).attr('billId');
		var reviewState=$(this).attr('reviewState');
		sessionStorage.billId=billId;
		sessionStorage.reviewState=reviewState;
		
		window.location.href="selectGoodsInit.do";
	});
	
	$(document).on("click", ".editInStock", function(){
		var billId=$(this).attr("billId");
		sessionStorage.purchaseBillId=billId;
		window.location.href="editPurchaseInit.do";
	});
	$(document).on("click", ".viewBySupplier", function(){
		var billId=$(this).attr('billId');
		sessionStorage.purchaseBillId=billId;
		window.location.href="purchaseBySupplierInit.do";
	});
	$(document).on("click", "#add", addPurchase);
	$(document).on("click", ".supplierSel", function(){
		billIds=$(this).attr("billId");//将订单id绑定到当前js对象中
		sessionStorage.purchaseBillId=billIds;
		window.location.href="supplierSelInit.do";	
	});

	
	$(document).on("click", ".purchaseTotallList", function(){
		var code=$(this).attr("code");//将订单id绑定到当前js对象中
		sessionStorage.purchaseTotallListCode=code;
		window.location.href="purchaseTotallListInit.do";
	});
	
	$(document).on("click", ".reviewProcess", function(){
		var billId=$(this).attr('billId');
		getReviewProcess(billId);
	});
	$(document).on("click", ".del", function(){
		var billId=$(this).attr('billId');
		delPurchaseBill(billId);
	});
	
	//======================办理/驳回==============================
	//办理
	$(document).on("click", "#banli", function(){
		$.post("../purchase/supplierSel.do?", {
			billId:billIds,
			//supplierJson:JSON.stringify(goodsList),
			remark:$.trim($("#beizhu").val())
		}, function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				window.location.href="../purchase/purchaseInit.do";
			}
		}, "json");
		
	});
	//驳回
	$(document).on("click", "#Manage", function(){
		$.post("../purchase/dealPurchaseBill.do?", {
			billId:billIds,
			reviewState:1,
			operType:1,
			remark:$.trim($("#beizhu").val())
		}, function(data) {
			tips(data.msg);
			setTimeout(function(){
				window.location.href="../purchase/purchaseInit.do";
			},1000);
		}, "json");
	
	});
	
	//====================================================
	
	
	$(document).on("click", ".deal", function(){
		dealBillId=$(this).attr("billId");
		billState=$(this).attr("billState");
		$("#remark").val("");
		operType=0;
		if(roleId==5 && billState==0){
			$("#varyTime").show();
			$("#submitTime").val(getDateStr(0));
		}else{
			$("#varyTime").hide();
		}
		$("#dealModal").modal();
	});
	$(document).on("click", ".refuse", function(){
		dealBillId=$(this).attr("billId");
		billState=$(this).attr("billState");
		$("#remark").val("");
		operType=1;
		$("#varyTime").hide();
		$("#dealModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
	
	//工作组
	$(document).on("click", ".daochu", function(){
		teamBillId=$(this).attr('billId');
		$.post("../customerteam/getCustomTeamNameListByPurchase.do",{teamBillId:teamBillId},function(data){
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				$("#customerTeam").empty();
				$("#customerTeamTmpl").tmpl(data.data).appendTo("#customerTeam");
			}
		},"json");
		$("#teamModal").modal();
	});
		
	//汇总单导出
	$(document).on("click", ".summaryExport", function(){
		exportType="summary";
		exportBillId=$(this).attr("billId");//获取订单Id
		sessionStorage.exportBillId=exportBillId;
		getSupplier();
		$("#supplierModal").modal();
	});
	
	//入库单导出/打印
	$(document).on("click", ".rkExport", function(){
		//exportType="rk";
		exportBillId=$(this).attr("billId");
		sessionStorage.exportBillId=exportBillId;
		getSupplier();
		$("#rkSupplierModal").modal();
	});
	
	//导出确定按钮
	$(document).on("click", "#exportSure", function(){
		var supplierId=$("#supplier").val();
		if(supplierId==-2){
			tips('请选择一个供应商');
			return;
		}
			location.href="exportPurchaseTotallList.do?billId="+exportBillId+
			"&supplierId="+supplierId;
			$("#supplierModal").modal("hide");
	});
	
	//入库单打印
	$(document).on("click", "#printSure", function(){
		var supplierId=$("#supplier_2").val();
		if(supplierId == -2){
			tips('请选择一个客户');
			return;
		}
		window.open("exportPurchaseInStockList.do?billId="+exportBillId+
				"&supplierId="+supplierId);
		$("#rkSupplierModal").modal("hide");
	});
	
	
	//入库单导出
	$(document).on("click", "#excelSure", function(){
		var supplierId=$("#supplier_2").val();
		if(supplierId == -2){
			tips('请选择一个客户');
			return;
		}
		window.location="../purchase/exportInStock.do?billId="+exportBillId+"&supplierId="+supplierId+"";
		$("#teamModal").modal("hide");
	});
	
	$(document).on("click", "#excelOut", excelOut);//导出
});

//导出
function excelOut(){
	g_state=$("#state").val();
	g_customerId = $("#customer").val();
	g_etime = $("#etime").val();
	g_stime = $("#stime").val();
	g_state = $("#state").val();
	window.location.href="../purchase/exportOutpurchaseBillByStateAndTimeAction.do?stime="
		+g_stime+"&etime="+g_etime+"&state="+g_state+"&page="+curPage
		+"&customerId="+g_customerId;
}

//采购列表
function getPurchaseList() {
	$.post("getPurchaseListH.do?", {
		state:g_state,
		stime:g_stime,
		etime:g_etime,
		customeId:g_customer,
		page:curPage
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			data.data.roleId=roleId;
			$("#purchaseTmpl").tmpl(data.data).appendTo("#tbody");
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

function search(){
	g_state=$("#state").val();
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	g_customer=$("#customer").val();
	sessionStorage.purchaseListState=g_state;
	sessionStorage.purchaseListStime=g_stime;
	sessionStorage.purchaseListEtime=g_etime;
	sessionStorage.purchaseListCustomer=g_customer;
	curPage=1;
	totalPage=1;
	getPurchaseList();
}

function addPurchase(){
	sessionStorage.purchaseBillId=-1;
	sessionStorage.addPurchaseFrom='new';
	window.location.href="addPurchaseInit.do";
}

function getReviewProcess(billId){
	$.post("../common/getReviewProcess.do?", {
		billId:billId,
		billType:1
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

function delPurchaseBill(billId){
	confirm("确定要删除该订单吗？",function(){
		$.post("delPurchaseBill.do?", {
			billId:billId
		}, function(data) {
			if(data.code!=1000){
				tips(data.msg);
			}else{
				location.reload();
			}
		}, "json"); 
	});
}

function dealBill(){
	var reviewState;
	var billDate = $("#submitTime").val();
	if(roleId==5&&billState==0){
		reviewState=1;
	}else if(roleId==4&&billState==1){
		reviewState=2;
	}else if(roleId==7&&billState==2){
		reviewState=3;
	}else if(roleId==5&&billState==3){
		reviewState=4;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealPurchaseBill.do?", {
		billId:dealBillId,
		reviewState:reviewState,
		operType:0,
		billDate:billDate,
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code!=209){
			setTimeout(function(){
				window.location.href="purchaseInit.do";
			},1000);
		}
	}, "json");	
}

function refuseBill(){
	var reviewState;
	if(roleId==4&&billState==1){
		reviewState=0;
	}else if(roleId==5&&billState==3){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealPurchaseBill.do?", {
		billId:dealBillId,
		reviewState:reviewState,
		operType:1,
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		setTimeout(function(){
			window.location.href="purchaseInit.do";
		},1000);
	}, "json");	
}

//为采购汇总获取供应商
function getSupplier(){
	$.post("getExportSupplier.do", {
		billId:exportBillId
	}, function(data) {
		if(data.code==1000){
			$("#supplier").empty();
			$("#supplierTmpl").tmpl(data).appendTo("#supplier");
			$("#supplier_2").empty();
			$("#supplierTmpl").tmpl(data).appendTo("#supplier_2");
		}
	}, "json");	
}