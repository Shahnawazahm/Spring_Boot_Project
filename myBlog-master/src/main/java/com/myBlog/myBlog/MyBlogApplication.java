package com.myBlog.myBlog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//default config class
@SpringBootApplication
public class MyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}
	/*
	  ---Once this method/application is run---
	  Postman/view will call Controller,
	  Controller will call Service,
	  Service will save the data and return back DTO object to Controller,
	  Controller takes a DTO object and gives it back to view/postman.
	 */

	/*
	To create an object for third party library, we need to let spring now about that so, it can create object for it.
	For that we need to specify under @Bean annotation for which library we need to set up the bean.
	 */
	@Bean
	public ModelMapper getModelMappedBean(){
		return new ModelMapper();
	}
}