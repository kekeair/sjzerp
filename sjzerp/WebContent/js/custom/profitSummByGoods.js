var g_goodsId = sessionStorage.goodsId;
var g_goodsType = sessionStorage.goodsType;
var g_stime = sessionStorage.purchaseSummStime;
var g_etime = sessionStorage.purchaseSummEtime;
var g_demandListDId = sessionStorage.demandListDId;
var g_purchaseListDId = sessionStorage.purchaseListDId;
var g_customerIds = sessionStorage.purchaseSummCustomerIds||-1;
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
							text : '单品统计',
							subtext: '按日统计'	
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '数量', '进价', '售价', '毛利率(%)' ],
							selected: {
					            '进价':false,
					            '售价':false,
					            '毛利率(%)':false,
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
									'26日', '27日', '28日', '29日', '30日', '31日' ]
						},
						yAxis : {
							type : 'value'
						},
						series : [ {
							name : '数量',
							type : 'line',
							stack : '总量',
							data : getOrderNum()
						}, {
							name : '进价',
							type : 'line',
							stack : '总量',
							data : getPPrice()
						}, {
							name : '售价',
							type : 'line',
							stack : '总量',
							data : getDPrice()
						}, {
							name : '毛利率(%)',
							type : 'line',
							stack : '总量',
							data : getRate()
						} ]
					};
					// 为echarts对象加载数据
					ziDingYi.setOption(option);
				});
			} else if (year == 0 ) {
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
							text : '单品统计',
							subtext: '按月统计'	
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '数量', '进价', '售价', '毛利率(%)' ],
						    selected: {
						    	 '进价':false,
						    	 '毛利率(%)':false,
						         '售价':false,
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
							data : getOrderNumInMonth()
						}, {
							name : '进价',
							type : 'line',
							stack : '总量',
							data : getPPriceInMonth()
						}, {
							name : '售价',
							type : 'line',
							stack : '总量',
							data : getDPriceInMonth()
						}, {
							name : '毛利率(%)',
							type : 'line',
							stack : '总量',
							data : getRateInMonth()
						} ]
					};
					// 为echarts对象加载数据
					ziDingYi.setOption(option);
				});
			} else {
				$('#main').removeAttr("style");
				$("#main").css({fontSize:"30px",color:"blue"});       
				$("#main").text("警告:请选择单月内时间段或者年内时间段").attr("align", "center");
				
			}
		});

// 获取采购列表
function GetProfitSummList() {
	var goodsNm = $("#goodsNm").val();
	$.post("../purchaseSumm/getProfitSummList.do?", {
		stime : g_stime,
		etime : g_etime,
		customerIds : g_customerIds,
		goodsNm : goodsNm
	}, function(data) {
		if (data.code != 1000) {
			tips(data.msg);
		} else {
			$("#tbody").empty();
			$("#listTmpl").tmpl(data.data.list).appendTo("#tbody");
			$("#totalTmpl").tmpl(data.data.totalList).appendTo("#tfoot");
		}
	}, "json");
}
//------------------------按日查-----------------------------
// 获取每天的数量
function getOrderNum() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getOrderNum.do",
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
			list = data.data;
			return false;
		}
	});
	return list;
}
// 获取每天的售价
function getDPrice() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getDPrice.do",
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
			list = data.data;
			return false;
		}
	});
	return list;
}
// 获取每天的售价
function getPPrice() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getPPrice.do?",
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
			list = data.data;
			return false;
		}
	});
	return list;
}
// 获取每天的毛利率
function getRate() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getRateInDay.do?",
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
			list = data.data;
			return false;
		}
	});
	return list;
}
 //-------------------------按月份查---------------------------
function getOrderNumInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getOrderNumInMonth.do?",
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
function getPPriceInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getPPriceInMonth.do?",
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
//获取每天的售价
function getDPriceInMonth() {
	var list = [];
	$.ajax({
		type : "GET",
		url : "../purchaseSumm/getDPriceInMonth.do",
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