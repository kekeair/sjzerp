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
		$("#leftSel").attr("atNo", treeNode.atNo);
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
		$("#rightSel").attr("atNo", treeNode.atNo);
		$("#rightSel").attr("code", treeNode.code);
		$("#rightSel").val(treeNode.name);
		hideRightMenu();
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
var g_leftKindCode="-1";//左列表--物料分类

var rightCurPage=1;//右列表--当前页
var rightTotalPage=1;//右列表--总页数
var g_rightName="";//右列表--物料名称
var g_rightKindCode="-1";//右列表--物料分类

var billId=sessionStorage.stockFixHId;
var gList;
var rightList;

$(document).ready(function() {
	getGoodsKind();
	getLeftList();
	if(billId>0){
		getRightList();
	}
	$("#leftSel").bind("click", leftSel);//左分类输入框
	$(document).on("click", "#leftSearch", leftSearch);
	//enter名称键搜索
	$(document).on("keydown", "#leftGoodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			leftSearch();
	});
	//enter名称键搜索
	$(document).on("keydown", "#rightGoodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			rightSearch();
	});
	$(document).on("click", "#leftPrePage", function(){
		leftPrePage();
	});
	$(document).on("click", "#leftNextPage", function(){
		leftNextPage();
	});
	$(document).on("click", "#leftPageSure", function(){
		leftPageSure();
	});
	$("#rightSel").bind("click", rightSel);//右分类输入框
	$(document).on("click", "#rightPrePage", function(){
		rightPrePage();
	});
	$(document).on("click", "#rightNextPage", function(){
		rightNextPage();
	});
	$(document).on("click", "#rightPageSure", function(){
		rightPageSure();
	});
	$(document).on("click", "#rightSearch", rightSearch);
	$(document).on('input propertychange','.goodsNum',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
	});
	$(document).on('input propertychange','.price',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
	});
	$(document).on("click", "#addTmpGoods", getTmpGoodsList);
	$(document).on("click", "#gSure", selTmpGoods);
	$(document).on("click", "#addTMpGoods", function(){
		clearUModal();
		$("#uModal").modal();
	});
	$(document).on("click", ".add", function(){
		var goodsId=$(this).attr("goodsId");
		addStockFixD(goodsId);
	});
	$("#uSure").bind("click", addExtraGoods);//添加自定义物料
	$(document).on("click", ".del", function(){
		var goodsId=$(this).attr("goodsId");
		var stockFixDId=$(this).attr("detailId");
		delStockFixD(stockFixDId,goodsId);
	});
	$(document).on("click", ".save", function(){
		var stockFixDId=$(this).attr("detailId");
		updateStockFixD(stockFixDId);
	});
	$(document).on("click", "#submit", dealStockFix);
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			var allKind={atNo:0, pId:-1, name:"全部",code:"-1",open:true};
			nodes.unshift(allKind);
			leftTree=$.fn.zTree.init($("#leftTree"), leftSetting, nodes);
			rightTree=$.fn.zTree.init($("#rightTree"), rightSetting, nodes);
		}
	}, "json");
}

//搜索
function leftSearch(){
	g_leftName=$.trim($("#leftGoodsNm").val());
	g_leftKindCode=$("#leftSel").attr("code");
	leftCurPage=1;
	leftTotalPage=1;
	getLeftList();
}

function leftPrePage(){
	leftCurPage=leftCurPage-1;
	getLeftList();
}

function leftNextPage(){
	leftCurPage=parseInt(leftCurPage)+parseInt(1);
	getLeftList();
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
	getLeftList();
}

//查询左列表数据
function getLeftList() {
	$.post("getCenterGoods.do?", {
		name:g_leftName,
		kindCode:g_leftKindCode,
		page:leftCurPage,
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//总物料库
			tmplLeft(data);
		}
	}, "json");
}

//搜索
function rightSearch(){
	g_rightName=$.trim($("#rightGoodsNm").val());
	g_rightKindCode=$("#rightSel").attr("code");
	rightCurPage=1;
	rightTotalPage=1;
	getRightList();
}

function rightPrePage(){
	rightCurPage=rightCurPage-1;
	getRightList();
}

function rightNextPage(){
	rightCurPage=parseInt(rightCurPage)+parseInt(1);
	getRightList();
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
	getRightList();
}

//查询右列表数据
function getRightList() {
	$.post("getStockFixD.do?", {
		name:g_rightName,
		kindCode:g_rightKindCode,
		page:rightCurPage,
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tmplRight(data);
		}
	}, "json");
}

//向物料修正详细中添加物料
function addStockFixD(goodsId){
	g_rightName="";
	g_rightKindCode="-1";
	rightCurPage=1;
	rightTotalPage=1;
	$("#rightSel").attr("atNo",0);
	$("#rightSel").attr("code","-1");
	$("#rightSel").val("全部");
	$.post("addStockFixD.do?", {
		name:g_rightName,
		kindCode:g_rightKindCode,
		page:rightCurPage,
		goodsId:goodsId,
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#add"+goodsId).hide();
			billId=data.data.billId;
			sessionStorage.stockFixHId=billId;
			tmplRight(data);
		}
	}, "json");
}

