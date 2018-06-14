var g_stime;//开始时间
var g_etime;//结束时间
var g_supplierId;
var g_customId//客户id
$(document).ready(function() {
	g_stime=sessionStorage.purchaseTotalDStime||(getDateStr(0)+" 00:00:00");
	g_etime=sessionStorage.purchaseTotalDEtime||(getDateStr(0)+" 23:00:00");
	g_customId = sessionStorage.purchaseTotalDCustomId||-1;
	if(sessionStorage.purchaseTotalDSupplierId){
		$("#supplier").val(sessionStorage.purchaseTotalDSupplierId);
	}
	g_supplierId=$("#supplier").val();
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	purchaseTotalD();
	$(document).on("click", "#search", search);
	$(document).on("click","#educe",educe)
	//获取客户列表
	$.post("../demandSumm/getCustom.do?", {},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#custom").empty();
			$("#customTmpl").tmpl(data.data).appendTo("#custom");
			$("#custom").val(g_customId);
		}
	},"json");
});

//导出
function educe(){
	g_customId = $("#custom").val();
	location.href="../purchase/exportOutPurchaseTotalD.do?stime="+g_stime+"&etime="+g_etime+"&supplierId="+g_supplierId+"&customerId="+g_customId+"";
}
//获取采购汇总单明细
function purchaseTotalD() {
	$.post("getPurchaseTotalD.do?",{
		stime:g_stime,
		etime:g_etime,
		supplierId:g_supplierId,
		g_customId:g_customId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#mainTable").empty();
			$("#detailTmpl").tmpl(data.data).appendTo("#mainTable");
			totalPage=data.data.totalPage;
		}
	}, "json");
}

function search(){
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	g_supplierId=$("#supplier").val();
	g_customId = $("#custom").val();
	sessionStorage.purchaseTotalDStime=g_stime;
	sessionStorage.purchaseTotalDEtime=g_etime;
	sessionStorage.purchaseTotalDSupplierId=g_supplierId;
	sessionStorage.purchaseTotalDCustomId=g_customId;
	purchaseTotalD();
}