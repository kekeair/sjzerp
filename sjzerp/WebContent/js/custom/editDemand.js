var billId=sessionStorage.demandBillId;
var billState;
var maxOrder = 0;// 添加行时候的下标
var lastEditObj;//预加载列表中最后一个input
var goodsList;//物料列表
var editList=new Array();//编辑列表
var newList=new Array();//新物料列表
var delAtNo="";//删除id字符串
var customerId;
var customerNm;
var teamId;
var teamNm;
var headAtNo;
var operType;


$(document).ready(function() {
	getAllCenterGoods();
	getGoodsUnit();
	$(document).on('input propertychange','.num',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var atNo=$(this).attr("atNo");
		var num=$(this).val()||0;
		var price=$("#price"+atNo).val()||0;
		$("#money"+atNo).text(formatDouble(price*num))
	});
	$(document).on('input propertychange','.newNum',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var order=$(this).attr("order");
		var num=$(this).val()||0;
		var price=$("#newPrice"+order).val()||0;
		$("#newMoney"+order).text(formatDouble(price*num));
	});
	$(document).on('input propertychange','.price',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var atNo=$(this).attr("atNo");
		var price=$(this).val()||0;
		var num=$("#num"+atNo).val()||0;
		$("#money"+atNo).text(formatDouble(price*num))
	});
	$(document).on('input propertychange','.newPrice',function(){
		$(this).val(v.moneyvalidate($(this).val(),9));
		var order=$(this).attr("order");
		var price=$(this).val()||0;
		var num=$("#newNum"+order).val()||0;
		$("#newMoney"+order).text(formatDouble(price*num));
	});
	$(document).on("click", ".oldDel", function(){
		var atNo=$(this).attr("atNo");
		delOld(atNo);
	});
	$(document).on("click", ".newDel", function(){
		var order=$(this).attr("order");
		delNew(order);
	});
	$(document).on("click", "#addTmp", addTmp);
	$(document).on("click", "#addGoods", addRow);
	$(document).on("click", "#tmpSure", addTmpSure);
	$(document).on("click", "#save", save);
	$(document).on("click", "#deal", function(){
		operType=0;
		$("#remark").val("");
		if(roleId==9 && billState==0){
			$("#varyTime").show();
			$("#submitTime").val(getDateStr(0));
		}else{
			$("#varyTime").hide();
		}
		$("#submitModal").modal();
	});
	$(document).on("click", "#refuse", function(){
		operType=1;
		$("#remark").val("");
		$("#varyTime").hide();
		$("#submitModal").modal();
	});
	$(document).on("click", "#uSure", function(){
		if(operType==0){
			dealBill();
		}else{
			refuseBill();
		}
	});
});

