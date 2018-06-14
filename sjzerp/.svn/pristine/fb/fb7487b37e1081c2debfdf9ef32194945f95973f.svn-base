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
var nodes;

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

var curPage=1;//当前页
var totalPage=1;//总页数
var g_name="";//名称
var g_code="";//编码
var g_kindCode='';//物料分类
var tagId=sessionStorage.myTagId;

$(document).ready(function() {
	getGoodsKind();
	getTagGoods();//获取列表数据
	$("#kindSel").bind("click", kindSel);
	bindPage(getTagGoods);
	$(document).on("click", "#search", search);
	//enter键搜索
	$(document).on("keydown", "#goodsCode", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	//enter键搜索
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
			//在最前面添加一个”全部“
			var allKind={atNo:0, pId:-1,code:"", name:"全部",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

function getTagGoods() {
	$.post("getTagGoods.do?", {
		name:g_name,
		code:g_code,
		kindCode:g_kindCode,
		page:curPage,
		tagId:tagId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#goodsTmpl").tmpl(data.data.list).appendTo("#tbody");
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
	g_name=$.trim($("#goodsNm").val());
	g_code=$.trim($("#goodsCode").val());
	g_kindCode=$("#kindSel").attr("code");
	curPage=1;
	totalPage=1;
	getTagGoods();
}