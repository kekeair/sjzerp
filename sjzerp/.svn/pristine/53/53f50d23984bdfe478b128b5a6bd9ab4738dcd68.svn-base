
var curPage=1;//当前页
var totalPage=1;//总页数
var jobMap=new Map();//把当前页的用户职务用map存储起来。key：userId，value:用户职务列表（jobList）
var g_structId=0;//架构id
var g_name="";//作业组名称
var g_roleId=0;//角色id
var jobRowIndex=1;//当前职务总数（modal中）
var curUserId=-1;//当前选中的userid
var customerList;//当前页的用户列表
var tagListforCustomer;
var tagId;
var myCustomAtNo;
$(document).ready(function() {
	if(roleId!=10){
		$("#addCustomer").show();
	}else{
		$("#addCustomer").hide();
	}
	getCustomerList();//用户列表
	$.post("getProvince.do?", {},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#province").empty();
			$("#provinceTmpl").tmpl(data.data).appendTo("#province");
		}
	},"json");
	//请求业务员列表
	$.post("../customer/getCountermanList.do?", {},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#counterman").empty();
			$("#countermanTmpl").tmpl(data.data).appendTo("#counterman");
		}
	},"json");
	bindPage(getCustomerList);
	$(document).on("click", "#search", search);
	$(document).on("click", "#tagSure", tagSure);
	$(document).on("click", "#addCustomer", function(){
		addCustomer(-1);
	});
	
	$(document).on("click", "#cSure", cSure);//模态框确定按钮
	//enter键搜索
	$(document).on("keydown", "#customerNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	$(document).on("click", ".edit", function(){
		
		var customerId=$(this).attr("atNo");
		addCustomer(customerId)
	});
	
	//作业组管理
	$(document).on("click", ".customteam", function(){
		var customerId=$(this).attr("atNo");
		customteamInit(customerId)
	});
	
	//标签管理
	$(document).on("click", ".customtag", function(){
	    tagId=$(this).attr("tagId");
	    myCustomAtNo=$(this).attr("atNo");
		tagAll();
	});
	
	$(document).on("click", ".del", function(){
		var customerId=$(this).attr("atNo");
		delCustomer(customerId);
	});
	
	$("#province").change(function(){
		provinceChange($(this).val());
	});
	$("#city").change(function(){
		cityChange($(this).val());
	});
});


//标签模态窗口
function tagAll(){
	tagAllList();
	$("#tagModal").modal();
}

//获得标签的集合
function tagAllList(){
	$.post("getTagList.do?",{tagId:tagId},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tag").empty();
			tagListforCustomer=data.data.tagListforCustomer;
			$("#customtagTmpl").tmpl(data.data).appendTo("#tag");
		}
	},"json")
}

//提交修改标签
function tagSure(){
	var tagArray = $("input[name='tagInput']:checked").val();
	
	$.post("editCustomTag.do?",{tagArray:tagArray,myCustomAtNo:myCustomAtNo},function(data){
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tips(data.msg);
			getCustomerList();
			$("#tagModal").modal('hide');
		}
	},"json")
	
}

//用户列表
function getCustomerList() {
	$.post("getCustomerList.do?", {
		customerNm:g_name,
		structId:g_structId,
		page:curPage
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			data.data.roleId=roleId;
			customerList=data.data.customerList;
			$("#customerTmpl").tmpl(data.data).appendTo("#tbody");
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


function search(){
	g_name=$.trim($("#customerNm").val());
	curPage=1;
	totalPage=1;
	getCustomerList();
}

function addCustomer(customerId){

	curUserId=customerId
	if(customerId==-1){
		//添加客户
		$("#cHead").text("添加客户");
		clearUModal();
	}else{
		$("#cHead").text('编辑客户');
		setValueToUModal();
	}
	
	$("#cModal").modal();
}

//清空模态对话框
function clearUModal(){
	$("#customNm").val("");
	$("#phone").val("");
	$("#address").val("");

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
	$.post("getCustomerById.do?", {
		customerId:curUserId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else{
			var u=data.data;
			$("#customNm").val(u.customNm);
			$("#counterman").val(u.countermanId);
			$("#phone").val(u.phone);
			$("#address").val(u.address);
			
			if(u.provinceId>0){
				$("#province").val(u.provinceId);
				setCityAndCounty(u.provinceId,u.cityId,u.countyId);
			}else{
				clearSupplier();
			}
		}
	}, "json");
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

function cSure(){
	var customNm=$.trim($("#customNm").val());
	
	var countermanId=$.trim($("#counterman").val());
	if(customNm==''){
		tips('姓名不能为空');
		$("#customNm").focus();
		return;
	}
	if(countermanId==-1){
		tips('请选择相应的业务员!');
		$("#counterman").focus();
		return;
	}
	var phone=$.trim($("#phone").val());
	if(phone==''){
		tips('电话不能为空');
		$("#phone").focus();
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
			tips('请填写地址');
			$("#address").focus();
			return;
		}
	}
	
	$.post("addCustomer.do?", {
		customerId:curUserId,
		customNm:customNm,
		phone:phone,
		provinceId:provinceId,
		cityId:cityId,
		countyId:countyId,
		address:address,
		countermanId:countermanId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		     if(data.code==206)
				$("#customNm").focus();
		} else{
			tips(data.msg);
			$("#cModal").modal('hide');
			getCustomerList();
		}
	}, "json");
}

function delCustomer(customerId){
	confirm("确定要删除该客户吗？",function(){
		$.post("delCustomer.do?", {
			customerId:customerId
		}, function(data) {
			tips(data.msg);
			if (data.code==1000) {
				getCustomerList();
			}
		}, "json");
	});
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

function isCenterChange(){
	showCenterAttr($("#isCenter").get(0).checked)
}


//工作组页面
function customteamInit(customerId){
	window.location.href="customteamInit.do?customerId="+customerId+"";
}



