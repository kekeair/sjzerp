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
		 kindCode = treeNode.code;
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
var nodes;

var leftCurPage=1;//左列表--当前页
var leftTotalPage=1;//左列表--总页数
var g_leftName="";//左列表--名称
var g_leftKindId=0;//左列表--分类id
var rightCurPage=1;//右列表--当前页
var rightTotalPage=1;//右列表--总页数
var kindCode;
var goodsCode;//物料编码
$(document).ready(function() {
	$("#header").text($("#supplier").find("option:selected").text()+"提供的物料");
	getGoodsKind();
	getSupplierGoodsData();//查询画面总数据（包括左右列表）
	$("#leftSel").bind("click", leftSel);
	$(document).on("click", "#search", search);
	
	//enter名称键搜索
	$(document).on("keydown", "#goodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	//enter编码搜索
	$(document).on("keydown", "#goodsCode", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	$(document).on("click", "#leftPrePage", function(){
		leftPrePage();
	});
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
	$("#supplier").change(changeSupplier);//切换
	$(document).on("click", ".add", function(){
		var goodsId=$(this).attr("goodsId");
		addSupplierGoods(goodsId);
	});
	
	//批量添加
	$(document).on("click", ".allAdd", function(){
		var supplierId = $("#supplier").val();
		confirm("批量添加会都添加,确定要批量添加？",function(){
			$.post("allAddSupplierGoods.do?",
					{
						name:g_leftName,
						kindCode:kindCode,
						goodsCode:goodsCode,
						supplierId:supplierId
					},
					function(data){
						if (data.code!=1000) {
							tips(data.msg);
						} else {
							tips(data.msg);
							$(".add").hide();
							getSupplierGoods();
							getCenterAvaiGoods();
						}
					},"json");
		});
	});
	
	$(document).on("click", ".del", function(){
		var goodsId=$(this).attr("goodsId");
		var supplierGoodsId=$(this).attr("atNo");
		delSupplierGoods(supplierGoodsId,goodsId);
	});
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			var allKind={atNo:0, pId:-1, name:"全部",open:true};
			nodes.unshift(allKind);
			leftTree=$.fn.zTree.init($("#leftTree"), leftSetting, nodes);
		}
	}, "json");
}

//查询画面总数据（包括左右列表）
function getSupplierGoodsData() {
	$.post("getSupplierGoodsData.do?", {
		name:g_leftName,
		kindCode:kindCode,
		leftPage:leftCurPage,
		supplierId:$("#supplier").val()||-1,
		rightPage:rightCurPage,
		goodsCode:goodsCode
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//可用物料
			tmplLeft(data);
			//餐饮中心物料
			tmplRight(data);
		}
	}, "json");
}

//搜索
function search(){
	g_leftName=$.trim($("#goodsNm").val());
	leftCurPage=1;
	leftTotalPage=1;
	goodsCode=$.trim($("#goodsCode").val());
	getCenterAvaiGoods();
}

function leftPrePage(){
	leftCurPage=leftCurPage-1;
	getCenterAvaiGoods();
}

function leftNextPage(){
	leftCurPage=parseInt(leftCurPage)+parseInt(1);
	getCenterAvaiGoods();
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
	getCenterAvaiGoods();
}

//查询左列表数据
function getCenterAvaiGoods() {
	$.post("getCenterAvaiGoods.do?", {
		name:g_leftName,
		kindCode:kindCode,
		leftPage:leftCurPage,
		supplierId:$("#supplier").val()||-1,
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
	getSupplierGoods();
}

function rightNextPage(){
	rightCurPage=parseInt(rightCurPage)+parseInt(1);
	getSupplierGoods();
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
	getSupplierGoods();
}

//查询右列表数据
function getSupplierGoods() {
	$.post("getSupplierGoods.do?", {
		rightPage:rightCurPage,
		supplierId:$("#supplier").val()||-1
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tmplRight(data);
		}
	}, "json");
}

//添加供应商可提供的物料
function addSupplierGoods(goodsId){
	rightCurPage=1;
	rightTotalPage=1;
	$.post("addSupplierGoods.do?", {
		rightPage:rightCurPage,
		goodsId:goodsId,
		supplierId:$("#supplier").val()||-1
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//左边列表的数据本地调整，不再重新查询
			$("#add"+goodsId).hide();
			var supplierNm=$("#supplierNm"+goodsId).text();
			if(supplierNm&&supplierNm.length>0)
				supplierNm=supplierNm+','+
					$("#supplier").find("option:selected").text();
			else
				supplierNm=$("#supplier").find("option:selected").text();
			$("#supplierNm"+goodsId).text(supplierNm);
			tmplRight(data);
		}
	}, "json");
}

function delSupplierGoods(supplierGoodsId,goodsId){
	var curSize=$(".rightGoods").size;
	if(curSize==1){
		rightCurPage=rightCurPage-1;
		if(rightCurPage==0)
			rightCurPage==1;
	}
	$.post("delSupplierGoods.do?", {
		rightPage:rightCurPage,
		supplierGoodsId:supplierGoodsId,
		supplierId:$("#supplier").val()
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			//左边列表的数据的本地调整，不再重新查询
			$("#add"+goodsId).show();
			var supplierNm=$("#supplierNm"+goodsId).text();
			var selectSupplier=$("#supplier").find("option:selected").text()
			if(supplierNm&&supplierNm.length>0){
				supplierNm=supplierNm+',';
				supplierNm=supplierNm.replace(selectSupplier+",","");
				if(supplierNm.length>0&&supplierNm.charAt(supplierNm.length-1)==',')
					supplierNm=supplierNm.substring(0,supplierNm.length-1);
			}else{
				supplierNm=$("#supplier").find("option:selected").text();
			}
			$("#supplierNm"+goodsId).text(supplierNm);
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

//更换供应商
function changeSupplier(){
	$("#header").text($("#supplier").find("option:selected").text()+"提供的物料");
	leftCurPage=1;//当前页
	leftTotalPage=1;//总页数
	g_leftName="";
	$("#goodsNm").val("");
	var g_leftKindId=0;
	$("#leftSel").attr("atNo",0);
	$("#leftSel").val("全部");
	rightCurPage=1;
	rightTotalPage=1;
	getSupplierGoodsData();
}