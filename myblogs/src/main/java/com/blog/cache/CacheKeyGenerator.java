package com.blog.cache;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.python.google.common.hash.Hashing;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         自定义Cache的key生成策略
 * 
 */
@Component("cacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

	private static Logger logger = Logger.getLogger(CacheKeyGenerator.class);

	public static final int NO_PARAM_KEY = 0;
	public static final int NULL_PARAM_KEY = 53;

	public Object generate(Object target, Method method, Object... params) {

		StringBuilder key = new StringBuilder();
		key.append(target.getClass().getSimpleName()).append(".").append(method.getName()).append(":");
		if (params.length == 0) {
			return key.append(NO_PARAM_KEY).toString();
		}
		for (Object param : params) {
			if (param == null) {
				logger.info("input null param for Spring cache, use default key={}" + NULL_PARAM_KEY);
				key.append(NULL_PARAM_KEY);
			} else if (ClassUtils.isPrimitiveArray(param.getClass())) {
				int length = Array.getLength(param);
				for (int i = 0; i < length; i++) {
					key.append(Array.get(param, i));
					key.append(',');
				}
			} else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
				key.append(param);
			} else {
				logger.warn("Using an object as a cache key may lead to unexpected results. "
						+ "Either use @Cacheable(key=..) or implement CacheKey. Method is " + target.getClass() + "#"
						+ method.getName());
				key.append(param.hashCode());
			}
			key.append('-');
		}
		String finalKey = key.toString();
		long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset()).asLong();
		logger.debug("using cache key={} hashCode={}[" + finalKey + "][" + cacheKeyHash + "]");
		// 调用本类方法将可以传入
		getKey(key.toString());
		return key.toString();
	}

	// 拿到生产的key作为查询规则 TODO:并发场景下有待测试
	public static String getKey(String... KeyGenerator) {
		String[] key = KeyGenerator != null ? KeyGenerator : null;
		return key.toString();
	}
}
