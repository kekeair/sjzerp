
var g_stime = sessionStorage.purchaseSummStime;
var g_etime = sessionStorage.purchaseSummEtime;
var g_customerIds = sessionStorage.purchaseSummCustomerIds||-1;
var g_goodsKind = sessionStorage.stockQueryGoodsKind;
var customNms = sessionStorage.customNm;
$(document).ready(
		function() {
			var stime = new Date(g_stime);
			var etime = new Date(g_etime);
			var syear = stime.getFullYear();
			var eyear = etime.getFullYear();
			var year = eyear - syear;
			var smonth = stime.getMonth();
			var emonth = etime.getMonth();
			var month = emonth - smonth;
			if (year == 0 && month == 0) {
				if(g_customerIds.length<2){
				// 路径配置
				require.config({
					paths : {
						echarts : '../js/plugins/echarts-2.2.7/build/dist'
					}
				});
				// 使用
				require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var ziDingYi = ec.init(document.getElementById('main'));
					option = {
						title : {
							text : customNms,
							subtext: '按日统计'	
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '数量', '成本', '销售金额'],
							selected: {
					            '成本':false,
					            '销售金额':false,
					        },
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : [ '1日', '2日', '3日', '4日', '5日', '6日', '7日',
									'8日', '9日', '10日', '11日', '12日', '13日',
									'14日', '15日', '16日', '17日', '18日', '19日',
									'20日', '21日', '22日', '23日', '24日', '25日',
									'26日', '27日', '28日', '29日', '30日', '31日']
						},
						yAxis : {
							type : 'value'
						},
						series : [{
							name : '数量',
							type : 'line',
							stack : '总量',
							data : getOrderNumByCustomerInDay()
						}, {
							name : '成本',
							type : 'line',
							stack : '总量',
							data : getTotalPMoneyByCustomerInday()
						}, {
							name : '销售金额',
							type : 'line',
							stack : '总量',
							data : getTotalDMoneyByCustomerInday()
						}]
					};
					// 为echarts对象加载数据
					ziDingYi.setOption(option);
				});
			 }else{
				// 路径配置
					require.config({
						paths: {
							echarts: 'http://echarts.baidu.com/build/dist'
						}
					});
					// 使用
					require(
						[
							'echarts',
							'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
						],
						function(ec) {
							// 基于准备好的dom，初始化echarts图表
							var ziDingYi = ec.init(document.getElementById('main'));
							option = {
								title: {//标题
									text: '多客户统计',
									subtext: '按客户'  //小标题
								},
								tooltip : {
					                 trigger: 'axis'
					            },
								legend: {
									data: ['数量', '成本','销售金额']
								},
								calculable: true,
								xAxis: [{//x坐标
									type: 'category',
									data: ['机关一食堂', '机关二食堂', '招待所', '汽车队', '78分队', '正定新校', '正定老校', '库损']
								}],
								yAxis: [{//y坐标
									type: 'value'
								}],
								series: [{
									name: '数量',
									type: 'bar',//柱形图
									data: getOrderNumByCustomersInDay()
								}, 
								{
									name: '成本',
									type: 'bar',
									data: getTotalPMoneyByCustomersInDay()
								},
								{
								
									name: '销售金额',
									type: 'bar',
									data: getTotalDMoneyByCustomersInDay()
								}]
							};
							// 为echarts对象加载数据 
							ziDingYi.setOption(option);
						}
					);
			 }
			} else if (year == 0 ) {
				if(g_customerIds.length<2){
				// 路径配置
				require.config({
					paths : {
						echarts : 'http://echarts.baidu.com/build/dist'
					}
				});
				// 使用
				require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				], function(ec) {
					// 基于准备好的dom，初始化echarts图表
					var ziDingYi = ec.init(document.getElementById('main'));
					option = {
						title : {
							text : customNms,
							subtext: '按月统计'	
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '数量', '成本', '销售金额'],
						    selected: {
						    	 '成本':false,
						         '销售金额':false,
						        },
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
							         '8月', '9月', '10月', '11月', '12月']
						},
						yAxis : {
							type : 'value'
						},
						series : [ {
							name : '数量',
							type : 'line',
							stack : '总量',
							data : getOrderNumByCustomerInMonth()
						}, {
							name : '成本',
							type : 'line',
							stack : '总量',
							data : getToTalPMoneyByCustomerInMonth()
						}, {
							name : '销售金额',
							type : 'line',
							stack : '总量',
							data : getTotalDMoenyByCustomerInMonth()
						}]
					};
					// 为echarts对象加载数据
					ziDingYi.setOption(option);
				});
			}else{
				// 路径配置
				require.config({
					paths: {
						echarts: 'http://echarts.baidu.com/build/dist'
					}
				});
				// 使用
				require(
					[
						'echarts',
						'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
					],
					function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var ziDingYi = ec.init(document.getElementById('main'));
						option = {
							title: {//标题
								text: '多客户统计',
								subtext: '按客户'  //小标题
							},
							tooltip : {
								trigger : 'axis'
							},
							legend: {
								data: ['数量', '成本','销售金额']
							},
							calculable: true,
							xAxis: [{//x坐标
								type: 'category',
								data: ['机关一食堂', '机关二食堂', '招待所', '汽车队', '78分队', '正定新校', '正定老校', '库损']
							}],
							yAxis: [{//y坐标
								type: 'value'
							}],
							series: [{
								name: '数量',
								type: 'bar',//柱形图
								data: getOrderNumByCustomersInDay()
							}, 
							{
								name: '成本',
								type: 'bar',
								data: getTotalPMoneyByCustomersInDay()
							},
							{
							
								name: '销售金额',
								type: 'bar',
								data: getTotalDMoneyByCustomersInDay()
							}]
						};
						// 为echarts对象加载数据 
						ziDingYi.setOption(option);
					}
				);
			}
			} else {
				$('#main').removeAttr("style");
				$("#main").css({fontSize:"30px",color:"blue"});       
				$("#main").text("警告:请选择单月内时间段或者年内时间段").attr("align", "center");
				
			}
		});
