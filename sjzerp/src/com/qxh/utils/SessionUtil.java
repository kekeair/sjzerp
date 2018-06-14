package com.qxh.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {

	public static HttpSession getSession() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra)
				.getRequest();
		return request.getSession();
	}

	public static void setcookie(HttpServletRequest request,
			HttpServletResponse response, String cookiename, String cookievalue)
			throws Exception {
		cookievalue = URLEncoder.encode(cookievalue, "UTF-8");
		Cookie cookies[] = request.getCookies();
		int existbar = 0;
		Cookie c = null;
		if (cookies != null) {
			// 判断cookie是否存在，存在则更新cookie
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
				if (c.getName().equals(cookiename)) {
					existbar++;
					c.setValue(cookievalue);
					c.setPath("/");
					c.setMaxAge(60 * 60 * 60 * 12 * 30);
					response.addCookie(c); // 修改后，要更新到浏览器中
				}
			}
		}
		if (existbar == 0)// 如果不存在则新建cookie
		{
			Cookie c1 = new Cookie(cookiename, cookievalue);
			c1.setPath("/");
			c1.setMaxAge(60 * 60 * 60 * 12 * 30);
			response.addCookie(c1);
		}

	}

	public static String getcookie(HttpServletRequest request, String cookiename)
			throws Exception {
		// 读取Cookie
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			Cookie c = null;
			String clickdate = "";
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];
				if (c.getName().equals(cookiename)) {
					clickdate = c.getValue();
				}
			}
			clickdate = URLDecoder.decode(clickdate, "UTF-8");
			return clickdate;
		} else {
			return null;
		}
	}
	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String  getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
