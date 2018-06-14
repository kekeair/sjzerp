//右边分类树设置
var kindTree;
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
		
		$("#kindSel").attr("code", treeNode.code);
		$("#kindSel").val(treeNode.name);
		g_KindCode=treeNode.code;
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
var g_name="";//物料名称
var g_kindId=0;//物料分类
var g_KindCode;//物料类型
var billId=sessionStorage.stockFixHId;

$(document).ready(function() {
	getGoodsKind();
	getStockFixD();
	bindPage(getStockFixD);
	$("#kindSel").bind("click", kindSel);//右分类输入框
	$(document).on("click", "#search", search);
	//enter键搜索
	$(document).on("keydown", "#goodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	$(document).on("click", "#deal", function(){
		dealStockFix(2,'确定要提交该订单吗？');
	});
	$(document).on("click", "#refuse", function(){
		dealStockFix(0,'确定要驳回该订单吗？');
	});
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			var allKind={atNo:0, pId:-1, name:"全部",open:true};
			nodes.unshift(allKind);
			kindTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

//搜索
function search(){
	g_name=$.trim($("#goodsNm").val());
	g_KindCode=$("#kindSel").attr("code");
	curPage=1;
	totalPage=1;
	getStockFixD();
}

//查询右列表数据
function getStockFixD() {
	$.post("getStockFixD.do?", {
		name:g_name,
		g_KindCode:g_KindCode,
		page:curPage,
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			if(roleId==4&&data.data.billState==1){
				$("#deal").show();
				$("#refuse").show();
			}else{
				$("#deal").hide();
				$("#refuse").hide();
			}
			tmplList(data);
		}
	}, "json");
}

//加载列表
function tmplList(data){
	$("#tbody").empty();
	var list=data.data.goodsList;
	$("#stockFixDTmpl").tmpl(list).appendTo("#tbody");
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

function dealStockFix(reviewState,msg){
	confirm(msg,function(){
		$.post("dealStockFix.do?", {
			billId:billId,
			reviewState:reviewState
		}, function(data) {
			if(data.code!=1000){
				tips(data.msg);
			}else{
				window.location.href="stockFixInit.do";
			}
		}, "json"); 
	});
}