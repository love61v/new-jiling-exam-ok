package com.happy.exam.common.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 常量类
 * @author Administrator
 *
 */
public class ConstantsUtil {

	public static Map<String, String> educationMap = new TreeMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put("小学", "小学");
			put("初中", "初中");
			put("高中", "高中");
			put("专科", "专科");
			put("本科", "本科");
			put("研究生", "研究生");
			put("硕士", "硕士");
			put("博士", "博士");
			put("博士后", "博士后");
		}
	};
}
