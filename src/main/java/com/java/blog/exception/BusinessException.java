package com.java.blog.exception;

/**@author TaoYu
 * @Description 业务异常
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 3563700966561142614L;

	public BusinessException(String msg) {
		super(msg);
	}
}
