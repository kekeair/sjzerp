var billId=sessionStorage.purchaseBillId;
var goodsList;
var operType;//0通过1驳回
$(document).ready(function() {
	getSupplierSelData();//供应商选择数据列表
	//getAllSupplier();//查询当前配送中心下的所有供应商
	$(document).on("change", ".supplierSel", function(){
		var goodsId=$(this).attr("goodsId");
		var purchaseListDId=$(this).attr("purchaseListDId");
		var frontPrice=$(this).find("option:selected").attr("frontPrice");//当前供应商的前置价格
		var supplierId=$(this).val();//供应商Id
		var l=goodsList.length;
		for(var i=0;i<l;i++){
			var goods=goodsList[i];
			if(goods.goodsType==0&&goods.goodsId==goodsId||
					goods.goodsType==1&&goods.purchaseListDId==purchaseListDId){
				goods.frontPrice=frontPrice;
				goods.sId=supplierId;//前置价格对应的物料Id
				goods.frontMoney=formatDouble(frontPrice*goods.orderNum);
				break;
			}
		}
		$("#tbody").empty();
		$("#detailTmpl").tmpl(goodsList).appendTo("#tbody");
	});
	$(document).on('input propertychange','.priceInput',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var val=$(this).val()||0;
		var goodsId=$(this).attr("goodsId");
		var purchaseListDId=$(this).attr("purchaseListDId");
		var l=goodsList.length;
		for(var i=0;i<l;i++){
			var goods=goodsList[i];
			if(goods.goodsType==0&&goods.goodsId==goodsId||
					goods.goodsType==1&&goods.purchaseListDId==purchaseListDId){
				goods.frontPrice=val;
				goods.frontMoney=formatDouble(val*goods.orderNum);
				if(goods.goodsType==0){
					$("#frontMoney"+goodsId).text(goods.frontMoney);
				}else{
					$("#frontMoney"+purchaseListDId).text(goods.frontMoney);
				}
				
				break;
			}
		}
	});
	$(document).on("click", "#submit", function(){
		operType=0;
		$("#remark").val("");
		$("#uModal").modal();
	});
	$(document).on("click", "#refuse", function(){
		operType=1;
		$("#remark").val("");
		$("#uModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			supplierSel();
		}else{
			refuseBill();
		}
	});
	//回车事件
	$(document).on("keydown",".priceInput",function(event){
		if (event.which == 13) {
			var a = $(this).parent().parent().next().find("input").focus().select();
		}
	});
	
	
});

//供应商选择数据列表
function getSupplierSelData() {
	$.post("getSupplierSelData.do?", {
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			goodsList=data.data;
			$("#tbody").empty();
			$("#detailTmpl").tmpl(goodsList).appendTo("#tbody");
		}
	}, "json");
}

function supplierSel() {
	/*	var checkMsg='';
	$(".supplierSel").each(function(){
		var val=$(this).val();
		if(val == null){
			$(this).focus();
			checkMsg='所有物料都必须设置供应商才可提交';
			return false;
		}
	});
	if(checkMsg!=''){
		tips(checkMsg);
		return;
	}
	*/
	load();
	$.post("supplierSel.do?", {
		billId:billId,
		supplierJson:JSON.stringify(goodsList),
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			window.location.href="purchaseInit.do";
		}
	}, "json");
}

function refuseBill(){
	load();
	$.post("dealPurchaseBill.do?", {
		billId:billId,
		reviewState:1,
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
//查询当前餐饮中心下的所有供应商
/*function getAllSupplier() {
	$.post("../common/getSupplier.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//console.log(data.data);
			//$("#tbody").empty();
			$("#detailTmpl").tmpl(data.data).appendTo("#tbody");
			$(".dt").each(function(){
				$("#selectAllSupplerOP").val(data.data.name);
			});	
		}
	}, "json");
}*/