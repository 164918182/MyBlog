package com.java.blog.exception;

/**@author TaoYu
 * @Description 参数异常
 */
public class ParamException extends Exception {

	private static final long serialVersionUID = 3563700966561142614L;

	public ParamException() {
		super("传入参数错误");
	}
}
