package com.java.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.blog.dao.BlogDao;
import com.java.blog.entity.Blog;
import com.java.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;

	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id);
	}

	@Override
	@Cacheable(cacheNames = "blog", key = "#pageNum")
	public PageInfo<Blog> findByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Blog> list = blogDao.list();
		PageInfo<Blog> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Blog> getAllBlogs() {
		List<Blog> lists = blogDao.list();
		return lists;
	}

}
