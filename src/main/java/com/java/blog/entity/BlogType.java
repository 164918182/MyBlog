package com.java.blog.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogType implements Serializable {

	private static final long serialVersionUID = 2957126148318018214L;

	private Integer id; // 编号

	private String typeName; // 博客类型名称

	private Integer blogCount; // 数量

	private Integer orderNo; // 排序 从小到大排序显示

}
