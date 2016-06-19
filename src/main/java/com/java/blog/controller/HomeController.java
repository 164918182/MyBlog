package com.java.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

/**@author TaoYu
 * @Description 主控制器，简化跳转
 */
@Controller
@ApiIgnore
public class HomeController {

	/**@Describe：重定向访问swaggerAPI
	 */
	@RequestMapping("/api")
	public ModelAndView api(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("redirect:/swagger-ui.html");
		return view;
	}

	/**@Describe：访问DRUID监控
	 */
	@RequestMapping("/druid")
	public ModelAndView druid() {
		ModelAndView view = new ModelAndView("druid/index.html");
		return view;
	}
}
