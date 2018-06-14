var g_goodsNm = sessionStorage.goodsNm;
var g_stime = sessionStorage.purchaseSummStime;
var g_etime = sessionStorage.purchaseSummEtime;
var g_customerIds = sessionStorage.purchaseSummCustomerIds||-1;
$(document).ready(function() {
//-------------------------图表展示开始--------------------------------
			// 路径配置
			require.config({
				paths: {
					echarts: '../js/plugins/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require(
				[
					'echarts',
					'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
				],
				function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var ziDingYi = ec.init(document.getElementById('main'));
					option = {
						timeline: {
							data: [
								'1(成本占比)', '2(收入占比)', '3(毛利率%)'
							],
							label: {
								formatter: function(s) {
									return s.slice(0, 10);
								}
							}
						},
						options: [{
							title: {
								text: '分类占比',
								subtext: '千喜鹤集团'
							},
							tooltip: {
								trigger: 'item',
								formatter: "{a} <br/>{b} : {c} ({d}%)"
							},
							legend: {
								data: ['蔬菜类', '水果类', '猪肉鲜品', '牛羊肉鲜品', '禽肉鲜品','水产鲜品',
								       '肉类冻品','调料类','蛋奶类','热食类','物耗','小商品']
							},
							series: [{
								name: '成本占比（千喜鹤大数据中心）',
								type: 'pie',
								center: ['50%', '45%'],
								radius: '50%',
								data: getSelectBigKindForCost()
							}]
						}, {
							series: [{
								name: '收入占比（千喜鹤大数据中心）',
								type: 'pie',
								data: getSelectBigKindForIncome()
							}]
						}, {
							series: [{
								name: '毛利率占比（千喜鹤大数据中心）',
								type: 'pie',
								data: getSelectBigKindForProfit()
							}]
						}
						]
					};
					// 为echarts对象加载数据 
					ziDingYi.setOption(option);
				}
			);
//---------------------------图表展示结束-----------------------------------
			
			//导出物料价格列表
	$(document).on("click", "#outExcelForKind", function(){
		location.href="../purchaseSumm/outExcelForBigKind.do?"+"goodsNm="+g_goodsNm+
		"&stime="+g_stime+"&etime="+g_etime+"&customerIds="+g_customerIds;
		 });
	});
// 获取收入占比
function getSelectBigKindForIncome() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getSelectBigKindForIncome.do",
		data : {
			goodsNm : g_goodsNm,
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
//获取成本占比
function getSelectBigKindForCost() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getSelectBigKindForCost.do",
		data : {
			goodsNm : g_goodsNm,
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds
		},
		async : false,

		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
//获取毛利率
function getSelectBigKindForProfit() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getSelectBigKindForProfit.do",
		data : {
			goodsNm : g_goodsNm,
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds
		},
		async : false,

		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}

