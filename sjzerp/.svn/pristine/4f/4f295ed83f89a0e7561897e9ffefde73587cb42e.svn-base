var code=sessionStorage.purchaseTotallListCode;
var list;
var operType;//0通过1驳回

var curPage=1;//当前页
var totalPage=1;//总页数

$(document).ready(function() {
	purchaseTotallList();//采购汇总列表
	var userNm=$(this).attr("supplier");
	$("#supplier").bind('change',function() {
		$.post("PurchaseTotalList.do?", {
			centerId:2,
			code:code,
			userNm:$("#supplier").val()//绑定id??
		}, function(data) {
			if (data.code!=1000) {
				tips(data.msg);
			} else {
				list=data.data;
				console.log("---"+list);
				$("#tbody").empty();
				$("#detailTmpl").tmpl(data.data).appendTo("#tbody");
			}
		}, "json");
	});
	//打印绑定事件
	var print = $("#print");
	var printBody = $("#printExcell");//参数为table
	$(print).click(function(){
			printE(printBody);//调用打印方法
	});
	$(document).on("click", "#export", function(){
		location.href="exportPurchaseTotallList.do?centerId=2"+"&code="+code+
			"&supplierId="+$("#supplier").val();
	});
	$(document).on("click", "#exportIn", function(){
		location.href="exportPurchaseInStockList.do?centerId=2"+"&code="+code+
			"&supplierId="+$("#supplier").val();
	});
});


//声明一个控制打印的函数，参数是一个对象，这个对象中的内容将要被打印
function printE(printBody){
	//获取要打印的内容
	var newstr = $(printBody).html(); 
	//原来body中的内容
	var oldstr = document.body.innerHTML;
	//用将要打印的内容替换原来body中的内容
	document.body.innerHTML =newstr; 
	//开始打印
	window.print(); 
	//再将原来body中的内容还原
	document.body.innerHTML=oldstr; 
	return false; 
}

//采购列表
function purchaseTotallList() {
	$.post("PurchaseTotalList.do?", {
		centerId:2,
		code:code,
		userNm:$("#supplier").val(),//绑定id??
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			list=data.data;
			$("#tbody").empty();
			$("#detailTmpl").tmpl(data.data).appendTo("#tbody");
			$("#detailTmpl").tmpl(data.data).appendTo("#tbody");
		}
	}, "json");
}

