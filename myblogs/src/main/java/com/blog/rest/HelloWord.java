package com.blog.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//helloword例子
@RestController
public class HelloWord {

	@RequestMapping(value = "/helloWord")
	public String test() {

		return "login";
	}
}
