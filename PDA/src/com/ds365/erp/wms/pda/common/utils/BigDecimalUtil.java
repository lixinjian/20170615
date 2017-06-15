package com.ds365.erp.wms.pda.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 精确计算工具类
 * @author ethan
 *
 */
public class BigDecimalUtil {
	
	private static int DEFAULT_PRECISION = 6;
	
	private static RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
	
	/**
	 * 加法运算,使用两个参数中大的精度为返回精度
	 * @param value1
	 * @param value2
	 * @return value1 + value2
	 */
	public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
		return value1.add(value2);
	}
	
	/**
	 * 加法运算,使用指定精度返回
	 * @param value1
	 * @param value2
	 * @param precision 小数精度
	 * @param roundingMode 截断处理方式
	 * @return value1 + value2
	 */
	public static BigDecimal add(BigDecimal value1, BigDecimal value2,
			Integer precision, RoundingMode roundingMode) {
		if (precision == null && roundingMode == null) {
			return add(value1, value2);
		}
		return setScale(value1.add(value2), precision, roundingMode);
		
	}
	
	/**
	 * 减法运算,使用两个参数中大的精度为返回精度
	 * @param value1
	 * @param value2
	 * @return value1 - value2
	 */
	public static BigDecimal subtract(BigDecimal value1, BigDecimal value2) {
		return value1.subtract(value2);
	}
	
	/**
	 * 减法运算,使用指定精度返回
	 * @param value1
	 * @param value2
	 * @param precision
	 * @param roundingMode
	 * @return value1 - value2
	 */
	public static BigDecimal subtract(BigDecimal value1, BigDecimal value2,
			Integer precision, RoundingMode roundingMode) {
		if (precision == null && roundingMode == null) {
			return subtract(value1, value2);
		}
		return setScale(value1.subtract(value2), precision, roundingMode);
	}
	
	/**
	 * 乘法运算,使用两个参数精度相加为返回精度
	 * @param value1
	 * @param value2
	 * @return value1 * value2
	 */
	public static BigDecimal multiply(BigDecimal value1, BigDecimal value2) {
		return value1.multiply(value2);
	}
	
	/**
	 * 乘法运算,使用指定精度返回
	 * @param value1
	 * @param value2
	 * @param precision
	 * @param roundingMode
	 * @return value1 * value2
	 */
	public static BigDecimal multiply(BigDecimal value1, BigDecimal value2,
			Integer precision, RoundingMode roundingMode) {
		if (precision == null && roundingMode == null) {
			return multiply(value1, value2);
		}
		return setScale(value1.multiply(value2), precision, roundingMode);
	}
	
	/**
	 * 除法运算,因为可能存在结果小数无限膨胀问题,默认使用4位精度进行四舍五入截断
	 * @param value1
	 * @param value2
	 * @return value1 / value2
	 */
	public static BigDecimal divide(BigDecimal value1, BigDecimal value2) {
		return value1.divide(value2, DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE);
	}
	
	/**
	 * 除法运算,使用指定精度返回
	 * @param value1
	 * @param value2
	 * @param precision
	 * @param roundingMode
	 * @return value1 / value2
	 */
	public static BigDecimal divide(BigDecimal value1, BigDecimal value2,
			Integer precision, RoundingMode roundingMode) {
		if (precision == null && roundingMode == null) {
			return divide(value1, value2);
		}
		
		int tempPrecision = DEFAULT_PRECISION;
		RoundingMode tempRoundingMode = DEFAULT_ROUNDING_MODE;
		if (precision != null && precision.intValue() > 0) {
			tempPrecision = precision.intValue();
		}
		if (roundingMode != null) {
			tempRoundingMode = roundingMode;
		}
		
		return value1.divide(value2, tempPrecision, tempRoundingMode);
	}
	
	private static BigDecimal setScale(BigDecimal result, Integer precision,
			RoundingMode roundingMode) {
		int tempPrecision = DEFAULT_PRECISION;
		RoundingMode tempRoundingMode = DEFAULT_ROUNDING_MODE;
		if (precision != null && precision.intValue() > 0) {
			tempPrecision = precision.intValue();
		}
		if (roundingMode != null) {
			tempRoundingMode = roundingMode;
		}
		return result.setScale(tempPrecision,tempRoundingMode);
	}
	
	
	/*public static void main(String[] args) {
		System.out.println(add(new BigDecimal("0.0122"), new BigDecimal("0.0003")));
		System.out.println(add(new BigDecimal("0.0122"), new BigDecimal("0.0003"), -1, null));
		System.out.println(divide(new BigDecimal("1"), new BigDecimal("3"), -1, RoundingMode.HALF_UP));
	}*/

}
