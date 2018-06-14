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
var g_customId//客户id
var g_goodsNm=sessionStorage.stockQueryGoodsNm||"";//物料名称
var g_goodsKind=sessionStorage.stockQueryGoodsKind||"-1";//物料分类
var g_supplierId;//供应商Id

$(document).ready(function() {
	g_customId = sessionStorage.purchaseSummCustomId||-1;
	g_stime=sessionStorage.purchaseSummStime||(getDateStr(0)+" 00:00:00");
	g_etime=sessionStorage.purchaseSummEtime||(getDateStr(0)+" 23:00:00");
	if(sessionStorage.purchaseTotalDSupplierId){
		$("#supplier").val(sessionStorage.purchaseTotalDSupplierId);
	}
	g_supplierId=$("#supplier").val();
	$("#goodsNm").val(g_goodsNm);
	$("#kindSel").attr("code", g_goodsKind);
	$("#kindSel").attr("atNo", sessionStorage.stockQueryKindId);
	$("#kindSel").val(sessionStorage.stockQueryKindNm);
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	getGoodsKind();
	$("#kindSel").bind("click", kindSel);
	GetPurchaseSummList();//库存修正单列表
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
	bindPage(GetPurchaseSummList);
	$(document).on("click", "#search", search);//搜索
	$(document).on("click", "#excelOut", excelOut);//导出
	$(document).on("dblclick", ".goodsNm", goodsNmD);
	

});
//导出

function excelOut(){
	g_goodsKind=$("#kindSel").attr("code");
	g_goodsNm=$("#goodsNm").val();
	g_supplierId=$("#supplier").val();
	window.location.href="../purchaseSumm/exportOutPurchaseSummAction.do?stime="+g_stime+"&etime="+g_etime+"&g_customId="+g_customId+"&goodsKind="+g_goodsKind+"&goodsNm="+g_goodsNm+"&supplierId="+g_supplierId+"";
}

//详情列表显示
function goodsNmD(){
	var goodsId=$(this).attr("goodsNm");
	sessionStorage.myGoodsId=goodsId;
	sessionStorage.myG_stime=g_stime;
	sessionStorage.myG_etime=g_etime;
	sessionStorage.myG_goodsType=$(this).attr("goodsType");
	sessionStorage.myG_purchaselistDId=$(this).attr("purchaselistDId");
	sessionStorage.myCustomId=$("#custom").val();
	window.location.href="purchaseSummDInit.do?";
}

//获取采购列表
function GetPurchaseSummList() {
	$.post("getPurchaseSummList.do?", {
		stime:g_stime,
		etime:g_etime,
		g_customId:g_customId,
		supplierId:g_supplierId,
		goodsNm:g_goodsNm,
		goodsKind:g_goodsKind
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#listTmpl").tmpl(data.data.list).appendTo("#tbody");
			
		}
	}, "json");
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
function search(){
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	g_customId=$("#custom").val();
	g_goodsKind=$("#kindSel").attr("code");
	g_goodsNm=$("#goodsNm").val();
	g_supplierId=$("#supplier").val();
	sessionStorage.purchaseSummStime=g_stime;
	sessionStorage.purchaseSummEtime=g_etime;
	sessionStorage.purchaseSummCustomId=g_customId;
	sessionStorage.stockQueryGoodsKind=g_goodsKind;
	sessionStorage.stockQueryKindId=$("#kindSel").attr("atNo");
	sessionStorage.stockQueryKindNm=$("#kindSel").val();
	sessionStorage.purchaseTotalDSupplierId=g_supplierId;
	sessionStorage.stockQueryGoodsNm=g_goodsNm;
	GetPurchaseSummList();
}

