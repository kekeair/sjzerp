var curPage=1;//当前页
var totalPage=1;//总页数
var g_flag=-1;//操作类型
var g_stime="";//开始时间
var g_etime="";//结束时间
var goodsId=sessionStorage.stockGoodsId;
$(document).ready(function() {
	getStockRecord();//库存记录
	bindPage(getStockRecord);
	$(document).on("click", "#search", search);
});

//库存记录
function getStockRecord() {
	$.post("getStockRecord.do?", {
		stockFlag:g_flag,
		stime:g_stime,
		etime:g_etime,
		page:curPage,
		goodsId:goodsId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#stockTmpl").tmpl(data.data.list).appendTo("#tbody");
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
	g_flag=$("#stockFlag").val();
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	curPage=1;
	totalPage=1;
	getStockRecord();
}