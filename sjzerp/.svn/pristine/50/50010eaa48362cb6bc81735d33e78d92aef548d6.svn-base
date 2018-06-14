var curPage=1;//当前页
var totalPage=1;//总页数
var unitList;//存储当前页的列表数据
$(document).ready(function() {
	getUnitList();
	bindPage(getUnitList);
	$(document).on("click", "#addUnit", function(){
		addUnit(-1);
	});
	$(document).on("click", "#uSure", uSure);
	$(document).on("click", ".edit", function(){
		var unitId=$(this).attr("atNo");
		var unitNm=$(this).attr("name");
		addUnit(unitId,unitNm);
	});
});

function getUnitList() {
	$.post("getUnitList.do?", {
		page:curPage
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			unitList=data.data.unitList;
			$("#unitTmpl").tmpl(unitList).appendTo("#tbody");
			totalPage=data.data.totalPage;
			if(curPage==1)
				$("#prePage").attr("disabled","disabled");
			else
				$("#prePage").removeAttr("disabled");
			if(curPage==totalPage)
				$("#nextPage").attr("disabled","disabled");
			else
				$("#nextPage").removeAttr("disabled");
			$("#curPage").text(curPage);
			$("#totalPage").text(totalPage);
		}
	}, "json");
}

function addUnit(unitId,unitNm){
	if(unitId==-1){
		//添加单位
		$("#uHead").text("添加");
		clearUModal();
	}else{
		$("#uHead").text('编辑');
		setValueToUModal(unitId,unitNm);
	}
	$("#uModal").modal();
}

function clearUModal(){
	$("#uNm").val("");
	$("#uNm").attr("unitId",-1);
}

function setValueToUModal(unitId,unitNm){
	$("#uNm").val(unitNm);
	$("#uNm").attr("unitId",unitId);
}

function uSure(){
	var name=$.trim($("#uNm").val());
	if(name==''){
		tips('名称不能为空');
		$("#uNm").focus();
		return;
	}
	var unitId=$("#uNm").attr("unitId");
	$.post("editUnit.do?", {
		atNo:unitId,
		name:name
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
			if(data.code==207)
				$("#uNm").focus();
		} else{
			$("#uModal").modal('hide');
			getUnitList();
		}
	}, "json");
}

