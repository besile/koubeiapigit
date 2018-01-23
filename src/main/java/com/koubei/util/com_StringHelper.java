package com.koubei.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class com_StringHelper {
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
				.toString().length()) + 1) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 格式化为“yyyy-MM-dd”的字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String toShortDate(java.util.Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return (null == date) ? "" : df.format(date);
	}

	/**
	 * 格式化为“yyyy-MM-dd HH:mm:ss”的字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String toLongDate(java.util.Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (null == date) ? "" : df.format(date);
	}

	/**
	 * 格式化为“yyyy-MM-dd HH:mm:ss”的字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String toLongDate(String date) {
		if (date != null)
			return date.substring(0, 19);
		else
			return "";
	}

	/**
	 * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}
		int len = str.length();
		if (len == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					// case '\b':
					// case '\f':
					break;
				default:
					return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转int
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Integer toInteger(String value, Integer defaultValue) {
		if (value != null) {
			return Integer.parseInt(value.trim());
		}
		return defaultValue;
	}
}
