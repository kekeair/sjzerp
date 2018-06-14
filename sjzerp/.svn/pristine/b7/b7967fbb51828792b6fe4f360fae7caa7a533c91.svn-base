package com.qxh.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qxh.service.CommonService;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private static final String SHOW_LOGIN_PATH = "SHOW_LOGIN_PATH"; // 显示登录页面
	private static final String DO_LOGIN_PATH = "DO_LOGIN_PATH"; // 登录操作不能过滤掉
	private static final String LOGIN_PERSONID = "LOGIN_PERSONID"; // 登录超时跳转中间页面
	private static final String SELECT_JOB = "SELECT_JOB"; // 选择职务
	// 登录用户在session中的属性key
	// --
	// session.setAttribute(key,value)
	private String showloginPath;
	private String dologinPath;
	private String loginPersonId;
	private String selectJob;
	private CommonService service;

	public void init(FilterConfig config) throws ServletException {
		showloginPath = config.getInitParameter(SHOW_LOGIN_PATH);
		dologinPath = config.getInitParameter(DO_LOGIN_PATH);
		loginPersonId = config.getInitParameter(LOGIN_PERSONID);
		loginPersonId = config.getInitParameter(LOGIN_PERSONID);
		selectJob=config.getInitParameter(SELECT_JOB);
		if (showloginPath == null || showloginPath.equals("")
				|| showloginPath.equals("null")) {
			throw new ServletException("登录页面配置出错...");
		}
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "config/applicationContext.xml" });
		if (ac != null && ac.getBean("CommonService") != null && service == null)
			service = (CommonService) ac.getBean("CommonService");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		httpResp.setContentType("text/html; charset=utf-8");
		// 判断是否是登陆页面
		String servletPath = httpReq.getServletPath();
		// flag:若为登陆页面的action路径 showloginPath/nologinPath,则赋值true，否则赋值false
		if (servletPath.equals(showloginPath)
				|| servletPath.equals(dologinPath)
				|| servletPath.equals(loginPersonId)
				||servletPath.equals(selectJob)
				||servletPath.equals("/test/test.do")) {
			chain.doFilter(request, response);
			return;
		} else { // 如果不是登录页面，则需先判断用户是否已登录
			String userAccount = null;
			String pwd = null;
			try {
				userAccount = (String) httpReq.getSession().getAttribute(
						"userAccount");
				pwd = (String) httpReq.getSession()
						.getAttribute("pwd");
			} catch (Exception e) {
			}
			if (!StringUtils.isEmpty(userAccount)
					&& !StringUtils.isEmpty(pwd)) {// 如果不为空,则进行已登录处理
				chain.doFilter(request, response);
			} else {// 如果为空,则进行未登录处理
				if (httpReq.getQueryString() != null) {
					servletPath += "?" + httpReq.getQueryString();
				}
				String ajax = httpReq.getHeader("x-Requested-with");
				if (ajax != null && ajax.equalsIgnoreCase("XMLHttpRequest")) {
					// 是ajax请求，则返回个消息给前台
					PrintWriter printWriter = response.getWriter();
					printWriter.print("{sessionState:timeout}");
					printWriter.flush();
					printWriter.close();
				} else {
					httpResp.sendRedirect(request.getServletContext()
							.getContextPath() + loginPersonId);
				}
			}
		}
	}

	public void destroy() {
		// do something
	}
}
