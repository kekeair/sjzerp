
var billId=sessionStorage.billId;//申请账单
var reviewState=sessionStorage.reviewState;
var goodsNm='';
var type=0;
$(document).ready(function() {
	
	if(reviewState>2){
		$("#opSupplierId").show();
	}else{
		$("#opSupplierId").hide();
	}
	
	//获取按物料查看的物料列表
	selectPurchaseGoodsByGoods();
	//下拉框获取列表
	$(document).on("change", "#viewType", function(){
		 type=$("#viewType").val();
		if(type==0){
			$("#goodsNmInput").show();
			$("#groupTable").hide();
			$("#goodsTable").show();
			$("#supplierTable").hide();
			selectPurchaseGoodsByGoods();
		}else if(type==1){
			$("#goodsTable").hide();
			$("#goodsNmInput").hide();
			$("#supplierTable").hide();
			$("#groupTable").show();
			selectPurchaseGoodsBygroup();
		}else{
			$("#groupTable").hide();
			$("#goodsTable").hide();
			$("#goodsNmInput").hide();
			$("#supplierTable").show();
			selectPurchaseGoodsBySupplier();
		}
		
	});
	//搜索
	$(document).on("click","#search",search);
});

//按物料查看方式
function selectPurchaseGoodsByGoods(){
    $.post("../purchase/selectPurchaseGoodsByGoods.do?",{
		type:type,
		billId:billId,
		goodsNm:goodsNm
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#goodsbody").empty();
			$("#goodsTmpl").tmpl(data.data).appendTo("#goodsbody");
		}
	},"json");
}

//按照作业组查看方式
function selectPurchaseGoodsBygroup(){
	$.post("../purchase/selectPurchaseGoodsBygroup.do?",{
		type:type,
		billId:billId,
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#groupbody").empty();
			$("#groupTmpl").tmpl(data.data).appendTo("#groupbody");
		}
	},"json");;
}
//按照供应商查看方式
function selectPurchaseGoodsBySupplier(){
	$.post("../purchase/selectPurchaseGoodsBySupplier.do?",{
		type:type,
		billId:billId,
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#supplierbody").empty();
			$("#supplierTmpl").tmpl(data.data).appendTo("#supplierbody");
		}
	},"json");;
}
//搜索
function search(){
	goodsNm=$("#goodsNm").val();
	type=0;
	selectPurchaseGoodsByGoods();
}



