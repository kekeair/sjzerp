var curPage=1;//当前页
var totalPage=1;//总页数
var g_stime="";//开始时间
var g_etime="";//结束时间
$(document).ready(function() {
	GetTmpGoodsRecordList();//库存修正单列表
	bindPage(GetTmpGoodsRecordList);
	$(document).on("click", "#search", search);

});

//获取临时列表
function GetTmpGoodsRecordList() {
	$.post("getTmpGoodsRecordList.do?", {
		stime:g_stime,
		etime:g_etime,
		page:curPage
	}, function(data) {
		if (data.code!=1000) {
			tips(data.msg);
		} else {
			data.data.roleId=roleId;
			$("#tbody").empty();
			$("#tmpGoodsRecordTmpl").tmpl(data.data).appendTo("#tbody");
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
	g_stime=$("#stime").val();
	g_etime=$("#etime").val();
	curPage=1;
	totalPage=1;
	GetTmpGoodsRecordList();
}

