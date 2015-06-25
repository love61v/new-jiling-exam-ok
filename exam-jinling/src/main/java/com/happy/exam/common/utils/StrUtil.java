package com.happy.exam.common.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具类
 *
 */
public class StrUtil {
	
	public final static String REG_AZ = "\\([A-Za-z]+\\)"; //匹配(ABc)
 
	public final static String REG_ALL = "\\((.*?)\\)"; //匹配1个括号与其中的内容(ABc在所有)
	public final static String REG_NUMBER = "([1-9]+|[0-9]*|0)(\\.{1}[0-9]{1})?"; //匹配正数,1位小数
	public final static String REG_MORE ="\\([^\\(\\)]*(\\(.*?\\)[^\\(\\)]*)*\\)\\w?[, ]*"; //多层括号匹配(ABc(在)所有)
	public final static String REG_S = "/\\s+|\\s|/g/";    //清除空格
	
	/**
	 * 全角转半角
	 * @param src
	 * @return
	 */
	public static String toSemiangle(String src) {
		char[] c = src.toCharArray();
		for (int index = 0; index < c.length; index++) {
			if (c[index] == 12288) {// 全角空格
				c[index] = (char) 32;
			} else if (c[index] > 65280 && c[index] < 65375) {// 其他全角字符
				c[index] = (char) (c[index] - 65248);
			}
		}
		return String.valueOf(c);
	}
	
	/**
	 * 将半角括号替换空
	 * @param content
	 * @return
	 */
	public static String replaceBracketNull(String content){
		return content.replaceAll("\\(", "").replaceAll("\\)", "").trim();
	}
	
	/**
	 * 排序字母 ,都转换大写
	 * bacAe 排序后AABCE
	 * @param value
	 * @return
	 */
	public static String sortCharater(String value) {
		StringBuffer sbf = new StringBuffer("");
		char[] charArray = value.toUpperCase().toCharArray();
		Arrays.sort(charArray);
		for (char c : charArray) {
			sbf.append(String.valueOf(c));
		}

		return sbf.toString();
	}
	

	private static String get(String subStr) {
		StringBuffer sbf = new StringBuffer();
	    String result = "";
		String regex= "\\([^\\(\\)]*(\\(.*?\\)[^\\(\\)]*)*\\)\\w?[, ]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(subStr);
		int i =0;
		while (matcher.find()) {
		  result = matcher.group(0);
		  result = result.replaceAll("\\)", "");
		  result = result.replaceFirst("\\(", "");
		  result = result.replaceFirst("\\(", "|");
		  if(i > 0){
			  sbf.append(";");
		  }
		  sbf.append(result);
		  i++;
		}
		 
		return sbf.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(toSemiangle("西太平洋动路径主, ,，！！！￥￥%%要可以分为（   DSF   ）台风的移动路径主要可以分为（   ABD   ）"));
	}

}
