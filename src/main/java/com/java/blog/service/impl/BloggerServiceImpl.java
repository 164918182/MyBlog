package com.java.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.blog.dao.BloggerDao;
import com.java.blog.service.BloggerService;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

	@Resource
	private BloggerDao bloggerDao;

}
