/** 
 * 设置未来(全局)的AJAX请求默认选项 
 * 主要设置了AJAX请求遇到Session过期的情况 
 */  
$().ready(function() {
	$.ajaxSetup({   
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
	    cache:false ,   
	    complete:function(XHR,TS){   
	        var resText=XHR.responseText;   
	        if(resText!=null && resText.indexOf("sessionState:timeout")>0){   
	        	  var top = getTopWinow();  
	        	  customalert('由于您长时间没有操作，登录已超时，请重新登录!',function(){
	            	  top.location.href = 'login/login.do?action=loginHtml';   
	              },"登录超时");  
	        }
	        if(resText!=null && resText.indexOf("pwd:change")>0){   
	        	  var top = getTopWinow();  
	        			top.location.href = 'userlogin.do?action=loginHtml&pwdChangeType=1';  
	        }  
	    }   
	}); 
	//禁止按键F5
	document.onkeydown = function (e) {
		var ev = window.event || e;
		var code = ev.keyCode || ev.which;
		if (code == 116) {
		if(e && e.preventDefault) {
		ev.preventDefault();
		} else {
		ev.keyCode = 0;
		ev.returnValue = false;
		}
		}
	}
});
/** 
 * 在页面中任何嵌套层次的窗口中获取顶层窗口 
 * @return 当前页面的顶层窗口对象 
 */  
function getTopWinow(){  
    var p = window;  
    while(p != p.parent){  
        p = p.parent;  
    }  
    return p;  
}  

function showdate(n) 
{ 
var uom = new Date(); 
uom.setDate(uom.getDate()+n); 
uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate(); 
return uom; 
} 

function checknum(obj)
{
 var re = /^-?[1-9]*(.d*)?$|^-?d^(.d*)?$/;
if(isNaN(obj.value)||obj.value<0){ 
	obj.value="";
        obj.focus();
        return false;
        }
}