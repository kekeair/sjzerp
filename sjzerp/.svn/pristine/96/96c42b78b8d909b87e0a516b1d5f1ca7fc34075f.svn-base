package com.qxh.utils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: CheckUtil
 * @Description: 验证类
 * @author chenyang
 * @date 2014-12-4 上午9:39:15
 * 
 */
public class CheckUtil {

	/**
	 * 方法描述 检查必须的参数 如果参数值为空 则直接返回该参数名 如果参数值都不为空 则返回空
	 * 
	 * @param req
	 * @param params
	 *            要验证的参数
	 * @return
	 * @变更记录 2014-12-3 下午3:38:40 陈阳 创建
	 */
	public static String checkParam(HttpServletRequest req, List<String> params) {
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				return param;
			}
		}
		return "success";
	}

	/**
	 * 方法描述
	 * 
	 * @param mav
	 *            返回ModelAndView
	 * @param code
	 *            返回代码
	 * @param msg
	 *            返回信息
	 * @param data
	 *            返回对象
	 * @return
	 * @变更记录 2014-12-4 上午9:38:23 陈阳 创建
	 */
	public static ModelAndView returnResult(ModelAndView mav, int code,
			String msg, Object data) {

		mav.addObject("code", code);
		mav.addObject("msg", msg);
		mav.addObject("data", data);
		return mav;
	}

	/**
	 * 方法描述 获取token值
	 * 
	 * @param input
	 * @return
	 * @变更记录 2014-12-17 下午2:22:50 陈阳 创建
	 */
	public static String token4MD5(String input) {
		try {
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = input.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}

	private static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	public static String doubleTrans(double d) {
		if (Math.round(d) - d == 0) {
			return String.valueOf((long) d);
		}
		return String.valueOf(d);
	}

	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

	public static void sendfile(InputStream in, String filename, String type,
			String uploadurl) {
		String BOUNDARY = "---------------------------123821742118716";
		try {
			URL url = new URL(uploadurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setChunkedStreamingMode(1024 * 1024);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");

			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());

			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\""
					+ filename + "\"\r\n");
			strBuf.append("Content-Type:" + type + "\r\n\r\n");

			out.write(strBuf.toString().getBytes());
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);

		}
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// 将字母转换成数字_1
	// 将数字转换成字母
	public static String numToLetter(String input) {
		return (char) (Integer.parseInt(input) + 64) + "";
	}

	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	// 保留2位小数
	public static double get2Double(double a) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(a).toString());
	}

	public static synchronized String getLsNo(int length) {
		String lsNo = IdWorker.createId(length);
		int lastIndex = 0;
		for (int i = 0; i < lsNo.length(); i++) {
			long tmpNo = Long.parseLong(lsNo.charAt(i) + "");
			lastIndex += tmpNo;
		}
		String lastIndexStr = lastIndex + "";
		lsNo = lsNo
				+ (Long.parseLong(lastIndexStr.charAt(lastIndexStr.length() - 1)
						+ ""));
		return lsNo;
	}
	
	public static  String getLsNo(String epId, String brId) {
		String lsNo =IdWorker.createId();
		int lastIndex = 0;
		for (int i = 0; i < lsNo.length(); i++) {
			long tmpNo = Long.parseLong(lsNo.charAt(i) + "");
			lastIndex += tmpNo;
		}
		String lastIndexStr = lastIndex + "";
		lsNo = lsNo
				+ (Long.parseLong(lastIndexStr.charAt(lastIndexStr.length() - 1)
						+ ""));
		return lsNo;
	}
}
