package com.java.blog.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;
import com.java.blog.page.ResponsePage;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseJson implements Serializable {

	private boolean success = true;

	private String msg = "操作成功";

	private Map<String, Object> attributes = new HashMap<>();// 不同类型的数据

	private List<?> results;// 单纯的放集合数据

	private ResponsePage page;// 需要分页的数据

	public ResponseJson() {
		super();
	}

	public ResponseJson(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public void setPage(PageInfo<?> pageInfo) {
		this.page = new ResponsePage(pageInfo);
	}

}
