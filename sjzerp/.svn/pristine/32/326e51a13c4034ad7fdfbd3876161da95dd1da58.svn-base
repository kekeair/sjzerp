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
var g_goodsCode;
var g_goodsKind=sessionStorage.stockQueryGoodsKind||"-1";//物料分类
$(document).ready(function() {
	g_customId = sessionStorage.demandSummCustomId||-1;
	g_stime=sessionStorage.demandSummStime||(getDateStr(0)+" 00:00:00");
	g_etime=sessionStorage.demandSummEtime||(getDateStr(0)+" 23:00:00");
	$("#stime").val(g_stime);
	$("#etime").val(g_etime);
	$("#kindSel").attr("code", g_goodsKind);
	$("#kindSel").attr("atNo", sessionStorage.stockQueryKindId);
	$("#kindSel").val(sessionStorage.stockQueryKindNm);
	getGoodsKind();
	$("#kindSel").bind("click", kindSel);
	getDemandSummary();//销售汇总
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
	
	$(document).on("click", "#excelOut", excelOut);//导出
	$(document).on("click", "#search", search);
	//双点击
	$(document).on("dblclick", ".goodsNmTr", function(){
		var goodsId=$(this).attr("goodsId");
		var goodsType=$(this).attr("goodsType");
		var demandListDId=$(this).attr("demandListDId");
		window.location.href="demandSummByGoodsInit.do?goodsId="+goodsId
		+"&goodsType="+goodsType+"&stime="+g_stime+"&etime="+g_etime
		+"&demandListDId="+demandListDId+"&g_customId="+g_customId;
	});
	
});

//导出

function excelOut(){
	var goodsNm=$("#goodsNm").val();
	g_goodsKind=$("#kindSel").attr("code");
	window.location.href="../demandSumm/exportOutDemandSummAction.do?stime="+g_stime+"&etime="+g_etime+"&g_customId="+g_customId+
	"&goodsNm="+goodsNm+"&kindCode="+g_goodsKind;
	/*$.post("../demandSumm/exportOutDemandSummAction.do?", {
		stime:g_stime,
		etime:g_etime,
		g_customId:g_customId,
		goodsNm:goodsNm,
		kindCode:g_goodsKind
	}, function(data) {
	}, "json");*/
}


//销售汇总
function getDemandSummary() {
	var goodsNm=$("#goodsNm").val();
	$.post("getDemandSummary.do?", {
		stime:g_stime,
		etime:g_etime,
		g_customId:g_customId,
		goodsNm:goodsNm,
		kindCode:g_goodsKind
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#demandTmpl").tmpl(data.data).appendTo("#tbody");
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
	sessionStorage.demandSummStime=g_stime;
	sessionStorage.demandSummEtime=g_etime;
	sessionStorage.demandSummCustomId=g_customId;
	sessionStorage.stockQueryGoodsKind=g_goodsKind;
	sessionStorage.stockQueryKindId=$("#kindSel").attr("atNo");
	sessionStorage.stockQueryKindNm=$("#kindSel").val();
	getDemandSummary();
}

