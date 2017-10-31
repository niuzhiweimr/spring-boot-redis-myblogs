package com.blog.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.blog.exception.CacheException;

@Component("currencyCacheManager")
public class CurrencyCacheManager implements ICurrencyCacheManager {

	@Autowired
	private CacheManager cacheManager;

	/**
	 * 获取缓存内容 加入缓存时可设置key的缓存时间
	 * 
	 * @param cache
	 * @param key
	 * @return
	 */
	public Object getFromCache(String cacheName, String key) throws CacheException {
		boolean isNull = StringUtils.isEmpty(cacheName.trim()) || StringUtils.isEmpty(key.trim()) ? true : false;
		if (isNull)
			throw new CacheException("获取缓存必要参数不可空");
		Cache cache = cacheManager.getCache(cacheName);
		final Cache.ValueWrapper valueWrapper = cache.get(key);
		return null == valueWrapper ? null : valueWrapper.get();
	}

	/**
	 * 设置缓存数据
	 * 
	 * @param cache
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putCache(String cacheName, String key, Object value) throws CacheException {
		// 做空值判斷
		boolean isNull = StringUtils.isEmpty(cacheName.trim()) || StringUtils.isEmpty(key.trim()) || value == null ? true
				: false;
		if (isNull)
			throw new CacheException("存入缓存必要值不能空");
		// 加入缓存
		Cache cache = cacheManager.getCache(cacheName);
		// 查找key是否在缓存中存在
		Object obj = getFromCache(cacheName, key);
		if (obj != null) {
			// 如果存在删除key
			boolean delFlag = evictFromCache(cacheName, key);
			if (!delFlag) {
				throw new CacheException("存入缓存失败");
			}
		}
		cache.put(key, value);
		return true;
	}

	/**
	 * 删除缓存数据
	 * 
	 * @param cache
	 * @param key
	 * @return
	 */
	public boolean evictFromCache(String cacheName, String key) throws CacheException {

		boolean isNull = StringUtils.isEmpty(cacheName.trim()) || StringUtils.isEmpty(key.trim()) ? true : false;
		if (isNull)
			throw new CacheException("删除值不能空");
		// 加入缓存
		Cache cache = cacheManager.getCache(cacheName);
		Object obj = getFromCache(cacheName, key);
		if (obj != null) {
			cache.evict(key);
			return true;
		} else
			return false;
	}

}
