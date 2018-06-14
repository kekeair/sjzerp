//树设置
var zTree;
var setting = {
		view: {
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "atNo",
				pIdKey: "pId",
				rootPId: -1
			}
		},
		callback: {
			onClick: clickKind
		}
	};

function kindSel(){
	var kindObj = $("#kindSel");
	var kindOffset = $("#kindSel").offset();
	$("#kindDiv").css({left:kindOffset.left + "px", top:kindOffset.top 
		+ kindObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onKindDown);
}

function clickKind(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#kindSel").attr("atNo", treeNode.atNo);
		$("#kindSel").attr("code", treeNode.code);
		$("#kindSel").val(treeNode.name);
		hideKindMenu();
	}
}

function hideKindMenu(){
	$("#kindDiv").fadeOut("fast");
	$("body").unbind("mousedown", onKindDown);
}

function onKindDown(event) {
	if (!(event.target.id == "kindSel" || event.target.id == "kindDiv" 
		|| $(event.target).parents("#kindDiv").length>0)) {
		hideKindMenu();
	}
}
//------------------------分类菜单结束-------------------------




var g_stime;//开始时间
var g_etime;//结束时间
//var g_customerId;//客户id
var g_customerIds=-1;//客户多选
var selStr = [];//下拉框多选后value拼接的字符串
var g_goodsCode;
var g_goodsKind=sessionStorage.stockQueryGoodsKind||"-1";//物料分类
$(document).ready(function() {
	//g_customerIds = sessionStorage.purchaseSummCustomerIds||-1;
	g_stime=sessionStorage.purchaseSummStime||(getDateStr(0)+" 00:00:00");
	g_etime=sessionStorage.purchaseSummEtime||(getDateStr(0)+" 23:00:00");
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	$("#kindSel").attr("code", g_goodsKind);
	$("#kindSel").attr("atNo", sessionStorage.stockQueryKindId);
	$("#kindSel").val(sessionStorage.stockQueryKindNm);
	getGoodsKind();
	$("#kindSel").bind("click", kindSel);
	GetProfitSummList();//获取毛利信息
	bindPage(GetProfitSummList);
	//获取客户列表
	getCustom();
	//获取选中下拉项的值
	$("#custom").multiselect({
		height: 200,
		minWidth: 160,
		noneSelectedText: "==请选择==",
		checkAllText: "全选",
		uncheckAllText: '全不选',
		selectedList: 20,
	});
	$(document).on("click", "#search", search);//搜索
	$(document).on("click", "#excelOut", excelOut);//导出
	/*$(document).on("dblclick", ".goodsNm", goodsNmD);*/
	//双点击
	$(document).on("dblclick", ".goodsNm", function(){
		var goodsId=$(this).attr("goodsId");
		var goodsType=$(this).attr("goodsType");
		var demandListDId=$(this).attr("demandListDId");
		var purchaseListDId=$(this).attr("purchaseListDId");
		g_customerIds = sessionStorage.purchaseSummCustomerIds||"-1";
		//存入 session
		sessionStorage.goodsId=goodsId;
		sessionStorage.goodsType=goodsType;
		sessionStorage.purchaseSummStime=g_stime;
		sessionStorage.purchaseSummEtime=g_etime;
		sessionStorage.demandListDId=demandListDId;
		sessionStorage.purchaseListDId=purchaseListDId;
		window.location.href="../purchaseSumm/profitSummByGoodsInit.do?goodsId="+goodsId
		+"&goodsType="+goodsType+"&stime="+g_stime+"&etime="+g_etime
		+"&demandListDId="+demandListDId+"&purchaseListDId="+purchaseListDId+"&customerIds="+g_customerIds;
	});
	
	//单机事件      分类占比
	$(document).on("click", "#selectBigKind", function(){
		//存入 session
		var goodsNm = $("#goodsNm").val();
		g_stime=$("#stime").val();
		g_etime=$("#etime").val();
		g_customerIds = sessionStorage.purchaseSummCustomerIds||"-1";
		sessionStorage.goodsNm=goodsNm;
		sessionStorage.purchaseSummStime=g_stime;
		sessionStorage.purchaseSummEtime=g_etime;
		//sessionStorage.purchaseSummCustomerIds=g_customerIds;
		window.location.href="../purchaseSumm/selectBigKindInit.do?stime="+g_stime+"&etime="+g_etime+"&customerIds="+g_customerIds+"&goodsNm="+goodsNm+"";
		
		});
	
	//单机事件     按客户汇总
	$(document).on("click", "#selectCustomerSumm", function(){
		//存入 session
		g_stime=$("#stime").val();
		g_etime=$("#etime").val();
		g_customerIds = sessionStorage.purchaseSummCustomerIds||"-1";
		g_goodsKind=$("#kindSel").attr("code");
		sessionStorage.goodsNm=goodsNm;
		sessionStorage.purchaseSummStime=g_stime;
		sessionStorage.purchaseSummEtime=g_etime;
		sessionStorage.stockQueryGoodsKind=g_goodsKind;
		//sessionStorage.purchaseSummCustomerIds=g_customerIds;
		if(g_customerIds==-1){
			tips("请先选择客户!!!!!");
		}else{
		window.location.href="../purchaseSumm/selectCustomerSummInit.do?stime="+g_stime+"&etime="+g_etime+"&customerIds="+g_customerIds
		+"&kindCode="+g_goodsKind+"";
		}
		});
});
//导出
function excelOut(){
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	var goodsNm=$("#goodsNm").val();
	g_goodsKind=$("#kindSel").attr("code");
	g_customerIds = sessionStorage.purchaseSummCustomerIds||"-1";
	window.location.href="../purchaseSumm/exportProfitSumm.do?stime="+g_stime+"&etime="+g_etime+"&customerIds="+g_customerIds+"&goodsNm="+goodsNm+"&kindCode="+g_goodsKind+"";
}

