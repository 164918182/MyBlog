package com.java.blog.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.java.blog.entity.Blog;

public interface BlogService {

	Blog findById(Integer id);

	PageInfo<Blog> findByPage(Integer pageNum, Integer pageSize);

	List<Blog> getAllBlogs();

}
