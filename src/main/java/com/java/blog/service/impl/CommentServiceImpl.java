package com.java.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.blog.dao.CommentDao;
import com.java.blog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;

}
