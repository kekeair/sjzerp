var curPage=1;//当前页
var totalPage=1;//总页数
var g_state=sessionStorage.stockFixState||-1;//状态
var g_stime=sessionStorage.stockFixStime||"";//开始时间
var g_etime=sessionStorage.stockFixEtime||"";//结束时间
$(document).ready(function() {
	$("#state").val(g_state);
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	getStockFixList();//库存修正单列表
	bindPage(getStockFixList);
	$(document).on("click", "#search", search);
	$(document).on("click", "#add", addStockFix);
	$(document).on("click", ".detail", function(){
		var billId=$(this).attr("billId");
		var txt=$(this).text();
		sessionStorage.stockFixHId=billId;
		if(roleId==5&&txt=='修改')
			window.location.href="addStockFixInit.do";
		else
			window.location.href="stockFixDInit.do"
	});
	$(document).on("click", ".del", function(){
		var billId=$(this).attr('billId');
		delBill(billId);
	});
});

//库存修正单列表
function getStockFixList() {
	$.post("getStockFixH.do?", {
		state:g_state,
		stime:g_stime,
		etime:g_etime,
		page:curPage
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#stockFixTmpl").tmpl(data.data).appendTo("#tbody");
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
	sessionStorage.stockFixState=g_state;
	sessionStorage.stockFixStime=g_stime;
	sessionStorage.stockFixEtime=g_etime;
	curPage=1;
	totalPage=1;
	getStockFixList();
}

function addStockFix(){
	var checkMsg='';
	$(".reviewState").each(function(){
		var state=$(this).attr("state");
		if(state==0){
			checkMsg='您有单据未提交，无法添加新单据';
			return false;
		}
	});
	if(checkMsg!=''){
		tips(checkMsg);
		return;
	}
	sessionStorage.stockFixHId=-1;
	window.location.href="addStockFixInit.do";
}

function delBill(billId){
	confirm("确定要删除该单据吗？",function(){
		$.post("delStockFix.do?", {
			billId:billId
		}, function(data) {
			tips(data.msg);
			setTimeout(function(){
				location.reload();
			},1000);
		}, "json");
	});
}