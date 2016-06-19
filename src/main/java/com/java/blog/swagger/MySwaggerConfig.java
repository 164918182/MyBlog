package com.java.blog.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class MySwaggerConfig {
	@Bean
	public Docket swaggerSpringMvcPlugin() {
	
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.groupName("test")
				.select()
				.build()
				.apiInfo(apiInfo());
		return docket;
	}

	private ApiInfo apiInfo() {
		return 	new ApiInfoBuilder()
		.title("博客系统API接口管理")
		.description("各个controller下对应相应业务接口")
		.termsOfServiceUrl("http://wwww.baidu.com")
		.contact(new Contact("陶宇", "ChengDu", "332309254@qq.com"))
		.license("Apache License Version 2.0")
		.licenseUrl("https://github.com/")
		.version("2.0")
		.build();
	}
	

}