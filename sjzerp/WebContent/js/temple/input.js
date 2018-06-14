//树设置
var zTreeTmpl;
var settingTmpl = {
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
			onClick: clickKindTmpl
		}
	};

function kindSelTmpl(){
	var kindObj = $("#kindSelTmpl");
	var kindOffset = $("#kindSelTmpl").offset();
	var top=$(document).scrollTop();  
	$("#kindDivTmpl").css({left:kindOffset.left + "px", top:kindOffset.top 
		+ kindObj.outerHeight()-top+ "px"}).slideDown("fast");
	$("body").bind("mousedown", onKindDownTmpl);
}

function clickKindTmpl(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#kindSelTmpl").attr("atNo", treeNode.atNo);
		$("#kindSelTmpl").attr("code", treeNode.code);
		$("#kindSelTmpl").val(treeNode.name);
		hideKindMenuTmpl();
	}
}

function hideKindMenuTmpl(){
	$("#kindDivTmpl").fadeOut("fast");
	$("body").unbind("mousedown", onKindDownTmpl);
}

function onKindDownTmpl(event) {
	if (!(event.target.id == "kindSelTmpl" || event.target.id == "kindDivTmpl" 
		|| $(event.target).parents("#kindDivTmpl").length>0)) {
		hideKindMenuTmpl();
	}
}
var nodesTmpl;
//------------------------分类菜单结束-------------------------

var g_goodsCode;
var g_goodsKindTempl=sessionStorage.stockQueryGoodsKind||"-1";//物料分类
$(document).ready(function() {
	/*$("#kindSelTmpl").attr("code", g_goodsKindTempl);
	$("#kindSelTmpl").attr("atNo", sessionStorage.stockQueryKindId);
	$("#kindSelTmpl").val(sessionStorage.stockQueryKindNm);*/
	//getGoodsKindTmpl();
	$("#kindSelTmpl").bind("click", kindSelTmpl);
	
});

//获取物料分类
/*function getGoodsKindTmpl(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			//在最前面添加一个”全部“
			var allKind={atNo:0, pId:-1, name:"全部",code:"-1",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#kindTreeTmpl"), setting, nodes);
		}
	}, "json");
}*/


