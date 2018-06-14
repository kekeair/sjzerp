var billId=sessionStorage.demandBillId||-1;

$(document).ready(function() {
	getCustomerList();//需求上报列表
	
/*	$(document).on("click", ".customerDemand", function(){
		var customerId=$(this).attr('customerId');
		sessionStorage.customerId=customerId;
		sessionStorage.teamDemandId=-1;
		window.location.href="addDemandInit.do?customerId="+customerId
			+"&teamId=-1&type=add";
	});*/
	//申报
	$(document).on("click", ".customerDemandNew", function(){
		var customerId=$(this).attr('customerId');
		var customNm=$(this).attr('customNm');
		sessionStorage.customerId=customerId;
		sessionStorage.customNm=customNm;
		sessionStorage.teamDemandId=-1;
		window.location.href="addDemandNewInit.do?customerId="+customerId
			+"&teamId=-1&type=add";
	});
	
	$(document).on("click", ".edit", function(){
		var customerId=$(this).attr('customerId');
		var teamDemandId=$(this).attr('teamDemandId');
		sessionStorage.customerId=customerId;
		sessionStorage.teamDemandId=teamDemandId;
		window.location.href="demandByGroupInit.do";
	});
	
	//提交
	$(document).on("click", "#submit", function(){
		if(billId==-1){
			tips("还没有单位进行申报，不能提交");
			return;
		}
		$("#remark").val("");
		if(roleId==9){
			$("#varyTime").show();
			$("#submitTime").val(getDateStr(0));
		}else{
			$("#varyTime").hide();
		}
		$("#submitModal").modal();
	});
	
	$(document).on("click", "#uSure", function(){
		dealBill();
	});
});

//需求上报列表
function getCustomerList() {
	$.post("getCustomerList.do?", {
		billId:billId
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#demandTmpl").tmpl(data.data).appendTo("#tbody");
		}
	}, "json");
}

function delDemandBill(billId){
	confirm("确定要删除该订单吗？",function(){
		$.post("delDemandBill.do?", {
			billId:billId
		}, function(data) {
			tips(data.msg);
			if(data.code==1000){
				setTimeout(function(){
					location.reload();
				},1000);
			}
		}, "json");
	});
}


//处理提交
function dealBill(){
	load();
	$.post("dealDemandBill.do?", {
		billId:billId,
		reviewState:1,
		operType:0,
		billDate:$("#submitTime").val(),
		remark:$.trim($("#remark").val())
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			setTimeout(function(){
				window.location.href="demandInit.do";
			},1000);
		}
	}, "json");	
}