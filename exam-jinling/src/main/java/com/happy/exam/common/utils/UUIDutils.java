/*
 * Beijing YESWAY Information Technology Co,Ltd.
 * All rights reserved.
 * 北京九五智驾信息技术股份有限公司
 * <p>UUID.java</p>
 */
package com.happy.exam.common.utils;

import java.util.UUID;

/**
 * UUID生成工具类.
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:hubo@95190.com">hubo</a>
 * @date	: 2015年5月17日 下午5:47:48 
 */
public class UUIDutils {

	/**
	 * 获取UUID
	 *
	 * @author 	: <a href="mailto:hubo@95190.com">hubo</a>  2015年5月17日 下午5:50:25
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}
	
	public static void main(String[] args) {
		String r = UUIDutils.getUUID();
		System.out.println(r);
	}
}
