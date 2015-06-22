/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>Md5.java</p>
 */
package com.happy.exam.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月18日 上午2:49:34 
 */
public class Md5 {

	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	/**
	 * 对字符串进行MD5加密
	 *
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		String md5Str = null;
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes("UTF-8"));
			md5Str = new String(byte2Hex(msgDigest.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("System doesn't support your EncodingException.");
		}
		return md5Str;
	}

	/**
	 * 用来把一个byte类型的数转换成十六进制的ASCII表示.
	 * 
	 * @param data
	 * @return
	 */
	private static char[] byte2Hex(byte[] bytes) {
		int l = bytes.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
			out[j++] = DIGITS[0x0F & bytes[i]];
		}
		return out;
	}

}
