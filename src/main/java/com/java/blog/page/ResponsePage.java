package com.java.blog.page;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("返回的分页")
public class ResponsePage implements Serializable {
	
	@ApiModelProperty("页码")
	private Integer pageNum;

	@ApiModelProperty("分页大小")
	private Integer pageSize;
	
	@ApiModelProperty("总页码")
	private Integer totalPage;
	
	@ApiModelProperty("总数据条数")
	private long totalRecords;

	@ApiModelProperty("返回结果")
	private List<?> results;

	public ResponsePage(PageInfo<?> pageInfo) {
		this.pageNum = pageInfo.getPageNum();
		this.pageSize = pageInfo.getPageSize();
		this.totalPage=pageInfo.getPages();
		this.totalRecords = pageInfo.getTotal();
		this.results = pageInfo.getList();
	}

}
