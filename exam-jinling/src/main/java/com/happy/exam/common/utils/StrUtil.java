package com.happy.exam.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具类
 *
 */
public class StrUtil {
	
	public final static String REG_AZ = "\\([A-Za-z]+\\)"; //匹配(ABc)
	public final static String REG_ALL = "\\((.*?)\\)"; //匹配(ABc在所有)
	public final static String REG_More ="\\([^\\(\\)]*(\\(.*?\\)[^\\(\\)]*)*\\)\\w?[, ]*";; //匹配(ABc在所有)
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
		
		String subStr = "aaaadsfsfd疏散槽是(加深(加强))的sfdsfsdf(a.id(b(c(d(e(f))))))i在无可奈何花落去d";
		String regex= "\\([^\\(\\)]*(\\(.*?\\)[^\\(\\)]*)*\\)\\w?[, ]*";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(subStr);
		while (matcher.find()) {
		  System.out.println("matcher: " + matcher.group(0));
		}
	}
}
