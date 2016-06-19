package com.java.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("blog")
public class Blog implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
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

	@TableField(exist = false)
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
