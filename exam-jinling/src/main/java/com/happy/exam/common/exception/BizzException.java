/*
 * Beijing happy Information Technology Co,Ltd.
 * All rights reserved.
 * 
 * <p>BizzException.java</p>
 */
package com.happy.exam.common.exception;

/**
 * 业务异常处理类
 *
 * @version : Ver 1.0
 * @author : <a href="mailto:hubo@95190.com">hubo</a>
 * @date : 2015年5月17日 下午9:48:03
 */
public class BizzException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BizzException() {
		super();
	}

	public BizzException(String message) {
		super(message);
	}

	public BizzException(Throwable cause) {
		super(cause);
	}

	public BizzException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizzException(String message, Throwable cause, String code,
			Object[] values) {
		super(message, cause, code, values);
	}
}
