var curPage=1;//当前页
var totalPage=1;//总页数
var g_state=sessionStorage.tuihuoListState||-1;//状态
var g_stime=sessionStorage.tuihuoListStime||"";//开始时间
var g_etime=sessionStorage.tuihuoListEtime||"";//结束时间
var g_supplierId=sessionStorage.tuihuoListSupplierId||"-1";
var submitBillId;
var billState;
var operType;
$(document).ready(function() {
	$("#state").val(g_state);
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	$("#supplier").val(g_supplierId);
	if(roleId==5){//经理
		$("#add").show();
	}else{
		$("#add").hide();
	}
	getTuihuoListH();//退库单列表
	bindPage(getTuihuoListH);
	$(document).on("click", "#search", search);
	$(document).on("click", "#add", function(){
		sessionStorage.tuihuoBillId=-1;
		window.location.href="addTuihuoInit.do";
	});
	$(document).on("click", ".edit", function(){
		var billId=$(this).attr('billId');
		sessionStorage.tuihuoBillId=billId;
		window.location.href="addTuihuoInit.do";
	});
	$(document).on("click", ".del", function(){
		var billId=$(this).attr('billId');
		delTuihuoBill(billId);
	});
	$(document).on("click", ".view", function(){
		var billId=$(this).attr('billId');
		sessionStorage.tuihuoBillId=billId;
		window.location.href="viewTuihuoDInit.do";
	});
	$(document).on("click", ".reviewProcess", function(){
		var billId=$(this).attr('billId');
		getReviewProcess(billId);
	});
	$(document).on("click", ".submit", function(){
		submitBillId=$(this).attr('billId');
		billState=$(this).attr("billState");
		operType=0;
		$("#remark").val("");
		if(roleId==5 && billState==0){
			$("#varyTime").show();
			$("#submitTime").val(getDateStr(0));
		}else{
			$("#varyTime").hide();
		}
		$("#submitModal").modal();
	});
	$(document).on("click", ".refuse", function(){
		submitBillId=$(this).attr('billId');
		billState=$(this).attr("billState");
		operType=1;
		$("#remark").val("");
		$("#varyTime").hide();
		$("#submitModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
	$(document).on("click", "#export", function(){
		window.location.href="../tuihuo/exportTuihuoBillList.do?stime="
			+g_stime+"&etime="+g_etime+"&state="+g_state
			+"&supplierId="+g_supplierId;
	});
});

//需求上报列表
function getTuihuoListH() {
	$.post("getTuihuoListH.do?", {
		state:g_state,
		stime:g_stime,
		etime:g_etime,
		page:curPage,
		supplierId:g_supplierId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#tuihuoTmpl").tmpl(data.data).appendTo("#tbody");
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
	g_supplierId=$("#supplier").val();
	sessionStorage.tuihuoListState=g_state;
	sessionStorage.tuihuoListStime=g_stime;
	sessionStorage.tuihuoListEtime=g_etime;
	sessionStorage.tuihuoListSupplierId=g_supplierId;
	curPage=1;
	totalPage=1;
	getTuihuoListH();
}

function getReviewProcess(billId){
	$.post("../common/getReturnProcess.do?", {
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


function delTuihuoBill(billId){
	confirm("确定要删除该订单吗？",function(){
		$.post("delTuihuoBill.do?", {
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
	if(roleId==5&&billState==0){
		reviewState=1;
		billDate = $("#submitTime").val();
	}else if(roleId==4&&billState==1){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealTuihuoBill.do?", {
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
				window.location.href="tuihuoInit.do";
			},1000);
		}
	}, "json");	
}

function refuseBill(){
	var reviewState;
	if(roleId==4&&billState==1){
		reviewState=0;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealTuihuoBill.do?", {
		billId:submitBillId,
		reviewState:reviewState,
		operType:1,
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			setTimeout(function(){
				window.location.href="tuihuoInit.do";
			},1000);
		}
	}, "json");	
}