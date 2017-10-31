package com.blog.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.DataException;
import com.blog.service.IUserService;

//测试数据库数据连接
@RestController
public class TestData {

	@Autowired
	private IUserService userService;

	private Logger logger = Logger.getLogger(TestData.class);

	@RequestMapping("/testdata")
	public String getUserNameByuid() {

		try {
			return userService.getUserById(1).getUserName();
		} catch (DataException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
