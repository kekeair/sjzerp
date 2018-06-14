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

var nodes;

var curPage=1;//当前页
var totalPage=1;//总页数
var g_goodsNm=sessionStorage.stockQueryGoodsNm||"";//物料名称
var g_goodsKind=sessionStorage.stockQueryGoodsKind||"-1";//物料分类
var goodsCode=sessionStorage.stockQueryGoodsCode||"";//物料编码
$(document).ready(function() {
	$("#goodsNm").val(g_goodsNm);
	$("#goodsCode").val(goodsCode);
	$("#kindSel").attr("code", g_goodsKind);
	$("#kindSel").attr("atNo", sessionStorage.stockQueryKindId);
	$("#kindSel").val(sessionStorage.stockQueryKindNm);
	getGoodsKind();
	getStockList();//库存列表
	bindPage(getStockList);
	$("#kindSel").bind("click", kindSel);
	$(document).on("click", "#search", search);
	//enter名称键搜索
	$(document).on("keydown", "#goodsCode", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	//enter名称键搜索
	$(document).on("keydown", "#goodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	$(document).on("click", ".detail", function(){
		var goodsId=$(this).attr("goodsId");
		sessionStorage.stockGoodsId=goodsId;
		window.location.href="stockRecordInit.do";
	});
});

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

//库存列表
function getStockList() {
	$.post("getStockList.do?", {
		name:g_goodsNm,
		kindCode:g_goodsKind,
		page:curPage,
		goodsCode:goodsCode
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
	g_goodsNm=$.trim($("#goodsNm").val());
	g_goodsKind=$("#kindSel").attr("code");
	goodsCode=$.trim($("#goodsCode").val());
	sessionStorage.stockQueryGoodsNm=g_goodsNm;
	sessionStorage.stockQueryGoodsKind=g_goodsKind;
	sessionStorage.stockQueryGoodsCode=goodsCode;
	sessionStorage.stockQueryKindId=$("#kindSel").attr("atNo");
	sessionStorage.stockQueryKindNm=$("#kindSel").val();
	curPage=1;
	totalPage=1;
	getStockList();
}