//左边分类树设置
var leftTree;
var leftSetting = {
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
			onClick: clickLeft
		}
	};

function leftSel(){
	var leftObj = $("#leftSel");
	var leftOffset = $("#leftSel").offset();
	$("#leftDiv").css({left:leftOffset.left + "px", top:leftOffset.top 
		+ leftObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onLeftDown);
}

function clickLeft(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#leftSel").attr("code", treeNode.code);
		$("#leftSel").val(treeNode.name);
		hideLeftMenu();
	}
}

function hideLeftMenu(){
	$("#leftDiv").fadeOut("fast");
	$("body").unbind("mousedown", onLeftDown);
}

function onLeftDown(event) {
	if (!(event.target.id == "leftSel" || event.target.id == "leftDiv" 
		|| $(event.target).parents("#leftDiv").length>0)) {
		hideLeftMenu();
	}
}

//右边分类树设置
var rightTree;
var rightSetting = {
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
			onClick: clickRight
		}
	};

function rightSel(){
	var rightObj = $("#rightSel");
	var rightOffset = $("#rightSel").offset();
	$("#rightDiv").css({left:rightOffset.left + "px", top:rightOffset.top 
		+ rightObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onRightDown);
}

function clickRight(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#rightSel").attr("code", treeNode.code);
		$("#rightSel").val(treeNode.name);
		hideRightMenu();
		g_rightKindCode=treeNode.code;
		
		rightCurPage=1;
		getCenterGoodsList();
	}
}

function hideRightMenu(){
	$("#rightDiv").fadeOut("fast");
	$("body").unbind("mousedown", onRightDown);
}

function onRightDown(event) {
	if (!(event.target.id == "rightSel" || event.target.id == "rightDiv" 
		|| $(event.target).parents("#rightDiv").length>0)) {
		hideRightMenu();
	}
}

var nodes;

var leftCurPage=1;//左列表--当前页
var leftTotalPage=1;//左列表--总页数
var g_leftName="";//左列表--物料名称
var g_leftKindId=0;//左列表--物料分类
var g_rightKindId=0;//右列表--物料分类
var rightCurPage=1;//右列表--当前页
var rightTotalPage=1;//右列表--总页数
var g_leftKindCode='';//左物料分类
var g_rightKindCode='';//右物料分类
var goodsCode;//物料编码
$(document).ready(function() {
	getGoodsKind();
	getCenterGoodsData();//获取页面数据（包括左右两个列表）
	$("#leftSel").bind("click", leftSel);//左分类输入框
	$("#rightSel").bind("click", rightSel);//右分类输入框
	$(document).on("click", "#search", search);
	$(document).on("click", "#leftPrePage", function(){
		leftPrePage();
	});
	//enter键搜索
	$(document).on("keydown", "#goodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	//enter编码搜索
	$(document).on("keydown", "#goodsCode", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	// 确定选择一个职务
	$(document).on("click", "#leftNextPage", function(){
		leftNextPage();
	});
	$(document).on('input propertychange','#leftPageInput',function(){
		$(this).val(v.passValidate($(this).val(),4));
	});
	$(document).on("click", "#leftPageSure", function(){
		leftPageSure();
	});
	$(document).on("click", "#rightPrePage", function(){
		rightPrePage();
	});
	$(document).on("click", "#rightNextPage", function(){
		rightNextPage();
	});
	$(document).on('input propertychange','#rightPageInput',function(){
		$(this).val(v.passValidate($(this).val(),4));
	});
	$(document).on("click", "#rightPageSure", function(){
		rightPageSure();
	});
	//单个添加
	$(document).on("click", ".add", function(){
		var goodsId=$(this).attr("atNo");
		$(this).hide();
		addCenterGoods(goodsId);
	});
	//批量添加
	$(document).on("click", ".allAdd", function(){
		confirm("批量添加会都添加,确定要批量添加？",function(){
			$.post("allAddCenterGoods.do?",
					{
						name:g_leftName,
						lKindCode:g_leftKindCode,
						goodsCode:goodsCode
					},
					function(data){
						if (data.code!=1000) {
							tips(data.msg);
						} else {
							tips(data.msg);
							$(".add").hide();
							getCenterGoodsList();
						}
					},"json");
		})
		
	});
	$(document).on("click", ".del", function(){
		var goodsId=$(this).attr("goodsId");
		var centerGoodsId=$(this).attr("atNo");
		delCenterGoods(centerGoodsId,goodsId);
	});
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			var allKind={atNo:0, pId:-1, code:'',name:"全部",open:true};
			nodes.unshift(allKind);
			leftTree=$.fn.zTree.init($("#leftTree"), leftSetting, nodes);
			rightTree=$.fn.zTree.init($("#rightTree"), rightSetting, nodes);
		}
	}, "json");
}

