
package com.java.blog.page;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Getter;


/**@author TaoYu
 * @Description 如果以对象形式上传分页信息用这个
 */
@ApiModel("上传分页")
@Getter
public class ParamPage implements Serializable {

	private static final long serialVersionUID = 4400465065502807974L;

	private final static int DEFAULT_PAGE_NUM = 1;

	private final static int DEFAULT_PAGE_SIZE = 10;

	private Integer pageNum;

	private Integer pageSize;

	private ParamPage() {
		this.pageNum = DEFAULT_PAGE_NUM;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	public void setPageNum() {
		if (pageNum == null || pageNum < 1) {
			this.pageNum = DEFAULT_PAGE_NUM;
		}
	}

	public void setPageSize() {
		if (pageSize == null || pageSize < 1) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
	}
}