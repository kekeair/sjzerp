var billId=sessionStorage.purchaseBillId;
var billState;
var operType;//0通过1驳回

$(document).ready(function() {
	getPurchaseSupplier();//供应商列表
	$(document).on("click", ".supplierD", function(){
		var supplierId=$(this).attr("supplierId");
		sessionStorage.purchaseSupplierId=supplierId;
		window.location.href="supplierDInit.do";
	});
	$(document).on("click", "#deal", function(){
		$("#remark").val("");
		operType=0;
		$("#uModal").modal();
	});
	$(document).on("click", "#refuse", function(){
		$("#remark").val("");
		operType=1;
		$("#uModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
});

//采购列表
function getPurchaseSupplier() {
	$.post("getPurchaseSupplier.do?", {
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			billState=data.data.billState;
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#detailTmpl").tmpl(data.data).appendTo("#tbody");
			if(roleId==5&&billState==3||roleId==4&&billState==4){
				$("#refuse").show();
				$("#deal").show();
			}
		}
	}, "json");
}

function dealBill(){
	var reviewState;
	if(roleId==5&&billState==3){
		reviewState=4;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealPurchaseBill.do?", {
		billId:billId,
		reviewState:reviewState,
		operType:0,
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
	if(roleId==4&&billState==4){
		reviewState=3;
	}else if(roleId==5&&billState==3){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealPurchaseBill.do?", {
		billId:billId,
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