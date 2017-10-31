package com.blog.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//测试视图
@Controller
public class TestView {

	@RequestMapping(value = "/testview")
	public String testView() {

		return "index";
	}

}
