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

var g_name="";//物料名称
var g_kindCode="-1";//物料分类
var g_stime="";//开始时间
var g_etime="";//结束时间
var goodsCode;//物料编码
$(document).ready(function() {
	getGoodsKind();
	var val=getDateStr(0);
	$("#stime").val(val);
	$("#etime").val(val);
	g_stime=val;
	g_etime=val;
	getStockChangeRec();//库存变动记录
	$("#kindSel").bind("click", kindSel);//右分类输入框
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
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			var allKind={atNo:0, pId:-1,code:"-1", name:"全部",open:true};
			nodes.unshift(allKind);
			kindTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

//库存变动记录
function getStockChangeRec() {
	$.post("getStockChangeRec.do?", {
		name:g_name,
		kindCode:g_kindCode,
		stime:g_stime,
		etime:g_etime,
		goodsCode:goodsCode
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
			$("#tbody").empty();
		} else {
			$("#tbody").empty();
			$("#stockTmpl").tmpl(data.data).appendTo("#tbody");
		}
	}, "json");
}

function search(){
	g_name=$.trim($("#goodsNm").val());
	g_kindCode=$("#kindSel").attr("code");
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	goodsCode=$.trim($("#goodsCode").val());
	getStockChangeRec();
}