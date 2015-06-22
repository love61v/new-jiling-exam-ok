package com.happy.exam.common.enums;

/**
 * session枚举类
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午5:23:15
 */
public enum ResponseCodeEnum {

	/* 响应状态码 */
	CODE_0(0),
	CODE_200(200),
	CODE_400(400),
	CODE_500(500);

	private int key;

	private ResponseCodeEnum(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
