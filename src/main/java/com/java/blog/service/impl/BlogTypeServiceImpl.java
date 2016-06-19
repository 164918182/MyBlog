package com.java.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.blog.dao.BlogTypeDao;
import com.java.blog.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

}