//获取采购列表
function GetProfitSummList() {
	var goodsNm = $("#goodsNm").val();
	load();
	$.post("../purchaseSumm/getProfitSummList.do?", {
		stime:g_stime,
		etime:g_etime,
		customerIds:g_customerIds,
		goodsNm:goodsNm,
		kindCode:g_goodsKind
	}, function(data) {
		closeAll();
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#tfoot").empty();
			$("#listTmpl").tmpl(data.data.list).appendTo("#tbody");	
			$("#totalTmpl").tmpl(data.data.totalList).appendTo("#tfoot");
		}
	}, "json");
}


function search(){
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	//g_customerId=$("#custom").val();
	g_goodsKind=$("#kindSel").attr("code");
	sessionStorage.purchaseSummStime=g_stime;
	sessionStorage.purchaseSummEtime=g_etime;
	sessionStorage.stockQueryGoodsKind=g_goodsKind;
	sessionStorage.stockQueryKindId=$("#kindSel").attr("atNo");
	sessionStorage.stockQueryKindNm=$("#kindSel").val();
	if(sessionStorage.purchaseSummCustomerIds!=undefined){
	g_customerIds=sessionStorage.purchaseSummCustomerIds;
	}else{
		g_customerIds=-1;
	}
	GetProfitSummList();
}
//获取客户信息
function getCustom() {
	$.ajax({
		type : "GET",
		url : "../demandSumm/getCustom.do?",
		data : {},
		async : false,
		success : function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				$("#custom").empty();
				$("#customTmpl").tmpl(data.data).appendTo("#custom");
				$("#custom").val(g_customerIds);
			}
		}
	});
}
function showValues() {
	var customerIds = $("select").multiselect("getChecked").map(function(){
	return this.value; 
	}).get();
	sessionStorage.purchaseSummCustomerIds=customerIds;
 }

//获取物料分类
function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			//在最前面添加一个”全部“
			var allKind={atNo:0, pId:-1, name:"全部",code:"-1",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}