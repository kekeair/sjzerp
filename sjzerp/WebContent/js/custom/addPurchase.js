var maxOrder = 0;// 添加行时候的下标
var goodsList;//物料列表
var newList=new Array();//新物料列表
var customerId=-2;
var customerNm="配送中心";
var teamId=-2;
var teamNm="配送中心";

$(document).ready(function() {
	getAllCenterGoods();
	getGoodsUnit();
	$(document).on('input propertychange','.newNum',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var order=$(this).attr("order");
		var num=$(this).val()||0;
		var price=$("#newPrice"+order).val()||0;
		$("#newMoney"+order).text(formatDouble(price*num));
	});
	$(document).on('input propertychange','.newPrice',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var order=$(this).attr("order");
		var price=$(this).val()||0;
		var num=$("#newNum"+order).val()||0;
		$("#newMoney"+order).text(formatDouble(price*num));
	});
	$(document).on("click", ".newDel", function(){
		var order=$(this).attr("order");
		delNew(order);
	});
	$(document).on("click", "#addGoods", addRow);
	$(document).on("click", "#addTmp", addTmp);
	$(document).on("click", "#tmpSure", addTmpSure);
	$(document).on("click", "#save", save);
	$(document).on("click", "#deal", function(){
		$("#remark").val("");
		operType=0;
		$("#varyTime").show();
		$("#submitTime").val(getDateStr(0));
		$("#dealModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		dealBill();
	});
});

//获取物料
function getAllCenterGoods(){
	$.post("../common/getAllCenterGoods.do",{
		billType:'purchase'
	},function(data) {
		if (data.code == 1000) {
			goodsList = data.data;
			pushModalGoodsList();
			$("#goodsbody").empty();
			$("#goodsTmpl").tmpl(modalGoodsList).appendTo("#goodsbody");
		}
	}, "json");
}

function getGoodsUnit(){
	$.post("../common/getGoodsUnit.do?", {
	}, function(data) {
		if(data.code==1000){
			$("#tmpUnit").empty();
			$("#unitTmpl").tmpl(data).appendTo("#tmpUnit");
		}
	}, "json");
}

//绑定tab键
function bindTab(obj){
	obj.bind("keydown", function(event) {
		if (event.which == 9) {
			addRow();
		}
	});
}

//绑定自动提示
function bindAutoComplete(obj){
	obj.bigAutocomplete({
		width:160,
		data:goodsList,
		callback:function(data){
			setValue(data,obj);
		}
	});
}

function setValue(data,obj){
	//检查物料是否存在
	var checkMsg=checkGoodsRepeat(data.goodsId,obj);
	if(checkMsg!=''){
		obj.val("");
		tips(checkMsg);
		return;
	}
	var order=obj.attr("order");
	$("#newGoodsCode"+order).text(data.goodsCode);
	$("#newGoodsNm"+order).attr("goodsId",data.goodsId);
	$("#newGoodsNm"+order).val(data.goodsNm);
	$("#newSpec"+order).text(data.spec);
	$("#newUnitNm"+order).text(data.unitNm);
	$("#newSupplier"+order).text(data.supplierNm);
	$("#newSupplier"+order).attr("supplierId",data.supplierId);
	$("#newPrice"+order).val(data.price);
	var num=$.trim($("#newNum"+order).val());
	$("#newMoney"+order).text(formatDouble(data.price*num));
	$("#newNum"+order).focus();
}

function checkGoodsRepeat(goodsId,obj){
	var checkMsg='';
	var order=obj.attr("order");
	$(".newGoodsNm").each(function(){
		if(order!=$(this).attr("order")){
			var thisGoodsId=$(this).attr("goodsId");
			var goodsType=$(this).attr("goodsType");
			if(thisGoodsId==goodsId&&goodsType==0){
				checkMsg='列表中已经有相同的物料，请勿重复添加';
				return false;
			}
		}
	});		
	return checkMsg;
}

