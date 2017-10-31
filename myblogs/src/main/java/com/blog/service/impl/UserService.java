package com.blog.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.blog.cache.*;
import com.blog.eto.UserEto;
import com.blog.exception.*;
import com.blog.mapper.IUserMapper;
import com.blog.model.UserEbo;
import com.blog.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	private Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private IUserMapper userMapper;
	@Autowired
	private ICurrencyCacheManager currencyCacheManager;

	private static final String USER_CACHE = "userCache";

	// value设置缓存时间 keyGenerator自定义生产key的类 需要加入扫描
	@Cacheable(value = "300s", keyGenerator = "cacheKeyGenerator")
	public UserEbo getUserById(Integer uid) throws DataException {
		logger.info("产生的key：" + CacheKeyGenerator.getKey().toString());
		if (uid <= 0)
			return null;
		UserEbo user = null;
		try {
			user = (UserEbo) currencyCacheManager.getFromCache(USER_CACHE, CacheKeyGenerator.getKey());
			if (user != null) {
				logger.info("从缓存中获取了");
				return user;
			} else {
				logger.info("从数据库中获取了");
				user = userMapper.getUserById(uid);
				if (user != null)
					currencyCacheManager.putCache(USER_CACHE, CacheKeyGenerator.getKey(), user);
				else
					return null;
				return user;
			}
		} catch (CacheException e) {

			e.printStackTrace();
		}
		return null;
	}

	public Integer regUser(UserEto user) throws DataException {

		return null;
	}

}
