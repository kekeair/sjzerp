$(document).ready(function() {
	getSupplierList();//供应商列表
	$(document).on("click", "#search", search);
	$(document).on("click", "#addUser", function(){
		addUser(-1);
	});
	$(document).on("click", ".uStruct", function(){//点击模态框中的架构选择框
		var index=$(this).attr("index");
		uStructSel(index);
	});
	$(document).on("click", ".uRole", function(){//点击模态框中的角色选择框
		var index=$(this).attr("index");
		uRoleSel(index);
	});
	$(document).on("click", ".addJobRow", addJobRow);//模态框中的职务+号
	$(document).on("click", ".removeJobRow", function(){//模态框中的职务-号
		var index=$(this).attr("index");
		removeJobRow(index);
	});
	$(document).on("click", "#uSure", uSure);//模态框确定按钮
	$(document).on("click", ".edit", function(){
		var userId=$(this).attr("atNo");
		addUser(userId)
	});
	$(document).on("click", ".del", function(){
		var userId=$(this).attr("atNo");
		delUser(userId);
	});
	$("#province").change(function(){
		provinceChange($(this).val());
	});
	$("#city").change(function(){
		cityChange($(this).val());
	});
});

//供应商列表
function getSupplierList() {
	$.post("getSupplierList.do?", {
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#supplierTmpl").tmpl(data.data).appendTo("#tbody");
			jobMap.clear();
			var l=userList.length;
			for(var i=0;i<l;i++){
				var u=userList[i];
				if(u.jobList.length>1){
					jobMap.put(u.atNo,u.jobList);
				}
			}
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

//职务的点击查看功能
function viewJob(userId){
	var jobList=jobMap.get(userId);
	$("#relbody").empty();
	$("#relTmpl").tmpl(jobList).appendTo("#relbody");
	$("#jobList").modal();
}

function search(){
	g_name=$.trim($("#userNm").val());
	g_structId=$("#structSel").attr("atNo");
	g_roleId=$("#roleSel").attr("atNo");
	curPage=1;
	totalPage=1;
	getUserList();
}

function addUser(userId){
	curUserId=userId
	if(userId==-1){
		//添加用户
		$("#uHead").text("添加用户");
		clearUModal();
	}else{
		$("#uHead").text('编辑用户');
		setValueToUModal();
	}
	$("#uModal").modal();
}

//清空模态对话框
function clearUModal(){
	$("#uNm").val("");
	$("#uAccount").val("");
	$("#uPwd").val("");
	$("#uPhone").val("");
	$("#uSex1").prop("checked",true);
	jobRowIndex=1;
	$("#jobDiv").empty();
	$("#jobDiv").append('<div class="row jobRow" id="jobRow1">'
			+'<label for="uStruct" class="control-label jobLbl">所属架构</label>'
			+'<div class="jobInputDiv">'
			+'<input type="text" readonly="readonly" class="form-control uStruct " '
			+'id="uStruct1" index="1" data-toggle="tooltip" data-placement="bottom"> </div>'
			+'<label for="uRole" class="control-label jobLbl1">角色</label>'
			+'<div class="jobInputDiv">'
			+'<input type="text" readonly="readonly" index="1" '
			+'class="form-control uRole" id="uRole1" data-toggle="tooltip" data-placement="bottom"></div>'
			+'<div class="pull-right">'
			+'<label class="addJobRow glyphicon glyphicon-plus '
			+'control-label" id="addJobRow1" '
			+'index="1"></label>'
			+'<label class="removeJobRow glyphicon glyphicon-minus control-label" ' 
			+'id="removeJobRow1" index="1" style="display:none"></label>'
			+'</div></div>');
	$("#supplier").hide();
	clearSupplier();
}

//模态对话框赋值
function setValueToUModal(){
	$.post("getUserById.do?", {
		userId:curUserId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else{
			var u=data.data;
			$("#uNm").val(u.userNm);
			$("#uAccount").val(u.userAccount);
			$("#uPwd").val(u.pwd);
			$("#uPhone").val(u.phone);
			if(u.sex==1)
				$("#uSex1").prop("checked",true);
			else 
				$("#uSex2").prop("checked",true);
			var relList=u.relList;
			jobRowIndex=relList.length;
			for(var i=0;i<jobRowIndex;i++){
				relList[i].rowIndex=i+1;
			}
			$("#jobDiv").empty();
			$("#jobTmpl").tmpl(relList,{
				getLength:function(){
					return relList.length;
				}
			}).appendTo("#jobDiv");
			if(u.provinceId>0){
				$("#supplier").show();
				$("#province").val(u.provinceId);
				$("#address").val(u.address);
				$("#remark").val(u.remark);
				setCityAndCounty(u.provinceId,u.cityId,u.countyId);
			}else{
				$("#supplier").hide();
				clearSupplier();
			}
		}
	}, "json");
}

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

//模态框中的职务+号
function addJobRow(){
	$("#addJobRow"+jobRowIndex).hide();
	$("#removeJobRow"+jobRowIndex).hide();
	jobRowIndex++;
	$("#jobDiv").append('<div class="row jobRow" id="jobRow'+jobRowIndex+'">'
			+'<label for="uStruct" class="control-label jobLbl">所属架构</label>'
			+'<div class="jobInputDiv">'
			+'<input type="text" index="'+jobRowIndex+'" readonly="readonly" class="form-control uStruct " '
			+'id="uStruct'+jobRowIndex+'" data-toggle="tooltip" data-placement="bottom"> </div>'
			+'<label for="uRole" class="control-label jobLbl1">角色</label>'
			+'<div class="jobInputDiv">'
			+'<input type="text" readonly="readonly" index="'+jobRowIndex+'" '
			+'class="form-control uRole" id="uRole'+jobRowIndex+'" data-toggle="tooltip" data-placement="bottom"></div>'
			+'<div class="pull-right">'
			+'<label class="addJobRow glyphicon glyphicon-plus '
			+'control-label" id="addJobRow'+jobRowIndex+'" '
			+'index="'+jobRowIndex+'"></label>'
			+'<label class="removeJobRow glyphicon glyphicon-minus control-label" ' 
			+'id="removeJobRow'+jobRowIndex+'" index="'+jobRowIndex+'"></label>'
			+'</div></div>');
}

//模态框中的职务-号
function removeJobRow(index){
	$("#jobRow"+index).remove();
	jobRowIndex--;
	$("#addJobRow"+jobRowIndex).show();
	if(jobRowIndex>1)
		$("#removeJobRow"+jobRowIndex).show();
	checkSupplier();
}

function uSure(){
	var name=$.trim($("#uNm").val());
	if(name==''){
		tips('姓名不能为空');
		$("#uNm").focus();
		return;
	}
	var uAccount=$.trim($("#uAccount").val());
	if(uAccount==''){
		tips('账户名不能为空');
		$("#uAccount").focus();
		return;
	}
	var uPwd=$.trim($("#uPwd").val());
	if(uPwd==''){
		tips('密码不能为空');
		$("#uPwd").focus();
		return;
	}
	var jobList=new Array();
	$(".uStruct").each(function(){
		var index=$(this).attr("index");
		var structId=$(this).attr("atNo")||0;
		var roleId=$("#uRole"+index).attr("atNo")||0;
		if(structId>0&&roleId>0){
			var job={structId:structId,roleId:roleId};
			jobList.push(job);
		}
	});
	if(jobList.length==0){
		tips('请至少选择一个职务');
		return;
	}
	var provinceId=-1,cityId=-1,countyId=-1,address='';
	if(!$("#supplier").is(":hidden")){
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
		address=$.trim($("#address").val());
		if(address==''){
			tips('请填写供应商地址');
			$("#address").focus();
			return;
		}
	}
	
	$.post("editUser.do?", {
		userId:curUserId,
		userNm:name,
		userAccount:uAccount,
		pwd:uPwd,
		phone:$.trim($("#uPhone").val()),
		sex:$('input[name="uSex"]:checked').val(),
		job:JSON.stringify(jobList),
		provinceId:provinceId,
		cityId:cityId,
		countyId:countyId,
		address:address,
		remark:$.trim($("#remark").val())
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
			if(data.code==206)
				$("#uAccount").focus();
		} else{
			$("#uModal").modal('hide');
			getUserList();
		}
	}, "json");
}

function delUser(userId){
	confirm("确定要删除该用户吗？",function(){
		$.post("delUser.do?", {
			userId:userId
		}, function(data) {
			tips(data.msg);
			if (data.code==1000) {
				search();
			}
		}, "json");
	});
}

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

//是否是供应商
function checkSupplier(){
	var supplierCount=0;
	$(".uRole").each(function(){
		var roleId=$(this).attr("atNo")||0;
		if(roleId==8){//供应商
			supplierCount++;
		}
	});
	if(supplierCount>0)
		$("#supplier").show();
	else
		$("#supplier").hide();
}
//清空供应商属性form
function clearSupplier(){
	$("#province").val(-1);
	$("#city").empty();
	$("#city").append('<option value="-1">请选择...</option>');
	$("#county").empty();
	$("#county").append('<option value="-1">请选择...</option>');
	$("#address").val("");
	$("#remark").val("");
}
