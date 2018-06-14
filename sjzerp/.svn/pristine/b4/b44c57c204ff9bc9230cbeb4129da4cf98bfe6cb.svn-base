/**
 * 名称: ExceptionHandler.java
 * 描述: 
 * 类型: JAVA<br>
 * 最近修改时间:2014-4-7 下午3:36:21
 * @since  2014-4-7
 * @author 付少峰
 */
package com.qxh.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionHandler implements HandlerExceptionResolver {
	Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerExceptionResolver#resolveException
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception ex) {
		// log.error(ex.getMessage()+ex.getCause());
		ModelAndView mav = new ModelAndView();
		try {

			String ff = arg0.getHeader("accept");
			if (!(arg0.getHeader("accept").indexOf("application/json") > -1 || (arg0
					.getHeader("X-Requested-With") != null && arg0.getHeader(
					"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				if (!ex.getMessage().contains("errorcode")) {
					log.error(ex.getMessage());
				}
				mav.addObject("errormessage", ex.getMessage());
				mav.setViewName("errorpage");
			} else {// JSON格式返回
				if (!ex.getMessage().contains("errorcode")) {
					log.error(ex.getMessage());
				}
				mav.addObject("errormessage", ex.getMessage());
				mav.addObject("success", false);
			}

		} catch (Exception e) {
			String error = e.getMessage();
			if (!error.contains("errorcode"))
				log.error("\r\n  errorcode=" + ErrorCode.geterrocode(this)
						+ "  \r\n" + e + "  \r\n\r\n");
		}
		return mav;

	}

}
