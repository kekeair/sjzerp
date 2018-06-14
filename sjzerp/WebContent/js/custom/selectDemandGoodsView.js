
var billId=sessionStorage.billId;//申请账单
var customerId=sessionStorage.customerId;
var goodsNm='';
var type=0;
$(document).ready(function() {
	//获取按物料查看的物料列表
	selectDemandGoodsByGoods();
	//下拉框获取列表
	$(document).on("change", "#viewType", function(){
		 type=$("#viewType").val();
		if(type==0){
			$("#goodsNmInput").show();
			$("#cTable").hide();
			$("#zTable").hide();
			$("#gTable").show();
			selectDemandGoodsByGoods();
		}else if(type==1){
			$("#gTable").hide();
			$("#zTable").hide();
			$("#goodsNmInput").hide();
			$("#cTable").show();
			selectDemandGoodsByCustomer();
			
		}else{
			$("#cTable").hide();
			$("#gTable").hide();
			$("#goodsNmInput").hide();
			$("#zTable").show();
			//按作业组查看
			selectDemandGoodsByTeam();
		}
	});
	//搜索
	$(document).on("click","#search",search);
});
//按物料查看方式
function selectDemandGoodsByGoods(){
    $.post("../demand/selectDemandGoodsByGoods.do?",{
		type:type,
		billId:billId,
		goodsNm:goodsNm,
		customerId:customerId
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#goodsTmpl").tmpl(data.data).appendTo("#tbody");
		}
	},"json");
}

//按照客户查看方式
function selectDemandGoodsByCustomer(){
	$.post("../demand/selectDemandGoodsByCustomer.do?",{
		type:type,
		billId:billId,
		customerId:customerId
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#cbody").empty();
			$("#customersTmpl").tmpl(data.data).appendTo("#cbody");
		}
	},"json");;
}
//按照作业组查看方式
function selectDemandGoodsByTeam(){
	$.post("../demand/selectDemandGoodsByTeam.do?",{
		//type:type,
		billId:billId,
		//customerId:customerId
	},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#zbody").empty();
			$("#teamTmpl").tmpl(data.data).appendTo("#zbody");
		}
	},"json");;
}

function search(){
	goodsNm=$("#goodsNm").val();
	type=0;
	selectDemandGoodsByGoods();
}