//------------------------按日查-----------------------------
// 获取每天的数量
function getOrderNumByCustomerInDay() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getOrderNumByCustomerInDay.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
// 获取每天的成本(按客户)
function getTotalPMoneyByCustomerInday() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getTotalPMoneyByCustomerInday.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
// 获取每天的收入(按单个客户)
function getTotalDMoneyByCustomerInday() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getTotalDMoneyByCustomerInday.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,

		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
//获取每天的数量
function getOrderNumByCustomersInDay() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getOrderNumByCustomersInDay.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}

//获取每天的成本(按多客户)
function getTotalPMoneyByCustomersInDay() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getTotalPMoneyByCustomersInDay.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
//获取每天的成本(按多客户)
function getTotalDMoneyByCustomersInDay() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getTotalDMoneyByCustomersInDay.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,
		success : function(data) {
			list = data.data;
			return false;
		}
	});
	return list;
}
 //-------------------------按月份查---------------------------
//获取每月的物料数量(按单个客户)
function getOrderNumByCustomerInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getOrderNumByCustomerInMonth.do?",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,

		success : function(data) {
			list = data.data
			return false;
		}
	});
	return list;
}
//获取每月成本
function getToTalPMoneyByCustomerInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getToTalPMoneyByCustomerInMonth.do?",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,

		success : function(data) {
			list = data.data
			return false;
		}
	});
	return list;
}
//获取每月收入
function getTotalDMoenyByCustomerInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getTotalDMoenyByCustomerInMonth.do",
		data : {
			stime : g_stime,
			etime : g_etime,
			customerIds : g_customerIds,
			kindCode : g_goodsKind
		},
		async : false,

		success : function(data) {
			list = data.data
			return false;
		}
	});
	return list;
}
//获取每天的毛利率
function getRateInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getRateInMonth.do?",
		data : {
			goodsId : g_goodsId,
			goodsType : g_goodsType,
			stime : g_stime,
			etime : g_etime,
			demandListDId : g_demandListDId,
			purchaseListDId : g_purchaseListDId,
			customerIds : g_customerIds
		},
		async : false,
		success : function(data) {
			list = data.data
			return false;
		}
	});
	return list;
}