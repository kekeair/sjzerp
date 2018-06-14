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
				rootPId: 0
			}
		},
		callback: {
			onClick: clickKind
		}
	};
var nodes;

function kindSel(){
	var kindObj = $("#goodsKind");
	var kindOffset = $("#goodsKind").offset();
	$("#kindDiv").css({left:kindOffset.left + "px", top:kindOffset.top 
		+ kindObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onKindDown);
}

function clickKind(event, treeId, treeNode,clickFlag){
	if(treeNode&&!treeNode.isParent){
		$("#goodsKind").attr("atNo", treeNode.atNo);
		$("#goodsKind").val(treeNode.name);
		hideKindMenu();
	}
}

function hideKindMenu(){
	$("#kindDiv").fadeOut("fast");
	$("body").unbind("mousedown", onKindDown);
}

function onKindDown(event) {
	if (!(event.target.id == "goodsKind" || event.target.id == "kindDiv" 
		|| $(event.target).parents("#kindDiv").length>0)) {
		hideKindMenu();
	}
}

$(document).ready(function() {
	getGoodsKind();
	if(goodsId!=-1){//编辑，需要获取物料详情
		$("#gHead").text("编辑物料");
		getGoodsById();
	}else{
		$("#gHead").text("新增物料");
	}
	$("#goodsKind").bind("click", kindSel);
	$(document).on("click", "#sure", save);
});

function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			zTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

//根据id查询物料详情
function getGoodsById(){
	$.post("getGoodsById.do?", {
		goodsId:goodsId
	}, function(data) {
		if(data.code==1000){
			var g=data.data;
			$("#code").val(g.code);
			$("#goodsNm").val(g.goodsNm);
			alias:$("#alias").val(g.alias);
			$("#goodsKind").val(g.kindNm);
			$("#goodsKind").attr("atNo",g.kindId);
			$("#baseUnit").val(g.baseUnit);
			shortNm:$("#shortNm").val(g.shortNm);
			$("#tradiNm").val(g.tradiNm);
			if(g.illegal==0)
				$("#illegal0").prop("checked",true);
			else
				$("#illegal1").prop("checked",true);
			$("#spec").val(g.spec);
			$("#groupStandardCode").val(g.groupStandardCode),
			$("#assistAttr").val(g.assistAttr);
			$("#assistUnit").val(g.assistUnit);
			$("#sequenceUnit").val(g.sequenceUnit);
			$("#helpCode").val(g.helpCode);
			$("#barCode").val(g.barCode),
			$("#approvalNumber").val(g.approvalNumber);
			$("#picNumber").val(g.picNumber);
			$("#weightUnit").val(g.weightUnit);
			$("#lengthUnit").val(g.lengthUnit);
			$("#grossWeight").val(g.grossWeight==0?"":g.grossWeight);
			$("#netWeight").val(g.netWeight==0?"":g.netWeight);
			$("#length").val(g.length==0?"":g.length);
			$("#width").val(g.width==0?"":g.width);
			$("#height").val(g.height==0?"":g.height);
			$("#volumeUnit").val(g.volumeUnit);
			$("#volume").val(g.volume==0?"":g.volume);
			$("#equipAttr").val(g.equipAttr);
			$("#tradeMark").val(g.tradeMark);
			$("#brand").val(g.brand);
			$("#depict").val(g.depict);
			$("#keyword").val(g.keyword);
			$("#remark").val(g.remark);
			$("#summary").val(g.summary);
			$("#firstLetter").val(g.firstLetter);
			$("#spell").val(g.spell);
			$("#englishNm").val(g.englishNm);
			$("#foreighNm").val(g.foreighNm);
			$("#groupId").val(g.groupId);
			$("#criteria").val(g.criteria);
			$("#minUnit").val(g.minUnit==0?"":g.minUnit);
			$("#minUnitNm").val(g.minUnitNm);
		}else{
			tips(data.msg);
		}
	}, "json");
}

//保存
function save(){
	var goodsNm=$.trim($("#goodsNm").val());
	if(goodsNm==''){
		tips('物料名称不能为空');
		$("#goodsNm").focus();
		return;
	}
	var kindId=$("#goodsKind").attr("atNo")||0;
	if(kindId<=0){
		tips('请选择一个分类');
		$("#goodsKind").focus();
		return;
	}
	var baseUnit=$("#baseUnit").val();
	if(baseUnit==-1){
		tips('请选择一个基本计量单位');
		$("#baseUnit").focus();
		return;
	}
	var grossWeight=$.trim($("#grossWeight").val());
	if(grossWeight.length>0&&isNaN(grossWeight)){
		tips('毛重只能是数字');
		$("#grossWeight").focus();
		return;
	}
	var netWeight=$.trim($("#netWeight").val());
	if(netWeight.length>0&&isNaN(netWeight)){
		tips('净重只能是数字');
		$("#netWeight").focus();
		return;
	}
	var length=$.trim($("#length").val());
	if(length.length>0&&isNaN(length)){
		tips('长度只能是数字');
		$("#length").focus();
		return;
	}
	var width=$.trim($("#width").val());
	if(width.length>0&&isNaN(width)){
		tips('宽度只能是数字');
		$("#width").focus();
		return;
	}
	var height=$.trim($("#height").val());
	if(height.length>0&&isNaN(height)){
		tips('高度只能是数字');
		$("#height").focus();
		return;
	}
	var volume=$.trim($("#volume").val());
	if(volume.length>0&&isNaN(volume)){
		tips('体积只能是数字');
		$("#volume").focus();
		return;
	}
	var minUnit=$.trim($("#minUnit").val());
	if(minUnit.length>0&&isNaN(minUnit)){
		tips('最小计量单位只能是数字');
		$("#minUnit").focus();
		return;
	}
	$.post("addGoods.do?", {
		goodsId:goodsId,//-1表示新增，大于0表示修改
		goodsNm:goodsNm,
		code:$("#code").val(),
		alias:$.trim($("#alias").val()),
		kindId:kindId,
		baseUnit:baseUnit,
		shortNm:$.trim($("#shortNm").val()),
		tradiNm:$.trim($("#tradiNm").val()),
		illegal:$('input[name="illegal"]:checked').val(),
		spec:$.trim($("#spec").val()),
		groupStandardCode:$.trim($("#groupStandardCode").val()),
		assistAttr:$.trim($("#assistAttr").val()),
		assistUnit:$("#assistUnit").val()||-1,
		sequenceUnit:$("#sequenceUnit").val()||-1,
		helpCode:$.trim($("#helpCode").val()),
		barCode:$.trim($("#barCode").val()),
		approvalNumber:$.trim($("#approvalNumber").val()),
		picNumber:$.trim($("#picNumber").val()),
		weightUnit:$("#weightUnit").val()||-1,
		lengthUnit:$("#lengthUnit").val()||-1,
		grossWeight:grossWeight||0,
		netWeight:netWeight||0,
		length:length||0,
		width:width||0,
		height:height||0,
		volumeUnit:$("#volumeUnit").val()||-1,
		volume:volume||0,
		equipAttr:$.trim($("#equipAttr").val()),
		tradeMark:$.trim($("#tradeMark").val()),
		brand:$.trim($("#brand").val()),
		depict:$.trim($("#depict").val()),
		keyword:$.trim($("#keyword").val()),
		remark:$.trim($("#remark").val()),
		summary:$.trim($("#summary").val()),
		firstLetter:$.trim($("#firstLetter").val()),
		spell:$.trim($("#spell").val()),
		englishNm:$.trim($("#englishNm").val()),
		foreighNm:$.trim($("#foreighNm").val()),
		groupId:$.trim($("#groupId").val()),
		criteria:$.trim($("#criteria").val()),
		minUnit:minUnit||0,
		minUnitNm:$("#minUnitNm").val()||-1
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else{
			//跳转回物料管理列表
			window.location.href="goodsInit.do";
		}
	}, "json");
}