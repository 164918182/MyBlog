package com.java.blog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

/**@author TaoYu
 * @Description 配置文件工具类
 */
@Log4j
public class PropertiesUtil {

	private PropertiesUtil() {
	}

	public static Properties getProperties(String path) {
		Properties p = null;
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		return p;
	}
}
