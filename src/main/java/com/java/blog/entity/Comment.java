package com.java.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Comment implements Serializable {

	private static final long serialVersionUID = 263673981856247655L;

	private Integer id; // 编号

	private String userIp; // 用户IP

	private String content; // 评论内容

	private Blog blog; // 被评论的博客

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date commentDate; // 评论日期

	private Integer state; // 审核状态 0 待审核 1 审核通过 2 审核未通过

}
