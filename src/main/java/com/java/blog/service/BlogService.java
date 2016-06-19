package com.java.blog.service;

import com.baomidou.framework.service.ISuperService;
import com.github.pagehelper.PageInfo;
import com.java.blog.entity.Blog;

public interface BlogService extends ISuperService<Blog>  {


	PageInfo<Blog> findByPage(Integer pageNum, Integer pageSize);
	

}
