package com.java.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.blog.dao.BlogDao;
import com.java.blog.entity.Blog;
import com.java.blog.service.BlogService;

@Service
public class BlogServiceImpl extends SuperServiceImpl<BlogDao, Blog> implements BlogService {

	@Resource
	private BlogDao blogDao;

	@Override
	/* @Cacheable(cacheNames = "blog", key = "#pageNum") */
	public PageInfo<Blog> findByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Blog> list = blogDao.selectList(new EntityWrapper<Blog>(null));
		PageInfo<Blog> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
