package com.java.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.java.blog.entity.Blog;
import com.java.blog.exception.ParamException;
import com.java.blog.service.BlogService;
import com.java.blog.utils.ErrorUtil;
import com.java.blog.utils.LuceneUtil;
import com.java.blog.utils.ResponseJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("user-api")
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@ApiOperation(value = "博客首页", notes = "分页查找带缓存", response = Blog.class)
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseJson blogs(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)
			throws ParamException {
		ResponseJson json = new ResponseJson();
		PageInfo<Blog> pageInfo = this.blogService.findByPage(pageNum, pageSize);
		json.setPage(pageInfo);
		return json;
	}

	@ApiOperation(value = "写索引", notes = "根据现有数据写索引", response = ResponseJson.class)
	@RequestMapping(value = "/indexBlogs", method = RequestMethod.GET)
	public ResponseJson indexBlogs() {
		ResponseJson json = new ResponseJson();
		List<Blog> lists = this.blogService.getAllBlogs();

		for (Blog blog : lists) {
			try {
				LuceneUtil.addIndex(blog);
			} catch (Exception e) {
				ErrorUtil.logError(e, json);
			}
		}
		return json;
	}

	@ApiOperation(value = "全文检索", notes = "根据现有数据写索引", response = ResponseJson.class)
	@RequestMapping(value = "/getBlog", method = RequestMethod.POST)
	public ResponseJson getBlog(@RequestParam String info) throws Exception {
		ResponseJson json = new ResponseJson();
		List<Blog> blogs = LuceneUtil.searchBlog(info, 100);
		json.setResults(blogs);
		return json;
	}

}
