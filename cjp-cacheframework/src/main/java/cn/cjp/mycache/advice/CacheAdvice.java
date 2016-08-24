package cn.cjp.mycache.advice;

import java.lang.reflect.Method;

import cn.cjp.mycache.cache.CacheDao;

public interface CacheAdvice {
	Object updateOrFindCache(Method method, Object[] args);
	
	/**
	 * 查询缓存库为空时，将数据库查询结果存入缓存库
	 * @param method
	 * @param args
	 * @param obj
	 */
	void saveCache(Method method, Object[] args, Object obj);
	
	public void setCacheDao(CacheDao cacheDao);
}
