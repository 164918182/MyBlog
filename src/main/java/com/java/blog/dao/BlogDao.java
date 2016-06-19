package com.java.blog.dao;

import java.util.List;

import com.java.blog.entity.Blog;

public interface BlogDao {

	Blog findById(Integer id);

	List<Blog> list();

}
