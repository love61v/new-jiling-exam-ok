package com.happy.exam.common.utils;

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
	 * 全角括号替换为半角
	 * @param content
	 * @return
	 */
	public static String replaceBracket(String content){
		return content.replaceAll("\\（", "(").replaceAll("\\）", ")").replaceAll(StrUtil.REG_S, "");
	}
	
	/**
	 * 将半角括号替换空
	 * @param content
	 * @return
	 */
	public static String replaceBracketNull(String content){
		return content.replaceAll("\\(", "").replaceAll("\\)", "").trim();
	}
	
	public static void main(String[] args) {
		String str = replaceBracket("aa夺标梦想成真（a）放声大哭果园（ab）sdfdsfs");
		System.out.println(str);
		
		String subStr = "aaaadsfsfd疏散槽是(加深(加强))的sfdsfsdfi在无(在无(在有))可奈何花落去d";
		String regex= "\\([^\\(\\)]*(\\(.*?\\)[^\\(\\)]*)*\\)\\w?[, ]*";
		
		String content = get(subStr);
		
		System.out.println(content);
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
}
