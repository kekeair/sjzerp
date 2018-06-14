var curPage=1;//当前页
var totalPage=1;//总页数
var g_stime=sessionStorage.myG_stime;//开始时间
var g_etime=sessionStorage.myG_etime;//结束时间
var goodsId=sessionStorage.myGoodsId;
var goodsType = sessionStorage.myG_goodsType;
var purchaselistDId = sessionStorage.myG_purchaselistDId;
var customerId=sessionStorage.myCustomId;

$(document).ready(function() {
	GetPurchaseSummListD();//库存修正单列表
	bindPage(GetPurchaseSummListD);
});

//获取采购列表
function GetPurchaseSummListD() {
	$.post("getPurchaseSummListD.do?", {
		goodsId:goodsId,
		stime:g_stime,
		etime:g_etime,
		goodsType:goodsType,
		customerId:customerId,
		purchaselistDId:purchaselistDId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#listTmpl").tmpl(data.data.list).appendTo("#tbody");
			
		}
	}, "json");
}