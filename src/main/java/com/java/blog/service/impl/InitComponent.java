/*package com.java.blog.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.java1234.entity.Blog;
import com.java1234.entity.BlogType;
import com.java1234.entity.Blogger;
import com.java1234.entity.Link;
import com.java1234.service.BlogService;
import com.java1234.service.BlogTypeService;
import com.java1234.service.BloggerService;
import com.java1234.service.LinkService;

*//**
 * ��ʼ����� �Ѳ�����Ϣ ��ݲ�����������Ϣ ������ڹ鵵������Ϣ
 * ��ŵ�application�У������ṩҳ����������
 * 
 * @author Administrator
 * 
 *//*
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.find();
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList = blogTypeService.countList();
		application.setAttribute("blogTypeCountList", blogTypeCountList);

		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList();
		application.setAttribute("blogCountList", blogCountList);

		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null);
		application.setAttribute("linkList", linkList);
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
*/