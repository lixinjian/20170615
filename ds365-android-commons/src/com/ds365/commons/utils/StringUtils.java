package com.ds365.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import android.widget.EditText;

public class StringUtils {

	static DecimalFormat df = new DecimalFormat("#0.00");

	public final static boolean isBlank(String str) {
		return null == str || str.equals("");
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @return String
	 */
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	public static String decimalFromart(double value) {

		return df.format(value);
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.trim().equals("")) {
			str = defaultValue;
		} else if (str.startsWith("null")) {
			str = str.substring(4, str.length());
		}
		return str.trim();
	}

	/**
	 * 编码
	 * 
	 * @param value
	 * @return
	 */
	public static String encoder(String value) {
		if (StringUtils.isBlank(value)) {
			return "";
		} else {
			return URLEncoder.encode(value);
		}
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decoder(String str) {

		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	/**
	 * @param price
	 * @return
	 */
	public static String formatPriceD2S(double price) {
		DecimalFormat fnum = new DecimalFormat("#,##0.00");
		String dd = fnum.format(price);
		return dd;
	}


	/**
	 * 提供精确的乘法运算。
	 * @param v1
	 * @param v2
	 * @return 两个参数的积
	 */

	public static double multiply(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * double转化为以万为单位
	 * @param price
	 * @return
	 */
	public static String formatSubtotalPrice(double price){
		BigDecimal b1 = new BigDecimal(Double.toString(price));
		BigDecimal b2 = new BigDecimal(Double.toString(10000));
		DecimalFormat df=new DecimalFormat("0.00");
		if(price<10000){
			return formatPriceD2S(price);
		}else{
			return new Double(df.format(b1.divide(b2).doubleValue()).toString())+"万";
		}
	}

	/**
	 * 判断字符串是否为null,如果为null返回"",如果不为null就返回本身.
	 * @param str
	 * @return
	 */
	public static String isEmptyString(String str) {
		if (str == null||"".equals(str)) {
			return "";
		}else{
			return str;
		}
	}

	/**
	 * 判断字符串是否为null,如果为null返回0,如果不为null就返回int类型.
	 * 
	 * 前提:确定字符串为数字
	 * @param str
	 * @return
	 */
	public static int isEmptyInt(String str) {
		if (str == null||"".equals(str)) {
			return 0;
		}else{
			return Integer.parseInt(str);
		}
	}
	
	/**
	 * 判断EditText编辑框内容是否为空
	 * 为空返回true
	 */
	public static boolean isEmptyEditText(EditText eidtText){
		
		if (eidtText.getText().toString().trim().length() == 0) {
			return true;
		}else{
			return false;
		}
	}
	
}