//添加行
function addRow() {
	maxOrder++;
	var str = '<tr id="newTr'+maxOrder+'" class="newTr" order="'+maxOrder+'">'+
		'<td id="newGoodsCode'+maxOrder+'"></td>'+
		'<td><div class="input-group">'+
		'<input type="text" rowType="new" goodsType="0" goodsId="-1" order="'+maxOrder+'" id="newGoodsNm'+maxOrder+'" class="form-control newGoodsNm">'+
  		'<span class="input-group-btn">'+
    	'<button order="'+maxOrder+'" class="goodsBtn btn btn-default" rowType="new" type="button"><span class="glyphicon glyphicon-search"></span></button>'+
  		'</span>'+
		'</div></td>'+
		'<td id="newSpec'+maxOrder+'"></td>'+
		'<td id="newUnitNm'+maxOrder+'"></td>'+
		'<td>'+customerNm+'</td>'+
		'<td>'+teamNm+'</td>'+
		'<td id="newSupplier'+maxOrder+'" supplierId="-1"></td>'+
		'<td><input type="text" class="form-control newNum" order="'+maxOrder+'" id="newNum'+maxOrder+'"></td>'+
		'<td><input type="text" order="'+maxOrder+'" class="form-control newPrice" id="newPrice'+maxOrder+'"></td>'+
		'<td id="newMoney'+maxOrder+'"></td>'+
		'<td><input id="newRemark'+maxOrder+'" class="form-control" order="'+maxOrder+'" type="text"></td>'+
		'<td><a class="newDel" id="newDel'+maxOrder+'" order="'+maxOrder+'">删除</a></td>'+
		'</tr>';
	$("#tbody").append(str);
	bindAutoComplete($("#newGoodsNm"+maxOrder));
	// tab键加行
	bindTab($("#newRemark" + maxOrder));
	if(maxOrder>1){
		$("#newRemark" + (maxOrder - 1)).unbind();
	}
}

function addTmp(){
	clearTmpModel();
	$("#tmpModal").modal();
}

function addTmpSure(){
	var goodsNm=$.trim($("#tmpGoodsNm").val());
	if(goodsNm==''){
		tips('物料名不能为空');
		$("#tmpGoodsNm").focus();
		return;
	}
	var unitId=$("#tmpUnit").val();
	if(unitId==-1){
		tips("请选择单位");
		$("#tmpUnit").focus();
		return;
	}
	var num=$.trim($("#tmpNum").val());
	if(num==''){
		tips('请填写数量');
		$("#tmpNum").focus();
		return;
	}
	if(isNaN(num)){
		tips('请填写正确的数量');
		$("#tmpNum").focus();
		return;
	}
	var price=$.trim($("#tmpPrice").val())||0;
	if(isNaN(price)){
		tips('请填写正确的单价');
		$("#tmpPrice").focus();
		return;
	}
	var money=formatDouble(price*num);
	var remark=$.trim($("#tmpRemark").val());
	maxOrder++;
	var str = '<tr id="newTr'+maxOrder+'" class="newTr" order="'+maxOrder+'">'+
		'<td id="newGoodsCode'+maxOrder+'"></td>'+
		
		'<td><div class="input-group">'+
		'<input type="text" rowType="new" goodsType="1" disabled="disabled" goodsId="-1" order="'+maxOrder+'" id="newGoodsNm'+maxOrder+'" class="form-control newGoodsNm" value="'+goodsNm+'">'+
  		'<span class="input-group-btn">'+
    	'<button order="'+maxOrder+'" rowType="new" class="goodsBtn btn btn-default" disabled="disabled" type="button"><span class="glyphicon glyphicon-search"></span></button>'+
  		'</span>'+
		'</div></td>'+
		'<td id="newSpec'+maxOrder+'">'+$.trim($("#tmpSpec").val())+'</td>'+
		'<td id="newUnitNm'+maxOrder+'" newUnitId="'+unitId+'">'+$("#tmpUnit").find("option:selected").text()+'</td>'+
		'<td>'+customerNm+'</td>'+
		'<td>'+teamNm+'</td>'+
		'<td id="newSupplier'+maxOrder+'" supplierId="-1">自采</td>'+
		'<td><input type="text" value="'+num+'" class="form-control newNum" order="'+maxOrder+'" id="newNum'+maxOrder+'"></td>'+
		'<td><input type="text" value="'+price+'" order="'+maxOrder+'" class="form-control newPrice" id="newPrice'+maxOrder+'"></td>'+
		'<td id="newMoney'+maxOrder+'">'+money+'</td>'+
		'<td><input id="newRemark'+maxOrder+'" value="'+remark+'" class="form-control" order="'+maxOrder+'" type="text"></td>'+
		'<td style="display:none" id="newTempGoodsType'+maxOrder+'">'+$("#tempGoodsType").val()+'</td>'+
		'<td><a class="newDel" id="newDel'+maxOrder+'" order="'+maxOrder+'">删除</a></td>'+
		'</tr>';
	$("#tbody").append(str);
	bindAutoComplete($("#newGoodsNm"+maxOrder));
	// tab键加行
	bindTab($("#newRemark" + maxOrder));
	if(maxOrder>1){
		$("#newRemark" + (maxOrder - 1)).unbind();
	}
	$("#tmpModal").modal('hide');
}

function clearTmpModel(){
	$("#tmpGoodsNm").val("");
	$("#tmpSpec").val("");
	$("#tmpNum").val("");
	$("#tmpPrice").val("");
	$("#tempGoodsType").val(1);
	$("#tmpRemark").val("");
}

