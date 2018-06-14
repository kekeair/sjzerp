var billId=sessionStorage.tuihuoBillId||-1;
var billState;
var operType;
$(document).ready(function() {
	viewTuihuoD();//退库单列表
	$(document).on("click", "#submit", function(){
		operType=0;
		$("#remark").val("");
		$("#submitModal").modal();
	});
	$(document).on("click", "#refuse", function(){
		operType=1;
		$("#remark").val("");
		$("#submitModal").modal();
	});
	
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
	
});

//需求上报列表
function viewTuihuoD() {
	$.post("viewTuihuoD.do?", {
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			var head=data.data.head;
			billState=head.reviewState;
			$("#billCode").text(head.code);
			$("#supplierNm").text(head.supplierNm);
			if(roleId==4&&billState==1){
				$("#submit").show();
				$("#refuse").show();
			}else{
				$("#submit").hide();
				$("#refuse").hide();
			}
			$("#tbody").empty();
			$("#tuihuoTmpl").tmpl(data.data.list).appendTo("#tbody");
		}
	}, "json");
}

function dealBill(){
	var reviewState;
	var billDate='';
	if(roleId==4&&billState==1){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealTuihuoBill.do?", {
		billId:billId,
		reviewState:reviewState,
		operType:0,
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
		billId:billId,
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