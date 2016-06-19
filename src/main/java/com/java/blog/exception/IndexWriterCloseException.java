package com.java.blog.exception;

/**@author TaoYu
 * @Description 关闭写索引流异常
 */
public class IndexWriterCloseException extends Exception {

	private static final long serialVersionUID = 3563700966561142614L;

	public IndexWriterCloseException() {
		super("关闭写索引流失败，原因未知");
	}
}
