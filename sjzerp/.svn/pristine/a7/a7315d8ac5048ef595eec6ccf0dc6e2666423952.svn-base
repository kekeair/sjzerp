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

//点击分类选择框
function kindSel(){
	var kindObj = $("#kindSel");
	var kindOffset = $("#kindSel").offset();
	$("#kindDiv").css({left:kindOffset.left + "px", top:kindOffset.top 
		+ kindObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onKindDown);
}

//选择分类
function clickKind(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#kindSel").attr("code", treeNode.code);
		$("#kindSel").val(treeNode.name);
		g_kindCode=$("#kindSel").attr("code");
		hideKindMenu();
	}
}

//隐藏分类下拉框
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

//-----------------------分类结束-------------------------------
var teamBillId=sessionStorage.teamBillId;//订单Id
var customerTeamId=sessionStorage.customerTeamId;//客户(工作组Id)
var g_kindCode=-1;//物料分类
$(document).ready(function(){
	getGoodsKind();//获取分类
	//加载物料信息
	getGoodsByTerm();
	$("#kindSel").bind("click", kindSel);//加载分类框
	$(document).on("click", "#search", search);//搜索
	
	//为打印做单机事件
	$(document).on("click", "#printGoodsByClass", function(){
		var codes=$("#teamSel").val();//物料分类的编码拼接的字符串
		if(codes == "'0101%','0102%'"){
			codes=1;
		}else if(codes =="'0103%','0106%','0111%'"){
			codes=2;
		}else{
			codes=3;
		}
			window.open("../demand/exportOutDemandBill.do?bill_Id="+teamBillId+"&teamBillId="+customerTeamId+
					"&kindCode="+codes+"");
	
	});
});

//查询物料分类
function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			//在最前面添加一个”全部“
			var allKind={atNo:0, pId:-1,code:'', name:"全部",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}
//获取物料列表数据
function getGoodsByTerm() {
	//sessionStorage.kindCode=g_kindCode;
	var codes=$("#teamSel").val();//物料分类的编码拼接的字符串
	$.post("../demand/getGoodsByTerm.do?", {
		teamBillId:teamBillId,
		customerTeamId:customerTeamId,
		codes:codes
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			/*console.log(data.data);*/
			$("#goodsTmpl").tmpl(data.data).appendTo("#tbody");
		}
	}, "json");
}
//搜索
function search(){
	getGoodsByTerm();
}