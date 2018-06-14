//提示
function tips(content) {
	layer.msg(content);
}
// 关闭所有layer对话框
function closeAll() {
	layer.closeAll();
}
// 加载中。。。
function load() {
	layer.load(2);
}
// 确认框
function confirm(content, callback) {
	layer.confirm(content, {
		skin : 'layui-layer-molv',
		title : '提示'
	}, function(index) {
		if (callback)
			callback();
		layer.close(index);
	});
}

function prePage(callback) {
	curPage = curPage - 1;
	callback();
}

function nextPage(callback) {
	curPage = parseInt(curPage) + parseInt(1);
	callback();
}

function pageSure(callback) {
	var page = $.trim($("#pageInput").val());
	if (page == '')
		return;
	if (isNaN(page)) {
		tips("请输入正确的页码");
		return;
	}
	if (parseInt(page) < 1 || parseInt(page) > parseInt(totalPage)) {
		tips("超出页码范围，无法跳转");
		return;
	}
	curPage = page;
	callback();
}
// 绑定翻页事件
function bindPage(callback) {
	$(document).on("click", "#prePage", function() {
		prePage(callback);
	});
	$(document).on("click", "#nextPage", function() {
		nextPage(callback);
	});
	$(document).on('input propertychange', '#pageInput', function() {
		$(this).val(v.passValidate($(this).val(), 4));
	});
	$(document).on("click", "#pageSure", function() {
		pageSure(callback);
	});
}
// 数组remove方法定义
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

// map方法定义
function Map() {
	this.elements = new Array();

	// 获取MAP元素个数
	this.size = function() {
		return this.elements.length;
	}

	// 判断MAP是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	}

	// 删除MAP所有元素
	this.clear = function() {
		this.elements = new Array();
	}

	// 向MAP中增加元素（key, value)
	this.put = function(_key, _value) {
		this.elements.push({
			key : _key,
			value : _value
		});
	}

	// 删除指定KEY的元素，成功返回True，失败返回False
	this.remove = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	// 获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key) {
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return null;
		}
	}

	// 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	}

	// 判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key) {
		varbln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	// 判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	// 获取MAP中所有VALUE的数组（ARRAY）
	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	}

	// 获取MAP中所有KEY的数组（ARRAY）
	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	}
}

// 验证
var validate = function() {
	// return new validate;
};

validate.prototype = {

	moneyvalidate : function(pstr, num, isMinus) { // pstr:当前字符串,num:限制金额总位数(小数只取2位),isMinus:允许为负数
		if (!pstr)
			return;
		var str = pstr;
		var oldStr = str.substr(0, str.length - 1);
		var num = arguments[1] ? arguments[1] : 6;
		var minusFlag = arguments[2] ? arguments[2] : false;
		var errorN = /^0[0-9]{1}/;
		if (errorN.test(str)) {
			return oldStr;
		}
		// var r=
		// /^[1-9][0-9]{1,3}\.[0-9]{0,1}$|^[1-9][0-9]{0,5}$|^0\.[0-9]{0,1}$|^0$/;
		var restr = "^[1-9][0-9]{0," + (num - 3)
				+ "}\\.[0-9]{0,2}$|^[1-9][0-9]{0," + (num - 1)
				+ "}$|^0\\.[0-9]{0,2}$|^0$";
		if (minusFlag) {
			if (str == '-' && str.length == 1) {
				return str;
			}
			restr = "^-?[1-9][0-9]{0," + (num - 3)
					+ "}\\.[0-9]{0,2}$|^-?[1-9][0-9]{0," + (num - 1)
					+ "}$|^-?0\\.[0-9]{0,2}$|^0$";
		}
		var re = new RegExp(restr, "g");
		if (re.test(str)) {
			return str;
		} else {
			return oldStr;
		}

	},
	moneyvalidate2 : function(pstr, num, isMinus) { // pstr:当前字符串,num:限制金额总位数(小数只取1位),isMinus:允许为负数
		if (!pstr)
			return;
		var str = pstr;
		var oldStr = str.substr(0, str.length - 1);
		var num = arguments[1] ? arguments[1] : 6;
		var minusFlag = arguments[2] ? arguments[2] : false;
		var errorN = /^0[0-9]{1}/;
		if (errorN.test(str)) {
			return oldStr;
		}
		// var r=
		// /^[1-9][0-9]{1,3}\.[0-9]{0,1}$|^[1-9][0-9]{0,5}$|^0\.[0-9]{0,1}$|^0$/;
		var restr = "^[1-9][0-9]{0," + (num - 3)
				+ "}\\.[0-9]{0,1}$|^[1-9][0-9]{0," + (num - 1)
				+ "}$|^0\\.[0-9]{0,1}$|^0$";
		if (minusFlag) {
			if (str == '-' && str.length == 1) {
				return str;
			}
			restr = "^-?[1-9][0-9]{0," + (num - 3)
					+ "}\\.[0-9]{0,1}$|^-?[1-9][0-9]{0," + (num - 1)
					+ "}$|^-?0\\.[0-9]{0,1}$|^0$";
		}
		var re = new RegExp(restr, "g");
		if (re.test(str)) {
			return str;
		} else {
			return oldStr;
		}

	},
	passValidate : function(pass, num, isMinus) { // 电话号码或密码验或负整数
		var num = arguments[1] ? arguments[1] : 6;
		var minusFlag = arguments[2] ? arguments[2] : false;
		var str = pass;
		var oldStr = str.substr(0, str.length - 1);
		var restr;
		restr = "^[0-9]{1," + num + "}$";
		if (minusFlag) {
			if (str == '-' && str.length == 1) {
				return str;
			}
			restr = "^-?[0-9]{1," + num + "}$";
		}
		var re = new RegExp(restr, "g");
		if (re.test(str)) {
			return str;
		} else {
			return oldStr;
		}

	},
	isinteger : function(str, num) {
		var num = arguments[1] ? arguments[1] : 3;

		if (!str)
			return '';
		var oldStr = str.substr(0, str.length - 1);
		var restr = "^[1-9]\\d{0," + num + "}$";
		var re = new RegExp(restr, "g");
		if (re.test(str) && str * 1 > 0) {
			return str;
		} else {
			return oldStr;
		}

	}
}
var v = new validate();// page输入框验证

String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substring(this.length - s.length) == s)
		return true;
	else
		return false;
	return true;
}
String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substr(0, s.length) == s)
		return true;
	else
		return false;
	return true;
}

function formatDouble(num) {
	num = num.toFixed(2);//四舍五入,指定保留范围
	if (num.endsWith(".00")) {
		num = num.substring(0, num.length - 3);
	} else if (num.endsWith("0")) {
		num = num.substring(0, num.length - 1);
	}
	return num;
}

function getDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = (dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd
			.getMonth() + 1);// 获取当前月份的日期，不足10补0
	var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate(); // 获取当前几号，不足10补0
	return y + "-" + m + "-" + d;
}

function getDateString(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = (dd.getMonth() + 1) < 10 ? "0" + (dd.getMonth() + 1) : (dd
			.getMonth() + 1);// 获取当前月份的日期，不足10补0
	var d = dd.getDate() < 10 ? "0" + dd.getDate() : dd.getDate(); // 获取当前几号，不足10补0
	var h = dd.getHours();
	var mm = dd.getMinutes();
	var s = dd.getSeconds();
	return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s;
}

