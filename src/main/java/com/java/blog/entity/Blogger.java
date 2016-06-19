package com.java.blog.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Blogger implements Serializable {

	private static final long serialVersionUID = -3509330899475885362L;

	private Integer id; // 编号

	private String userName; // 用户名

	private String password; // 密码

	private String nickName; // 昵称

	private String sign; // 个性签名

	private String proFile; // 个人简介

	private String imageName; // 博主头像

}