//获取页面总数据（包括左右两个列表）
function getCenterGoodsData() {
	$.post("getCenterGoodsData.do?", {
		name:g_leftName,
		lKindCode:g_leftKindCode,
		rKindCode:g_rightKindCode,
		leftPage:leftCurPage,
		rightPage:rightCurPage,
		goodsCode:goodsCode
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//总物料库
			tmplLeft(data);
			//餐饮中心物料
			tmplRight(data);
		}
	}, "json");
}

//搜索
function search(){
	g_leftName=$.trim($("#goodsNm").val());
	g_leftKindCode=$("#leftSel").attr("code");
	goodsCode=$.trim($("#goodsCode").val());
	leftCurPage=1;
	leftTotalPage=1;
	getLeftGoodsList();
}

function leftPrePage(){
	leftCurPage=leftCurPage-1;
	getLeftGoodsList();
}

function leftNextPage(){
	leftCurPage=parseInt(leftCurPage)+parseInt(1);
	getLeftGoodsList();
}

function leftPageSure()
{
	var page = $.trim($("#leftPageInput").val());
	if(page=='')
		return;
	if(isNaN(page)){
		tips("请输入正确的页码");
		return;
	}
	if(parseInt(page)<1||parseInt(page) > parseInt(leftTotalPage)){
		tips("超出页码范围，无法跳转");
		return;
	}
	leftCurPage=page;	
	getLeftGoodsList();
}

//查询左列表数据
function getLeftGoodsList() {
	$.post("getLeftGoodsList.do?", {
		name:g_leftName,
		lKindCode:g_leftKindCode,
		leftPage:leftCurPage,
		goodsCode:goodsCode
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//总物料库
			tmplLeft(data);
		}
	}, "json");
}

function rightPrePage(){
	rightCurPage=rightCurPage-1;
	getCenterGoodsList();
}

function rightNextPage(){
	rightCurPage=parseInt(rightCurPage)+parseInt(1);
	getCenterGoodsList();
}

function rightPageSure()
{
	var page = $.trim($("#rightPageInput").val());
	if(page=='')
		return;
	if(isNaN(page)){
		tips("请输入正确的页码");
		return;
	}
	if(parseInt(page)<1||parseInt(page) > parseInt(rightTotalPage)){
		tips("超出页码范围，无法跳转");
		return;
	}
	rightCurPage=page;	
	getCenterGoodsList();
}

//查询右列表数据
function getCenterGoodsList() {
	$.post("getCenterGoodsList.do?", {
		rKindCode:g_rightKindCode,
		rightPage:rightCurPage,
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tmplRight(data);
		}
	}, "json");
}

//向餐饮中心使用的物料当中添加物料
function addCenterGoods(goodsId){
	g_rightKindId=0;
	rightCurPage=1;
	rightTotalPage=1;
	$("#rightSel").attr("atNo",0);
	$("#rightSel").val("全部");
	$.post("addCenterGoods.do?", {
		rightKindId:g_rightKindId,
		rightPage:rightCurPage,
		goodsId:goodsId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tmplRight(data);
		}
	}, "json");
}

//从餐饮中心使用的物料当中删除物料
function delCenterGoods(centerGoodsId,goodsId){
	var curSize=$(".rightGoods").size;
	if(curSize==1){
		rightCurPage=rightCurPage-1;
		if(rightCurPage==0)
			rightCurPage==1;
	}
	$.post("delCenterGoods.do?", {
		rightKindId:g_rightKindId,
		rightPage:rightCurPage,
		centerGoodsId:centerGoodsId,
		goodsId:goodsId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#add"+goodsId).show();
			tmplRight(data);
		}
	}, "json");
}

//加载左列表
function tmplLeft(data){
	$("#leftbody").empty();
	var leftList=data.data.leftGoodsList;
	$("#leftTmpl").tmpl(leftList).appendTo("#leftbody");
	leftTotalPage=data.data.leftTotalPage;
	if(leftCurPage==1)
		$("#leftPrePage").attr("disabled","disabled");
	else
		$("#leftPrePage").removeAttr("disabled");
	if(leftCurPage==leftTotalPage)
		$("#leftNextPage").attr("disabled","disabled");
	else
		$("#leftNextPage").removeAttr("disabled");
	$("#leftCurPage").text(leftCurPage);
	$("#leftTotalPage").text(leftTotalPage);
}

//加载右列表
function tmplRight(data){
	$("#rightbody").empty();
	var rightList=data.data.rightGoodsList;
	$("#rightTmpl").tmpl(rightList).appendTo("#rightbody");
	rightTotalPage=data.data.rightTotalPage;
	if(rightCurPage==1)
		$("#rightPrePage").attr("disabled","disabled");
	else
		$("#rightPrePage").removeAttr("disabled");
	if(rightCurPage==rightTotalPage)
		$("#rightNextPage").attr("disabled","disabled");
	else
		$("#rightNextPage").removeAttr("disabled");
	$("#rightCurPage").text(rightCurPage);
	$("#rightTotalPage").text(rightTotalPage);
}