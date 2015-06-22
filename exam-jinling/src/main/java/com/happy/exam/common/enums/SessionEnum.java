package com.happy.exam.common.enums;

/**
 * session枚举类
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:23:15
 */
public enum SessionEnum {

	/* 当前登陆用户的存储在session中的key名 */
	SESSION("currentUser");

	private String key;

	private SessionEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
