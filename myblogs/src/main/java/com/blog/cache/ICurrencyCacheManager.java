package com.blog.cache;

import com.blog.exception.CacheException;

public interface ICurrencyCacheManager {

	// 获取缓存中的数据
	Object getFromCache(String cacheName, String key) throws CacheException;

	// 添加缓存
	boolean putCache(String cacheName, String key, Object value) throws CacheException;

	// 删除缓存
	boolean evictFromCache(String cacheName, String key) throws CacheException;
}