//获取物料
function getAllCenterGoods(){
	$.post("../common/getAllCenterGoods.do",{
		billId:billId,
		billType:'demand'
	},function(data) {
		if (data.code == 1000) {
			goodsList = data.data;
			pushModalGoodsList();
			$("#goodsbody").empty();
			$("#goodsTmpl").tmpl(modalGoodsList).appendTo("#goodsbody");
			getDemandD();
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

function getDemandD() {
	$.post("getDemandD.do?",{
		billId:billId
	},function(data) {
			if (data.code != 1000) {
				tips(data.msg);
			} else {
				billState=data.data.billState;
				$("#tbody").empty();
				$("#demandTmpl").tmpl(data.data.list).appendTo("#tbody");
				if(data.data.list!=null&&data.data.list.length>0){
					var d=data.data.list[0];
					customerId=d.customerId;
					customerNm=d.customerNm;
					teamId=d.teamId;
					teamNm=d.teamNm;
					headAtNo=d.headAtNo;
				}
				var l=$(".goodsNm").length;
				var index=0;
				$(".goodsNm").each(function(){
					var thisObj=$(this);
					bindAutoComplete(thisObj);
					if(index==l-1){
						var demandDId=thisObj.attr("atNo");
						lastEditObj=$("#remark"+demandDId);
						bindTab(lastEditObj);
					}
					index++;
				});	
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
			setValue(data,obj)
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
	var rowType=obj.attr("rowType");
	if(rowType=="old"){
		//原有物料
		var demandDId=obj.attr("atNo");
		$("#goodsCode"+demandDId).text(data.goodsCode);
		$("#goodsNm"+demandDId).attr("goodsId",data.goodsId);
		$("#goodsNm"+demandDId).val(data.goodsNm);
		$("#goodsNm"+demandDId).attr("goodsType",0);
		$("#spec"+demandDId).text(data.spec);
		$("#unitNm"+demandDId).text(data.unitNm);
		$("#price"+demandDId).val(data.price);
		var num=$.trim($("#num"+demandDId).val());
		$("#money"+demandDId).text(formatDouble(data.price*num));
		$("#num"+demandDId).focus();
	}else{
		//新物料
		var order=obj.attr("order");
		$("#newGoodsCode"+order).text(data.goodsCode);
		$("#newGoodsNm"+order).attr("goodsId",data.goodsId);
		$("#newGoodsNm"+order).val(data.goodsNm);
		$("#newSpec"+order).text(data.spec);
		$("#newUnitNm"+order).text(data.unitNm);
		$("#newPrice"+order).val(data.price);
		var num=$.trim($("#newNum"+order).val());
		$("#newMoney"+order).text(formatDouble(data.price*num));
		$("#newNum"+order).focus();
	}
}

function checkGoodsRepeat(goodsId,obj){
	var checkMsg='';
	var atNo=obj.attr("atNo");
	var order=obj.attr("order");
	$(".goodsNm").each(function(){
		if(atNo!=$(this).attr("atNo")){
			var thisGoodsId=$(this).attr("goodsId");
			var goodsType=$(this).attr("goodsType")
			if(thisGoodsId==goodsId&&goodsType==0){
				checkMsg='列表中已经有相同的物料，请勿重复添加';
				return false;
			}
		}
	});	
	if(checkMsg==''){
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
	}
	return checkMsg;
}

//添加行
function addRow() {
	var oldLength=$(".goodsNm").length;
	maxOrder++;
	var str = '<tr id="newTr'+maxOrder+'" class="newTr" order="'+maxOrder+'">'+
	    '<td id="newIndex'+maxOrder+'">'+(maxOrder+oldLength)+'</td>'+
		'<td id="newGoodsCode'+maxOrder+'"></td>'+
		'<td><div class="input-group">'+
		'<input type="text" rowType="new" goodsType="0" goodsId="-1" order="'+maxOrder+'" id="newGoodsNm'+maxOrder+'" class="form-control newGoodsNm">'+
  		'<span class="input-group-btn">'+
    	'<button order="'+maxOrder+'" rowType="new" class="goodsBtn btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>'+
  		'</span>'+
		'</div></td>'+
		'<td id="newSpec'+maxOrder+'"></td>'+
		'<td id="newUnitNm'+maxOrder+'"></td>'+
		'<td>'+customerNm+'</td>'+
		'<td>'+teamNm+'</td>'+
		'<td><input type="text" class="form-control newNum" order="'+maxOrder+'" id="newNum'+maxOrder+'"></td>'+
		'<td><input type="text" class="form-control newPrice" order="'+maxOrder+'" id="newPrice'+maxOrder+'"></td>'+
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
	}else{
		lastEditObj.unbind();
	}
}

function addTmp(){
	clearTmpModel();
	$("#tmpModal").modal();
}

function addTmpSure(){
	var oldLength=$(".goodsNm").length;//获取元素个数
	var goodsNm=$.trim($("#tmpGoodsNm").val());
	if(goodsNm==''){
		tips('物料名不能为空');
		$("#tmpGoodsNm").focus();
		return;
	}
	var tempGoodsType=$("#kindSelTmpl").attr("code");
	if(tempGoodsType==undefined || tempGoodsType=='-1'){
		tips('清选择正确的物料类型');
		$("#kindSelTmpl").focus();
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
	    '<td id="newIndex'+maxOrder+'">'+(maxOrder+oldLength)+'</td>'+
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
		'<td><input type="text" value="'+num+'" class="form-control newNum" order="'+maxOrder+'" id="newNum'+maxOrder+'"></td>'+
		'<td><input type="text" value="'+price+'" class="form-control newPrice" order="'+maxOrder+'" id="newPrice'+maxOrder+'"></td>'+
		'<td id="newMoney'+maxOrder+'">'+money+'</td>'+
		'<td><input id="newRemark'+maxOrder+'" value="'+remark+'" class="form-control" order="'+maxOrder+'" type="text"></td>'+
		'<td style="display:none" id="newTempGoodsType'+maxOrder+'">'+tempGoodsType+'</td>'+
		'<td><a class="newDel" id="newDel'+maxOrder+'" order="'+maxOrder+'">删除</a></td>'+
		'</tr>';
	$("#tbody").append(str);
	bindAutoComplete($("#newGoodsNm"+maxOrder));
	// tab键加行
	bindTab($("#newRemark" + maxOrder));
	if(maxOrder>1){
		$("#newRemark" + (maxOrder - 1)).unbind();
	}else{
		lastEditObj.unbind();
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

function delOld(atNo){
	delAtNo=delAtNo+atNo+",";
	//删除行
	$("#oldTr"+atNo).remove();
	//绑定tab
	var l=$(".goodsNm").length;
	var index=0;
	$(".goodsNm").each(function(){
		if(index==l-1){
			var demandDId=$(this).attr("atNo");
			lastEditObj=$("#remark"+demandDId);
			if(maxOrder==0){
				lastEditObj.unbind();
				bindTab(lastEditObj);
			}
		}
		index++;
	});	
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
			$("#newNum"+preOrder).attr("order",thisOrder);
			$("#newNum"+preOrder).attr("id","newNum"+thisOrder);
			$("#newPrice"+preOrder).attr("id","newPrice"+thisOrder);
			$("#newMoney"+preOrder).attr("id","newMoney"+thisOrder);
			$("#newRemark"+preOrder).attr("order",thisOrder);
			$("#newRemark"+preOrder).attr("id","newRemark"+thisOrder);
			$("#newTempGoodsType"+preOrder).attr("order",thisOrder);
			$("#newTempGoodsType"+preOrder).attr("id","newTempGoodsType"+thisOrder);
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
	if(newList.length==0&&editList.length==0){
		tips("请至少保存一种物料");
		return;
	}
	load();
	$.post("saveDemandD.do?", {
		billId:billId,
		newList:JSON.stringify(newList),
		editList:JSON.stringify(editList),
		delAtNo:delAtNo,
		headAtNo:headAtNo,
		customerId:customerId,
		teamId:teamId
	}, function(data) {
		closeAll();
		tips(data.msg);
		setTimeout(function(){
			location.reload();
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
		var goodsId=-1,spec='',unitId=-1,unitNm='',tempGoodsType=1;
		if(goodsType==0){
			goodsId=$("#newGoodsNm"+order).attr("goodsId");
		}else{
			spec=$("#newSpec"+order).text();
			unitId=$("#newUnitNm"+order).attr("newUnitId");
			unitNm=$("#newUnitNm"+order).text();
			tempGoodsType=$("#newTempGoodsType"+order).text();
		}
		if(goodsType==0&&goodsId==-1){
			return true;
		}
		var remark=$("#newRemark"+order).val();
		var price=$("#newPrice"+order).val();
		var obj={atNo:-1,num:num,goodsId:goodsId,goodsType:goodsType,
				remark:remark,spec:spec,unitNm:unitNm,goodsNm:goodsNm,
				tempGoodsType:tempGoodsType,price:price};
		newList.push(obj);
	});
	//编辑列表
	$(".oldTr").each(function(){
		var atNo=$(this).attr("atNo");
		var num=$.trim($("#num"+atNo).val());
		var goodsType=$("#goodsNm"+atNo).attr("goodsType");
		var goodsId=-1;
		if(goodsType==0){
			goodsId=$("#goodsNm"+atNo).attr("goodsId");
		}
		var goodsNm=$.trim($("#goodsNm"+atNo).val());
		var remark=$("#remark"+atNo).val();
		var price=$("#price"+atNo).val();
		var obj={atNo:atNo,num:num,goodsId:goodsId,goodsType:goodsType,
				remark:remark,goodsNm:goodsNm,price:price};
		editList.push(obj);
	});
	//删除字符串
	if(delAtNo.length>0){
		delAtNo=delAtNo.substring(0,delAtNo.length-1);
	}
}

function checkBeforeSave(){
	var checkMsg='';
	$(".num").each(function(){
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
	$(".price").each(function(){
		var val=$.trim($(this).val());
		if(isNaN(val)){
			$(this).focus();
			checkMsg='价格只能填写数字';
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
	var reviewState;
	var billDate='';
	if(roleId==9&&billState==0){
		reviewState=1;
		billDate = $("#submitTime").val();
	}else if(roleId==5&&billState==1){
		reviewState=2;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	var checkMsg=checkBeforeSave();
	if(checkMsg.length>0){
		tips(checkMsg);
		return;
	}
	generateList();
	if(newList&&newList.length==0&&editList&&editList.length==0){
		tips("请至少提交一种物料");
		return;
	}
	load();
	$.post("dealDemandBill.do?", {
		billId:billId,
		reviewState:reviewState,
		operType:0,
		billDate:billDate,
		remark:$.trim($("#remark").val()),
		newList:JSON.stringify(newList),
		editList:JSON.stringify(editList),
		delAtNo:delAtNo,
		headAtNo:headAtNo,
		customerId:customerId,
		teamId:teamId
	}, function(data) {
		closeAll();
		tips(data.msg);
		if(data.code==1000){
			setTimeout(function(){
				window.location.href="demandInit.do";
			},1000);
		}else if(data.code==209){
			setTimeout(function(){
				window.location.href="../purchase/purchaseInit.do";
			},1000);
		}
	}, "json");	
}

function refuseBill(){
	var reviewState;
	if(roleId==5&&billState==1){
		reviewState=0;
	}else{
		tips("对不起，您没有权限进行此项操作");
		return;
	}
	load();
	$.post("dealDemandBill.do?", {
		billId:billId,
		reviewState:0,
		operType:1,
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