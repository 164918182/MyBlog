package com.java.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("博客")
public class Blog implements Serializable {

	private static final long serialVersionUID = 24077686880928000L;

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("博客标题")
	private String title;
	
	@ApiModelProperty("简介")
	private String summary;
	
	@ApiModelProperty("简介")
	private String keyWord;

	@ApiModelProperty("博客内容")
	private String content;

	@ApiModelProperty("发布日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;

	@ApiModelProperty("查看次数")
	private Integer clickHit;

	@ApiModelProperty("回复次数")
	private Integer replyHit;

	@ApiModelProperty("博客类型")
	private BlogType blogType;

	public Blog(Integer id, String title, Date releaseDate, String content) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.content = content;
	}

}
