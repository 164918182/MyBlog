package com.java.blog.utils;

import lombok.extern.log4j.Log4j;

@Log4j
public class ErrorUtil {
	public static void logError(Exception e, ResponseJson json) {
		json.setMsg(e.getMessage());
		json.setSuccess(false);
		log.error(e.getMessage());
		EmailUtil.sendSimpleEmail(e);
	}
}