//从库存修正详细中删除物料
function delStockFixD(stockFixDId,goodsId){
	var curSize=$(".rightGoods").size;
	if(curSize==1){
		rightCurPage=rightCurPage-1;
		if(rightCurPage==0)
			rightCurPage==1;
	}
	$.post("delStockFixD.do?", {
		name:g_rightName,
		kindCode:g_rightKindCode,
		page:rightCurPage,
		stockFixDId:stockFixDId,
		billId:billId
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
	var leftList=data.data.goodsList;
	$("#leftTmpl").tmpl(leftList).appendTo("#leftbody");
	leftTotalPage=data.data.totalPage;
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
	rightList=data.data.goodsList;
	$("#rightTmpl").tmpl(rightList).appendTo("#rightbody");
	rightTotalPage=data.data.totalPage;
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

function clearUModal(){
	$("#uGoodsNm").val("");
	$("#uGoodsUnit option:first").prop("selected", 'selected');
	$("#uGoodsNum").val("");
	$("#uGoodsPrice").val("");
}

function addExtraGoods(){
	var goodsCode=$.trim($("#uGoodsCode").val());
	if(goodsCode==''){
		tips("物料编码不能为空");
		$("#uGoodsCode").focus();
		return;
	}
	var goodsNm=$.trim($("#uGoodsNm").val());
	if(goodsNm==''){
		tips("物料名称不能为空");
		$("#uGoodsNm").focus();
		return;
	}
	var goodsUnit=$("#uGoodsUnit").val();
	if(goodsUnit==-1){
		tips("请选择一个单位");
		$("#uGoodsUnit").focus();
		return;
	}
	$.post("addExtraGoods.do?", {
		goodsCode:goodsCode,
		goodsNm:goodsNm,
		goodsUnit:goodsUnit
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#uModal").modal('hide');
			gList=data.data;
			$("#gbody").empty();
			$("#gTmpl").tmpl(gList).appendTo("#gbody");
		}
	}, "json");
}

function getTmpGoodsList(){
	if(!gList||gList.length==0){
		$.post("getTmpGoodsList.do?", {
		}, function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				gList=data.data;
				$("#gbody").empty();
				$("#gTmpl").tmpl(gList).appendTo("#gbody");
				$("#gModal").modal();
			}
		}, "json");
	}else{
		$("#gbody").empty();
		$("#gTmpl").tmpl(gList).appendTo("#gbody");
		$("#gModal").modal();
	}
}

function selTmpGoods(){
	var goodsIdStr="";
	$("input[type=checkbox]:checked").each(function(){
		var goodsId=$(this).attr("goodsId");
		goodsIdStr=goodsIdStr+goodsId+",";
	});
	if(goodsIdStr.length>0){
		if(rightList&&rightList.length>0){
			var rLength=rightList.length;
			for(var i=0;i<rLength;i++){
				if(rightList[i].goodsType==1){
					var goodsId=rightList[i].goodsId;
					if(goodsIdStr.indexOf(goodsId+",")>=0){
						goodsIdStr=goodsIdStr.replace(goodsId+",","");
					}
				}
			}
		}
		if(goodsIdStr.length>0){
			goodsIdStr=goodsIdStr.substring(0,goodsIdStr.length-1);
			g_rightName="";
			g_rightKindCode="-1";
			rightCurPage=1;
			rightTotalPage=1;
			$("#rightSel").attr("atNo",0);
			$("#rightSel").attr("code","-1");
			$("#rightSel").val("全部");
			$.post("selTmpGoods.do?", {
				goodsIdStr:goodsIdStr,
				name:g_rightName,
				kindCode:g_rightKindCode,
				page:rightCurPage,
				billId:billId
			}, function(data) {
				if (data.code!=1000) {
					tips(data.msg);
				} else {
					$("#gModal").modal('hide');
					billId=data.data.billId;
					sessionStorage.stockFixHId;
					tmplRight(data);
				}
			}, "json");
		}else{
			$("#gModal").modal('hide');
		}
	}else{
		$("#gModal").modal('hide');
	}
}

function updateStockFixD(stockFixDId){
	var goodsNum=$.trim($("#goodsNum"+stockFixDId).val())||0;
	if(isNaN(goodsNum)){
		tips("请填写正确的数量");
		$("#goodsNum"+stockFixDId).focus();
		return;
	}
	var price=$.trim($("#price"+stockFixDId).val())||0;
	if(isNaN(price)){
		tips("请填写正确的单价");
		$("#price"+stockFixDId).focus();
		return;
	}
	$.post("updateStockFixD.do?", {
		stockFixDId:stockFixDId,
		goodsNum:goodsNum,
		price:price
	}, function(data) {
		tips(data.msg);
	}, "json");
}

function dealStockFix(){
	confirm("确定要提交该订单吗？",function(){
		$.post("dealStockFix.do?", {
			billId:billId,
			reviewState:1
		}, function(data) {
			if(data.code!=1000){
				tips(data.msg);
			}else{
				window.location.href="stockFixInit.do";
			}
		}, "json"); 
	});
}