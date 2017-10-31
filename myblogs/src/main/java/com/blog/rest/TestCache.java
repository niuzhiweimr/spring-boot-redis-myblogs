package com.blog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.cache.ICurrencyCacheManager;
import com.blog.exception.CacheException;
import com.blog.model.UserEbo;

//测试redis缓存
@RestController
public class TestCache {

	@Autowired
	private ICurrencyCacheManager currencyCacheManager;

	private static final String TEST = "TEST";

	@RequestMapping("/testAddStr")
	public String addRedisStr() {
		UserEbo user = new UserEbo();
		user.setEmail("123");
		user.setMobileNum("12435");
		try {
			currencyCacheManager.putCache(TEST, "HELLO", user);
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "添加成功";
	}

	@RequestMapping("/testGetStr")
	public Object getRedisStr() {
		UserEbo user = null;
		try {

			user = (UserEbo) currencyCacheManager.getFromCache(TEST, "HELLO");
		} catch (CacheException e) {

			e.printStackTrace();
		}
		return user;
	}

}