function delNew(removeOrder){
	maxOrder--;
	$("#newTr"+removeOrder).remove();
	$(".newTr").each(function(){
		var obj=$(this);
		var thisOrder=obj.attr("order");
		var preOrder=obj.attr("order");
		if(parseInt(thisOrder)>parseInt(removeOrder)){
			thisOrder--;
			obj.attr("order",thisOrder);
			obj.attr("id",'newTr'+thisOrder);
			$("#newGoodsCode"+preOrder).attr("id","newGoodsCode"+thisOrder);
			$("#newGoodsNm"+preOrder).attr("order",thisOrder);
			$("#newGoodsNm"+preOrder).attr("id","newGoodsNm"+thisOrder);
			$("#newSpec"+preOrder).attr("id","newSpec"+thisOrder);
			$("#newUnitNm"+preOrder).attr("id","newUnitNm"+thisOrder);
			$("#newSupplier"+preOrder).attr("id","newSupplier"+thisOrder);
			$("#newNum"+preOrder).attr("order",thisOrder);
			$("#newNum"+preOrder).attr("id","newNum"+thisOrder);
			$("#newPrice"+preOrder).attr("id","newPrice"+thisOrder);
			$("#newMoney"+preOrder).attr("id","newMoney"+thisOrder);
			$("#newRemark"+preOrder).attr("order",thisOrder);
			$("#newRemark"+preOrder).attr("id","newRemark"+thisOrder);
			$("#newDel"+preOrder).attr("order",thisOrder);
			$("#newDel"+preOrder).attr("id","newDel"+thisOrder);
		}
	});
	if(maxOrder>0){
		if(maxOrder==removeOrder-1){
			bindTab($("#newRemark" + maxOrder));
		}
	}else{
		bindTab(lastEditObj);
	}
}

function save(){
	var checkMsg=checkBeforeSave();
	if(checkMsg.length>0){
		tips(checkMsg);
		return;
	}
	generateList();
	if(newList.length==0){
		tips("请至少保存一种物料");
		return;
	}
	load();
	$.post("savePurchaseD.do?", {
		newList:JSON.stringify(newList),
		billId:-1,
		customerId:customerId,
		teamId:teamId
	}, function(data) {
		closeAll();
		tips(data.msg);
		setTimeout(function(){
			location.href="purchaseInit.do";
		},1000);
	}, "json");	
}

function generateList(){
	//新物料列表
	$(".newTr").each(function(){
		var order=$(this).attr("order");
		var num=$.trim($("#newNum"+order).val());
		var goodsType=$("#newGoodsNm"+order).attr("goodsType");
		var goodsNm=$("#newGoodsNm"+order).val();
		var goodsId=-1,spec='',unitNm='',tempGoodsType=1,supplierId=-1;
		if(goodsType==0){
			goodsId=$("#newGoodsNm"+order).attr("goodsId");
		}else{
			spec=$("#newSpec"+order).text();
			unitNm=$("#newUnitNm"+order).text();
			tempGoodsType=$("#newTempGoodsType"+order).text();
			supplierId=$("#newSupplier"+order).attr("supplierId");
		}
		if(goodsType==0&&goodsId==-1){
			return true;
		}
		var remark=$("#newRemark"+order).val();
		var price=$("#newPrice"+order).val()||0;
		var obj={atNo:-1,num:num,goodsId:goodsId,goodsType:goodsType,
				remark:remark,spec:spec,unitNm:unitNm,goodsNm:goodsNm,
				tempGoodsType:tempGoodsType,price:price,supplierId:supplierId};
		newList.push(obj);
	});
}


function checkBeforeSave(){
	var checkMsg='';
	$(".newNum").each(function(){
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
	$(".newPrice").each(function(){
		var val=$.trim($(this).val());
		if(isNaN(val)){
			$(this).focus();
			checkMsg='价格只能填写数字';
			return false;
		}
	});
	return checkMsg;
}

function dealBill(){
	var billDate = $("#submitTime").val();
	var checkMsg=checkBeforeSave();
	if(checkMsg.length>0){
		tips(checkMsg);
		return;
	}
	generateList();
	if(newList&&newList.length==0){
		tips("请至少提交一种物料");
		return;
	}
	load();
	$.post("dealPurchaseBill.do?", {
		billId:-1,
		reviewState:1,
		operType:0,
		billDate:billDate,
		remark:$.trim($("#remark").val()),
		newList:JSON.stringify(newList),
		customerId:customerId,
		teamId:teamId
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code!=209){
			setTimeout(function(){
				window.location.href="purchaseInit.do";
			},1000);
		}
	}, "json");	
}