var curPage=1;//当前页
var totalPage=1;//总页数
var jobMap=new Map();//把当前页的用户职务用map存储起来。key：userId，value:用户职务列表（jobList）
var g_structId=0;//架构id
var g_name="";//作业组名称
var g_roleId=0;//角色id
var jobRowIndex=1;//当前职务总数（modal中）
var curTagId=-1;//当前选中的userid
var customerList;//当前页的用户列表

$(document).ready(function() {
	if(roleId==10){
		$("#addTag").show();
	}else{
		$("#addTag").hide();
	}
	getTagList();//用户列表
	bindPage(getTagList);
	$(document).on("click", "#addTag", function(){
		addTag(-1);
	});
	
	//enter名称键搜索
	$(document).on("keydown", "#tagNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			tSure();
	});
	
	$(document).on("click", "#tSure", tSure);//模态框确定按钮
	
	$(document).on("click", ".edit", function(){
		var tagId=$(this).attr("atNo");
		addTag(tagId)
	});
	
	//作业组管理
	$(document).on("click", ".tagGoods", function(){
		var tagId=$(this).attr("atNo");
		var tagNm=$(this).attr("tagNm");
		sessionStorage.tagNm=tagNm;
		tagGoodsInit(tagId)
	});
	
	$(document).on("click", ".viewTagGoods", function(){
		var tagId=$(this).attr("atNo");
		sessionStorage.myTagId=tagId;
		window.location.href="viewTagGoodsInit.do";
	});
	
	$(document).on("click", ".del", function(){
		var tagId=$(this).attr("atNo");
		delTag(tagId);
	});
	
	$("#province").change(function(){
		provinceChange($(this).val());
	});
	$("#city").change(function(){
		cityChange($(this).val());
	});
});

//用户列表
function getTagList() {
	$.post("getTagList.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			tagList=data.data.tagList;
			data.data.roleId=roleId;
			$("#tagTmpl").tmpl(data.data).appendTo("#tbody");
		}
	}, "json");
}


function addTag(tagId){
	curTagId=tagId
	if(tagId==-1){
		//添加客户
		$("#cHead").text("添加标签");
		clearUModal();
	}else{
		$("#cHead").text('编辑标签');
		setValueToUModal();
	}
	$("#tModal").modal();
	$("#tagNm").focus();
}

//清空模态对话框
function clearUModal(){
	$("#tagNm").val("");
}



//模态对话框赋值
function setValueToUModal(){
	$.post("getTagById.do?", {
		tagId:curTagId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else{
			var u=data.data;
			$("#tagNm").val(u.tagNm);
		}
	}, "json");
}

function tSure(){
	var tagNm=$.trim($("#tagNm").val());
	if(tagNm==''){
		tips('名称不能为空');
		$("#tagNm").focus();
		return;
	}
	$.post("addTag.do?", {
		tagId:curTagId,
		tagNm:tagNm,
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		     if(data.code==206)
				$("#tagNm").focus();
		} else{
			tips(data.msg);
			$("#tModal").modal('hide');
			getTagList();
		}
	}, "json");
}

function delTag(tagId){
	confirm("确定要删除该标签吗？",function(){
		$.post("delTag.do?", {
			tagId:tagId
		}, function(data) {
			tips(data.msg);
			if (data.code==1000) {
				getTagList();
			}
		}, "json");
	});
}


//物料管理页面
function tagGoodsInit(tagId){
	sessionStorage.myTagId=tagId;
	window.location.href="tagGoodsInit.do";
}