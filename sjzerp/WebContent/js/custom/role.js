//角色树设置
var roleTree;
var roleSetting = {
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
			parent:true,
			leaf:true
		},
		simpleData: {
			enable: true,
			idKey: "atNo",
			pIdKey: "pId",
			rootPId: -1
		}
	},
	callback: {
		onClick: clickRole,
		beforeRename: beforeRename
	}
};
var roleNodes;
//权限树设置
var powerTree;
var powerSetting={
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true,
			rootPId: -1
		}
	}
};




//权限
var powerNodes;

//获得动态菜单
/*$(document).ready(function() {
	$.post("../list/getlist.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			powerNodes = data.data;
			powerNodes[0].open=true;
		}
	}, "json");
	
});
*/

$(document).ready(function() {
	//获得动态菜单
	$.post("../list/getlist.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			powerNodes = data.data;
			powerNodes[0].open=true;
			powerTree=$.fn.zTree.init($("#powerTree"), powerSetting, powerNodes);
		}
	}, "json");
	
	getInitData();
	$("#addNode").bind("click", addNode);//添加角色
	$("#editNode").bind("click", editRoleNm);//编辑角色
	$("#delNode").bind("click", delRole);//删除角色
	$("#savePower").bind("click", savePower);//保存权限设置
	
});

/**
 * 初始化数据
 */
function getInitData() {
	$.post("getRole.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			roleNodes=data.data;
			roleNodes[0].open=true;
			roleTree=$.fn.zTree.init($("#roleTree"), roleSetting, roleNodes);
		}
	}, "json");
}

/**
 * 点击角色节点
 */
function clickRole(event, treeId, treeNode,clickFlag){
	powerTree.checkAllNodes(false);
	if(!treeNode.isParent){
        var nodePower=treeNode.power;
        //把对应的权限勾选上
        if(nodePower!=null&&nodePower.length>0){
        	var powerArray=nodePower.split(',');
        	var l=powerArray.length;
        	for(var i=0;i<l;i++){
        		powerTree.checkNode(
        				powerTree.getNodeByParam("id", parseInt(powerArray[i])),true,true);
        	}
        }
        
	}
}

/**
 * 编辑角色--前置方法
 */
function editRoleNm(){
	nodes = roleTree.getSelectedNodes();
	var treeNode = nodes[0];
	if (nodes.length == 0) {
		tips("请先选择一个节点");
		return;
	}
	if(treeNode.atNo<11){
		tips("该角色为基础角色，无法修改");
		return;
	}
	roleTree.editName(treeNode);
}

/**
 * 编辑角色--提交后台
 */
function beforeRename(treeId, treeNode, newName){
	//验证
	if (newName.length == 0||newName.length>20) {
		tips(newName.length==0?"节点名称不能为空":"节点名称过长");
		setTimeout(function(){
			roleTree.editName(treeNode);
		}, 10);
		return false;
	}
	$.post("editRoleNm.do?", {
		name:newName,
		atNo:treeNode.atNo
	}, function(data) {
	}, "json");
	return true;
}

/**
 * 添加角色
 */
function addNode() {
	nodes = roleTree.getSelectedNodes();
	var treeNode = nodes[0];
	if(treeNode&&treeNode.levelOrder!=1){
		$.post("addRole.do?", {
			pId:treeNode.atNo,
			levelOrder:treeNode.levelOrder+1,
			name:'未取名'//默认为‘未取名’
		}, function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				var isParent=true;
				if(treeNode.levelOrder==0)
					isParent=false;
				treeNode = roleTree.addNodes(treeNode, {atNo:data.data, pId:treeNode.atNo,levelOrder:treeNode.levelOrder+1, isParent:isParent, name:"未取名"});
				roleTree.editName(treeNode[0]); //添加子节点后，让其属于编辑状态
			}
		}, "json");
	}
}

/**
 * 保存权限设置
 */
function savePower(){
	var roleNodes = roleTree.getSelectedNodes();
	var roleNode = roleNodes[0];
	if (roleNodes.length == 0||roleNode.levelOrder!=1) {
		tips("请先选择一个角色");
		return;
	}
	var powerNodes = powerTree.transformToArray(powerTree.getNodes()); 
	var l=powerNodes.length;
	var power="";
	for (var i = 0; i < l; i++) { 
		var powerNode=powerNodes[i];
		if((powerNode.id+"").length==3&&powerNode.checked)//如果是子节点
			power+=powerNode.id+","
	} 
	if(power.length>0)
		power=power.substring(0,power.length-1);
	$.post("editRolePower.do?", {
		power:power,
		atNo:roleNode.atNo
	}, function(data) {
		roleNode.power=power;
		tips(data.msg);
	}, "json");
}

/**
 * 删除角色询问
 */
function delRole(){
	nodes = roleTree.getSelectedNodes();
	var treeNode = nodes[0];
	if (nodes.length == 0||treeNode.levelOrder==-1) {
		tips("请先选择一个节点");
		return;
	}
	if(treeNode.atNo<11){
		tips("该角色为基础角色，无法删除");
		return;
	}
	confirm('确定要删除该角色吗?',function(){
		delRoleSure(treeNode);
	});
}

/**
 * 删除角色确定
 * @param treeNode
 */
function delRoleSure(treeNode){
	load();
	$.post("delRole.do?", {
		atNo:treeNode.atNo
	}, function(data) {
		closeAll();
		if(data.code==1000){
			roleTree.removeNode(treeNode);
			powerTree.checkAllNodes(false);
		}else{
			tips(data.msg);
		}
	}, "json");
	
}