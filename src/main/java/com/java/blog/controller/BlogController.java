package com.java.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.java.blog.entity.Blog;
import com.java.blog.exception.DocException;
import com.java.blog.exception.IndexWriterCloseException;
import com.java.blog.exception.ParamException;
import com.java.blog.service.BlogService;
import com.java.blog.utils.LuceneUtil;
import com.java.blog.utils.ResponseJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api("user-api")
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@ApiOperation(value = "博客首页", notes = "分页查找带缓存打开@Cacheable", response = Blog.class)
	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseJson blogs(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)
			throws ParamException {
		ResponseJson json = new ResponseJson();
		PageInfo<Blog> pageInfo = this.blogService.findByPage(pageNum, pageSize);
		json.setPage(pageInfo);
		return json;
	}

	@ApiOperation(value = "全文检索", notes = "根据现有数据写索引", response = ResponseJson.class)
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseJson search(@RequestParam String info) throws Exception {
		ResponseJson json = new ResponseJson();
		List<Blog> blogs = LuceneUtil.searchBlog(info, 100);
		json.setResults(blogs);
		return json;
	}

	@ApiIgnore
	@ApiOperation(value = "写索引", notes = "根据现有数据写索引", response = ResponseJson.class)
	@RequestMapping(value = "/writeIndex", method = RequestMethod.GET)
	public ResponseJson writeIndex() throws IndexWriterCloseException, DocException {
		ResponseJson json = new ResponseJson();
		List<Blog> lists = this.blogService.selectList(new Blog());
		for (Blog blog : lists) {
			LuceneUtil.addIndex(blog);
		}
		return json;
	}
}
