// ==============================tree===========================================================
// 树设置
// 左边分类树设置
var zTree;
var setting = {
	view : {
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "atNo",
			pIdKey : "pId",
			rootPId : -1
		}
	},
	callback : {
		onClick : clickKind
	}
};

function kindSel() {
	var kindObj = $("#kindSel");
	var kindOffset = $("#kindSel").offset();
	var scrollT = $("#kindSel").scrollTop();
	$("#kindDiv").css({
		left : kindOffset.left + "px",
		top : $("#kindSel").position().top + 140 - scrollT + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onKindDown);
}

function clickKind(event, treeId, treeNode, clickFlag) {
	if (treeNode && (!treeNode.isParent || treeNode.atNo == 0)) {
		$("#kindSel").attr("atNo", treeNode.atNo);
		$("#kindSel").val(treeNode.name);
		hideKindMenu();
	}
}

function hideKindMenu() {
	$("#kindDiv").fadeOut("fast");
	$("body").unbind("mousedown", onKindDown);
}

function onKindDown(event) {
	if (!(event.target.id == "kindSel" || event.target.id == "kindDiv" || $(
			event.target).parents("#kindDiv").length > 0)) {
		hideKindMenu();
	}
}
// =====================================Tree==========================================================

//var oo = new mSift('oo', 20);
// 搜索数据
//oo.Data = [];// 物料名称搜索数据
var maxOrder = 0;// 添加行时候的下标
var idvar = 0;
var nodes;
var g_name = "";// 物料列表--物料名称
var g_kindId = 0;// 物料列表--物料分类
var selOrder = 0;
var modalObj;
var KindCode = 0;// 物料分类
var goodsNm = '';// 物料名称
var goodsCode = '';// 物料编码
var goodsIdVal = -1;// 商品Id
var supplierId = -2// 供应商id
var changeList = new Array();// 把参数变化的行存起来
var addList = new Array(); // 存储往订单中批量添加数据
var customerId = sessionStorage.customerId; //客户id
var teamDemandId = sessionStorage.teamDemandId; 
$(document).ready(function() {
	// 从purchaseListD中获取需采物料
	getNeedGoodsBypurchaseListD();
	// 获取物料名称
	$.post("../common/getGoodsList.do?", {}, function(data) {
		if (data.code == 1000) {
			oo.Data = data.data;
		}
	}, "json");
    //添加行
	$(document).on("click", "#add", addRow);
	// 添加
	$(document).on("click", ".glyphicon", function() {
		selOrder = $(this).attr("orderIndex");
		modalObj = null;
		$("#goodsModal").modal();
		// 获取物料分类
		getGoodsKind();
		// 获取物料集合
		getCenterGoods();
		bindPage(getCenterGoods);
	});

	$("#kindSel").bind("click", kindSel);// 分类输入框
	
	// 为保存添加单机事件
	$(document).on('click', '#save', function() {
		// 将物料批量加入订单
		addRemove(addList);
		for (var i = 1; i <= maxOrder; i++) {
			var goodsNm = $("#" + i).val();
			var goodsId=$("#righttr"+i).attr("goodsId");
			var supplierNm = $("#supplier" + i).text();
			var supplierId=$("#righttr"+i).attr("supplierId");
			var orderNum = $("#input" + i).val();
			var headAtNo = $("#tbody").children("tr").eq(0).attr("headAtNo");
			var obj = {
				goodsNm : goodsNm,
				supplierNm : supplierNm,
				orderNum : orderNum,
				headAtNo : headAtNo,
				goodsId : goodsId,
				supplierId:supplierId
			};
				addList.push(obj);
		}
		if (maxOrder>=1) {
			add();
			maxOrder=0;
		}
		var trList = $("#tbody").children("tr");
		for (var i = 0; i < trList.length; i++) {
			var tds = trList.eq(i).find("td");
			var atNo = trList.eq(i).attr("atNo");
			var goodsId = trList.eq(i).attr("goodsId");
			var supplierId = trList.eq(i).attr("supplierId");
			var orderNum = tds.eq(5).children().val();
			var obj = {
				atNo : atNo,
				goodsId : goodsId,
				supplierId : supplierId,
				orderNum : orderNum
			};
				changeList.push(obj);
          }
		save();
	});
});
//将弹框中的物料移到展示列表
$(document).on("dblclick", ".goodstr", function() {
	// 获取编码,物料名称,规格型号,单位,供应商,数量
	var goodsIdVal = $(this).attr("goodsTmId");
	var goodsCode = $(this).attr("goodsCode");
	var goodsNm = $(this).attr("goodsNm");
	var spec = $(this).attr("spec");
	var unitNm = $(this).attr("unitNm");
	var supplierNm = $(this).attr("supplierNm");
	var supplierId = $(this).attr("supplierId");

	$("#goodsModal").modal('hide');
	
	$("#" + maxOrder).val(goodsNm);
	$("#id" + maxOrder).text(goodsCode);
	//赋属性
	$("#righttr"+maxOrder).attr("goodsId",goodsIdVal);
	$("#righttr"+maxOrder).attr("supplierId",supplierId);
	
	$("#spec" + maxOrder).text(spec);
	$("#unitNm" + maxOrder).text(unitNm);
	$("#supplier" + maxOrder).text(supplierNm);
});
// 获取物料分类
function getGoodsKind() {
	$.post("../common/getGoodsKind.do?", {}, function(data) {
		if (data.code == 1000) {
			nodes = data.data;
			var allKind = {
				atNo : 0,
				pId : -1,
				code : '',
				name : "全部",
				open : true
			};
			nodes.unshift(allKind);
			zTree = $.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

// 查询餐饮中心物料
function getCenterGoods() {
	$.post("../common/getCenterGoods.do?", {
		name : g_name,
		kindId : g_kindId,
		page : curPage,
	}, function(data) {
		if (data.code != 1000) {
			tips(data.msg);
		} else {
			// 可用物料
			tmplModel(data);
		}
	}, "json");
}

// 添加行
function addRow() {
	maxOrder++;
	var str = '<tr id="righttr'
			+ maxOrder
			+ '" class="righttr pointer" orderIndex='
			+ maxOrder
			+ '>'
			+ '<td class="width5" id="id'
			+ maxOrder
			+ '">'
			+ maxOrder
			+ '</td>'
			+ '<td  class="goodsNmTr width15" orderIndex='
			+ maxOrder
			+ '>'
			+ '<span class="goodsNm" style="width:100%" id="goodsNm'
			+ maxOrder
			+ '"></span>'
			+ '<span class="glyphicon glyphicon-search pull-right totop2"></span>'
			+ '<input class="inputClass" value="" id=' + maxOrder + '></td>'
			+ '<td class="width13" id="spec' + maxOrder + '"></td>'
			+ '<td class="width5" id="unitNm' + maxOrder + '"></td>'

			+ '<td class="width10" id="supplier' + maxOrder + '"></td>'
			+ '<td class="width10"><input value="0" id="input' + maxOrder
			+ '" ></td>' + '<td class="width5"><a class="del">删除</a></td>'
			+ '</tr>';
	$("#tbody").append(str);
	$(document).on("focus", ".inputClass", function() {
		idvar = $(this).attr("id");
		oo.Create(document.getElementById(idvar));
	});

	// 指定文本框对象建立特效
	oo.Create(document.getElementById(maxOrder));
	// tap键加行
	$("#input" + maxOrder).bind("keydown", function(event) {
		if (event.which == 9) {
			addRow();
		}
	});
	// 按enter键执行添加到订单详情列表

	/*
	 * $(document).keydown(function(event) { if (event.keyCode == 13) { //
	 * 添加到订单详情列表中 var orderNum = $("#input" + maxOrder).val();
	 * addpurchaseListD(goodsIdVal, supplierId, orderNum); } });
	 */
	$("#input" + (maxOrder - 1)).unbind();
	// $("#leftSel").bind("click", leftSel);//左分类输入框
}

// 搜索
/*$(document).on("click", "#search", search);
function search() {
	g_name = $.trim($("#goodsNm").val());
	g_kindId = $("#kindSel").attr("atNo");
	curPage = 1;
	totalPage = 1;
	getCenterGoods();
}*/

// 查询餐饮中心物料
/*function getCenterGoods() {
	$.post("../common/getCenterGoods.do?", {
		name : g_name,
		kindId : g_kindId,
		page : curPage,
	}, function(data) {
		if (data.code != 1000) {
			tips(data.msg);
		} else {
			// 可用物料
			tmplModel(data);
		}
	}, "json");
}*/

// 加载左列表
function tmplModel(data) {
	$("#ubody").empty();
	var list = data.data.goodsList;
	$("#goodsTmpl").tmpl(list).appendTo("#ubody");
	totalPage = data.data.totalPage;
	if (curPage == 1)
		$("#prePage").attr("disabled", "disabled");
	else
		$("#prePage").removeAttr("disabled");
	if (curPage == totalPage)
		$("#nextPage").attr("disabled", "disabled");
	else
		$("#nextPage").removeAttr("disabled");
	$("#curPage").text(curPage);
	$("#totalPage").text(totalPage);
}
// 加载添加物料表
function tmplRight(data) {
	$("#tbody").empty();
	var rightList = data.data.rightGoodsList;
	$("#rightTmpl").tmpl(rightList).appendTo("#tbody");
}

// 从purchaseListD中获取需采物料
function getNeedGoodsBypurchaseListD() {
	$.post("../needPurchaseBill/getPurchaseListHByHeadAtNo.do?",
			function(data) {
				if (data.code != 1000) {
					tips(data.msg);
				} else {
					$("#tbody").empty();
					$("#needPurchaseTmpl").tmpl(data.data).appendTo("#tbody");
				}
			}, "json");

}

//-------------- 从需采物料中删除---------
var purchaseListDatNo = "";
$(document).on("click", ".del", function() {
	purchaseListDatNo = $(this).attr("atNo");
	delNeedPurchaseGoods(purchaseListDatNo);
});
function delNeedPurchaseGoods(purchaseListDatNo) {
	confirm("确定要删除该物料吗？", function() {
		$.post("delOfNeedPurchaseList.do?", {
			purchaseListDatNo : purchaseListDatNo
		}, function(data) {
			tips(data.msg);
			if (data.code == 1000) {
				getNeedGoodsBypurchaseListD();
			}
		}, "json");
	});
}
//--------------------
// 保存修改
function save() {
	// 验证数字
	/*
	 * var l = changeList.length; for (var i = 0; i < l; i++) { if
	 * (isNaN(changeList[i].orderNum)) { tips("数量必须是数字"); return; } }
	 */
	$.post("editNeedGoods.do?", {
		changeJson : JSON.stringify(changeList)
	}, function(data) {
		tips(data.msg);
		if (data.code == 1000) {
			getNeedGoodsBypurchaseListD();
		}
	}, "json");
}
// 添加
function add() {
	$.post("addNeedpurchase.do?", {
		addJson : JSON.stringify(addList)
	}, function(data) {
		tips(data.msg);
		if(data.code == 1000) {
			getNeedGoodsBypurchaseListD();
		}
	}, "json");

}

// 添加数据到订单详情列表
/*function addpurchaseListD(goodsIdVal, supplierId, orderNum) {
	var headAtNo = $("#tbody").children("tr").eq(0).attr("headAtNo");
	$.post("addNeedGoods.do?", {
		headAtNo : headAtNo,
		goodsId : goodsIdVal,
		supplierId : supplierId,
		orderNum : orderNum
	}, function(data) {
		tips(data.msg);
		if (data.code == 1000) {
			getNeedGoodsBypurchaseListD();
		}
	}, "json");
}*/
//清除数组中的数据
function addRemove(addList){
	var index=addList.length;
	if(index>0){
		changeList.splice(0, index);
	}
}