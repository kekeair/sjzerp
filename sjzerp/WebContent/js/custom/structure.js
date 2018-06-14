//架构树设置
var zTree;
var setting = {
	view: {
		selectedMulti: false
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		drag: {
			isCopy: false,
//			isMove: true,
			isMove: false,
			autoExpandTrigger:true,
			prev:false,
			next:false
		}
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "atNo",
			pIdKey: "pId",
			rootPId: 0
		}
	},
	callback: {
		onClick: clickStruct,
		beforeDrop:beforeDrop
	}
};
var nodes;
//编辑状态：0添加1编辑
var state;

$(document).ready(function() {
	getInitData();
	$("#addNode").bind("click", addNode);
	$("#save").bind("click", save);
	$("#isCenter").bind("click", isCenterChange);//是否是餐饮中心勾选
	$("#province").change(function(){
		provinceChange($(this).val());
	});
	$("#city").change(function(){
		cityChange($(this).val());
	});
});

//初始化数据
function getInitData() {
	$.post("getStructure.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			nodes=data.data;
			nodes[0].open=true;
			zTree=$.fn.zTree.init($("#zTree"), setting, nodes);
		}
	}, "json");
}

/**
 * 点击节点
 */
function clickStruct(event, treeId, treeNode,clickFlag){
	setValueToRight(treeNode);
}

function setValueToRight(treeNode){
	$("#operType").text("编辑");
	state=1;
	clearRightForm();
	$("#code").val(treeNode.code);
	$("#name").val(treeNode.name);
	if(treeNode.isCenter){//是餐饮中心，展开餐饮中心属性
		$("#isCenter").prop("checked",true);
		showCenterAttr(true);
		$("#province").val(treeNode.provinceId);
		setCityAndCounty(treeNode.provinceId,treeNode.cityId,treeNode.countyId);
		$("#address").val(treeNode.address);
	}else{//不是餐饮中心，隐藏餐饮中心属性
		$("#isCenter").prop("checked",false);
		showCenterAttr(false);
	}
}

//查询市和县
function setCityAndCounty(provinceId,cityId,countyId){
	$.post("../common/getCityAndCounty.do?", {
		provinceId:provinceId,
		cityId:cityId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#city").empty();
			$("#cityTmpl").tmpl(data.data).appendTo("#city");
			$("#county").empty();
			$("#countyTmpl").tmpl(data.data).appendTo("#county");
			$("#city").val(cityId);
			$("#county").val(countyId);
		}
	}, "json");
}

function showCenterAttr(show){
	if(show){
		$(".center").removeClass("hide");
	}else{
		$(".center").addClass("hide");
	}
}

function addNode() {
	$("#operType").text("添加");
	state=0;
	var selectNodes = zTree.getSelectedNodes();
	var selectNode = selectNodes[0];
	if (selectNodes.length == 0) {
		tips("请先选择一个节点");
		return;
	}
	clearRightForm();
}

//清空右边form
function clearRightForm(){
	$("#code").val("");
	$("#name").val("");
	$("#isCenter").prop("checked",false);
	$("#province").val(-1);
	$("#city").empty();
	$("#city").append('<option value="-1">请选择...</option>');
	$("#county").empty();
	$("#county").append('<option value="-1">请选择...</option>');
	$("#address").val("");
	showCenterAttr(false);
}

function save(){
	var selectNodes = zTree.getSelectedNodes();
	var selectNode = selectNodes[0];
	if (selectNodes.length == 0) {
		tips("请先选择一个节点");
		return;
	}
	var atNo=-1,parentId=-1,levelOrder,pCode;
	if(state==1){//编辑
		atNo=selectNode.atNo;
	}else{//新增
		parentId=selectNode.atNo;
		levelOrder=selectNode.levelOrder+1;
		pCode=selectNode.code;
	}
	var name=$.trim($("#name").val());
	if(name==''){
		tips('名称不能为空');
		$("#name").focus();
		return;
	}
	var isCenter=0,provinceId,cityId,countyId;
	if($("#isCenter").get(0).checked){
		isCenter=1;
		provinceId=$("#province").val();
		if(provinceId==-1){
			tips('请选择省');
			$("#province").focus();
			return;
		}
		cityId=$("#city").val();
		if(cityId==-1){
			tips('请选择市');
			$("#city").focus();
			return;
		}
		countyId=$("#county").val();
		if(countyId==-1){
			tips('请选择县');
			$("#county").focus();
			return;
		}
	}
	var address=$.trim($("#address").val());
	load();
	$.post("editStructure.do?", {
		atNo:atNo,
		pCode:pCode,
		pId:parentId,
		levelOrder:levelOrder,
		name:name,
		isCenter:isCenter,
		provinceId:provinceId,
		cityId:cityId,
		countyId:countyId,
		address:address
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			if(state==1){
				//编辑状态
				selectNode.name=name;
				selectNode.isCenter=isCenter;
				selectNode.provinceId=provinceId;
				selectNode.cityId=cityId;
				selectNode.countyId=countyId;
				selectNode.address=address;
				zTree.updateNode(selectNode);
			}else{
				$("#code").val(data.data.code);
				var newNodes=zTree.addNodes(selectNode, 
					{atNo:data.data.structId, pId:parentId,code:data.data.code,name:name,
					levelOrder:levelOrder,isCenter:isCenter,provinceId:provinceId,
					cityId:cityId,countyId:countyId,address:address});
				zTree.selectNode(newNodes[0]);
			}
		}
	}, "json");
}

//改变省
function provinceChange(provinceId){
	$("#city").empty();
	$("#city").append('<option value="-1">请选择...</option>');
	$("#county").empty();
	$("#county").append('<option value="-1">请选择...</option>');
	$.post("../common/getCityList.do?", {
		provinceId:provinceId
	}, function(data) {
		$("#city").empty();
		$("#cityTmpl").tmpl(data.data).appendTo("#city");
		$("#city").val(-1);
	}, "json");
}

//改变市
function cityChange(cityId){
	$("#county").empty();
	$("#county").append('<option value="-1">请选择...</option>');
	$.post("../common/getCountyList.do?", {
		cityId:cityId
	}, function(data) {
		$("#county").empty();
		$("#countyTmpl").tmpl(data.data).appendTo("#county");
		$("#county").val(-1);
	}, "json");
}

function isCenterChange(){
	showCenterAttr($("#isCenter").get(0).checked)
}

//拖拽节点
function beforeDrop(treeId,treeNodes,targetNode){
	var pId=targetNode.atNo;
	var levelOrder=targetNode.levelOrder+1;
	var moveNode=treeNodes[0];
	$.post("updateStructOrder.do?", {
		pId:pId,
		levelOrder:levelOrder,
		atNo:moveNode.atNo
	}, function(data) {
		moveNode.pId=pId;
		moveNode.levelOrder=levelOrder;
		var selectNodes = zTree.getSelectedNodes();
		setValueToRight(moveNode);
	}, "json");
}