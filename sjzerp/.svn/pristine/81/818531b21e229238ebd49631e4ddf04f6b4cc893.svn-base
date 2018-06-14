
var billId=sessionStorage.demandBillId;//账单ID
var customerTeamId = sessionStorage.customerTeamId;//客户下的工作组的ID
$(document).ready(function() {
	//获取列表
	getDemandList();
	//打印
	$(document).on("click", "#printOut",printOut);
	//监控input标签
	$(document).on("input propertychange", ".goodsNmTd",function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var price = $(this).attr("price");
		var numVal =$(this).val();
		$(this).parent("td").next().next().text(formatDouble(price*numVal));
	});
})

//打印
function printOut(){
	var numList = new Array();
	var checkMsg=checkBeforeSave();
	if(checkMsg.length>0){
		tips(checkMsg);
		return;
	}
	
	$('table input').each(function(){
		var val=$.trim($(this).val());
		numList.push(val);
	});

	window.open("../demand/printOutDemandBill.do?customerTeamId="+customerTeamId+"&billId="+billId+"&numList="+numList+"");
}

//获取列表
function getDemandList(){
	$.post("../demand/getDemandListByCustomerId.do",{
		billId:billId,
		customerTeamId:customerTeamId
			},function(data){
				if (data.code != 1000) {
					tips(data.msg);
				} else {
					$("#tbody").empty();
					$("#customerTmpl").tmpl(data.data).appendTo("#tbody");
				}
			},"json");
	
};


//检查输入的数量是否正确
function checkBeforeSave(){
	var checkMsg='';
	$("table input").each(function(){
		var val=$.trim($(this).val());
		if(val==''){
			$(this).focus();
			checkMsg='数量不能为空';
			return false;
		}
		if(isNaN(val)){
			$(this).focus();
			checkMsg='数量只能填写数字';
			return false;
		}
		if(val<0){
			$(this).focus();
			checkMsg='数量必须大于等于0';
			return false;
		}
	});
	return checkMsg;
}