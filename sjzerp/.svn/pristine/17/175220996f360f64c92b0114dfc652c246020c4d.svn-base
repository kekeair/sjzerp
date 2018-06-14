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
				rootPId: -1
			}
		},
		callback: {
			onClick: clickKind
		}
	};
var nodes;

//点击分类选择框
function kindSel(){
	var kindObj = $("#kindSel");
	var kindOffset = $("#kindSel").offset();
	$("#kindDiv").css({left:kindOffset.left + "px", top:kindOffset.top 
		+ kindObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onKindDown);
}

//选择分类
function clickKind(event, treeId, treeNode,clickFlag){
	if(treeNode){
		$("#kindSel").attr("code", treeNode.code);
		$("#kindSel").val(treeNode.name);
		g_kindCode=$("#kindSel").attr("code");
		hideKindMenu();
	}
}

//隐藏分类下拉框
function hideKindMenu(){
	$("#kindDiv").fadeOut("fast");
	$("body").unbind("mousedown", onKindDown);
}

function onKindDown(event) {
	if (!(event.target.id == "kindSel" || event.target.id == "kindDiv" 
		|| $(event.target).parents("#kindDiv").length>0)) {
		hideKindMenu();
	}
}

var curPage=1;//当前页
var totalPage=1;//总页数
var g_name="";//名称
var g_code="";//编码
var g_kindCode=-1;//物料分类
var changeList=new Array();//把价格有变化的行存起来

$(document).ready(function() {
	if(roleId==10){
		$("#save").show();
		$("#outExcel").show();
		$("#importdiv").show();
	}else{
		$("#save").hide();
		$("#outExcel").hide();
		$("#importdiv").hide();
	}
	getGoodsKind();//获取分类
	getGoodsPrice();//获取列表数据
	$(document).on('input propertychange','.newPrice',function(){
		//数字验证
		$(this).val(v.moneyvalidate($(this).val(),9));
		//把价格有变化的行存起来
		var newPrice=$(this).val();
		var supplierGoodsId=$(this).attr("supplierGoodsId");
		var centerId=$(this).attr("centerId");
		var supplierId=$(this).attr("supplierId");
		var goodsId=$(this).attr("goodsId");
		var frontPrice=$("#frontPrice"+supplierGoodsId).text();
		var obj={atNo:supplierGoodsId,centerId:centerId,
				supplierId:supplierId,goodsId:goodsId,frontPrice:newPrice};
		if(parseFloat(newPrice)!=parseFloat(frontPrice)){
			if(!contains(changeList,obj))
				changeList.push(obj);
		}else{
			remove(changeList,obj);
		}
	});
	
	$("#kindSel").bind("click", kindSel);
	bindPage(getGoodsPrice);//绑定翻页事件
	$(document).on("click", "#search", search);//点击搜索
	//enter名称键搜索
	$(document).on("keydown", "#goodsNm", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	//enter名称键搜索
	$(document).on("keydown", "#goodsCode", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
			search();
	});
	$(document).on("click", "#save", save);//点击保存
	
	//enter名称键搜索
	$(document).on("keydown", ".newPrice", function(event){
		if (event.which == 13) // 13等于回车键(Enter)键值,ctrlKey 等于
		save();
		//$(".newPrice").parent().children().next().focus();
	});
});

//判断changeList中是否已经包含指定元素obj
function contains(changeList,obj){
	var l=changeList.length;
	for(var i=0;i<l;i++){
		if(changeList[i].atNo==obj.atNo){
			changeList[i].frontPrice=obj.frontPrice;
			return true;
		}
	}
	return false;
}

//从changeList中移除指定元素obj
function remove(changeList,obj){
	var l=changeList.length;
	var index=-1;
	for(var i=0;i<l;i++){
		if(changeList[i].atNo==obj.atNo){
			index=i;
			break;
		}
	}
	if(index>-1){
		changeList.splice(index, 1);
	}
}

//查询物料分类
function getGoodsKind(){
	$.post("../common/getGoodsKind.do?", {
	}, function(data) {
		if(data.code==1000){
			nodes=data.data;
			//在最前面添加一个”全部“
			var allKind={atNo:0, pId:-1,code:'', name:"全部",open:true};
			nodes.unshift(allKind);
			zTree=$.fn.zTree.init($("#kindTree"), setting, nodes);
		}
	}, "json");
}

//获取列表数据
function getGoodsPrice() {
	$.post("getGoodsPrice.do?", {
		name:g_name,
		code:g_code,
		kindCode:g_kindCode,
		page:curPage,
		supplierId:$("#supplier").val()
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			tmplList(data);//加载列表
		}
	}, "json");
}

//搜索
function search(){
	g_name=$.trim($("#goodsNm").val());
	g_code=$.trim($("#goodsCode").val());
	curPage=1;
	totalPage=1;
	getGoodsPrice();
}

//保存
function save(){
	//验证数字
	var l=changeList.length;
	for(var i=0;i<l;i++){
		if(isNaN(changeList[i].frontPrice)){
			tips("价格必须是数字");
			return;
		}
	}
	if(changeList&&changeList.length>0){
		$.post("editGoodsPrice.do?", {
			priceJson:JSON.stringify(changeList)
		}, function(data) {
			tips(data.msg);
			if (data.code==1000) {
				//把列表数据在本地进行修改，不再重新查询
				for(var i=0;i<l;i++){
					var atNo=changeList[i].atNo;
					var price=changeList[i].frontPrice;
					$("#frontPrice"+atNo).text(price);
					$("#newPrice"+atNo).val(price);
				}
				changeList.length=0;
			}
		}, "json");
	}
}

//加载列表数据
function tmplList(data){
	$("#tbody").empty();
	data.data.roleId=roleId;
	$("#goodsPriceTmpl").tmpl(data.data).appendTo("#tbody");
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

//导出物料价格列表
$(document).on("click", "#outExcel", function(){
	location.href="../goodsPrice/outExcelGoodsPrice.do?"+"name="+g_name+
	"&code="+g_code+"&kindCode="+g_kindCode+"&supplierId="+$("#supplier").val();
});

$(document).on("change", "#inFile", uploadFile);//本地上传
//导入物料参数列表
function uploadFile(){
	$.ajaxFileUpload({
		url: '../goodsPrice/inExcelGoodsPrice.do', //用于文件上传的服务器端请求地址
		secureuri: false, //是否需要安全协议，一般设置为false
		fileElementId: 'inFile', //文件上传域的ID
		dataType: 'json', //返回值类型 一般设置为json
		success: function(data, status){ //服务器成功响应处理函数
			tips(data.msg);
			if(data.code==1000){
				setTimeout(function(){
					location.reload();
				},1000);
			}
		},
		error: function(data, status, e){ //服务器响应失败处理函数
			tips("上传失败");
		}
	});
}