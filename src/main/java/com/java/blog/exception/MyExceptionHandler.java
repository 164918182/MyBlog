package com.java.blog.exception;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Throwables;
import com.java.blog.utils.ResponseJson;

import lombok.extern.log4j.Log4j;

/**@author TaoYu
 * @Description 自动异常处理
 */
@Log4j
@ControllerAdvice("com.java.blog.controller")
public class MyExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseJson exception(Exception e, WebRequest request) {
		String message = Throwables.getRootCause(e).getMessage();
		log.error(e);
		return new ResponseJson(false, message);
	}

	/**@Describe：参数异常，返回给前台传入的参数和错误信息
	 */
	@ExceptionHandler(value = ParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseJson paramException(ParamException e, WebRequest request) {
		StringBuilder message = new StringBuilder(e.getMessage());
		log.error(e);
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			message.append(entry.getKey()).append("=");
			String[] values = entry.getValue();
			for (int i = 0; i < values.length; i++) {
				message.append(values[i]).append("&");
			}
		}
		message.deleteCharAt(message.length() - 1);
		return new ResponseJson(false, message.toString());
	}

	/**@Describe：未传递参数异常
	 */
/*	@ExceptionHandler(value = ParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseJson missingServletRequestParameterException(MissingServletRequestParameterException e,
			WebRequest request) {
		StringBuilder message = new StringBuilder("需要参数:");
		log.error(e);
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			if (StringUtils.isNotEmpty(entry.getKey())) {
				message.append(entry.getKey()).append(",");
			}
		}
		message.deleteCharAt(message.length() - 1);
		return new ResponseJson(false, message.toString());
	}*/

	@ExceptionHandler(value = BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseJson businessException(BusinessException e, WebRequest request) {
		String message = e.getMessage();
		log.error(e);
		return new ResponseJson(false, message);
	}

	@ExceptionHandler(value = IOException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseJson iOException(IOException e, WebRequest request) {
		String message = e.getMessage();
		log.error(e);
		return new ResponseJson(false, message);
	}
}
