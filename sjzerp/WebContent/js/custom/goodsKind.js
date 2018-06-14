//树设置
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
			isMove: false
		}
	},
	data: {
		keep: {
			parent:false,
			leaf:false
		},
		simpleData: {
			enable: true,
			idKey: "atNo",
			pIdKey: "pId",
			rootPId: -1
		}
	},
	callback: {
		beforeRename: beforeRename
	}
};
var nodes;

$(document).ready(function() {
	getInitData();
	$("#addNode").bind("click", addNode);//添加分类
	$("#editNode").bind("click", editKindNm);//编辑角色
	$("#delNode").bind("click", delKind);//删除角色
});

/**
 * 初始化数据
 */
function getInitData() {
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			nodes=data.data;
			var allKind={atNo:0, pId:-1, name:"全部",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#zTree"), setting, nodes);
		}
	}, "json");
}

/**
 * 编辑角色--前置方法
 */
function editKindNm(){
	var selectNodes = zTree.getSelectedNodes();
	var selectNode = selectNodes[0];
	if (selectNodes.length == 0) {
		tips("请先选择一个节点");
		return;
	}
	zTree.editName(selectNode);
}

/**
 * 编辑角色--提交后台
 */
function beforeRename(treeId, treeNode, newName){
	if (newName.length == 0||newName.length>20) {
		tips(newName.length==0?"分类名称不能为空":"分类名称过长");
		setTimeout(function(){
			zTree.editName(treeNode);
		}, 10);
		return false;
	}
	$.post("editGoodsKindNm.do?", {
		name:newName,
		atNo:treeNode.atNo
	}, function(data) {
	}, "json");
	return true;
}

/**
 * 添加分类
 */
function addNode() {
	var selectNodes = zTree.getSelectedNodes();
	var selectNode = selectNodes[0];
	if(selectNode){
		$.post("addGoodsKind.do?", {
			pId:selectNode.atNo,
			code:selectNode.code,
			name:'未取名'
		}, function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				var newNodes = zTree.addNodes(selectNode, {atNo:data.data.atNo, 
					pId:selectNode.atNo,code:data.data.code,name:"未取名"});
				zTree.editName(newNodes[0]); 
			}
		}, "json");
	}
}

/**
 * 删除分类询问
 */
function delKind(){
	var selectNodes = zTree.getSelectedNodes();
	var selectNode = selectNodes[0];
	if (selectNodes.length == 0||selectNodes.atNo==0) {
		tips("请先选择一个节点");
		return;
	}
	confirm('确定要删除该分类吗?',function(){
		delKindSure(selectNode);
	});
}

/**
 * 删除分类确定
 * @param treeNode
 */
function delKindSure(treeNode){
	load();
	$.post("delGoodsKind.do?", {
		atNo:treeNode.atNo
	}, function(data) {
		closeAll();
		if(data.code==1000){
			zTree.removeNode(treeNode);
		}else{
			tips(data.msg);
		}
	}, "json");
}